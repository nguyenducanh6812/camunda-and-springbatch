-- Insert records into Customer table if not exist
MERGE INTO Customers AS target
    USING (
        VALUES (1, 'John Doe', '123 Main St', 'Good'),
               (2, 'Jane Smith', '456 Elm St', 'Excellent'),
               (3, 'Sam Johnson', '789 Oak St', 'Fair')
    ) AS source (id, name, address, credit)
    ON target.id = source.id
    WHEN NOT MATCHED THEN
        INSERT (id, name, address, credit)
            VALUES (source.id, source.name, source.address, source.credit);

-- Insert records into Order table if not exist
MERGE INTO Orders AS target
    USING (
        VALUES (1, 1, '2023-09-17', 1),
               (2, 2, '2023-09-18', 2),
               (3, 1, '2023-09-19', 1)
    ) AS source (id, customer_id, date, status)
    ON target.id = source.id
    WHEN NOT MATCHED THEN
        INSERT (id, customer_id, date, status)
            VALUES (source.id, source.customer_id, source.date, source.status);

-- Insert Payment records if not exist, related to existing customers
MERGE INTO Payment AS target
    USING (
        VALUES (1, '2023-09-18', 100.00),
               (2, '2023-09-19', 150.00),
               (3, '2023-09-20', 120.00)
    ) AS source (order_id, payment_date, amount)
    ON target.order_id = source.order_id
    WHEN NOT MATCHED THEN
        INSERT (order_id, payment_date, amount)
            VALUES (source.order_id, source.payment_date, source.amount);
-- Insert Transactions records if not exist, related to existing customers
MERGE INTO Transactions AS target
    USING (
        VALUES (1, '2023-09-18', 0),
               (2, '2023-09-19', 1),
               (3, '2023-09-20', 0)
    ) AS source (payment_id, date, money_laundering)
    ON target.payment_id = source.payment_id
    WHEN NOT MATCHED THEN
        INSERT (payment_id, date, money_laundering)
            VALUES (source.payment_id, source.date, source.money_laundering);