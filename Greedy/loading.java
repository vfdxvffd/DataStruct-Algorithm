import java.util.Arrays;
public class loading {

    public static class Element implements Comparable{
        int weight;   //重量
        int i;          //序号

        public Element(int x, int y){ //构造器
            this.weight = x;
            this.i = y;
        }

        @Override
        public int compareTo(Object x) {    //实现接口方法
            return((this.weight - ((Element)x).weight));
        }
    }

    private static void merge(Element data[], int start, int mid, int end){
        int p = start, q = mid+1, r = 0;
        Element newdata[] = new Element[end-start+1];
        while(p <= mid && q <= end){
            if(data[p].weight <= data[q].weight){                 //从大到小排序
                newdata[r++] = data[p++];
            }else{
                newdata[r++] = data[q++];
            }
        }

        //此时，两个子数组中会有一个中元素还未被全部归并到新数组中，作如下处理
        while(p <= mid){
            newdata[r++] = data[p++];
        }
        while(q <= end){
            newdata[r++] = data[q++];
        }
        //再将有序的数组中的值赋给原数组，其实也可以直接返回这个新数组
        for (int i = start; i <= end; i++) {
            data[i] = newdata[i-start];
        }
    }

    public static void merge_sort(Element data[], int start, int end){
        int mid = (start+end)/2;
        if(start < end){
            merge_sort(data,start,mid);
            merge_sort(data,mid+1,end);
            merge(data,start,mid,end);
        }
    }

    /**
     *
     * @param C     表示船的最大装载量
     * @param w     w[i]表示第i个物品的质量
     * @param x     x[i] = 0表示不将其装进船，x[i] = 1表示将其装进船
     * @return
     */
    public static int load(int C, int []w, int []x){
        Element[] d = new Element[w.length];
        for (int i = 0; i < w.length; i++) {    //构造好对象
            d[i] = new Element(w[i],i);
        }
        int opt = 0;    //用opt表示装入货物的总重量
        int sum = 0;    //sum表示装入的数量
        Arrays.fill(x,0);
        merge_sort(d,0,w.length-1);
        for(int i = 0; i < w.length && d[i].weight < C; i++){
            System.out.println(i);
            x[d[i].i] = 1;
            opt += d[i].weight;
            C -= d[i].weight;
            sum++;
        }
        return opt;
    }

    public static void main(String[] args) {
        int [] w = new int[10];
        int [] x = new int[10];
        for (int i = 0; i < 10; i++) {
            w[i] = i+1;
        }
        System.out.println(load(33,w,x));
    }
}

