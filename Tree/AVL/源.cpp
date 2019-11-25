#include<iostream>
#include"BST.h"
using namespace std;
int main()
{
	BiTree bst = Create_Binary_Tree();
	//Delete_Binary_Tree(bst, 3);
	int level = 1;
	Midorder_Traverse(bst, level);
	system("pause");
}