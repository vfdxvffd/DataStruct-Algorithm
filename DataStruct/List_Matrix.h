#include<stdio.h>
#include<stdlib.h>
#define SIZE 10
typedef int element;
static int MAXSIZE = 101;

typedef struct MyList{
    element* data;
    int size;
}*List;

List list(){
    List l = (struct MyList*)malloc(sizeof(struct MyList));
    if(l == NULL){
        printf("malloc fail!\n");
        exit(1);
    }
    l->data = (element*)malloc(MAXSIZE*sizeof(element));
    l->size = 0;
    return l;
}

bool empty(List l){
    return l->size == 0;
}

bool Insert(List l,int index, element data){
    if(index <= 0 || index-1 > l->size){
        printf("Error on where you want to insert! --->%d\n",index);
        return false;
    }
    if(l->size >= MAXSIZE-1){
        MAXSIZE += SIZE;
        l->data = (element*)realloc(l->data,MAXSIZE*sizeof(element));
        if(l->data == NULL){
            printf("in function Insert:fail realloc!\n");
            exit(1);
        }
    }
    int i;
    for(i = l->size+1; i>index; i--){
        l->data[i] = l->data[i-1];
    }
    if(i == index){
        l->data[index] = data;
    }else{
        printf("there is a bug!\n");
        exit(1);
    }
    l->size++;
}

element Getelement(List l,int index){
    if(index <= 0 || index > l->size){
        printf("Error on where you want to get! --->%d\n",index);
        exit(1);
    }
    return l->data[index];
}

element Delete(List l, int index){
    if(index <= 0 || index > l->size){
        printf("Error on where you want to delete! --->%d\n",index);
        exit(1);
    }
    element result = l->data[index];
    for(int i = index; i < l->size; i++){
        l->data[i] = l->data[i+1];
    }
    l->size--;
    return result;
}

void Reset(List l, int index, element newdata){
    if(index <= 0 || index > l->size){
        printf("Error on where you want to reset! --->%d\n",index);
        exit(1);
    }
    l->data[index] = newdata;
}