#include<stdio.h>
#include<stdlib.h>
typedef char elemtype;
//结点
typedef struct Binode
{
	char data;
	struct Binode *lchild, *rchild;
}Binode, *BiTree;

//先序创建一棵二叉树，先序的顺序创建一个二叉树
void PcreateBiTree(BiTree &T)			//*T是一个结点指针 			BiTree *T 就相当于	struct Binode **T		 
{
	char c;
	scanf("%c", &c);
	if (' ' == c)
	{
		T = NULL;
	}
	else
	{
		T = (struct Binode *)malloc(sizeof(struct Binode));
		T->data = c;
		PcreateBiTree(T->lchild);
		PcreateBiTree(T->rchild);
	}
}

//访问二叉树
void visit(char c,int level)
{
	printf("%c位于第%d层\n", c, level);
}

//先序遍历二叉树
void Preorder_Traverse(BiTree T, int level)
{
	if (T)
	{
		visit(T->data, level);
		Preorder_Traverse(T->lchild, level + 1);
		Preorder_Traverse(T->rchild, level + 1);
	}
}

//后序遍历二叉树
void  Aftorder_Traverse(BiTree T, int level)
{
	if (T)
	{
		Aftorder_Traverse(T->lchild, level + 1);
		Aftorder_Traverse(T->rchild, level + 1);
		visit(T->data, level);
	}
}

//中序遍历
void Midorder_Traverse(BiTree T, int level)
{
	if (T)
	{
		Midorder_Traverse(T->lchild, level + 1);
		visit(T->data, level);
		Midorder_Traverse(T->rchild, level + 1);	
	}
}
int main()
{
	int level = 1;
	BiTree T = NULL;
	PcreateBiTree(T);
	Midorder_Traverse(T, level);
	printf("************************************\n");
	Preorder_Traverse(T, level);
	printf("************************************\n");
	Aftorder_Traverse(T, level);
	system("pause");
}
