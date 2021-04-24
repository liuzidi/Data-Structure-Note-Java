/**
 * @author:liuzidi
 * @Description:
 */
public class BinarySearchTest {
    public static void main(String[] args) {

    }
}

    public int firstBadVersion(int n) {//找出第一个true的选项
        int left = 1;
        int right = n;
        int middle = 0;
        while(left < right){
            middle = left + (right - left) / 2;
            if(isBadVersion(middle)){
                // if(middle - 1 >= 0 && !isBadVersion(middle - 1)){
                //     return middle;
                // }
                //上面这段导致效率只有5%,因为调用过了isBadVersion
                right = middle;
                continue;
            }
            left = middle + 1;
            continue;
        }
        return right ;
    }
}
