package recursion;/*
    @author shl
    @create 2020-03-28-17:58
*/

public class MiGong {
    public static void main(String[] args) {

        //先创建一个二维数组，模拟这个迷宫
        //地图
        int[][] map = new int[8][7];//固定
        //约定：使用1表示墙不能移动
        //先把上下置为1
        for (int i = 0; i < 7; i++) { //行在变化
            map[0][i] = 1;
            map[7][i] = 1;

        }
        //再把左右置1
        for (int j = 0; j < 8; j++) { //列在变化
            map[j][0] = 1;
            map[j][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        //setWay(map,1,1);
        setWay1(map,1,1);

        //输出新的地图，小球标识过的通路
        System.out.println("小球走过标识的地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归回溯给小球找路

    //map:表示地图
    //i:表示起始的位置 行
    //j:表示起始的位置 列
    //若找到通路 返回true否则返回false
    public static boolean setWay(int[][] map, int i, int j) {
        //约定从（1,1）开始出发，若能到（6,5）说明找到

        //当map[i][j] = 0表示没有走过，1代表墙，2表示通路可以走，3表示该位置已走但是走不通

        //默认走迷宫的方法：下->右->上->左，如果走不通则回溯

        if (map[6][5] == 2) {
            //通路已经完成
            return true;

        } else {
            if (map[i][j] == 0) {
                //如果当前这个点没有走过
                //按照默认方法进行
                map[i][j] = 2;//假定这个点能走通
                if (setWay(map, i + 1, j)) {
                    //标志为2后向下走
                    return true;

                } else if (setWay(map, i, j + 1)) {
                    //不通，则向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    //不通，则向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    //不通，则向左走
                    return true;
                } else {
                    //进入这个判断说明这个路是死路
                    //置3
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j]不等于0的情况：1,2,3
                //直接返回false
                return false;

            }
        }
    }

    //修改找路的策略：上 右 下 左
    public static boolean setWay1(int[][] map, int i, int j) {
        //约定从（1,1）开始出发，若能到（6,5）说明找到

        //当map[i][j] = 0表示没有走过，1代表墙，2表示通路可以走，3表示该位置已走但是走不通

        //默认走迷宫的方法：下->右->上->左，如果走不通则回溯

        if (map[6][5] == 2) {
            //通路已经完成
            return true;

        } else {
            if (map[i][j] == 0) {
                //如果当前这个点没有走过
                //按照默认方法进行
                map[i][j] = 2;//假定这个点能走通
                if (setWay1(map, i - 1, j)) {
                    //标志为2后向上走
                    return true;

                } else if (setWay1(map, i, j + 1)) {
                    //不通，则向右走
                    return true;
                } else if (setWay1(map, i + 1, j)) {
                    //不通，则向下走
                    return true;
                } else if (setWay1(map, i, j - 1)) {
                    //不通，则向左走
                    return true;
                } else {
                    //进入这个判断说明这个路是死路
                    //置3
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j]不等于0的情况：1,2,3
                //直接返回false
                return false;

            }
        }
    }
  }
// 继续实现最短路径算法===》？