public class Package {
    /**
     * 此函数用于计算背包中能存放的最大values
     * @param m     m[i][j]用于记录1,2,...,i个物品在背包容量为j时候的最大value
     * @param w     w数组存放了每个物品的重量weight,w[i]表示第i+1个物品的weight
     * @param v     v数组存放了每个物品的价值value,v[i]表示第i+1个物品的value
     * @param C     C表示背包最大容量
     * @param sum   sum表示物品个数
     * 状态转移方程: m[i][j] = max{ m[i-1][j-w[i]]+v[i] , m[i-1][j]}
     *            m[i-1][j]很好理解，就是不将第i个物品放入背包的最大value
     *            m[i-1][j-w[i]]+v[i]表示将第i个物品放入背包，m[i-1][j-w[i]]表示在背包中先给第i个物品把地方腾出来
     *            然后背包可用空间就是j-w[i],在这些可用空间里1,2,...,i-1个物品放的最大value就是m[i-1][j-w[i]],那
     *            最后再加上第i个物品的value，就是将第i个物品放入背包的最大value了
     */
    public static void knap(int[][] m, int[] w,int[] v, int C, int sum){
        for(int j = 0; j < C; j++){     //初始化   stuttering
            if(j+1 >= w[0]){        //第一行只有一个物品，如果物品比背包容量大就放进去，否则最大value只能为0
                m[0][j] = v[0];
            }else{
                m[0][j] = 0;
            }
        }
        for(int i = 1; i < sum; i++){
            for(int j = 0; j < C; j++){
                int a = 0, b = 0;       //a表示将第i个物品放入背包的value，b表示不放第i个物品
                if(j >= w[i])
                    a = m[i-1][j-w[i]]+v[i];
                b = m[i-1][j];
                m[i][j] = (a>b?a:b);
            }
        }
    }

    /**
     * 此函数用于输出组成背包最大values的物品编号
     * @param m     m[i][j]用于记录1,2,...,i个物品在背包容量为j时候的最大value
     * @param w     w数组存放了每个物品的重量weight,w[i]表示第i+1个物品的weight
     * @param v     v数组存放了每个物品的价值value,v[i]表示第i+1个物品的value
     * @param C     C表示背包最大容量
     * @param sum   sum表示物品个数
     */
    public static void trace(int[][] m, int[] w,int[] v, int C, int sum){
        if(sum == 1) {          //递归结束条件，就剩最后一个物品，如果在背包里就输出然后return，否则直接return
            if(m[0][C-1] > 0)
                System.out.print(sum+" ");
            return;
        }
        if(m[sum-1][C-1] == m[sum-2][C-1-w[sum-1]]+v[sum-1]){   //判断方法参考上个函数中给m数组赋值的方法
            //此物品在背包中，去掉这个物品，即将背包容量中减去这个物品的weight，sums-1表示继续从上个物品开始找
            trace(m,w,v,C-w[sum-1],sum-1);
            System.out.print(sum+" ");  //根据递归出栈顺序，所以先递归再输出，因为是从最后一个物品开始判断的
        }else{      //此物品不在背包中，则递归从上一个物品继续找
            trace(m,w,v,C,sum-1);
        }
    }

    public static void main(String[] args) {
        int w[] = new int[5];
        w[0] = 2; w[1] = 2; w[2] = 3; w[3] = 5; w[4] = 5;
        int v[] = new int[5];
        v[0] = 6; v[1] = 3; v[2] = 5; v[3] = 4; v[4] = 6;
        int m[][] = new int[5][6];
        knap(m,w,v,6,5);
        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }*/
        trace(m,w,v,6,5);
    }
}
