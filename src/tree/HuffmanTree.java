package tree;/*
    @author shl
    @create 2020-04-15-15:00
*/

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {

    public static void main(String[] args) {


        int arr[] = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);//返回哈夫曼树根节点
        //哈夫曼树前序遍历
        preOrder(root);

    }

    //创建哈夫曼树的方法：

    /**
     *
     * @param arr 传入数组
     * @return 返回创建后的Huffman树的根节点
     */
    public static Node createHuffmanTree(int []arr) {

        //传入数组，建立哈夫曼树
        //1、遍历arr，将arr每个元素构建成一个node，将node放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value:
            arr ) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //2、排序，默认从小到大，取决于compareTo方法是怎么排序的
            //使用collections工具类
            Collections.sort(nodes);
            //实现有序
            System.out.println("Nodes = " + nodes);

            //3.取出根节点最小的两颗二叉树（理解为二叉树，实际是结点）
            //(1)取出权值最小和次小的结点
            Node leftNode = nodes.get(0);//若从大到小，应该是最后一个
            Node rightNode= nodes.get(1);//若从大到小，应该是倒数第二个
            //(2)从底部开始构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);//新建一个父结点，值是两者之和
            parent.left = leftNode;
            parent.right = rightNode;
            //(3)从ArrayList中删除处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(4)将父节点加入ArrayList
            nodes.add(parent);

            //4.将新二叉数再次从小到大进行排序，直到所有结点都被处理，就构建完成。
            //到最后集合中只有一个元素，以此为条件，循环实现。
        }
        //返回哈夫曼树的根节点
        return nodes.get(0);
    }
    // 前序遍历方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("该哈夫曼树是空树，无法前序遍历。");
        }
    }
}

//创建结点类
//为了让Node类的对象支持排序（集合排序）
//实现comparable接口
class Node implements Comparable<Node>{
    int value;//结点的权值
    //两个指针；分别指向左子结点和右子结点
    Node left;
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //this在前，o在后，表示从小到大排序；若要实现从大到小排，返回结果加负号即可
        return this.value - o.value;//对权值进行比较
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
