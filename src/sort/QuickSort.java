package sort;/*
    @author shl
    @create 2020-04-04-18:25
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {

        //int[] arr = {-9,78,0,23,-567,70,-7,900,463};
        //System.out.println("arr = "+ Arrays.toString(arr));

        //测试快排的速度
        int[] arr = new int[800000];
        for (int i = 0; i < 800000 ; i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1str = simpleDateFormat.format(date1);
        System.out.println("执行前的时间是="+ date1str);

        quickSort(arr,0,arr.length-1);

        Date date2 = new Date();
        String date1str1 = simpleDateFormat.format(date2);
        System.out.println("执行后的时间是="+ date1str);

    }


    public static void quickSort(int[] arr,int left,int right) {
        //传入待排序的数组，最左边元素的索引和最右边元素的索引
        int l = left; //左下标
        int r = right; //右下标,用两个变量来暂存
        int pivot = arr[(left + right) / 2];//pivot表示中轴值
        int temp = 0;//交换时使用的中间变量
        while (l < r) {
            //上面while循环的目的是让比pivot小的值放到左边，比pivot大的值放到右边
            //下面while循环的目的是在pivot的左边一直找，找到一个大于等于pivot的值才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //while循环的目的是在pivot的右边一直找，找到一个小于等于pivot的值才退出
            while (arr[r] > pivot){
                r -= 1;
            }
            //若l >= r 说明pivot的左右两边的值已经按照左边全部是小于等于pivot的值，右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //若没有满足条件，则要交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后发现arr[l]==pivot,就让r--；相当于r前移一步
            if (arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后发现arr[r]==pivot,就让l++；相当于l后移一步
            if (arr[l] == pivot){
                l += 1;
            }
        }

        //如果l == r，必须让 l++，r--；否则会出现栈溢出
        if (l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }



    }
}
