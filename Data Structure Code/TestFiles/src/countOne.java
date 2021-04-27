/**
 * @author:liuzidi
 * @Description:
 */
public class countOne {
    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
        System.out.println((5 >>> 1));

    }
    public static int hammingDistance(int x, int y) {
        int res = x ^ y;
        int count = 0;
        for(int i = 0; i < 32; i++){
            if(((res >>> 1) & 1) == 1){
                count++;
            }
        }
        return count;
    }
}
