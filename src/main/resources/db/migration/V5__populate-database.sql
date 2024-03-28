
INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Claire Redfield', 'claire123', 'claire@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Jill Valentine', 'jill123', 'jill@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'INSTRUCTOR', '2024-03-21');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Ashley Graham ', 'ashley123', 'ashley@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'INSTRUCTOR', '2024-03-21');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Ada Wong', 'ada123', 'ada@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'ADMIN', '2024-03-27');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Chris Redfield', 'chris123', 'chris@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Leon Kennedy', 'leon123', 'leon@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Albert Wesker', 'wesker123', 'wesker@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Nemesis', 'nemesis123', 'nemesis@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Jack Krauser', 'krauser123', 'krauser@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');

INSERT INTO users (name, username, email, password, role, created_at)
VALUES ('Luis Serra', 'luis123', 'luis@example.com', '$2a$10$xYDAmLQiuBIZm5uODh5SKOEYaWtxiMllcBQAHPsgRoIU8gjQG/dpe', 'STUDENT', '2024-03-22');


INSERT INTO courses (name, code, instructor_email, description, status, created_at, inactive_at, instructor_id)
VALUES ('PHP e MySQL', 'php-mysql', 'jill@example.com', 'Aprenda MySQL e PHP', 'ACTIVE', '2024-03-27', NULL, 2);

INSERT INTO courses (name, code, instructor_email, description, status, created_at, inactive_at, instructor_id)
VALUES ('Java exceções: aprenda a criar, lançar e controlar exceções', 'java-1', 'jill@example.com', 'Aprenda a criar, lançar e controlar exceções', 'ACTIVE', '2024-03-27', NULL, 2);

INSERT INTO courses (name, code, instructor_email, description, status, created_at, inactive_at, instructor_id)
VALUES ('Maven: gerencie dependências e faça o build de aplicações Java', 'maven', 'ashley@example.com', 'Gerencie dependências e faça o build de aplicações Java', 'ACTIVE', '2024-03-27', NULL, 3);

INSERT INTO courses (name, code, instructor_email, description, status, created_at, inactive_at, instructor_id)
VALUES ('PHP: Conceito', 'php', 'ashley@example.com', 'Aprenda conceitos', 'ACTIVE', '2024-03-27', NULL, 3);

INSERT INTO courses (name, code, instructor_email, description, status, created_at, inactive_at, instructor_id)
VALUES ('Terminal', 'terminal', 'ashley@example.com', 'Aprenda terminal no Ubuntu', 'INACTIVE', '2024-03-27', '2024-03-27', 3);


INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (1, 1,'2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (1, 2, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (1, 3, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (1, 4, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (5, 1, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (5, 2, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (5, 4, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (6, 3, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (6, 4, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (7, 1, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (7, 4, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (8, 2, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (9, 3, '2024-03-27');
INSERT INTO enrollments (user_id, course_id, created_at_enrollment) VALUES (10, 1, '2024-03-27');

UPDATE enrollments SET score = 5, reason = 'xpto1', created_at_feedback = '2024-03-29' WHERE user_id = 1 AND course_id = 1;
UPDATE enrollments SET score = 6, reason = 'xpto2', created_at_feedback = '2024-03-28' WHERE user_id = 1 AND course_id = 2;
UPDATE enrollments SET score = 7, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 1 AND course_id = 3;
UPDATE enrollments SET score = 5, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 5 AND course_id = 1;
UPDATE enrollments SET score = 10, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 5 AND course_id = 2;
UPDATE enrollments SET score = 10, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 5 AND course_id = 4;
UPDATE enrollments SET score = 8, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 6 AND course_id = 3;
UPDATE enrollments SET score = 0, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 6 AND course_id = 4;
UPDATE enrollments SET score = 1, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 7 AND course_id = 4;
UPDATE enrollments SET score = 8, reason = 'xpto2', created_at_feedback = '2024-03-26' WHERE user_id = 7 AND course_id = 1;









