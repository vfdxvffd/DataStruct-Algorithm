public class chessBoard {
    static int board[][] = new int[4][4];   //棋盘
    static int tag = 1;                     //骨牌编号
    /**
     * 分治算法典例2———棋盘覆盖问题
     * @date    2019/11/3   afternoon
     * @param tr    棋盘左上角方格的行号
     * @param tc    棋盘左上角方格的列号
     * @param dr    特殊方格所在的行号
     * @param dc    特殊方格所在的列号
     * @param size  棋盘宽度
     * @param s     当前棋盘宽度的一半
     * @param tr+s  当前棋盘中间行的行号
     * @param tc+s  当前棋盘中间列的列号
     */
    public static void chess(int tr, int tc, int dr, int dc, int size){
        if(size == 1)
            return;
        int newtag = tag++;
        int s = size / 2;     //分割棋盘

        //覆盖左上角子棋盘
        if(dr < tr+s && dc < tc+s){ //特殊方格在此棋盘中
            chess(tr,tc,dr,dc,s);
        }else{      //此棋盘中无特殊方格
            board[tr+s-1][tc+s-1] = newtag;
            chess(tr,tc,tr+s-1,tc+s-1,s);
        }

        //覆盖右上角子棋盘
        if(dr < tr+s && dc >= tc+s){
            chess(tr,tc+s,dr,dc,s);
        }else{
            board[tr+s-1][tc+s] = newtag;
            chess(tr,tc+s,tr+s-1,tc+s,s);
        }

        //覆盖左下角子棋盘
        if(dr >= tr+s && dc < tc+s){
            chess(tr+s,tc,dr,dc,s);
        }else{
            board[tr+s][tc+s-1] = newtag;
            chess(tr+s,tc,tr+s,tc+s-1,s);
        }

        //覆盖右下角子棋盘
        if(dr >= tr+s && dc >= tc+s){
            chess(tr+s,tc+s,dr,dc,s);
        }else{
            board[tr+s][tc+s] = newtag;
            chess(tr+s,tc+s,tr+s,tc+s,s);
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
        chess(0,0,2,3,4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
