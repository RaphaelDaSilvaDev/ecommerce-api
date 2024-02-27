CREATE TABLE addresses (
  id UUID NOT NULL,
   street_name VARCHAR(255),
   number VARCHAR(255),
   city_name VARCHAR(255),
   complement VARCHAR(255),
   state_name VARCHAR(255),
   country VARCHAR(255),
   postal_code VARCHAR(255),
   phone_number VARCHAR(255),
   mobile_number VARCHAR(255),
   created_at TIMESTAMP WITHOUT TIME ZONE,
   updated_at TIMESTAMP WITHOUT TIME ZONE,
   CONSTRAINT pk_addresses PRIMARY KEY (id)
);