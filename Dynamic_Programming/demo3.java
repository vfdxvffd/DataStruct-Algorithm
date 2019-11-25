public class demo3 {
    /**
     * 这个问题是小青蛙跳台阶，也是递归中的典型例题了
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级，求该青蛙跳上一个n级的台阶共有多少种跳法
     * 对此问题我们给出两种解法：递归和Dynamic Programming
     */

     //递归
    public static int Jump_Floor1(int n){
        if(n <= 2){
            return n;
        }else{  //这里涉及到两种跳法，1、最后一次跳1级就还有n-1级要跳，2、最后一次跳2级就还有n-2级要跳
            return Jump_Floor1(n-1)+Jump_Floor1(n-2);
        }
    }

    /**
     * 看完递归的方法不要先被它的代码简洁所迷惑，可以分析一下复杂度，就会发现有很多重复的计算
     * 而且看完这个会发现和Fibonacci的递归方法有点像
     * @非递归
     */
    private static int result[] = new int[100];
    public static int Jump_Floor2(int n){
        if(n <= 2){
            return n;
        }else{
            if(result[n] != 0)
                return result[n];
            else{
                result[n] = Jump_Floor2(n-1)+Jump_Floor2(n-2);
                return result[n];
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("递归："+Jump_Floor1(3));
        System.out.println("非递归："+Jump_Floor2(3));
    }
}
