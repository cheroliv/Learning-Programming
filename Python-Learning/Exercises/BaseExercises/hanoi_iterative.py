'''
Exercise    : Hanoi Towers
Author      : Charles T.
'''

import sys

class MyAction:
    def __init__(self, nb_disks, start, end, intermediate):
        self.nb_disks = nb_disks
        self.start = start
        self.end = end
        self.intermediate = intermediate

stack = []

def hanoi(nb_disks, start, end, intermediate):
    action = MyAction(nb_disks, start, end, intermediate)
    stack.append(action)

    while stack:
        action = stack.pop()
        nb_disks = action.nb_disks
        start = action.start
        end = action.end
        intermediate = action.intermediate
        if nb_disks == 1:
            print(start, " -> ", end)
        else:
            stack.append(MyAction(nb_disks - 1, intermediate, end, start))
            stack.append(MyAction(1, start, end, intermediate))
            stack.append(MyAction(nb_disks - 1, start, intermediate, end))


def main():
    nb_disks = int(sys.argv[1])
    hanoi(nb_disks, 1, 3, 2)


if __name__ == '__main__':
    main()
