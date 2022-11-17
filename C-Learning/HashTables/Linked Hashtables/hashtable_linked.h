/*
 =================================
 Name         : hashtable_linked.h
 Author       : Charles T.
 =================================
*/

#ifndef _HASHTABLE_LINKED_H_
#define _HASHTABLE_LINKED_H_

#include "structs.h"


void  init_hashtable(hash_t *, unsigned int);
void  insert_htl(hash_t *, t_player *, unsigned int (*f)(const char *, unsigned int));
int   search_htl(hash_t *, char *, unsigned int (*f)(const char *, unsigned int));
void  display_htl(hash_t *, unsigned int);

#endif
