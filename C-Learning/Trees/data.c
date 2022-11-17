/*
 =========================
 Name         : data.c
 Author       : Charles T.
 =========================
*/

#include <stdlib.h>
#include <string.h>


int     count_nodes(char *elements)
{
    char    *token      = NULL;
    int     nb_nodes    = 0;

    token   = strtok(elements, " ");
    while(token != NULL) {
        nb_nodes++;
        token = strtok(NULL, " ");
    }
    return nb_nodes;
}

int      *get_data(char *elements, int size)
{
    char    *token  = NULL;
    int     *nodes  = NULL;
    int     i       = 0;

    nodes   = calloc(size, sizeof(int));
    token   = strtok(elements, " ");
    while(token != NULL) {
        nodes[i++] = strtoul(token, NULL, 10);
        token = strtok(NULL, " ");
    }
    return nodes;
}