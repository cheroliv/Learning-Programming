'''
Exercise    : Matrix / numpy
Author      : Charles T.
'''

import sys
import numpy as np

def main():
    ''' main function '''
    m_a = np.matrix(np.loadtxt(sys.argv[1], dtype=int))
    m_b = np.matrix(np.loadtxt(sys.argv[2], dtype=int))
    m_c = m_a * m_b
    print('Matrice A       :\n {0}\n'.format(m_a).replace('[', '').replace(']', ''))
    print('Matrice B       :\n {0}\n'.format(m_b).replace('[', '').replace(']', ''))
    print('A + B           :\n {0}\n'.format(m_a + m_b).replace('[', '').replace(']', ''))
    print('A * B           :\n {0}\n'.format(m_c).replace('[', '').replace(']', ''))
    print('Transposée de A :\n {0}\n'.format(np.transpose(m_a)).replace('[', '').replace(']', ''))
    print('Inverse de B    :\n {0}\n'.format(np.linalg.inv(m_b)).replace('[', '').replace(']', ''))
    print('Déterminant de C:\n {0}\n'.format(np.linalg.det(m_c)).replace('[', '').replace(']', ''))


if __name__ == '__main__':
    main()
