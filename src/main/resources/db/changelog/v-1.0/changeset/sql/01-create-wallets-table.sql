CREATE TABLE wallets (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         balance NUMERIC(38, 18),
                         base_asset VARCHAR(20),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);