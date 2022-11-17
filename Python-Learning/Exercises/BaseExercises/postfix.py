'''
Exercise    : compute postfix expression
Author      : Charles T.
'''

import re
import sys

def calculate(operator, x, y):
    cases = {
        "+": lambda a, b: a + b,
        "-": lambda a, b: a - b,
        "*": lambda a, b: a * b,
        "/": lambda a, b: a / b,
    }
    return cases[operator](x, y)

def postfix(expression):
    """ compute postfix expression """
    res = 0
    stack = []
    elements = re.split(r"\s+", expression)

    for elem in elements:
        if re.match("^[-+\/*()]$", elem):
            op1 = stack.pop()
            op2 = stack.pop()
            res = calculate(elem, int(op2), int(op1))
            stack.append(res)
        else:
            stack.append(elem)
    return res


def main():
    """main function"""
    expression = sys.argv[1]
    print(postfix(expression))


if __name__ == '__main__':
    main()
