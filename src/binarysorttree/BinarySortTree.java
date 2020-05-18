package binarysorttree;/*
    @author shl
    @create 2020-04-17-17:41
*/

public class BinarySortTree {

    public static void main(String[] args) {

        int arr[] = {7,3,10,12,5,1,9,2};
        Bst binarySortTree = new Bst();

        //循环添加节点到bst中
        for (int i = 0; i < arr.length ; i++) {
            //每次加入都new一个node节点
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历输出
        System.out.println("中序遍历BST:");
        binarySortTree.infixOrder();

        //测试BST删除
        binarySortTree.deleteNode(2);//情况一：删除一个叶子节点
        binarySortTree.deleteNode(1);//情况二：删除只有一个叶子节点的节点
        binarySortTree.deleteNode(10);//情况三：删除有两个叶子节点的节点
        System.out.println("删除节点后");
        binarySortTree.infixOrder();

    }
}

//创建二叉排序树
class Bst {
    private Node root;//根节点

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
