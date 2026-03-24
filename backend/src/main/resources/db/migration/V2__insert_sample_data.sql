-- Insert sample customers (based on ksdscust.txt)
INSERT INTO customer (customer_number, first_name, last_name, date_of_birth, house_name, house_number, postcode, phone_home, phone_mobile, email_address) VALUES
(1, 'Andrew', 'Pandy', '1950-07-11', '', '34', 'PI101O', '01962 811234', '07799 123456', 'A.PANDY@BEEBHOUSE.COM'),
(2, 'Scott', 'Tracey', '1965-09-30', '', '1', 'TB14TV', '001 911911', '', 'REFROOM@TBHOLDINGS.COM'),
(3, 'John', 'Noakes', '1934-03-06', '', '70', 'HX116B', '0207 325656', '09008 329855', 'Noaksey@beebhouse.com'),
(4, 'Louie', 'Pug', '1969-09-06', '', '21', 'PP159D', '0208 344344', '', ''),
(5, 'Graham', 'Cuthbert', '1967-01-03', '', '55', 'TR68BK', '0208 344344', '01101 499787', 'A.Cut@CG.co.uk'),
(6, 'Brian', 'Cant', '1933-07-12', '', '58', 'IP221P', '01855 122134', '', 'CANT@beebhouse.com'),
(7, 'Troy', 'Tempest', '1964-10-04', '', '48', 'ST106R', '', '', 'TROYT@BUSCOMM.COM'),
(8, 'Johnny', 'Morris', '1961-06-20', '', '72', 'BS83HA', '0345 245245', '0345 245245', 'JM@ZOOLAND.CO.UK'),
(9, 'Micky', 'Murphy', '1966-01-03', '', '51', 'CA316R', '', '', ''),
(10, 'Susan', 'Stranks', '1938-12-02', '', '68', 'W1A4WW', '0207 845845', '', '');

-- Reset customer sequence to start from 11
SELECT setval('customer_customer_number_seq', 11, false);

-- Insert sample policies
-- Motor policy for customer 2
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (1, 2, '2024-01-01', '2025-01-01', 'M', 1001, 'BRK001', 85000, 10);

INSERT INTO motor (policy_number, make, model, value, reg_number, colour, cc, year_of_manufacture, premium, accidents)
VALUES (1, 'Ford', 'KA', 85000, 'LL60LOO', 'Red', 1200, '2020-01-01', 85000, 0);

-- Motor policy for customer 10
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (2, 10, '2024-02-01', '2025-02-01', 'M', 1001, 'BRK002', 60000, 10);

INSERT INTO motor (policy_number, make, model, value, reg_number, colour, cc, year_of_manufacture, premium, accidents)
VALUES (2, 'Volkswagen', 'Beetle', 60000, 'A567WWR', 'Blue', 1600, '2018-01-01', 60000, 0);

-- Motor policy for customer 5
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (3, 5, '2024-03-01', '2025-03-01', 'M', 1002, 'BRK003', 235000, 10);

INSERT INTO motor (policy_number, make, model, value, reg_number, colour, cc, year_of_manufacture, premium, accidents)
VALUES (3, 'Dennis', 'Engine', 235000, 'FIRE1', 'Red', 5000, '2019-01-01', 235000, 0);

-- Endowment policy for customer 8
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (4, 8, '2024-04-01', '2044-04-01', 'E', 1003, 'BRK004', 50000, 5);

INSERT INTO endowment (policy_number, equities, with_profits, managed_fund, fund_name, term, sum_assured, life_assured)
VALUES (4, 'Y', 'Y', 'N', 'LIONTMR', 20, 50000, 'J. MORRIS');

-- Endowment policy for customer 3
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (5, 3, '2024-05-01', '2044-05-01', 'E', 1003, 'BRK005', 40000, 5);

INSERT INTO endowment (policy_number, equities, with_profits, managed_fund, fund_name, term, sum_assured, life_assured)
VALUES (5, 'N', 'N', 'N', 'SHEPPA', 20, 40000, 'Shep');

-- House policy for customer 4
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (6, 4, '2024-06-01', '2025-06-01', 'H', 1004, 'BRK006', 150000, 8);

INSERT INTO house (policy_number, property_type, bedrooms, value, house_name, house_number, postcode)
VALUES (6, 'House', 5, 1500000, '', '1', 'SO211UP');

-- House policy for customer 6
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (7, 6, '2024-07-01', '2025-07-01', 'H', 1004, 'BRK007', 375000, 8);

INSERT INTO house (policy_number, property_type, bedrooms, value, house_name, house_number, postcode)
VALUES (7, 'Farm', 0, 4037500, 'Home Farm', '', 'SO529ED');

-- House policy for customer 9
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (8, 9, '2024-08-01', '2025-08-01', 'H', 1004, 'BRK008', 26000, 8);

INSERT INTO house (policy_number, property_type, bedrooms, value, house_name, house_number, postcode)
VALUES (8, 'Flat', 1, 260000, '', '', 'E15WW');

-- Commercial policy for customer 5
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (9, 5, '2024-09-01', '2025-09-01', 'C', 1005, 'BRK009', 100000, 12);

INSERT INTO commercial (policy_number, start_date, renewal_date, address, zipcode, customer_name, property_type, fire_peril, fire_premium, crime_peril, crime_premium, flood_peril, flood_premium, weather_peril, weather_premium, status, rejection_reason)
VALUES (9, '2024-09-01', '2025-09-01', 'Clarets Merchandise', 'BB104BX', 'Graham Cuthbert', 'Retail', 100, 50000, 50, 25000, 30, 15000, 20, 10000, 1, '');

-- Commercial policy for customer 1
INSERT INTO policy (policy_number, customer_number, issue_date, expiry_date, policy_type, broker_id, brokers_reference, payment, commission) 
VALUES (10, 1, '2024-10-01', '2025-10-01', 'C', 1005, 'BRK010', 150000, 12);

INSERT INTO commercial (policy_number, start_date, renewal_date, address, zipcode, customer_name, property_type, fire_peril, fire_premium, crime_peril, crime_premium, flood_peril, flood_premium, weather_peril, weather_premium, status, rejection_reason)
VALUES (10, '2024-10-01', '2025-10-01', 'IBM', 'SO212JN', 'Andrew Pandy', 'Office', 80, 60000, 60, 30000, 40, 20000, 30, 15000, 1, '');

-- Reset policy sequence to start from 11
SELECT setval('policy_policy_number_seq', 11, false);

-- Made with Bob
