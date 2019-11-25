public class demo2 {
    //分治算法的典例1———二分查找

    public static int Binary_Search(int []data, int x, int n){   //data为待搜索数组(有序)，x为待搜索元素，n为数组大小
        int left = 0, right = n - 1;            //指示左右的两个指示器
        while(left <= right){                   //left可以等于right，因为有可能刚好两个指示器同时指示到了待查找元素上
            int mid = (left+right)/2;
            if(data[mid] > x)
                right = mid-1;
            else if(data[mid] < x)
                left = mid+1;
            else    return mid;
        }
        return -1;           //表示查找失败
    }

    public static void main(String[] args) {
        int data[] = new int[10];
        for (int i = 0; i < 10; i++) {
            data[i] = i*3;
        }
        System.out.println("the 9 index is "+Binary_Search(data,9,10));
    }
}
