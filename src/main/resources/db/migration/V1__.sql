CREATE SEQUENCE  IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE json_place_holder_post (
  id INTEGER NOT NULL,
  title VARCHAR(255),
  body VARCHAR(255),
  user_id INTEGER,
  create_at TIMESTAMP with time zone,
  CONSTRAINT pk_jsonplaceholderpost PRIMARY KEY (id)
);