/*
 =============================
 Name         : extended_asm.c
 Author       : Charles T.
 =============================
*/

#include <stdio.h>

int     main(void)
{
  int   sum = 0;
  int   lim = 100;

  __asm__ __volatile__(".intel_syntax noprefix;"
                       "xor ecx, ecx;"
                       "loop:"
                       "add eax, ecx;"
                       "inc ecx;"
                       "cmp ecx, %2;"
                       "jle loop;"
                       :"=a"(sum)
                       :"a"(sum), "r"(lim)
                       );
  printf("%d\n", sum);
  return 0;
}