'''
Exercise    : students without template
Author      : Charles T.
'''

from flask import Flask, request, make_response, redirect, url_for

students = {'henry': 'A', 'bobby': 'C', 'alice': 'B', 'sarah': 'D'}

app = Flask(__name__)


@app.route('/')
def hello():
    header_str = """<html><head><title>Students</title></head><body>"""
    my_link = "hello flask <br> " + '<a href=/students>students</a>'
    footer_str = """</body></html>"""
    return header_str + my_link + footer_str

@app.route('/students/')
def students_list():
    students_str = "<br />".join(["{} has {} as note".format(*s) for s in students.items()])
    form_str =  """<br><br> Add a student:
                <form method="post">
                <input type="text" name="student_name"></input>
                <input type="text" name="student_note"></input>
                <input type="submit" value="Submit">
                """
    header_str = """<html><head><title>Students</title></head><body>"""
    footer_str = """</body></html>"""
    return header_str + students_str + form_str + footer_str

@app.route('/students/', methods=['POST',])
def add_student():
    print(request.form)
    print(request.data)
    students[request.form['student_name']] = request.form['student_note']
    return redirect(url_for('students_list'))

app.run(host='127.0.0.1', port=8999)