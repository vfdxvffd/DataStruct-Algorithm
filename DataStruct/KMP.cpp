#include<stdio.h>
int* get_next(char son[], int len){
    int *next = new int[len];
    next[1] = 0;
    int j = 0, i = 1;
    while(i < len){
        if(j == 0 || son[i] == son[j]){
            i++;
            j++;
            if(son[i] == son[j]){
                next[i] = next[j];
            }else{
                next[i] = j;
            }
        }else{
            j = next[j];
        }
    }
    return next;
}

void Kmp(char far[], char son[], int farlen, int sonlen){
    int index = 1;
    int* next = get_next(son,sonlen);
    for(int i = 1; i <= farlen; i++){
        while(index <= sonlen)
        {
            if(far[i] == son[index]){
                index++;
                break;
            }else{
                index = next[index];
                if(index == 0){
                    index = 1;
                    break;
                }
            }
        }
        if(index == sonlen+1){
            printf("success!\n%d\n",i-sonlen+1);
            return;
        }
    }
    printf("failed!\n");
}

int main(){
    char son[8];
    scanf("%s",son+1);
    char far[10];
    scanf("%s",far+1);
    Kmp(far,son,9,7);    
}