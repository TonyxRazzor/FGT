CREATE TABLE feedback (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          message TEXT NOT NULL
);