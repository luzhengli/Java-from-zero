import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class App {
    public static void main(String[] args) throws Exception {
        // 创建2000个随机数组成的数组:
        long[] array = new long[2000];
        long expectedSum = 0;
        // for循环：串行解决方法
        for (int i = 0; i < array.length; i++) {
            array[i] = random(); // 数组添加随机数元素
            expectedSum += array[i];
        }
        System.out.println("Expected sum: " + expectedSum);

        // fork/join: 并行解决方法
        ForkJoinTask<Long> task = new SumTask(array, 0, array.length); // 创建ForkJoin任务

        // 使用并行方法解决求和问题 并计算消耗的时间
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task); // 创建 ForkJoin线程池 调用invoke()方法并传入ForkJoin任务
                                                              // 获得结果(类型由定义任务类的代码决定)
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    static Random random = new Random(0); // 伪随机数生成器

    static long random() { // 随机数生成静态函数（生成0~10000）之间的数
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long> { // 定义ForkJoin任务类 必须继承RecursiveTask抽象类 且重写compute方法
    static final int THRESHOLD = 500; // 任务量阈值 超过阈值则划分任务
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() { // ForkJoin的核心方法 
        // 任务不大 直接执行
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
                // 故意放慢计算速度:
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            return sum;
        }
        
        // 任务太大 一分为二
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));

        // 定义两个子任务 然后分别执行
        SumTask subtask1 = new SumTask(this.array, start, middle);
        SumTask subtask2 = new SumTask(this.array, middle, end);
        invokeAll(subtask1, subtask2); // 执行两个子任务
        Long subresult1 = subtask1.join(); // 获取子任务1的结果
        Long subresult2 = subtask2.join(); // 获取子任务2的结果
        Long result = subresult1 + subresult2; // 合并子任务的结果
        System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
        
        // 返回整个任务的结果
        return result;
    }
}
