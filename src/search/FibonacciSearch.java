package search;/*
    @author shl
    @create 2020-04-06-11:55
*/

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,1));

    }


    //获取一个斐波那契数列的方法
    //非递归方法
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


    /**
     * //斐波那契查找算法
     * //非递归方法
     *
     * @param arr 数组
     * @param key 要查找的关键值
     * @return 返回对应下标，没有返回-1
     */
    public static int fibSearch(int arr[], int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0; //存放mid
        int f[] = fib(); //获取斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]可能大于arr的长度，需要使用arrays类构造一个新的数组并指向temp
        //不足的部分会用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需要使用arr数组最后的数填充 temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while来循环处理，找到要查找的数key
        while (low <= high) {
            //只要条件满足就一直找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                //要向数组左边查找
                high = mid - 1;
                /*
                1、全部元素 = 前面元素加后面元素
                2、f[k] = f[k-1] + f[k-2]
                因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                相当于在f[k-1]的前面继续查找 k--
                下次循环mid = f[k-1-1] -1
                 */
                k--;
            } else if (key > temp[mid]) {
                //说明向数组右边查找
                low = mid + 1;
                /*
                1、全部元素 = 前面元素加后面元素
                2、f[k] = f[k-1] + f[k-2]
                因为后面有f[k-2]个元素，所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                相当于在f[k-2]的前面继续查找 k-=2
                下次循环mid = f[k-1-2] -1
                 */
                k -= 2;
            } else {
                //说明找到值
                //需要说明返回的下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;//说明没有找到
    }

}