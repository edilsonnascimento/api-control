ALTER TABLE controltest.customer AUTO_INCREMENT = 0;
INSERT INTO controltest.customer (name, national_document, email, register_date, uuid)
       VALUES('API TESTE', '0000000', 'api@test.com', CURRENT_TIMESTAMP, "59e57782-0dae-48c1-a9f4-048f7d27695f");
ALTER TABLE controltest.person AUTO_INCREMENT = 0;
DELETE FROM controltest.person;
INSERT INTO controltest.person (name, enrolment, email, admission_date, register_date, customer_id, external_id, last_update_date, uuid)
       VALUES('PEDRO', '1234ABC', 'pedro@apostolo.com', '1980-10-10', CURRENT_TIMESTAMP, 1, 'UURSL9009', CURRENT_TIMESTAMP, "adc48803-71d9-11ef-8bff-0242ac110002");
DELETE FROM controltest.task;
INSERT INTO controltest.task (description, status, person_id, register_date, customer_id, uuid)
       VALUES("Faith Helle", "ACTIVE", 1, CURRENT_TIMESTAMP, 1, "ebfc9ffa-4997-436f-8733-08b7d08f67ae");