package tree;/*
    @author shl
    @create 2020-04-09-18:33
*/

import java.util.Arrays;

//u1s1,这个没听懂，有时间慢慢debug......
//树的应用：堆排序
public class HeapSort {

    public static void main(String[] args) {
        //默认将数组进行升序排列，则是大顶堆，若是降序排列，则是小顶堆
        int [] arr = {4,6,8,5,9,1,12,7,36,15,21,-5,20,-12};
        heapSort(arr);

    }

    //堆排序方法
    public static void heapSort(int []arr) {
        System.out.println("堆排序");

        //定义一个临时变量，用于第二个步骤中的交换
        int temp = 0;

        //分步测试代码
       /* adjustToHeap(arr,1,arr.length);
        System.out.println("第一次调整 = "+ Arrays.toString(arr));

        adjustToHeap(arr,0,arr.length);
        System.out.println("第二次调整 = "+ Arrays.toString(arr));*/

       //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        //1、完成堆排序的第一个步骤：构建出一个大顶堆
        //从最后一个非叶子节点开始：arr.length / 2 - 1
        for (int i = arr.length / 2 - 1 ; i  >= 0 ; i--) {
            adjustToHeap(arr,i,arr.length);
        }

        //2、堆排序第二个步骤：将堆顶元素与末尾元素交换
        //重新调整结构，满足堆的定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整和交换，直到整个序列有序
        for (int j = arr.length - 1; j > 0 ; j--) {
            //5个数排序实际上只要排4次就行了
            //交换
            temp = arr[j];
            arr[j] = arr[0];//在arr[0]的是每次大顶堆的最大值
            arr[0] = temp;
            //调整
            //每次排成一个大顶堆之后还要继续调整
            //每次都是从顶调整的，只要将堆顶元素排到适合他的位置就好了，相当于在堆中只变动了堆顶元素
            adjustToHeap(arr,0,j);
        }

        System.out.println("数组 = "+ Arrays.toString(arr));

    }

    //将一个数组（二叉树）调整成一个大顶堆

    /**
     * 将 i 对应的非叶子节点的数调整成大顶堆
     * {4,6,8,5,9} =(i=1传入后)=> 得到{4,9,8,5,6}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的下标/索引
     * @param length 对多少个元素进行调整，length长度是在逐渐减少的
     */
    public static void adjustToHeap(int arr[],int i,int length) {

        //先取出当前元素的值，保存在临时变量temp中
        int temp = arr[i];
        //开始调整成堆
        //注意：从非叶子节点，从左到右，从下到上进行调整的
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //k代表的是以i为非叶子节点的左子节点，下一次k又变成k下面的左子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                //说明左子节点的值小于右子节点的值
                // 让k指向右子节点
                k++;
            }
            if (arr[k] > temp) {
                //说明子节点大于了父节点
                //将大的值赋给当前节点
                arr[i] = arr[k];
                //让i指向k继续循环比较,即i指向当前的节点（！！！）
                i = k;
                //arr[i] = temp;//将temp放到调整后的位置
            } else {
                break;
            }
        }
        //for循环结束后已经将以 i 为父节点的最大数放在了i原先的位置 （局部调整成了最大值）
        //i这个时候已经指向了当前的节点
        arr[i] = temp;//将temp放到调整后的位置
    }
}

