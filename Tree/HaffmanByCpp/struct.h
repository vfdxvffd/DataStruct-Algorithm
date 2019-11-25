#pragma once
#include<iostream>
#include<cstring>
using namespace std;

//优先级队列
typedef struct QNode
{
	struct TNode* val;			//树的结点,其实也就是数据域
	int priority;				//优先级
	struct QNode* next;			//指针域
}*Node;
typedef struct Queue
{
	int size;					//队列大小
	struct QNode* front;		//队列头指针
}queue;

//树
typedef struct TNode
{
	char data;					//字符值
	struct TNode* left;			//左孩子
	struct TNode* right;		//右孩子
}*Tree;

//表
typedef struct BNode
{
	char code[256];					//编码
	char symbol;				//字符
	struct BNode* next;			//指向下一个
}*bNode;
typedef struct Table
{
	struct BNode* first;		//表头
	struct BNode* last;			//表尾
}*table;

//队列的函数

//初始化
queue Init_queue()
{
	queue q;
	q.size = 0;
	q.front = new struct QNode;
	if (!q.front)
	{
		printf("分配失败！\n");
		exit(1);
	}
	q.front->next = NULL;
	return q;
}

//插入，根据优先级
bool EnQueue(queue& q, Tree avl, int weight)
{
	Node newp = new struct QNode;
	newp->val = avl;
	newp->priority = weight;
	//空表
	if (q.size == 0 || q.front == NULL)
	{
		newp->next = NULL;
		q.front = newp;
		q.size = 1;
		return true;
	}
	else		//中间位置，需要迭代
	{
		if (weight <= q.front->priority)		//比第一个都小
		{
			newp->next = q.front;
			q.front = newp;
			q.size++;
			return true;
		}
		else
		{
			Node beforp = q.front;
			while (beforp->next != NULL)
			{
				if (weight <= beforp->next->priority)
				{
					newp->next = beforp->next;
					beforp->next = newp;
					q.size++;
					return true;
				}
				else
				{
					beforp = beforp->next;
				}
			}
			//需要插在队列最后
			if (beforp->next == NULL)
			{
				newp->next = NULL;
				beforp->next = newp;
				q.size++;
				return true;
			}
		}
	}
	return true;
}

//创建队列
queue Create_Queue()
{
	queue q = Init_queue();
	while (1)
	{
		char symbol;
		int weight;
		cin >> symbol >> weight;

		if (weight == 0)
			break;

		Tree t = new struct TNode;
		t->data = symbol;
		t->left = NULL;
		t->right = NULL;
		EnQueue(q, t, weight);
	}
	return q;
}

//弹出队列优先级最小的
Tree Dequeue(queue& q)
{
	if (q.front == NULL)
	{
		cout << "空队！" << endl;
		exit(1);
	}
	Node p = q.front;
	q.front = p->next;
	Tree e = p->val;
	q.size--;
	delete[] p;
	return e;
}


//树的函数

//创建一棵树
Tree Create_Tree(queue& q)
{
	while (q.size != 1)
	{
		int priority = q.front->priority + q.front->next->priority;
		Tree left = Dequeue(q);
		Tree right = Dequeue(q);

		Tree newTNode = new struct TNode;
		newTNode->left = left;
		newTNode->right = right;

		EnQueue(q, newTNode, priority);
	}
	Tree root = new struct TNode;
	root = Dequeue(q);
	return root;
}

//表的函数


void travel(Tree root, table& t, char code[256], int k)
{
	if (root->left == NULL && root->right == NULL)
	{
		code[k] = '\0';

		bNode b = new struct BNode;
		b->symbol = root->data;
		strcpy(b->code, code);
		b->next = NULL;

		//尾部插入法
		if (t->first == NULL)		//空表
		{
			t->first = b;
			t->last = b;
		}
		else
		{
			t->last->next = b;
			t->last = b;
		}
	}
	if (root->left != NULL)
	{
		code[k] = '0';
		travel(root->left, t, code, k + 1);
	}
	if (root->right != NULL)
	{
		code[k] = '1';
		travel(root->right, t, code, k + 1);
	}

}

//创建一张表
table Create_Table(Tree root)
{
	table t = new struct Table;
	t->first = NULL;
	t->last = NULL;

	char code[256];
	int k = 0;
	travel(root, t, code, k);
	return t;
}