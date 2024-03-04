CREATE TABLE movie (
    id SERIAL PRIMARY  KEY,
    releaseYear int4,
    title VARCHAR(255),
    winner boolean
);

CREATE TABLE producer (
    id SERIAL PRIMARY  KEY,
    name VARCHAR(255)
);

CREATE TABLE studio (
    id SERIAL PRIMARY  KEY,
    name VARCHAR(255)
);

CREATE TABLE movie_producer (
    movie_id INT,
    producer_id INT,
    PRIMARY KEY (movie_id, producer_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (producer_id) REFERENCES producer(id)
);

CREATE TABLE movie_studio (
    movie_id INT,
    studio_id INT,
    PRIMARY KEY (movie_id, studio_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (studio_id) REFERENCES studio(id)
);