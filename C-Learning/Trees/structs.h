/*
 =========================
 Name         : structs.h
 Author       : Charles T.
 =========================
*/

#ifndef _STRUCTS_H_
#define _STRUCTS_H_

typedef struct      s_node
{
    int             elem;
    struct s_node   *left;
    struct s_node   *right;
}                   t_node;

#endif