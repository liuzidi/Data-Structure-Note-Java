package LeetCode.Medium_Algorithm;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class MA14 {
    @Test
    public void test(){
        System.out.println(fractionToDecimal(7, -12));
    }
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
        if(denominator == -1 && numerator == Integer.MIN_VALUE){
            return String.valueOf(-(long)Integer.MIN_VALUE);
        }
        StringBuilder res = new StringBuilder();
        if((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)){
            res.append("-");
        }
        long a = numerator > 0 ? (long) numerator : -(long)numerator;
        long b = denominator > 0 ? (long)denominator : -(long)denominator;
        long head = a / b;
        long remainder = a % b;
        res.append(head);
        if(remainder == 0){
            return res.toString();
        }
        res.append(".");
        Map<Long, Integer> map = new HashMap<>();//余数和对应的值
        StringBuilder tail = new StringBuilder();
        int index = 0;
        while(remainder != 0){
            if(map.containsKey(remainder)){
                tail.insert(map.get(remainder), "(");
                tail.append(")");
                break;
            }
            map.put(remainder, index++);
            remainder *= 10;
            long value = remainder / b;
            remainder = remainder % b;
            tail.append(value);
        }
        res.append(tail.toString());
        return res.toString();
    }
}
