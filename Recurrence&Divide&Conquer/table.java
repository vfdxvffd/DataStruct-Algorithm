public class table {
    static final int N = 50;
    /**
     * 将比赛日程表设计成n行n列，表中除了第一列，其他n-1列才是我们要的，数组下标行列都从0开始，第i行j列代表第（i+1）位选手在第j天的对手：
     * 表格初始化会将第一行按1到n一次填充，然后递归填充下面的，用左上角和右上角分别去填充右下角和左下角，因为要是对称矩阵（具体原因好好想想）
     * @param p     表示行序号
     * @param q     表示列序号
     * @param t     表示当前传进函数方格的规模也就是大小
     * @param arr   表格
     */
    public static void arrange(int p, int q, int t, int arr[][]){
        if(t>=4){           //如果规模大于4，就继续递归
            arrange(p,q,t/2,arr);
            arrange(p,q+t/2,t/2,arr);
        }

        //填左下角
        for(int i=p+t/2;i<p+t;i++){
            for(int j=q;j<q+t/2;j++){
                arr[i][j]=arr[i-t/2][j+t/2];
            }
        }
        //填右下角
        for(int i=p+t/2;i<p+t;i++){
            for(int j=q+t/2;j<q+t;j++){
                arr[i][j]=arr[i-t/2][j-t/2];
            }
        }
    }

    public static void main(String[] args) {
        int n = 8;
        int game[][] = new int[N][N];

        //初始化第一行,其他全为0
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==0)
                    game[i][j]=j+1;
                else
                    game[i][j]=0;
            }
        }

        //递归
        arrange(0,0,n,game);

        //打印输出循环赛日程表
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(game[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
