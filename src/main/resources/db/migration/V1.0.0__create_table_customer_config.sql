CREATE SEQUENCE customer_config_seq
START WITH 1
INCREMENT BY 1
NO CYCLE;

CREATE TABLE customer_config (
  int_id BIGINT NOT NULL,
  code_id VARCHAR(36) NOT NULL,
  code_customer VARCHAR(36) NOT NULL,
  str_token VARCHAR(4096) NOT NULL,
  str_client_id VARCHAR(4096) NOT NULL,
  enum_api VARCHAR(256) NOT NULL,
  dt_updated_at TIMESTAMP NOT NULL,
  dt_created_at TIMESTAMP NOT NULL
);

ALTER TABLE customer_config
ADD CONSTRAINT customer_config_int_id_pk
PRIMARY KEY (int_id);

CREATE INDEX customer_config_code_customer_idx
ON customer_config (code_customer);

CREATE INDEX customer_config_code_id_idx
ON customer_config (code_id);

ALTER TABLE customer_config
ADD CONSTRAINT customer_config_code_id_uk
UNIQUE (code_id);
