/*
 =========================
 Name         : display.h
 Author       : Charles T.
 =========================
*/

#ifndef _DISPLAY_H_
#define _DISPLAY_H_

#include "structs.h"

void     display_tree(int, int, t_node *, int **, char *);
int     **get_level_trasversal(t_node **, int, int);

#endif