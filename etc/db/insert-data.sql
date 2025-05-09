USE ecourse;

-- 1. Populate user_role table
INSERT INTO user_role (name, description) VALUES
('admin', 'System administrator with full privileges'),
('lecturer', 'Course instructors who create and teach content'),
('student', 'Enrolled students who take courses');

-- 2. Populate user table
INSERT INTO user (last_name, first_name, birthday, username, password, avatar, email, is_active, user_role_id) VALUES
-- Admins
('Smith', 'John', '1985-05-15', 'admin1', '1', '/avatars/admin1.jpg', 'admin1@ecourse.com', TRUE, 1),
('Johnson', 'Sarah', '1990-08-22', 'admin2', '1', '/avatars/admin2.jpg', 'admin2@ecourse.com', TRUE, 1),
-- Lecturers
('Brown', 'Michael', '1975-03-10', 'prof_brown', '1', '/avatars/brown.jpg', 'brown@ecourse.com', TRUE, 2),
('Davis', 'Emma', '1982-11-28', 'prof_davis', '1', '/avatars/davis.jpg', 'davis@ecourse.com', TRUE, 2),
('Wilson', 'Robert', '1978-06-14', 'prof_wilson', '1', '/avatars/wilson.jpg', 'wilson@ecourse.com', TRUE, 2),
-- Students
('Martinez', 'Ana', '2000-02-18', 'ana_m', '1', '/avatars/ana.jpg', 'ana@student.com', TRUE, 3),
('Taylor', 'James', '1999-07-24', 'james_t', '1', '/avatars/james.jpg', 'james@student.com', TRUE, 3),
('Anderson', 'Lisa', '2001-04-05', 'lisa_a', '1', '/avatars/lisa.jpg', 'lisa@student.com', TRUE, 3),
('Thomas', 'Kevin', '1998-09-30', 'kevin_t', '1', '/avatars/kevin.jpg', 'kevin@student.com', TRUE, 3),
('White', 'Sophia', '2000-12-12', 'sophia_w', '1', '/avatars/sophia.jpg', 'sophia@student.com', TRUE, 3);

-- 3. Populate category table
INSERT INTO category (name, description) VALUES
('Programming', 'Courses related to software development and programming languages'),
('Data Science', 'Courses about data analysis, machine learning and statistics'),
('Web Development', 'Courses covering front-end and back-end web technologies'),
('Mobile Development', 'Courses on building apps for iOS and Android'),
('Mathematics', 'Courses covering various mathematical concepts');

-- 4. Populate course table
INSERT INTO course (name, description, date_added, date_start, date_end, created_by_user_id, category_id) VALUES
('Python Fundamentals', 'An introduction to Python programming language basics', '2023-01-15', '2023-02-01', '2023-05-30', 3, 1),
('Machine Learning with Python', 'Learn machine learning techniques using Python libraries', '2023-01-20', '2023-02-15', '2023-06-15', 4, 2),
('Full Stack JavaScript', 'Learn to build web applications with Node.js and React', '2023-02-10', '2023-03-01', '2023-07-30', 3, 3),
('iOS App Development', 'Build iOS applications with Swift and SwiftUI', '2023-02-15', '2023-03-15', '2023-08-15', 5, 4),
('Linear Algebra', 'A comprehensive study of linear algebra concepts', '2023-01-25', '2023-02-10', '2023-06-10', 4, 5);

-- 5. Populate course_lecturer table
INSERT INTO course_lecturer (course_id, lecturer_id) VALUES
(1, 3), -- Python Fundamentals taught by Prof Brown
(2, 4), -- Machine Learning taught by Prof Davis
(3, 3), -- Full Stack JavaScript taught by Prof Brown
(4, 5), -- iOS Development taught by Prof Wilson
(5, 4); -- Linear Algebra taught by Prof Davis

-- 6. Populate course_student table
INSERT INTO course_student (name, progress, course_id, student_id) VALUES
('Python Fundamentals - Ana', 75.5, 1, 6),
('Machine Learning - Ana', 50.0, 2, 6),
('Full Stack JavaScript - James', 85.0, 3, 7),
('Python Fundamentals - Lisa', 90.0, 1, 8),
('iOS Development - Kevin', 65.0, 4, 9),
('Linear Algebra - Sophia', 70.0, 5, 10),
('Machine Learning - Kevin', 45.0, 2, 9),
('Full Stack JavaScript - Sophia', 30.0, 3, 10);

-- 7. Populate course_certificate table
INSERT INTO course_certificate (download_link, course_student_id) VALUES
('/certificates/ana_python_cert.pdf', 1),
('/certificates/lisa_python_cert.pdf', 4);

-- 8. Populate course_rate table
INSERT INTO course_rate (rate, comment, course_id, student_id) VALUES
(4.5, 'Great introduction to Python! The exercises were very helpful.', 1, 6),
(5.0, 'Comprehensive and well-structured course.', 1, 8),
(4.0, 'Good content but some sections were a bit confusing.', 2, 6),
(4.8, 'The instructor was excellent at explaining complex concepts.', 3, 7),
(3.5, 'Decent course but needed more practical examples.', 4, 9),
(4.2, 'Very thorough coverage of linear algebra topics.', 5, 10);

-- 9. Populate attachment table
INSERT INTO attachment (name, link, description) VALUES
('Python Cheatsheet', '/attachments/python_cheatsheet.pdf', 'Quick reference for Python syntax'),
('ML Algorithms Guide', '/attachments/ml_algorithms.pdf', 'Overview of common machine learning algorithms'),
('JavaScript Guide', '/attachments/js_guide.pdf', 'Complete JavaScript reference'),
('Swift Basics', '/attachments/swift_basics.pdf', 'Introduction to Swift programming'),
('Linear Algebra Formulas', '/attachments/linear_algebra_formulas.pdf', 'Key formulas and concepts'),
('Python Exercise Files', '/attachments/python_exercises.zip', 'Practice exercises for Python'),
('Machine Learning Dataset', '/attachments/ml_dataset.csv', 'Dataset for ML exercises');

-- 10. Populate lesson_type table
INSERT INTO lesson_type (name, description) VALUES
('Video', 'Video-based lesson with instructor presentation'),
('Text', 'Text-based lesson with examples and explanations');

-- 11. Populate lesson table
INSERT INTO lesson (name, embed_link, description, lesson_type_id, course_id, user_upload_id) VALUES
('Introduction to Python', 'https://video.ecourse.com/python_intro', 'Overview of Python and its applications', 1, 1, 3),
('Python Data Types', 'https://video.ecourse.com/python_datatypes', 'Exploring Python data types and operations', 1, 1, 3),
('Python Functions', 'https://docs.ecourse.com/python_functions', 'Working with functions in Python', 2, 1, 3),
('Introduction to ML', 'https://video.ecourse.com/ml_intro', 'Basics of machine learning', 1, 2, 4),
('Supervised Learning', 'https://docs.ecourse.com/supervised_learning', 'Understanding supervised learning approaches', 2, 2, 4),
('JavaScript Basics', 'https://video.ecourse.com/js_basics', 'Introduction to JavaScript', 1, 3, 3),
('Swift Syntax', 'https://video.ecourse.com/swift_syntax', 'Fundamentals of Swift programming', 1, 4, 5),
('Matrices and Vectors', 'https://docs.ecourse.com/matrices', 'Understanding matrices and vectors', 2, 5, 4);

-- 12. Populate lesson_attachment table
INSERT INTO lesson_attachment (lesson_id, attachment_id) VALUES
(1, 1), -- Python Intro - Python Cheatsheet
(2, 1), -- Python Data Types - Python Cheatsheet
(3, 6), -- Python Functions - Python Exercise Files
(4, 2), -- Intro to ML - ML Algorithms Guide
(5, 7), -- Supervised Learning - ML Dataset
(6, 3), -- JavaScript Basics - JavaScript Guide
(7, 4), -- Swift Syntax - Swift Basics
(8, 5); -- Matrices and Vectors - Linear Algebra Formulas

-- 13. Populate lesson_student table
INSERT INTO lesson_student (name, is_learn, lesson_id, student_id) VALUES
('Ana - Intro to Python', TRUE, 1, 6),
('Ana - Python Data Types', TRUE, 2, 6),
('Ana - Python Functions', FALSE, 3, 6),
('Lisa - Intro to Python', TRUE, 1, 8),
('Lisa - Python Data Types', TRUE, 2, 8),
('Lisa - Python Functions', TRUE, 3, 8),
('James - JavaScript Basics', TRUE, 6, 7),
('Kevin - Swift Syntax', TRUE, 7, 9),
('Sophia - Matrices and Vectors', TRUE, 8, 10);

-- 14. Populate question_type table
INSERT INTO question_type (name, description) VALUES
('Multiple Choice', 'Question with multiple answer options'),
('Writing', 'Open-ended question requiring a written response');

-- 15. Populate exercise table
INSERT INTO exercise (name, duration_minutes, created_by_user_id, course_id, lesson_id, attachment_id) VALUES
('Python Basics Quiz', 30, 3, 1, 1, 1),
('Python Functions Exercise', 45, 3, 1, 3, 6),
('Machine Learning Concepts', 60, 4, 2, 4, 2),
('JavaScript Practice', 40, 3, 3, 6, 3),
('Swift Fundamentals Quiz', 30, 5, 4, 7, 4),
('Linear Algebra Problem Set', 90, 4, 5, 8, 5);

-- 16. Populate exercise_attachment table
INSERT INTO exercise_attachment (exercise_id, attachment_id) VALUES
(1, 1), -- Python Basics Quiz - Python Cheatsheet
(2, 6), -- Python Functions Exercise - Python Exercise Files
(3, 2), -- ML Concepts - ML Algorithms Guide
(3, 7); -- ML Concepts - ML Dataset

-- 17. Populate question table
INSERT INTO question (content, exercise_id, question_type_id) VALUES
('What is the correct way to declare a variable in Python?', 1, 1),
('Explain the difference between a list and a tuple in Python.', 1, 2),
('Write a Python function that calculates the factorial of a number.', 2, 2),
('Which of the following is NOT a supervised learning algorithm?', 3, 1),
('Explain the concept of overfitting in machine learning.', 3, 2),
('What does the "let" keyword do in JavaScript?', 4, 1),
('What is the difference between var, let, and const in JavaScript?', 4, 2),
('In Swift, what is an optional?', 5, 1),
('Solve the system of linear equations: 2x + 3y = 10, 4x - y = 5', 6, 2);

-- 18. Populate multiple_choice_answer table
INSERT INTO multiple_choice_answer (content, is_correct, question_id) VALUES
('var x = 10', FALSE, 1),
('x = 10', TRUE, 1),
('let x = 10', FALSE, 1),
('int x = 10', FALSE, 1),
('K-means clustering', TRUE, 4),
('Linear regression', FALSE, 4),
('Logistic regression', FALSE, 4),
('Support Vector Machines', FALSE, 4),
('It creates block-scoped variables', TRUE, 6),
('It creates function-scoped variables', FALSE, 6),
('It creates immutable variables', FALSE, 6),
('It creates global variables', FALSE, 6),
('A variable that might contain nil', TRUE, 8),
('A required function parameter', FALSE, 8),
('A special Swift data type for integers only', FALSE, 8),
('A debugging tool in Swift', FALSE, 8);

-- 19. Populate writing_answer table
INSERT INTO writing_answer (content, question_id) VALUES
('In Python, a list is mutable while a tuple is immutable. Lists use square brackets [] and can be modified after creation, while tuples use parentheses () and cannot be changed after creation.', 2),
('def factorial(n):\n    if n == 0 or n == 1:\n        return 1\n    else:\n        return n * factorial(n-1)', 3),
('Overfitting occurs when a model learns the training data too well, including its noise and outliers, resulting in poor performance on new, unseen data.', 5),
('In JavaScript, var is function-scoped and can be redeclared, let is block-scoped and cannot be redeclared within the same block, and const is block-scoped and cannot be reassigned after initialization.', 7),
('Solving step by step:\n1) From the second equation: 4x - y = 5 → y = 4x - 5\n2) Substitute into first equation: 2x + 3(4x - 5) = 10\n3) 2x + 12x - 15 = 10\n4) 14x = 25\n5) x = 25/14\n6) y = 4(25/14) - 5 = 100/14 - 5 = 100/14 - 70/14 = 30/14 = 15/7\nSolution: x = 25/14, y = 15/7', 9);

-- 20. Populate exercise_question table (this table seems redundant as questions already link to exercises)
INSERT INTO exercise_question (exercise_id) VALUES
(1), (2), (3), (4), (5), (6);

-- 21. Populate exercise_score_status table
INSERT INTO exercise_score_status (name, description) VALUES
('Pending', 'Exercise has been submitted but not yet scored'),
('Scored', 'Exercise has been scored by the lecturer'),
('Failed', 'Exercise score is below passing threshold'),
('Passed', 'Exercise score is at or above passing threshold');

-- 22. Populate exercise_attempt table
INSERT INTO exercise_attempt (started_at, submitted_at, total_score, response, status_id, exercise_id, score_by_user_id) VALUES
('2023-02-15 10:00:00', '2023-02-15 10:25:30', 85.00, '{"answers": [{"question_id": 1, "answer": "x = 10"}, {"question_id": 2, "answer": "Lists are mutable, tuples are immutable..."}]}', 4, 1, 3),
('2023-02-20 14:30:00', '2023-02-20 15:10:45', 90.00, '{"answers": [{"question_id": 3, "answer": "def factorial(n): if n <= 1: return 1..."}]}', 4, 2, 3),
('2023-03-05 09:15:00', '2023-03-05 10:10:00', 70.00, '{"answers": [{"question_id": 4, "answer": "K-means clustering"}, {"question_id": 5, "answer": "Overfitting is when..."}]}', 4, 3, 4),
('2023-03-10 16:00:00', '2023-03-10 16:35:20', 60.00, '{"answers": [{"question_id": 6, "answer": "It creates function-scoped variables"}, {"question_id": 7, "answer": "var is function-scoped..."}]}', 3, 4, 3),
('2023-04-02 11:30:00', '2023-04-02 11:55:45', 75.00, '{"answers": [{"question_id": 8, "answer": "A variable that might contain nil"}]}', 4, 5, 5),
('2023-04-15 13:00:00', '2023-04-15 14:25:10', 95.00, '{"answers": [{"question_id": 9, "answer": "Solving step by step: 1) From the second equation..."}]}', 4, 6, 4);

-- 23. Populate test table
INSERT INTO test (name, description, duration_minutes, created_at, created_by_user_id, course_id, lesson_id) VALUES
('Python Midterm Exam', 'Comprehensive assessment of Python fundamentals', 120, '2023-03-20', 3, 1, NULL),
('Machine Learning Midterm', 'Assessment covering key ML concepts', 90, '2023-04-10', 4, 2, NULL),
('JavaScript Final Exam', 'Final assessment of JavaScript skills', 150, '2023-06-15', 3, 3, NULL),
('Swift Programming Test', 'Assessment of Swift syntax and concepts', 60, '2023-05-20', 5, 4, 7),
('Linear Algebra Final', 'Comprehensive assessment of linear algebra', 180, '2023-05-25', 4, 5, NULL);

-- 24. Populate test_question table
INSERT INTO test_question (test_id, question_id) VALUES
(1, 1), -- Python Midterm - Variable declaration question
(1, 2), -- Python Midterm - List vs tuple question
(1, 3), -- Python Midterm - Factorial function question
(2, 4), -- ML Midterm - Supervised learning algorithms
(2, 5), -- ML Midterm - Overfitting concept
(3, 6), -- JS Final - let keyword
(3, 7), -- JS Final - var, let, const differences
(4, 8), -- Swift Test - Optionals question
(5, 9); -- Linear Algebra Final - System of equations

-- 25. Populate test_attempt table
INSERT INTO test_attempt (started_at, submitted_at, total_score, test_id, user_id) VALUES
('2023-03-25 09:00:00', '2023-03-25 10:45:30', 88.50, 1, 6), -- Ana - Python Midterm
('2023-03-25 09:00:00', '2023-03-25 10:50:15', 95.00, 1, 8), -- Lisa - Python Midterm
('2023-04-15 14:00:00', '2023-04-15 15:25:10', 78.75, 2, 6), -- Ana - ML Midterm
('2023-04-15 14:00:00', '2023-04-15 15:20:45', 65.00, 2, 9), -- Kevin - ML Midterm
('2023-06-20 10:00:00', '2023-06-20 12:15:30', 91.25, 3, 7), -- James - JS Final
('2023-05-22 13:00:00', '2023-05-22 13:55:20', 82.50, 4, 9), -- Kevin - Swift Test
('2023-06-01 09:00:00', '2023-06-01 11:45:10', 89.00, 5, 10); -- Sophia - Linear Algebra Final
