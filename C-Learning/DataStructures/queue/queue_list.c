/*
 =============================
 Name           : queue_list.c
 Author         : Charles T.
 =============================
*/

#include "queue_list.h"
#include <stdio.h>
#include <stdlib.h>

static void display(t_node *queue) {
  t_node    *tmp = queue;
  
  while (tmp != NULL) {
    printf("%s ", (char *) tmp->data);
    tmp = tmp->next;
  }
}

int         main(int argc, char *argv[])
{
  t_node    *queue = NULL;
  
  for (int i = 1; i < argc; i++) {
    t_node  *elem = malloc(sizeof(t_node));
    elem->data = argv[i];
    PUSH_QUEUE_LIST(&queue, elem);
  }
  display(queue);
  puts("\n\n ---------- \n");
  POP_QUEUE_LIST(&queue);
  POP_QUEUE_LIST(&queue);
  display(queue);
  puts("\n\n ---------- \n");
  t_node  *e1 = malloc(sizeof(t_node));
  e1->data = "E";
  PUSH_QUEUE_LIST(&queue, e1);
  t_node  *e2 = malloc(sizeof(t_node));
  e2->data = "F";
  PUSH_QUEUE_LIST(&queue, e2);
  display(queue);
  puts("\n\n ---------- \n");
  POP_QUEUE_LIST(&queue);
  POP_QUEUE_LIST(&queue);
  display(queue);
  puts("\n\n ---------- \n");
  t_node  *e3 = malloc(sizeof(t_node));
  e3->data = "G";
  PUSH_QUEUE_LIST(&queue, e3);
  display(queue);
  puts("\n\n ---------- \n");
  POP_QUEUE_LIST(&queue);
  POP_QUEUE_LIST(&queue);
  display(queue);
  puts("\n\n ---------- \n");
  POP_QUEUE_LIST(&queue);
  display(queue);
  putchar('\n');
  return 0;
}
