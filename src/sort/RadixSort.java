package sort;/*
    @author shl
    @create 2020-04-05-14:44
*/
//基数排序没有实现负数的排序，一般不使用基数排序排序负数
//若要实现负数排序，则要增加桶的数量，不止10个 maybe19个，参考http://code.i-harness.com/zh-CN/a/e98a9
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {

        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {
        /*
        //第一轮（针对每个元素的个位数进行排序处理）
        //定义一个二维数组，代表10（0-9）个桶，每个桶就是一个一维数组
        //二维数组包含10个一维数组
        //为了防止在放入数据的时候，数据溢出，将每个一维数组即桶的大小定为arr.length
        //基数排序是用空间换时间的算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，每次放的时候不一定一样
        //定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCount[0]，记录的就是bucket[0]桶的放入数据的个数，以此类推
        int[] bucketElementCount = new int[10];

        for (int j = 0; j < arr.length ; j++) {
            //取出每个元素的个位
            int digitOfElement = arr[j] / 1 % 10;
            //放入对应桶里
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];//画图理解
            bucketElementCount[digitOfElement] ++ ;
        }
        //按照每个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        int index = 0;
        //遍历每一个桶，并将桶中的数据，放到原数组中
        for (int k = 0; k < bucketElementCount.length ; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCount[k] != 0) {
                //循环第k个桶（第k个一维数组）
                for (int l = 0; l < bucketElementCount[k] ; l++) {
                    //取出元素放入arr中
                    arr[index] = bucket[k][l];
                    //k范围实际上是0-10
                    //l范围实际上是0-总共这个桶有元素的数量
                    index++;
                }
                //第一轮处理后，需要将bucketElementCount[k] = 0
                //因为后序第二轮，第三轮都是重新进行排序的
                bucketElementCount[k] = 0;
            }

        }
        System.out.println("第一轮对个位排序处理="+ Arrays.toString(arr));

        //=========================================================
        //第二轮(对每个元素的十位进行处理）
        for (int j = 0; j < arr.length ; j++) {
            //取出每个元素的十位
            int digitOfElement = arr[j] / 10 % 10;
            //放入对应桶里
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];//画图理解
            bucketElementCount[digitOfElement] ++ ;
        }
        //按照每个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据，放到原数组中
        for (int k = 0; k < bucketElementCount.length ; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCount[k] != 0) {
                //循环第k个桶（第k个一维数组）
                for (int l = 0; l < bucketElementCount[k] ; l++) {
                    //取出元素放入arr中
                    arr[index] = bucket[k][l];
                    //k范围实际上是0-10
                    //l范围实际上是0-总共这个桶有元素的数量
                    index ++;
                }
            }
            //必须将bucketelementcount[k]置空！！
            bucketElementCount[k] = 0;
        }
        System.out.println("第二轮对十位排序处理="+ Arrays.toString(arr));

        //=========================================================
        //第三轮(对每个元素的百位进行处理）
        for (int j = 0; j < arr.length ; j++) {
            //取出每个元素的百位
            int digitOfElement = arr[j] / 100 % 10;
            //放入对应桶里
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];//画图理解
            bucketElementCount[digitOfElement] ++ ;
        }
        //按照每个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据，放到原数组中
        for (int k = 0; k < bucketElementCount.length ; k++) {
            //如果桶中有数据，才放入到原数组中
            if (bucketElementCount[k] != 0) {
                //循环第k个桶（第k个一维数组）
                for (int l = 0; l < bucketElementCount[k] ; l++) {
                    //取出元素放入arr中
                    arr[index] = bucket[k][l];
                    //k范围实际上是0-10
                    //l范围实际上是0-总共这个桶有元素的数量
                    index ++;
                }
            }
            //必须将bucketelementcount[k]置空！！
            //最后一轮可置可不置
            bucketElementCount[k] = 0;
        }
        System.out.println("第三轮对百位排序处理="+ Arrays.toString(arr));*/

        //根据规律推导基数排序代码：
        //1.得到待排序数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大数
        for (int i = 1; i < arr.length ; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数的位数是几位数
        int maxLength = (max + "").length();//妙啊！

        //定义一个二维数组，代表10（0-9）个桶，每个桶就是一个一维数组
        //二维数组包含10个一维数组
        //为了防止在放入数据的时候，数据溢出，将每个一维数组即桶的大小定为arr.length
        //基数排序是用空间换时间的算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，每次放的时候不一定一样
        //定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCount[0]，记录的就是bucket[0]桶的放入数据的个数，以此类推
        int[] bucketElementCount = new int[10];

        //使用循环进行进一步处理
        for (int i = 0 , n = 1; i < maxLength ; i++, n *= 10) {

            //针对每个元素的对应位进行排序处理，第一次是个位，第二次十位，第三次百位...
            for (int j = 0; j < arr.length ; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;//每次循环乘上10 ，10的幂级数
                //放入对应桶里
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];//画图理解
                bucketElementCount[digitOfElement] ++ ;
            }
            //按照每个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放到原数组中
            for (int k = 0; k < bucketElementCount.length ; k++) {
                //如果桶中有数据，才放入到原数组中
                if (bucketElementCount[k] != 0) {
                    //循环第k个桶（第k个一维数组）
                    for (int l = 0; l < bucketElementCount[k] ; l++) {
                        //取出元素放入arr中
                        arr[index] = bucket[k][l];
                        //k范围实际上是0-10
                        //l范围实际上是0-总共这个桶有元素的数量
                        index++;
                    }
                    //第i+1轮处理后，需要将bucketElementCount[k] = 0
                    //因为后序第二轮，第三轮都是重新进行排序的
                    bucketElementCount[k] = 0;
                }
            }
            System.out.println("第"+(i + 1)+"轮后，arr="+ Arrays.toString(arr));
        }
    }
}
