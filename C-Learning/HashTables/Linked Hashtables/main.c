/*
 =========================
 Name         : main.c
 Author       : Charles T.
 =========================
*/

#include "data.h"
#include "hash_functions.h"
#include "hashtable_linked.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int           main(int argc, char *argv[])
{
  int         size                = strtoul(argv[1], NULL, 10);
  int         nb                  = strtoul(argv[2], NULL, 10);
  t_player    players[NB_RECORDS] = { 0 };
  t_player    *player             = { 0 };
  hash_t      ht_linked           = { 0 };

  get_data(argv[NB_ARGS], players, nb);
  init_hashtable(&ht_linked, size);
  display_htl(&ht_linked, size);

  for (int i = 0; i < nb; i++) {
    player = calloc(1, sizeof(t_player));
    memset(player->name, 0, 25);
    strcpy(player->name, players[i].name);
    player->elo = players[i].elo;
    player->next = NULL;
    insert_htl(&ht_linked, player, elf_hash);
  }

  printf("--------------------\n");
  display_htl(&ht_linked, size);
  printf("--------------------\n");

  int         elo = 0;

  printf((elo = search_htl(&ht_linked, "Carlsen", elf_hash)) != 0 ? "%-25s%d\n" : "Player not found", "Carlsen", elo);
  printf((elo = search_htl(&ht_linked, "Anand", elf_hash)) != 0 ? "%-25s%d\n" : "Player not found", "Anand", elo);
  printf((elo = search_htl(&ht_linked, "Nakamura", elf_hash)) != 0 ? "%-25s%d\n" : "Player not found", "Nakamura", elo);
  printf((elo = search_htl(&ht_linked, "Caruana", elf_hash)) != 0 ? "%-25s%d\n" : "Player not found", "Caruana", elo);
  return 0;
}
