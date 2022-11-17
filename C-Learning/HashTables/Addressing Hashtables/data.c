/*
 =========================
 Name         : data.c
 Author       : Charles T.
 =========================
*/

#include "structs.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


void      get_data(char *filename, t_player *players, size_t nb)
{
  FILE    *file   = NULL;
  char    *token  = NULL;

  if ((file = fopen(filename, "r")) == NULL) {
    fprintf(stderr, "Error args.\n");
    exit(EXIT_FAILURE);
  }
  for (int i = 0; i < nb; i++) {
    char line[SIZE_LINE] = { 0 };
    fgets(line, SIZE_LINE, file);
    token = strtok(line, ",");
    strncpy(players[i].name, token, strlen(token));
    token = strtok(NULL, ",");
    players[i].elo = strtol(token, NULL, 10);
    token = NULL;
  }
}
