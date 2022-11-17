/*
 =============================
 Name           : stack_list.h
 Author         : Charles T.
 =============================
*/

#ifndef _STACK_LIST_H_
#define _STACK_LIST_H_

#include <stddef.h>

typedef struct  s_node {
  void          *data;
  struct s_node *next;
}               t_node;

static inline int     is_empty(t_node *stack) {
  return stack == NULL;
}

static inline void    push(t_node **stack, t_node *elem) {
  elem->next = *stack;
  *stack = elem;
}

static inline t_node  *pop(t_node **stack) {
  t_node  *elem = *stack;

  *stack = elem->next;
  return elem;  
}

#define PUSH_STACK_LIST(stack, elem)  push(stack, elem)
#define POP_STACK_LIST(stack)         pop(stack)

#endif
