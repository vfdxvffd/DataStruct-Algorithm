public class mergesort {

    /**
     * 这个函数是将数组合并在一起的，其实并没有将数组真的分开，只是用start和end指示不同的元素，来达到分割的目的
     * @param  p        指示子数组1的元素
     * @param  q        指示子数组2的元素
     * @param  r        指示合并后数组的元素
     * @param start     start到mid是需要合并的子数组1
     * @param mid
     * @param end       mid+1到end是需要合并的子数组2
     */
    private static void merge(int data[], int start, int mid, int end){
        int p = start, q = mid+1, r = 0;
        int newdata[] = new int[end-start+1];
        while(p <= mid && q <= end){
            if(data[p] >= data[q]){                 //从大到小排序
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

    /**
     * 只要数组的大小不为1，就一直分割，直到不能分割为止（即数组长度为1），
     * 不能分割后按照出入栈顺序，会将分割的小数组分别排序后归并起来
     * @param data      待排序数组
     * @param start     起始位置
     * @param end       终止位置
     */
    public static void merge_sort(int data[], int start, int end){
        int mid = (start+end)/2;
        if(start < end){
            merge_sort(data,start,mid);
            merge_sort(data,mid+1,end);
            merge(data,start,mid,end);
        }
    }

    public static void main(String[] args) {
        int array[] = new int[10];
        array[0] = 3;
        array[1] = 2;
        array[2] = 7;
        array[3] = 5;
        array[4] = 1;
        array[5] = 9;
        array[6] = 8;
        array[7] = 4;
        array[8] = 0;
        array[9] = 6;
        merge_sort(array,0,9);
        for (int i = 0; i < 10; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
