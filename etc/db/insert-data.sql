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

