package tree;/*
    @author shl
    @create 2020-04-06-21:23
*/

public class BinaryTreeDemo {

    public static void main(String[] args) {

        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1,"zs");
        HeroNode heroNode2 = new HeroNode(2,"ls");
        HeroNode heroNode3 = new HeroNode(3,"ww");
        HeroNode heroNode4 = new HeroNode(4,"zl");
        HeroNode heroNode5 = new HeroNode(5,"cn");
        //需要的节点创建

        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();


        //前序遍历查找测试
        System.out.println("前序遍历方式：");
        HeroNode res = binaryTree.postOrderSearch(5);
        if (res != null) {
            System.out.printf("找到了，信息为%d，name为%s\n",res.getNo(),res.getName());
        }else {
            System.out.println("没有找到");
        }

        //删除测试
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        //binaryTree.deleteNode(5);
        binaryTree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();




    }



}

//定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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



//先创建节点HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

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
