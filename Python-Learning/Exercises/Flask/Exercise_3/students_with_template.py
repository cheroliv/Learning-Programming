'''
Exercise    : students with template
Author      : Charles T.
'''

import os
from flask import Flask, request, render_template

templates = os.path.join(os.getcwd(), 'templates')

students = {'henry': 'A', 'bobby': 'C', 'alice': 'B', 'sarah': 'D'}

app = Flask(__name__, template_folder='templates')

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/students', methods=['GET'])
def students_list():
    return render_template('list_students.html', students=students)

@app.route('/students', methods=['POST',])
def add_student():
    students[request.form['student_name']] = request.form['student_note']
    return render_template('list_students.html', students=students)

app.run(host='127.0.0.1', port=8999)
