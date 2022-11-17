/*
 =========================
 Name         : structs.h
 Author       : Charles T.
 =========================
*/

#ifndef _STRUCTS_H_
#define _STRUCTS_H_

#include "constants.h"


typedef struct  player {
  int           elo;
  char          name[SIZE_NAME];
}               t_player;

#endif
