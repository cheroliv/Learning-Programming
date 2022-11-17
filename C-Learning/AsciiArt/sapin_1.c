/*
 =========================
 Name         : sapin_1.c
 Author       : Charles T.
 =========================
*/

#include <stdio.h>
#include <stdlib.h>

int                 main(int argc, char *argv[])
{
    int size        = strtoul(argv[1], NULL, 10);
    int nb_spaces   = 4;
    int nb_stars    = 1;
    int pos         = 3;

    putchar('\n');
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < nb_spaces; k++)
                putchar(' ');
            for (int l = 0; l < nb_stars; l++)
                putchar('*');
            putchar('\n');
            nb_spaces--;
            nb_stars += 2;
        }
        nb_spaces = 4;
        nb_stars = 1;
    }
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < pos; j++)
            putchar(' ');
        for (int k = 0; k < 3; k++)
            putchar('|');
        putchar('\n');
    }
    putchar('\n');
    return 0;
}