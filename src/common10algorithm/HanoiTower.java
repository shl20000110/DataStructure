package common10algorithm;/*
    @author shl
    @create 2020-04-25-17:25
*/

public class HanoiTower {
    public static void main(String[] args) {

        //test
        hanoiTower(5,'A','B','C');

    }

    //汉诺塔移动算法
    //使用分治算法解决
    /**
     *
     * @param num 盘子的个数
     * @param a 分别是ABC三个柱子
     * @param b
     * @param c
     */
    public static void hanoiTower(int num,char a,char b,char c) {

        //int count = 0;//记录执行的次数
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
            //count++;
        } else {
            //n>=2,总是看成两个盘，1、最下面的一个盘 2、上面的所有盘
            //1.先把最上面的盘A->B,移动过程中会使用到C
            //第二个参数是开始的塔，第四个参数是要移动到的塔，中间的参数是移动过程中需要使用到的辅助塔
            hanoiTower(num - 1,a,c,b);


            //2.把最下面的盘移动到C
            System.out.println("第"+ num  + "个盘从 " + a + "->" + c);

            //3.把B所有的盘移动到C，移动过程中会使用到A
            hanoiTower(num -1,b,a,c);
        }
        //System.out.println("共执行了 " + count + "次");
    }

}
