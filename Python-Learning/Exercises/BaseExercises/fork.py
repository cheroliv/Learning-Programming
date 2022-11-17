'''
Exercise    : fork
Author      : Charles T.
'''

import os
import sys

def child():
    ''' child function '''
    print("I am the process {0}, my father is the process: {1}".format(os.getpid(), os.getppid()))

def main():
    ''' main function '''
    if len(sys.argv) == 2:
        nb_forks = int(sys.argv[1])

        print('I am the daddy  pid :-): {0}'.format(os.getpid()))
        for _ in range(nb_forks):
            try:
                newpid = os.fork()
                if newpid == 0:
                    child()
                else:
                    os.waitpid(newpid, 0)
            except OSError:
                sys.stderr.write('Failed to create child process.')


if __name__ == '__main__':
    main()
