/*
 =================================
 Name         : hashtable_linked.c
 Author       : Charles T.
 =================================
*/

#include "hashtable_linked.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void            init_hashtable(hash_t *ht, unsigned int size)
{
  ht->size = size;
  ht->keys = calloc(size, sizeof(t_player *));
}

void            insert_htl(hash_t *ht, t_player *player, unsigned int (*f)(const char *, unsigned int))
{
  t_player      *tmp = NULL;
  unsigned int  code = (*f)(player->name, strlen(player->name)) % ht->size;

  tmp = ht->keys[code];
  if (tmp == NULL) {
    ht->keys[code] = player;
  } else {
    player->next = tmp;
    ht->keys[code] = player;
  }
}

int             search_htl(hash_t *ht, char *key, unsigned int (*f)(const char *, unsigned int))
{
  int           elo  = -1;
  unsigned int  code    = (*f)(key, strlen(key)) % ht->size;
  t_player      *player = ht->keys[code];

  while (player != NULL) {
    if(strncmp(player->name, key, strlen(key)) == 0) {
      elo = player->elo;
    }
    player = player->next;
  }
  return elo;
}

void            display_htl(hash_t *ht, unsigned int size)
{
  t_player      *player = NULL;

  for (int i = 0; i < size; i++) {
    player = ht->keys[i];
    printf("%s[[%-3d]]%s", PINK, i, EMPTY);
    while (player != NULL) {
      printf("%s->", BLUE);
      printf("(%s, %d)%s", player->name, player->elo, EMPTY);
      player = player->next;
    }
    putchar('\n');
  }
}
