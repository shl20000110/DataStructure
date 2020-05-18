package queue;/*
    @author shl
    @create 2020-03-21-17:29
*/

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        System.out.println("数组模拟环形队列测试");
        //创建一个队列
        CircleArray queue = new CircleArray(4);//有效数据最大值为3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("接收一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                case 'g':
                    try{
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        int result = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}

class CircleArray {
    private int maxSize;//表示数组的最大容量
    private int front;//指向第一个元素
    private int rear;//指向队列的最后一个元素的后一个位置，空出一个空间
    private int[] arr;//该数组用于存放数据，模拟环形队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;//指向队列头部第一个元素
        rear = 0;//指向队列尾部元素的后一个位置
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入就可以
        arr[rear] = n;
        //rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断是否空
        if (isEmpty()){
            //抛异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
       //1.先把front对应的值保存到一个临时变量
        int value = arr[front];
       //2.将front后移,必须考虑取模
        front = (front + 1) % maxSize;
       //3.将临时保存的变量返回
        return value;
    }

    //显示队列中所有数据
    public void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //修改为：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            //下标不一定是i，因为环形的存在，必须要取余
            System.out.printf("arr[%d] = %d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //求出当前队列的有效的数据个数
    public int size(){
        return (rear + maxSize -front) % maxSize;
    }

    //显示队列的头数据（不是取出数据)
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        //front实际含义即为队列第一个元素
        return arr[front];
    }
}
