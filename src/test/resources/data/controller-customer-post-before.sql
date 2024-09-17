DELETE FROM controltest.customer;
ALTER TABLE controltest.customer AUTO_INCREMENT = 0;
INSERT INTO controltest.customer (name, national_document, email, register_date, uuid)
       VALUES("Aeroporto Numenor", "12445678901", "legolas@example.com", CURRENT_TIMESTAMP, "adc49298-71d9-11ef-8bff-0242ac110002");
