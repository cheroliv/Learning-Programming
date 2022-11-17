'''
Tkinter     : hello world
Author      : Charles T.
'''

import tkinter as tk

root = tk.Tk()
root.title("Hello Tkinter")
root.geometry('{}x{}'.format(400, 200))
label = tk.Label(root, text="Hey guys what's up?")
label.pack()

root.mainloop()
