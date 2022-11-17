'''
Exercise    : blog
Author      : Charles T.
'''

import sqlite3 as sql
from os import path


ROOT = path.dirname(path.realpath((__file__)))


def create_post(name, content):
    ''' create_post function '''
    con = sql.connect(path.join(ROOT, 'database.db'))
    cur = con.cursor()
    cur.execute('insert into posts (name, content) values(?, ?)', (name, content))
    con.commit()
    con.close()


def get_posts():
    ''' get_posts function '''
    con = sql.connect(path.join(ROOT, 'database.db'))
    cur = con.cursor()
    cur.execute('select * from posts')
    posts = cur.fetchall()
    return posts
