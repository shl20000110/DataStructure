package search;/*
    @author shl
    @create 2020-04-05-17:07
*/

public class SeqSearch {
    public static void main(String[] args) {
        int arr[]  = {1,9,11,-1,34,89};
        int index = seqSearch(arr,11);
        if (index == -1){
            System.out.println("没有找到");
        }else{
            System.out.println("找到，下标为="+ index);
        }
    }

    /**
     * 实现的是找到一个满足条件的值再返回
     * @param arr
     * @param values
     * @return
     */
    public static int seqSearch(int[] arr,int values){
        //逐一比对，有相同值就返回下标
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] == values) {
                return i;
            }
        }
        return -1;
    }
}
