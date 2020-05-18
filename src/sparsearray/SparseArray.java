package sparsearray;/*
    @author shl
    @create 2020-03-20-20:14
*/

public class SparseArray {
    public static void main(String[] args){
        //创建原始二维数组
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出
        System.out.println("原始的二维数组是：");
        for (int[] row : chessArr1) {
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //二维数组转化为稀疏数组
        //1.遍历二维数组，得到非0数据个数
        int sum =0;
        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = "+sum);

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //3.将非0的值存放到数组中
        int count = 0;//用于记录第几个非0数据
        for (int i = 0; i < 11 ; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i <sparseArr.length ; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复成原始的二维数组
        //1.读取稀疏数组第一行，根据第一行的数据，创建原始的二维数组

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.再读稀疏数组后几行数据(从第二行开始)，并赋给二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();
        System.out.println("恢复后的二维数组是：");
        for (int[] row : chessArr1) {
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}

