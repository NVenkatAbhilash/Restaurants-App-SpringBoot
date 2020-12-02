DROP DATABASE  IF EXISTS `springboot_security_bcrypt`;

CREATE DATABASE  IF NOT EXISTS `springboot_security_bcrypt`;
USE `springboot_security_bcrypt`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2b$10$lPfrffq5leOzKUT0WW/FR.ELwx7C1KpG2tAlsQYupErVEU44AB6L6',1),
('mary','{bcrypt}$2b$10$lPfrffq5leOzKUT0WW/FR.ELwx7C1KpG2tAlsQYupErVEU44AB6L6',1),
('susan','{bcrypt}$2b$10$lPfrffq5leOzKUT0WW/FR.ELwx7C1KpG2tAlsQYupErVEU44AB6L6',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) on delete cascade,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_CUSTOMER'),
('mary','ROLE_VIP'),
('susan','ROLE_INSPECTOR');


