package common10algorithm;/*
    @author shl
    @create 2020-05-07-16:16
*/

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int [][] matrix = new int[vertex.length][vertex.length];
        //65535表示不可连接
        final int N = 65535;

        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        //创建一个图
        Graph graph = new Graph(vertex, matrix);
        //测试
        graph.showGraph();
        //测试
        graph.dijkstra(6);
        graph.showDijkstra();
    }


}

//已访问顶点集合
class  VisitedVertex{

    public int[] already_arr; //记录各个顶点是否访问过，1表示访问过，0表示没有被访问，动态更新次数组

    public int[] pre_visited;//每个下标对应的值为前一个顶点下标，会动态更新

    public int[] distance;//记录出发顶点到其他所有顶点的距离，求得最短距离就会放在这个数组中，动态更新

    public VisitedVertex() {
    }

    /**
     *
     * @param length 顶点的个数
     * @param index 出发顶点对应的下标，如G点对应下标为6
     */
    public VisitedVertex(int length,int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.distance = new int[length];

        //初始化distance数组
        //上来一开始默认全部不可到达
        Arrays.fill(distance,655335);
        this.already_arr[index] = 1;//设置出发顶点被访问过
        //将出发顶点，自己到自己的距离置为0
        this.distance[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过，就返回true，否则返回false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param length
     */
    public void updateDistance(int index,int length) {
        distance[index] = length;
    }

//    //更新index下标到周围顶点的距离和周围顶点的前驱节点
//    private void update(int index) {
//
//    }

    /**
     * 更新pre
     * 顶点的前驱为index结点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDistance(int index) {
        return distance[index];
    }

    //继续选择并返回新的访问节点
    /**
     *
     * @return 继续选择并返回新的访问节点
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && distance[i] < min) {
                //目前下标为i的顶点还没有被访问过
                min = distance[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    //显示最后结果
    //将三个数组情况输出
    public void show() {
        System.out.println("===================================");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visted
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出distance
        for (int i : distance) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("===================================");
        //美化结果处理
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i:
            distance ) {
            if ( i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")");
            } else {
                //表示无穷大
                System.out.println("N");
            }
            count ++;
        }
        System.out.println();
    }



}

class Graph{

    private char[] vertex; //存放顶点数组
    private int[][] matrix; //记录邻接矩阵
    private VisitedVertex visitedVertex;//已经访问顶点的集合

    public Graph() {
    }

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int []link:
             matrix ) {
            System.out.println(Arrays.toString(link));

        }
    }

    //显示结果方法
    public void showDijkstra() {
        visitedVertex.show();
    }

    //迪杰斯特拉算法
    /**
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dijkstra(int index) {

       visitedVertex = new VisitedVertex(vertex.length, index);
       //更新index顶点到周围顶点的距离与前驱顶点
       update(index);

       for (int j = 0; j < vertex.length; j++) {
           //选择并返回新的访问节点
           index = visitedVertex.updateArr();
           //更新index顶点到周围顶点的距离与前驱顶点
           update(index);
       }
    }


    //更新index下标到周围顶点的距离和周围顶点的前驱节点
    private void update(int index) {
        int len = 0;
        //根据遍历邻接矩阵的matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {

            //len表示出发顶点到index顶点的距离+从index顶点到j顶点的距离的和
            // (从出发顶点经过index再到j的距离)
            len = visitedVertex.getDistance(index) + matrix[index][j];
            if ( !visitedVertex.in(j) && len < visitedVertex.getDistance(j)) {
                //如果j这个顶点没有被访问过，并且len 小于出发顶点到j顶点的距离，就需要更新

                //更新j的前驱节点为index节点
                visitedVertex.updatePre(j,index);
                //更新出发顶点到j顶点的距离
                visitedVertex.updateDistance(j,len);
            }
        }
    }
}