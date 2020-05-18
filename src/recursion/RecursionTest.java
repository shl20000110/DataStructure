package recursion;/*
    @author shl
    @create 2020-03-28-17:33
*/

public class RecursionTest {
    public static void main(String[] args) {

        int res = factorial(4);
        System.out.println(res);

    }
    //阶乘
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }
    }

}
