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
  struct player *next;
}               t_player;

typedef struct  {
  unsigned int  size;
  t_player      **keys;
}               hash_t;

#endif
