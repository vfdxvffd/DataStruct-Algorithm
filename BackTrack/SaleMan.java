public class SaleMan {
    //货郎问题回溯解法
    static int[][] map = {
            { 0,10,5,9},
            {10,0,6,9},
            { 5,6,0,3},
            { 9,9,3,0}
    };          //邻接矩阵
    static int Min = 10000;            //最短的长度
    static int[] city = {1,0,0,0};      //第一个默认已经走过了
    final static int N = 4; //城市数量

    public static void travel(int[] city,int j,int len,int level) {
        if(level == N-1){     //最后一层
            Min = Math.min(len+map[j][0], Min);
            return;
        }
        for (int i = 0; i < N; i++) {
            int[] newcity = city.clone();
            if(map[j][i] != 0 && newcity[i] == 0){
                newcity[i] = level+2;
                travel(newcity,i,len+map[j][i],level+1);
            }
        }
    }

    public static void main(String[] args) {
        travel(city,0,0,0);
        System.out.println(Min);
    }
}
