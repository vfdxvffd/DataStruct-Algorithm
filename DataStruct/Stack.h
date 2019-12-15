#include<stdio.h>
#include<stdlib.h>
typedef int element;

typedef struct Node{
    element data;
    struct Node*next;
}SNode, *Stack;

Stack stack(){
    Stack top = (struct Node*)malloc(sizeof(struct Node));
    if(top == NULL){
        printf("Empty stack!\n");
        return NULL;
    }
    top->data = 0;
    top->next = NULL;
    return top;
}

bool empty(Stack top){
    if(top->next == NULL && top->data == 0){
        return true;
    }else if(top->next == NULL || top->data == 0){
        printf("There is a bug, come to repair it! quickly!\n");
        system("pause");
    }else{
        return false;
    }
}

bool Push(Stack top, element data){
    Stack newNode = (struct Node*)malloc(sizeof(struct Node));
    if(newNode == NULL){
        printf("Error!\n");
        return false;
    }
    newNode->data = data;
    newNode->next = top->next;
    top->next = newNode;
    top->data++;
    return true;
}

element Pop(Stack top){
    if(empty(top)){
        printf("empty Stack!\n");
        exit(1);
    }
    Stack q = top->next;
    element result = q->data;
    top->next = q->next;
    free(q);
    top->data--;
    return result;
}

element Top(Stack top){
    if(empty(top)){
        printf("empty Stack!\n");
        exit(1);
    }
    return top->next->data;
}

void clear(Stack top){
    Stack p = top->next;
    while(p != NULL){
        Stack temp = p;
        p = temp->next;
        free(temp); 
    }
    top->next = NULL;
    top->data = 0;
}

