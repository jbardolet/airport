-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema airport
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema airport
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airport` DEFAULT CHARACTER SET utf8 ;
USE `airport` ;

-- -----------------------------------------------------
-- Table `airport`.`Airlines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Airlines` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAirlines_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Airplanes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Airplanes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `id_Airline` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idAirplane_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_Airlines_idx` (`id_Airline` ASC) VISIBLE,
  CONSTRAINT `id_Airlines`
    FOREIGN KEY (`id_Airline`)
    REFERENCES `airport`.`Airlines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Seats` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seatNumber` INT NOT NULL,
  `seatLetter` VARCHAR(45) NOT NULL,
  `Id_airplanne` INT NULL,
  `price` DECIMAL(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `Ariplane_id_idx` (`Id_airplanne` ASC) VISIBLE,
  CONSTRAINT `Ariplane_id`
    FOREIGN KEY (`Id_airplanne`)
    REFERENCES `airport`.`Airplanes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Cities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Cities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `City_Name` VARCHAR(45) NULL,
  `Country` VARCHAR(45) NULL,
  `Seats_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cities_Seats1_idx` (`Seats_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cities_Seats1`
    FOREIGN KEY (`Seats_id`)
    REFERENCES `airport`.`Seats` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`People`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`People` (
  `id` INT NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `date_birth` DATE NOT NULL,
  `id_city` INT NULL,
  `worker_id` INT NULL,
  `start_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `City_id_idx` (`id_city` ASC) VISIBLE,
  UNIQUE INDEX `worker_id_UNIQUE` (`worker_id` ASC) VISIBLE,
  CONSTRAINT `City_id`
    FOREIGN KEY (`id_city`)
    REFERENCES `airport`.`Cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Pilot_license`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Pilot_license` (
  `id` INT NOT NULL,
  `pilot_license` VARCHAR(45) NULL,
  `id_Worker` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_worker_idx` (`id_Worker` ASC) VISIBLE,
  CONSTRAINT `id_worker`
    FOREIGN KEY (`id_Worker`)
    REFERENCES `airport`.`People` (`worker_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Trips`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Trips` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_Airplane` INT NULL,
  `id_from` INT NULL,
  `id_to` INT NULL,
  `time_departure` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idTrip_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_city_from_idx` (`id_from` ASC) VISIBLE,
  INDEX `id_city_to_idx` (`id_to` ASC) VISIBLE,
  INDEX `id_airplane_idx` (`id_Airplane` ASC) VISIBLE,
  CONSTRAINT `id_city_from`
    FOREIGN KEY (`id_from`)
    REFERENCES `airport`.`Cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_city_to`
    FOREIGN KEY (`id_to`)
    REFERENCES `airport`.`Cities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_airplane`
    FOREIGN KEY (`id_Airplane`)
    REFERENCES `airport`.`Airplanes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Luggage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Luggage` (
  `id` INT NOT NULL,
  `price` DECIMAL NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Food` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Tickets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_passenger` INT NULL,
  `id_trip` INT NULL,
  `id_luggage` INT NULL,
  `id_seats` INT NOT NULL,
  `id_food` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_trip_idx` (`id_trip` ASC) VISIBLE,
  INDEX `id_luggage_idx` (`id_luggage` ASC) VISIBLE,
  INDEX `id_passenger_idx` (`id_passenger` ASC) VISIBLE,
  INDEX `fk_Tickets_Seats1_idx` (`id_seats` ASC) VISIBLE,
  INDEX `fk_food_idx` (`id_food` ASC) VISIBLE,
  CONSTRAINT `fk_trip`
    FOREIGN KEY (`id_trip`)
    REFERENCES `airport`.`Trips` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_luggage`
    FOREIGN KEY (`id_luggage`)
    REFERENCES `airport`.`Luggage` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passenger`
    FOREIGN KEY (`id_passenger`)
    REFERENCES `airport`.`People` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tickets_Seats1`
    FOREIGN KEY (`id_seats`)
    REFERENCES `airport`.`Seats` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food`
    FOREIGN KEY (`id_food`)
    REFERENCES `airport`.`Food` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Crew_Services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Crew_Services` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_trip` INT NOT NULL,
  `People_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idTripService_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_trip_idx` (`id_trip` ASC) VISIBLE,
  INDEX `fk_Crew_Services_People1_idx` (`People_id` ASC) VISIBLE,
  CONSTRAINT `id_trip`
    FOREIGN KEY (`id_trip`)
    REFERENCES `airport`.`Trips` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Crew_Services_People1`
    FOREIGN KEY (`People_id`)
    REFERENCES `airport`.`People` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airport`.`Gate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airport`.`Gate` (
  `id` INT NOT NULL,
  `number` INT NOT NULL,
  `id_airline` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_airline_idx` (`id_airline` ASC) VISIBLE,
  CONSTRAINT `fk_airline`
    FOREIGN KEY (`id_airline`)
    REFERENCES `airport`.`Airlines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
