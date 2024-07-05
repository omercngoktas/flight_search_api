-- Airport verileri ekle
INSERT INTO airport (airport_id, city) SELECT 1, 'Istanbul' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Istanbul');
INSERT INTO airport (airport_id, city) SELECT 2, 'Ankara' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Ankara');
INSERT INTO airport (airport_id, city) SELECT 3, 'Izmir' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Izmir');
INSERT INTO airport (airport_id, city) SELECT 4, 'Antalya' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Antalya');
INSERT INTO airport (airport_id, city) SELECT 5, 'Adana' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Adana');
INSERT INTO airport (airport_id, city) SELECT 6, 'Bursa' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Bursa');
INSERT INTO airport (airport_id, city) SELECT 7, 'Trabzon' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Trabzon');
INSERT INTO airport (airport_id, city) SELECT 8, 'Gaziantep' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Gaziantep');
INSERT INTO airport (airport_id, city) SELECT 9, 'Kayseri' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Kayseri');
INSERT INTO airport (airport_id, city) SELECT 10, 'Konya' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Konya');
INSERT INTO airport (airport_id, city) SELECT 11, 'Erzurum' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Erzurum');
INSERT INTO airport (airport_id, city) SELECT 12, 'Diyarbakir' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Diyarbakir');
INSERT INTO airport (airport_id, city) SELECT 13, 'Kars' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Kars');
INSERT INTO airport (airport_id, city) SELECT 14, 'Rize' WHERE NOT EXISTS (SELECT 1 FROM airport WHERE city = 'Rize');

-- Flight verileri ekle
INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 1, 1, 2, '2024-07-05', '2024-07-05', 250.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 1);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 2, 1, 3, '2024-07-06', '2024-07-06', 200.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 2);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 3, 2, 3, '2024-07-07', '2024-07-07', 180.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 3);

-- Yeni flight verileri
INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 4, 4, 5, '2024-07-08', '2024-07-08', 220.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 4);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 5, 6, 7, '2024-07-09', '2024-07-09', 170.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 5);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 6, 8, 9, '2024-07-10', '2024-07-10', 190.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 6);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 7, 10, 11, '2024-07-11', '2024-07-11', 240.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 7);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 8, 12, 13, '2024-07-12', '2024-07-12', 260.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 8);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 9, 14, 1, '2024-07-13', '2024-07-13', 210.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 9);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 10, 2, 4, '2024-07-14', '2024-07-14', 230.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 10);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 11, 1, 2, '2024-07-15', '2024-07-15', 250.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 11);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 12, 2, 1, '2024-07-16', '2024-07-16', 250.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 12);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 13, 3, 1, '2024-07-17', '2024-07-17', 200.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 13);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 14, 1, 3, '2024-07-18', '2024-07-18', 200.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 14);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 15, 5, 4, '2024-07-19', '2024-07-19', 220.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 15);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 16, 4, 5, '2024-07-20', '2024-07-20', 220.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 16);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 17, 7, 6, '2024-07-21', '2024-07-21', 170.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 17);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 18, 6, 7, '2024-07-22', '2024-07-22', 170.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 18);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 19, 9, 8, '2024-07-23', '2024-07-23', 190.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 19);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 20, 8, 9, '2024-07-24', '2024-07-24', 190.0 
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 20);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 21, 1, 2, '2024-07-15', '2024-07-15', 280.0
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 21);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 22, 2, 1, '2024-07-16', '2024-07-16', 310.0
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 22);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 23, 1, 2, '2024-07-15', '2024-07-15', 195.0
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 23);

INSERT INTO flight (flight_id, departure_airport_id, arrival_airport_id, departure_date_time, arrival_date_time, price) 
SELECT 24, 2, 1, '2024-07-16', '2024-07-16', 212.0
WHERE NOT EXISTS (SELECT 1 FROM flight WHERE flight_id = 24);