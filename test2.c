#include<stdio.h>
#define C(x)((x) % 2)
int main(){
    int i;
    for ( i = 10; i<= 50; i++){
        if (C(i) == 0) 
        printf("%d\n" , i );
    }
    return 0;
}