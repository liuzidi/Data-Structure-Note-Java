package TestFiles;

import java.util.*;

/**
 * @author:liuzidi
 * @Description:
 */
public class AutoBox {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        for(Integer i : list){
            System.out.println(i);
        }
        System.out.println(add(3, 5));
        Integer i = 7;
        System.out.println(compare(1, 4));
        System.out.println(compare(i, 4));
        System.out.println(compare(7, 7));
    }
    public static Integer add(Integer a, Integer b){
        int aInt = a;
        int bInt = b;
        return (a+b);
    }
    public static boolean compare(Integer a, Integer b){
        if(a < b){
            return true;
        }else if(a.equals(b)){
            System.out.println("可以发生相等事件");
        }
        return false;
    }
}
