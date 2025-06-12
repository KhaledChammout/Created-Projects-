#include <stdio.h> 
#include <stdlib.h>
#define C(x)((x) % 2)
int main (int argc, char *argv[]){
   int  x;
    char *str = argv[1];
    x = atoi(str);
    int num = C(x);
    if (num == 0){
        printf("number is even");
    } else{printf("number is odd");}
    return 0;
} 