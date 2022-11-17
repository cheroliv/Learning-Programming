/*
 ==========================
 Name         : utilities.c
 Author       : Charles T.
 ==========================
*/

#include <stdlib.h>


int     **allocate_2d(int heigth, int width)
{
    int **matrix = calloc(heigth, sizeof(int *));

    for (int i = 0; i < heigth; i++) {
        matrix[i] = calloc(width, sizeof(int));
    }

    return matrix;
}