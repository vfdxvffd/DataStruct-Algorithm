package BackTrack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Package {
	
	//0-1背包问题，回溯解法
	public static final int N = 5;		//物品数量
	static int[] values = {4,5,2,1,3};	//物品的价值
	static int[] weights = {3,5,1,2,2};	//物品的质量
	public static final int C = 8;		//背包的容量
	static int MAX = -1;				//记录最大的价值
	static int[] bag = {0,0,0,0,0};		//物品放置情况，bag[i] = 0表示第i个物品未被装入，等于1则表示已装入
	static List<int[]> list = new LinkedList<int[]>();	//保存最优的结果，可能有多个结果，所以用链表装
	
	public static boolean IsOk(int[] bag, int level) {	//判断当前背包是否超重
		int weight = 0;
		for (int i = 0; i <= level; i++) {	//计算当前背包中所有的物品的总质量
			if(bag[i] == 1) {	//bag[i] == 1表示这个物品已被装入背包
				weight += weights[i];
				if(weight > C)
					return false;
			}
		}
		return true;
	}
	
	public static void MaxValue(int[] bag, int level) {
		if(level == N) {				//已经判断完最后一个物品
			//先计算当前总价值
			int value = 0;
			for (int i = 0; i < N; i++) {
				if(bag[i] == 1) {
					value += values[i];
				}
			}
			if(value > MAX) {
				list.clear();		//发现更优的结果
				MAX = value;
				list.add(bag.clone());
			}else if (value == MAX) {	//其他放置情况的最优解
				list.add(bag.clone());
			}
			return;
		}
		for (int i = 0; i < 2; i++) {
			bag[level] = i;
			if(IsOk(bag, level)) {
				MaxValue(bag, level+1);
			}
		}
	}

	public static void main(String[] args) {
		MaxValue(bag, 0);
		System.out.println(MAX);
		Iterator<int[]> iter = list.iterator();
		while(iter.hasNext()) {
			int[] temp = iter.next();
			for (int i = 0; i < temp.length; i++) {
				System.out.print(temp[i]+" ");
			}
			System.out.println();
		}
	}
}

