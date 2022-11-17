'''
Exercise    : blog
Author      : Charles T.
'''

from flask import Flask, render_template, request
from posts import create_post, get_posts

app = Flask(__name__, static_folder='static')

@app.route('/', methods=['GET', 'POST'])
def index():
    ''' index function '''
    if request.method == 'POST':
        name = request.form.get('name')
        post = request.form.get('post')
        create_post(name, post)
    messages = get_posts()
    return render_template('index.html', posts=messages)


if __name__ == '__main__':
    app.run(debug=True)
