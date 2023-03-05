CREATE SCHEMA `test_rec` DEFAULT CHARACTER SET utf8 ;

USE `test_rec`;

CREATE TABLE `test_rec`.`base_person` (
  `ID` VARCHAR(64) NOT NULL,
  `FIRST_NAME` VARCHAR(255) NOT NULL,
  `LAST_NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `test_rec`.`person` (
  `ID` VARCHAR(64) NOT NULL,
  `EYE_COLOR` VARCHAR(45) NULL,
  `GENDER` VARCHAR(45) NULL,
  `DATE_OF_BIRTH` DATETIME NULL,
  `EMAIL` VARCHAR(255) NULL,
  `PHONE` INT NULL,
  `ADDRESS` VARCHAR(512) NULL,
  `COUNTRY` VARCHAR(255) NULL,
  `ABOUT` MEDIUMTEXT NULL,
  `FL_ACTIVE` INT NOT NULL,
  `REGISTERED` DATETIME NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_PERSON_BASEP`
    FOREIGN KEY (`ID`)
    REFERENCES `test_rec`.`base_person` (`ID`)
    ON DELETE NO ACTION
);

CREATE TABLE `test_rec`.`child` (
  `ID` VARCHAR(64) NOT NULL,
  `DUMMY` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `FK_CHILD_BASEP`
    FOREIGN KEY (`ID`)
    REFERENCES `test_rec`.`base_person` (`ID`)
    ON DELETE NO ACTION
);

CREATE TABLE `test_rec`.`person_children` (
  `ID_PERSON` VARCHAR(64) NOT NULL,
  `ID_CHILD` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`ID_PERSON`, `ID_CHILD`),
  CONSTRAINT `FK_PC_PERSON`
    FOREIGN KEY (`ID_PERSON`)
    REFERENCES `test_rec`.`person` (`ID`)
    ON DELETE CASCADE,
  CONSTRAINT `FK_PC_CHILD`
    FOREIGN KEY (`ID_CHILD`)
    REFERENCES `test_rec`.`child` (`ID`)
    ON DELETE CASCADE
);
