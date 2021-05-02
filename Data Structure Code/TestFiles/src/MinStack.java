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
           if(stack.pop() == min){
               min = stack.pop();
           }
        }

        public int top() {
            return stack.peek().intValue();
        }

        public int getMin() {
            return min;
        }
    }
