-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2024 at 08:26 PM
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
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `Module_code` varchar(20) NOT NULL,
  `Module_name` varchar(255) NOT NULL,
  `Department` varchar(255) NOT NULL,
  `Program` varchar(255) NOT NULL,
  `Level` varchar(255) NOT NULL,
  `Trainer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`Module_code`, `Module_name`, `Department`, `Program`, `Level`, `Trainer_id`) VALUES
('AUTDR01', 'Drawing', 'AUTO', 'AUTO', 'L6 Y1', 8),
('ITLLN601', 'LARGE NETWORK', 'Information Communication Technology ', 'Information Technology ', 'L6 Y2', 3),
('ITLOJ601', 'OOP USING JAVA', 'Information Communication Technology ', 'Information Technology ', 'L6 Y2', 2);

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
-- Table structure for table `evaluations`
--

CREATE TABLE `evaluations` (
  `id` int(11) NOT NULL,
  `Module_code` varchar(255) DEFAULT NULL,
  `Module_name` varchar(255) DEFAULT NULL,
  `Department` varchar(255) DEFAULT NULL,
  `Trainer_id` varchar(255) DEFAULT NULL,
  `classroom_setting` int(255) DEFAULT NULL,
  `learning_consumables` int(255) DEFAULT NULL,
  `ppe_availability` int(255) DEFAULT NULL,
  `course_objectives` int(255) DEFAULT NULL,
  `learning_units` int(255) DEFAULT NULL,
  `learning_activities` int(255) DEFAULT NULL,
  `punctuality` int(255) DEFAULT NULL,
  `feedback_encouragement` int(255) DEFAULT NULL,
  `content_mastery` int(255) DEFAULT NULL,
  `instructional_technology` int(255) DEFAULT NULL,
  `active_participation` int(255) DEFAULT NULL,
  `communication_skills` int(255) DEFAULT NULL,
  `assessments_provided` int(255) DEFAULT NULL,
  `feedback_provision` int(255) DEFAULT NULL,
  `strengths` text DEFAULT NULL,
  `improvement_areas` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `evaluations`
--

INSERT INTO `evaluations` (`id`, `Module_code`, `Module_name`, `Department`, `Trainer_id`, `classroom_setting`, `learning_consumables`, `ppe_availability`, `course_objectives`, `learning_units`, `learning_activities`, `punctuality`, `feedback_encouragement`, `content_mastery`, `instructional_technology`, `active_participation`, `communication_skills`, `assessments_provided`, `feedback_provision`, `strengths`, `improvement_areas`) VALUES
(14, 'ITLOJ601', 'OOP USING JAVA', 'Information Communication Technology ', '2', 5, 5, 5, 5, 5, 5, NULL, NULL, 5, 5, 5, 5, 5, 5, 'lkjhg', 'lkj'),
(15, 'ITLLN601', 'LARGE NETWORK', 'Information Communication Technology ', '3', 2, 3, 4, 4, 4, 5, NULL, NULL, 5, 5, 4, 5, 5, 4, 'lkjhgf', 'lkjh'),
(16, 'AUTDR01', 'Drawing', 'AUTO', '8', 5, 5, 5, 5, 5, 5, NULL, NULL, 5, 5, 5, 5, 5, 5, 'Hardworking trainer', 'Keep it up'),
(17, 'ITLOJ601', 'OOP USING JAVA', 'Information Communication Technology ', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', ''),
(18, 'ITLLN601', 'LARGE NETWORK', 'Information Communication Technology ', '3', 5, 5, 4, 4, 5, 5, NULL, NULL, 5, 5, 5, 5, 5, 5, 'Hard worker', 'Keep goin');

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
(5, 7, 'I will try my best next semester');

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
-- Table structure for table `replies`
--

CREATE TABLE `replies` (
  `Trainer_id` varchar(255) DEFAULT NULL,
  `Feedback` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `replies`
--

INSERT INTO `replies` (`Trainer_id`, `Feedback`) VALUES
('1', 'improve please '),
('2', 'done well'),
('3', 'add practices'),
('2', 'let do it');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `Rol_id` int(10) NOT NULL,
  `Rol_name` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`Rol_id`, `Rol_name`) VALUES
(1, 'Admin'),
(2, 'Quality'),
(3, 'Trainer'),
(4, 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `RegNo` varchar(9) NOT NULL,
  `Stud_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `Tel` int(10) DEFAULT NULL,
  `Department` varchar(255) NOT NULL,
  `Options` varchar(255) NOT NULL,
  `Level` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`RegNo`, `Stud_name`, `email`, `Tel`, `Department`, `Options`, `Level`) VALUES
('22RP10000', 'Ishimwe Frank', 'ishimwefrank@gmail.com', 783456789, 'Information Communication Technology ', 'Information Technology ', 'L6 Y1'),
('22RP10101', 'Mutoni Clemence', 'mutoniclemence@gmail.com', 792736541, 'Hospitality', 'Culninary Arts', 'L6 Y1'),
('22RP10123', 'NIYONGIRA Ruths', 'niyoruth@gmail.com', 0, 'Information Communication Technology', 'Information Technology', '785924163'),
('22RP11911', 'IGIRANEZA Felicite', 'felicite@gmail.com', 785948910, 'ICT', 'IT', 'L6 Y2'),
('22RP12049', 'Tuyizere Ignace', 'tuyizereignace722@gmail.com', 785948918, 'Information Communication Technology', 'Information Technology', 'L6 Y2'),
('22RP13429', 'Mutijima Eloi', 'mutijimaeloi@gmail.com', 792504082, 'Information Communication Technology', 'Information Technology', 'L6 Y2'),
('22RP19802', 'Iriho Dociles', 'irihodocil@gmail.com', NULL, 'Information Communication Technology ', 'Information Technology ', '0784055255'),
('22RP22222', 'Umwizerwa Florence', 'florence@gmail.com', 730076253, 'Hospitality', 'Room Division Management', 'L6 Y3');

-- --------------------------------------------------------

--
-- Table structure for table `trainers`
--

CREATE TABLE `trainers` (
  `Trainer_id` int(11) NOT NULL,
  `Trainer` varchar(255) NOT NULL,
  `Tel` int(11) NOT NULL,
  `Departments` varchar(255) NOT NULL,
  `Options` varchar(255) NOT NULL,
  `Modules` varchar(255) NOT NULL,
  `email` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trainers`
--

INSERT INTO `trainers` (`Trainer_id`, `Trainer`, `Tel`, `Departments`, `Options`, `Modules`, `email`) VALUES
(2, 'NISHIMWE GABRIEL', 789956439, 'Information Communication Technology ', 'Information Technology ', 'OOP USING JAVA', 'nishigabby@gmail.com'),
(3, 'HARERIMANA Sophonie', 789354783, 'Information Communication Technology ', 'Information Technology ', 'LARGE NETWORK', 'harerimanasoph@gmail.com'),
(8, 'mukwiye boris', 785948910, 'auto', 'auto', 'drawing', 'mukwiyeboris@gmail.com'),
(19, 'NIYIGABA Ephrem', 782764965, 'Information Communication Technology', 'Information Technology', 'Research Methodology', 'niyigabaephrem@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `Rol_id` int(11) NOT NULL,
  `RegNo` varchar(10) DEFAULT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Trainer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Rol_id`, `RegNo`, `Username`, `Password`, `Trainer_id`) VALUES
(4, '22RP13429', 'Eloi', 'eloi', NULL),
(3, NULL, 'nishigabby@gmail.com', 'gab123', 2),
(2, NULL, 'qualityassuarance@gmail.com', 'qualityiprckarongi', NULL),
(1, NULL, 'mutijimaeloi@gmail.com', 'admin123', NULL),
(2, NULL, 'sophonie@gmail.com', '123', NULL),
(3, NULL, 'harerimanasophonie@gmail.com', '123', 3),
(4, '22RP10123', 'ruth', 'ruth@123', NULL),
(4, '22RP11911', 'Felicite', 'felicite123', NULL),
(4, '22RP19802', 'Docile', '123', NULL),
(4, '22RP10000', 'frank', '123456', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`Module_code`),
  ADD KEY `Trainer_id` (`Trainer_id`);

--
-- Indexes for table `evaluatedmodule`
--
ALTER TABLE `evaluatedmodule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `M_code` (`M_code`);

--
-- Indexes for table `evaluations`
--
ALTER TABLE `evaluations`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`Rol_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`RegNo`),
  ADD UNIQUE KEY `Tel` (`Tel`);

--
-- Indexes for table `trainers`
--
ALTER TABLE `trainers`
  ADD PRIMARY KEY (`Trainer_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD KEY `Rol_id` (`Rol_id`),
  ADD KEY `RegNo` (`RegNo`),
  ADD KEY `Trainer_id` (`Trainer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `evaluatedmodule`
--
ALTER TABLE `evaluatedmodule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `evaluations`
--
ALTER TABLE `evaluations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`Trainer_id`) REFERENCES `trainers` (`Trainer_id`);

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

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`Rol_id`) REFERENCES `role` (`Rol_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`RegNo`) REFERENCES `students` (`RegNo`),
  ADD CONSTRAINT `users_ibfk_3` FOREIGN KEY (`Trainer_id`) REFERENCES `trainers` (`Trainer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
