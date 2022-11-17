'''
Exercise    : convert infix expression to postfix expression
Author      : Charles T.
'''

import re
import sys

def infix_postfix(expr):
    """ convert infix expression to postfix expression """
    res = ""
    stack = []

    for elem in expr:
        if re.match("[0-9]", elem):
            res += elem
        elif re.match("[-+\/*]", elem):
            res += ' '
            stack.append(elem)
        elif elem == ')':
            res += ' '
            res += stack.pop()
    while stack:
        res += ' '
        res += stack.pop()
    return res


def main():
    """main function"""
    expr = sys.argv[1]
    print(infix_postfix(expr))


if __name__ == '__main__':
    main()