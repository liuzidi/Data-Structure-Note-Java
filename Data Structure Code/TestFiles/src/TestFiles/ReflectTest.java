package TestFiles;

import org.junit.Test;

/**
 * @author:liuzidi
 * @Description:
 */
public class ReflectTest {
    @Test
    public void test1(){
        Class pClazz = Person.class;
        System.out.println(pClazz);
    }
}
class Person{
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}