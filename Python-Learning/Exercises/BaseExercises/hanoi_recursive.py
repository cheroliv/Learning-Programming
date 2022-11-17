'''
Exercise    : Hanoi Towers
Author      : Charles T.
'''

import sys
from timeit import timeit

setup = """
from __main__ import hanoi
from __main__ import nb_disks
"""

nb_moves = 0

def hanoi(nb_disks, start, end, tmp):
    global nb_moves
    if nb_disks > 0:
        nb_moves += 1
        hanoi(nb_disks - 1, start, tmp, end)
        print(nb_disks, "\t", start, " -> ", end, " ", tmp)
        hanoi(nb_disks - 1, tmp, end, start)


if __name__ == '__main__':
    nb_disks = int(sys.argv[1])
    hanoi(nb_disks, 1, 3, 2)
    #print("Hanoi Towers:\t {0:.3f}".format(timeit('hanoi(nb_disks, 1, 3, 2)', setup=setup, number=1)))
    print(nb_moves)
