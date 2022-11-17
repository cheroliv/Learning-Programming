'''
Exercise    : Sequential and dichotomous search
Author      : Charles T.
'''

import random
import sys
import time


def search_sequential(elements, to_find):
     i = 0

     while elements[i] != to_find: i += 1
     return i


def search_dichotomic(elements, beg, end, to_find):
    while beg <= end:
        middle = (beg + end) // 2
        if to_find == elements[middle]:
            return middle
        elif to_find < elements[middle]:
            end = middle - 1
        else:
            beg = middle + 1
    return None


if __name__ == '__main__':
    i = 0
    nb_tests = int(sys.argv[1])
    limit = int(sys.argv[2])
    size = int(sys.argv[3])
    A = random.sample(range(0, limit), size)
    A.sort()
    B = A[:]
    values_to_find = []
    [values_to_find.append(A[random.randint(0, size)]) for i in range(nb_tests)]

    s1 = time.time_ns()
    [search_sequential(A, values_to_find[i]) for i in range(nb_tests)]
    s2 = time.time_ns()
    print(s2 - s1)

    s3 = time.time_ns()
    [search_dichotomic(B, 0, size, values_to_find[i]) for i in range(nb_tests)]
    s4 = time.time_ns()
    print(s4 - s3)
