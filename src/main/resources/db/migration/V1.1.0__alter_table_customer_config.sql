ALTER TABLE customer_config
ADD COLUMN str_public_key VARCHAR(4096) NOT NULL;

ALTER TABLE customer_config
ADD COLUMN str_private_key VARCHAR(4096) NOT NULL;
