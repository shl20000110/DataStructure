package linkedlist;/*
    @author shl
    @create 2020-03-22-13:58
*/

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode node1 = new HeroNode(1,"张三","zs");
        HeroNode node2 = new HeroNode(2,"李四","ls");
        HeroNode node3 = new HeroNode(3,"王五","ww");
        HeroNode node4 = new HeroNode(4,"赵六","zl");

        //创建一个单链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);
//        singleLinkedList.add(node4);

        singleLinkedList.insert(node4);
        singleLinkedList.insert(node2);
        singleLinkedList.insert(node1);
        singleLinkedList.insert(node3);

        //修改节点
            //修改前显示
        singleLinkedList.list();
        HeroNode newHeroNode = new HeroNode(2,"石弘利","shl" );
        singleLinkedList.update(newHeroNode);

        System.out.println("-----------------------修改后的链表------------------------");

        //删除节点
//        singleLinkedList.delete(1);
//        singleLinkedList.delete(4);

        //测试单链表中有效节点的个数
        System.out.println("有效节点个数：" + SingleLinkedList.getLength(singleLinkedList.getHead()));

        //显示
        singleLinkedList.list();
        System.out.println("---------------------------------------------------------------------");

        //查看倒数第index个节点
        HeroNode res = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(),3);
        System.out.println(res);
        System.out.println("---------------------------------------------------------------------");

        //反转单链表
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        System.out.println("----------------------------------------------------------------------");


        //逆序打印单链表
        System.out.println("逆序打印结果：");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());
    }
}

//定义SingleLinkList单链表管理
class SingleLinkedList{
    //初始化一个头结点
    private HeroNode head = new HeroNode(0,"", "");//头结点不存放具体的数据

    //返回头结点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //不考虑编号的顺序时
    //1.找到当前链表的最后节点
    //2.将最后一个节点的next域指向新的节点即可
    public void add(HeroNode heroNode){

        //head节点不能动，引入辅助变量temp指向头结点head
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后将temp后移
            temp = temp.next;
        }
        //退出while循环时，temp已经指向了链表的最后
        //将最后节点的next指向新的节点
        temp.next = heroNode;
    }

    //按编号顺序插入
    public void insert(HeroNode heroNode){
        //头结点不能动，依然通过一个辅助指针来找到插入的位置
        //temp是位于插入位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标志插入的编号是否存在，默认false
        while (true) {
            if (temp.next == null){
                break;
            }
            if (temp.next.no > heroNode.no){
                //找到位置，插入temp后边插入
                break;
            }else if (temp.next.no == heroNode.no){
                //说明编号已经存在了
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag){
            System.out.printf("编号%d已经存在，不能添加",heroNode.no);
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }


    }
    //遍历链表
    public void list(){
        //判断链表是否为空，空的话不要遍历
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，引入辅助变量来变量
        //不为空，则至少有一个数据
        HeroNode temp = head.next;
        while (true){
            //判断链表是否到最后
            if (temp == null){
                break;
            }
            //没有到链表最后，则输出信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //修改节点的信息，根据no修改
    public void update(HeroNode newHeroNode){
        //根据newHeroNode的no来修改即可
        //判断链表是否空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //先定义辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null) {
                break;//已经到链表的最后
            }
            if (temp.no == newHeroNode.no){
                //找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else
        {
            //没有找到
            System.out.printf("没有找到编号为%d的节点",newHeroNode.no);
        }
    }

    //删除一个节点
    public void delete(int no){
        //head不能动，引入辅助节点temp找到待删除节点的前一个节点
        //在比较时，是temp.next.no和待删除的节点的no进行比较
        HeroNode temp = head;
        boolean flag = false;//标志是否找到待删除节点
        while (true){
            if (temp.next == null){
                //已经到链表的最后
                break;
            }
            if (temp.next.no == no){
                //找到了待删除节点前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //进行删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }
    //------------------------------------------------------------------------------

    //方法：获取单链表的节点个数（带头结点的链表不统计头结点）
    //head:单链表头结点
    public static int getLength(HeroNode head){
        if (head.next == null)//带头结点的空的单链表
            return 0;
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;//遍历头结点
        }
        return length;
    }

    //查找单链表中的倒数第k个节点
    //1.接收head节点和index index表示倒数第index个节点
    //2.先把链表从头到尾遍历，得到链表的总的长度 getLength
    //3.得到size后，从链表第一个开始遍历，遍历size-index个
    //找到返回，找不到返回空
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //如果链表为空，返回null
        if (head.next == null){
            return null;
        }
        //第一次遍历获得链表长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size - index 位置，就是倒数第k个节点
        if (index <= 0 || index > size){
            return null;
        }
        //定义一个辅助变量,for循环定位倒数index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index ; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表进行翻转
    public static void reverseList(HeroNode head){
        //如果当前链表为空或者只有一个元素，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        //定义一个辅助指针（变量），进行遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点【cur】的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就放到reverseHead的最前端
        while (cur != null){
            next = cur.next;//暂时保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur下一个节点指向新的链表的最前端
            reverseHead.next  = cur;//将cur连接到新的链表上
            cur = next;//cur后移
        }
        //head.next 指向 reverseHead.next
        head.next = reverseHead.next;
    }

    //逆序打印单链表（利用栈先进后出的特点）
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表所有节点压入栈中
        while (cur != null){
            stack.push(cur);
            cur = cur.next;//cur后移，压入下一个节点
        }
        //将栈中节点打印即可
        while (stack.size() > 0){
            System.out.println(stack.pop());//先进后出
        }
    }
}





//定义HeroNode，每个HeroNode就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {//构造器
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    //为了显示方便，重写tostring方法
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}