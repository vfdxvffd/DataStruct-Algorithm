import java.util.Stack;

public class lcs {
    /**
     * 求最长公共子序列问题
     * Talk is cheap, show me the code!
     * 参考公式(也是最难的一步)：
     *           { 0                             i = 0, j = 0
     * c[i][j] = { c[i-1][j-1]+1                 i,j>0, x[i] == y[i]
     *           { max{c[i-1][j],c[i][j-1]}      i,j>0, x[i] != y[i]
     * 参考书目：算法设计与分析（第四版）清华大学出版社    王晓东 编著
     * 参考博客：https://www.cnblogs.com/hapjin/p/5572483.html
     * 比如A = "LetMeDownSlowly!"   B="LetYouDownQuickly!"   A和B的最长公共子序列就是"LetDownly!"
     * @param x
     * @param y
     * @param b 做记录，明确这一步是通过什么得到的
     * @Param c 用c[i,j]表示：(x1,x2....xi) 和 (y1,y2...yj) 的**最长**公共子序列的长度
     * @return  最长公共子序列的长度
     */

    //maybe private method
    private static int lcsLength(String x, String y, int[][] b,int[][] c){
        int m = x.length();
        int n = y.length();
        //下面是初始化操作，其实也没必要，因为Java中默认初始化为0，其他语言随机应变
        for (int i = 0; i <= m; i++) c[i][0] = 0;
        for (int i = 0; i <= n; i++) c[0][i] = 0;

        //用一个序列的每个元素去和另一个序列分别比较
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(x.charAt(i-1) == y.charAt(j-1)){     //如果遇到相等的，就给序列的上一行上一列的加1
                    c[i][j] = c[i-1][j-1]+1;
                    b[i][j] = 1;
                }else if(c[i-1][j] >= c[i][j-1]){       //取上一次最大的，保证最长子序列的最长要求
                    c[i][j] = c[i-1][j];
                    b[i][j] = 2;
                }else{
                    c[i][j] = c[i][j-1];
                    b[i][j] = 3;
                }
            }
        }
        return c[m][n];
    }

    public static void Print(int i, int j, String x, int[][] b){
        if(i == 0 && j == 0) return;
        if(b[i][j] == 1){ //如果为1，说明这个字符是在另一个序列中有相等的字符，但因为是倒序遍历b数组，所以先递归最后输出
            Print(i-1,j-1,x,b);
            System.out.print(x.charAt(i-1));
        }else if(b[i][j] == 2) Print(i-1,j,x,b);//等于2，则说明c[i-1][j] >= c[i][j-1],则向上一行递归，因为最长子序列在c数组上一行的元素或上一行元素之前
        else Print(i,j-1,x,b);
    }

    public static void Lcs(int i, int j, String x, int [][]c){
        Stack s = new Stack<Character>();
       while(i != 0 && j != 0){
           if(c[i][j] == c[i-1][j-1] + 1){  //相等则说明c[i][j]是c[i-1][j-1]+1来的，就相当于b[i][j]=1的情况
               s.push(x.charAt(i-1));   //将其入栈
               i--;
               j--;
           }else if(c[i-1][j] >= c[i][j-1]){
               i--;
           }else{
               j--;
           }
       }
       while (!s.isEmpty()){
           System.out.print(s.pop());
       }
    }

    public static void main(String[] args) {
        String str1 = "LetMeDownSlowly!";
        String str2 = "LetYouDownQuickly!";
        int [][]b = new int[str1.length()+1][str2.length()+1];
        int [][]c = new int[str1.length()+1][str2.length()+1];
        int result = lcsLength(str1,str2,b,c);
        for (int i = 0; i < str1.length()+1; i++) {
            for (int j = 0; j < str2.length()+1; j++) {
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
        //Print(str1.length(),str2.length(),str1,b);
        Lcs(str1.length(),str2.length(),str1,c);
        System.out.println();
        System.out.println(result);
    }
}
