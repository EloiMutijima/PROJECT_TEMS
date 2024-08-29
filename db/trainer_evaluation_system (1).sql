-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2024 at 06:19 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trainer_evaluation_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `evaluatedmodule`
--

CREATE TABLE `evaluatedmodule` (
  `id` int(11) NOT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `M_code` varchar(10) DEFAULT NULL,
  `academic_year` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `trainer` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `option_field` varchar(255) DEFAULT NULL,
  `classroom_setting` int(11) DEFAULT NULL,
  `learning_consumables` int(11) DEFAULT NULL,
  `ppe_availability` int(11) DEFAULT NULL,
  `course_objectives` int(11) DEFAULT NULL,
  `learning_units` int(11) DEFAULT NULL,
  `learning_activities` int(11) DEFAULT NULL,
  `punctuality` int(11) DEFAULT NULL,
  `feedback_encouragement` int(11) DEFAULT NULL,
  `content_mastery` int(11) DEFAULT NULL,
  `instructional_technology` int(11) DEFAULT NULL,
  `active_participation` int(11) DEFAULT NULL,
  `communication_skills` int(11) DEFAULT NULL,
  `assessments_provided` int(11) DEFAULT NULL,
  `feedback_provision` int(11) DEFAULT NULL,
  `strengths` varchar(255) DEFAULT NULL,
  `improvement_area` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `evaluatedmodule`
--

INSERT INTO `evaluatedmodule` (`id`, `semester`, `M_code`, `academic_year`, `module_name`, `trainer`, `department`, `option_field`, `classroom_setting`, `learning_consumables`, `ppe_availability`, `course_objectives`, `learning_units`, `learning_activities`, `punctuality`, `feedback_encouragement`, `content_mastery`, `instructional_technology`, `active_participation`, `communication_skills`, `assessments_provided`, `feedback_provision`, `strengths`, `improvement_area`) VALUES
(2, '1', 'ITLLN601', '2023-2024', 'LARGE NETWORK ADMINISTRATION', 'HARERIMANA Sophonie', 'ICT', 'IT', 2, 4, 5, 5, 4, 4, 4, 5, 5, 4, 4, 5, 5, 4, 'Hardworking Trainer', 'Practical work'),
(3, '2', 'ITLOF601', '2023-2024', 'Oracle Fundamentals', 'Valens GISIMBA', '	ICT', 'IT', 5, 4, 4, 5, 4, 4, 5, 5, 5, 4, 4, 3, 2, 5, 'Very well performances', 'Keep Working hard'),
(6, '1', 'ITLOJ601', '2023-2024', 'OOP USING JAVA', 'NISHIMWE Gabriel', 'ICT', 'IT', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 'Well', 'WEll'),
(7, '2', 'AUTMA601', '2023-2024', 'MATHEMATICS', 'Viatuer NISHIMWE', 'AUTO', 'Automobile', 4, 3, 1, 2, 3, 5, 4, 3, 2, 1, 3, 4, 3, 3, 'Update skills', 'Improve technology'),
(8, '2', 'ITLOF601', '2023-2024', 'Oracle Fundamentals', 'Valens GISIMBA', 'ict', 'it', 3, 4, 4, 5, 5, 4, NULL, 2, 2, 4, 3, 5, 3, 3, 'You tried your best to explain us', 'improve tecnology');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `feedback` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `module_id`, `feedback`) VALUES
(2, 2, 'I will improve sir'),
(5, 7, 'I will try my best next semester'),
(6, 2, 'Yes i will improve my practices');

-- --------------------------------------------------------

--
-- Table structure for table `newmodule`
--

CREATE TABLE `newmodule` (
  `Semester` int(5) NOT NULL,
  `Academic` varchar(15) NOT NULL,
  `M_code` varchar(10) NOT NULL,
  `M_name` varchar(30) NOT NULL,
  `Department` varchar(30) NOT NULL,
  `Trainer` varchar(50) NOT NULL,
  `Program` varchar(50) NOT NULL,
  `Level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `newmodule`
--

INSERT INTO `newmodule` (`Semester`, `Academic`, `M_code`, `M_name`, `Department`, `Trainer`, `Program`, `Level`) VALUES
(2, '2023-2024', 'AUTMA601', 'MATHEMATICS', 'AUTO', 'Viatuer NISHIMWE', 'Automobile', 'L6 Y1'),
(1, '2023-2024', 'ITLLN601', 'LARGE NETWORK ADMINISTRATION', 'ICT', 'HARERIMANA Sophonie', 'IT', 'L6 Year 2'),
(2, '2023-2024', 'ITLOF601', 'Oracle Fundamentals', 'ICT', 'Valens GISIMBA', 'IT', 'L6 Y2'),
(1, '2023-2024', 'ITLOJ601', 'OOP USING JAVA', 'ICT', 'NISHIMWE Gabriel', 'IT', 'L6 Y2');

-- --------------------------------------------------------

--
-- Table structure for table `student_profile`
--

CREATE TABLE `student_profile` (
  `Regno` varchar(10) NOT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `Department` varchar(100) DEFAULT NULL,
  `Options` varchar(100) DEFAULT NULL,
  `Level` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Dates` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_profile`
--

INSERT INTO `student_profile` (`Regno`, `FirstName`, `LastName`, `Department`, `Options`, `Level`, `Email`, `Dates`) VALUES
('22RP09837', 'Mpayimana', 'Phillipe', 'AUTO', NULL, 'L7 Year 3', 'phillips@gmail.com', '2024-08-29'),
('22RP10123', 'umutoni', 'alice', 'ICT', NULL, 'L6 Year 2', 'alice@gmail.com', '2024-08-14'),
('22rp11666', 'kkkk', 'kkk', 'ICT', NULL, 'L6 Year 2', 'igiranezafelicite1@gmail.com', '2024-07-15');

-- --------------------------------------------------------

--
-- Table structure for table `trainer_profile`
--

CREATE TABLE `trainer_profile` (
  `Id` int(16) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Department` varchar(20) NOT NULL,
  `Options` varchar(20) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trainer_profile`
--

INSERT INTO `trainer_profile` (`Id`, `FirstName`, `LastName`, `Department`, `Options`, `Email`, `Phone`) VALUES
(11111111, 'mutijamaaa', 'drick', 'AUTO', 'Mechanics Automobile', 'mutijimaeloi@gmail.com', 786587656),
(87628933, 'Isabelle', 'Bona', 'Hospitality', 'Culnary Arts', 'isabelle@gmail.com', 780000012),
(87656787, 'Blaise', 'Matuidi', 'Hospitality', 'Room Division', 'blaise@gmail.com', 788834567);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Username` varchar(50) NOT NULL,
  `UserType` varchar(20) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Username`, `UserType`, `Password`) VALUES
('Mutijima', 'Student', '1234'),
('Felicite', 'Trainer', 'train'),
('Ruth', 'Evaluator', '9090'),
('Isabelle', 'Admin', 'admin123'),
('Eric', 'Evaluator', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evaluatedmodule`
--
ALTER TABLE `evaluatedmodule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `M_code` (`M_code`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `module_id` (`module_id`);

--
-- Indexes for table `newmodule`
--
ALTER TABLE `newmodule`
  ADD PRIMARY KEY (`M_code`);

--
-- Indexes for table `student_profile`
--
ALTER TABLE `student_profile`
  ADD PRIMARY KEY (`Regno`);

--
-- Indexes for table `trainer_profile`
--
ALTER TABLE `trainer_profile`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `evaluatedmodule`
--
ALTER TABLE `evaluatedmodule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evaluatedmodule`
--
ALTER TABLE `evaluatedmodule`
  ADD CONSTRAINT `evaluatedmodule_ibfk_1` FOREIGN KEY (`M_code`) REFERENCES `newmodule` (`M_code`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`module_id`) REFERENCES `evaluatedmodule` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
