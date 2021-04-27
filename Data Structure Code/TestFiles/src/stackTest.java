import java.util.Stack;


public class stackTest {
    public static void main(String[] args) {
        String s = "{()}{}";
        System.out.println(isValid(s));

    }
    public static boolean isValid(String s) {
        int len = s.length();
        if(len % 2 != 0){//如果非偶数则直接不匹配
            return false;
        }
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for(int i = 0; i < len; i++){//将字符串s的所有字符压入stack1
            Character temp = s.charAt(i);
            stack1.push(temp);
        }
        stack2.push(stack1.pop()); //stack1的pop到stack2中:stack2的初始化

        while(!stack1.empty() || !stack2.empty()){//终止条件：两个栈均为空时（所有字符验证完毕）
            if(stack1.empty()){//stack1为空，stack2不空
                return false;//stack1 pop到空还未匹配上
            }
            if(stack2.empty()){//stack1不空，stack2为空
                stack2.push(stack1.pop());
            }
            if(isMatch(stack1.peek(), stack2.peek())){//都不为空时
                stack1.pop();
                stack2.pop();
            }else{
                stack2.push(stack1.pop());
            }
        }
        return true;
    }
    public static boolean isMatch(char s1, char s2){
        if(s1 == '{' && s2 == '}'){
            return true;
        }
        if(s1 == '[' && s2 == ']'){
            return true;
        }
        return s1 == '(' && s2 == ')';
    }
}
