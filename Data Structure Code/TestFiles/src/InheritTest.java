/**
 * @author:liuzidi
 * @Description:
 */
public class InheritTest {
    public static void main(String[] args) {
        Person a = new Students();

        Students s = (Students) new Person();
    }

}
class Person{
    int age;
    String name;
    public void test(){
        System.out.println("hello!");
    }
}
class Students extends Person{
    String number;
}
