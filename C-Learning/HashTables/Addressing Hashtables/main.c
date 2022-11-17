/*
 =========================
 Name         : main.c
 Author       : Charles T.
 =========================
*/

#include "data.h"
#include "hash_functions.h"
#include "hashtable_array.h"
#include <stdio.h>
#include <stdlib.h>


int           main(int argc, char *argv[])
{
  int         size                = strtoul(argv[1], NULL, 10);
  int         nb                  = strtoul(argv[2], NULL, 10);
  t_player    players[NB_RECORDS] = { 0 };

  get_data(argv[NB_ARGS], players, nb);

  t_player      *ht_array         = calloc(size, sizeof(t_player));
  unsigned int  (*f[2])(const char *, unsigned int) = { djb_hash, elf_hash };
  
  display_hta(ht_array, size);

  for (int i = 0; i < nb; i++) {
    insert_hta(ht_array, &players[i], size, f);
  }

  printf("--------------------\n");
  display_hta(ht_array, size);
  printf("--------------------\n");

  int         elo = 0;

  printf((elo = search_hta(ht_array, size, "Carlsen", f)) != 0 ? "%-25s%d\n" : "Player not found", "Carlsen", elo);
  printf((elo = search_hta(ht_array, size, "Anand", f)) != 0 ? "%-25s%d\n" : "Player not found", "Anand", elo);
  printf((elo = search_hta(ht_array, size, "Nakamura", f)) != 0 ? "%-25s%d\n" : "Player not found", "Nakamura", elo);
  printf((elo = search_hta(ht_array, size, "Caruana", f)) != 0 ? "%-25s%d\n" : "Player not found", "Caruana", elo);
  return 0;
}
