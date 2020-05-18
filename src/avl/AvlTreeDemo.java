package avl;/*
    @author shl
    @create 2020-04-18-15:59
*/

//在二叉排序树基础上建立AVL树
//平衡二叉树

public class AvlTreeDemo {

    public static void main(String[] args) {

        //int[] arr = {4,3,6,5,7,8};
        //int[] arr1 = {10,12,8,9,7,6};
        int [] arr2 = {10,11,7,6,8,9};
        //创建AVL对象
        AvlTree avlTree = new AvlTree();
        //添加节点
        for (int i = 0; i < arr2.length ; i++) {
            avlTree.add(new Node(arr2[i]));
        }
        //遍历
        System.out.println("中序遍历：");
        avlTree.infixOrder();

        System.out.println("=======================================");

       // System.out.println("没有进行平衡处理之前：");
        System.out.println("树的高度 = "+avlTree.getRoot().height());
        System.out.println("树的左子树高度 = "+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度 = "+avlTree.getRoot().rightHeight());
        System.out.println("树的根节点 = "+avlTree.getRoot());
        System.out.println("根节点的左子节点 = "+avlTree.getRoot().left);
        System.out.println("根节点的右子节点 = "+avlTree.getRoot().right);
        //画图检验。。。

    }
}

//创建AvlTree
class AvlTree {
    private Node root;//根节点

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(Node node) {
        if (root == null) {
            //空树
            root = node;
        } else {
            //不为空就加入
            root.add(node);
        }
    }

    //中序遍历方法
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历。");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchNodeParent(value);
        }
    }

    //

    /**
     * 返回的是以node为根节点的二叉排序树得最小节点的值
     * 删除以node为根节点的二叉排序树的最小节点
     *
     * @param node 传入的节点当做二叉排序树的根节点
     * @return 返回的是以node为根节点的二叉排序树得最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左节点，找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //target指向最小节点
        //删除最小节点
        deleteNode(target.value);
        return target.value;
    }

    //删除节点
    public void deleteNode (int value) {
        if (root == null) {
            return;
        } else {
            //1.找要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果targetNode没有父节点，相当于就是根节点
            if (root.left == null && root.right == null) {
                //只有一个节点就是root节点
                root = null;
                return;
            }
            //2.找targetNode父节点
            Node parent = searchParent(value);
            //情况一：如果删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //(1)判断target是parent的左节点还是右节点
                if (parent.left != null && parent.left.value == value) {
                    //说明是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    //说明是右子节点
                    parent.right = null;
                }
                //情况三：如果删除的节点是有两个叶子节点
            } else if (targetNode.left != null && targetNode.right != null) {

                //从target结点开始找到右子树值最小的节点、左子树最大的值的节点也可以
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            }   //情况二：如果删除的节点是只有一个叶子节点（用排除法）
            else {
                //如果要删除的节点targetNode有左子节点
                if (targetNode.left != null) {
                    //若根节点是空要单独判断，否则会出现顺序bug
                    if (parent != null) {
                        //targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }

                }  //如果要删除的节点targetNode有右子节点
                else {
                    //若根节点是空要单独判断，否则会出现顺序bug
                    if (parent != null) {
                        if (parent.left.value == value) {
                            //targetNode是parent的左子节点
                            parent.left = targetNode.right;
                        } else {
                            //targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

//创建节点
class Node {
    int value;
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

    //返回左子树的高度
    public int leftHeight () {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight () {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回当前节点为根节点的树的高度
    public int height() {
        //太妙了
        //左子树若为空，返回0，左子树不为空，就递归进行
        //右子树若为空，返回0，右子树不为空，就递归进行
        //最后取两者最大值加1
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height())+ 1;
    }

    //左旋转的方法：
    private void leftRotate() {

        //创建新的节点，以当前节点的值创建，值保持一致
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        ////把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        //当前节点的左子节点（左子树）设置成新的节点
        left = newNode;

    }

    //右旋转的方法：
    private void rightRotate() {

        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    //查找要删除的节点的方法
    /**
     *
     * @param value 删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            //说明找到
            return this;
        } else if (value < this.value) {
            //如果查找的值小于当前节点，就向左子树递归查找
            //左子节点为空就不能再找了
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //如果查找的值大于等于当前节点，就向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    /**
     *
     * @param value 要删除的节点
     * @return 返回其父节点，找不到就返回null
     */
    public Node searchNodeParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null&& this.right.value == value)) {
            //this.left != null && this.left.value == value) ||
            // (this.right != null&& this.right.value == value)
            //判断条件
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归查找
                return this.left.searchNodeParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归查找
                return this.right.searchNodeParent(value);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    //添加节点方法
    //递归形式添加，满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入节点和当前子树根节点的大小
        //this等价于当前子树的根节点
        if (node.value < this.value) {
            if (this.left == null) {
                //当前节点左子节点为空
                this.left = node;
            } else {
                //不为空，就递归向左子树添加
                this.left.add(node);
            }
        } else {
            //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
        //添加节点时，进行判断，如果(右子树高度-左子树高度) > 1 ,就进行左旋转
        if ((rightHeight() - leftHeight()) > 1) {
            //如果他的右子树的左子树的高度大于右子树的右子树的高度
            if (right != null && (right.rightHeight() < right.leftHeight())) {
                //先他的（右子节点）右子树进行旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftRotate();
            } else {
                //直接进行左旋转
                leftRotate();
            }
            //如果完成了就不用向下执行，直接返回
            return;
        }
        //当添加一个节点后，左子树高度-右子树高度 > 1,右旋转
        if ((leftHeight() - rightHeight()) > 1) {
            //如果他的左子树的右子树的高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点（左子树）进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转
                rightRotate();
            }

        }
    }

    //中序遍历
    //左中右
    public void infixOrder() {

        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
