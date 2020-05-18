package common10algorithm;/*
    @author shl
    @create 2020-04-26-20:37
*/
/*
    动态规划算法————背包问题
*/
public class KnapsackProblem {

    public static void main(String[] args) {

        int w[] = {1, 4, 3,5,2};//物品的重量
        int val[] = {1500, 3000, 2000,5000,3500};//对应物品的价值
        int m = 6;//表示背包的容量
        int n = val.length;//物品的个数

        //定义一个二维数组,记录商品存放情况
        int[][] path = new int[n + 1][m + 1];


        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包的最大价值
        int[][] v = new int[n + 1][m + 1];

        //初始化第一行与第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //将第一行设置为0
        }

        //根据公式动态规划处理背包问题
        //不处理第一行，i从1开始
        for (int i = 1; i < v.length; i++) {
            //不处理第一列
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i - 1] > j) {
                    //因为程序i从1开始，原来公式中w[i]修改成i-1
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为i从1开始，因此公式调整成下面的代码
                    //v[i][j] = Math.max(v[i - 1][j],val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //
                    //进一步使用if-else记录商品存放在背包中的情况
                    //不能直接使用上述公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        //输出最后放入背包的商品
        System.out.println("============================");

        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            //从path数组最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包中\n",i);
                j -= w[i - 1];//
            }
            i--;
        }

    }

}
