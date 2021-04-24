import java.util.Random;

/**
 * @author:liuzidi
 * @Description:
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        Random random1 = new Random();
        int a = random.nextInt(100);
        int b = random1.nextInt(100);
        System.out.println(a);
        System.out.println(b);

    }

}
