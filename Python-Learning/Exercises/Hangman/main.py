'''
Hangman     : main function
Author      : Charles T.
'''

""" main file """
import random
import hangman as gh

def main():
    """main method - choose a word and run the game"""
    dictionary = open("words.txt", 'r')
    list_words = dictionary.read().split('\n')
    dictionary.close()

    while True:
        print("The computer chooses a word.\n")
        word = random.choice(list_words)
        game = gh.HangMan()
        game.run(word)
        game.display_result()
        print("Another game? y/n")
        response = input()
        if response != "y":
            break

if __name__ == '__main__':
    main()
