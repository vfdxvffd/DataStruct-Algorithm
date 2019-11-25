public class demo1 {
    /**
     * 对斐波那契数列求法的优化：如果单纯使用递归，那重复计算的次数就太多了，为此，我们对其做一些优化
     * 假设最多计算到第100个斐波那契数
     * 用arr这个数组来保存已经计算过的Fibonacci数，以确保不会重复计算某些数
     */
    private static int arr[] = new int[100];
    public static int Fibonacci(int n){
        if(n <= 2){
            return 1;
        }else{
            if(arr[n] != 0)
                return arr[n];
            else{
                arr[n] = Fibonacci(n-1)+Fibonacci(n-2);
                return arr[n];
            }

        }
    }
    //甚至可以使用递推来求解Fibonacci数列
    public static int fibonacci(int n){
        if(n <= 2) return 1;
        int f1 = 1, f2 = 1, sum = 0;
        for(int i = 3; i <= n; i++){
            sum = f1+f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci(10));
    }
}
