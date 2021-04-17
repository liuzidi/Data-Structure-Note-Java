package TestFiles;

import java.sql.SQLOutput;

/**
 * @author:liuzidi
 * @Description:
 */
public class StringTest {
    public static void main(String[] args) {
        test("a","b");


    }
    public static void test(String a, String b){
        String s11 = "a";
        String s12 = "b";
        String s1 = new String("ab");
        String s2 = "ab";
        String s3 = "a"+"b";
        String s4 = "a"+s12;
        String s5 = s11 + s12;
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s4));
        System.out.println(System.identityHashCode(s5));
        System.out.println(System.identityHashCode(s11));
        System.out.println(System.identityHashCode(s12));
//        System.out.println(s1 == s2);//false
//        System.out.println(s1 == s3);//false
//        System.out.println(s1 == s4);//false
//        System.out.println(s1 == s5);//false
//
//
//        System.out.println(s2 == s3);//true
//        System.out.println(s2 == s4);//false
//        System.out.println(s2 == s5);//false
//
//
//        System.out.println(s3 == s4);//false
//        System.out.println(s3 == s5);//false
//
//
//        System.out.println(s4 == s5);//false
//
//        System.out.println(s11 == a);//true
//        System.out.println(s11 + s12 == s2);//false

    }



}
