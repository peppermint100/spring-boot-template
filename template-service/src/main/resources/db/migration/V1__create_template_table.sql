CREATE TABLE templates (
   id BINARY(16) NOT NULL,
   name VARCHAR(64) NOT NULL,
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   PRIMARY KEY (id)
);