CREATE TABLE buy_crypto_requests (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         amount NUMERIC(38, 18),
                         price NUMERIC(38, 18),
                         base_asset VARCHAR(20),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);