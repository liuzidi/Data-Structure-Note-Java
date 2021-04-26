import java.util.Stack;

/**
 * @author:liuzidi
 * @Description:
 */
public class MinStack {
        /** initialize your data structure here. */
        int min;
        private Stack<Integer> stack;

        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if(stack.isEmpty()){
                min = val;
                stack.push(Integer.valueOf(val));
                return;
            }
            stack.push(Integer.valueOf(val));
            min = Math.min(min, val);
        }
        public void pop() {
            if(stack.size() == 1){
                stack.pop();
                return;
            }
            if(min == stack.peek().intValue()){
                stack.pop();
                min = stack.peek().intValue();
                for(Integer val : stack){
                    min = Math.min(min,val);
                }
                return;
            }
            stack.pop();
        }

        public int top() {
            return stack.peek().intValue();
        }

        public int getMin() {
            return min;
        }
    }
    class Test{
        public static void main(String[] args) {
            MinStack minStack = new MinStack();
            minStack.push(1);
            minStack.push(2);
            minStack.push(3);

        }
    }
