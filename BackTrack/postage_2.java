public class postage_2 {

    final public static int MAX_Postage = 300;      //最大邮资应不超过这个值
    final public static int N = 6;                  //numbers,用3张邮票
    final public static int M = 5;                  //kinds,一共4种面值
    public static int[] best = new int[M+1];          //best,存放最优解
    public static int[] x = new int[M+1];             //当前解
    public static int Max = 0;                      //最大邮资
    public static int[][] dp = new int[M+1][MAX_Postage];     //用于动态规划保存第x[0]到x[cur]的最大邮资

    public static int findMax(int cur){         //向下dp和向右dp
        if(cur == 1)
            return N;
        //向下
        int j = 1;
        while(dp[cur-1][j] != 0){
            dp[cur][j] = dp[cur-1][j];
            for (int k = 1; k*x[cur] <= j; k++) {
                int temp = dp[cur-1][j-k*x[cur]]+k;
                dp[cur][j] = Math.min(temp,dp[cur][j]);
            }
            j++;
        }

        //向右
        while(true){
            int temp = MAX_Postage;
            for (int i = 1; i<=cur; i++) {
             /**
             * 这里很妙，因为向右dp时每次都是向右一个一个推进，所以我们从x[]的第一种面值往上加，直到超过限制张数，那么如果x[]的
             * 第二种面值刚好能将前面的多个第一张替换，那就替换更新张数
             * 反正意思就是每一次for循环是对前面的较小面值的邮票是一个累加的过程
             */
                temp = Math.min(dp[cur][j-x[i]]+1,temp);
            }
            if(temp > N || temp == MAX_Postage){   //超过限制张数或没进行上述循环或上述循环没能更新它的值
                break;
            }else{
                dp[cur][j] = temp;
            }
            j++;
        }
        /**对下面这条语句做一个解释
         * 确保同一层上一次dp的结果不会影响下一次尝试时的dp，因为可能上一次的一个分支中dp时已经给dp[2][10]赋过值了，但如果没有这一句
         * 就会导致后面的某一个分支中dp的时候，向下dp的时候直接将dp[2][10]向下dp了，而事实上，应该向右dp的时候才给dp[2][10]赋值的
         * 其实就是向回溯的下一层发一个信号，表示这块是我上一层dp停止的地方，过了这块可能就是别的回溯分支给dp赋的值了
         */
        dp[cur][j] = 0;
        return j-1;
    }

    public static void backtrack(int t){        //t表示当前层数,max是上一层的最大邮资
        if(t == M){                             //已经选够最多的面值种类
            /* do something */
            int max = findMax(t);
            if(max > Max){                      //如果大于之前的最优解
                Max = max;
                for (int i = 0; i < M+1; i++) {
                    best[i] = x[i];
                }
                return;
            }
        }else{
            int temp = findMax(t);               //得到当前层的最大邮资
            for (int i = x[t]+1; i <= temp+1; i++) {//下一层只能在上一层所选的邮资+1到上一层所能拼出最大邮资+1中
                x[t+1] = i;                               //第t个面值采用i
                backtrack(t+1);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= N; i++) {
            dp[1][i] = i;
        }
        x[0] = 0;
        x[1] = 1;                           //邮资第一种面值只能为1
        backtrack(1);
        System.out.println(Max);
        for (int i = 1; i < M+1; i++) {
            System.out.print(best[i]+" ");
        }
    }
}
