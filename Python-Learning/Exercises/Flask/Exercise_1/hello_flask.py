'''
Exercise    : hello flask
Author      : Charles T.
'''

from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello():
    return "hello flask"

app.run(host='127.0.0.1', port=8999)