public class calTax {
    public static void main(final String[] args) {
        Income[] incomes = new Income[] { new Income(3000), new Salary(7500), new StateCouncilSpecialAllowance(15000) }; // 这个初始化相当于三次向上转型；
        
        System.out.println(totalTax(incomes));   


    }

    public static double totalTax(Income... incomes) {  // 必须把 totalTax 定义为 static 方法
        double total = 0;
        for (Income icome : incomes) {
            total += icome.getTax();
        }
        return total;
    }
}

class Income {
    protected double income;

    public Income(double income) { // 父类要创建构造方法
        this.income = income;
    }

    public double getTax() {
        return income * 0.1;
    }
}

class Salary extends Income {
    public Salary(double income) {
        super(income); // 子类要先调用父类构造方法
    }

    @Override
    public double getTax() {
        if (income <= 5000) { // income 是子类的属性还是父类的属性？
            return 0;
        } else {
            return (income - 5000) * 0.2;
        }
    }
}

class StateCouncilSpecialAllowance extends Income {
    public StateCouncilSpecialAllowance(double income) {
        super(income); // 子类要先调用父类构造方法
    }

    @Override
    public double getTax() {
        return 0;
    }
}
