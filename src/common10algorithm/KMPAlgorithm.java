package common10algorithm;/*
    @author shl
    @create 2020-04-28-20:14
*/

import java.util.Arrays;

//KMP算法
public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        //test

        int[] next = kmpNext("ABCDABD");
        System.out.println("next = " + Arrays.toString(next));

        int index = kmpSearch(str1,str2,next);
        System.out.println("index = "+index);//15


    }

    //KMP搜索算法
    /**
     *
     * @param str1 原始字符串
     * @param str2 子串
     * @param next 部分匹配值表，子串对应的部分匹配表
     * @return 没有匹配到，返回-1，否则返回第一匹配位置的下标。从0开始计数
     */
    public static int kmpSearch(String str1,String str2,int []next) {

        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //处理str1.charAt(i) ！= str2.charAt(j)
            //KMP算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //j向部分匹配表前面匹配，直到找到一个相同的为止
                //!!!!!!!!!!!!!!!!!!!!
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                //说明找到了
                return i - j + 1;
            }
        }
        return -1;
    }



    //获取一个字符串（子串）的 部分匹配值表
    public static int[] kmpNext(String dest) {

        //创建一个与dest长度相同的字符数组：next保存部分匹配值
        int []next = new int[dest.length()];

        //如果dest的长度为1，则部分匹配值就是0
        next[0] = 0;
        for (int i = 1,j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) ！= dest.charAt(j),需要从next[j-1]获取新的j
            //直到发现dest.charAt(i) == dest.charAt(j）成立时才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //KMP算法的核心点
                //!!!!!!!!!!!!!
                j = next[j - 1];
            }
            //dest.charAt(i) == dest.charAt(j)
            if (dest.charAt(i) == dest.charAt(j)) {
                //满足条件时，部分匹配值就要加1
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
