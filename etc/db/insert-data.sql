-- Sample data for user_role
INSERT INTO user_role (name, description) VALUES
    ('ROLE_ADMIN', 'Administrator'),
    ('ROLE_LECTURER', 'Lecturer'),
    ('ROLE_STUDENT', 'Student');

-- Sample data for user
INSERT INTO user (last_name, first_name, birthday, username, password, avatar, email, user_role_id)
VALUES
#     pass: 123
    ('Smith', 'John', '1990-01-01', 'admin', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'john.smith@example.com', 1),
    ('Duong Huu', 'Thanh', '1993-11-11', 'thanhduong', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://yt3.googleusercontent.com/ytc/AIdro_kMHMMUSXvOBzfi9e6ks4pbI6I7I8yMuqFOUoAuwXpIzUE', 'thanh.dh@ou.edu.vn', 2),
    ('Doe', 'Jane', '1992-02-02', 'janedoe', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'jane.doe@example.com', 2),
    ('Brown', 'Charlie', '1995-03-03', 'charlieb', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'charlie.brown@example.com', 2),
    ('Johnson', 'Emily', '1998-04-04', 'emilyj', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'daddasd.@dadad.com', 2),
    ('Williams', 'Michael', '1996-05-05', 'michaelw', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'dasd@dasd.com', 3),
    ('Jones', 'Sarah', '1994-06-06', 'sarahj', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'dasdasddasd@dasd.com', 3),
    ('Garcia', 'David', '1991-07-07', 'davidg', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'erty@dsd.com', 3),
    ('Martinez', 'Sophia', '1993-08-08', 'sophiam', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'dfgh@dfgh.com', 3),
    ('Hernandez', 'James', '1990-09-09', 'jamesh', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'dfghytr@das.com', 3),
    ('Lopez', 'Olivia', '1992-10-10', 'olivial', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', NULL, 'dfghgfd@dfghgf.com', 3);

-- Sample data for student
INSERT INTO student (user_id)
VALUES
    (6),
    (7),
    (8),
    (9),
    (10),
    (11);

-- Sample data for admin
INSERT INTO admin (user_id)
VALUES
    (1);

-- Sample data for lecturer
INSERT INTO lecturer (is_active, user_id)
VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5);



-- Sample data for category
INSERT INTO category (name, description) VALUES
    ('Programming', 'Programming courses'),
    ('Mathematics', 'Math courses'),
    ('Science', 'Science courses'),
    ('History', 'History courses'),
    ('Literature', 'Literature courses'),
    ('Art', 'Art courses'),
    ('Music', 'Music courses'),
    ('Physical Education', 'Physical education courses'),
    ('Health', 'Health courses'),
    ('Business', 'Business courses');

-- Khai báo biến
SET @default_img := 'https://res.cloudinary.com/dmv0tafrk/image/upload/v1747491519/CITYPNG.COM_HD_Java_Programming_Logo_PNG_-_2000x2000_pnlkwz.png';

SET @description := 'Môn này cung cấp cho sinh viên những kiến thức lập trình Java từ căn bản đến nâng cao, tập trung phát triển ứng dụng Web với framework phổ biến Spring, đặc biệt là phát triển Restful API với Spring và xây dựng phân hệ client bằng công nghệ ReactJS.';

-- Chèn dữ liệu vào bảng course
INSERT INTO course (name, description, created_by_admin_id, category_id, image)
VALUES
('Web System Development', @description, 1, 1, @default_img ),
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
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 1),
    (5, 2);


-- Sample data for course_student
INSERT INTO course_student (progress, course_id, student_id) VALUES
    (1, 1, 1),
    (0.75, 1, 2),
    (0.25, 1, 3),
    (0.6, 1, 4),
    (0.8, 1, 5),
    (0.4, 1, 6),
    (1, 2, 6);


-- Sample data for course_certificate
INSERT INTO course_certificate (download_link, course_student_id) VALUES
    ('https://example.com/certs/john-java.pdf', 1),
    ('https://example.com/certs/jane-web.pdf', 7);



-- Sample data for course_rate
INSERT INTO course_rate (rate, comment, course_student_id)
VALUES
    (4.5, 'Great course!', 1),
    (3.0, 'Average course.', 2),
    (5.0, 'Excellent course!', 3),
    (2.5, 'Not what I expected.', 4),
    (4.0, 'Good content.', 5),
    (3.5, 'Decent course.', 6);


-- Sample data for attachment
INSERT INTO attachment (name, link, description, public_id) VALUES
    ('Intro PDF', '/files/intro.pdf', 'Introduction material', 'dfdsdnbsahdfvddhsfvsdhgf');

-- Sample data for lesson_type
INSERT INTO lesson_type (name, description) VALUES
    ('video', 'Video lesson'),
    ('text', 'Text lesson');

-- Sample data for lesson
INSERT INTO lesson (name, embed_link, description, lesson_type_id, course_id, user_upload_id) VALUES
    ('Phát Triển Hệ Thống Web - Lý Thuyết 1 - GV: Th.s Dương Hữu Thành', 'https://www.youtube.com/embed/9gsCCaFGSuo?si=PDfydo-PQ0L1-yWb', 'Phát Triển Hệ Thống Web - Lý Thuyết 1 - GV: Th.s Dương Hữu Thành', 1, 1, 2),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 2 - GV: Th.s Dương Hữu Thành', 'https://www.youtube.com/embed/dIPqLrbbKPc?si=SOrMDTohl7F4Ua_v', 'Phát Triển Hệ Thống Web - Lý Thuyết 2 - GV: Th.s Dương Hữu Thành', 1, 1, 2),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 3 - GV: Th.s Dương Hữu Thành', 'https://www.youtube.com/embed/Dgu7Y-Puzfw?si=H0w7SY1HHjd48m_Z', 'Phát Triển Hệ Thống Web - Lý Thuyết 3 - GV: Th.s Dương Hữu Thành', 1, 1, 2),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 4 - GV: Th.s Dương Hữu Thành', 'https://drive.google.com/file/d/134pjmZIX_KMW1F3Rxjzh4m5nSA74BQ7M/preview', 'Phát Triển Hệ Thống Web - Lý Thuyết 4 - GV: Th.s Dương Hữu Thành', 1, 1, 2);

-- Sample data for lesson_attachment
INSERT INTO lesson_attachment (lesson_id, attachment_id) VALUES
    (1, 1);

-- Sample data for lesson_student
INSERT INTO lesson_student (is_learn, learned_at, lesson_id, student_id) VALUES
    (1, NOW(), 1, 1),
    (0, NOW(), 1, 2),
    (1, NOW(), 2, 2),
    (0, NOW(), 3, 3),
    (1, NOW(), 4, 4);

-- Sample data for exercise
INSERT INTO exercise (name, duration_minutes, max_score, created_by_user_id, course_id, lesson_id) VALUES
    ('Quiz 2', 30, 100.00, 1, 1, 1),
    ('Quiz 3', 30, 100.00, 1, 1, 1),
    ('Quiz 4', 30, 100.00, 1, 1, 1),
    ('Quiz 5', 30, 100.00, 1, 1, 1),
    ('Quiz 6', 30, 100.00, 1, 1, 1),
    ('Quiz 7', 30, 100.00, 1, 1, 1),
    ('Quiz 8', 30, 100.00, 1, 1, 1),
    ('Quiz 9', 30, 100.00, 1, 1, 1),
    ('Quiz 10', 30, 100.00, 1, 1, 1);

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
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1),
    ('Final Test', 'End of course test', 60, 100.00, 1, 1);

-- Sample data for test_question
INSERT INTO test_question (test_id, question_id) VALUES
    (1, 1);

-- Sample data for test_attempt
INSERT INTO test_attempt (started_at, submitted_at, total_score, test_id, user_id) VALUES
    (NOW(), NOW(), 90.00, 1, 3);

-- Dữ liệu giả cho exercise_attempt_answer

-- Câu trả lời cho bài tập 1 (Quiz 2) - Câu hỏi trắc nghiệm
INSERT INTO exercise_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
    (1, 1, 'A programming language', 1, 100.00);

-- Thêm các câu trả lời khác cho các bài tập khác
INSERT INTO exercise_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                                  (1, 1, 'A fruit', 0, 0.00),
                                                                                                  (1, 1, 'A programming language', 1, 100.00),
                                                                                                  (1, 1, 'A programming language', 1, 100.00),
                                                                                                  (1, 1, 'A fruit', 0, 0.00);

-- Thêm câu hỏi tự luận mới và câu trả lời
-- Thêm câu hỏi tự luận vào bảng question
INSERT INTO question (content, exercise_id, question_type_id) VALUES
    ('Explain the main features of Java', 1, 2);

-- Thêm câu trả lời mẫu cho câu hỏi tự luận
INSERT INTO writing_answer (content, question_id) VALUES
    ('Java is an object-oriented, platform-independent, secure programming language with automatic memory management.', 2);

-- Thêm câu trả lời của sinh viên cho câu hỏi tự luận
INSERT INTO exercise_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                                  (1, 2, 'Java is object-oriented and has automatic memory management called garbage collection.', NULL, 80.00),
                                                                                                  (1, 2, 'Java features include platform independence through JVM and strong security features.', NULL, 90.00),
                                                                                                  (1, 2, 'Java is fast and easy to learn.', NULL, 60.00);

-- Thêm nhiều câu hỏi trắc nghiệm khác
INSERT INTO question (content, exercise_id, question_type_id) VALUES
                                                                  ('What is JVM?', 1, 1),
                                                                  ('Which of these is not a Java keyword?', 1, 1),
                                                                  ('What is the default value of int in Java?', 1, 1);

-- Thêm lựa chọn cho các câu hỏi trắc nghiệm mới
INSERT INTO multiple_choice_answer (content, is_correct, question_id) VALUES
                                                                          ('Java Virtual Machine', 1, 3),
                                                                          ('Java Visual Machine', 0, 3),
                                                                          ('Java Verified Machine', 0, 3),
                                                                          ('Java Variable Machine', 0, 3),

                                                                          ('class', 0, 4),
                                                                          ('interface', 0, 4),
                                                                          ('extends', 0, 4),
                                                                          ('string', 1, 4), -- 'string' không phải là keyword, String mới là class

                                                                          ('0', 1, 5),
                                                                          ('1', 0, 5),
                                                                          ('null', 0, 5),
                                                                          ('-1', 0, 5);

-- Thêm câu trả lời của sinh viên cho các câu hỏi mới
INSERT INTO exercise_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                                  (1, 3, 'Java Virtual Machine', 1, 100.00),
                                                                                                  (1, 3, 'Java Visual Machine', 0, 0.00),
                                                                                                  (1, 3, 'Java Virtual Machine', 1, 100.00),
                                                                                                  (1, 3, 'Java Variable Machine', 0, 0.00),

                                                                                                  (1, 4, 'string', 1, 100.00),
                                                                                                  (1, 4, 'interface', 0, 0.00),
                                                                                                  (1, 4, 'extends', 0, 0.00),
                                                                                                  (1, 4, 'string', 1, 100.00),

                                                                                                  (1, 5, '0', 1, 100.00),
                                                                                                  (1, 5, '1', 0, 0.00),
                                                                                                  (1, 5, 'null', 0, 0.00),
                                                                                                  (1, 5, '0', 1, 100.00);

-- Thêm các lần thi thử cho test 1 (Final Test)
-- Sửa lại các lệnh INSERT vào test_attempt
INSERT INTO test_attempt (started_at, submitted_at, total_score, test_id, user_id) VALUES
                                                                                       ('2023-10-01 09:00:00', '2023-10-01 09:50:00', 85.00, 1, 1), -- student_id 1 (user_id 6)
                                                                                       ('2023-10-02 14:00:00', '2023-10-02 14:55:00', 92.00, 1, 2),  -- student_id 2 (user_id 7)
                                                                                       ('2023-10-03 10:30:00', '2023-10-03 11:25:00', 78.00, 1, 3),  -- student_id 3 (user_id 8)
                                                                                       ('2023-10-04 13:15:00', '2023-10-04 14:10:00', 95.00, 1, 4),  -- student_id 4 (user_id 9)
                                                                                       ('2023-10-05 15:45:00', '2023-10-05 16:40:00', 88.00, 1, 5);  -- student_id 5 (user_id 10)

-- Câu trả lời cho lần thi thử 1 (user_id 6)
INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                              (7, 1, 'A programming language', 1, 100.00), -- Câu hỏi trắc nghiệm
                                                                                              (7, 2, 'Java is object-oriented, has automatic memory management and runs on JVM', NULL, 90.00); -- Câu hỏi tự luận

-- Câu trả lời cho lần thi thử 2 (user_id 7)
INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                              (8, 1, 'A fruit', 0, 0.00),
                                                                                              (8, 2, 'Java is a popular programming language', NULL, 80.00),
                                                                                              (8, 3, 'Java Virtual Machine', 1, 100.00),
                                                                                              (8, 4, 'string', 1, 100.00),
                                                                                              (8, 5, '0', 1, 100.00);

-- Câu trả lời cho lần thi thử 3 (user_id 8)
INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                              (9, 1, 'A programming language', 1, 100.00),
                                                                                              (9, 2, 'Java is fast and secure', NULL, 70.00),
                                                                                              (9, 3, 'Java Visual Machine', 0, 0.00),
                                                                                              (9, 4, 'extends', 0, 0.00),
                                                                                              (9, 5, 'null', 0, 0.00);

-- Câu trả lời cho lần thi thử 4 (user_id 9)
INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                              (10, 1, 'A programming language', 1, 100.00),
                                                                                              (10, 2, 'Java features include platform independence, object-oriented programming, and strong memory management', NULL, 95.00),
                                                                                              (10, 3, 'Java Virtual Machine', 1, 100.00),
                                                                                              (10, 4, 'string', 1, 100.00),
                                                                                              (10, 5, '0', 1, 100.00);

-- Câu trả lời cho lần thi thử 5 (user_id 10)
INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
                                                                                              (11, 1, 'A programming language', 1, 100.00),
                                                                                              (11, 2, 'Java is used for web and mobile applications', NULL, 75.00),
                                                                                              (11, 3, 'Java Virtual Machine', 1, 100.00),
                                                                                              (11, 4, 'interface', 0, 0.00),
                                                                                              (11, 5, '1', 0, 0.00);

SELECT id, user_id, test_id FROM test_attempt;