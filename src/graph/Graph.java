package graph;
/*
    @author shl
    @create 2020-04-22-17:39
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点的集合

    private int[][] edges;//存储图对应的邻接矩阵

    private int numberOfEdges;//表示边的个数

    //定义数组boolean[] 记录某个节点是否被访问过
    private boolean isVisited[];

    public static void main(String[] args) {

        int n = 8;//确定节点的个数
        //String Vertexs[] = {"A","B","C","D","E"};
        String Vertexs[] = {"1","2","3","4","5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String VertexValue : Vertexs) {
            graph.insertVertex(VertexValue);
        }
//        //添加边
//        graph.insertEdge(0,1,1);
//        graph.insertEdge(0,2,1);
//        graph.insertEdge(1,2,1);
//        graph.insertEdge(1,3,1);
//        graph.insertEdge(1,4,1);

        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        graph.insertEdge(3,7,1);
        graph.insertEdge(4,7,1);
        graph.insertEdge(2,5,1);
        graph.insertEdge(2,6,1);
        graph.insertEdge(5,6,1);


        //显示图的邻接矩阵
        graph.showGraph();

        //测试dfs
        System.out.println("dfs");
        graph.dfs();

        //测试bfs
        System.out.println("bfs");
        graph.bfs();

    }

    //构造器
    //n代表构造图的顶点的个数
    public Graph(int n) {
        //初始化矩阵和arraylist
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numberOfEdges = 0;//初始化默认为0
        isVisited = new boolean[vertexList.size()];
    }

    //得到第一个邻接节点的下标
    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0 ) {
                //说明下一个邻接点是存在的
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2) {
        for (int i = v2 + 1; i < vertexList.size() ; i++) {
            if (edges[v1][i] > 0) {
                //说明下一个邻接节点存在
                return i;
            }
        }
        return -1;
    }

    //DFS算法
    //i第一次就是0
    private void dfs(boolean[] isVisited,int i) {

        //首次访问该节点，即输出
        System.out.print(getValueByIndex(i) + "->");
        //将访问过的节点设置成为已经访问过
        isVisited[i] = true;

        //查找节点i的第一个节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            //进入循环，说明找到了
            //说明有邻接节点
            if (!isVisited[w]){
                //没有访问
                dfs(isVisited,w);
            }
            //如果w这个节点被访问过，就去查找节点w的下一个邻接节点
            w = getNextNeighbor(i,w);
        }
    }

    //对dfs进行重载，遍历所有的节点并进行dfs
    public void dfs() {
        //遍历所有的节点进行dfs
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i); //???????????????
            }
        }
    }

    //bfs广度优先遍历
    private void bfs(boolean[] isVisited,int i) {
        int u;//表示队列头结点对应的下标
        int w;//表示邻接节点w

        //队列，记录节点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        //访问这个节点
        System.out.print(getValueByIndex(i) + "->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while (! queue.isEmpty()) { //只要不为空
            //取出队列的头结点下标
             u = (Integer)queue.removeFirst();
             //得到第一个邻接点下标w
             w = getFirstNeighbor(u);
             while (w != -1) {
                 //说明找到了
                 //再判断是否访问过
                 if (!isVisited[w]) {
                     //进入判断说明没有访问过
                     System.out.println(getValueByIndex(w) + "->");
                     //标记已经访问
                     isVisited[w] = true;
                     //入队
                     queue.addLast(w);
                 }
                 //以u为前驱节点找w后面的下一个邻接点
                 w = getNextNeighbor(u,w);//bfs
             }
        }
    }

    //遍历所有的节点都进行bfs算法
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        //对bfs进行重载
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
    }


    //方法：插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //添加边
    //通过对应矩阵是0还是1进行区别于描述的
    /**
     * 无向图，对称矩阵
     * @param v1 第一个顶点对应的下标 从0开始
     * @param v2 第二个顶点对应下标
     * @param weight 权值（0或1）
     */
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

    //返回节点的个数
    public int getNumberOfVertex () {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    //返回节点i对应的数据（下标）类似 0->A,1->B
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1与v2对应的权值
    public int getWeight (int v1,int v2) {
        return edges[v1][v2];
    }

    //显示图对应的邻接矩阵
    public void showGraph() {
        for (int [] link:
            edges ) {
            System.out.println(Arrays.toString(link));
        }
        return;
    }
}
