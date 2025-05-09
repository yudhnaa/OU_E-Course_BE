USE ecourse;

CREATE TABLE `user_role`(
    -- admin, lecturer, user
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL
);

CREATE TABLE `user` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    last_name NVARCHAR(50) NOT NULL,
    first_name NVARCHAR(50) NOT NULL,
    birthday VARCHAR(50) NOT NULL,
    username NVARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    avatar VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    is_active BOOL NOT NULL DEFAULT TRUE,
    
    user_role_id INT NOT NULL,
    FOREIGN KEY (user_role_id) REFERENCES user_role(id)
);

CREATE TABLE `category`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL
);

CREATE TABLE `course`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL,
    
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_start TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_end TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    created_by_user_id INT,
    FOREIGN KEY (created_by_user_id) REFERENCES user(id),
    
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- relationship: n-n
CREATE TABLE `course_lecturer`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),
    
    lecturer_id INT NOT NULL,    
    FOREIGN KEY (lecturer_id) REFERENCES user(id) 
);

-- relationship: n-n
CREATE TABLE `course_student`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    progress DOUBLE NOT NULL DEFAULT 0,
        
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),

    student_id INT NOT NULL,    
    FOREIGN KEY (student_id) REFERENCES user(id)    
);

CREATE TABLE course_certificate(
    id INT AUTO_INCREMENT PRIMARY KEY,
    download_link VARCHAR(255) NOT NULL,
    
    course_student_id INT NOT NULL,
    FOREIGN KEY (course_student_id) REFERENCES course_student(id)
);

CREATE TABLE `course_rate`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    rate DOUBLE NOT NULL CHECK (rate <= 5),
    comment TEXT NULL,
        
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),
    
    student_id INT NOT NULL,    
    FOREIGN KEY (student_id) REFERENCES user(id)
);

CREATE TABLE attachment(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    link VARCHAR(255) NOT NULL,
    description TEXT NULL
);

-- video, text
CREATE TABLE lesson_type(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL
);

CREATE TABLE `lesson`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    
    embed_link VARCHAR(255) NOT NULL,
    description TEXT NULL,
    
    lesson_type_id INT NOT NULL,
    FOREIGN KEY (lesson_type_id) REFERENCES lesson_type(id),
    
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),
    
    user_upload_id INT NOT NULL,    
    FOREIGN KEY (user_upload_id) REFERENCES user(id)
    
);

CREATE TABLE lesson_attachment(
	id INT AUTO_INCREMENT PRIMARY KEY,
    
    lesson_id INT NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson(id),
    
    attachment_id INT NOT NULL,
    FOREIGN KEY (attachment_id) REFERENCES attachment(id)
);

CREATE TABLE `lesson_student`(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    is_learn BOOL DEFAULT FALSE,
    
    lesson_id INT NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson(id),

    student_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES user(id)
);

-- multiple choice, writing
CREATE TABLE question_type(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL
);

CREATE TABLE exercise(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    duration_minutes INT NOT NULL,
    
    created_by_user_id INT NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES user(id),
    
    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),
    
    lesson_id INT NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson(id),
    
    attachment_id INT NULL,
    FOREIGN KEY (attachment_id) REFERENCES attachment(id)
);

CREATE TABLE exercise_attachment(
	id INT AUTO_INCREMENT PRIMARY KEY,
    
    exercise_id INT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id),
    
    attachment_id INT NOT NULL,
    FOREIGN KEY (attachment_id) REFERENCES attachment(id)
);

CREATE TABLE question(
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    
    exercise_id INT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id),
    
    question_type_id INT NOT NULL,
    FOREIGN KEY (question_type_id) REFERENCES question_type(id)
);

CREATE TABLE multiple_choice_answer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    is_correct BOOL NOT NULL,
    
    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE writing_answer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    
    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE exercise_question( -- n-n ???
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    exercise_id INT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id)
);

CREATE TABLE exercise_score_status(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
    description TEXT NULL
);

CREATE TABLE exercise_attempt(
    id INT AUTO_INCREMENT PRIMARY KEY,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    submitted_at TIMESTAMP NULL,
    total_score DECIMAL(5,2),
    response TEXT NOT NULL,
    
    status_id INT NOT NULL,
    FOREIGN KEY (status_id) REFERENCES exercise_score_status(id),
    
    exercise_id INT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id),
    
    score_by_user_id INT NOT NULL,
    FOREIGN KEY (score_by_user_id) REFERENCES user(id)
);

CREATE TABLE test (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    description TEXT,
    duration_minutes INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    created_by_user_id INT NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES user(id),

    course_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(id),

    lesson_id INT,
    FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);

CREATE TABLE test_question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    test_id INT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test(id),

    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

CREATE TABLE test_attempt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    submitted_at TIMESTAMP NULL,
    total_score DECIMAL(5,2),

    test_id INT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test(id),

    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);