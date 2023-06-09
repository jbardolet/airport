use airport;
/*DROP DATABASE airport;*/

/**DROP TABLE cities;**/


/**INSERTS**/
INSERT INTO cities (City_Name, Country) VALUES ('city 1', 'Country 1');
INSERT INTO cities (City_Name, Country) VALUES ('city 2', 'Country 2');
INSERT INTO people (id, firstName, lastName, date_birth, id_city, worker_id,start_date) VALUES (1234, 'aaa', 'bbb', '1991-12-20', 1, null, null);
INSERT INTO people (id, firstName, lastName, date_birth, id_city, worker_id,start_date) VALUES (1235, 'bbb', 'ccc', '1987-11-21', 1, 1111,'2015-05-18');
INSERT INTO people (id, firstName, lastName, date_birth, id_city, worker_id,start_date) VALUES (1236, 'ccc', 'ddd', '1988-10-21', 1, 1112, '2015-05-20');
INSERT INTO pilot_license (id ,pilot_license, id_Worker) VALUES (1, 'BB1111', 1236);
INSERT INTO luggage (id, price, description) VALUES (1, 50.3, 'weight up to 30 Kg');
INSERT INTO luggage (id, price, description) VALUES (2, 70.3, 'weight up to 80 Kg');
INSERT INTO airlines (name) VALUES ('airair');
INSERT INTO airplanes (name, id_Airline) VALUES ('Plane 1', 1);
INSERT INTO airplanes (name, id_Airline) VALUES ('Plane 2', 1);
INSERT INTO seats (seatNumber,seatLetter,Id_airplanne, price) VALUE (18, 'B', 1, 200);
INSERT INTO seats (seatNumber,seatLetter,Id_airplanne, price) VALUE (17, 'B', 1, 200);
/**INSERT INTO seats (id,seatNumber,seatLetter,Id_airplanne, price) VALUE (1, 20, 'C', 1, 150);**/
/**INSERT INTO seats (id, seatNumber,seatLetter,Id_airplanne, price) VALUE (2, 23, 'J', null, 150);**/
INSERT INTO trips (id_Airplane,id_from,id_to,time_departure) VALUE (1,1,2,'2023-05-30 08:00:00');
INSERT INTO food (id,name,description) VALUES (1,'Vegetarian','vegetarian food');
INSERT INTO tickets (id,id_passenger,id_trip, id_luggage, id_seats,id_food) VALUES (2,1234,1,1,1,1);
INSERT INTO crew_services(id,id_trip,People_id) VALUES (1,1,1236);
INSERT INTO crew_services(id,id_trip,People_id) VALUES (2,1,1235);
INSERT INTO gate (id,number,id_airline) VALUES (1, 222, 1);

/**UPDATE**/
UPDATE luggage SET price = 60 WHERE id = 2;
UPDATE people SET start_date = '2015-06-20' WHERE id = 1236;
UPDATE airlines SET name = 'airairair' WHERE id = 1;
UPDATE cities SET Country = 'country 2' WHERE id = 1;
UPDATE pilot_license SET pilot_license = 'SS666' WHERE id = 1236;
UPDATE seats SET seatLetter = 'C' WHERE id = 1;
UPDATE people SET firstName = 'bbbb', lastName='vvvv' WHERE id = 1234;


/**DELETE**/
DELETE FROM luggage WHERE id = 2;
DELETE FROM people WHERE id = 1236;
DELETE FROM airlines WHERE id = 1;
DELETE FROM cities WHERE id = 1;
DELETE FROM pilot_license WHERE id = 1236;
DELETE FROM seats WHERE id = 1;

/**ALTER TABLE**/
ALTER TABLE luggage ADD moreInfo varchar(20);
SELECT * FROM luggage;
ALTER TABLE luggage DROP COLUMN moreInfo;
ALTER TABLE luggage ADD UNIQUE (id);
ALTER TABLE seats ADD UNIQUE (id);
ALTER TABLE luggage modify COLUMN price varchar(20);
ALTER TABLE luggage modify COLUMN price decimal;


/**JOIN ALL TABLES**/
SELECT * FROM people 
JOIN pilot_license ON pilot_license.id_Worker = people.id
JOIN tickets ON people.id=tickets.id_passenger
JOIN trips ON tickets.id_trip=trips.id
JOIN airplanes ON airplanes.id=trips.id_Airplane
JOIN seats ON seats.Id_airplanne = airplanes.id
JOIN airlines ON airlines.id=airplanes.id_Airline
JOIn luggage ON luggage.id=tickets.id_luggage
JOIN crew_services ON crew_services.id_trip=trips.id
JOIN cities ON cities.id = people.id_city
JOIN gate ON airlines.id = gate.id_airline;

select * from seats;

/**JOINS**/
SELECT seats.id FROM seats INNER JOIN airplanes ON seats.Id_airplanne= airplanes.id;
SELECT seats.id FROM seats RIGHT JOIN airplanes ON seats.Id_airplanne= airplanes.id; /**one null register bc int the right table - aiplane we have one register that it is not in the left**/
SELECT seats.id FROM seats LEFT JOIN airplanes ON seats.Id_airplanne= airplanes.id;

SELECT * FROM seats LEFT JOIN airplanes ON seats.Id_airplanne= airplanes.id
UNION
SELECT * FROM seats RIGHT JOIN airplanes ON seats.Id_airplanne= airplanes.id;

/**AGGREGATE FUNCTIONS WITHOUT HAVING**/
SELECT MIN(price) AS SmallestPrice FROM luggage;
SELECT COUNT(id) FROM seats group by Id_airplanne;
SELECT MAX(price) AS MaxPrice FROM seats;
SELECT AVG(price) FROM seats;
SELECT SUM(seats.price+luggage.price) FROM seats JOIN tickets ON tickets.id_seats = seats.id JOIN luggage ON tickets.id_luggage = luggage.id WHERE tickets.id = 2;


/**AGGREGATE FUNCTIONS WITH HAVING**/
SELECT COUNT(id), Id_airplanne FROM seats group by Id_airplanne HAVING COUNT(id) > 1 ;
SELECT SUM(seats.price+luggage.price) FROM seats JOIN tickets ON tickets.id_seats = seats.id JOIN luggage ON tickets.id_luggage = luggage.id group by tickets.id HAVING tickets.id =2;
SELECT AVG(price) FROM seats group by id HAVING (id) > 1;