/*
 =============================
 Name           : queue_list.h
 Author         : Charles T.
 =============================
*/

#ifndef _QUEUE_LIST_H_
#define _QUEUE_LIST_H_

#include <stddef.h>

typedef struct  s_node {
  void          *data;
  struct s_node *next;
}               t_node;

t_node  *last_elem = NULL;

static inline int     is_empty(t_node *queue) {
  return queue == NULL;
}

static inline void    push(t_node **queue, t_node *elem) {
  if (*queue == NULL) {
    *queue = elem;
    last_elem = elem;
  } else {
    last_elem->next = elem;
    last_elem = elem;
  }
  last_elem->next = NULL;
}

static inline t_node  *pop(t_node **queue) {
  t_node  *elem = *queue;

  *queue = elem->next;
  return elem;
}

#define PUSH_QUEUE_LIST(queue, elem)  push(queue, elem)
#define POP_QUEUE_LIST(queue)         pop(queue)

#endif
