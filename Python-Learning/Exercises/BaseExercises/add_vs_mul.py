'''
Exercise    : Multiplication and addition
Author      : Charles T.
'''
import random
import sys
from timeit import timeit

setup = """
from functools import reduce
from __main__ import list_size
from __main__ import A
from __main__ import B
from __main__ import nb
"""

if __name__ == '__main__':
    inf_bound = int(sys.argv[1])
    upper_bound = int(sys.argv[2])
    list_size = int(sys.argv[3])
    nb = int(sys.argv[4])

    A = random.sample(range(inf_bound, upper_bound), list_size)
    B = A[:]
    print("Addition:       ", "{0:.3f}".format(timeit('reduce(lambda x, y: x + y, A)', setup=setup, number=nb)))
    ###
    print("Multiplication: ", "{0:.3f}".format(timeit('reduce(lambda x, y: x * y, B)', setup=setup, number=nb)))