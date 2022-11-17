'''
Exercise    : Unitary tests
Author      : Charles T.
'''

import logging

def add(*integers):
    ''' add function '''
    result = 0

    for number in integers:
        result += number
    return result

def substract(op_a, op_b):
    ''' substract function '''
    return op_a - op_b

def multiply(**integers):
    ''' multiply function '''
    result = 1

    for number in integers.values():
        result *= number
    return result

def divide(op_a, op_b):
    ''' divide function '''
    try:
        result = op_a / op_b
        return result
    except ZeroDivisionError as error:
        logging.warning(error)
