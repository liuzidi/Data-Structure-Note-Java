package TestFiles;

/**
 * @author:liuzidi
 * @Description:
 */
public class diguiTest {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));

    }
    public static String countAndSay(int n) {
        if(n == 1){
            return "1";
        }
        String prev = countAndSay(n - 1);
        int count = 0;
        char current =' ';
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < prev.length(); i++){
            if(i == 0){
                count++;
                current = prev.charAt(i);
                continue;
            }
            if(current == prev.charAt(i)){
                count++;
            }else{
                res.append(count).append(current);
                current = prev.charAt(i);
                count = 1;
            }
        }
        res.append(count).append(current);
        return res.toString();
    }
}
