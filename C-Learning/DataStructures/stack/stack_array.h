/*
 ==============================
 Name           : stack_array.h
 Author         : Charles T.
 ==============================
*/

#ifndef _STACK_ARRAY_H_
#define _STACK_ARRAY_H_

typedef struct  s_node {
  void          *data;
}               t_node;

int nb_elements = 0;

static inline int     is_empty() {
  return nb_elements == 0;
}

static inline void    push(t_node *stack, t_node elem) {
  stack[nb_elements++] = elem;  
}

static inline t_node  pop(t_node *stack) {
  return stack[--nb_elements];  
}

#define PUSH_STACK_ARRAY(stack, elem) push(stack, elem)
#define POP_STACK_ARRAY(stack)        pop(stack)

#endif
