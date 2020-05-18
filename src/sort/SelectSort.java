package sort;/*
    @author shl
    @create 2020-04-02-19:38
*/

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {

        int[] arr = {101,34,119,1,-1,90,58,123};//测试数据
        System.out.println("初始数据为：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    //方法：选择排序
    public static void selectSort(int[] arr){ //传入一个数组
        //时间复杂度为O(n^2)
        for (int i = 0; i < arr.length - 1 ; i++) {
            int minIndex = i;//假定当前最小数的下标就是i
            int min = arr[i];//假定当前最小数就是数组的第i+1个数
            for (int j = i + 1; j < arr.length ; j++) {
                if (min > arr[j]) {
                    //若要从大到小排序，就把 <小于号 改为 >大于号 就可以
                    //说明假定的最小值不是最小的
                    min = arr[j];//重置最小值min
                    minIndex = j;//重置最小下标minIndex
                }
            }
            //将最小值放在arr[0],即交换
            //arr[minIndex]  = arr[0];
            //arr[0] = min ;
            //优化:
            if (minIndex != i) {
                //两个位置不能交换
                arr[minIndex]  = arr[i];
                arr[i] = min;

            }
            //显示每轮排序的结果
            //System.out.println("第"+(i+1)+"轮后：");
            //System.out.println(Arrays.toString(arr));
        }

        /*
        //以下是推导过程：

        //第一轮排序
        int minIndex = 0;//假定当前最小数的下标就是0
        int min = arr[0];//假定当前最小数就是数组的第一个数
        for (int j = 0 + 1; j < arr.length ; j++) {
            if (min > arr[j]) {
                //说明假定的最小值不是最小的
                min = arr[j];//重置最小值min
                minIndex = j;//重置最小下标minIndex
            }
        }
        //将最小值放在arr[0],即交换
        //arr[minIndex]  = arr[0];
        //arr[0] = min ;
        //优化:
        if (minIndex != 0) {
            arr[minIndex]  = arr[0];
            arr[0] = min;
        }

        System.out.println("第1轮后：");
        System.out.println(Arrays.toString(arr));

        //第二轮排序
        minIndex = 1;//假定当前最小数的下标就是1
        min = arr[1];//假定当前最小数是数组的第二个数
        for (int j = 1 + 1; j < arr.length ; j++) {
            if (min > arr[j]) {
                //说明假定的最小值不是最小的
                min = arr[j];//重置最小值min
                minIndex = j;//重置最小下标minIndex
            }
        }
        //将最小值放在arr[0],即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第2轮后：");
        System.out.println(Arrays.toString(arr));

        //第三轮排序
        minIndex = 2;//假定当前最小数的下标就是1
        min = arr[2];//假定当前最小数是数组的第二个数
        for (int j = 2 + 1; j < arr.length ; j++) {
            if (min > arr[j]) {
                //说明假定的最小值不是最小的
                min = arr[j];//重置最小值min
                minIndex = j;//重置最小下标minIndex
            }
        }
        //将最小值放在arr[0],即交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第3轮后：");
        System.out.println(Arrays.toString(arr));
        */
    }
}
