create table books
(
    id           int primary key auto_increment,
    title        varchar(255)   not null,
    author       varchar(255)   not null,
    publisher    varchar(255)   not null,
    publish_date date           not null,
    category     varchar(255)   not null,
    price        decimal(10, 2) not null,
    quantity     int            not null
);

INSERT INTO books (title, author, publisher, publish_date, category, price, quantity)
VALUES ('Java for Beginners', 'John Doe', 'Tech Press', '2020-05-15', 'Programming', 29.99, 10),
       ('Advanced Java', 'Jane Smith', 'Code Masters', '2019-08-21', 'Programming', 39.99, 5),
       ('Database Design', 'Alice Johnson', 'Data Science Pub', '2021-03-10', 'Database', 24.99, 8),
       ('Machine Learning Basics', 'Robert Brown', 'AI Press', '2022-07-05', 'AI', 49.99, 7),
       ('Software Engineering Principles', 'Martin Fowler', 'SE Books', '2018-11-12', 'Engineering', 34.99, 3),
       ('Python Crash Course', 'Eric Matthes', 'No Starch Press', '2023-02-20', 'Programming', 27.99, 12),
       ('Clean Code', 'Robert C. Martin', 'Prentice Hall', '2008-08-01', 'Software Development', 45.99, 6),
       ('Effective Java', 'Joshua Bloch', 'Addison-Wesley', '2017-12-27', 'Programming', 39.99, 4),
       ('Deep Learning with Python', 'François Chollet', 'Manning', '2019-10-15', 'AI', 59.99, 9),
       ('The Pragmatic Programmer', 'Andrew Hunt', 'Addison-Wesley', '1999-10-30', 'Software Development', 42.99, 5);

select * from books;