USE ecourse;

CREATE TABLE `user_role`
(
    -- admin, lecturer, user
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    description TEXT         NULL
);

-- la student co the xoa
CREATE TABLE `user`
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    last_name    NVARCHAR(50) NOT NULL,
    first_name   NVARCHAR(50) NOT NULL,
    birthday     DATE         NOT NULL,
    username     NVARCHAR(50) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    avatar       VARCHAR(255) NULL,
                        public_id varchar(100) null,
    email        VARCHAR(100) NOT NULL UNIQUE,

    user_role_id INT          NOT NULL,
    FOREIGN KEY (user_role_id) REFERENCES user_role (id) ON DELETE RESTRICT
);


-- la admin, khong the xoa
CREATE TABLE `student`
(
    id      INT AUTO_INCREMENT PRIMARY KEY,

    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT
);


-- la admin, khong the xoa
CREATE TABLE `admin`
(
    id      INT AUTO_INCREMENT PRIMARY KEY,

    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT
);

-- la giang vien, khong the xoa chi co the khoa
create table `lecturer`
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    is_active BOOL NOT NULL DEFAULT TRUE,

    user_id   INT  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE RESTRICT
);

CREATE TABLE `category`
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    description TEXT         NULL
);

CREATE TABLE `course`
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    name                NVARCHAR(50) NOT NULL,
    description         TEXT         NULL,
    image               VARCHAR(255) NULL,
    public_id           varchar(100) null,
    price               DECIMAL(10, 2) NOT NULL DEFAULT 0,

    date_added          DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_start          DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_end            DATETIME DEFAULT CURRENT_TIMESTAMP,

    created_by_admin_id INT,
    FOREIGN KEY (created_by_admin_id) REFERENCES admin (id) ON DELETE RESTRICT,

    category_id         INT          NULL,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE RESTRICT
);

-- relationship: n-n
CREATE TABLE `course_lecturer`
(
    id          INT AUTO_INCREMENT PRIMARY KEY,

    course_id   INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,

    lecturer_id INT NOT NULL,
    FOREIGN KEY (lecturer_id) REFERENCES lecturer (id) ON DELETE CASCADE
);

-- relationship: n-n
CREATE TABLE `course_student`
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    progress   DOUBLE NOT NULL DEFAULT 0,

    course_id  INT    NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,

    student_id INT    NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,

    UNIQUE KEY unique_course_student (course_id, student_id)
);

CREATE TABLE course_certificate
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    download_link     VARCHAR(255) NOT NULL,

    course_student_id INT          NOT NULL,
    FOREIGN KEY (course_student_id) REFERENCES course_student (id) ON DELETE CASCADE,

#     1 student chi co the co 1 certificate cho 1 course
    UNIQUE KEY unique_certificate (course_student_id)
);

CREATE TABLE `course_rate`
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    rate              DOUBLE NOT NULL CHECK (rate <= 5),
    comment           TEXT   NULL,

    course_student_id INT    NULL,
    FOREIGN KEY (course_student_id) REFERENCES course_student (id) ON DELETE SET NULL
);

CREATE TABLE attachment
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    link        VARCHAR(255) NOT NULL,
    public_id   varchar(100) null,
    description TEXT         NULL
);

-- video, text
CREATE TABLE lesson_type
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    description TEXT         NULL
);

CREATE TABLE `lesson`
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           text         NOT NULL,

    embed_link     VARCHAR(255) NOT NULL,
    description    TEXT         NULL,
    image          VARCHAR(255) NULL,
    public_id      varchar(100) null,
    order_index    INT          NOT NULL DEFAULT 0,

    lesson_type_id INT          NOT NULL,
    FOREIGN KEY (lesson_type_id) REFERENCES lesson_type (id) ON DELETE RESTRICT,

    course_id      INT          NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,

    user_upload_id INT          NOT NULL,
    FOREIGN KEY (user_upload_id) REFERENCES user (id) ON DELETE RESTRICT
);

-- 1 attachment thuoc 1 lesson, 1 lesson co nhieu attachment
-- nhung vi attachment co the thuoc exercise hoac lesson nen ta phai tao 1 bang trung gian
CREATE TABLE lesson_attachment
(
    id            INT AUTO_INCREMENT PRIMARY KEY,

    lesson_id     INT NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson (id) ON DELETE CASCADE,

    attachment_id INT NOT NULL,
    FOREIGN KEY (attachment_id) REFERENCES attachment (id) ON DELETE CASCADE
);

CREATE TABLE `lesson_student`
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    is_learn   BOOL DEFAULT FALSE,
    learned_at DATETIME NULL,

    lesson_id  INT      NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson (id) ON DELETE CASCADE,

    student_id INT      NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE
);

CREATE TABLE exercise
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               NVARCHAR(50)  NOT NULL,
    duration_minutes   INT           NOT NULL,
    max_score          DECIMAL(5, 2) NOT NULL,

    created_by_user_id INT           NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES lecturer (id) ON DELETE RESTRICT,

    course_id          INT           NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,

    lesson_id          INT           NOT NULL,
    FOREIGN KEY (lesson_id) REFERENCES lesson (id) ON DELETE CASCADE
);

-- 1 attachment thuoc 1 exercise, 1 exercise co nhieu attachment
-- nhung vi attachment co the thuoc exercise hoac lesson nen ta phai tao 1 bang trung gian
CREATE TABLE exercise_attachment
(
    id            INT AUTO_INCREMENT PRIMARY KEY,

    exercise_id   INT NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise (id) ON DELETE CASCADE,

    attachment_id INT NOT NULL,
    FOREIGN KEY (attachment_id) REFERENCES attachment (id) ON DELETE CASCADE
);

-- multiple choice, writing
CREATE TABLE question_type
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    description TEXT         NULL
);

-- 1 question thuoc 1 exercise, 1 exercise co nhieu question
-- luc tao bai test thi lay nhung question thuoc tu nhieu exercise
CREATE TABLE question
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    content          TEXT NOT NULL,

    exercise_id      INT  NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise (id) ON DELETE CASCADE,

    question_type_id INT  NOT NULL,
    FOREIGN KEY (question_type_id) REFERENCES question_type (id) ON DELETE RESTRICT
);

CREATE TABLE multiple_choice_answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    content     TEXT NOT NULL,
    is_correct  BOOL NOT NULL,

    question_id INT  NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE
);

CREATE TABLE writing_answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    content     TEXT NOT NULL,

    question_id INT  NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE
);

-- 1 exercise co nhieu status: Submitted, Pending, Graded
CREATE TABLE exercise_score_status
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        NVARCHAR(50) NOT NULL,
    description TEXT         NULL
);

CREATE TABLE exercise_attempt
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    started_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    submitted_at     DATETIME NULL,
    total_score      DECIMAL(5, 2),
    response         TEXT     NULL,

    status_id        INT      NOT NULL,
    FOREIGN KEY (status_id) REFERENCES exercise_score_status (id) ON DELETE RESTRICT,

    exercise_id      INT      NOT NULL,
    FOREIGN KEY (exercise_id) REFERENCES exercise (id) ON DELETE CASCADE,

    score_by_user_id INT      NOT NULL,
    FOREIGN KEY (score_by_user_id) REFERENCES lecturer (id) ON DELETE RESTRICT,

    student_id       INT      NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE
);

-- lecturer co the tao bai tap
CREATE TABLE test
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    name               NVARCHAR(100) NOT NULL,
    description        TEXT,
    duration_minutes   INT           NOT NULL,
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP,
    max_score          DECIMAL(5, 2) NOT NULL,

    created_by_user_id INT           NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES lecturer (id) ON DELETE RESTRICT,

    course_id          INT           NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
);

CREATE TABLE test_question
(
    id          INT AUTO_INCREMENT PRIMARY KEY,

    test_id     INT NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test (id) ON DELETE CASCADE,

    question_id INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE
);

CREATE TABLE test_attempt
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    started_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    submitted_at DATETIME NULL,
    total_score  DECIMAL(5, 2),

    test_id      INT      NOT NULL,
    FOREIGN KEY (test_id) REFERENCES test (id) ON DELETE CASCADE,

    user_id      INT      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES student (id) ON DELETE CASCADE
);

CREATE TABLE exercise_attempt_answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,

    attempt_id  INT           NOT NULL,
    FOREIGN KEY (attempt_id) REFERENCES exercise_attempt (id) ON DELETE CASCADE,

    question_id INT           NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE,

    answer_text TEXT          NULL, -- câu trả lời của student
    is_correct  BOOL          NULL,
    score       DECIMAL(5, 2) NULL
);

CREATE TABLE test_attempt_answer
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    attempt_id  INT           NOT NULL,
    FOREIGN KEY (attempt_id) REFERENCES test_attempt (id) ON DELETE CASCADE,
    question_id INT           NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question (id) ON DELETE CASCADE,

    answer_text TEXT          NULL,
    is_correct  BOOL          NULL,
    score       DECIMAL(5, 2) NULL
);

# Triger

# chi co the tao certificate khi student da hoan thanh course: progress = 1
DELIMITER //

CREATE TRIGGER trg_before_insert_certificate
    BEFORE INSERT
    ON course_certificate
    FOR EACH ROW
BEGIN
    DECLARE p DOUBLE;

    SELECT progress INTO p FROM course_student WHERE id = NEW.course_student_id;

    IF p < 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cannot issue certificate: Course progress is not complete';
    END IF;
END;
//

DELIMITER ;
