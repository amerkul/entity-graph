INSERT INTO users(name, email)
VALUES('Anna', 'xxxxxx@mail.ru'), ('Basya', 'eeeeee@mail.ru');

INSERT INTO posts(subject, user_id)
VALUES('Aaaaaaaaaaaaaaaaa', 1), ('Xxxxxxxxxxxxxxxxx', 1),
      ('Kkkkkkkkkkkkkkkk', 2), ('Eeeeeeeeeeeeeeeeee', 2);

INSERT INTO comments(reply, post_id, user_id)
VALUES('Reply_1', 1, 2), ('Reply_2', 2, 2),
      ('Reply_3', 3, 1), ('Reply_4', 4, 1);