/*
 ========================
 Name        : game.c
 Author      : Charles T.
 ========================
*/

#include "game.h"
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

static void init_borders(Game *game, int width, int height)
{
  game->right_limit   = width - 1;
  game->bottom_limit  = height - 1;
}

static void init_food(Game *game, int x, int y)
{
  game->food.x  = x;
  game->food.y  = y;
}

static void init_snake(Game *game, int x, int y)
{
  game->snake[0].x  = x;
  game->snake[0].y  = y;
  
  for (int i = 1; i < game->length; i++) {
    y++;
    game->snake[i].x = x;
    game->snake[i].y = y;
  }
}

static void update_snake(Game *game, WINDOW *win)
{
  game->tail.x = game->snake[game->length - 1].x;
  game->tail.y = game->snake[game->length - 1].y;
  
  for (int i = game->length - 1; i > 0; i--) {
    game->snake[i].x = game->snake[i - 1].x;
    game->snake[i].y = game->snake[i - 1].y;
  }

  if (game->direction == NORTH) {
    game->snake[0].x--;
  } else if (game->direction == WEST) {
    game->snake[0].y--;
  } else if (game->direction == SOUTH) {
    game->snake[0].x++;
  } else if (game->direction == EAST) {
    game->snake[0].y++;
  }
}

static void get_inputs(Game *game, WINDOW *win)
{
  int       ch = wgetch(win);

  switch (ch)
    {
    case KEY_UP:
      game->direction = NORTH;
      break;
    case KEY_RIGHT:
      game->direction = EAST;
      break;
    case KEY_DOWN:
      game->direction = SOUTH;
      break;
    case KEY_LEFT:
      game->direction = WEST;
      break;
    case 'q':
      game->life = false;
      break;
    default:
      break;
    }
}

static void generate_food(Game *game)
{
  int array_x[ROWS]     = { [0 ... ROWS - 1] = -1 };
  int array_y[COLUMNS]  = { [0 ... COLUMNS - 1] = -1 };
  int row               = 1 + (rand() % (ROWS - 2));
  int col               = 0;

  for (int i = 0; i < game->length; i++) {
    array_x[game->snake[i].x] = game->snake[i].x;
    array_y[game->snake[i].y] = game->snake[i].y;
  }
  
  if (array_x[row] == -1) {
    col = 1 + (rand() % (COLUMNS - 2));
    game->food.x = row;
    game->food.y = col;
  } else {
    for (col = 0; col < COLUMNS; col++) {
      if (array_y[col] != -1) break;
    }
    game->food.x = row;
    game->food.y = col - 1;
  }
}

static void eat_food(Game *game)
{
  if (game->snake[0].x == game->food.x && game->snake[0].y == game->food.y) {
    game->snake[game->length].x = game->tail.x;
    game->snake[game->length].y = game->tail.y;
    game->length++;
    generate_food(game);
  }
}

static void check_endgame(Game *game, WINDOW *win)
{
  if (game->snake[0].y <= 0 || game->snake[0].x <= 0) {
    game->life = false;
  } else if (game->snake[0].y >= game->right_limit || game->snake[0].x >= game->bottom_limit) {
    game->life = false;
  }
}

static void render(Game *game, WINDOW *win)
{
  werase(win);
  box(win, 0, 0);
  mvwprintw(win, game->food.x, game->food.y, " ");
  for (int i = 0; i < game->length; i++) {
    mvwprintw(win, game->snake[i].x, game->snake[i].y, " ");
  }
  wrefresh(win);
}

void        run(Game *game, WINDOW *win, long time)
{
  while (game->life) {
    get_inputs(game, win);
    update_snake(game, win);
    eat_food(game);
    render(game, win);
    check_endgame(game, win);
    usleep(time);
  }
}

void        ctor_game(Game *game, int length, int width, int height)
{
  srand(time(NULL));
  game->life = true;
  game->direction = WEST;
  game->length = length;
  init_borders(game, width, height);
  init_food(game, 10, 10);
  init_snake(game, 10, 50);
}
