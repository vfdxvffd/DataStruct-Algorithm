public class Postage {
    final public static int MAX_Postage = 300;      //最大邮资应不超过这个值
    final public static int N = 6;                  //numbers,用3张邮票
    final public static int M = 5;                  //kinds,一共4种面值
    public static int[] best = new int[M];          //best,存放最优解
    public static int[] x = new int[M];             //当前解
    public static int Max = 0;                      //最大邮资
    public static int[][] dp = new int[M][MAX_Postage];     //用于动态规划保存第x[0]到x[cur]的最大邮资

    public static int getMax(int cur){      //cur表示当前用到第1到第cur种面值的邮票,cur其实就是x的下标
        //其实就是每一次向下dp，但是每次都要执行重复的dp过程，很影响效率，也违背了dp的初衷
        for (int i = 1; i <= cur; i++) {
            for (int j = 0; j < MAX_Postage; j++) {
                dp[i][j] = dp[i-1][j];
                for (int k = 1; (k*x[i]) <= j ; k++) {
                    int temp = dp[i - 1][j - (k * x[i])] + k;
                    dp[i][j] = Math.min(temp,dp[i][j]);
                }
                /*if(dp[i][j] > N)          //这里涉及一个非常巨大的时间浪费，需要处理！
                    break;*/
            }
        }
        for (int i = 0; i < MAX_Postage; i++) {
            if(dp[cur][i] > N){
                return i-1;
            }
        }
        return -1;          //Error,所给dp数组不够大
    }

    public static void backtrack(int t){        //t表示当前层数,max是上一层的最大邮资
        if(t == M){                             //已经选够最多的面值种类
            /* do something */
            int max = getMax(t-1);
            if(max > Max){                      //如果大于之前的最优解
                Max = max;
                for (int i = 0; i < M; i++) {
                    best[i] = x[i];
                }
                return;
            }
        }else{
            int temp = getMax(t-1);               //得到当前层的最大邮资
            for (int i = x[t-1]+1; i <= temp+1; i++) {//下一层只能在上一层所选的邮资+1到上一层所能拼出最大邮资+1中
                x[t] = i;                               //第t个面值采用i
                backtrack(t+1);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < MAX_Postage; i++) {     //dp的第一行为边界
            dp[0][i] = i;
        }
        x[0] = 1;                           //邮资第一种面值只能为1
        backtrack(1);
        System.out.println(Max);
        for (int i = 0; i < M; i++) {
            System.out.print(best[i]+" ");
        }
    }
}