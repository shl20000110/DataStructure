package stack;/*
    @author shl
    @create 2020-03-27-17:49
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
          //非完整版

        //将中缀表达式转成后缀表达式的功能
        //1.因为直接对字符串操作不方便，将中缀表达式转为对应的list
        //2.将中缀表达式对应list转为后缀表达式对应的list（8步）


        String expression = "1+((2+3)*4)-5";
        List<String> toInfixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list："+toInfixExpressionList);
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(toInfixExpressionList);
        System.out.println("后缀表达式对应的list："+parseSuffixExpressionList);
        int result = calculate(parseSuffixExpressionList);
        System.out.println("表达式的值是："+ result);


        //定义一个逆波兰表达式
        //为了方便，数字和符号之间使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        //1.先将suffixExpression放到一个ArrayList中
        //不用index一个一个进行扫描了！！

        //2.将ArrayList传递给一个方法，扫描表达式就是遍历ArrayList，配合栈（Stack）完成计算
        /*
        List<String> rpnlist = getListString(suffixExpression);//获得一个逆波兰表达式的列表

        System.out.println("逆波兰表达式为：" + rpnlist);

        int res = calculate(rpnlist);

        System.out.println("计算结果为："+res);*/


    }

    //中缀表达式对应list==>>后缀表达式对应list
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //Stack<String> s2 = new Stack<String>();
        //本来是要建一个存放中间结果的栈s2，但是s2在转换过程中没有pop操作，且最后还要逆序
        //转换成list<string> Arraylist更加方便
        List<String> s2 = new ArrayList<String>();

        //遍历ls
        for (String item:
             ls) {
            //如果是一个数，就入s2
            //使用正则表达式判断
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                //如果是右括号，依次弹出s1栈顶运算符，并压入s2，直到是s1栈顶是左括号为止
                while (!s1.peek().equals("(")){
                    //如果没有看见栈顶的（，就依次向外弹出
                    s2.add(s1.pop());
                }
                s1.pop();//消除了一对小括号，原先括号弹出一个栈
            } else {
               //当item的优先级小于等于栈顶运算符，将s1栈顶运算符弹出并压入s2中，再转入到4.1步，与s1中新的栈顶运算符比较
               //需要一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //还需要item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余运算符依次弹出加入s2
        while (s1.size() != 0 ){
              s2.add(s1.pop());
        }
      return s2;//用list的好处就是不需要逆序输出了，否则用Stack栈还要逆序输出才是后缀表达式
    }



    //将中缀表达式转成对应的list方法
    public static List<String> toInfixExpressionList(String s){
       //定义一个list，存放对应中缀表达式的内容
       List<String> ls = new ArrayList<String>();
       //定义一个指针，用于遍历中缀表达式字符串
        int index = 0;
        String str;//用于多位数的拼接
        char c;//每遍历一个字符，就放入到c
        do {
            //如果c非数字，就需要加入到ls中
            if ((c = s.charAt(index)) < 48  ||(c = s.charAt(index)) > 57) {  //anscll码
                ls.add("" + c);
                index++;//index后移
            } else {
               // 如果是一个数字
               // 就要考虑多位数的问题
                str = "";//str置空串                         0[48]---9[57]
                while (index < s.length() &&(c = s.charAt(index)) >= 48 && (c = s.charAt(index)) <= 57){
                    str += c;//拼接
                    index++;
                }
                ls.add(str);
            }
        }while (index < s.length());
      return ls;
    }

    //将逆波兰表达式依次将数字和运算符放入ArrayList中
    public static List<String> getListString (String suffixExpression){
        //将suffixExpression分割
        //按照空格分割，返回一个数组
        String[] split = suffixExpression.split(" ");      //以空格为分割
        List<String> list = new ArrayList<String>();
        //对split数组进行增强for循环
        for (String ele: split) {
            //每次循环都取出一个元素,加入到list中
            list.add(ele);
        }
        return list;
    }

    //逆波兰表达式运算方法
    //从左向右扫描，数字就入栈，符号就弹出两个数进行运算，然后结果返回栈，直到遍历到最右端
    public static int calculate(List<String> ls){
        //创建一个栈，只要一个栈就行
        //使用java自带的Stack类
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls ) {
            //使用正则表达式取出数
            if (item.matches("\\d+")){ //代表匹配的是多位数
               //入栈
               stack.push(item);
            }else{
                //如果不是数，是运算符，就pop出两个数,再入栈
                int num2 = Integer.parseInt(stack.pop());//pop出来的第一个数
                int num1 = Integer.parseInt(stack.pop());//pop出来的第二个数
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                } else if (item.equals("-")){
                    res = num1 - num2; //后pop出的数 - 先pop的数 这里写的有点绕
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")){
                    res = num1 / num2;//后pop出的数 / 先pop的数  和取出来的数据顺序有关
                }  else{
                   throw new RuntimeException("运算符有误");
                }
                //把结果入栈
                stack.push(res +"" );  //把整数转成字符串来入栈
            }
        }
        //最后留在栈中就是结果
        //字符串转成整数
        return Integer.parseInt( stack.pop() );
    }
}
     class Operation{
    //返回一个运算符对应优先级的类
         private static int ADD = 1;
         private static int SUB = 1;
         private static int MUL = 2;
         private static int DIV = 2;

         //方法：返回对应优先级数字
         public  static int getValue(String operation){
             int result = 0;
             switch (operation){
                 case "+":
                     result = ADD;
                     break;
                 case "-":
                     result = SUB;
                 case "*":
                     result = MUL;
                     break;
                 case "/":
                     result =DIV;
                     break;
                 default:
                     System.out.println("不存在该运算符");
                     break;
             }
             return result;
         }
     }