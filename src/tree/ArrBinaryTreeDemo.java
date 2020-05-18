package tree;/*
    @author shl
    @create 2020-04-08-11:48
*/

//按顺序存储二叉树
//用数组实现 树 的数据结构
//实现数组和二叉树的存储结构的转换
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println("=============================");
        arrBinaryTree.infixOrder();
        System.out.println("=============================");
        arrBinaryTree.postOrder();
    }
}

//编写一个ArrBinaryTree,用于实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree() {
    }

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 表示数组的下标
     */
    public void preOrder(int index){
        //如果数组为空，或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        //输出当前的元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((2 * index + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((2 * index + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    //重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    //完成顺序存储二叉树的中序遍历
    /**
     *
     * @param index 表示数组的下标
     */
    public void infixOrder(int index){
        //如果数组为空，或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        //向左递归遍历
        if ((2 * index + 1) < arr.length){
            infixOrder(2 * index + 1);
        }
        //输出当前的元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((2 * index + 2) < arr.length){
            infixOrder(2 * index + 2);
        }
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }


    //完成顺序存储二叉树的后序遍历
    /**
     *
     * @param index 表示数组的下标
     */
    public void postOrder(int index){
        //如果数组为空，或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        //向左递归遍历
        if ((2 * index + 1) < arr.length){
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((2 * index + 2) < arr.length){
            postOrder(2 * index + 2);
        }
        //输出当前的元素
        System.out.println(arr[index]);

    }

    //重载postOrder
    public void postOrder() {
        this.postOrder(0);
    }
}