CREATE TABLE cryptocurrencies (
                                  id SERIAL PRIMARY KEY,
                                  base_asset VARCHAR(255),
                                  wallet_id BIGINT,
                                  FOREIGN KEY (wallet_id) REFERENCES wallets(id)
);