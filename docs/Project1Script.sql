SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `facility_management_system` ;
CREATE SCHEMA IF NOT EXISTS `facility_management_system` DEFAULT CHARACTER SET utf8 ;
USE `facility_management_system` ;

-- -----------------------------------------------------
-- Table `facility_management_system`.`facility`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`facility` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`facility` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `FacilityName` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facility_management_system`.`information`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`information` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`information` (
  `AvailableCapacity` INT(11) NULL DEFAULT NULL,
  `TotalCapacity` INT(11) NULL DEFAULT NULL,
  `Description` VARCHAR(40) NULL DEFAULT NULL,
  `Status` VARCHAR(40) NULL DEFAULT NULL,
  `Id` INT(11) NULL,
  `Info_Id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Info_Id`),
  INDEX `Facility_Information` (`Id` ASC),
  CONSTRAINT `Facility_Information`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facility_management_system`.`inspections`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`inspections` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`inspections` (
  `Id` INT(11) NULL,
  `Date` DATETIME NULL DEFAULT NULL,
  `ProblemStatus` VARCHAR(40) NULL DEFAULT NULL,
  `InspectionId` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`InspectionId`),
  INDEX `Facility_Inspections` (`Id` ASC),
  CONSTRAINT `Facility_Inspections`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facility_management_system`.`maintenance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`maintenance` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`maintenance` (
  `Id` INT(11) NULL,
  `Cost` DOUBLE NULL DEFAULT NULL,
  `StartTime` DATETIME NULL DEFAULT NULL,
  `EndTime` DATETIME NULL,
  `MainId` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`MainId`),
  INDEX `Facility_Maintenance` (`Id` ASC),
  CONSTRAINT `Facility_Maintenance`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facility_management_system`.`maintenancerequest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`maintenancerequest` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`maintenancerequest` (
  `Date` DATETIME NULL,
  `Id` INT(11) NULL,
  `Status` VARCHAR(40) NULL,
  `RequestId` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`RequestId`),
  INDEX `Facility_MaintenanceRequest` (`Id` ASC),
  CONSTRAINT `Facility_MaintenanceRequest`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facility_management_system`.`problem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`problem` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`problem` (
  `ProblemId` INT(11) NOT NULL AUTO_INCREMENT,
  `ProblemType` VARCHAR(40) NULL DEFAULT NULL,
  `Status` VARCHAR(40) NULL DEFAULT NULL,
  `Id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ProblemId`),
  INDEX `Facility_Problem` (`Id` ASC),
  CONSTRAINT `Facility_Problem`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `facility_management_system`.`uselog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `facility_management_system`.`uselog` ;

CREATE TABLE IF NOT EXISTS `facility_management_system`.`uselog` (
  `StartTime` DATETIME NULL,
  `EndTime` DATETIME NULL DEFAULT NULL,
  `IsInUse` TINYINT(1) NULL DEFAULT True,
  `Id` INT(11) NULL,
  `UseId` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`UseId`),
  INDEX `Facility_UseLog` (`Id` ASC),
  CONSTRAINT `Facility_UseLog`
    FOREIGN KEY (`Id`)
    REFERENCES `facility_management_system`.`facility` (`Id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
