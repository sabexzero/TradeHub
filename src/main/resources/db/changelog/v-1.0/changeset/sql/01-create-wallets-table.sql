CREATE TABLE wallets (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         balance NUMERIC(19, 2),
                         cryptocurrency_id BIGINT,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);