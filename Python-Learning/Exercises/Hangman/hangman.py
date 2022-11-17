'''
Hangman     : HangMan class
Author      : Charles T.
'''

""" hang_man module """

import constants as const

class HangMan():
    """ class level"""

    def __init__(self):
        """constructor to initialize the number of tests"""
        self.tests = 0

    def run(self, word):
        """game method: choose a letter and check if it belongs to word"""
        stri = '_' * len(word)
        self.display_step(stri)
        while self.tests < 7 and stri != word:
            print("Choose a letter: ")
            letter = input()
            if letter in word:
                stri = self.check_word(letter, stri, word)
            else:
                print(const.HANGMAN[self.tests])
                self.tests += 1
            self.display_step(stri)

    @classmethod
    def check_word(cls, letter, stri, word):
        """check_word method: build list of your intermediate word"""
        tmp = stri

        if letter in stri:
            print("Already chosen.")
        else:
            list_stri = list(stri)
            for ind, elem in enumerate(word):
                if elem == letter:
                    list_stri[ind] = elem
                    tmp = ''.join(list_stri)
        return tmp

    @classmethod
    def display_step(cls, stri):
        """display_step: display the intermediate word"""
        for elem in stri:
            print(elem, '  ', end='')

        print("\n")

    def display_result(self):
        """display_result: display the winner, you or the computer"""
        if self.tests < 7:
            print("You won :-).")
        else:
            print("You lose :-(.")
