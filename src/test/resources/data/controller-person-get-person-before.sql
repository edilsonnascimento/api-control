ALTER TABLE controltest.customer AUTO_INCREMENT = 0;
INSERT INTO controltest.customer (id, name, national_document, email, register_date)
       VALUES(1, 'API TESTE', '0000000', 'api@test.com', CURRENT_TIMESTAMP);
ALTER TABLE controltest.person AUTO_INCREMENT = 0;
INSERT INTO controltest.person (name, enrolment, email, admission_date, register_date, customer_id, external_id, last_update_date)
       VALUES('PEDRO', '1234ABC', 'pedro@apostolo.com', '1980-10-10', CURRENT_TIMESTAMP, 1, 'UURSL9009', CURRENT_TIMESTAMP);