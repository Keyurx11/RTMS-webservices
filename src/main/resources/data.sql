-- If you want to try with some data here is mock data, run it in your postgres

INSERT INTO address (street, city, state, postal_code, country)
VALUES ('123 Main St', 'New York', 'NY', '10001', 'USA'),
       ('456 Elm St', 'Los Angeles', 'CA', '90001', 'USA'),
       ('789 Oak St', 'Chicago', 'IL', '60601', 'USA'),
       ('321 Pine St', 'Houston', 'TX', '77001', 'USA'),
       ('654 Maple St', 'San Francisco', 'CA', '94101', 'USA');

INSERT INTO car_owner (first_name, last_name, email, phone_number, address_id)
VALUES ('John', 'Doe', 'johndoe@example.com', '123-456-7890', 1),
       ('Jane', 'Doe', 'janedoe@example.com', '234-567-8901', 2),
       ('Bob', 'Smith', 'bobsmith@example.com', '345-678-9012', 3),
       ('Alice', 'Johnson', 'alicejohnson@example.com', '456-789-0123', 4),
       ('Mike', 'Brown', 'mikebrown@example.com', '567-890-1234', 5);

INSERT INTO tax_category (name, description, tax_rate_id)
VALUES ('Category A', 'Description for Category A', 1),
       ('Category B', 'Description for Category B', 2),
       ('Category C', 'Description for Category C', 3),
       ('Category D', 'Description for Category D', 4),
       ('Category E', 'Description for Category E', 5);

INSERT INTO car_registration (registration_number, make, model, year, tax_category_id, car_owner_id)
VALUES ('ABC123', 'Honda', 'Accord', 2010, 1, 1),
       ('DEF456', 'Toyota', 'Camry', 2015, 2, 2),
       ('GHI789', 'Ford', 'Fusion', 2018, 3, 3),
       ('JKL012', 'Chevrolet', 'Malibu', 2020, 4, 4),
       ('MNO345', 'Tesla', 'Model S', 2021, 5, 5);

INSERT INTO tax_information (start_date, end_date, amount, car_registration_id)
VALUES ('2021-01-01', '2021-12-31', 500.00, 1),
       ('2021-01-01', '2021-12-31', 600.00, 2),
       ('2021-01-01', '2021-12-31', 700.00, 3),
       ('2021-01-01', '2021-12-31', 800.00, 4),
       ('2021-01-01', '2021-12-31', 900.00, 5);

INSERT INTO tax_payment (payment_date, amount, car_registration_id)
VALUES ('2022-01-01', 200, 1),
       ('2022-07-01', 200, 1),
       ('2023-01-01', 200, 1),
       ('2023-05-01', 200, 2),
       ('2024-01-01', 200, 2);

INSERT INTO tax_rate (tax_category_id, rate, effective_date, expiration_date)
VALUES (1, 0.02, '2021-01-01', '2021-12-31'),
       (2, 0.015, '2021-01-01', '2021-12-31'),
       (3, 0.025, '2021-01-01', '2021-12-31'),
       (1, 0.025, '2022-01-01', '2022-12-31'),
       (2, 0.02, '2022-01-01', '2022-12-31');

