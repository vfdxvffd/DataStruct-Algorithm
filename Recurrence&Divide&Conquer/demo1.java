public class demo1 {
    //对递归的浅显举例解释

    /**
     * @author localhost
     * The first demo is for factorial
     * @data 2019/11/1 afternoon
     */
    public static int factorial(int n){                     //0的阶乘等于1
        if(n == 0)  return 1;
        else return factorial(n - 1) * n;
    }

    /**
     * @author localhost
     * The second demo is for Fibonacci
     * @data 2019/11/1 afternoon
     */
    public static int fibonacci(int n){
        if(n == 1) return 1;
        else if (n == 2) return 1;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * @author localhost
     * The third demo is for Ackerman
     * @data 2019/11/1 afternoon
     * 这是一个双递归函数(当一个函数及它的一个变量是由函数自身定义时称为双递归函数)
     */
    public static int ackerman(int n, int m){
        if(n == 1 && m == 0) return 2;
        else if(n == 0) return 1;
        else if(m == 0) return n + 2;
        else return ackerman(ackerman(n - 1,m),m - 1);
    }

    /**
     * @author localhost
     * 排列问题
     * @param args
     * @data 2019/11/1 evening
     * 算法思路：
     * (1)n个元素的全排列=（n-1个元素的全排列）+（另一个元素作为前缀）；
     * (2)出口：如果只有一个元素的全排列，则说明已经排完，则输出数组；
     * (3)不断将每个元素放作第一个元素，然后将这个元素作为前缀，并将其余元素继续全排列，等到出口，出口出去后还需要还原数组；
     */
    private static void swap(int a[], int k, int m){       //交换k和m下标的元素的值
        int temp = a[k];
        a[k] = a[m];
        a[m] = temp;
    }
    public static void perm(int a[], int k, int m){
        if(k == m) {     //只有一个元素
            for (int i = 0; i <= m; i++) {
                System.out.print(a[i]+" ");
            }
            System.out.println();
        }else{          //还有多个元素，递归产生排列
            for (int i = k; i <= m; i++) {
                swap(a,k,i);                //排列到这个元素就要将其放在第一个位置
                perm(a,k+1,m);
                swap(a,k,i);                //从此出口出去后还需要将刚刚调换的位置换回来
            }
        }
    }

    /**
     * @author localhost
     * 整数划分问题
     * @param args
     * @date 2019/11/3 12:57
     *
     * 说明一下问题，什么是整数划分？
     * n=m1+m2+...+mi; （其中mi为正整数，并且1 <= mi <= n），则{m1,m2,...,mi}为n的一个划分。
     * 如果{m1,m2,...,mi}中的最大值不超过m，即max(m1,m2,...,mi)<=m，则称它属于n的一个m划分。这里我们记n的m划分的个数为f(n,m);
     * 举个例子，当n=5时我们可以获得以下这几种划分（注意，例子中m>=5）
     *  5 = 5
     *    = 4 + 1
     *    = 3 + 2
     *    = 3 + 1 + 1
     *    = 2 + 2 + 1
     *    = 2 + 1 + 1 + 1
     *    = 1 + 1 + 1 + 1 + 1
     * 算法思路：我们用q(n,m)表示将n用不大于m的数字划分的方法的个数
     *   1、n = 1时：只有一种划分法就是1
     *   2、m = 1时：也只有一种划分法就是n个1相加
     *   3、n < m时: 划分的方法也就只限于q(n,n)了，毕竟比n大的数也取不到嘛（不能取负数，要不然无限多了）
     *   4、n = m时：就是1+(m-1)这一种情况加q(n,m-1)即1+q(n,m-1)。比如q(6,6)就是1+q(6,5)
     *   5、n > m时：这种情况下又包含两种情况：
     *      5(1)、划分中包含m时：即{m, {x1,x2,...xi}}(它们之和为n), 其中{x1,x2,... xi} 的和为n-m，
     *          所以就是n-m的m划分，即q(n-m,m)
     *      5(2)、划分中不包含m时:划分中所有的值都比m小，即q(n,m-1)
     *   因此第5中情况的划分为q(n-m,m)+1(n,m-1)
     *   对第2中举例子详述：q(5,3):
     *        (1)包含3:   1+1+3;    2+3;  既然每种情况都包含了3，那去掉3对其余各数相加为(5-3=)2的划分的个数和其相等，
     *              那就是对2(m=3)的划分了
     *        (2)不包含3：  1+1+1+1+1;     1+1+1+2;     1+2+2;
     */
    public static int equationCount(int n, int m){
        if (n < 1 || m < 1)
            return 0;
        if(n == 1 || m == 1)
            return 1;
        if(n < m)
            return equationCount(n,n);
        if(n == m)
            return equationCount(n,m-1)+1;
        return equationCount(n-m,m)+equationCount(n,m-1);   //n > m的情况
    }



    public static void main(String[] args) {
        /*System.out.println("The factorial of 3 is "+factorial(3));
        System.out.println("The fibonacci index 3 is "+ fibonacci(3));
        System.out.println("The ackerman index 3 3 is "+ackerman(3,3));
        int a[] = new int[3];       a[0] = 0; a[1] = 1; a[2] = 2;
        perm(a,0,2);*/
        //System.out.println(equationCount(6,6));

        System.out.println("The fibonacci index 10 is "+ fibonacci(100));
    }
}
