package common10algorithm;/*
    @author shl
    @create 2020-05-02-19:48
*/

import java.util.Arrays;

public class PrimAlgorithm {

    public static void main(String[] args) {

        //测试图是否创建成功
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertex = data.length;
        //邻接矩阵通过二维数组描述关系
        int [][]weight = new int[][] {
                //10000表示不连通用大数表示两个点不直连
                //ABCDEFG
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        //创建一个MGraph对象
        MGraph mGraph = new MGraph(vertex);

        //创建一个mintree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,vertex,data,weight);
        //输出
        minTree.showGraph(mGraph);

        System.out.println("==============================");

        //测试prim算法
        minTree.prim(mGraph,1);

    }

}

//创建最小生成树MST
class MinTree {
    //创建图的邻接矩阵
    /**
     *
     * @param mGraph 传入图对象
     * @param vertexs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph mGraph,int vertexs,char data[],int [][]weight) {
        for (int i = 0; i < vertexs; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < vertexs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(MGraph mGraph) {
        for (int []link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public MinTree() {
    }

    //prim算法：
    /**
     *
     * @param graph 图
     * @param v 从第几个顶点开始生成
     */
    public void prim(MGraph graph,int v) {
        //visited标记节点是否被访问过
        int[] visited = new int[graph.vertexs];
        //visited默认全为0
        for (int i = 0; i < graph.vertexs; i++) {
            visited[i] = 0;
        }
        //当前节点标记为已经访问：1
        visited[v] = 1;
        int h1 = -1;
        int h2 = -1;
        //h1与h2 用于记录两个顶点的下标
        int minWeight = 10000; //最先初始化最大，后续遍历中用于替换
        for (int k = 1; k < graph.vertexs; k++) {
            //n个顶点，n-1条边
            //从1开始遍历
            //确定每一个生成的子图，和哪个节点的距离最近
            for (int i = 0; i < graph.vertexs; i++) {
                //i节点表示被访问过的节点
                for (int j = 0; j < graph.vertexs; j++) {
                    //j节点表示还没有被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 &&
                            graph.weight[i][j] < minWeight) {
                        //替换minWeight
                        //寻找已经访问过的节点与未访问的节点之间权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //当退出这个for循环，说明找到一条最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前找到得节点 标记为已经访问
            visited[h2] = 1;
            //重置设置minWeight最大值
            minWeight = 10000;
        }
    }
}



//图类
class MGraph {
    int vertexs;//表示节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，即邻接矩阵

    public MGraph() {
    }

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}
