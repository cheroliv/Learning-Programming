/*
 ==============================
 Name           : queue_array.h
 Author         : Charles T.
 ==============================
*/

#ifndef _QUEUE_ARRAY_H_
#define _QUEUE_ARRAY_H_

typedef struct  s_node {
  void          *data;
}               t_node;

int head        = 0;
int tail        = 0;
int nb_elements = 0;

static inline int     is_empty() {
  return nb_elements == 0;
}

static inline void    push(t_node *queue, t_node elem, int size) {
  if (nb_elements < size) {
    queue[head++] = elem;
    nb_elements++;
  }
  head = (head >= size) ? 0 : head;
}

static inline t_node  pop(t_node *queue, int size) {
  t_node  elem;

  if (nb_elements > 0) {
    queue[tail].data = "X";
    elem = queue[tail++];
    nb_elements--;
  }
  tail = (tail >= size) ? 0 : tail;
  return elem;
}

#define PUSH_QUEUE_ARRAY(queue, elem, size) push(queue, elem, size)
#define POP_QUEUE_ARRAY(queue, size)        pop(queue, size)

#endif
