/*
 =========================
 Name         : display.c
 Author       : Charles T.
 =========================
*/

#include "constants.h"
#include "display.h"
#include "utilities.h"
#include <math.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int head        = 0;
int tail        = 0;
int nb_elements = 0;

void        push(t_node *queue, t_node *elem, int size) {
    if (nb_elements < size) {
        if (elem != NULL) {
            queue[head++] = *elem;
        } else {
            queue[head++].elem  = -1;
        }
        nb_elements++;
    }
    head = (head >= size) ? 0 : head;
}

t_node      pop(t_node *queue, int size) {
    t_node  elem;

    if (nb_elements > 0) {
        elem = queue[tail++];
        nb_elements--;
    }
    tail = (tail >= size) ? 0 : tail;
    return elem;
}

void        display_queue(t_node *queue, int size)
{
    for (int i = 0; i < size; i++) {
        printf("%d ", queue[i].elem);
    }
}

void        display_level(int *level, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d ", level[i]);
    }
}

void        display_matrix_h(int heigth, int length, int **matrix) {
    for (int i = 0; i < heigth; i++) {
        for (int j = 0; j < length; j++) {
            //printf("%s%d %s", BLUE, matrix[i][j], EMPTY);
            if (matrix[i][j] != 0) {
                printf("%s%d%s", BLUE, matrix[i][j], EMPTY);
            } else {
                printf("%s%s%s", EMPTY, " ", EMPTY);
            }
        }
        printf("\n\n");
    }
}

void        display_matrix_v(int heigth, int length, int **matrix) {
    for (int i = 0; i < heigth; i++) {
        for (int j = 0; j < length; j++) {
            if (matrix[i][j] != 0) {
                printf("%s|%d|%s", BLUE, matrix[i][j], EMPTY);
            } else {
                printf("%s%s%s", EMPTY, "\t", EMPTY);
            }
        }
        printf("\n");
    }
}

int         get_coordinates(int elem, t_node *tree, int n, int in_off) {
    if (tree->elem == elem) {
        return n;
    } else if (elem < tree->elem) {
        return get_coordinates(elem, tree->left, n - (in_off + 1), in_off / 2);
    } else {
        return get_coordinates(elem, tree->right, n + (in_off + 1), in_off / 2);
    }
}

void        display_tree(int depth, int length, t_node *tree, int **matrix, char *mode) {
    int     **m_h       = allocate_2d(depth, length);
    int     **m_v       = allocate_2d(length, depth);
    int     init_offset = pow(2, (depth * 2 - 1) / 2) - 1;
    int     new_offset  = init_offset;

    m_h[0][init_offset] = tree->elem;
    for (int i = 1; i < depth; i++) {
        for (int j = 0; matrix[i][j] != 0 && j < length; j++) {
            int elem    = matrix[i][j];
            int x       = get_coordinates(elem, tree, init_offset, new_offset / 2);
            m_h[i][x]   = elem;
        }
    }
    for (int i = 0; i < depth; i++) {
        for (int j = 0; j < length; j++) {
            m_v[j][i] = m_h[i][j];
        }
    }
    if (strncmp(mode, "h", 1) == 0) {
        display_matrix_h(depth, length, m_h);
    } else {
        display_matrix_v(length, depth, m_v);
    }
}

int         **get_level_trasversal(t_node **root, int nb_nodes, int depth)
{
    t_node  *queue      = calloc(nb_nodes, sizeof(t_node));
    t_node  *node       = calloc(1, sizeof(t_node));
    int     length      = pow(2, depth) - 1;
    int     **matrix    = allocate_2d(depth, length);
    int     row         = 0;
    int     col         = 0;

    matrix[0][0] = (*root)->elem;
    row++;
    col = 0;
    push(queue, *root, nb_nodes);
    push(queue, NULL, nb_nodes);
    for (int i = 0; i < nb_nodes; i++) {
        *node = queue[tail];
        pop(queue, nb_nodes);
        if (node->elem == -1) {
           push(queue, NULL, nb_nodes);
           row++;
           col = 0;
        } else {
            if(node->left != NULL) {
                push(queue, node->left, nb_nodes);
                matrix[row][col++] = node->left->elem;
            }
            if(node->right != NULL) {
                push(queue, node->right, nb_nodes);
                matrix[row][col++] = node->right->elem;
            }
        }
    }
    return matrix;
}