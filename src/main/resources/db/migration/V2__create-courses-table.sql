CREATE TABLE courses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) UNIQUE NOT NULL,
    instrutor_name VARCHAR(200) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status ENUM("ACTIVE", "INACTIVE") NOT NULL,
    created_at DATE NOT NULL,
    inactive_at DATE NULL,
    instructor_id BIGINT NOT NULL,
    INDEX idx_code (code),
    CONSTRAINT COURSE_FK_USER FOREIGN KEY (instructor_id) REFERENCES users(id)
);