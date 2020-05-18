package sort;/*
    @author shl
    @create 2020-04-04-17:24
*/

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        //shellsort(arr);//交换法。效率较低
        shellsort2(arr);//排序法。效率较高
    }

    //逐步推导实现希尔排序
    public static void shellsort(int[] arr){

        int temp = 0;
        int count  =0;
        //利用循环处理
        //用交换式实现希尔排序
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            //每次将数组长度除以2
            for (int i = gap; i < arr.length; i++) {
                //遍历每个组中所有的元素（共gap组，每组有xx个元素），步长是gap
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素
                    //说明需要交换（默认从小到大排序）
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第"+(++count)+"轮后="+ Arrays.toString(arr));

        }

        /*int temp = 0;
        //希尔排序第一轮
        //第一轮排序将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            //遍历每个组中所有的元素（共5组，每组两个元素），步长是5
            for (int j = i - 5; j >= 0 ; j -= 5) {
                //如果当前元素大于加上步长后的那个元素
                //说明需要交换（默认从小到大排序）
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第1轮后="+ Arrays.toString(arr));

        //希尔排序第二轮
        //第一轮排序将10个数据分成5/2=2组
        for (int i = 2; i < arr.length; i++) {
            //遍历每个组中所有的元素（共5组，每组两个元素），步长是5
            for (int j = i - 2; j >= 0 ; j -= 2) {
                //如果当前元素大于加上步长后的那个元素
                //说明需要交换（默认从小到大排序）
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第2轮后="+ Arrays.toString(arr));


        //希尔排序第三轮
        //第一轮排序将10个数据分2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            //遍历每个组中所有的元素（共5组，每组两个元素），步长是5
            for (int j = i - 1; j >= 0 ; j -= 1) {
                //如果当前元素大于加上步长后的那个元素
                //说明需要交换（默认从小到大排序）
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第3轮后="+ Arrays.toString(arr));*/
    }



        //对交换式的希尔排序进行优化->移位法
        public static void shellsort2(int[] arr) {
            int count = 0;
            // 增量gap，并逐步缩小增量
            for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
                //从第gap个元素，逐个对其所在的组进行直接插入排序
                for (int i = gap; i < arr.length; i++) {
                    int j = i;//保存待插入的位置的下标
                    int temp = arr[j];//临时变量记录要插入的数
                    if (arr[j] < arr[j - gap]) {
                        while (j - gap >= 0 && temp < arr[j - gap]){
                            //开始移动
                            arr[j] = arr[j - gap];
                            j -= gap;
                        }
                        //当退出while循环后，就给temp找到了插入的位置
                        arr[j] = temp;
                    }
                }
                System.out.println("希尔排序第"+(++count)+"轮后="+ Arrays.toString(arr));
            }
        }

}
