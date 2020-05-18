package sort;/*
    @author shl
    @create 2020-03-30-20:33
*/

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,20}; //测试的数据
        int temp = 0; //临时变量，用于交换
        boolean flag = false;//标识变量，表示是否进行过交换
        //理解冒泡排序的演变过程
        //时间复杂度O(n^2)
        //理解 1：
        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                //如果后面的数比后面的大，则交换
                if (arr[j] > arr[j+1]){
                    flag = true; //表示进行了交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (flag == false){
                //进入判断，就意味着这一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag = false; //重置flag，进行下次判断
            }
        }

        /*
        //理解2：
        //第一趟排序就是将最大的数排到最后
        for (int j = 0; j < arr.length - 1 -0 ; j++) {
            //如果后面的数比后面的大，则交换
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }


        //第二趟排序就是将第二大的数排到倒数第二位,第一趟选出最大的数不参与排序，以下同理
        for (int j = 0; j < arr.length - 1 - 1 ; j++) {
            //如果后面的数比后面的大，则交换
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        //第三趟排序就是将第三大的数排到倒数第三位
        for (int j = 0; j < arr.length - 1 - 2 ; j++) {
            //如果后面的数比后面的大，则交换
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        //第四趟排序就是将第四的数排到倒数第四位
        for (int j = 0; j < arr.length - 1 - 3 ; j++) {
            //如果后面的数比后面的大，则交换
            if (arr[j] > arr[j+1]){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
            */

    }

    //将冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr) {
        int temp = 0; //临时变量，用于交换
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                //如果后面的数比后面的大，则交换
                if (arr[j] > arr[j+1]){
                    flag = true; //表示进行了交换
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (flag == false){
                //进入判断，就意味着这一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag = false; //重置flag，进行下次判断
            }
        }
    }

}

