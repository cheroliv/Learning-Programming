'''
Exercise    : curves
Author      : Charles T.
'''

import matplotlib.pyplot as plt
import numpy as np

def main():
    """main function"""
    my_functions = [lambda n: 2 * n, lambda n: np.sqrt(n)]
    x_p = np.linspace(0, 10, 11)
    
    for _ in my_functions:
        plt.plot(x_p, _(x_p))
    
    plt.yscale('linear')
    plt.grid(linestyle='--')
    plt.show()


if __name__ == '__main__':
    main()
