package search;/*
    @author shl
    @create 2020-04-05-17:17
*/

import java.util.ArrayList;

//二分查找的前提是：要求数组必须是有序的
//使用递归实现
public class BinarySearch {
    public static void main(String[] args) {

        int arr[] = {1,8,10,89,1000,1000,1234};

        int res = binarysearch(arr,0,arr.length-1,1);
        System.out.println("res = "+res);

        //======================================
        ArrayList<Integer> resultlist = binarysearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resultlist ="+resultlist);

    }


    //二分查找算法
    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 查找的值
     * @return 找到返回下标，否则返回-1
     */
    public static int binarysearch(int[] arr,int left,int right,int findVal) {
        //默认从小到大排序

        //当left > right 时，说明递归完毕，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //向右递归
            return binarysearch(arr,mid + 1,right,findVal);
        } else if(findVal < midVal){
            //向左递归
            return binarysearch(arr,left,mid - 1,findVal);
        } else {
            return mid;
        }
    }



    //二分查找完善版
    //若有多个相同的数时，需要返回所有相同的数的下标
    /*
    1.在找到mid索引值时，不需要立刻返回
    2.向mid的索引值左边扫描，将所有满足条件的下标加入一个集合中
    3.向mid的索引值右边扫描，将所有满足条件的下标加入一个集合中
    4.将集合返回即可
     */
    public static ArrayList<Integer> binarysearch2(int[] arr, int left, int right, int findVal) {
        //默认从小到大排序

        //当left > right 时，说明递归完毕，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            //向右递归
            return binarysearch2(arr,mid + 1,right,findVal);
        } else if(findVal < midVal){
            //向左递归
            return binarysearch2(arr,left,mid - 1,findVal);
        } else {
            ArrayList<Integer> resIndexarraylist = new ArrayList<>();
            //向左边扫描
            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    //退出的两种情况
                    break;
                }
                //否则就将temp放入集合中
                resIndexarraylist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexarraylist.add(mid);
            //向右边扫描
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != findVal){
                    //退出的两种情况:
                    break;
                }
                //否则就将temp放入集合中
                resIndexarraylist.add(temp);
                temp += 1; //temp右移
            }
            return resIndexarraylist;

        }
    }
}
