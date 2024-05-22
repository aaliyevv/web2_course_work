CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS student_course (
    student_id INT,
    course_id INT,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (course_id) REFERENCES courses(id),
    PRIMARY KEY (student_id, course_id)
);

INSERT INTO students (name, surname, email, age) VALUES ('John', 'Doe', 'john@example.com', 25);
INSERT INTO students (name, surname, email, age) VALUES ('Alice', 'Smith', 'alice@example.com', 23);
INSERT INTO students (name, surname, email, age) VALUES ('Bob', 'Johnson', 'bob@example.com', 22);
INSERT INTO students (name, surname, email, age) VALUES ('Emma', 'Brown', 'emma@example.com', 24);
INSERT INTO students (name, surname, email, age) VALUES ('Michael', 'Wilson', 'michael@example.com', 26);

INSERT INTO courses (title, description) VALUES ('Mathematics', 'Introduction to calculus');
INSERT INTO courses (title, description) VALUES ('History', 'World history overview');
INSERT INTO courses (title, description) VALUES ('Computer Science', 'Fundamentals of programming');
INSERT INTO courses (title, description) VALUES ('Literature', 'Classical literature analysis');
INSERT INTO courses (title, description) VALUES ('Physics', 'Basics of physics');

INSERT INTO student_course (student_id, course_id) VALUES (1, 1);
INSERT INTO student_course (student_id, course_id) VALUES (2, 2);
INSERT INTO student_course (student_id, course_id) VALUES (3, 3);
INSERT INTO student_course (student_id, course_id) VALUES (4, 4);
INSERT INTO student_course (student_id, course_id) VALUES (5, 5);
