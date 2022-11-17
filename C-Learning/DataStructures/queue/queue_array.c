/*
 ============================
 Name         : queue_array.c
 Author       : Charles T.
 ============================
*/

#include "queue.h"
#include <stdio.h>
#include <stdlib.h>

#define BLUE  "\033[1;34m"
#define EMPTY "\033[0m"


static void display(t_node *queue, int size) {
  for (int i = 0; i < size; i++) {
    printf("%s ", (char *) queue[i].data);
  }
}

int       main(int argc, char *argv[])
{
  t_node  queue[4];
  t_node  elem;
  
  for (int i = 1; i < argc; i++) {
    elem.data = argv[i];
    PUSH(queue, elem, 4);
  }
  display(queue, 4);
  printf("\t <--- %s ADD A B C D E %s\n", BLUE, EMPTY);
  
  POP(queue, 4);
  display(queue, 4);
  printf("\t <--- %s DEL 1 ELEMENT %s\n", BLUE, EMPTY);
  
  elem.data = "F";
  PUSH(queue, elem, 4);
  elem.data = "G";
  PUSH(queue, elem, 4);
  display(queue, 4);
  printf("\t <--- %s ADD F G %s\n", BLUE, EMPTY);
  
  POP(queue, 4);
  POP(queue, 4);
  display(queue, 4);
  printf("\t <--- %s DEL 2 ELEMENTS %s\n", BLUE, EMPTY);

  elem.data = "H";
  PUSH(queue, elem, 4);
  elem.data = "I";
  PUSH(queue, elem, 4);
  elem.data = "J";
  PUSH(queue, elem, 4);
  display(queue, 4);
  printf("\t <--- %s ADD H I J  %s\n", BLUE, EMPTY);
  
  POP(queue, 4);
  POP(queue, 4);
  POP(queue, 4);
  POP(queue, 4);
  display(queue, 4);
  printf("\t <--- %s DEL 4 ELEMENTS %s\n", BLUE, EMPTY);

  elem.data = "K";
  PUSH(queue, elem, 4);
  display(queue, 4);
  printf("\t <--- %s ADD K %s\n", BLUE, EMPTY);

  elem.data = "L";
  PUSH(queue, elem, 4);
  elem.data = "M";
  PUSH(queue, elem, 4);
  display(queue, 4);
  printf("\t <--- %s ADD L M %s\n", BLUE, EMPTY);

  POP(queue, 4);
  POP(queue, 4);
  display(queue, 4);
  printf("\t <--- %s DEL 2 ELEMENTS %s\n", BLUE, EMPTY);

  elem.data = "N";
  PUSH(queue, elem, 4);
  elem.data = "O";
  PUSH(queue, elem, 4);
  elem.data = "P";
  PUSH(queue, elem, 4);
  display(queue, 4);
  printf("\t <--- %s ADD N O P %s\n", BLUE, EMPTY);
  
  putchar('\n');
  return 0;
}
