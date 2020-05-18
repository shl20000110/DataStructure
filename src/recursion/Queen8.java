package recursion;/*
    @author shl
    @create 2020-03-29-11:53
*/

public class Queen8 {
    //八皇后回溯问题

    //定义一个max表示有多少个皇后
    public int max = 8;
    //定义一个数组arr保存皇后放置位置的结果，{arr[i] = val}==>表示的是第i+1个皇后放在对应第i+1行，第val+1列
    int arr[] = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        //test
        Queen8 queen8 = new Queen8();
        queen8.checkQueen(0);
        System.out.printf("一共有%d解法",count);
    }

    //方法：将皇后摆放的位置打印
    private void printQueen() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i]+" ");
        }
        System.out.println();
    }

    //方法：判断皇后之间是否冲突
    //查看放置 第n个皇后后 检测该皇后是否和前面摆放的皇后冲突
    /**
     * n表示第n个皇后；n和i一样都是从0开始的，因为n作为数组下标，n为0表示第一个皇后
     * @return
     */
    private boolean judgeCollison(int n) {
        for (int i = 0; i < n ; i++) {
            //第n个皇后放置后，就要对前面的皇后进行检测
            //说明！n从0开始
            //1.arr[i] == arr[n]  值比较，表示第n个皇后是否和前面n-1个皇后在同一列
            //2.Math.abs(arr[n] - arr [i]) == Math.abs(n - i)表示第n个皇后是否和第i个皇后是否在同一斜线上
            //3.判断在同一行没有必要，因为n每次在递增
            if (arr[i] == arr[n] || Math.abs(arr[n] - arr [i]) == Math.abs(n - i)){
                //冲突判断条件
                return false;
                }
            }
        return true;
        }

    //方法：放置第n个皇后
    //checkQueen是每一次递归时，进入checkQueen中都有for循环，因此产生回溯
    private void checkQueen(int n){
        //
        if (n == max){
            //说明已经在放第9个皇后，n从0开始，n=0相当于第一个皇后
            //n等于8时，前面八个皇后已经放好了
            printQueen();
            return;
        }
        //依次放入皇后并判断是否冲突
        for (int i = 0; i < max ; i++) {
            //先把当前这个皇后n，放到该行的第1列
            arr[n] = i;
            //判断当放置第n个皇后的i列时候，前面的皇后之间是否冲突
            if (judgeCollison(n)){
                //不冲突
                //接着放n+1个皇后，递归
                checkQueen(n+1); //产生了回溯
            }
            //如果冲突，就继续执行arr[n] = i,就是将第n个皇后放在本行的后移的一个位置，就是放到后一列
            //就返回arr[n] = i  产生了回溯
        }

    }


}
