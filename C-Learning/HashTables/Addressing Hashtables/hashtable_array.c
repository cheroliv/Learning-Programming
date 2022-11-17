/*
 ================================
 Name         : hashtable_array.c
 Author       : Charles T.
 ================================
*/

#include "structs.h"
#include <stdio.h>
#include <string.h>


void                display_hta(t_player *ht, unsigned int size)
{
    for (int i = 0; i < size; i++) {
        printf("%s[[%-3d]]->%s(%s, %d)%s\n", PINK, i, BLUE, ht[i].name, ht[i].elo, EMPTY);
    }
}

void                insert_hta(t_player *ht, t_player *player, unsigned int size, unsigned int (*f[])(const char *, unsigned int))
{
    unsigned int    h1 = f[0](player->name, strlen(player->name)) % size;
    unsigned int    h2 = 0;

    if (ht[h1].elo == 0) {
        ht[h1] = *player;
    } else {
      h2 = f[1](player->name, strlen(player->name)) % size;
      for (unsigned int i = 1; i <= size; i++) {
        unsigned int index = (h1 +  i * h2) % size;
        if (ht[index].elo == 0) {
          ht[index] = *player;
          break;
        }
      }
    }
}

int                 search_hta(t_player *ht, unsigned int size, char *key, unsigned int (*f[])(const char *, unsigned int))
{
    int             elo = 0;
    unsigned int    h1  = f[0](key, strlen(key)) % size;
    unsigned int    h2  = 0;

    if (strncmp(ht[h1].name, key, strlen(key)) == 0) {
        elo = ht[h1].elo;
    } else {
        h2 = f[1](key, strlen(key)) % size;
        for (unsigned int i = 1; i <= size; i++) {
            unsigned int index = (h1 +  i * h2) % size;
            if(strncmp(ht[index].name, key, strlen(key)) == 0) {
                elo = ht[index].elo;
                break;
            }
        }
    }
  return elo;
}
