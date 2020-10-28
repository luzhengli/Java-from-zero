import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

public class Instance {
    public static void main(String[] args) {
        // System.out.println(Math.E);
        // System.out.println(Math.cos(Math.PI / 3));

        // for (int i = 0; i < 10; i++) {
        // System.out.println(Math.random());
        // }

        // 安全随机数
        SecureRandom sr = null;
        try {
        sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
        sr = new SecureRandom();
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));

        // 安全随机数生成时间 vs Random伪随机数生成时间
        // SecureRandom sr = null;

        // try {

        //     sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器

        // } catch (NoSuchAlgorithmException e) {

        //     sr = new SecureRandom(); // 获取普通的安全随机数生成器

        // }

        // byte[] buffer = new byte[16];

        // byte[] buff = new byte[16];

        // Random s = new Random(128);

        // double t1 = System.nanoTime();

        // sr.nextBytes(buffer); // 用安全随机数填充buffer

        // double t2 = System.nanoTime();

        // s.nextBytes(buff);

        // double t3 = System.nanoTime();

        // System.out.println(t2 - t1);

        // System.out.println(t3 - t2);

    }

}