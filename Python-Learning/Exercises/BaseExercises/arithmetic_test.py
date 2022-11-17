'''
Exercise    : Unitary tests
Author      : Charles T.
'''

import unittest
from arithmetic_operations import add, substract, multiply, divide

class ArithmeticTest(unittest.TestCase):
    ''' ArithmeticTest class '''
    def test_add(self):
        ''' testAdd function '''
        self.assertTrue(add(10, 15, -1) == 22)

    def test_substract(self):
        ''' testSubstract function '''
        self.assertTrue(substract(0, 5) == -5)

    def test_multiply(self):
        ''' testMultiply function '''
        self.assertTrue(multiply(a=1, b=2, c=3, d=4, e=5, f=6) == 720)

    def test_divide(self):
        ''' testDivide function '''
        self.assertRaises(ZeroDivisionError, divide(8, 0))


def main():
    ''' main function '''
    unittest.main()


if __name__ == '__main__':
    main()
