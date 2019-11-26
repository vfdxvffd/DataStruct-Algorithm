import java.util.HashMap;

public class Package {
    //0-1背包问题，回溯解法
    final static int N = 5;
    static int[] values = {4,5,2,1,3};
    static int[] weights= {3,5,1,2,2};
    final static int C = 8;
    static int Max = -1;
    static HashMap<Integer,int[]> map = new HashMap<Integer, int[]>();


    public static boolean IsOk(int[] bag,int level){
        int weight = 0;
        for (int j = 0; j <= level; j++) {
            if(bag[j] == 1){
                weight += weights[j];
                if(weight > C)
                    return false;
            }
        }
        return true;
    }

    public static void MaxValue(int[] bag,int level){
        if(level == N){
            int temp = 0;
            for (int i = 0; i < N; i++) {
                if(bag[i] == 1)
                    temp+=values[i];
            }
            Max = Math.max(Max,temp);
            int[] bags = bag.clone();
           /* for (int i:bags) {
                System.out.print(i+" ");
                //bags[i] = bag[i];
            }*/
            //System.out.print("---value--->"+temp+"\n");
            map.put(temp,bags);               //一个可行解
            return;
        }
        for (int i = 0; i < 2; i++) {
            bag[level] = i;
            if(IsOk(bag,level)){
                MaxValue(bag,level+1);
            }
        }
    }

    public static void main(String[] args) {
        int[] bag = {0,0,0,0,0};
        MaxValue(bag,0);
        System.out.println("The follows are all the feasible solution:");
        for (Integer key:map.keySet()) {
            int[] result = map.get(key);
            for (int i:result){
                System.out.print(i+" ");
            }
            System.out.println("---value--->"+key);
        }
        System.out.println("The optimal solution is:");
        for (int i:map.get(Max)) {
            System.out.print(i+" ");
        }
        System.out.println("---value--->"+Max);
    }
}
