INSERT INTO controltest.customer (id, name, national_document, email, register_date)
       VALUES(1, 'API TESTE', '0000000', 'api@test.com', CURRENT_TIMESTAMP);
ALTER TABLE controltest.person AUTO_INCREMENT = 0;
