-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema soil_and_pimp_live
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `soil_and_pimp_live` ;

-- -----------------------------------------------------
-- Schema soil_and_pimp_live
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `soil_and_pimp_live` DEFAULT CHARACTER SET utf8 ;
USE `soil_and_pimp_live` ;

-- -----------------------------------------------------
-- Table `soil_and_pimp_live`.`events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_and_pimp_live`.`events` ;

CREATE TABLE IF NOT EXISTS `soil_and_pimp_live`.`events` (
  `event_key` VARCHAR(50) NOT NULL,
  `name` TEXT CHARACTER SET 'utf8mb4' NOT NULL,
  `social_networking_title` TEXT CHARACTER SET 'utf8mb4' NULL,
  `memo` TEXT CHARACTER SET 'utf8mb4' NULL,
  `event_url` TEXT CHARACTER SET 'utf8mb4' NULL,
  `jvc_url` TEXT CHARACTER SET 'utf8mb4' NULL,
  `open_date` DATETIME NULL,
  `schedule_change` TINYINT NULL,
  `broadcast` TINYINT NULL,
  PRIMARY KEY (`event_key`),
  UNIQUE INDEX `event_key_UNIQUE` (`event_key` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `soil_and_pimp_live`.`schedules`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_and_pimp_live`.`schedules` ;

CREATE TABLE IF NOT EXISTS `soil_and_pimp_live`.`schedules` (
  `schedule_id` INT NOT NULL AUTO_INCREMENT,
  `event_key` VARCHAR(50) NOT NULL,
  `date` DATE NULL,
  `enter_time` VARCHAR(45) NULL,
  `start_time` VARCHAR(45) NULL,
  `prefecture` TEXT CHARACTER SET 'utf8mb4' NULL,
  `place` TEXT CHARACTER SET 'utf8mb4' NULL,
  `call` TEXT CHARACTER SET 'utf8mb4' NULL,
  `memo` TEXT CHARACTER SET 'utf8mb4' NULL,
  `link` TEXT CHARACTER SET 'utf8mb4' NULL,
  INDEX `fk_schedules_events_idx` (`event_key` ASC),
  PRIMARY KEY (`schedule_id`),
  UNIQUE INDEX `schedule_id_UNIQUE` (`schedule_id` ASC),
  CONSTRAINT `fk_schedules_events`
    FOREIGN KEY (`event_key`)
    REFERENCES `soil_and_pimp_live`.`events` (`event_key`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `soil_and_pimp_live`.`email_subscriptions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_and_pimp_live`.`email_subscriptions` ;

CREATE TABLE IF NOT EXISTS `soil_and_pimp_live`.`email_subscriptions` (
  `email_address` VARCHAR(100) CHARACTER SET 'utf8mb4' NOT NULL,
  PRIMARY KEY (`email_address`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `soil_and_pimp_live`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_and_pimp_live`.`users` ;

CREATE TABLE IF NOT EXISTS `soil_and_pimp_live`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(100) NULL,
  `user_role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `soil_and_pimp_live`.`user_api_identity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `soil_and_pimp_live`.`user_api_identity` ;

CREATE TABLE IF NOT EXISTS `soil_and_pimp_live`.`user_api_identity` (
  `user_api_identity_id` INT NOT NULL AUTO_INCREMENT,
  `api_type` VARCHAR(45) NOT NULL,
  `api_user_id` VARCHAR(45) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`user_api_identity_id`),
  UNIQUE INDEX `user_api_identity_id_UNIQUE` (`user_api_identity_id` ASC),
  INDEX `fk_user_api_identity_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_user_api_identity_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `soil_and_pimp_live`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO soilandpimplive;
 DROP USER soilandpimplive;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'soilandpimplive' IDENTIFIED BY 'soilandpimplive69';

GRANT ALL ON `soil_and_pimp_live`.* TO 'soilandpimplive';
GRANT SELECT, INSERT, TRIGGER ON TABLE `soil_and_pimp_live`.* TO 'soilandpimplive';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `soil_and_pimp_live`.* TO 'soilandpimplive';
GRANT SELECT ON TABLE `soil_and_pimp_live`.* TO 'soilandpimplive';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


ALTER DATABASE soil_and_pimp_live CHARACTER SET = utf8mb4 
    COLLATE = utf8mb4_unicode_ci;