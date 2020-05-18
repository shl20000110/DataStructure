package linkedlist;/*
    @author shl
    @create 2020-03-25-19:22
*/

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表test");

        HeroNode1 node1 = new HeroNode1(1,"张三","zs");
        HeroNode1 node2 = new HeroNode1(2,"李四","ls");
        HeroNode1 node3 = new HeroNode1(3,"王五","ww");
        HeroNode1 node4 = new HeroNode1(4,"赵六","zl");

        //创建一个双向链表
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(node1);
        doubleLinkList.add(node2);
        doubleLinkList.add(node3);
        doubleLinkList.add(node4);

        doubleLinkList.list();

        //修改
        HeroNode1 newNode = new HeroNode1(4,"石弘利","shl");
        doubleLinkList.update(newNode);
        System.out.println("修改后的链表为：");
        doubleLinkList.list();

        //删除
        doubleLinkList.delete(2);
        System.out.println("删除后的链表是：");
        doubleLinkList.list();
    }
}

//创建一个双向链表的类（带表头）
class DoubleLinkList{
    //初始化一个头结点
    private HeroNode1 head = new HeroNode1(0,"", "");//头结点不存放具体的数据

    //返回头结点
    public HeroNode1 getHead() {
        return head;
    }

    //遍历双向链表（和单链表一样）
    public void list(){
        //判断链表是否为空，空的话不要遍历
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，引入辅助变量来变量
        //不为空，则至少有一个数据
        HeroNode1 temp = head.next;
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

    //双向链表添加（默认添加到双向链表最后）
    public void add(HeroNode1 heroNode){
        //head节点不能动，引入辅助变量temp指向头结点head
        HeroNode1 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改双向链表中的值（与单链表几乎一样，节点类型进行了改变）
    public void update(HeroNode1 newHeroNode){
        //根据newHeroNode的no来修改即可
        //判断链表是否空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //先定义辅助变量
        HeroNode1 temp = head.next;
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

    //双向链表删除一个节点
    //1.对于双向链表，可以直接找到要删除的节点，不像单链表那样找到删除节点的前一个节点再删除
    //2.找到后自我删除
    public void delete(int no){
        //head不能动，引入辅助节点temp找到待删除节点的前一个节点
        //判断当前链表是否为空
        if (head.next == null){//空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode1 temp = head.next;//辅助变量从head.next开始，不是从head开始了，因为可以直接找到待删除的节点
        boolean flag = false;//标志是否找到待删除节点
        while (true){
            if (temp == null){
                //实际上已经到链表的最后节点的next了
                break;
            }
            if (temp.no == no){
                //找到了待删除节点前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag){
            //进行删除(两句话，画图理解）
            //temp.next = temp.next.next{单向链表}
            temp.pre.next = temp.next;
            //存在小问题（如果是最后一个节点，就不需要执行下面语句，否则出现空指针异常）
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在",no);
        }
    }
}

class HeroNode1{
    public int no;
    public String name;
    public String nickName;
    public HeroNode1 next;//指向下一个节点，默认为null
    public HeroNode1 pre;//指向前一个节点，默认为null

    public HeroNode1(int no, String name, String nickName) {//构造器
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