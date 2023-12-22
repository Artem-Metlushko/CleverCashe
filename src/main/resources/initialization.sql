
DROP TABLE IF EXISTS "user";
CREATE TABLE IF NOT EXISTS "user"
(
    user_id     VARCHAR(255) PRIMARY KEY,
    firstName   VARCHAR(255) NOT NULL,
    lastName    VARCHAR(255) NOT NULL,
    surName     VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(255) NOT NULL
);
INSERT INTO "user" (user_id, firstName, lastName, surName, email, phoneNumber)
VALUES ('1', 'Ivan', 'Ivanov', 'Ivanov', '123523@mail.ru', '555-4819');
INSERT INTO "user" (user_id, firstName, lastName, surName, email, phoneNumber)
VALUES ('a87db4e1-5093-4b41-ad02-4a055a871a98', 'Petr', 'Petrov', 'Petrov', '123523@mail.ru', '555-9465');
INSERT INTO "user" (user_id, firstName, lastName, surName, email, phoneNumber)
VALUES ('66fd3eeb-405a-4628-a55e-d32fe1470497', 'Sergey', 'Sergeev', 'Sergeev', '123523@mail.ru', '555-0471');
INSERT INTO "user" (user_id, firstName, lastName, surName, email, phoneNumber)
VALUES ('7bd1f9fe-21ab-49ac-a461-d9ea85b987d3', 'Valery', 'Valeryev', 'Valeryev', '123523@mail.ru', '555-5076');
INSERT INTO "user" (user_id, firstName, lastName, surName, email, phoneNumber)
VALUES ('357df1d3-9970-4516-985f-d640665f2ea5', 'Yurik', 'Yuriev', 'Yuriev', '123523@mail.ru', '555-3013');
