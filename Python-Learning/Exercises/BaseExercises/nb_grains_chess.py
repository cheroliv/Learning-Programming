'''
Exercise    : Legend of Chess
Author      : Charles T.
'''

import sys

def nb_grains_chess(nb_grains):
    """nb_grains_chess function"""
    tmp = 0
    total = 0

    for i in range(nb_grains):
        tmp = 2 ** i
        total += tmp
        print("Case\t", "{:2}: {:26}".format(i, tmp))
    return total

def main():
    """main function"""
    nb_cases = int(sys.argv[1])
    print("\nNumber of grains on the chessboard: {0}.".format(nb_grains_chess(nb_cases)))


if __name__ == '__main__':
    main()
