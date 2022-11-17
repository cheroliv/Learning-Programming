# pylint: disable= unused-argument, wildcard-import, unused-wildcard-import, too-many-ancestors, redefined-builtin, eval-used
'''
Tkinter     : calculator
Author      : Charles T.
'''

''' calc module '''

from math import *
from tkinter import Tk
from tkinter import Frame
from tkinter import Entry
from tkinter import Label
from tkinter import font

class Application(Frame):
    ''' class Application '''

    def evaluate(self, event):
        ''' evaluate method '''
        self.my_result.configure(text = "Result = " + str(eval(self.my_entry.get())))

    def create_widget(self):
        ''' widget function '''
        self.my_entry = Entry()
        self.my_entry['font'] = self.my_font
        self.my_entry.bind("<Return>", self.evaluate)
        self.my_entry.grid(row=0, column=0, padx=10, pady=10)

        self.my_result = Label(text="Result = ")
        self.my_result['font'] = self.my_font
        self.my_result.grid(row=0, column=1, padx=30)

    def __init__(self, master):
        ''' constructor '''
        Frame.__init__(self)
        self.my_font = font.Font(family='Verdana', size=10, weight='bold')
        self.create_widget()
        self.mainloop()


if __name__ == '__main__':
    root = Tk()
    root.geometry("400x100")
    app = Application(root)
