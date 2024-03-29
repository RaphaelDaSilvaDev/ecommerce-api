CREATE TABLE rules (
  id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_rules PRIMARY KEY (id)
);

ALTER TABLE rules ADD CONSTRAINT uc_rules_name UNIQUE (name);