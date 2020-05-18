package linkedlist;/*
    @author shl
    @create 2020-03-25-20:16
*/

public class Josephu {
    public static void main(String[] args) {
        //test
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //test出圈：2-4-1-5-3
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，目前没有编号
    private Boy first = new Boy();
    //添加节点，构建一个环形链表
    public void addBoy(int nums){
        //nums是节点总个数，进行数据校验
        if (nums <= 0){
            System.out.println("数值不正确");
            return;
        }
        Boy curBoy = null;//辅助变量，用于建立环形链表
        //使用for循环创建单向环形链表
        for (int i = 1; i <= nums ; i++) {
            //根据编号，创建节点
            Boy boy = new Boy(i);
            //如果是第一个节点，需要自连构成环
            if (i == 1){
                first = boy;//first不能动，只能指向第一个节点，类似head
                first.setNext(first);//构成一个环状
                curBoy = first;//让辅助变量指向第一个节点
            }
            else{
                //从第二个节点开始，只和curBoy有关系了
                //三步骤
                //----------------也可以把curBoy理解为尾指针-----------------
                curBoy.setNext(boy);//当前指针指向下一个节点，形成连接
                boy.setNext(first);//最后一个节点指向第一个节点
                curBoy = boy;//表示最后的指针向后移一位
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        //判断是否为空
        if (first == null){
            System.out.println("没有任何节点，链表为空");
            return;
        }
        //因为first类似head，不能动，因此仍然使用辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("节点编号为%d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                //说明已经遍历完
                break;
            }
            curBoy = curBoy.getNext();//setnext相当于赋值，getnext相当于后移
        }
    }

    //根据用户的输入，计算出节点出圈的顺序
    /*
    startNo:从第几个节点开始数
    countNum:表示数几下
    nums:最初在圈中总共的节点个数
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //数据校验
        if (first == null || startNo < 0 || startNo > nums ){
            System.out.println("参数输入有误");
            return;
        }
        //创建一个辅助指针，辅助完成出圈
        Boy helper = first;
        //事先将helper指向环形链表的最后这个节点
        while (true){
            if (helper.getNext() == first)//说明指针指向最后一个节点
                break;
            helper = helper.getNext();
        }
        //小孩节点报数前，先让first 和 helper移动k-1次
        for (int j = 0;j < startNo -1 ; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //循环操作，知道圈中只有一个节点
        while (true){
            if (helper == first){ //说明圈中只有一个节点
                break;
            }
            //让first，helper移动countNum-1次，再出圈
            for (int i = 0; i < countNum - 1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点就是要出圈的节点
            System.out.printf("节点%d出圈 \n",first.getNo());
            //将first指向的节点出圈
            first = first.getNext();//目前指针向后移一位
            helper.setNext(first);//原来指针后面的helper指针，指向了移动后现在的指针

        }
        System.out.printf("最后的节点是：%d",first.getNo());
    }
}


//创建一个boy类，表示节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null

    public Boy() {

    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
