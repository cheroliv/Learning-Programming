/*
 ================================
 Name         : hashtable_array.h
 Author       : Charles T.
 ================================
*/

#ifndef _HASHTABLE_ARRAY_H_
#define _HASHTABLE_ARRAY_H_

#include "structs.h"

void  display_hta(t_player *, unsigned int);
void  insert_hta(t_player *, t_player *, unsigned int, unsigned int (*f[])(const char *, unsigned int));
int   search_hta(t_player *, unsigned int, char *, unsigned int (*f[])(const char *, unsigned int));

#endif
