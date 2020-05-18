package tree.threadbinarytree;/*
    @author shl
    @create 2020-04-08-12:23
*/

//线索化二叉树
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        //测试中序线索二叉树
        HeroNode root = new HeroNode(1, "a");
        HeroNode node2 = new HeroNode(3, "b");
        HeroNode node3 = new HeroNode(6, "c");
        HeroNode node4 = new HeroNode(8, "d");
        HeroNode node5 = new HeroNode(10, "e");
        HeroNode node6 = new HeroNode(14, "f");
        //测试使用手动创建，也可以递归创建

        //建立关系
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        //设置根节点，否则无法调用
        threadedBinaryTree.setRoot(root);
        //重载方法后，可以省略判断root是否为空这一步骤
        threadedBinaryTree.infixThreadNodes();

        //找点进行测试 10
        HeroNode leftNode = node5.getLeft();//3
        HeroNode rightNode = node5.getRight();//1
        System.out.println("10号节点前驱节点是 = "+leftNode);
        System.out.println("10号节点后继节点是 = "+rightNode);

        //线索化二叉树后，不能采用原来的遍历方法
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();//8 3 10 1 14 6

    }
}
//创建线索化二叉树的class
class ThreadedBinaryTree {
    private HeroNode root;
    //实现线索化，要创建一个指向当前节点的前驱节点的指针（变量）
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载前序线索化方法
    public void preThreadNodes() {
        this.preThreadNodes(root);
    }
    //重载中序线索化方法
    public void infixThreadNodes() {
        this.infixThreadNodes(root);
    }
    //重载后序线索化方法
    public void postThreadNodes() {
        this.postThreadNodes(root);
    }





    //遍历前序线索化二叉树的办法
    //遍历后序线索化二叉树的办法（？？？）卡住了






    //遍历中序线索化二叉树的方法
    //非递归的方法
    //debug理解
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始遍历
        HeroNode node = root;
        while (node != null) {
            //循环的找到leftType==1的节点
            //后面随着遍历会变化，当leftType==1的时候说明该节点按照线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前的节点
            System.out.println(node);
            //如果当前节点右指针指向的是后继节点，如果是，就一直输出
            while (node.getRightType() == 1) {
                //获得当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }//循环结束说明现在右指针节点不等于1
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //对二叉树前序线索化的方法
    /**
     * 前序线索化：中->左->右
     * @param node 当前需要线索化的节点
     */
    public void preThreadNodes(HeroNode node) {
        //若节点为空，无法线索化
        if (node == null) {
            return;
        }
        //1.先线索化当前节点
        //先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针，指向前驱节点
            node.setLeft(pre);
            //修改当前节点左指针的类型
            //1说明当前节点左指针已经指向前驱节点了
            //如果是第一个节点，此时pre还是初始值null，left == null,leftType == 1
            node.setLeftType(1);
        }
        //处理后继节点
        //实际上是递归后，对前一个指针的处理（画图理解）
        //因为树单向，不能同时处理左右
        if ( pre != null && pre.getRight() == null ) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱指针的右指针类型
            pre.setRightType(1);
        }
        //!!
        //每次处理一个节点后，就让当前节点是下一个节点的前驱节点：实现递归
        pre = node;
        //2.再线索化左子树
        preThreadNodes(node.getLeft());
        //3.最后线索化右子树
        preThreadNodes(node.getRight());
    }

    //对二叉树中序线索化的方法
    /**
     * 中序线索化：左->中->右
     * @param node 当前需要线索化的节点
     */
    public void infixThreadNodes(HeroNode node) {
        //若节点为空，无法线索化
        if (node == null) {
            return;
        }

        //1.先线索化左子树
        infixThreadNodes(node.getLeft());

        //2.再线索化当前节点

        //先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针，指向前驱节点
            node.setLeft(pre);
            //修改当前节点左指针的类型
            //1说明当前节点左指针已经指向前驱节点了
            //如果是第一个节点，此时pre还是初始值null，left == null,leftType == 1
            node.setLeftType(1);
        }
        //处理后继节点
            //实际上是递归后，对前一个指针的处理（画图理解）
            //因为树单向，不能同时处理左右
        if ( pre != null && pre.getRight() == null ) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱指针的右指针类型
            pre.setRightType(1);
        }
        //!!
        //每次处理一个节点后，就让当前节点是下一个节点的前驱节点：实现递归
        pre = node;

        //3.最后线索化右子树
        infixThreadNodes(node.getRight());
    }

    //对二叉树后序线索化的方法
    /**
     * 前序线索化：左->右->中
     * @param node 当前需要线索化的节点
     */
    public void postThreadNodes(HeroNode node) {
        //若节点为空，无法线索化
        if (node == null) {
            return;
        }

        //1.先线索化左子树
        postThreadNodes(node.getLeft());

        //2.然后线索化右子树
        postThreadNodes(node.getRight());

        //3.最后线索化当前节点
        //先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针，指向前驱节点
            node.setLeft(pre);
            //修改当前节点左指针的类型
            //1说明当前节点左指针已经指向前驱节点了
            //如果是第一个节点，此时pre还是初始值null，left == null,leftType == 1
            node.setLeftType(1);
        }
        //处理后继节点
        //实际上是递归后，对前一个指针的处理（画图理解）
        //因为树单向，不能同时处理左右
        if ( pre != null && pre.getRight() == null ) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱指针的右指针类型
            pre.setRightType(1);
        }
        //!!
        //每次处理一个节点后，就让当前节点是下一个节点的前驱节点：实现递归
        pre = node;
    }

    //删除节点
    public void deleteNode(int no) {
        if (root != null) {
            //如果只有一个root节点，则立刻判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //不是要删除的，就进行递归删除
                root.deleteNode(no);
            }
        } else {
            System.out.println("空数，无法删除");
        }
    }


    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    //因为left指向的是左子树或者是前驱节点；right指向的是右子树或者是后继节点，要加以区分
    //说明：
    //1.如果leftType == 0，表示指向的是左子树，leftType == 1, 表示指向的是前驱节点
    //2.如果rightType == 0，表示指向的是右子树，rightType == 1, 表示指向的是后继节点

    private int leftType;
    private int rightType;


    public HeroNode() {
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //递归删除节点
    //1.如果是叶子节点，就删除该节点
    //2.如果是非叶子节点，就删除该子树
    public void deleteNode(int no) {

        //如果当前节点左子节点不为空，并且左子节点就是要删除的节点，就将this.left置空，并且返回
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //如果当前节点右子节点不为空，并且左子节点就是要删除的节点，就将this.right置空，并且返回
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //若上面两步都没有删除节点，就向左子树进行递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        //若还没有删除，就向右子树进行递归删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    //编写前序遍历方法
    public void preOrder() {

        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向左子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //编写中序遍历方法
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);//输出父节点

        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //编写后序遍历方法
    public void postOrder() {
        //递归向左子树前后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归向左子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序遍历查找
    /**
     *
     * @param no 查找no
     * @return 如果找到就返回node，没有找到就返回null
     */
    public HeroNode preOrderSearch(int no) {
        //比较当前节点是不是
        if (this.no == no){
            return this;
        }
        //若不是，判断左子树是否为空，如果不为空，递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            //说明左子树找到了
            return resNode;
        }
        //判断右子树是否为空，如果不为空，递归前序查找
        //如果右递归前序查找，找到节点，则返回
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //最终遍历可能为空，也可能找到
        return resNode;
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no) {

        //判断左子树是否为空，如果不为空，递归中序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            //说明左子树找到了
            return resNode;
        }
        //比较当前节点是不是，若是则返回当前节点
        if (this.no == no){
            return this;
        }
        //判断右子树是否为空，如果不为空，递归中序查找
        //如果右递归前序查找，找到节点，则返回
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        //最终遍历可能为空，也可能找到
        return resNode;
    }

    //后序遍历
    public HeroNode postOrderSearch(int no) {

        //判断左子树是否为空，如果不为空，递归后序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            //说明左子树找到了
            return resNode;
        }

        //判断右子树是否为空，如果不为空，递归后序查找
        //如果右递归前序查找，找到节点，则返回
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        //若左右节点都没有找到，则比较当前节点是不是，若是则返回当前节点
        if (this.no == no){
            return this;
        }
        return resNode;
    }
}