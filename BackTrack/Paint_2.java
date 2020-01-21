package BackTrack;

import java.util.Scanner;

public class Paint {

	static int[][] p_Maxtrix = new int[4][4];		//图的邻接矩阵
	static int Colornum = 3;						//颜色数目
	static int[] result = {-1,-1,-1,-1};			//保存结果
	
	/**
	 * @param index         当前顶点的下标
	 * @param color			颜色的编号
	 * @return              染色方案是否可行
	 */
	public static boolean IsOk(int index, int color) {		//判断是否可以染色
		for (int i = 0; i < p_Maxtrix.length; i++) {
			if(p_Maxtrix[index][i] == 1 && result[i] == color) {
				return false;
			}
		}
		return true;
	}
	
	public static void backtrack(int index) {
		if(index == p_Maxtrix.length) {			//完成最后一个顶点的着色，输出其中一种结果
			for (int i = 0; i < result.length; i++) {
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
		}
		for (int i = 0; i < Colornum; i++) {	//对每一种颜色进行尝试
			result[index] = i;
			if(IsOk(index, i)) {
				backtrack(index+1);
			}
			result[index] = -1;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        for (int i = 0; i <p_Maxtrix.length ; i++) {
            for (int j = 0; j < p_Maxtrix.length; j++) {
                p_Maxtrix[i][j] = in.nextInt();
            }
        }
		backtrack(0);
	}

}

