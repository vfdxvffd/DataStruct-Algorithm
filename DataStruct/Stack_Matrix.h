#include<stdio.h>
#include<stdlib.h>
#define SIZE 10
typedef int element;
static int MAXSIZE = 100;

typedef struct MyStack {
	element *data;
	int top = -1;
}*Stack;

Stack init() {
	Stack stack = (struct MyStack*)malloc(sizeof(struct MyStack));
	if (stack == NULL) {
		printf("stack malloc fail!\n");
		return NULL;
	}
	stack->data = (element*)malloc(MAXSIZE*sizeof(element));
	if(stack->data == NULL){
	    printf("stack->data malloc fail!\n");
	    return NULL;
	}
	stack->top = -1;
	return stack;
}

void Push(Stack s, element data) {
	if (s->top + 1 >= MAXSIZE) {        //relloc
		MAXSIZE += SIZE;
		s->data = (element*)realloc(s->data, MAXSIZE * sizeof(element));
		if (s->data == NULL) {
			printf("In Push function:realloc fail!\n");
			exit(1);
		}
	}
	s->data[++s->top] = data;
}

bool empty(Stack s) {
	return s->top == -1;
}

element Pop(Stack s) {
	if (empty(s)) {
		printf("fail to pop,Empty stack!\n");
		exit(1);
	}
	return s->data[s->top--];
}

element Top(Stack s) {
	if (empty(s)) {
		printf("fail to pop,Empty stack!\n");
		exit(1);
	}
	return s->data[s->top];
}

void clear(Stack s) {
	MAXSIZE = 100;
	s->data = (element*)realloc(s->data, MAXSIZE * sizeof(element));
	if (s->data == NULL) {
		printf("In clear function:realloc fail!\n");
		exit(1);
	}
	s->top = -1;
}