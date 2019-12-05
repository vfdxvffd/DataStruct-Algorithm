public class Empress {
    final static int N = 8;                   //皇后个数
    static int count = 0;                    //输出的结果个数
    static int[] postion = new int[N];      //保存每一行皇后拜访的位置

    /**
     * @param row   判断第row行摆放是否合理
     * @return      合理返回true，否则false
     */
    public static boolean IsOk(int row){
        for (int i = 0; i < row; i++) {     //和前面的每一行进行对比
            if(postion[i] == postion[row] || Math.abs(i-row) == Math.abs(postion[i] - postion[row])){
                //如果在同一列则postion[i] == postion[row]
                //如果在同一斜线上Math.abs(i-row) == Math.abs(postion[i] - postion[row])
                return false;
            }
        }
        return true;
    }

    public static void Print(){
        System.out.println("This is the No."+(++count)+" result:");
        for (int i = 0; i < N; i++) {           //i为行序号
            for (int j = 0; j < N; j++) {       //j为第i行皇后的列的序号
                if(postion[i] == j){    //不是皇后的拜访地址
                    System.out.print("# ");
                }else{
                    System.out.print("@ ");
                }
            }
            System.out.println();       //换行
        }
    }

    /**
     * @param row   尝试第row行皇后的摆放位置，找到可行位置就继续深度搜索下一行，否则在尝试完i的所有取值无果后回溯
     */
    public static void backtrack(int row){
        if(row == N){       //若已经等于N，则说明0~N-1已经赋值完毕，直接打印返回
            Print();
            return;
        }
        for (int i = 0; i < N; i++) {
            postion[row] = i;           //第row行皇后的位置在i处
            if(IsOk(row)){
                backtrack(row+1);
            }else{
                /**
                 * 如果第row行的皇后拜访在i(0-N)处可行，则继续向下深度搜索，否则就直接让这层递归函数出栈
                 * 此层函数出栈也就是当前结点不满足继续搜索的限制条件，即回溯到上一层，继续搜索上一层的下一个i值
                 */
            }
        }
    }

    public static void main(String[] args) {
        backtrack(0);
    }
}
