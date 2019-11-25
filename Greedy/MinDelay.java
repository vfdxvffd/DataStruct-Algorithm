import java.util.ArrayList;
import java.util.Collections;

public class MinDelay {
    public static class job implements Comparable{
        int length;         //此任务所需长度
        int deadline;       //截止时间

        public job(int length, int deadline){
            this.deadline = deadline;
            this.length = length;
        }
        @Override
        public int compareTo(Object o) {
            return this.deadline - ((job) o).deadline;
        }
    }

    /**
     *
     * @param jobs      保存所有任务的长度和截止时间
     * @return          返回最小的延迟
     */
    public static int GetMinDelay(ArrayList<job> jobs){
        Collections.sort(jobs);
        int delay = 0,temp = 0;
        for (int i = 0; i < jobs.size(); i++) {
            temp += jobs.get(i).length;
            if(delay < temp-jobs.get(i).deadline){
                delay = temp-jobs.get(i).deadline;
            }
        }
        return delay;
    }

    public static void main(String[] args) {
        ArrayList<job> jobs = new ArrayList<job>();
        jobs.add(new job(3,6));
        jobs.add(new job(2,9));
        jobs.add(new job(1,8));
        jobs.add(new job(4,9));
        jobs.add(new job(3,14));
        jobs.add(new job(2,15));
        System.out.println(GetMinDelay(jobs));
    }

}
