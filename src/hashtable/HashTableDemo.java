package hashtable;/*
    @author shl
    @create 2020-04-14-19:16
*/

import java.util.Scanner;

//使用hash表管理员工简易demo
//使用数组+链表的形式构建一个hash表
//这个例子使用分离链表法已经解决了hash冲突的问题
public class HashTableDemo {

    public static void main(String[] args) {
        //创建hash表
        HashTab hashTab = new HashTab(7);

        //菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add : 添加员工");
            System.out.println("list : 显示员工");
            System.out.println("find : 查找员工");
            System.out.println("delete : 删除员工");
            System.out.println("exit : 退出系统");

            key = scanner.next();
            switch (key) {
                case "add" :
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTab.add(employee);
                    break;
                case "list" :
                    hashTab.list();
                    break;
                case "find" :
                    System.out.println("输入要查找的员工id");
                    id = scanner.nextInt();
                    hashTab.findEmployeeById(id);
                case "delete" :
                    System.out.println("输入要删除的员工的id");
                    id = scanner.nextInt();
                    hashTab.deleteEmployeeById(id);
                    break;
                case "exit" :
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

//创建hashtab类，管理多条链表
class HashTab {
    private EmployeeLinkedList[] employeeLinkedListArray; //存放多条数组的链表
    private int size;

    public HashTab(EmployeeLinkedList[] employeeLinkedListArray) {
        this.employeeLinkedListArray = employeeLinkedListArray;
    }
    public  HashTab(int size) {
        this.size = size;//表示链表的条数
        //初始化链表
        employeeLinkedListArray = new EmployeeLinkedList[size];
        //如果没有下面的代码就相当于，还没有添加元素只是创建了链表
        //分别初始化每个链表 循环实现
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
    }

    //添加雇员
    public void add(Employee employee) {
        //根据雇员id，得到该员工应该加入到哪个链表中
        int employeelinkedListNo = hashFun(employee.id);
        //将employee加入到对应的链表中
        employeeLinkedListArray[employeelinkedListNo].add(employee);
    }

    //遍历hashtab
    //遍历所有雇员的信息
    public void list() {
        for (int i = 0; i < size ; i++) {
            employeeLinkedListArray[i].list(i);
        }
    }

    //根据输入id查找雇员
    public void findEmployeeById(int id) {
        //使用散列函数，确定到哪条链表
        int empLinkedListNo = hashFun(id);
        Employee employee = employeeLinkedListArray[empLinkedListNo].findEmployeeById(id);
        if (employee !=  null) { //找到了
            System.out.printf("在第%d条链表中找到雇员，id = %d",empLinkedListNo+1,id);
            //return employee;
        } else {
            System.out.println("在hash表中没有找到该员工");
        }

    }

    //根据输入id删除某个雇员
    public void deleteEmployeeById(int id) {
        //使用散列函数，确定到哪条链表
        int empLinkedListNo = hashFun(id);
        employeeLinkedListArray[empLinkedListNo].deleteEmployeeById(id);
        //System.out.printf("在hash表中已经删除编号为%d员工",id);

    }

    //散列函数（取模建立）
    //根据员工id，返回所在链表的编号
    public int hashFun(int id) {
        return id % size;
    }
}

//雇员类
//为了简单测试，只定义了两个变量，后续需要再进行封装和补充
class Employee {
    public int id;
    public String name;
    public Employee next;// 指向下一个节点的指针，默认为null

    public Employee() {
    }

    public Employee(int id, String name, Employee next) {
        this.id = id;
        this.name = name;
        this.next = next;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmployeeLinkedList
//表示一条链表
class EmployeeLinkedList {

    public EmployeeLinkedList() {
    }

    //头指针,指向第一个employee。这个链表无头结点，head直接指向第一个employee
    private Employee head ;

    //添加雇员到链表的方法
    //说明：
    //添加雇员id自增长，从小到大
    //默认将雇员直接加到本链表的最后一个即可
    public void add(Employee employee) {
        //如果添加第一个雇员
        if (head == null) {
            head = employee;
            return;
        }
        //如果不是第一个，使用辅助指针帮助定位到最后
        //currentEmployee是辅助指针
        Employee currentEmployee = head;
        while (true) {
           if (currentEmployee.next == null) {
               //说明已经遍历到链表的最后
               break;
           }
           currentEmployee = currentEmployee.next; // 后移操作
        }
        //此时退出已经是最后一个
        //退出时直接将employee加到链表最后即可
        currentEmployee.next = employee;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            //说明当前链表为空
            System.out.println("第"+(no + 1)+"链表为空，无法遍历");
            return;
        }
        //说明链表信息不为空
        System.out.println("第"+(no + 1)+"链表信息不为空");
        System.out.print("链表信息为：");
        //使用辅助指针currentEmployee
        Employee currentEmployee = head;
        while (true) {
            //遍历
            //进入while循环说明至少存在一个节点
            System.out.printf(" => id = %d ; name = %s\t",currentEmployee.id,currentEmployee.name);
            if (currentEmployee.next == null) {
                //说明已经是最后节点
                break;
            }
            currentEmployee = currentEmployee.next;//后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查到就返回employee，如果没有找到就返回null
    public Employee findEmployeeById(int id) {
        //判断链表是否为空
        if (head == null) {
            return null;
        }
        //不为空，使用一个辅助指针
        Employee currentEmployee = head;
        while (true) {
            if (currentEmployee.id == id) {
                //说明找到
                //返回并退出
                break;
                //currentEmployee就指向要查找的雇员
            }
            if (currentEmployee.next == null){
                //说明遍历当前链表没有找到该雇员
                currentEmployee = null;
                break;
            }
            currentEmployee = currentEmployee.next;
        }
        return currentEmployee;
    }

    //尾删法这里有点问题，还没有解决
    //添加
    //根据id删除雇员
    public void deleteEmployeeById(int id) {
        //判断链表是否为空
        if (head == null) {
            return;
        }
        //不为空，使用一个辅助指针
        Employee currentEmployee = head;
        boolean flag = false;//标志是否找到待删除节点
        while (true) {
            if (currentEmployee.next == null){
               // currentEmployee = null;
                break;
            }
            if (currentEmployee.next.id == id) {
                //说明找到要删除的节点的前一个节点，判断id是否相同
                flag = true;//标志位置true
                break;
            }
            currentEmployee = currentEmployee.next;
        }
        if (flag) {
            //进行删除
            currentEmployee.next = currentEmployee.next.next;
        } else {
            System.out.printf("id为%d的员工不存在\n",id);
        }

    }
}