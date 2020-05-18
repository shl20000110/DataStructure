package common10algorithm;/*
    @author shl
    @create 2020-05-10-14:44
*/

//骑士周游算法（马走日字）
//马踏棋盘算法

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {

    private static int X; //X表示棋盘的列数
    private static int Y; //Y表示棋盘的行数

    //创建数组标记棋盘的各个位置是否被访问过
    private static boolean isVisited[];
    //使用一个属性，标记棋盘的所有位置都被访问过了
    private static boolean isFinished; //如果为true表示成功，否则就表示额没有成功


    public static void main(String[] args) {

        System.out.println("骑士周游算法~");

        //测试
        X = 8;
        Y = 8;
        //马走的初始位置的行，从1开始
        int row = 1;
        //马走的初始位置的列，从1开始
        int column = 1;
        //创建棋盘
        int [][]chessBoard = new int[X][Y];
        isVisited = new boolean[X * Y];//初始值为false

        //测试时间
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard,row - 1,column - 1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "ms");

        //输出棋盘的最后情况
        for (int []rows : chessBoard) {
            for (int steps : rows) {
                System.out.print(steps + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     * @param chessBoard 棋盘
     * @param row 马当前的位置的行从0开始
     * @param column 马当前的位置的列从0开始
     * @param step 表示第几步，初始位置从1开始
     */
    public static void traversalChessBoard(int[][] chessBoard,int row,int column,int step) {

        chessBoard[row][column] = step;
        //如果用二维数组访问 是不是会更简洁一点？

        //使用一维数组标记该位置已经被访问
        isVisited[row * X + column] = true;
        //获取当前位置可以走的下一位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序，对ps所有的point对象的下一步的数目进行非递减排序
        //使用贪心算法优化，可以大幅度缩短回溯次数与运行时间
        sort(ps);

        //遍历ArrayList中存放的所有位置，看谁能走通
        //如果走通，就继续，如果走不通，就回溯
        while (! ps.isEmpty() ) {
            //取出下一个可以走的位置
            Point point = ps.remove(0);
            //判断该点是否已经访问过
            if (!isVisited[point.y * X + point.x]) {
                //不为真，说明还没有访问过
                traversalChessBoard(chessBoard,point.y,point.x,step + 1);
            }
        }

        //判断马是否完成了任务，使用step和应该走的步数进行比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0

        //(step < X * Y 条件成立的两种情况：
        //1 棋盘到目前位置还是没有走完
        //2 棋盘处于回溯的过程中
        if (step < X * Y && ! isFinished) {
            chessBoard[row][column] = 0;
            //重新回溯，置为false
            isVisited[row * X + column] = false;
        } else {
            isFinished = true;
        }
    }

    /**
     * 根据当前位置（Point对象），计算马还能走哪些位置，并且放入到一个集合中（Arraylist），最多有8个位置
     * 0 1 2 3 4 5 6 7位置都需要判断
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建ArrayList
        ArrayList<Point> points = new ArrayList<>();

        //判断这个位置能不能走，能走就加到ArrayList中
        //总共进行8次判断

        //创建一个point
        Point point = new Point();

        //核心代码：从当前位置计算马能走的位置，最多有8个所以要进行八次判断
        //注意不能用if-else判断，全部用if方式进行判断

        //判断位置5能不能走
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            //相当于向左移动两列和向上移动一行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置6能不能走
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            //相当于向左移动一列和向上移动两行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置7能不能走
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            //相当于向右移动一列和向上移动两行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置0能不能走
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            //相当于向右移动两列和向上移动一行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置1能不能走
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            //相当于向右移动两列和向下移动一行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置2能不能走
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            //相当于向右移动一列和向下移动两行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置3能不能走
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            //相当于向左移动一列和向下移动两行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        //判断位置4能不能走
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            //相当于向左移动两列和向下移动一行可以移动 相当于走了一次日字
            points.add(new Point(point));
        }
        return points;
    }

    //根据当前这个一步的所有的下一步选择的位置，进行非递减排序
    public static void sort(ArrayList<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取o2的下一步的所有位置个数
                int count2 = next(o2).size();

                //目的是为了减少回溯的次数
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

}
