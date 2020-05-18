package stack;/*
    @author shl
    @create 2020-03-26-15:32
*/

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //test
        //先创建一个对象->表示一个栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");

            System.out.println("请输入你的选择:");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();//关闭流
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是：%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

//创建一个类表示栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据放在数组中
    private int top = -1;//top表示栈底，初始化-1表示没有元素

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        //栈没有满
        top++;
        stack[top] = value;
    }

    //出栈，将栈顶元素返回
    public int pop(){
        //先判断栈是否空
        if (isEmpty()){
            //抛出异常处理
            throw new RuntimeException("栈空，没有数据");
        }
        //栈不空，分三步进行
        //1.取得栈顶的值
        int value = stack[top];
        //2.栈顶向下移
        top--;
        //3.返回值
        return value;
    }

    //显示栈（遍历栈）
    public void list(){
        //遍历时，要从栈顶开始显示数据
        if (isEmpty()){
            System.out.println("栈空，无法遍历");
            return;
        }
        //从栈顶开始显示数据
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

}

//有时间用链表模拟栈给实现了