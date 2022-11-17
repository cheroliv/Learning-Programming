/*
 ================================
 Name         : pointers_params.c
 Author       : Charles T.
 ================================
*/

#include <stdio.h>

#define RED     "\033[1;31m"
#define TGREEN  "\033[2;33m"
#define BLUE    "\033[1;34m"
#define PINK    "\033[1;35m"
#define TCYAN   "\033[2;36m"
#define EMPTY   "\033[0m"


static void modify_v(int a)
{
  printf("Value of argument a in modify_v: %s%20d%s\n", RED, a, EMPTY);
  printf("Address of argument a in modify_v: %s%18p%s\n", TCYAN, &a, EMPTY);
  a = 32;
}

static void modify_p(int *a)
{
  printf("Value of argument a in modify_p: %s%20d%s\n", BLUE, *a, EMPTY);
  printf("Address of argument a in modify_p: %s%18p%s\n", TGREEN, &a, EMPTY);
  *a = 32;
}

int   main(void)
{
  int a = 16;

  modify_v(a);
  printf("Value of variable a after call to modify_v: %s%9d%s\n\n", RED, a, EMPTY);
  modify_p(&a);
  printf("Value of variable a after call to modify_p: %s%9d%s\n\n", RED, a, EMPTY);
  return 0;
}
