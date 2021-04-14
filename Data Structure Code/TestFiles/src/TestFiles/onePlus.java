package TestFiles;

/**
 * @author:liuzidi
 * @Description:
 */
public class onePlus {
    public static void main(String[] args) {
        int[] digits = {9,8,7,6,5,4,3,2,1,0};
        int[] res;
        res =plusOne(digits);
        for(int data : res){
            System.out.println(data);
        }

    }
    public static int[] plusOne(int[] digits) {
        long initValue = 0;
        int count = 0;
        int[] res;
        for (int i = 0; i < digits.length; i++){
            initValue += (digits[i])*((long)Math.pow(10, digits.length - i - 1));//Math方法默认为double
            if(digits[i] == 9){
                count++;//计算数组内有几个9
            }
        }
        long newValue = initValue + 1;
        if(count == digits.length){//全是9的情况下
            res = new int[digits.length + 1];
            res[0] = 1;
            for(int i = 1; i < digits.length + 1; i++){
                res[i] = 0;
            }
        }else{//不全为9
            res = new int[digits.length];
            for(int i = 0; i < digits.length; i++){
                res[i] = (int)(newValue / (long)(Math.pow(10, digits.length - i - 1)) % 10);
            }
        }
        return res;
    }

}
