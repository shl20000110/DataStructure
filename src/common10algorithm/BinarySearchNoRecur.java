package common10algorithm;/*
    @author shl
    @create 2020-04-25-17:22
*/

public class BinarySearchNoRecur {

    public static void main(String[] args) {

            //test
            int arr[] = {1,3,8,10,11,67,100};
            int index = BinarySearch(arr, 100);
            System.out.println("index = " + index);

    }

        //二分查找的非递归实现（和递归算法一起理解）
        /**
         *
         * @param arr 待查找的数组。默认是升序排列
         * @param target 需要查找的数
         * @return 返回对应下标，-1表示没有找到
         */
        public static int BinarySearch(int[] arr,int target) {
            int left = 0;
            int right = arr.length - 1;
            while (left <= right) {
                //说明可以继续查找
                int mid = (left + right) / 2;
                if (arr[mid] == target) {
                    return mid;
                } else if (arr[mid] > target) {
                    //需要向左边查找
                    right = mid - 1;
                } else {
                    //需要向右边查找
                    left = mid + 1;
                }
            }
            return -1;
        }
    }


