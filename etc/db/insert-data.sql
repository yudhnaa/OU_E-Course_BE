-- Sample data for user_role
INSERT INTO user_role (name, description) VALUES
  ('admin', 'Administrator'),
  ('lecturer', 'Lecturer'),
  ('user', 'Student');

-- Sample data for user
INSERT INTO user (last_name, first_name, birthday, username, password, avatar, email, user_role_id) VALUES
  ('Smith', 'John', '1990-01-01', 'johnsmith', 'hashedpassword1', NULL, 'john.smith@example.com', 1),
  ('Doe', 'Jane', '1992-02-02', 'janedoe', 'hashedpassword2', NULL, 'jane.doe@example.com', 2),
  ('Brown', 'Charlie', '1995-03-03', 'charlieb', 'hashedpassword3', NULL, 'charlie.brown@example.com', 3),
('Johnson', 'Emily', '1998-04-04', 'emilyj', 'hashedpassword4', NULL, 'daddasd.@dadad.com', 3),
('Williams', 'Michael', '1996-05-05', 'michaelw', 'hashedpassword5', NULL, 'dasd@dasd.com', 3),
('Jones', 'Sarah', '1994-06-06', 'sarahj', 'hashedpassword6', NULL, 'dasdasddasd@dasd.com', 3),
('Garcia', 'David', '1991-07-07', 'davidg', 'hashedpassword7', NULL, 'erty@dsd.com', 3),
('Martinez', 'Sophia', '1993-08-08', 'sophiam', 'hashedpassword8', NULL, 'dfgh@dfgh.com', 3),
('Hernandez', 'James', '1990-09-09', 'jamesh', 'hashedpassword9', NULL, 'dfghytr@das.com', 3),
('Lopez', 'Olivia', '1992-10-10', 'olivial', 'hashedpassword10', NULL, 'dfghgfd@dfghgf.com', 3);


-- Sample data for admin
INSERT INTO admin (user_id)
VALUES
    (2),
    (3),
    (4),
    (5),
    (6),
    (7);

-- Sample data for lecturer
INSERT INTO lecturer (is_active, user_id)
VALUES (1, 2);

-- Sample data for category
INSERT INTO category (name, description) VALUES
  ('Programming', 'Programming courses'),
  ('Mathematics', 'Math courses');

-- Khai báo biến
SET @default_img := 'https://res.cloudinary.com/dmv0tafrk/image/upload/v1747491519/CITYPNG.COM_HD_Java_Programming_Logo_PNG_-_2000x2000_pnlkwz.png';

-- Chèn dữ liệu vào bảng course
INSERT INTO course (name, description, created_by_admin_id, category_id, image) VALUES
('Java Basics', 'Learn Java from scratch', 1, 1, @default_img),
('Algebra I', 'Basic algebra course', 1, 2, @default_img),
('Calculus I', 'Introduction to calculus', 1, 2, @default_img),
('Data Structures', 'Learn data structures in Java', 1, 1, @default_img),
('Algorithms', 'Introduction to algorithms', 1, 1, @default_img),
('Web Development', 'Learn HTML, CSS, and JavaScript', 1, 1, @default_img),
('Database Management', 'Learn SQL and database design', 1, 1, @default_img),
('Machine Learning', 'Introduction to machine learning concepts', 1, 1, @default_img),
('Cybersecurity Basics', 'Learn the fundamentals of cybersecurity', 1, 1, @default_img),
('Cloud Computing', 'Introduction to cloud services and architecture', 1, 1, @default_img);

-- Sample data for course_lecturer
INSERT INTO course_lecturer (course_id, lecturer_id) VALUES
  (1, 1);

-- Sample data for course_student
INSERT INTO course_student (name, progress, course_id, student_id) VALUES
  ('John in Java Basics', 0.5, 1, 1),
    ('Jane in Java Basics', 0.75, 1, 2),
    ('Charlie in Java Basics', 0.25, 1, 3),
    ('John in Data Java Basics', 0.6, 1, 4),
    ('Jane in Java Basics', 0.8, 1, 5),
    ('Charlie in Java Basics', 0.4, 1, 6),
    ('John in Java Basics', 0.9, 1, 7),

    ('Jane in Algebra I', 0.5, 2, 1),
    ('Charlie Algebra I', 0.7, 2, 2),
    ('John in Algebra I', 0.3, 2, 3),
    ('Jane in Algebra I', 0.85, 2, 4),
    ('Charlie in Algebra I', 0.65, 2, 5),
    ('John in Algebra I', 0.55, 2, 6),
    ('Jane in Algebra I', 0.45, 2, 7),

    ('John in Calculus I', 0.5, 3, 1),
    ('Jane in Calculus I', 0.75, 3, 2),
    ('Charlie in Calculus I', 0.25, 3, 3),
    ('John in Calculus I', 0.6, 3, 4),
    ('Jane in Calculus I', 0.8, 3, 5),
    ('Charlie in Calculus I', 0.4, 3, 6),
    ('John in Calculus I', 0.9, 3, 7);

-- Sample data for course_certificate
INSERT INTO course_certificate (download_link, course_student_id) VALUES
  ('/certs/john-java.pdf', 1);

-- Sample data for course_rate
INSERT INTO course_rate (rate, comment, course_student_id)
VALUES
    (4.5, 'Great course!', 1),
    (3.0, 'Average course.', 2),
    (5.0, 'Excellent course!', 3),
    (2.5, 'Not what I expected.', 4),
    (4.0, 'Good content.', 5),
    (3.5, 'Decent course.', 6),
    (4.8, 'Very informative.', 7);


-- Sample data for attachment
INSERT INTO attachment (name, link, description) VALUES
  ('Intro PDF', '/files/intro.pdf', 'Introduction material');

-- Sample data for lesson_type
INSERT INTO lesson_type (name, description) VALUES
  ('video', 'Video lesson'),
  ('text', 'Text lesson');

-- Sample data for lesson
INSERT INTO lesson (name, embed_link, description, lesson_type_id, course_id, user_upload_id) VALUES
  ('Lesson 1', 'https://youtu.be/abc123', 'First lesson', 1, 1, 1);

-- Sample data for lesson_attachment
INSERT INTO lesson_attachment (lesson_id, attachment_id) VALUES
  (1, 1);

-- Sample data for lesson_student
INSERT INTO lesson_student (name, is_learn, learned_at, lesson_id, student_id) VALUES
  ('John learns Lesson 1', 1, NOW(), 1, 3);

-- Sample data for exercise
INSERT INTO exercise (name, duration_minutes, max_score, created_by_user_id, course_id, lesson_id) VALUES
  ('Quiz 1', 30, 100.00, 1, 1, 1);

-- Sample data for exercise_attachment
INSERT INTO exercise_attachment (exercise_id, attachment_id) VALUES
  (1, 1);

-- Sample data for question_type
INSERT INTO question_type (name, description) VALUES
  ('multiple choice', 'Choose the correct answer'),
  ('writing', 'Write your answer');

-- Sample data for question
INSERT INTO question (content, exercise_id, question_type_id) VALUES
  ('What is Java?', 1, 1);

-- Sample data for multiple_choice_answer
INSERT INTO multiple_choice_answer (content, is_correct, question_id) VALUES
  ('A programming language', 1, 1),
  ('A fruit', 0, 1);

-- Sample data for writing_answer
INSERT INTO writing_answer (content, question_id) VALUES
  ('Java is a programming language.', 1);

-- Sample data for exercise_score_status
INSERT INTO exercise_score_status (name, description) VALUES
  ('Submitted', 'Submitted for grading'),
  ('Pending', 'Waiting for grading'),
  ('Graded', 'Graded by lecturer');

-- Sample data for exercise_attempt
INSERT INTO exercise_attempt (started_at, submitted_at, total_score, response, status_id, exercise_id, score_by_user_id) VALUES
  (NOW(), NOW(), 95.00, 'Answer to quiz', 3, 1, 1);

-- Sample data for test
INSERT INTO test (name, description, duration_minutes, max_score, created_by_user_id, course_id) VALUES
  ('Final Test', 'End of course test', 60, 100.00, 1, 1);

-- Sample data for test_question
INSERT INTO test_question (test_id, question_id) VALUES
  (1, 1);

-- Sample data for test_attempt
INSERT INTO test_attempt (started_at, submitted_at, total_score, test_id, user_id) VALUES
  (NOW(), NOW(), 90.00, 1, 3);

