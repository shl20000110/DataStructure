package stack;/*
    @author shl
    @create 2020-03-26-16:49
*/

public class Calculator {
    public static void main(String[] args) {
        //表达式计算器
        String expression = "70+3*6-5";//要测试的表达式
        //创建两个栈：
        //数栈 和 符号栈
        ArrayStack1 numStack = new ArrayStack1(100);
        ArrayStack1 operStack = new ArrayStack1(100);
        //定义相关变量
        int index = 0;//索引值，用于扫描表达式
        int num1 = 0;//先pop出来的数
        int num2 = 0;//后pop出来的数
        int oper = 0;//pop出来的运算符
        int res = 0;//最后的结果
        char ch = ' ';//将每次扫描得到的char保存到ch里
        String keepNum = "";//用于拼接多位数

        //开始while循环的扫描expression
        while (true){
            //先依次expression的每个字符
            ch = expression.substring(index,index+1).charAt(0);//每次返回一个字符
            //判断ch是数字还是符号，分别做不同的处理

            if (operStack.isOper(ch)){//判断是不是运算符
                //若是运算符，判断符号栈是否为空
                if (!operStack.isEmpty()){
                    //进入此if 则表示符号栈不为空
                    //再次判断，符号栈有操作符就进行比较，当前操作符优先级小于等于栈的优先级就进行下面操作
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //数栈pop出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        //符号栈pop一个符号
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);//调用之前定义的计算方法计算
                        //运算后，将运算的结果入数栈
                        numStack.push(res);
                        //当前符号入栈
                        operStack.push(ch);
                    }else{
                        //当前的运算符优先级大于栈中运算符的优先级
                        //当前符号直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //表示栈为空，直接入栈
                    operStack.push(ch);
                }
            }else {
                //如果当前字符是数，则直接入数栈
                //numStack.push(ch - 48);//传入的是字符，ASCII码值-48为数值
                //上述操作处理多位数有bug
                //处理方法：向expression表达式的index 后再看一位，如果是数字就继续扫描，如果是符号才入栈，定义一个字符串进行拼接
                    keepNum += ch;
                //如果ch已经是expression最后一位，就直接入栈
                    if (index == expression.length()-1) {
                        numStack.push(Integer.parseInt(keepNum));
                }else {
                //判断下一个字符是不是数字，若是数字继续扫描，若是符号就入数栈
                //向后看一位，不是index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    //进入if代表是运算符
                        numStack.push(Integer.parseInt(keepNum));//将字符串转为数字
                    //-----------------重要----------------
                    //将字符串清空
                    keepNum = "";
                    }
                }
            }
            //索引值index加1，判断是否到表达式最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕后，顺序的从数栈和符号栈pop出相应的数和符号，并计算
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字即结果
            if (operStack.isEmpty())
                break;
            else{
                //数栈pop出两个数
                num1 = numStack.pop();
                num2 = numStack.pop();
                //符号栈pop一个符号
                oper = operStack.pop();
                res = numStack.cal(num1,num2,oper);//调用之前定义的计算方法计算
                numStack.push(res);
            }
        }
        //将数栈最后的数字pop出就是结果
        int result = numStack.pop();
        System.out.printf("表达式 %s =  %d",expression,result);
    }
}

//创建一个栈，用之前的数组模拟的栈，并扩展
class ArrayStack1 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据放在数组中
    private int top = -1;//top表示栈底，初始化-1表示没有元素

    public ArrayStack1(int maxSize) {
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

    //返回运算符的优先级，优先级用数字表示，数字越大，则优先级越高
    public int priority(int oper){
        if (oper == '*' || oper == '/')
            return 1;
        else if (oper == '+' || oper == '-'){
            return 0;
        }else return -1;
    }

    //判断是否是一个运算符
    public boolean isOper(char value){
        return value == '+' || value == '-' || value == '*' || value == '/' ;
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放返回结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//后pop出来的数 减去- 前pop出来的数
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//后pop出来的数 / 前pop出来的数
                break;
            default:
                break;
        }
        return res;
    }

    //返回当前栈顶的值方法
    public int peek(){
        return stack[top];
    }

}