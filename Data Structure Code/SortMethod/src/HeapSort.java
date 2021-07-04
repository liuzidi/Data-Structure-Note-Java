/**
 * @author:liuzidi
 * @Description:
 */
public class HeapSort {
    public static void main(String[] args) {
        Person p = new Student();
        p.study();
    }
}
interface Person{
    public void eat();
}
class Student implements  Person{

    @Override
    public void eat() {
        System.out.println("chi");
    }
    public void study(){
        System.out.println("xue");
    }
}

