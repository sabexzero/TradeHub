CREATE TABLE crypto_transactions_history (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         base_amount NUMERIC(38, 18),
                         quote_amount NUMERIC(38, 18),
                         base_asset VARCHAR(20),
                         quote_asset VARCHAR(20),
                         transaction_type VARCHAR(20),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);