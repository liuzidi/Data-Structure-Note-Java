package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author:liuzidi
 * @Description:
 */
public class topHot100_301 {
    @Test
    public void test(){
        String s = "()())()";
        List<String>list =removeInvalidParentheses(s);
        for(String sv : list){
            System.out.println(sv);
        }

    }

    private char[] chars;
    private Set<String> set = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        //1.求出要删除左括号的数量和右括号的数量
        int delLeftNum = 0, delRightNum = 0;
        this.chars = s.toCharArray();
        for(char c : chars){
            if(c == '('){
                delLeftNum++;
            }else if(c == ')'){
                if(delLeftNum > 0){
                    delLeftNum--;
                }else{
                    delRightNum++;
                }
            }
        }
        DFS(0,delLeftNum,delRightNum,0,0,"");
        return new ArrayList<String>(set);

    }
    private void DFS(int index, int delLeftNum, int delRightNum, int leftCount, int rightCount, String curString){
        if(leftCount < rightCount) return;
        if(index == chars.length){
            if(delLeftNum == 0 && delRightNum == 0 && leftCount == rightCount){
                set.add(curString);
            }
            return;
        }
        if(chars[index] == '('){//选择删除或者不删除
            if(delLeftNum > 0){//删除
                DFS(index + 1, delLeftNum - 1,delRightNum,leftCount,rightCount,curString);
            }//不删除
            DFS(index + 1, delLeftNum,delRightNum,leftCount + 1,rightCount,curString += "(");
        }else if(chars[index] == ')'){
            if(delRightNum > 0){//删除
                DFS(index + 1, delLeftNum,delRightNum - 1,leftCount,rightCount,curString);
            }
            DFS(index + 1, delLeftNum,delRightNum,leftCount,rightCount + 1,curString += ")");
        }else{//其他字符
            DFS(index + 1, delLeftNum,delRightNum,leftCount,rightCount,curString += chars[index]);
        }
    }
}
