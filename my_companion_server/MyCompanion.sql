SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mycompanion
-- -----------------------------------------------------
-- DROP SCHEMA IF EXISTS `mycompanion` ;

-- -----------------------------------------------------
-- Schema mycompanion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mycompanion` DEFAULT CHARACTER SET utf8 ;
USE `mycompanion` ;

-- -----------------------------------------------------
-- Table `mycompanion`.`user`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `mycompanion`.`user` ;

CREATE TABLE IF NOT EXISTS `mycompanion`.`user` (
  `uuid` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `join_date` DATETIME NOT NULL,
  `enabled` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`uuid`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `mycompanion`.`contact`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `mycompanion`.`contact` ;

CREATE TABLE IF NOT EXISTS `mycompanion`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `enabled` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_mycompanion_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_mycompanion_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mycompanion`.`user` (`uuid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `mycompanion`.`configuration`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `mycompanion`.`configuration` ;

CREATE TABLE IF NOT EXISTS `mycompanion`.`configuration` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `greeting_phrase` VARCHAR(100) NULL,
  `wakeword_phrase` VARCHAR(100) NOT NULL,
  `save_speech` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `fk_mycompanion_configuration_users_idx` (`user_id` ASC),
  CONSTRAINT `fk_mycompanion_configuration_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mycompanion`.`user` (`uuid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `mycompanion`.`temperature`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `mycompanion`.`temperature` ;

CREATE TABLE IF NOT EXISTS `mycompanion`.`temperature` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `temperature` INT(11) NULL,
  `humidity` INT(11) NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mycompanion_temperature_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_mycompanion_temperature_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mycompanion`.`user` (`uuid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `mycompanion`.`user_response`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `mycompanion`.`user_response` ;

CREATE TABLE IF NOT EXISTS `mycompanion`.`user_response` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `type` ENUM('speech', 'action'),
  `data` VARCHAR(250) NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mycompanion_user_response_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_mycompanion_user_response_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mycompanion`.`user` (`uuid`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;
