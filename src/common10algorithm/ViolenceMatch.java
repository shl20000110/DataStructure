package common10algorithm;/*
    @author shl
    @create 2020-04-28-20:02
*/

public class ViolenceMatch {

    public static void main(String[] args) {

        String str1 = "dasdkfnsdfkafds";
        String str2 = "sdk";
        //下标从0开始计数
        int index = violenceMatch(str1,str2);//2

        if (index != -1)
            System.out.println("下标为"+index);
        else
            System.out.println("未能成功匹配");;
    }

    //暴力匹配算法实现

    /**
     *
     * @param str1 原始字符串
     * @param str2 子串
     * @return 匹配成功返回初始下标，否则返回-1
     */
    public static int violenceMatch(String str1,String str2) {

        //将字符串转为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1len = str1.length();
        int s2len = str2.length();

        //i，j相当于索引分别指向s1，s2
        int i = 0;
        int j = 0;
        while (i < s1len && j < s2len) {
            //为了保证匹配时没有越界
            if (s1[i] == s2[j]) {
                //说明匹配成功
                i++;
                j++;
            } else {
                //没有匹配成功
                i = i - j + 1;//i = i - (j - 1)
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2len) {
            //说明匹配成功
            return i - j;
        } else {
            return -1;
        }
    }
}
