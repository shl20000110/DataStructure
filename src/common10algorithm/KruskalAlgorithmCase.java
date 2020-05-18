package common10algorithm;/*
    @author shl
    @create 2020-05-05-16:39
*/

import java.util.Arrays;

public class KruskalAlgorithmCase {

    private int edgeNum; //记录边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE;
    //INF常量表示两个顶点之间不能连通

    //构造器

    public KruskalAlgorithmCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数与边的个数
        int vlen = vertexs.length;
        //初始化顶点
        //使用复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //或者直接this.vertexs = vertexs

        //初始化边
        //使用复制拷贝方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen ; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    //说明这条边是有效的
                    edgeNum ++;
                }
            }
        }
    }

    public void Kruskal() {
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //用于保存已有最小生成树中的每个顶点在最小生成树中的终点
        //创建结果数组
        //保存最小生成树
        EData[] results = new EData[edgeNum];

        //获取原始图中所有的边的集合
        //初始12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合"+ Arrays.toString(edges));
        System.out.println("共"+ edges.length + "条边");

        //按照边的权值大小进行排序,从小到大
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中，判断准备加入的边是否构成了回路，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第二个顶点（终点）
            int p2 = getPosition(edges[i].end);

            //获取p1已有（当前）的最小生成树中的终点是哪个
            int m = getEnd(ends,p1);
            //获取p2已有（当前）的最小生成树中的终点是哪个
            int n = getEnd(ends,p2);

            //判断是否构成回路
            if(m != n) {
                //没有构成回路
                //设置m在已有最小生成树中的终点
                ends[m] = n;
                //有一条边加入到results数组中
                results[index++] = edges[i];
            }
        }
        //统计并打印最小生成树，输出results数组
        System.out.println("最小生成树 = ");
        for (int i = 0; i < index; i++) {
            System.out.println(results[i]);
        }


    }


    //打印方法
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t",matrix[i][j]);
            }
            //打印完每一行之后进行换行处理
            System.out.println();
        }
    }

    public static void main(String[] args) {

        char []vertexs = {'A','B','C','D','E','F','G'};
        int [][]matrix = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,10}
        };

        KruskalAlgorithmCase kruskalAlgorithmCase = new KruskalAlgorithmCase(vertexs, matrix);
        //输出
        kruskalAlgorithmCase.print();
        kruskalAlgorithmCase.Kruskal();


//        EData[] edges = kruskalAlgorithmCase.getEdges();
//        //未排序
//        System.out.println(Arrays.toString(edges));
//
//        System.out.println("=======================================");
//        //排序后
//        kruskalAlgorithmCase.sortEdges(edges);
//        System.out.println(Arrays.toString(edges));

        //System.out.println(Arrays.toString(kruskalAlgorithmCase.sortEdges(kruskalAlgorithmCase.getEdges())));
    }


    //对边进行排序处理
    //冒泡排序
    /**
     *
     * @param edges 边的集合
     * 对边进行排序，冒泡排序
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    //交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }


    /**
     *
     * @param ch 传入顶点的值，如‘A’，‘B’等等
     * @return 返回ch顶点对应的下标，若找不到，则返回-1
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                //说明找到
                return i;
            }
        }
        //找不到直接返回-1
        return -1;
    }

    /**
     * 获取图中的边，放到EData[] 数组中，后续需要遍历该数组
     * 通过matrix邻接矩阵得到
     * 类似['A','B',12]。。。
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index ++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点是否相同
     * @param ends 数组记录了各个顶点对应的终点是哪个
     * @param i 表示传入顶点对应的下标
     * @return 返回下标为i对应顶点的终点的下标
     */
    private int getEnd(int [] ends,int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


}

//创建一个类data，对象实例表示为一条边
class EData {

    char start;//边的一个点
    char end; //边的另外一个点
    int weight;//边的权值

    //构造器
    public EData() {
    }

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写toString方法，便于输出边

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                ">, weight=" + weight +
                '}';
    }


//    //对边进行排序处理
//    //冒泡排序
//    /**
//     * @param edges 边的集合
//     *              对边进行排序，冒泡排序
//     */
//    private void sortEdges(EData[] edges) {
//        for (int i = 0; i < edges.length - 1; i++) {
//            for (int j = 0; j < edges.length - i; j++) {
//                if (edges[j].weight > edges[j + 1].weight) {
//                    //交换
//                    EData tmp = edges[j];
//                    edges[j] = edges[j + 1];
//                    edges[j + 1] = tmp;
//                }
//            }
//        }
//    }
}


