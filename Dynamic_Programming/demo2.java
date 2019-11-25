import java.util.Arrays;

public class demo2 {

    /**
     * 此题是求解路径个数，让你从(1,1)走到某个特定的位置，求一共有多少种走法
     * @param i
     * @param j
     * @return
     */
    public static int Count_Path(int i, int j){
        int result[][] = new int[i][j];
        for (int k = 0; k < i; k++) {           //将二维数组初始化为1
            Arrays.fill(result[k],1);
        }
       for (int k = 1; k < i; k++) {
            for (int l = 1; l < j; l++){
                    result[k][l] = result[k-1][l]+result[k][l-1];
            }
        }
        return result[i-1][j-1];
    }

    public static void main(String[] args) {
        System.out.println(Count_Path(7,3));
    }
}
