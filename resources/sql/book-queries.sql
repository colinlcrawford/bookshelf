-- :name add-book! :! :1
-- :doc create a new book record
INSERT INTO books
(id, title, author, isbn, current_chapter, total_chapters)
VALUES (:id, :title, :author, :isbn, :current_chapter, :total_chapters)

-- :name update-book! :! :n
-- :doc update an existing book record
UPDATE books
SET
  title = :title,
  author = :author,
  isbn = :isbn,
  current_chapter = :current_chapter,
  total_chapters = :total_chapters
WHERE id = :id

-- :name get-book-by-id :! :*
-- :doc get a book by id
SELECT * FROM books WHERE id = :id
