CREATE TABLE books (
  id UUID PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(127),
  description VARCHAR(255),
  isbn VARCHAR(15),
  current_chapter INT NOT NULL,
  total_chapters INT
);
