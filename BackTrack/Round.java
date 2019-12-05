public class Round{

    final static int N = 3;
    static double min = 1000;              //the min length
    static double[] x = new double[N];        //center of every round
    static int[] r = {1,1,2};//new int[N];        //r of every round
    static int[] best = new int[N];     //the best answer

    // a swap function for exchange
    public static void swap(int[] a, int x, int y){
        int temp1 = a[x];
        int temp2 = a[y];
        a[x] = temp2;
        a[y] = temp1;
    }

    //a function to get the center of a circle

    /**
     *  对为什么要使用循环做一解释：
     *      因为可能存在第x个圆，它太过于小，而导致其对第x-1和x+1，甚至其他的圆来说，第x个圆存在于不存在都是没影响的
     *      取x-1，和x+1来说：可能x太小，x+1与x-1相切，所以计算第x+1圆心坐标的时候，只能以x-1的圆心与它的圆心来计算
     *      所以要每次循环比较选择最大的那一个做半径
     *      可以参考https://blog.csdn.net/qq_37373250/article/details/81477394中的图
     */
    public static double center(int t){
        double max = 0.0;                //default: the first round center of x is 0
        for (int i = 0; i < t; i++) {
            double temp = x[i]+2.0*Math.sqrt(r[i]*r[t]);//+Math.sqrt((Math.pow(r[i]+r[t],2))+Math.pow(r[i]-r[t],2));
            if(temp > max){
                max = temp;
            }
        }
        return max;
    }

    /**
     * 针对为什么不能直接temp = x[N-1]+x[0]+r[N-1]+r[0]（直接用第一个圆到最后一个圆的圆心距离加上两圆半径）做一解释：
     *      为避免第一个圆太小，而第二个圆太大，而导致第二个圆的边界甚至超过了第一个圆的边界，最右边同理
     *      那也可以依次推出可能第三个，第四个...的边界超过了第一个圆的边界，右边同理，所以需要每一个都做一下比较
     *      但是可以放心，x是按圆当前排列顺序放置圆心坐标的
     */
    public static void compute(){
        double  low = 0, high = 0;
        for (int i = 0; i < N; i++) {
            if(x[i]-r[i] < low){
                low = x[i]-r[i];
            }
            if(x[i]+r[i] > high){
                high = x[i]+r[i];
            }
        }
        double temp = high-low;
        if(temp < min){
            min = temp;
            for (int i = 0; i < N; i++) {
                best[i] = r[i];
            }
        }
    }

    public static void backtrack(int t){
        if(t == N){
            compute();
        }
        for (int i = t; i < N; i++) {           //before i, the r[0]-r[i], we have already sorted
            swap(r,t,i);
            double centerx = center(t);
            if(centerx+r[i] < min){
                x[t] = centerx;
                backtrack(t+1);
            }
            swap(r,t,i);                        //return before
        }
    }

    public static void main(String[] args) {
        backtrack(0);
        for (int i = 0; i < N; i++) {
            System.out.print(best[i]+" ");
        }
        System.out.println();
        System.out.println(min);
    }
}
