/*
 ============================
 Name         : stack_array.c
 Author       : Charles T.
 ============================
*/

#include "stack_array.h"
#include <stdio.h>
#include <stdlib.h>

static void display(t_node *stack) {
  for (int i = nb_elements - 1; i >= 0; i--) {
    printf("%s\n", (char *) stack[i].data);
  }
}

int         main(int argc, char *argv[])
{
  t_node  stack[8];
  t_node  elem;
  
  for (int i = 1; i < argc; i++) {
    elem.data = argv[i];
    PUSH_STACK_ARRAY(stack, elem);
  }
  display(stack);
  puts("\n ---------- \n");
  POP_STACK_ARRAY(stack);
  POP_STACK_ARRAY(stack);
  display(stack);
  puts("\n ---------- \n");
  elem.data = "E";
  PUSH_STACK_ARRAY(stack, elem);
  display(stack);
  return 0;
}
