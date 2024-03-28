CREATE TABLE enrollments(
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,

    primary key (user_id, course_id),
    CONSTRAINT USER_ENROLLMENTS_FK_USER FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT USER_ENROLLMENTS_FK_COURSE FOREIGN KEY (course_id) REFERENCES courses(id)
)