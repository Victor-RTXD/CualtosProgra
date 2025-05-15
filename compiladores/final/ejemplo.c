#include <stdio.h>

int main() {
    int x = 10;
    float y = 3.14;
    
    if (x > 5) {
        printf("X es mayor que 5\n");
    } else {
        printf("X es menor o igual que 5\n");
    }
    
    for (int i = 0; i < 10; i++) {
        x = x + i;
    }
    
    return 0;
}