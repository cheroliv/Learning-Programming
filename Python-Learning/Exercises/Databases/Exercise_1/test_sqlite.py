'''
Databases   : Test sqlite3
Author      : Charles T.
'''

import sqlite3

connection = sqlite3.connect('test.db')
cursor = connection.cursor()

cursor.execute(""" create table IF NOT EXISTS Player (
                id INTEGER,
                name VARCHAR(26) NOT NULL,
                elo INTEGER,
                CONSTRAINT pk_Player PRIMARY KEY(id)
                ) """)

cursor.execute("""  insert or ignore into Player (id, name, elo)
                    VALUES
                    ('1', 'tintin', '1937'),
                    ('2', 'milou', '2410'),
                    ('3', 'tryphon', '2022');
                """)

def main():
    cursor.execute(""" select * from Player """)
    rows = cursor.fetchall()

    for record in rows:
        print(record)


if __name__ == '__main__':
    main()
