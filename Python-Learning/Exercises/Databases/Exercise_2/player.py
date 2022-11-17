# pylint: disable=C0103, line-too-long, too-few-public-methods
'''
Exercise    : sqlalchemy
Author      : Charles T.
'''

from sqlalchemy import Column, Integer, String
from base import Base

class Player(Base):
    ''' Player class '''
    __tablename__ = 'Player'
    id = Column(Integer, primary_key=True)
    name = Column(String)
    elo = Column(Integer)

    def __init__(self, name, elo):
        ''' init method '''
        self.name = name
        self.elo = elo

    def __str__(self):
        ''' str method '''
        return "<Player {}: {}, {}>".format(self.id, self.name, self.elo)
