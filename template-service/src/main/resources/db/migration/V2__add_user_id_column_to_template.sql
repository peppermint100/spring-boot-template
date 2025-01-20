ALTER TABLE templates
    ADD COLUMN user_id BINARY(16);

UPDATE templates
SET user_id = UNHEX(REPLACE('00000000-0000-0000-0000-000000000000', '-', ''));

ALTER TABLE templates
    MODIFY COLUMN user_id BINARY(16) NOT NULL;

CREATE INDEX idx_user_id ON templates(user_id);