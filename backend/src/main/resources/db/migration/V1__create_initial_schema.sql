-- Create customer table
CREATE TABLE customer (
    customer_number SERIAL PRIMARY KEY,
    first_name VARCHAR(10),
    last_name VARCHAR(20),
    date_of_birth DATE,
    house_name VARCHAR(20),
    house_number VARCHAR(4),
    postcode VARCHAR(8),
    phone_home VARCHAR(20),
    phone_mobile VARCHAR(20),
    email_address VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create index on customer
CREATE INDEX idx_customer_name ON customer(last_name, first_name);
CREATE INDEX idx_customer_postcode ON customer(postcode);

-- Create policy table
CREATE TABLE policy (
    policy_number SERIAL PRIMARY KEY,
    customer_number INTEGER NOT NULL,
    issue_date DATE,
    expiry_date DATE,
    policy_type CHAR(1) NOT NULL CHECK (policy_type IN ('M', 'E', 'H', 'C')),
    last_changed TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    broker_id INTEGER,
    brokers_reference VARCHAR(10),
    payment INTEGER,
    commission SMALLINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_policy_customer FOREIGN KEY (customer_number) 
        REFERENCES customer(customer_number) ON DELETE CASCADE
);

-- Create indexes on policy
CREATE INDEX idx_policy_customer ON policy(customer_number);
CREATE INDEX idx_policy_type ON policy(policy_type);
CREATE INDEX idx_policy_dates ON policy(issue_date, expiry_date);

-- Create endowment table (E - Endowment insurance policies)
CREATE TABLE endowment (
    policy_number INTEGER PRIMARY KEY,
    equities CHAR(1),
    with_profits CHAR(1),
    managed_fund CHAR(1),
    fund_name VARCHAR(10),
    term SMALLINT,
    sum_assured INTEGER,
    life_assured VARCHAR(31),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_endowment_policy FOREIGN KEY (policy_number) 
        REFERENCES policy(policy_number) ON DELETE CASCADE
);

-- Create house table (H - House insurance policies)
CREATE TABLE house (
    policy_number INTEGER PRIMARY KEY,
    property_type VARCHAR(15),
    bedrooms SMALLINT,
    value INTEGER,
    house_name VARCHAR(20),
    house_number VARCHAR(4),
    postcode VARCHAR(8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_house_policy FOREIGN KEY (policy_number) 
        REFERENCES policy(policy_number) ON DELETE CASCADE
);

-- Create index on house postcode
CREATE INDEX idx_house_postcode ON house(postcode);

-- Create motor table (M - Motor insurance policies)
CREATE TABLE motor (
    policy_number INTEGER PRIMARY KEY,
    make VARCHAR(15),
    model VARCHAR(15),
    value INTEGER,
    reg_number VARCHAR(7),
    colour VARCHAR(8),
    cc SMALLINT,
    year_of_manufacture DATE,
    premium INTEGER,
    accidents INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_motor_policy FOREIGN KEY (policy_number) 
        REFERENCES policy(policy_number) ON DELETE CASCADE
);

-- Create index on motor registration
CREATE INDEX idx_motor_reg ON motor(reg_number);

-- Create commercial table (C - Commercial property insurance policies)
CREATE TABLE commercial (
    policy_number INTEGER PRIMARY KEY,
    request_date TIMESTAMP,
    start_date DATE,
    renewal_date DATE,
    address VARCHAR(255),
    zipcode VARCHAR(8),
    latitude_n VARCHAR(11),
    longitude_w VARCHAR(11),
    customer_name VARCHAR(255),
    property_type VARCHAR(255),
    fire_peril SMALLINT,
    fire_premium INTEGER,
    crime_peril SMALLINT,
    crime_premium INTEGER,
    flood_peril SMALLINT,
    flood_premium INTEGER,
    weather_peril SMALLINT,
    weather_premium INTEGER,
    status SMALLINT,
    rejection_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_commercial_policy FOREIGN KEY (policy_number) 
        REFERENCES policy(policy_number) ON DELETE CASCADE
);

-- Create index on commercial location
CREATE INDEX idx_commercial_zipcode ON commercial(zipcode);

-- Create claim table
CREATE TABLE claim (
    claim_number SERIAL PRIMARY KEY,
    policy_number INTEGER NOT NULL,
    claim_date DATE,
    paid INTEGER,
    value INTEGER,
    cause VARCHAR(255),
    observations VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_claim_policy FOREIGN KEY (policy_number) 
        REFERENCES policy(policy_number) ON DELETE CASCADE
);

-- Create index on claim policy
CREATE INDEX idx_claim_policy ON claim(policy_number);
CREATE INDEX idx_claim_date ON claim(claim_date);

-- Create trigger function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers for all tables
CREATE TRIGGER update_customer_updated_at BEFORE UPDATE ON customer
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_policy_updated_at BEFORE UPDATE ON policy
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_endowment_updated_at BEFORE UPDATE ON endowment
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_house_updated_at BEFORE UPDATE ON house
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_motor_updated_at BEFORE UPDATE ON motor
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_commercial_updated_at BEFORE UPDATE ON commercial
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_claim_updated_at BEFORE UPDATE ON claim
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Made with Bob
