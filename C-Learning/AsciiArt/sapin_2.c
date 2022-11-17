/*
 =========================
 Name         : sapin_2.c
 Author       : Charles T.
 =========================
*/

#include <stdio.h>
#include <stdlib.h>


int                 main(int argc, char *argv[])
{
    int height      = strtoul(argv[1], NULL, 10);
    int stage       = 4;
    int base        = stage * height + 3;
    int nb_spaces   = base >> 1;
    int nb_stars    = 1;
    int width       = 0;
    int pos         = 0;

    putchar('\n');
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < stage; j++) {
            for (int k = 0; k < nb_spaces; k++)
                putchar(' ');
            for (int l = 0; l < nb_stars; l++)
                putchar('*');
            putchar('\n');
            nb_spaces--;
            nb_stars += 2;
        }
        nb_spaces += (stage - 1);
        stage++;
        nb_stars -= 2;
        nb_stars >>= 1;
    }
    width = (height << 1) - 1;
    pos = (base >> 1) - width + height;
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < pos; j++)
            putchar(' ');
        for (int k = 0; k < width; k++)
            putchar('|');
        putchar('\n');
    }
    putchar('\n');
    return 0;
}