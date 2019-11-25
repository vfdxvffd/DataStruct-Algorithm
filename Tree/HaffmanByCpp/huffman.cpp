#include<iostream>
#include"struct.h"
using namespace std;

void EnCode(table t, char* str)
{
	cout << "EnCodeing............./" << endl;
	int len = strlen(str);
	for (int i = 0; i < len; i++)
	{
		bNode p = t->first;
		while (p != NULL)
		{
			if (p->symbol == str[i])
			{
				cout << p->code;
				break;
			}
			p = p->next;
		}
	}
	cout << endl;
}

void DeCode(Tree root, char* str)
{
	cout << "DeCode............./" << endl;
	Tree p = root;
	int len = strlen(str);
	for (int i = 0; i < len; i++)
	{
		if (p->left == NULL && p->right == NULL)
		{
			cout << p->data;
			p = root;
		}
		if (str[i] == '0')
			p = p->left;
		if (str[i] == '1')
			p = p->right;
		if (str[i] != '0' && str[i] != '1')
		{
			cout << "The Input String Is Not Encoded correctly !" << endl;
			return;
		}
	}
	if (p->left == NULL && p->right == NULL)
		cout << p->data;

}

int main()
{
	queue q = Create_Queue();
	Tree root = Create_Tree(q);
	table t = Create_Table(root);
	char* str = new char;
	cin >> str;
	EnCode(t, str);
	char* str1 = new char;
	cin >> str1;
	DeCode(root, str1);
}