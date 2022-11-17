/*
 =============================
 Name           : stack_list.c
 Author         : Charles T.
 =============================
*/

#include "stack_list.h"
#include <stdio.h>
#include <stdlib.h>

static void display(t_node *stack) {
  t_node  *tmp = stack;
  
  while (tmp != NULL) {
    printf("%s\n", (char *) tmp->data);
    tmp = tmp->next;
  }
}

int         main(int argc, char *argv[])
{
  t_node  *stack = NULL;
  
  for (int i = 1; i < argc; i++) {
    t_node  *elem = malloc(sizeof(t_node));
    elem->data = argv[i];
    PUSH_STACK_LIST(&stack, elem);
  }
  display(stack);
  puts("\n ---------- \n");
  POP_STACK_LIST(&stack);
  POP_STACK_LIST(&stack);
  display(stack);
  puts("\n ---------- \n");
  t_node  *elem = malloc(sizeof(t_node));
  elem->data = "E";
  PUSH_STACK_LIST(&stack, elem);
  display(stack);
  return 0;
}
