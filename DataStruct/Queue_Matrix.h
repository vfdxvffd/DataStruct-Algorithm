#include<stdio.h>
#include<stdlib.h>
#define SIZE 10
typedef int element;
static int MAXSIZE = 100;

typedef struct Q{
    element* data;
    int front;
    int rear;
}*Queue;

Queue queue(){
    Queue q = (struct Q*)malloc(sizeof(struct Q));
    if(q == NULL){
        printf("fail malloc!\n");
        exit(1);
    }
    q->data = (element*)malloc(MAXSIZE*sizeof(element));
    if(q->data == NULL){
        printf("fail malloc!\n");
        exit(1);
    }
    q->front = q->rear = -1;
    return q;
}

bool empty(Queue q){
    return q->rear == q->front;
}

void Push(Queue q, element data){
    if(q->rear > MAXSIZE -1){
        MAXSIZE += SIZE;
        q->data = (element*)realloc(q->data,MAXSIZE*sizeof(element));
        //q->data = (element*)realloc(&q->data[q->front],MAXSIZE*sizeof(element));
        if(q->data == NULL){
            printf("In Push function:realloc fail!\n");
			exit(1);
        }
    }
    q->data[++q->rear] = data;
}

element Pop(Queue q){
    if(empty(q)){
        printf("empty queue!\n");
        exit(1);
    }
    return q->data[++q->front];
}

element Top(Queue q){
    if(empty(q)){
        printf("empty queue!\n");
        exit(1);
    }
    return q->data[q->front+1];
}

void clear(Queue q){
    MAXSIZE = 100;
    q->data = (element*)realloc(q->data, MAXSIZE * sizeof(element));
	if (q->data == NULL) {
		printf("In clear function:realloc fail!\n");
		exit(1);
	}
    q->front = q->rear = -1;
}