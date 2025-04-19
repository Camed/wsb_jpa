insert into address (id, address_line1, address_line2, city, postal_code)
            values (901, 'xx', 'yy', 'city', '60-400');

insert into address (address_line1, address_line2, city, postal_code)
    values ('test', 'test', 'choroszcz', '21-37');


INSERT INTO Patient
        (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_under_regular_observation)
VALUES
        (1, 'Adam', 'Małysz', '123456789', 'test@test.test', '123', '01/04/2025', false);


INSERT INTO Patient
        (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, is_under_regular_observation)
VALUES
        (2, 'Robert', 'Kubica', '987654321', 'tset@tset.tset', '124', '31/03/2025', false);

INSERT INTO Doctor
        (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES
        (1, 'Mariusz', 'Pudzianowski', '111222333', 'm@pudzian.pl', '1', 1);


INSERT INTO Doctor
        (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES
        (2, 'Robert', 'Makłowicz', '333222111', 'r@maklowicz.pl', '2', 2);

INSERT INTO Visit
        (id, time, patient_id, doctor_id)
VALUES
        (1, "07/04/2025", 1, 1);