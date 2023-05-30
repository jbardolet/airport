use airport;




/**INSERTS**/
INSERT INTO cities (City_Name, Country) VALUES ('city 1', 'Country 1');
INSERT INTO cities (City_Name, Country) VALUES ('city 2', 'Country 2');
INSERT INTO people (passport, firstName, lastName, date_birth, id_city) VALUES (1234, 'aaa', 'bbb', '1991-12-20', 1);
INSERT INTO people (passport, firstName, lastName, date_birth, id_city) VALUES (1235, 'bbb', 'ccc', '1987-11-21', 1);
INSERT INTO people (passport, firstName, lastName, date_birth, id_city) VALUES (1236, 'ccc', 'ddd', '1988-10-21', 1);
INSERT INTO workers_information (id_worker, start_day) VALUES (1235, '2015-05-18');
INSERT INTO workers_information (id_worker, start_day) VALUES (1236, '2015-05-20');
INSERT INTO pilot_license (idPilot,pilot_license) VALUES (1236, 'BB1111');
INSERT INTO passengers (passport) VALUES (1234);
INSERT INTO luggage (idLuggage, price, description) VALUES (1, 50.3, 'weight up to 30 Kg');
INSERT INTO luggage (idLuggage, price, description) VALUES (2, 70.3, 'weight up to 80 Kg');
INSERT INTO airlines (name) VALUES ('airair');
INSERT INTO airplanes (name, id_Airline) VALUES ('Plane 1', 1);
INSERT INTO airplanes (name, id_Airline) VALUES ('Plane 2', 1);
INSERT INTO seats (seatNumber,seatLetter,Id_airplanne, price) VALUE (18, 'B', 1, 200);
INSERT INTO seats (seatNumber,seatLetter,Id_airplanne, price) VALUE (17, 'B', 1, 200);
INSERT INTO seats (id,seatNumber,seatLetter,Id_airplanne, price) VALUE (1, 20, 'C', 1, 150);
/**INSERT INTO seats (id, seatNumber,seatLetter,Id_airplanne, price) VALUE (2, 23, 'J', null, 150);**/
INSERT INTO trips (id_Airplane,id_from,id_to,time_departure) VALUE (1,1,2,'2023-05-30 08:00:00');
INSERT INTO tickets (ticketId,id_passenger,id_trip, id_seat,id_luggage) VALUES (2,1234,1,1,1);
INSERT INTO crew_services(id_TripService,id_trip,id_worker) VALUES (1,1,1236);
INSERT INTO crew_services(id_TripService,id_trip,id_worker) VALUES (2,1,1235);


/**UPDATE**/
UPDATE luggage SET price = 60 WHERE idLuggage = 2;
UPDATE workers_information SET start_day = '2015-06-20' WHERE id_worker = 1236;
UPDATE airlines SET name = 'airairair' WHERE idAirlines = 1;
UPDATE cities SET Country = 'country 2' WHERE idCity = 1;
UPDATE pilot_license SET pilot_license = 'SS666' WHERE idPilot = 1236;
UPDATE seats SET seatLetter = 'C' WHERE id = 1;
UPDATE people SET firstName = 'bbbb', lastName='vvvv' WHERE passport = 1234;


/**DELETE**/
DELETE FROM luggage WHERE idLuggage = 2;
DELETE FROM workers_information WHERE id_worker = 1236;
DELETE FROM airlines WHERE idAirlines = 1;
DELETE FROM cities WHERE idCity = 1;
DELETE FROM pilot_license WHERE idPilot = 1236;
DELETE FROM seats WHERE id = 1;
DELETE FROM people WHERE passort = 1234;

/**ALTER TABLE**/
ALTER TABLE luggage ADD moreInfo varchar(20);
SELECT * FROM luggage;
ALTER TABLE luggage DROP COLUMN moreInfo;
ALTER TABLE luggage ADD UNIQUE (idLuggage);
ALTER TABLE seats ADD UNIQUE (id);
ALTER TABLE luggage modify COLUMN price varchar(20);
ALTER TABLE luggage modify COLUMN price decimal;


/**JOIN ALL TABLES**/


SELECT * FROM people 
JOIN workers_information ON people.passport=workers_information.id_worker
JOIN pilot_license ON pilot_license.idPilot=workers_information.id_worker
JOIN passengers ON people.passport=passengers.Passport
JOIN tickets ON passengers.Passport=tickets.id_passenger
JOIN trips ON tickets.id_trip=trips.idTrip
JOIN airplanes ON airplanes.idAirplane=trips.id_Airplane
JOIN seats ON seats.Id_airplanne = airplanes.idAirplane
JOIN airlines ON airlines.idAirlines=airplanes.id_Airline
JOIn luggage ON luggage.idLuggage=tickets.id_luggage
JOIN crew_services ON crew_services.id_trip=trips.idTrip
JOIN cities ON cities.idCity = people.id_city;

select * from seats;

/**JOINS**/
SELECT seats.id FROM seats INNER JOIN airplanes ON seats.Id_airplanne= airplanes.idAirplane;
SELECT seats.id FROM seats RIGHT JOIN airplanes ON seats.Id_airplanne= airplanes.idAirplane; /**one null register bc int the right table - aiplane we have one register that it is not in the left**/
SELECT seats.id FROM seats LEFT JOIN airplanes ON seats.Id_airplanne= airplanes.idAirplane;

SELECT * FROM seats LEFT JOIN airplanes ON seats.Id_airplanne= airplanes.idAirplane
UNION
SELECT * FROM seats RIGHT JOIN airplanes ON seats.Id_airplanne= airplanes.idAirplane;

/**AGGREGATE FUNCTIONS WITHOUT HAVING**/
SELECT MIN(price) AS SmallestPrice FROM luggage;
SELECT COUNT(id) FROM seats group by Id_airplanne;



/**AGGREGATE FUNCTIONS WITH HAVING**/
SELECT COUNT(id), Id_airplanne FROM seats group by Id_airplanne HAVING COUNT(id) > 1 ;

