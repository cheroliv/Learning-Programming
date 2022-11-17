/*
 ===================================
 Name         : pointers_functions.c
 Author       : Charles T.
 ===================================
*/

#include <stdio.h>


static int  modify_v(int a)
{
  a <<= 1;
  return a;
}

static void modify_p(int *a)
{
  *a <<= 2;
}

int     main(void)
{
  int   a             = 16;
  int   (*fpv)(int)   = modify_v;
  void  (*fpp)(int *) = modify_p;

  printf("Value of a: %u\n", a);
  a = (*fpv)(a);
  printf("Value of a: %u\n", a);
  (*fpp)(&a);
  printf("Value of a: %u\n", a);
  return 0;
}
