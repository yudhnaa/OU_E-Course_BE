-- Sample data for user_role
INSERT INTO user_role (name, description) VALUES
    ('ROLE_ADMIN', 'Administrator'),
    ('ROLE_LECTURER', 'Lecturer'),
    ('ROLE_STUDENT', 'Student');

-- Sample data for user
INSERT INTO user (last_name, first_name, birthday, username, password, avatar, public_id, email, user_role_id)
VALUES
#     pass: 123
    ('Smith', 'John', '1990-01-01', 'admin', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/7.jpg', 'random_user', 'john.smith@example.com', 1),
    ('Duong Huu', 'Thanh', '1993-11-11', 'thanhduong', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://yt3.googleusercontent.com/ytc/AIdro_kMHMMUSXvOBzfi9e6ks4pbI6I7I8yMuqFOUoAuwXpIzUE', 'instructor_thanh_profile', 'thanh.dh@ou.edu.vn', 2),
    ('Doe', 'Jane', '1992-02-02', 'janedoe', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/men/60.jpg', 'random_user', 'jane.doe@example.com', 2),
    ('Brown', 'Charlie', '1995-03-03', 'charlieb', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/men/1.jpg', 'random_user', 'charlie.brown@example.com', 2),
    ('Johnson', 'Emily', '1998-04-04', 'emilyj', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/men/39.jpg', 'random_user', 'emily.johnson@example.com', 2),
    ('Williams', 'Michael', '1996-05-05', 'michaelw', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/4.jpg', 'random_user', 'michael.williams@example.com', 3),
    ('Jones', 'Sarah', '1994-06-06', 'sarahj', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/4.jpg', 'random_user', 'sarah.jones@example.com', 3),
    ('Garcia', 'David', '1991-07-07', 'davidg', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/3.jpg', 'random_user', 'david.garcia@example.com', 3),
    ('Martinez', 'Sophia', '1993-08-08', 'sophiam', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/2.jpg', 'random_user', 'sophia.martinez@example.com', 3),
    ('Hernandez', 'James', '1990-09-09', 'jamesh', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/7.jpg', 'random_user', 'james.hernandez@example.com', 3),
    ('Lopez', 'Olivia', '1992-10-10', 'olivial', '$2a$10$ppQqUHJwO8UPXRRmroXExu1XGH3RjKSFx6OOFxJwz1Njlnf8/kayy', 'https://randomuser.me/api/portraits/lego/5.jpg', 'random_user', 'olivia.lopez@example.com', 3);

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
SET @default_img_id := 'java_logo_default';
SET @description := 'Môn này cung cấp cho sinh viên những kiến thức lập trình Java từ căn bản đến nâng cao, tập trung phát triển ứng dụng Web với framework phổ biến Spring, đặc biệt là phát triển Restful API với Spring và xây dựng phân hệ client bằng công nghệ ReactJS.';

-- Chèn dữ liệu vào bảng course với đầy đủ các trường
INSERT INTO course (name, description, created_by_admin_id, category_id, image, public_id, date_added, date_start, date_end, price)
VALUES
('Web System Development', @description, 1, 1, @default_img, @default_img_id, '2023-09-01', '2023-09-15', '2024-01-15', 1000.00),
('Java Basics', 'Learn Java from scratch', 1, 1, @default_img, @default_img_id, '2023-09-05', '2023-09-20', '2024-01-20', 1000.00),
('Algebra I', 'Basic algebra course', 1, 2, @default_img, @default_img_id, '2023-08-15', '2023-09-01', '2023-12-31', 1000.00),
('Calculus I', 'Introduction to calculus', 1, 2, @default_img, @default_img_id, '2023-08-20', '2023-09-05', '2024-01-05', 1000.00),
('Data Structures', 'Learn data structures in Java', 1, 1, @default_img, @default_img_id, '2023-09-10', '2023-09-25', '2024-01-25', 1000.00),
('Algorithms', 'Introduction to algorithms', 1, 1, @default_img, @default_img_id, '2023-09-12', '2023-09-27', '2024-01-27', 1000.00),
('Web Development', 'Learn HTML, CSS, and JavaScript', 1, 1, @default_img, @default_img_id, '2023-09-15', '2023-10-01', '2024-02-01', 1000.00),
('Database Management', 'Learn SQL and database design', 1, 1, @default_img, @default_img_id, '2023-09-18', '2023-10-03', '2024-02-03', 1000.00),
('Machine Learning', 'Introduction to machine learning concepts', 1, 1, @default_img, @default_img_id, '2023-09-20', '2023-10-05', '2024-02-05', 1000.00),
('Cybersecurity Basics', 'Learn the fundamentals of cybersecurity', 1, 1, @default_img, @default_img_id, '2023-09-22', '2023-10-07', '2024-02-07', 1000.00),
('Cloud Computing', 'Introduction to cloud services and architecture', 1, 1, @default_img, @default_img_id, '2023-09-25', '2023-10-10', '2024-02-10', 1000.00);

-- Sample data for course_lecturer
INSERT INTO course_lecturer (course_id, lecturer_id) VALUES
    (1, 1),
    (2, 1),
    (3, 3),
    (4, 1),
    (5, 2);

-- Sample data for course_student
INSERT INTO course_student (progress, course_id, student_id) VALUES
    (0, 1, 1),
    (0, 1, 2),
    (0, 1, 3),
    (0, 1, 4),
    (0, 1, 5),
    (0, 1, 6),
    (0, 2, 2);

-- Sample data for course_certificate
# INSERT INTO course_certificate (download_link, course_student_id) VALUES
#     ('https://drive.google.com/file/d/1Q2zOARXiZrEpNu_aMngCdb3qGszNV2nJ/view?usp=drivesdk', 1);
#     ('https://drive.google.com/file/d/1Q2zOARXiZrEpNu_aMngCdb3qGszNV2nJ/view?usp=drivesdk', 7);

-- Sample data for course_rate
INSERT INTO course_rate (rate, comment, course_student_id)
VALUES
    (5.0, 'Great course!', 1),
    (5.0, 'Average course.', 2),
    (5.0, 'Excellent course!', 3),
    (5.0, 'Not what I expected.', 4),
    (5.0, 'Good content.', 5),
    (5.0, 'Decent course.', 6);

-- Sample data for attachment
INSERT INTO attachment (name, link, description, public_id) VALUES
    ('01-Nhap-mon-Java.pdf', 'https://drive.google.com/file/d/18NfyQ6rHlwpUBjd7MqxG-wEaLU09RfO6/view?usp=drivesdk', 'Introduction material', '18NfyQ6rHlwpUBjd7MqxG-wEaLU09RfO6'),
    ('03-JDBC.pdf', 'https://drive.google.com/file/d/1kBeb44VULgmj2fg-19YQ_8bYCPCuf5-u/view?usp=drivesdk', 'Chapter 1 supplementary notes', '1kBeb44VULgmj2fg-19YQ_8bYCPCuf5-u'),
    ('04-Hibernate.pdf', 'https://drive.google.com/file/d/10Zqw4R_3PryF4cpMcm2ZOaPMjfOad6Wv/view?usp=drivesdk', 'Project requirements and guidelines', '10Zqw4R_3PryF4cpMcm2ZOaPMjfOad6Wv'),
    ('05-reactjs.pdf', 'https://drive.google.com/file/d/1LzkdMOvwGB3OmZHGF8xmApftRpYjToaB/view?usp=drivesdk', 'Project requirements and guidelines', '1LzkdMOvwGB3OmZHGF8xmApftRpYjToaB');

-- Sample data for lesson_type
INSERT INTO lesson_type (name, description) VALUES
    ('video', 'Video lesson'),
    ('text', 'Text lesson');

-- Sample data for lesson with order_index
INSERT INTO lesson (name, embed_link, description, lesson_type_id, course_id, user_upload_id, order_index, image, public_id) VALUES
    ('Phát Triển Hệ Thống Web - Lý Thuyết 1', 'https://www.youtube.com/embed/9gsCCaFGSuo?si=PDfydo-PQ0L1-yWb', 'Phát Triển Hệ Thống Web - Lý Thuyết 1 - GV: Th.s Dương Hữu Thành', 1, 1, 2, 1, @default_img, 'lesson1_thumbnail'),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 2', 'https://www.youtube.com/embed/dIPqLrbbKPc?si=SOrMDTohl7F4Ua_v', 'Phát Triển Hệ Thống Web - Lý Thuyết 2 - GV: Th.s Dương Hữu Thành', 1, 1, 2, 2, @default_img, 'lesson2_thumbnail'),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 3', 'https://www.youtube.com/embed/Dgu7Y-Puzfw?si=H0w7SY1HHjd48m_Z', 'Phát Triển Hệ Thống Web - Lý Thuyết 3 - GV: Th.s Dương Hữu Thành', 1, 1, 2, 3, @default_img, 'lesson3_thumbnail'),
    ('Phát Triển Hệ Thống Web - Lý Thuyết 4', 'https://drive.google.com/file/d/134pjmZIX_KMW1F3Rxjzh4m5nSA74BQ7M/preview', 'Phát Triển Hệ Thống Web - Lý Thuyết 4 - GV: Th.s Dương Hữu Thành', 1, 1, 2, 4, @default_img, 'lesson4_thumbnail'),

    ('Lập Trình Hướng Đối Tượng - Lý Thuyết 1', 'https://www.youtube.com/embed/BwQ0fXRlKmo?si=UvEZiUWzIlugDPL_', 'Lập Trình Hướng Đối Tượng - Lý Thuyết 1 - GV: Th.s Dương Hữu Thành', 1, 2, 1, 1, @default_img, 'lesson1_thumbnail'),
    ('Lập Trình Hướng Đối Tượng - Lý Thuyết 2', 'https://www.youtube.com/embed/qKxePVasXNk?si=ajqQCFW8JD5YGC1N', 'Lập Trình Hướng Đối Tượng - Lý Thuyết 2 - GV: Th.s Dương Hữu Thành', 1, 2, 1, 2, @default_img, 'lesson1_thumbnail');


-- Sample data for lesson_attachment
INSERT INTO lesson_attachment (lesson_id, attachment_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

# -- Sample data for lesson_student with learned_at
# INSERT INTO lesson_student (is_learn, learned_at, lesson_id, student_id) VALUES
#     (1, '2023-09-20 14:30:00', 1, 1),
#     (0, NULL, 1, 2),
#     (1, '2023-09-22 10:15:00', 2, 2),
#     (0, NULL, 3, 3),
#     (1, '2023-09-25 16:45:00', 4, 4);

-- Sample data for exercise
INSERT INTO exercise (name, duration_minutes, max_score, created_by_user_id, course_id, lesson_id) VALUES
    ('Quiz 1', 30, 100.00, 1, 1, 1),
    ('Quiz 2', 30, 100.00, 2, 1, 2);
#     ('Quiz 3', 30, 100.00, 3, 1, 3),
#     ('Quiz 4', 30, 100.00, 1, 1, 4),
#     ('Assignment 1', 60, 100.00, 2, 2, 5),
#     ('Assignment 2', 60, 100.00, 3, 2, 6),
#     ('Midterm Exercise', 90, 100.00, 1, 1, 1),
#     ('Final Exercise', 120, 100.00, 2, 1, 2);

-- Sample data for exercise_attachment
INSERT INTO exercise_attachment (exercise_id, attachment_id) VALUES
    (1, 1),
    (2, 2);
#     (3, 3);

-- Sample data for question_type
INSERT INTO question_type (name, description) VALUES
    ('multiple choice', 'Choose the correct answer'),
    ('writing', 'Write your answer');

-- Sample data for question
INSERT INTO question (content, exercise_id, question_type_id) VALUES
    ('What is Java?', 1, 1),
    ('Explain the main features of Java', 1, 1),
    ('What is JVM?', 2, 1),
    ('Which of these is not a Java keyword?', 2, 1),
    ('What is the default value of int in Java?', 2, 1);

-- Sample data for multiple_choice_answer
INSERT INTO multiple_choice_answer (content, is_correct, question_id) VALUES
    ('A programming language', 1, 1),
    ('A fruit', 0, 1),
    ('An operating system', 0, 1),
    ('A database', 0, 1),
    
    ('Java Virtual Machine', 1, 3),
    ('Java Visual Machine', 0, 3),
    ('Java Verified Machine', 0, 3),
    ('Java Variable Machine', 0, 3),
    
    ('class', 0, 4),
    ('interface', 0, 4),
    ('extends', 0, 4),
    ('string', 1, 4),
    
    ('0', 1, 5),
    ('1', 0, 5),
    ('null', 0, 5),
    ('-1', 0, 5);

-- Sample data for writing_answer
INSERT INTO writing_answer (content, question_id) VALUES
    ('Java is a high-level, class-based, object-oriented programming language.', 1),
    ('Java is an object-oriented, platform-independent, secure programming language with automatic memory management.', 2);

-- Sample data for exercise_score_status
INSERT INTO exercise_score_status (name, description) VALUES
    ('Submitted', 'Submitted for grading'),
    ('Pending', 'Waiting for grading'),
    ('Graded', 'Graded by lecturer');

# -- Sample data for exercise_attempt with proper dates
# INSERT INTO exercise_attempt (started_at, submitted_at, total_score, response, status_id, exercise_id, score_by_user_id, student_id) VALUES
#     ('2023-09-20 10:00:00', '2023-09-20 10:25:00', 95.00, 'Answer to quiz 1', 3, 1, 1, 1),
#     ('2023-09-22 14:30:00', '2023-09-22 14:55:00', 85.00, 'Answer to quiz 2', 3, 2, 2, 1),
#     ('2023-09-25 09:15:00', '2023-09-25 09:40:00', 90.00, 'Answer to quiz 3', 3, 3, 3, 3),
#     ('2023-09-27 13:00:00', '2023-09-27 13:25:00', 88.00, 'Answer to quiz 4', 2, 4, 1, 4);

-- Sample data for test
INSERT INTO test (name, description, duration_minutes, created_at, max_score, created_by_user_id, course_id) VALUES
    ('Midterm Exam', 'Midterm examination', 90, '2023-10-15', 100.00, 1, 1),
    ('Final Exam', 'Final examination', 120, '2023-12-10', 100.00, 2, 1);
#     ('Java Basics Test', 'Basic Java concepts test', 60, '2023-10-05', 50.00, 3, 2),
#     ('Web Development Quiz', 'HTML/CSS/JS quiz', 45, '2023-10-20', 40.00, 1, 7),
#     ('Database Design Test', 'ER diagrams and SQL test', 75, '2023-10-25', 60.00, 2, 8);

-- Sample data for test_question
INSERT INTO test_question (test_id, question_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5);
#     (3, 1),
#     (3, 3);

# -- Sample data for test_attempt with proper student IDs
# INSERT INTO test_attempt (started_at, submitted_at, total_score, test_id, user_id) VALUES
#     ('2023-10-15 09:00:00', '2023-10-15 10:25:00', 92.00, 1, 1),
#     ('2023-10-15 09:00:00', '2023-10-15 10:28:00', 88.00, 1, 2),
#     ('2023-10-15 09:00:00', '2023-10-15 10:15:00', 95.00, 1, 3),
#     ('2023-12-10 14:00:00', '2023-12-10 15:55:00', 90.00, 2, 1),
#     ('2023-12-10 14:00:00', '2023-12-10 15:50:00', 85.00, 2, 2);

# -- Sample data for exercise_attempt_answer
# INSERT INTO exercise_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
#     (1, 1, 'A programming language', 1, 100.00),
#     (1, 2, 'Java is object-oriented and has automatic memory management called garbage collection.', NULL, 90.00),
#     (1, 3, 'Java Virtual Machine', 1, 100.00),
#     (2, 1, 'A programming language', 1, 100.00),
#     (2, 2, 'Java features include platform independence through JVM and strong security features.', NULL, 85.00),
#     (2, 4, 'string', 1, 100.00);

# -- Sample data for test_attempt_answer
# INSERT INTO test_attempt_answer (attempt_id, question_id, answer_text, is_correct, score) VALUES
#     (1, 1, 'A programming language', 1, 100.00),
#     (1, 2, 'Java is a versatile, object-oriented language with platform independence via the JVM.', NULL, 95.00),
#     (1, 3, 'Java Virtual Machine', 1, 100.00),
#     (2, 1, 'A programming language', 1, 100.00),
#     (2, 2, 'Java has features like garbage collection and platform independence.', NULL, 85.00),
#     (2, 3, 'Java Visual Machine', 0, 0.00);
