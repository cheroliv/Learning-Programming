/*
 ================================
 Name         : pointers_arrays.c
 Author       : Charles T.
 ================================
*/

#include <stdio.h>


void  f(char t[], int size)
{
  //t[3] = 'A';
  *(t + 3) = 'A';
  for (int i = 0; i < size; i++) {
      printf("%c\t", t[i]);
  }
  puts("");
}

int     main(void)
{
  char  t[]       = "abcd";
  char  *p        = t;
  char  (*pp)[5]  = &t;

  f(t, 4);
  f(&t[0], 4);
  f(p, 4);
  f(pp[0], 4);
  printf("%p %p %p %p\n", t, &t[0], p, pp);
  printf("%p %p %p %p\n", t + 1, &t[0] + 1, p + 1, pp + 1);
  printf("%ld %ld %ld\n", sizeof(t), sizeof(p), sizeof(pp));
  printf("%ld %ld %ld\n", sizeof(*t), sizeof(*p), sizeof(*pp));
  p = t;
  //t = p;
  //p++;
  //t++;
  printf("%c\n", t[3]);
  printf("%c\n", p[3]);
  return 0;
}
