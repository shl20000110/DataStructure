package sort;/*
    @author shl
    @create 2020-04-04-20:32
*/
//归并排序！！！
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

        int arr[] = {8,4,5,7,1,3,6,2};
        int temp[] = new int[arr.length];
        //归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length-1, temp);

        System.out.println("归并排序后="+ Arrays.toString(arr));
    }

    //分+合方法：
    public static void  mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr,mid + 1, right, temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }


    //合并的方法
    /**
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param right 中间索引
     * @param mid 右边索引
     * @param temp 做中转的数组（要做递归时使用）
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //1.
        //先把左右（有序）两边的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //继续
            if (arr[i] < arr[j]){
                //如果左边有序序列的当前元素小于等于右边有序序列当前序列元素，将左边当前元素拷贝到temp数组
                //然后t向后移，i向后移
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                //反之将右边当前元素拷贝到temp数组里
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //2.
        //把有剩余数据的一边的数据依次全部填充到temp去

        while (i <= mid) {
            //说明左边的有序序列还有剩余的元素
            //全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            //说明右边的有序序列还有剩余的元素
            //全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //3.
        //将temp数组的元素拷贝到arr

        //并不是每次都拷贝所有元素
        t = 0;
        int templeft = left; //
        while (templeft <= right){ //第一次合并templeft=0，right=1。。。 最后一次templeft=0，right=7
            arr[templeft] = temp[t];
            t += 1;
            templeft += 1;
        }
    }

}
