CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE people (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city_id INTEGER REFERENCES cities(id),
    unique (id, city_id)
);

INSERT INTO cities (name) VALUES ('Астана');
INSERT INTO cities (name) VALUES ('Семей');

INSERT INTO people (name, city_id) VALUES ('Майк', 1);
INSERT INTO people (name, city_id) VALUES ('Федя', 2);
INSERT INTO people (name, city_id) VALUES ('Альберт', 2);