package common10algorithm;/*
    @author shl
    @create 2020-05-09-16:02
*/

import java.util.Arrays;

//时间复杂度（n3） 时间复杂度较高
//弗洛伊德算法用于求图中 各点到其余顶点之间的最短路径
public class FloydAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int [][] matrix = new int[vertex.length][vertex.length];
        //65535表示不可连接
        final int N = 65535;

        matrix[0] = new int[]{0,5,7,N,N,N,2};
        matrix[1] = new int[]{5,0,N,9,N,N,3};
        matrix[2] = new int[]{7,N,0,N,8,N,N};
        matrix[3] = new int[]{N,9,N,0,N,4,N};
        matrix[4] = new int[]{N,N,8,N,0,5,4};
        matrix[5] = new int[]{N,N,N,4,5,0,6};
        matrix[6] = new int[]{2,3,N,N,4,6,0};

        //创建一个图对象
        Graphs graphs = new Graphs(vertex.length, matrix, vertex);

        //调用Floyd算法
        graphs.floyd();

        graphs.show();

    }

}

//创建图
class Graphs{

    private char[] vertex; //存放顶点的数组

    private int[][] distance; //保存各个顶点出发到其他顶点的距离

    private int[][] pre; // 保存到达目标节点的前驱节点

    public Graphs() {
    }

    /**
     *
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graphs(int length,int [][]matrix,char []vertex) {
       this.vertex = vertex;
       this.distance = matrix;
       this.pre = new int[length][length];

       //对pre数组进行初始化，存放的是前驱节点的下标，并不是存放的ABCD

        for (int i = 0; i < length; i++) {
            Arrays.fill( pre[i] , i );
        }
    }

    //显示distance 数组 与 pre 数组
    public void show() {

        //为了显示便于阅读，优化输出
        char[] vertex = {'A','B','C','D','E','F','G'};
        for (int i = 0; i < distance.length; i++) {
            //先将pre数组输出
            for (int j = 0; j < distance.length; j++) {

                //pre前驱节点的数组是否可以不打印？？

               // System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //再输出distance数组的一行数据
            for (int j = 0; j < distance.length; j++) {
                System.out.println("（"+ vertex[i]+ "到" + vertex[j] +
                        "最短路径是" +distance[i][j] + "） ");
            }
            System.out.println();
            System.out.println();
        }
//
//        for (int i = 0; i < distance.length; i++) {
//            //先将distance数组输出
//            for (int j = 0; j < distance.length; j++) {
//                System.out.println(distance[i][j] + " ");
//            }
//        }
    }

    //核心算法
    //弗洛伊德算法
    public void floyd() {
        //定义变量保存距离
        int length = 0;
        //对中间顶点的遍历，k代表中间顶点的下标
        for (int k = 0; k < distance.length; k++) {
            //从i顶点开始出发{A,B,C,D,E,F,G}
            for (int i = 0; i < distance.length; i++) {
                //从i顶点出发通过中间顶点k到达j
                for (int j = 0; j < distance.length; j++) {
                    //分别将i到k的距离与k到j的距离相加
                    //相当于求从i顶点出发通过中间顶点k到达j的距离
                    length = distance[i][k] + distance[k][j];
                    if (length < distance[i][j]) {
                        //如果len小于distance[i][j]直连的距离
                        //就更新距离
                        distance[i][j] = length;
                        //更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}