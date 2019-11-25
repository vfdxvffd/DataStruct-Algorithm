#pragma once
#include<iostream>
constexpr auto ENDKEY = -1;
template<typename T1, typename T2>
constexpr auto max(T1 x, T2 y) { return x>y?x:y; }

using namespace std;

typedef struct Binary_Tree_node
{
	int data;	//数据域
	struct Binary_Tree_node* lchild, * rchild;		//左右孩子结点
}Binode, * BiTree;

//访问二叉树
void visit(int c, int level)
{
	printf("%d位于第%d层\n", c, level);
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

//一个结点的深度
int Binary_Tree_height(BiTree bst)
{
	if (bst == NULL)
		return 0;
	int l = Binary_Tree_height(bst->lchild);
	int r = Binary_Tree_height(bst->rchild);
	return max(l, r) + 1;
}

//遍历整棵树，如果出现不平衡的情况就旋转

//左左型单旋转
void Lleft(BiTree &bst)
{
	BiTree lch = bst->lchild;		//保存不平衡结点的左孩子结点
	bst->lchild = lch->rchild;
	lch->rchild = bst;
	bst = lch;
}

//右右型单旋转
void Rright(BiTree &bst)
{
	BiTree rch = bst->rchild;		//保存不平衡结点的右孩子结点
	bst->rchild = rch->lchild;
	rch->lchild = bst;
	bst = rch;
}

//左右型双旋转
void Lright(BiTree &bst)
{
	//先做左旋，将其变成左左型
	BiTree lch = bst->lchild;
	BiTree lrch = bst->lchild->rchild;
	bst->lchild = lrch;
	bst->lchild->lchild = lch;
	bst->lchild->lchild->rchild = NULL;		//可能存在bug	todo

	Lleft(bst);
}

//右左型双旋转
void Rleft(BiTree &bst)
{
	//先做右旋，将其变成右右型
	BiTree rch = bst->rchild;
	BiTree rlch = bst->rchild->lchild;
	bst->rchild = rlch;
	bst->rchild->rchild = rch;
	bst->rchild->rchild->lchild = NULL;

	Rright(bst);
}

void Check_Binary_Tree(BiTree  &bst)
{	
	if (bst == NULL)
		return;
	if (Binary_Tree_height(bst->lchild) - Binary_Tree_height(bst->rchild) > 1)
	{
		if (Binary_Tree_height(bst->lchild->lchild) > Binary_Tree_height(bst->lchild->rchild))
			Lleft(bst);
		else
			Lright(bst);
	}
	if (Binary_Tree_height(bst->rchild) - Binary_Tree_height(bst->lchild) > 1)
	{
		if (Binary_Tree_height(bst->rchild->rchild) > Binary_Tree_height(bst->rchild->lchild))
			Rright(bst);
		else
			Rleft(bst);
	}
	//Check_Binary_Tree(bst->lchild);
	//Check_Binary_Tree(bst->rchild);
}

//假设没有相等的data
//插入结点
void Insert_Binary_Tree(BiTree& bst ,int t)			//bst是根节点
{

	if (bst == NULL)	//空树或者叶结点
	{
		BiTree newp = new Binode;
		newp->data = t;
		newp->lchild = NULL;
		newp->rchild = NULL;
		bst = newp;	
	}
	else
	{
		if (t > bst->data)
			Insert_Binary_Tree(bst->rchild, t);
		else
			Insert_Binary_Tree(bst->lchild, t);
	}	
	Check_Binary_Tree(bst);
}

//创建一棵二叉排序树
BiTree Create_Binary_Tree()
{
	BiTree bst = NULL;
	int t;
	cin >> t;
	while (t != ENDKEY)
	{
		Insert_Binary_Tree(bst, t);
		cin >> t;
	}
	return bst;
}

//二叉排序树的删除
BiTree Delete_Binary_Tree(BiTree bst, int t)
{
	Binode* newp, * f, * s, * q;	
	newp = bst;
	f = NULL;
	while (newp)					
	{
		if (newp->data == t)
			break;
		f = newp;				
		if (t > newp->data)
			newp = newp->rchild;
		else
			newp = newp->lchild;
	}
	if (newp == NULL)				
		return bst;
	if (newp->lchild == NULL)
	{
		if (f == NULL)					
			 bst = bst->rchild;
		else if (f->lchild == newp)
			f->lchild = newp->rchild;
		else							
			f->rchild = newp->rchild;

		delete[]newp;			
	}
	else if (newp->rchild == NULL)		
	{
		if (f == NULL)
			bst = bst->lchild;
		else if (f->lchild == newp)
			f->lchild = newp->lchild;
		else
			f->rchild = newp->lchild;

		delete[]newp;
	}
	else				
	{
		q = newp;
		s = newp->lchild;
		while (s->rchild)
		{
			q = s;				
			s = s->rchild;		
		}
		if (q == newp)
			q->lchild = s->lchild;	
		else	
			q->rchild = s->lchild;
		newp->data = s->data;
		delete[]s;
	}
	Check_Binary_Tree(bst);
	return bst;
}

//搜索二叉树
BiTree Search_Binary_Tree(BiTree bst, int t)
{
	if (bst == NULL)
		return NULL;
	if (bst->data == t)
		return bst;
	else if (t > bst->data)
		return Search_Binary_Tree(bst->rchild, t);
	else
		return Search_Binary_Tree(bst->lchild, t);
}