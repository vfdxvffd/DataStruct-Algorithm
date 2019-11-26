import java.util.Scanner;

public class Paint {
    static int[][] p_Maxtrix = new int[4][4];       //5个顶点的图
    static int Colornum = 3;                        //颜色数目
    //static int[] result = new int[p_Maxtrix.length];//保存结果

    public static boolean IsOk(int index, int color,int[] result){   //判断是否可以染色
        for (int i = 0; i < p_Maxtrix.length; i++) {
            if(p_Maxtrix[index][i] == 1 && result[i] == color){
                return false;
            }
        }
        return true;
    }

    public static void backtrack(int index, int[] result){     //index表示当前顶点的下标
        if(index == p_Maxtrix.length){
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < Colornum; i++) {
            int news[] = new int[result.length];
            for (int j = 0; j < result.length; j++) {
                news[j] = result[j];
            }
            if(IsOk(index,i,news)){
                news[index] = i;
                backtrack(index+1,news);
            }
        }
    }

    public static void main(String[] args) {
        int[] result = new int[p_Maxtrix.length];//保存结果
        Scanner in = new Scanner(System.in);
        for (int i = 0; i <p_Maxtrix.length ; i++) {
            for (int j = 0; j < p_Maxtrix.length; j++) {
                p_Maxtrix[i][j] = in.nextInt();
            }
            result[i] = -1;
        }
        backtrack(0,result);
    }
}
