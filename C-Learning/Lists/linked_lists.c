/*
 =============================
 Name         : linked_lists.c
 Author       : Charles T.
 =============================
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct      s_node
{
    void            *elem;
    struct s_node   *next;
}                   t_node;


static  void    show_list(t_node *list)
{
    t_node      *node = list;

    while (node != NULL) {
        printf("%s ", (char *)(node->elem));
        node = node->next;
    }
}

static  void    add_node(t_node **list, void *data)
{
    t_node      *new_node = NULL;

    if ((new_node = (t_node *) malloc(sizeof(t_node))) == NULL) {
        fprintf(stderr, "Malloc failed, add.\n");
        exit(EXIT_FAILURE);
    }
    new_node->elem = data;
    new_node->next = *list;
    *list = new_node;
}

static  void    remove_node(t_node **list, t_node *to_remove)
{
    t_node      **node = list;

    while (*node != to_remove) {
        node = &(*node)->next;
    }
    *node = to_remove->next;
    free(to_remove);
}

static  void    apply_on_list(t_node *list, int (*f)(void *))
{
    while (list != NULL) {
        printf("size of string %s: %d\n", (char *)(list->elem), (*f)((char *)(list->elem)));
        list = list->next;
    }
}

static  void    reverse_list_v1(t_node **list)
{
    t_node      *left = *list;
    t_node      *right = left->next;
    t_node      *next = right->next;

    right->next = left;
    left->next = NULL;
    while (next != NULL) {
        left = right;
        right = next;
        next = right->next;
        right->next = left;
    }
    *list = right;
}

static  void    reverse_list_v2(t_node **list)
{
    t_node      *left = *list;
    t_node      *new_link = NULL;
    t_node      *next = NULL;

    while (left != NULL) {
        next = left->next;
        left->next = new_link;
        new_link = left;
        left = next;
    }
    *list = new_link;
}

int         main(int argc, char *argv[])
{
    t_node  *list = NULL;
    t_node  *tmp = NULL;

    for (int i = 0; i < argc; i++) {
        add_node(&list, argv[i]);
    }
    show_list(list);
    putchar('\n');
    apply_on_list(list, (int (*)(void *))(strlen));
    remove_node(&list, list->next->next);
    show_list(list);
    putchar('\n');
    reverse_list_v1(&list);
    show_list(list);
    putchar('\n');
    reverse_list_v2(&list);
    show_list(list);
    putchar('\n');
    while (list != NULL) {
        tmp = list;
        list = list->next;
        free(tmp);
    }
    return 0;
}