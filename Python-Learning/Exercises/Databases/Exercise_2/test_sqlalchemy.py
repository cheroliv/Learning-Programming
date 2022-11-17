'''
Exercise    : sqlalchemy
Author      : Charles T.
'''

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from player import Player


def main():
    ''' main function '''
    engine = create_engine('sqlite:///test.db')
    Session = sessionmaker(bind=engine)
    db_session = Session()
    my_query = db_session.query(Player)
    players = my_query.all()
    for player in players:
        print(player)


if __name__ == '__main__':
    main()
