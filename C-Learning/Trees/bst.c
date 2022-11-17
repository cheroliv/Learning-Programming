/*
 =========================
 Name         : bst.c
 Author       : Charles T.
 =========================
*/

#include "bst.h"
#include "constants.h"
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>

void        free_bst(t_node *root)
{
    if (root != NULL) {
        free_bst(root->left);
        free_bst(root->right);
        free(root);
    }
}

int         get_depth(t_node *node)
{
    if (node == NULL) {
        return 0;
    } else {
        int l = get_depth(node->left);
        int r = get_depth(node->right);
        return (l > r) ? l + 1 : r + 1;
    }
}

void        insert(t_node **root, int elem, predicat p)
{
    t_node  *node = calloc(1, sizeof(t_node));

    if (*root == NULL) {
        node->elem  = elem;
        *root       = node;
    } else if (p(elem, (*root)->elem) == true) {
        insert(&((*root)->left), elem, p);
    } else {
        insert(&((*root)->right), elem, p);
    }
}

void        infix(t_node *root, int (*f)(int))
{
    t_node  *node = root;

    if (node != NULL) {
        infix(node->left, f);
        printf("%s%-4d%s", BLUE, node->elem, EMPTY);
        if (f != NULL) (*f)(node->elem);
        infix(node->right, f);
    }
}

void        postfix(t_node *root, int (*f)(int))
{
    t_node  *node = root;

    if (node != NULL) {
        postfix(node->left, f);
        postfix(node->right, f);
        printf("%s%-4d%s", BLUE, node->elem, EMPTY);
        if (f != NULL) (*f)(node->elem);
    }
}

void        prefix(t_node *root, int (*f)(int))
{
    t_node  *node = root;

    if (node != NULL) {
        printf("%s%-4d%s", BLUE, node->elem, EMPTY);
        if (f != NULL) (*f)(node->elem);
        prefix(node->left, f);
        prefix(node->right, f);
    }
}

int         search(t_node **root, int elem)
{
    if((*root)->elem == elem) {
        return (*root)->elem;
    } else if (elem > (*root)->elem) {
        return search(&(*root)->right, elem);
    } else {
        return search(&(*root)->left, elem);
    }
}