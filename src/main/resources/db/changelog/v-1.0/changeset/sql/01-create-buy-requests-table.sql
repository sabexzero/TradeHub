CREATE TABLE buy_crypto_requests (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         amount_to_buy NUMERIC(38, 18),
                         one_unit_price NUMERIC(38, 18),
                         base_asset VARCHAR(20),
                         quote_asset VARCHAR(20),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);