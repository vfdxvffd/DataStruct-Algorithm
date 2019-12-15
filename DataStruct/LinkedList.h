#include<stdio.h>
#include<stdlib.h>
typedef int element;

typedef struct Node{
    element data;      //数据域
    struct Node* next;  //指针域   
}LNode,*LinkedList;


/**
 * create a linkedlist
 * 调用格式: LinkedList list = List();
 * 需要判断: if(list == NULL)   {your code...} 
 */
LinkedList List(){
    LinkedList list = (struct Node*)malloc(sizeof(struct Node));   //Head Node
    if(list == NULL){
        printf("Fail to init!\n");
        return NULL;
    }
    list->data = 0;     //  indicate the length of the linkedlist
    list->next = NULL;  //  empty the next
}

/**
 * to judge if the linkedlist is empty
 * 调用格式：if(empty(list)){cout<<"it's empty"<<endl;} 
 */
bool empty(LinkedList list){
    if(list->next == NULL && list->data == 0){
        return true;
    }else if (list->next == NULL || list->data == 0){
        printf("There is a bug, come to repair it! quickly!\n");
        system("pause");
    }else{
        return false;       //not empty
    } 
}

/**
 * Insert a data into a linkedlist
 * 调用格式：Insert(list,index,data)
 * 判断：if(Insert(list,index,data)){your code...}
 */
bool Insert(LinkedList list, int index, element data){
    if(index <= 0 || index-1 > list->data){        //illegal postion 
        printf("Error on where you want to insert! --->%d\n",index);
        return false;
    }
    LinkedList p = list;
    while(--index && p != NULL){
        p = p->next;
    }
    
    if(p == NULL){      //I'm sure it won't happen! don't ask me why
        printf("Unknow Error!\n");
        return false;
    }
    LinkedList newNode = (struct Node*)malloc(sizeof(struct Node));
    if(newNode == NULL){
        printf("Error!\n");
        return false;
    }
    newNode->data = data;
    newNode->next = p->next;
    p->next = newNode;
    list->data++;
    return true;
}

/**
 * to get the data of a random index
 * 调用格式：element data = GetElement(list,index);
 */ 
element GetElement(LinkedList list, int index){
    if(index <= 0 || index > list->data){        //illegal postion 
        printf("Error on where you want to get! --->%d\n",index);
        exit(1);
    }
    LinkedList p = list;
    while(index-- && p != NULL){
        p = p->next;
    }
    if(index != -1){
        printf("Unknown Error:index = %d\n",index);
        exit(1);
    }
    return p->data;
}

/**
 * to delete the data of index
 * 调用格式：element data = Delete(list,index);
 */ 
element Delete(LinkedList list, int index){
    if(index <= 0 || index > list->data){        //illegal postion 
        printf("Error on where you want to delete! --->%d\n",index);
        exit(1);
    }
    LinkedList p = list;
    while(--index && p != NULL){
        p = p->next;
    }
    
    if(p == NULL){      //I'm sure it won't happen! don't ask me why
        printf("Unknow Error!\n");
        exit(1);
    }
    LinkedList q = p->next;    //to delete q
    element result = q->data;
    p->next = q->next;
    free(q);        
    list->data--;       
    return result;
}

/**
 * to reset the data of index using newdata
 * 调用格式：reset(list,index,newdata);
 * return true of false
 */ 
bool Reset(LinkedList list, int index, element newdata){
    Delete(list,index);
    return Insert(list,index,newdata);
}