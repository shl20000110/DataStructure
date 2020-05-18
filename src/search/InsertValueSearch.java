package search;/*
    @author shl
    @create 2020-04-05-17:54
*/

import java.util.Arrays;

//插值查找算法
//对数据量大，关键字分布均匀的查找适用，分布不均匀时不一定比二分查找更快
public class InsertValueSearch {
    public static void main(String[] args) {
        //int[] arr = new int[100];
        //for (int i = 0; i <100 ; i++) {
        //    arr[i] = i + 1;
        //}
        int arr[] = {1,8,10,89,1000,1000,1234};
        System.out.println(Arrays.toString(arr));
        int index = InterpolationSearch(arr, 0, arr.length - 1, 1000);
        System.out.println("index = "+index);
    }

    //插值排序方法
    //使用前提也要是数组是有序的
    /**
     *
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找的值
     * @return
     */
    private static int InterpolationSearch(int[] arr,int left,int right,int findVal) {
        //必须要判断，否则mid可能越界和死递归
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        //默认arr从小到大排序
        //mid公式进行改进
        //自适应mid
        int mid = left +(right - left) * (findVal -arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            //说明要向右递归
            return InterpolationSearch(arr,mid + 1,right,findVal);
        } else if (findVal < midVal) {
            //向左递归
            return InterpolationSearch(arr,left,mid-1,findVal);
        } else {
            return mid;
        }
    }
}
