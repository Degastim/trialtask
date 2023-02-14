INSERT INTO users (email, user_name,password, creation_date, update_date)
VALUES ('zhenya@gmail.com', 'Zhenya', '123jgke43g1rkrg1rijriw45w#trji', '2013-09-13T12:12:12', '2013-09-13T12:12:12');
INSERT INTO users (email, user_name,password, creation_date, update_date)
VALUES ('dima@gmail.com', 'Dima', '123jgke43g1rkrg1rijriw45w#trji', '2013-09-13T12:12:12', '2013-09-13T12:12:12');

INSERT INTO quotes (content, user_id, creation_date, update_date)
VALUES ('Be yourself; everyone else is already taken.', 1, '2013-09-13T12:12:12', '2013-9-12T12:12:12');
INSERT INTO quotes (content, user_id, creation_date, update_date)
VALUES ('So many books, so little time.', 2, '2013-09-13T12:12:12', '2013-9-12T12:12:12');


INSERT INTO votes (mark, quote_id, user_id, creation_date, update_date)
VALUES (1, 1, 1, '2013-09-13T12:12:12', '2013-9-12T12:12:12');
INSERT INTO votes (mark, quote_id, user_id, creation_date, update_date)
VALUES (1, 2, 2, '2013-09-13T12:12:12', '2013-9-12T12:12:12');
INSERT INTO votes (mark, quote_id, user_id, creation_date, update_date)
VALUES (-1, 2, 2, '2013-09-13T12:12:12', '2013-9-12T12:12:12');

