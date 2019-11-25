import java.util.*;

public class Map {
    final static int MAX = 333;         //定义一个无穷大

    /*======================顶点===================*/
    public static class Node{
        /*关键路径*/
        int id;                 //序号
        int earlist;
        int lastest;
        /*常规*/
        char m_cdata;           //顶点数据
        boolean m_bIsVisited;   //判断顶点是否被访问过
        public Node(){}         //无参构造器
        public Node(char data){ //初始data域和未被访问
            this.m_cdata = data;
            this.m_bIsVisited = false;
        }
        public Node(int id/*, int earlist, int lastest*/){
            this.id  = id;
            this.earlist = 0;
            this.lastest = MAX;
        }
    }

    /*======================边======================*/

    public static class Edge{
        int m_iNodeIndexA;          //边的A顶点
        int m_iNodeIndexB;          //边的B顶点
        int m_iWeightValue;         //边的权值
        boolean m_bIs_Selected;     //边是否被选择过

        public Edge(){}             //无参构造器
        public Edge(int nodeIndexA, int nodeIndexB, int nodeWeight){
            this.m_iNodeIndexA = nodeIndexA;
            this.m_iNodeIndexB = nodeIndexB;
            this.m_iWeightValue = nodeWeight;
            this.m_bIs_Selected = false;    //默认此边未被选择过
        }
        public Edge(int m_iNodeIndexA, int m_iNodeIndexB){
            this.m_iNodeIndexA = m_iNodeIndexA;
            this.m_iNodeIndexB = m_iNodeIndexB;
        }
    }

    /*======================图的数据成员================*/
    int m_iCapacity;            //图中最多可容纳的顶点数
    int m_iNodeCount;           //已经添加的顶点数
    Node[] m_pNodeArray;        //用来存放顶点数目
    int[][] m_pMatrix;            //用来存放邻接矩阵
    Edge[] m_pEdge;             //用来存最小生成树的边

    /*=======================图的成员函数=================*/

    private int getValueFromMatrix(int row, int col){       //读取邻接矩阵某个行列的值
        if(row >= this.m_iCapacity || row < 0 || col >= this.m_iCapacity || col < 0){
            System.out.println("Out of Index");
            return -1;
        }
        return this.m_pMatrix[row][col];

    }

    private int getMinEdge(Vector<Edge> e){
        int i = 0;      //对整个数组的索引
        int minWeight = 0, edgeIndex = 0;
        for(; i < e.size(); i++){
            if(e.get(i).m_bIs_Selected == false){
                minWeight = e.get(i).m_iWeightValue;
                edgeIndex = i;
                break;
            }
        }
        if(minWeight == 0)      //说明没有未被访问过的边了，返回-1作为标志
            return -1;
        for(; i < e.size(); i++){
            if(e.get(i).m_iWeightValue < minWeight && e.get(i).m_bIs_Selected == false){
                minWeight = e.get(i).m_iWeightValue;
                edgeIndex = i;
            }
        }
        return edgeIndex;
    }

    private void reSetNode(){       //将所有顶点置为未访问
        for (int i = 0; i < this.m_iNodeCount; i++) {
            this.m_pNodeArray[i].m_bIsVisited = false;
        }
    }

    private boolean setValueToMatrixForUndirectedGraph(int row, int col, int val){  //无向图
        if(row >= this.m_iCapacity || row < 0 || col >= this.m_iCapacity || col < 0)
            return false;
        this.m_pMatrix[row][col] = val;
        this.m_pMatrix[col][row] = val;
        return true;
    }

    public boolean setValueToMatrixForDirectedGraph(int row, int col, int val){  //无向图
        if(row >= this.m_iCapacity || row < 0 || col >= this.m_iCapacity || col < 0)
            return false;
        this.m_pMatrix[row][col] = val;
        return true;
    }

    public Map(int capacity){   //构造器
        this.m_iCapacity = capacity;                        //图中顶点的数目
        this.m_iNodeCount = 0;
        this.m_pNodeArray = new Node[this.m_iCapacity];
        this.m_pMatrix = new int[m_iCapacity][m_iCapacity]; //邻接矩阵
        this.m_pEdge = new Edge[m_iCapacity-1];             //最小生成树边的数目等于点的数目-1
    }

    public boolean addNode(Node pNode){  //点向无环图中增加顶
        System.out.println("Attention : This function is for undirected graph! Good Luck!");
        if(this.m_iCapacity == this.m_iNodeCount){          //图已经满了
            System.out.println("The map has already been full!");
            return false;
        }
        System.out.print("How many Nodes you want to connect with "+pNode.m_cdata+"? Input:");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        if(num != 0)
            System.out.println("It's time to input those nodes:");
        for (int i = 0; i < num; i++) {
          char data = in.nextLine().charAt(0);
            for (int j = 0; j < this.m_iNodeCount; j++) {
                if(this.m_pNodeArray[j].m_cdata == data){
                    setValueToMatrixForUndirectedGraph(this.m_iNodeCount,j,1);
                }
            }
        }
        this.m_pNodeArray[this.m_iNodeCount] = pNode;
        this.m_iNodeCount++;
        return true;
    }

    public boolean AddNode(Node pNode){     //向有向图中加入结点，配合setValueToMatrixForDirectedGraph使用
        if (this.m_iNodeCount >= this.m_iCapacity)
        {
            System.out.println("The map has already been full!");
            return false;
        }
        //加到顶点数组
        this.m_pNodeArray[m_iNodeCount] = pNode;
        this.m_iNodeCount++;		//数量加1
        return true;
    }

    public void Print_Matrix(){         //打印邻接矩阵
        for (int i = 0; i < this.m_iCapacity; i++) {
            for (int j = 0; j < this.m_iCapacity; j++) {
                System.out.print(this.m_pMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void DepthFirstTravel(int nodeIndex){    //从指定下标开始
        System.out.print(this.m_pNodeArray[nodeIndex].m_cdata); //访问此结点
        this.m_pNodeArray[nodeIndex].m_bIsVisited = true;       //设置为访问过的
        for (int i = 0; i < this.m_iNodeCount; i++) {
            if(getValueFromMatrix(nodeIndex,i) == 1 && this.m_pNodeArray[i].m_bIsVisited == false)
                DepthFirstTravel(i);
        }
    }

    public void BreadthFirstTravel(int nodeIndex){
        Queue<Integer> q = new LinkedList<Integer>();
        System.out.print(this.m_pNodeArray[nodeIndex].m_cdata);
        this.m_pNodeArray[nodeIndex].m_bIsVisited = true;
        q.offer(nodeIndex);      //将第一个访问的顶点加入队列
        while(!q.isEmpty()){
            int p = q.poll();          //弹出第一个顶点
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(getValueFromMatrix(p,i) == 1 && this.m_pNodeArray[i].m_bIsVisited == false){
                    System.out.print(this.m_pNodeArray[i].m_cdata);
                    this.m_pNodeArray[i].m_bIsVisited = true;
                    q.offer(i);
                }
            }
        }
    }

    public void PrimTree(int nodeIndex){
        int value = 0;      //保存边的权值
        Stack<Integer> s = new Stack<Integer>();    //用s保存顶点
        s.push(nodeIndex);
        Vector<Edge> vec = new Vector<Edge>();      //保存边
        int edgeCount = 0;                          //最小生成树边的个数，构建完成后等于顶点个数-1

        while(edgeCount < this.m_iNodeCount - 1){
            int temp = s.pop();
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(getValueFromMatrix(temp,i) != 0 && this.m_pNodeArray[i].m_bIsVisited == false){
                    Edge newEdge = new Edge(temp,i,getValueFromMatrix(temp,i));
                    vec.add(newEdge);
                }
            }
            //for循环结束后，就将与此顶点相连的所有边加入vec中了
            int edgeIndex = getMinEdge(vec);        //找出最小边的下标
            //打印这条边的信息
            System.out.println(this.m_pNodeArray[vec.get(edgeIndex).m_iNodeIndexA].m_cdata+"---" +
                    "------value = "+vec.get(edgeIndex).m_iWeightValue+"--------->" +
                    this.m_pNodeArray[vec.get(edgeIndex).m_iNodeIndexB].m_cdata);
            //处理后续，为下次while循环做准备
            vec.get(edgeIndex).m_bIs_Selected = true;           //将此边设置为访问过的
            this.m_pEdge[edgeCount] = vec.get(edgeIndex);       //将此边加入最小生成树的边的数组
            s.push(this.m_pEdge[edgeCount].m_iNodeIndexB);      //将此边的B顶点加入s
            edgeCount++;
            this.m_pNodeArray[temp].m_bIsVisited = true;        //将A顶点置为访问过的

        }

    }

    public void KruskalTree(){
        Vector<Edge> vec = new Vector<Edge>();        //存放所有边
        int edgeCount = 0;                            //最小生成树边的数目
        //1、取出所有边
        for (int i = 0; i < this.m_iNodeCount; i++) {
            for (int j = i; j < this.m_iNodeCount; j++) {   //考虑到邻接矩阵是个对称矩阵，所以只取其一半
                if(getValueFromMatrix(i,j) != 0){
                    Edge newEdge = new Edge(i,j,getValueFromMatrix(i,j));
                    vec.add(newEdge);
                }
            }
        }
        //2、从所有边中选出最小生成树的边
        while(edgeCount < this.m_iNodeCount - 1){
            int temp = getMinEdge(vec);
            if(this.m_pNodeArray[vec.get(temp).m_iNodeIndexA].m_bIsVisited == false
                    || this.m_pNodeArray[vec.get(temp).m_iNodeIndexB].m_bIsVisited == false){
                this.m_pEdge[edgeCount] = vec.get(temp);
                this.m_pNodeArray[vec.get(temp).m_iNodeIndexA].m_bIsVisited = true;
                this.m_pNodeArray[vec.get(temp).m_iNodeIndexB].m_bIsVisited = true;
                edgeCount++;
            }
            vec.get(temp).m_bIs_Selected = true;
        }
        for (int i = 0; i < edgeCount; i++) {
            System.out.println(this.m_pNodeArray[this.m_pEdge[i].m_iNodeIndexA].m_cdata+"-------" +
                    "-value" + this.m_pEdge[i].m_iWeightValue+"---------->"+
                    this.m_pNodeArray[this.m_pEdge[i].m_iNodeIndexB].m_cdata);
        }
    }

    /**
     * 考虑清楚为什么每加入一个顶点(即将这个顶点置为已访问)，只可能会对这个顶点的邻接点造成影响
     * @param nodeIndex
     * @param index         从nodexIndex到index的最短路径
     */
    public void Dijkstra(int nodeIndex, int index){        //单源最短路径问题，贪心策略
        int[] dist = new int[this.m_iNodeCount];   //保存每个顶点的最短路径
        int[] path = new int[this.m_iNodeCount];   //保存必经过的路径顶点
        Vector<Integer> adjacent = new Vector<Integer>();   //保存最小值dist邻接点
        Arrays.fill(dist,MAX);
        Arrays.fill(path,-1);
        dist[nodeIndex] = 0;        //自己与自己的最短距离当然是0啊

        while(true){
            int min = MAX;
            int pos = 0;
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(min > dist[i] && this.m_pNodeArray[i].m_bIsVisited == false){//找出dist中最小的且未访问的
                    pos = i;
                    min = dist[i];
                }
            }   //for循环结束后，应该是找到了当前dist中最小，且未被访问的顶点
            if(min == MAX){ //说明上述for循环没有找出未被访问的顶点
                break;
            }
            this.m_pNodeArray[pos].m_bIsVisited = true;
            //将其邻接点保存到adjacent中
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(this.m_pNodeArray[i].m_bIsVisited == false && getValueFromMatrix(pos,i) != 0){
                    adjacent.add(i);
                }
            }   //将选出最小dist的那个顶点的所有未被访问过的邻接点保存到adjacent中
            for (int i = 0; i < adjacent.size(); i++) {
                if(dist[pos] + getValueFromMatrix(pos,adjacent.get(i)) < dist[adjacent.get(i)]){
                    dist[adjacent.get(i)] = dist[pos] + getValueFromMatrix(pos,adjacent.get(i));
                    path[adjacent.get(i)] = pos;
                }
            }
            //每次while结束后，要将adjacent清空
            adjacent.clear();
        }
       //此时，dist和path应该都已填充完毕，最后进行对结果的打印
        System.out.println(dist[index]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(index);
        while(path[index] != -1){
            s.push(path[index]);
            index = path[index];
        }
        while(!s.isEmpty()){
            System.out.print(this.m_pNodeArray[s.pop()].m_cdata+" ");
        }
    }

    public void Floyd(int a, int b){    //多源最短路径问题，DP策略

        int[][] dist = new int[this.m_iNodeCount][this.m_iNodeCount];
        int[][] path = new int[this.m_iNodeCount][this.m_iNodeCount];

        //初始化
        for (int i = 0; i < this.m_iNodeCount; i++) {
            for (int j = 0; j < this.m_iNodeCount; j++) {
                if(i == j){
                    dist[i][j] = 0;
                    path[i][j] = -1;
                }else if(getValueFromMatrix(i,j) == 0){
                    dist[i][j] = MAX;
                    path[i][j] = i;
                }else{
                    dist[i][j] = this.m_pMatrix[i][j];
                    path[i][j] = -1;
                }
            }
        }
        /**
         *每新加进来一个顶点，判断会不会影响其他顶点的dist，如果能使得其他某个顶点的dist变小，则更新其dist
         *对于dist(i,j)，新加进来顶点k
         *状态转移方程：dist(i,j) = min{ dist(i,j), dist(i,k)+dist(k,j) }
         *最后别忘更新path为k，因为必经过k
         */
        for (int k = 0; k < this.m_iNodeCount; k++) {
            for (int i = 0; i < this.m_iNodeCount; i++) {
                for (int j = 0; j < this.m_iNodeCount; j++) {
                    if(dist[i][k] + dist[k][j] < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

        //打印
        System.out.println(dist[a][b]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(b);
        int index = b;
        while(path[a][index] != -1){
            s.push(path[a][index]);
            index = path[a][index];
        }
        s.push(a);
        while(!s.isEmpty()){
            System.out.print(this.m_pNodeArray[s.pop()].m_cdata+" ");
        }
    }

    public void ToSort(){           //无环图，且每条边的权值为1
        int[] Indegree = new int[this.m_iNodeCount];    //保存每个顶点的入度
        Queue<Integer> queue = new LinkedList<Integer>();   //保存入度为0的顶点
        Queue<Integer> result = new LinkedList<Integer>(); //保存最后的输出序列，为了预防有环的情况，所以有此一招

        //先给Indegree和queue初始化，如果邻接数组某一列都是0，说明这个顶点的入度为0
        for (int i = 0; i < this.m_iNodeCount; i++) {
            for (int j = 0; j < this.m_iNodeCount; j++) {
                if(getValueFromMatrix(j,i) != 0)
                    Indegree[i]++;
            }
            if(Indegree[i] == 0){   //先将入度为0的顶点保存进来
                queue.offer(i);
            }
        }   //for循环结束后，会将两个初始化完成

        while(!queue.isEmpty()){
            int index = queue.poll();   //取出一个入度为0的顶点
            //本来这里应该直接打印，但为防止后面出现环的情况，先将结果存于队列，while循环结束后判断无误，再做输出
            result.offer(index);
            this.m_pNodeArray[index].m_bIsVisited = true;   //将其置为已访问
            //处理index的邻接点
            //1、先对其邻接点的入度减一
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(getValueFromMatrix(index,i) != 0){
                    Indegree[i]--;
                    //2、如果减一后入度为0，则将其入队
                    if(Indegree[i] == 0 && this.m_pNodeArray[i].m_bIsVisited == false){
                        queue.offer(i);
                    }
                }
            }
        }
        if(result.size() == this.m_iNodeCount){
            while(!result.isEmpty()){
                System.out.print(this.m_pNodeArray[result.poll()].m_cdata+" ");
            }
        }else{
            System.out.println("Error : There is a circle in this map!");
        }
    }

    /**
     * 首先保证此图为连通图
     * @return
     */
    public int getEarliest(){
        //先寻找入度为0的顶点,用enter保存其下标
        int enter = 0;
        for (int i = 0; i < this.m_iNodeCount; i++) {
            for (int j = 0; j < this.m_iNodeCount; j++) {
                if(getValueFromMatrix(j,i) != 0)
                    enter++;
            }
            if(enter == 0){
                enter = i;
                break;
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        this.m_pNodeArray[enter].earlist = 0;
        this.m_pNodeArray[enter].m_bIsVisited = true;
        q.offer(enter);      //将第一个访问的顶点加入队列
        while(!q.isEmpty()){
            int p = q.poll();          //弹出第一个顶点
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(getValueFromMatrix(p,i) != 0 && this.m_pNodeArray[i].m_bIsVisited == false){
                    //对这个顶点进行访问
                    this.m_pNodeArray[i].earlist = Math.max
                            (getValueFromMatrix(p,i)+this.m_pNodeArray[p].earlist,this.m_pNodeArray[i].earlist);
                    this.m_pNodeArray[i].m_bIsVisited = true;
                    q.offer(i);
                }
            }
        }
        return this.m_pNodeArray[this.m_iNodeCount-1].earlist;
    }

    public int getLatest(){         //假设第一个元素的下标为0
        int earliest = getEarliest();       //最后一个顶点最早的结束时间
        reSetNode();
        Stack<Integer> SNode = new Stack<Integer>();  //存储广度优先顺序的顶点

        Queue<Integer> q = new LinkedList<Integer>();
        SNode.push(0);
        this.m_pNodeArray[0].m_bIsVisited = true;
        q.offer(0);      //将第一个访问的顶点加入队列
        while(!q.isEmpty()){
            int p = q.poll();          //弹出第一个顶点
            for (int i = 0; i < this.m_iNodeCount; i++) {
                if(getValueFromMatrix(p,i) != 0 && this.m_pNodeArray[i].m_bIsVisited == false){
                    SNode.push(i);
                    this.m_pNodeArray[i].m_bIsVisited = true;
                    q.offer(i);
                }
            }
        }       //至此，已将每个顶点按逆序存入栈中

        int temp = SNode.peek();
        this.m_pNodeArray[temp].lastest = this.m_pNodeArray[temp].earlist;        //最栈顶的那个顶点先做初始化
        while(!SNode.isEmpty()){
            for(int i = 0; i < temp; i++){
                if(getValueFromMatrix(i,temp) != 0){
                    this.m_pNodeArray[i].lastest = Math.min
                            (this.m_pNodeArray[i].lastest,this.m_pNodeArray[temp].lastest-getValueFromMatrix(i,temp));
                }
            }
            temp = SNode.pop();
        }
        return this.m_pNodeArray[0].lastest;
    }

    public void key(int[][] felxible, int i,Queue<Integer> queue){
        if(i == this.m_iNodeCount-1){
            queue.add(this.m_pNodeArray[i].id);
            while(!queue.isEmpty()){
                System.out.print(queue.poll()+" ");
            }
            System.out.println();
        }
        for (int j = 0; j < this.m_iNodeCount; j++){        //回溯算法，输出每一条关键路径
            Queue<Integer> queue1 = new LinkedList<Integer>(queue); //拷贝一份这个队列
            if(felxible[i][j] == 0){
                queue1.add(this.m_pNodeArray[i].id);
                key(felxible,j,queue1);
            }
        }
    }

    public void KeyPath(){
        this.getLatest();
        int[][] flexible = new int[this.m_iNodeCount][this.m_iNodeCount];   //存储机动时间
        for (int i = 0; i < this.m_iNodeCount; i++) {
            Arrays.fill(flexible[i],-1);
        }

        for (int i = 0; i < this.m_iNodeCount; i++) {
            for (int j = 0; j < this.m_iNodeCount; j++) {
                if(this.getValueFromMatrix(i,j) != 0){
                    flexible[i][j] = this.m_pNodeArray[j].lastest - this.m_pNodeArray[i].earlist-this.getValueFromMatrix(i,j);
                }
            }
        }       //机动时间赋值完成

        Queue<Integer> queue = new LinkedList<Integer>();
        this.key(flexible,0,queue);
    }

    public static void main(String[] args) {
        Map p = new Map(10);
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        p.AddNode(n0);
        p.AddNode(n1);
        p.AddNode(n2);
        p.AddNode(n3);
        p.AddNode(n4);
        p.AddNode(n5);
        p.AddNode(n6);
        p.AddNode(n7);
        p.AddNode(n8);
        /*p.AddNode(n1);
        p.AddNode(n2);
        p.AddNode(n3);
        p.AddNode(n4);
        p.AddNode(n5);
        p.AddNode(n6);*/
        p.setValueToMatrixForDirectedGraph(0,1,6);
        p.setValueToMatrixForDirectedGraph(0,2,4);
        p.setValueToMatrixForDirectedGraph(0,3,5);
        p.setValueToMatrixForDirectedGraph(1,4,1);
        p.setValueToMatrixForDirectedGraph(2,4,1);
        p.setValueToMatrixForDirectedGraph(3,5,2);
        p.setValueToMatrixForDirectedGraph(4,6,9);
        p.setValueToMatrixForDirectedGraph(4,7,7);
        p.setValueToMatrixForDirectedGraph(5,4,0);
        p.setValueToMatrixForDirectedGraph(5,7,4);
        p.setValueToMatrixForDirectedGraph(6,8,2);
        p.setValueToMatrixForDirectedGraph(7,8,4);

        p.KeyPath();
    }
}
