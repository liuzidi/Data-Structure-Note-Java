package LeetCode;

import org.junit.Test;

import java.util.Stack;

/**
 * @author:liuzidi
 * @Description:
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 */
public class topHot100_394 {
    @Test
    public void test1(){
        System.out.println(decodeString("3[a2[c]]"));
    }
    public String decodeString(String s) {
        char[] charArr = s.toCharArray();
        StringBuilder res = new StringBuilder();
        Stack<Integer> curNumStack = new Stack<>();
        Stack<String> curStringStack =new Stack<>();
        int curNum = 0;
        for (char c : charArr) {
            if (Character.isDigit(c)) {//若是数字
                curNum = curNum * 10 + c - '0';
            } else if (c == '[') {//若是 [
                curNumStack.push(curNum);
                curStringStack.push(res.toString());
                curNum = 0;
                res= new StringBuilder();
            } else if (Character.isLetter(c)) {//若是字母
                res.append(c);
            } else {//若是 ]
                StringBuilder temp = new StringBuilder();
                if (!curNumStack.isEmpty()) {
                    int multi = curNumStack.pop();
                    for (int j = 0; j < multi; j++) {
                        temp.append(res);
                    }
                    String curString = curStringStack.pop();
                    res = new StringBuilder(curString + temp.toString());
                }
            }
        }
        return res.toString();
    }

//    public String decodeString(String s){
//        char[] cArr = s.toCharArray();
//        StringBuilder res = new StringBuilder();
//
//        return res.toString();
//    }
//    public String DFS(int i, char[] cArr){//从i开始的索引，返回第一个[]中的内容
//        StringBuilder res = new StringBuilder();
//        int multi = 0;
//        while(i < cArr.length){
//            char c = cArr[i];
//            if(Character.isDigit(c)){
//                multi = multi * 10 + c - '0';
//            }else if(Character.isLetter(c)){
//                res.append(c);
//            }else if(c == '['){
//                String temp = DFS(i + 1, cArr);
//            }else{
//                return
//            }
//            i++;
//        }
//
//        return ;
//    }
}
