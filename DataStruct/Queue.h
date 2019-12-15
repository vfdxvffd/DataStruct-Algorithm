#include<stdio.h>
#include<stdlib.h>
typedef int element;

typedef struct Node{
    element data;
    struct Node* next;
}*QNode;

typedef struct Q{
    struct Node* front;
    struct Node* rear;
    int size;
}*Queue;

Queue queue(){
    Queue q = (struct Q*)malloc(sizeof(struct Q));
    if(q == NULL){
        printf("malloc fail!\n");
        return NULL;
    }
    q->front = q->rear = NULL;
    q->size = 0;
    return q;
}

bool empty(Queue q){
    return q->front == NULL&&q->size == 0;
}

void Push(Queue q, element data){
    QNode newNode = (struct Node*)malloc(sizeof(struct Node));
    if(newNode == NULL){
        printf("Push:fail to malloc!\n");
        exit(1);
    }
    newNode->data = data;
    newNode->next = NULL;
    if(empty(q)){
        q->front = newNode;
        q->rear = newNode;
    }else{
        q->rear->next = newNode;
        q->rear = newNode;
    }
    q->size++;
}

element Pop(Queue q){
    if(empty(q)){
        printf("empty queue!\n");
        exit(1);
    }
    QNode temp = q->front;
    element result = temp->data;
    q->front = temp->next;
    free(temp);
    if(q->front == NULL){       //last Node
        q->rear = NULL;
    }
    q->size--;
    return result;
}

element Front(Queue q){
    if(empty(q)){
        printf("empty queue!\n");
        exit(1);
    }
    return q->front->data;
}

void clear(Queue q){
    while(q->front != NULL){
        QNode temp = q->front;
        q->front = temp->next;
        free(temp);
    }
    q->rear = NULL;
    q->size = 0;
}