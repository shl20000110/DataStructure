package sort;/*
    @author shl
    @create 2020-04-02-21:33
*/

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,89,68};
        insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr){

        //使用for循环简化代码
        for (int i = 1; i < arr.length ; i++) {//此处arr.length不需要减1，减去1后最后一个数就不参与运算了
            //从第二个数开始，即下标为1
            int insertVal = arr[i];
            int insertIndex = i - 1 ;//即arr[1]前面这个数的下标
            //给insertVal找到插入的位置
            //insertIndex >= 0保证在给insertVal找插入位置时不越界；
            //insertVal < arr[insertIndex]说明待插入的数还没有找到适当插入的位置，就需要arr[insertIndex]向后移动
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                //若要从大到小，就把小于改为大于即可
                arr[insertIndex + 1] = arr[insertIndex];//表示后移
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，是insertIndex+1这个位置
            if (insertIndex+1 == i){
                arr[insertIndex + 1] = insertVal;
            }

            System.out.println("第"+i+"轮后：");
            System.out.println(Arrays.toString(arr));
        }



 /*

        //逐步推导理解
        //第1轮{101,34,119,1}=>{34,101,119,1}
        //定义待插入的数
        int insertVal = arr[1];

        int insertIndex = 1 - 1 ;//即arr[1]前面这个数的下标

        //给insertVal找到插入的位置
        //insertIndex >= 0保证在给insertVal找插入位置时不越界；
        //insertVal < arr[insertIndex]说明待插入的数还没有找到适当插入的位置，就需要arr[insertIndex]向后移动
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//表示后移；相当于此时{101,34,119,1}=>{101,101,119,1}
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，是insertIndex+1这个位置
        arr[insertIndex + 1] = insertVal;

        System.out.println("第1轮后：");
        System.out.println(Arrays.toString(arr));


        //第2轮
        insertVal = arr[2];
        insertIndex = 2 - 1 ;//即arr[1]前面这个数的下标
        //给insertVal找到插入的位置
        //insertIndex >= 0保证在给insertVal找插入位置时不越界；
        //insertVal < arr[insertIndex]说明待插入的数还没有找到适当插入的位置，就需要arr[insertIndex]向后移动
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//表示后移
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，是insertIndex+1这个位置
        arr[insertIndex + 1] = insertVal;


        System.out.println("第2轮后：");
        System.out.println(Arrays.toString(arr));

        //第3轮
        insertVal = arr[3];
        insertIndex = 3 - 1 ;//即arr[1]前面这个数的下标
        //给insertVal找到插入的位置
        //insertIndex >= 0保证在给insertVal找插入位置时不越界；
        //insertVal < arr[insertIndex]说明待插入的数还没有找到适当插入的位置，就需要arr[insertIndex]向后移动
        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];//表示后移
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到，是insertIndex+1这个位置
        arr[insertIndex + 1] = insertVal;


        System.out.println("第3轮后：");
        System.out.println(Arrays.toString(arr));

  */
    }
}
