'''
Exercise    : compare bubbleSort and quickSort
Author      : Charles T.
'''
import random
import sys
from timeit import timeit

setup = """
from functools import reduce
from __main__ import bubbleSort
from __main__ import quickSort
from __main__ import A
from __main__ import B
from __main__ import nb
"""

def bubbleSort(elements):
    i = len(elements) - 1
    j = 0

    while i > 0:
        while j < i:
            if elements[j] > elements[j + 1]:
                elements[j], elements[j + 1] = elements[j + 1], elements[j]
            j += 1
        j = 0
        i -= 1


def partition(elements, left, right):
    i = left - 1
    j = right
    k = elements[right]

    while i < j:
        i += 1
        while elements[i] < k:
            i += 1
        j -= 1
       	while j > 0 and elements[j] > k:
            j -= 1
       	if i < j:
            elements[i], elements[j] = elements[j], elements[i]
    elements[i], elements[right] = elements[right], elements[i]
    return i            


def quickSort(elements, left, right):
	pivot = 0

	if left < right:
		pivot = partition(elements, left, right)
		quickSort(elements, left, pivot - 1)
		quickSort(elements, pivot + 1, right)


if __name__ == '__main__':
    inf_bound = int(sys.argv[1])
    upper_bound = int(sys.argv[2])
    list_size = int(sys.argv[3])
    nb = int(sys.argv[4])

    A = random.sample(range(inf_bound, upper_bound), list_size)
    B = A[:]
    #print("Before:      ", A)
    print("Bubble sort: ", "{0:.3f}".format(timeit('bubbleSort(A)', setup=setup, number=nb)), "\bs")
    #print("After:       ", A, "\n\n")
    #print("Before:      ", B)
    print("Quick  sort: ", "{0:.3f}".format(timeit('quickSort(B, 0, len(B) - 1)', setup=setup, number=nb)), "\bs")
    #print("After:       ", B)