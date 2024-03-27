ALTER TABLE enrollments
    RENAME COLUMN created_at TO created_at_enrollment;

ALTER TABLE enrollments
    ADD COLUMN score INT,
    ADD COLUMN reason TEXT,
    ADD COLUMN created_at_feedback DATE;

