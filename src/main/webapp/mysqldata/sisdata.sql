-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 19 Tem 2019, 16:30:08
-- Sunucu sürümü: 10.1.40-MariaDB
-- PHP Sürümü: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `sisdata`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `classes`
--

CREATE TABLE `classes` (
  `CLASSES_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `COURSE_ID` int(11) NOT NULL,
  `VIZE_NOT` int(11) DEFAULT NULL,
  `FINAL_NOT` int(11) DEFAULT NULL,
  `GRADE` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `classes`
--

INSERT INTO `classes` (`CLASSES_ID`, `USER_ID`, `COURSE_ID`, `VIZE_NOT`, `FINAL_NOT`, `GRADE`) VALUES
(1, 9, 1, NULL, NULL, NULL),
(2, 9, 3, NULL, NULL, NULL),
(3, 9, 2, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `courses`
--

CREATE TABLE `courses` (
  `COURSE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TERM_ID` int(11) NOT NULL,
  `COURSE_NAME` varchar(25) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `CURRENT_SIZE` int(11) DEFAULT NULL,
  `MAX_SIZE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `courses`
--

INSERT INTO `courses` (`COURSE_ID`, `USER_ID`, `TERM_ID`, `COURSE_NAME`, `STATUS`, `CURRENT_SIZE`, `MAX_SIZE`) VALUES
(1, 7, 1, 'BIL212', 1, 0, 50),
(2, 7, 1, 'BIL331', 1, NULL, 50),
(3, 7, 1, 'BIL214', 1, NULL, 50),
(4, 22, 1, 'BIL481', 1, 0, 50),
(5, 1, 11, 'BIL212', 0, NULL, 51),
(6, 1, 11, 'testcourseaktif', 0, NULL, 50);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `logs`
--

CREATE TABLE `logs` (
  `LOG_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `EVENT_CODE` int(11) NOT NULL,
  `DETAIL` text,
  `DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `logs`
--

INSERT INTO `logs` (`LOG_ID`, `USER_ID`, `EVENT_CODE`, `DETAIL`, `DATE`) VALUES
(1, 1, 1, 'succesfully logined', '2019-06-19 13:56:55'),
(2, 1, 1, 'succesfully logined', '2019-06-19 14:12:41'),
(3, 1, 1, 'succesfully logined', '2019-06-19 15:01:11'),
(4, 1, 1, 'succesfully logined', '2019-06-19 15:01:31'),
(5, 1, 1, 'succesfully logined', '2019-06-19 15:03:58'),
(6, 1, 1, 'succesfully logined', '2019-06-19 15:15:10'),
(7, 1, 1, 'succesfully logined', '2019-06-19 15:18:24'),
(8, 1, 4, '1', '2019-06-19 15:24:05'),
(9, 1, 1, 'succesfully logined', '2019-06-19 15:27:00'),
(10, 1, 4, 'password changed', '2019-06-19 15:27:22'),
(11, 1, 1, 'succesfully logined', '2019-06-19 15:27:53'),
(12, 1, 1, 'succesfully logined', '2019-06-19 15:30:38'),
(13, 1, 1, 'succesfully logined', '2019-06-19 15:38:26'),
(14, 1, 1, 'succesfully logined', '2019-06-19 15:41:03'),
(15, 1, 1, 'succesfully logined', '2019-06-19 15:43:31'),
(16, 1, 4, 'password changed', '2019-06-19 15:44:05'),
(17, 1, 4, 'password changed', '2019-06-19 16:12:15'),
(18, 1, 1, 'succesfully logined', '2019-06-19 16:12:22'),
(19, 1, 1, 'succesfully logined', '2019-06-19 16:52:58'),
(20, 1, 1, 'succesfully logined', '2019-06-19 16:52:58'),
(21, 1, 1, 'succesfully logined', '2019-06-19 16:58:47'),
(22, 1, 1, 'succesfully logined', '2019-06-20 09:09:37'),
(23, 1, 1, 'succesfully logined', '2019-06-20 14:14:18'),
(24, 1, 1, 'succesfully logined', '2019-06-20 15:24:27'),
(25, 1, 1, 'succesfully logined', '2019-06-20 16:35:26'),
(26, 1, 1, 'succesfully logined', '2019-06-20 17:27:29'),
(27, 1, 1, 'succesfully logined', '2019-06-20 18:12:26'),
(28, 1, 1, 'succesfully logined', '2019-06-21 09:52:17'),
(29, 1, 1, 'succesfully logined', '2019-06-21 10:36:11'),
(30, 1, 1, 'succesfully logined', '2019-06-21 10:36:11'),
(31, 1, 1, 'succesfully logined', '2019-06-21 10:36:36'),
(32, 1, 1, 'succesfully logined', '2019-06-21 10:37:03'),
(33, 1, 1, 'succesfully logined', '2019-06-21 10:37:42'),
(34, 1, 4, 'password changed', '2019-06-21 10:45:43'),
(35, 1, 1, 'succesfully logined', '2019-06-21 10:48:13'),
(36, 1, 1, 'succesfully logined', '2019-06-21 10:52:25'),
(37, 1, 1, 'succesfully logined', '2019-06-21 10:54:01'),
(38, 1, 1, 'succesfully logined', '2019-06-21 10:58:29'),
(39, 1, 1, 'succesfully logined', '2019-06-21 11:29:09'),
(40, 1, 1, 'succesfully logined', '2019-06-21 11:53:17'),
(41, 1, 1, 'succesfully logined', '2019-06-21 12:08:45'),
(42, 1, 1, 'succesfully logined', '2019-06-21 12:10:19'),
(43, 1, 1, 'succesfully logined', '2019-06-21 12:20:19'),
(44, 1, 1, 'succesfully logined', '2019-06-21 13:41:52'),
(45, 1, 1, 'succesfully logined', '2019-06-21 13:46:02'),
(46, 1, 1, 'succesfully logined', '2019-06-21 13:46:37'),
(47, 1, 1, 'succesfully logined', '2019-06-21 13:50:06'),
(48, 1, 1, 'succesfully logined', '2019-06-21 13:53:36'),
(49, 1, 1, 'succesfully logined', '2019-06-21 13:59:05'),
(50, 1, 1, 'succesfully logined', '2019-06-21 14:05:51'),
(51, 1, 1, 'succesfully logined', '2019-06-21 14:19:34'),
(52, 1, 1, 'succesfully logined', '2019-06-21 14:22:40'),
(53, 1, 1, 'succesfully logined', '2019-06-21 14:23:09'),
(54, 1, 1, 'succesfully logined', '2019-06-21 14:23:43'),
(55, 1, 1, 'succesfully logined', '2019-06-21 14:30:07'),
(56, 1, 1, 'succesfully logined', '2019-06-21 14:30:21'),
(57, 1, 1, 'succesfully logined', '2019-06-21 14:36:28'),
(58, 1, 1, 'succesfully logined', '2019-06-21 14:40:28'),
(59, 1, 1, 'succesfully logined', '2019-06-21 14:54:34'),
(60, 1, 1, 'succesfully logined', '2019-06-21 14:54:34'),
(61, 1, 1, 'succesfully logined', '2019-06-21 14:55:15'),
(62, 1, 1, 'succesfully logined', '2019-06-21 16:31:10'),
(63, 1, 1, 'succesfully logined', '2019-06-21 16:32:17'),
(64, 1, 1, 'succesfully logined', '2019-06-21 17:08:09'),
(65, 1, 1, 'succesfully logined', '2019-06-21 18:19:25'),
(66, 1, 1, 'succesfully logined', '2019-06-21 18:19:25'),
(67, 1, 1, 'succesfully logined', '2019-06-21 18:26:19'),
(68, 1, 1, 'succesfully logined', '2019-06-21 18:28:32'),
(69, 1, 1, 'succesfully logined', '2019-06-21 18:28:42'),
(70, 1, 1, 'succesfully logined', '2019-06-24 09:27:54'),
(71, 1, 1, 'succesfully logined', '2019-06-24 09:49:25'),
(72, 1, 1, 'succesfully logined', '2019-06-24 09:50:51'),
(73, 1, 1, 'succesfully logined', '2019-06-24 09:52:15'),
(74, 1, 1, 'succesfully logined', '2019-06-24 09:53:07'),
(75, 1, 1, 'succesfully logined', '2019-06-24 11:41:10'),
(76, 1, 1, 'succesfully logined', '2019-06-24 11:44:36'),
(77, 1, 1, 'succesfully logined', '2019-06-24 11:49:21'),
(78, 1, 1, 'succesfully logined', '2019-06-24 11:53:49'),
(79, 1, 1, 'succesfully logined', '2019-06-24 11:56:04'),
(80, 1, 1, 'succesfully logined', '2019-06-24 11:56:40'),
(81, 1, 1, 'succesfully logined', '2019-06-24 11:57:32'),
(82, 1, 1, 'succesfully logined', '2019-06-24 12:00:36'),
(83, 1, 1, 'succesfully logined', '2019-06-24 12:00:44'),
(84, 1, 1, 'succesfully logined', '2019-06-24 12:02:50'),
(85, 1, 1, 'succesfully logined', '2019-06-24 12:05:08'),
(86, 1, 1, 'succesfully logined', '2019-06-24 12:06:45'),
(87, 1, 1, 'succesfully logined', '2019-06-24 13:35:56'),
(88, 1, 1, 'succesfully logined', '2019-06-24 13:38:21'),
(89, 1, 1, 'succesfully logined', '2019-06-24 13:40:44'),
(90, 1, 1, 'succesfully logined', '2019-06-24 17:40:36'),
(91, 1, 4, 'password changed', '2019-06-24 17:50:31'),
(92, 2, 4, 'password changed', '2019-06-24 17:53:41'),
(93, 1, 1, 'succesfully logined', '2019-06-24 18:12:54'),
(94, 1, 1, 'succesfully logined', '2019-06-24 18:13:04'),
(95, 1, 4, 'password changed', '2019-06-24 18:14:31'),
(96, 1, 4, 'password changed', '2019-06-24 18:14:53'),
(97, 1, 1, 'succesfully logined', '2019-06-25 09:11:58'),
(98, 1, 1, 'succesfully logined', '2019-06-25 09:12:45'),
(99, 1, 1, 'succesfully logined', '2019-06-25 09:27:47'),
(100, 1, 1, 'succesfully logined', '2019-06-25 10:21:57'),
(101, 1, 1, 'succesfully logined', '2019-06-25 11:29:13'),
(102, 1, 1, 'succesfully logined', '2019-06-25 15:16:49'),
(103, 1, 1, 'succesfully logined', '2019-06-25 15:17:08'),
(104, 1, 1, 'succesfully logined', '2019-06-25 15:18:05'),
(105, 1, 1, 'succesfully logined', '2019-06-25 15:24:36'),
(106, 1, 1, 'succesfully logined', '2019-06-25 15:40:13'),
(107, 1, 1, 'succesfully logined', '2019-06-25 15:40:36'),
(108, 1, 1, 'succesfully logined', '2019-06-26 10:54:51'),
(109, 1, 1, 'succesfully logined', '2019-06-26 11:37:48'),
(110, 1, 1, 'succesfully logined', '2019-06-26 11:38:04'),
(111, 1, 1, 'succesfully logined', '2019-06-26 11:38:54'),
(112, 1, 1, 'succesfully logined', '2019-06-26 11:38:54'),
(113, 1, 1, 'succesfully logined', '2019-06-26 12:21:37'),
(114, 1, 1, 'succesfully logined', '2019-06-26 13:50:56'),
(115, 1, 1, 'succesfully logined', '2019-06-26 16:11:42'),
(116, 1, 1, 'succesfully logined', '2019-06-26 16:11:42'),
(117, 1, 1, 'succesfully logined', '2019-06-26 16:11:42'),
(118, 1, 1, 'succesfully logined', '2019-06-26 16:11:42'),
(119, 1, 1, 'succesfully logined', '2019-06-26 16:11:42'),
(120, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(121, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(122, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(123, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(124, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(125, 1, 1, 'succesfully logined', '2019-06-26 16:12:40'),
(126, 1, 1, 'succesfully logined', '2019-06-26 16:12:41'),
(127, 1, 1, 'succesfully logined', '2019-06-26 16:16:11'),
(128, 1, 1, 'succesfully logined', '2019-06-27 10:06:52'),
(129, 1, 1, 'succesfully logined', '2019-06-27 11:53:46'),
(130, 1, 1, 'succesfully logined', '2019-06-27 11:53:46'),
(131, 1, 1, 'succesfully logined', '2019-06-27 11:53:46'),
(132, 1, 1, 'succesfully logined', '2019-06-27 12:03:40'),
(133, 1, 1, 'succesfully logined', '2019-06-27 14:54:28'),
(134, 1, 1, 'succesfully logined', '2019-06-27 15:11:24'),
(135, 1, 1, 'succesfully logined', '2019-06-27 15:59:54'),
(136, 1, 1, 'succesfully logined', '2019-06-27 16:01:42'),
(137, 1, 1, 'succesfully logined', '2019-06-28 09:21:22'),
(138, 1, 1, 'succesfully logined', '2019-06-28 11:36:43'),
(139, 1, 1, 'succesfully logined', '2019-06-28 11:37:09'),
(140, 1, 1, 'succesfully logined', '2019-06-28 12:08:12'),
(141, 1, 1, 'succesfully logined', '2019-06-28 16:48:30'),
(142, 1, 1, 'succesfully logined', '2019-07-01 10:30:18'),
(143, 1, 1, 'succesfully logined', '2019-07-01 17:01:13'),
(144, 1, 1, 'succesfully logined', '2019-07-01 17:01:13'),
(145, 1, 1, 'succesfully logined', '2019-07-01 18:16:19'),
(146, 1, 1, 'succesfully logined', '2019-07-03 10:41:07'),
(147, 1, 1, 'succesfully logined', '2019-07-03 10:43:58'),
(148, 1, 1, 'succesfully logined', '2019-07-03 10:43:58'),
(149, 1, 1, 'succesfully logined', '2019-07-03 11:12:47'),
(150, 1, 1, 'succesfully logined', '2019-07-03 11:12:48'),
(151, 1, 1, 'succesfully logined', '2019-07-03 11:56:27'),
(152, 1, 1, 'succesfully logined', '2019-07-03 11:56:27'),
(153, 1, 1, 'succesfully logined', '2019-07-03 11:56:27'),
(154, 1, 1, 'succesfully logined', '2019-07-03 11:58:53'),
(155, 1, 1, 'succesfully logined', '2019-07-03 13:35:32'),
(156, 1, 1, 'succesfully logined', '2019-07-03 13:37:18'),
(157, 1, 1, 'succesfully logined', '2019-07-04 09:11:47'),
(158, 1, 1, 'succesfully logined', '2019-07-04 09:21:34'),
(159, 1, 1, 'succesfully logined', '2019-07-04 09:39:51'),
(160, 1, 1, 'succesfully logined', '2019-07-04 10:27:54'),
(161, 1, 1, 'succesfully logined', '2019-07-04 12:08:21'),
(162, 1, 1, 'succesfully logined', '2019-07-04 12:17:14'),
(163, 1, 1, 'succesfully logined', '2019-07-04 13:49:14'),
(164, 1, 1, 'succesfully logined', '2019-07-04 13:49:14'),
(165, 1, 1, 'succesfully logined', '2019-07-04 13:49:15'),
(166, 1, 1, 'succesfully logined', '2019-07-04 13:49:14'),
(167, 1, 1, 'succesfully logined', '2019-07-04 14:22:56'),
(168, 11, 4, 'password changed', '2019-07-04 14:25:08'),
(169, 11, 4, 'password changed', '2019-07-04 14:29:44'),
(170, 11, 4, 'password changed', '2019-07-04 14:31:58'),
(171, 9, 4, 'password changed', '2019-07-04 17:41:54'),
(172, 1, 1, 'succesfully logined', '2019-07-04 17:42:08'),
(173, 1, 1, 'succesfully logined', '2019-07-04 17:48:33'),
(174, 1, 1, 'succesfully logined', '2019-07-05 14:44:24'),
(175, 9, 4, 'password changed', '2019-07-05 14:57:49'),
(176, 1, 1, 'succesfully logined', '2019-07-05 15:38:38'),
(177, 1, 1, 'succesfully logined', '2019-07-05 15:40:36'),
(178, 1, 1, 'succesfully logined', '2019-07-05 15:45:48'),
(179, 1, 1, 'succesfully logined', '2019-07-05 15:46:38'),
(180, 1, 1, 'succesfully logined', '2019-07-05 15:48:18'),
(181, 1, 1, 'succesfully logined', '2019-07-05 15:48:45'),
(182, 1, 1, 'succesfully logined', '2019-07-05 16:01:50'),
(183, 1, 1, 'succesfully logined', '2019-07-05 16:35:10'),
(184, 1, 1, 'succesfully logined', '2019-07-05 16:43:30'),
(185, 1, 1, 'succesfully logined', '2019-07-05 16:51:04'),
(186, 1, 1, 'succesfully logined', '2019-07-05 16:57:50'),
(187, 1, 1, 'succesfully logined', '2019-07-05 17:04:46'),
(188, 1, 1, 'succesfully logined', '2019-07-05 17:23:18'),
(189, 1, 1, 'succesfully logined', '2019-07-05 17:28:03'),
(190, 1, 1, 'succesfully logined', '2019-07-05 17:31:37'),
(191, 1, 1, 'succesfully logined', '2019-07-08 10:08:37'),
(192, 1, 1, 'succesfully logined', '2019-07-08 10:22:39'),
(193, 1, 1, 'succesfully logined', '2019-07-08 10:30:34'),
(194, 1, 1, 'succesfully logined', '2019-07-08 13:38:46'),
(195, 1, 1, 'succesfully logined', '2019-07-08 14:53:36'),
(196, 1, 1, 'succesfully logined', '2019-07-08 14:59:42'),
(197, 1, 1, 'succesfully logined', '2019-07-08 15:17:53'),
(198, 1, 1, 'succesfully logined', '2019-07-08 15:26:56'),
(199, 1, 1, 'succesfully logined', '2019-07-08 15:28:05'),
(200, 1, 1, 'succesfully logined', '2019-07-08 15:28:06'),
(201, 1, 1, 'succesfully logined', '2019-07-08 15:31:45'),
(203, 1, 1, 'succesfully logined', '2019-07-08 15:33:17'),
(204, 1, 1, 'succesfully logined', '2019-07-08 15:34:03'),
(205, 1, 1, 'succesfully logined', '2019-07-08 15:38:14'),
(206, 1, 1, 'succesfully logined', '2019-07-08 15:43:50'),
(207, 1, 1, 'succesfully logined', '2019-07-08 18:15:24'),
(208, 1, 1, 'succesfully logined', '2019-07-09 09:12:49'),
(209, 1, 1, 'succesfully logined', '2019-07-09 09:18:16'),
(210, 1, 1, 'succesfully logined', '2019-07-09 09:19:48'),
(211, 1, 1, 'succesfully logined', '2019-07-09 09:20:37'),
(212, 1, 1, 'succesfully logined', '2019-07-09 09:21:58'),
(213, 1, 1, 'succesfully logined', '2019-07-09 09:24:12'),
(214, 1, 1, 'succesfully logined', '2019-07-09 09:24:59'),
(215, 1, 1, 'succesfully logined', '2019-07-09 09:49:59'),
(216, 1, 1, 'succesfully logined', '2019-07-09 09:52:22'),
(217, 1, 1, 'succesfully logined', '2019-07-09 09:53:39'),
(218, 1, 1, 'succesfully logined', '2019-07-09 09:55:18'),
(219, 1, 1, 'succesfully logined', '2019-07-09 10:11:18'),
(220, 1, 1, 'succesfully logined', '2019-07-09 10:26:08'),
(221, 1, 1, 'succesfully logined', '2019-07-09 10:32:26'),
(222, 1, 1, 'succesfully logined', '2019-07-09 10:34:53'),
(223, 1, 1, 'succesfully logined', '2019-07-09 10:46:55'),
(224, 1, 1, 'succesfully logined', '2019-07-09 10:55:58'),
(225, 1, 1, 'succesfully logined', '2019-07-09 11:36:37'),
(226, 1, 1, 'succesfully logined', '2019-07-09 11:43:02'),
(227, 1, 1, 'succesfully logined', '2019-07-09 11:45:04'),
(228, 1, 1, 'succesfully logined', '2019-07-09 11:59:20'),
(229, 1, 1, 'succesfully logined', '2019-07-09 11:59:20'),
(230, 1, 1, 'succesfully logined', '2019-07-09 12:28:33'),
(231, 1, 1, 'succesfully logined', '2019-07-09 13:38:37'),
(232, 1, 1, 'succesfully logined', '2019-07-09 13:38:38'),
(233, 1, 1, 'succesfully logined', '2019-07-09 13:38:38'),
(234, 1, 1, 'succesfully logined', '2019-07-09 13:48:38'),
(235, 1, 1, 'succesfully logined', '2019-07-09 13:50:49'),
(236, 1, 1, 'succesfully logined', '2019-07-09 14:02:03'),
(237, 1, 1, 'succesfully logined', '2019-07-09 14:03:42'),
(238, 1, 1, 'succesfully logined', '2019-07-09 14:05:40'),
(239, 1, 1, 'succesfully logined', '2019-07-09 15:15:21'),
(240, 1, 1, 'succesfully logined', '2019-07-09 15:36:25'),
(241, 1, 1, 'succesfully logined', '2019-07-09 16:19:10'),
(242, 1, 1, 'succesfully logined', '2019-07-09 16:22:53'),
(243, 1, 1, 'succesfully logined', '2019-07-09 17:06:45'),
(244, 1, 1, 'succesfully logined', '2019-07-09 17:09:28'),
(245, 1, 1, 'succesfully logined', '2019-07-09 17:21:22'),
(246, 1, 1, 'succesfully logined', '2019-07-09 17:33:22'),
(247, 1, 1, 'succesfully logined', '2019-07-09 17:36:07'),
(248, 1, 1, 'succesfully logined', '2019-07-09 18:01:34'),
(249, 1, 1, 'succesfully logined', '2019-07-10 09:56:48'),
(250, 1, 1, 'succesfully logined', '2019-07-10 10:08:01'),
(251, 1, 1, 'succesfully logined', '2019-07-10 10:10:04'),
(252, 1, 1, 'succesfully logined', '2019-07-10 10:18:38'),
(253, 1, 1, 'succesfully logined', '2019-07-10 10:33:25'),
(254, 1, 1, 'succesfully logined', '2019-07-10 10:40:57'),
(255, 1, 1, 'succesfully logined', '2019-07-10 10:43:23'),
(256, 1, 1, 'succesfully logined', '2019-07-10 10:45:05'),
(257, 1, 1, 'succesfully logined', '2019-07-10 10:58:58'),
(258, 1, 1, 'succesfully logined', '2019-07-10 11:16:36'),
(259, 1, 1, 'succesfully logined', '2019-07-10 11:18:22'),
(260, 1, 1, 'succesfully logined', '2019-07-10 11:45:39'),
(261, 1, 1, 'succesfully logined', '2019-07-10 12:01:27'),
(262, 1, 1, 'succesfully logined', '2019-07-10 12:11:39'),
(263, 1, 1, 'succesfully logined', '2019-07-10 12:13:21'),
(264, 1, 1, 'succesfully logined', '2019-07-10 12:14:50'),
(265, 1, 1, 'succesfully logined', '2019-07-10 12:20:05'),
(266, 1, 1, 'succesfully logined', '2019-07-10 13:40:48'),
(267, 1, 1, 'succesfully logined', '2019-07-10 14:09:13'),
(268, 1, 1, 'succesfully logined', '2019-07-10 14:27:05'),
(269, 1, 1, 'succesfully logined', '2019-07-10 14:43:30'),
(270, 1, 1, 'succesfully logined', '2019-07-10 14:48:03'),
(271, 1, 1, 'succesfully logined', '2019-07-10 14:55:57'),
(272, 1, 1, 'succesfully logined', '2019-07-10 15:16:46'),
(273, 1, 1, 'succesfully logined', '2019-07-10 16:12:15'),
(274, 1, 1, 'succesfully logined', '2019-07-10 16:14:47'),
(275, 1, 1, 'succesfully logined', '2019-07-10 16:38:01'),
(276, 1, 1, 'succesfully logined', '2019-07-10 16:49:55'),
(277, 1, 1, 'succesfully logined', '2019-07-10 16:56:10'),
(278, 1, 1, 'succesfully logined', '2019-07-10 17:09:06'),
(279, 1, 1, 'succesfully logined', '2019-07-10 17:23:06'),
(280, 1, 1, 'succesfully logined', '2019-07-10 17:28:46'),
(281, 1, 1, 'succesfully logined', '2019-07-10 17:51:23'),
(282, 1, 1, 'succesfully logined', '2019-07-10 17:54:59'),
(283, 1, 1, 'succesfully logined', '2019-07-10 18:03:21'),
(284, 1, 1, 'succesfully logined', '2019-07-10 18:07:13'),
(285, 1, 1, 'succesfully logined', '2019-07-10 18:17:42'),
(286, 1, 1, 'succesfully logined', '2019-07-11 09:02:07'),
(287, 1, 1, 'succesfully logined', '2019-07-11 09:25:52'),
(288, 1, 1, 'succesfully logined', '2019-07-11 09:32:47'),
(289, 1, 1, 'succesfully logined', '2019-07-11 09:40:56'),
(290, 1, 1, 'succesfully logined', '2019-07-11 09:41:01'),
(291, 1, 1, 'succesfully logined', '2019-07-11 09:52:13'),
(292, 1, 1, 'succesfully logined', '2019-07-11 09:54:01'),
(293, 1, 1, 'succesfully logined', '2019-07-11 09:55:58'),
(294, 1, 1, 'succesfully logined', '2019-07-11 09:57:11'),
(295, 1, 1, 'succesfully logined', '2019-07-11 09:57:47'),
(296, 1, 1, 'succesfully logined', '2019-07-11 09:58:46'),
(297, 1, 1, 'succesfully logined', '2019-07-11 09:59:40'),
(298, 1, 1, 'succesfully logined', '2019-07-11 10:03:20'),
(299, 1, 1, 'succesfully logined', '2019-07-11 10:06:02'),
(300, 1, 1, 'succesfully logined', '2019-07-11 10:06:56'),
(301, 1, 1, 'succesfully logined', '2019-07-11 10:07:40'),
(302, 1, 1, 'succesfully logined', '2019-07-11 10:08:54'),
(303, 1, 1, 'succesfully logined', '2019-07-11 10:10:31'),
(304, 1, 1, 'succesfully logined', '2019-07-11 10:12:38'),
(305, 1, 1, 'succesfully logined', '2019-07-11 10:15:58'),
(306, 1, 1, 'succesfully logined', '2019-07-11 10:21:33'),
(307, 1, 1, 'succesfully logined', '2019-07-11 10:23:28'),
(308, 1, 1, 'succesfully logined', '2019-07-11 10:46:32'),
(309, 1, 1, 'succesfully logined', '2019-07-11 11:27:25'),
(310, 2, 1, 'succesfully logined', '2019-07-11 11:58:51'),
(311, 1, 1, 'succesfully logined', '2019-07-11 12:00:36'),
(312, 1, 1, 'succesfully logined', '2019-07-11 12:27:27'),
(313, 1, 1, 'succesfully logined', '2019-07-11 13:27:27'),
(314, 1, 1, 'succesfully logined', '2019-07-11 14:27:59'),
(315, 1, 1, 'succesfully logined', '2019-07-11 14:58:37'),
(316, 1, 1, 'succesfully logined', '2019-07-11 15:08:41'),
(317, 1, 1, 'succesfully logined', '2019-07-11 16:07:56'),
(318, 1, 1, 'succesfully logined', '2019-07-11 16:21:53'),
(319, 1, 1, 'succesfully logined', '2019-07-11 16:24:51'),
(320, 1, 1, 'succesfully logined', '2019-07-11 16:33:49'),
(321, 1, 1, 'succesfully logined', '2019-07-11 16:51:34'),
(322, 1, 1, 'succesfully logined', '2019-07-11 17:08:53'),
(323, 1, 1, 'succesfully logined', '2019-07-11 17:31:44'),
(324, 1, 1, 'succesfully logined', '2019-07-11 17:39:08'),
(325, 1, 1, 'succesfully logined', '2019-07-11 17:44:11'),
(326, 1, 1, 'succesfully logined', '2019-07-11 17:49:34'),
(327, 1, 1, 'succesfully logined', '2019-07-11 18:14:15'),
(328, 1, 1, 'succesfully logined', '2019-07-12 09:18:13'),
(329, 1, 1, 'succesfully logined', '2019-07-12 09:29:09'),
(330, 1, 1, 'succesfully logined', '2019-07-12 09:36:23'),
(331, 1, 1, 'succesfully logined', '2019-07-12 10:01:50'),
(332, 1, 1, 'succesfully logined', '2019-07-12 10:10:07'),
(333, 1, 1, 'succesfully logined', '2019-07-12 10:14:57'),
(334, 1, 1, 'succesfully logined', '2019-07-12 10:26:35'),
(335, 1, 1, 'succesfully logined', '2019-07-12 10:49:04'),
(336, 1, 1, 'succesfully logined', '2019-07-12 11:10:13'),
(337, 1, 1, 'succesfully logined', '2019-07-12 11:14:37'),
(338, 1, 1, 'succesfully logined', '2019-07-12 11:21:24'),
(339, 1, 1, 'succesfully logined', '2019-07-12 11:24:05'),
(340, 1, 1, 'succesfully logined', '2019-07-12 11:24:55'),
(341, 1, 1, 'succesfully logined', '2019-07-12 11:30:05'),
(342, 1, 1, 'succesfully logined', '2019-07-12 11:31:15'),
(343, 1, 1, 'succesfully logined', '2019-07-12 11:32:47'),
(344, 1, 1, 'succesfully logined', '2019-07-12 11:36:08'),
(345, 1, 1, 'succesfully logined', '2019-07-12 11:54:19'),
(346, 1, 1, 'succesfully logined', '2019-07-12 11:57:48'),
(347, 1, 1, 'succesfully logined', '2019-07-12 12:00:46'),
(348, 1, 1, 'succesfully logined', '2019-07-12 12:06:35'),
(349, 1, 1, 'succesfully logined', '2019-07-12 12:17:39'),
(350, 1, 1, 'succesfully logined', '2019-07-12 12:19:30'),
(351, 1, 1, 'succesfully logined', '2019-07-12 13:38:33'),
(352, 1, 1, 'succesfully logined', '2019-07-12 14:10:31'),
(353, 1, 1, 'succesfully logined', '2019-07-12 14:18:18'),
(354, 1, 1, 'succesfully logined', '2019-07-12 14:23:22'),
(355, 1, 1, 'succesfully logined', '2019-07-12 14:28:16'),
(356, 1, 1, 'succesfully logined', '2019-07-12 15:16:59'),
(357, 1, 1, 'succesfully logined', '2019-07-12 15:18:42'),
(358, 1, 1, 'succesfully logined', '2019-07-12 15:20:26'),
(359, 1, 1, 'succesfully logined', '2019-07-12 15:33:20'),
(360, 1, 1, 'succesfully logined', '2019-07-12 15:54:10'),
(361, 1, 1, 'succesfully logined', '2019-07-12 15:58:17'),
(362, 1, 1, 'succesfully logined', '2019-07-12 16:03:09'),
(363, 1, 1, 'succesfully logined', '2019-07-12 16:13:57'),
(364, 1, 1, 'succesfully logined', '2019-07-12 16:20:00'),
(365, 1, 1, 'succesfully logined', '2019-07-12 16:25:57'),
(366, 1, 1, 'succesfully logined', '2019-07-12 18:15:48'),
(367, 1, 1, 'succesfully logined', '2019-07-12 18:27:53'),
(368, 1, 1, 'succesfully logined', '2019-07-16 09:06:59'),
(369, 1, 1, 'succesfully logined', '2019-07-16 09:16:37'),
(370, 1, 1, 'succesfully logined', '2019-07-16 09:26:11'),
(371, 1, 1, 'succesfully logined', '2019-07-16 09:28:28'),
(372, 1, 1, 'succesfully logined', '2019-07-16 09:32:20'),
(373, 1, 1, 'succesfully logined', '2019-07-16 09:40:40'),
(374, 1, 1, 'succesfully logined', '2019-07-16 09:41:56'),
(375, 1, 1, 'succesfully logined', '2019-07-16 09:51:08'),
(376, 1, 1, 'succesfully logined', '2019-07-16 09:51:09'),
(377, 1, 1, 'succesfully logined', '2019-07-16 09:54:13'),
(378, 1, 1, 'succesfully logined', '2019-07-16 10:02:30'),
(379, 1, 1, 'succesfully logined', '2019-07-16 10:14:28'),
(380, 1, 1, 'succesfully logined', '2019-07-16 10:26:06'),
(381, 1, 1, 'succesfully logined', '2019-07-16 10:34:33'),
(382, 1, 1, 'succesfully logined', '2019-07-16 11:24:23'),
(383, 1, 1, 'succesfully logined', '2019-07-16 11:33:25'),
(384, 1, 1, 'succesfully logined', '2019-07-16 11:33:34'),
(385, 1, 1, 'succesfully logined', '2019-07-16 11:44:13'),
(386, 1, 1, 'succesfully logined', '2019-07-16 11:50:12'),
(387, 1, 1, 'succesfully logined', '2019-07-16 11:55:06'),
(388, 1, 1, 'succesfully logined', '2019-07-16 12:10:13'),
(389, 1, 1, 'succesfully logined', '2019-07-16 13:58:56'),
(390, 1, 1, 'succesfully logined', '2019-07-16 14:09:16'),
(391, 1, 1, 'succesfully logined', '2019-07-16 14:40:06'),
(392, 1, 1, 'succesfully logined', '2019-07-16 15:33:33'),
(393, 1, 1, 'succesfully logined', '2019-07-16 15:53:37'),
(394, 1, 1, 'succesfully logined', '2019-07-16 16:08:51'),
(397, 1, 1, 'succesfully logined', '2019-07-16 16:35:14'),
(401, 1, 1, 'succesfully logined', '2019-07-16 16:41:55'),
(404, 1, 1, 'succesfully logined', '2019-07-16 16:50:26'),
(405, 1, 1, 'succesfully logined', '2019-07-16 16:54:02'),
(406, 1, 1, 'succesfully logined', '2019-07-16 17:00:12'),
(407, 1, 1, 'succesfully logined', '2019-07-16 17:53:09'),
(408, 1, 1, 'succesfully logined', '2019-07-16 18:06:05'),
(409, 1, 1, 'succesfully logined', '2019-07-16 18:06:05'),
(410, 1, 1, 'succesfully logined', '2019-07-16 18:10:41'),
(413, 1, 1, 'succesfully logined', '2019-07-16 18:27:51'),
(414, 1, 1, 'succesfully logined', '2019-07-17 09:09:30'),
(415, 1, 1, 'succesfully logined', '2019-07-17 09:13:06'),
(416, 3, 5, 'user made passive', '2019-07-17 09:15:05'),
(417, 4, 5, 'user made passive', '2019-07-17 09:16:10'),
(419, 6, 5, 'user made passive', '2019-07-17 09:17:27'),
(423, 1, 1, 'succesfully logined', '2019-07-17 09:26:13'),
(424, 1, 1, 'succesfully logined', '2019-07-17 09:33:25'),
(425, 4, 5, 'user made passive', '2019-07-17 09:34:24'),
(428, 1, 1, 'succesfully logined', '2019-07-17 09:38:13'),
(433, 1, 1, 'succesfully logined', '2019-07-17 09:48:50'),
(435, 1, 1, 'succesfully logined', '2019-07-17 10:03:46'),
(436, 6, 6, 'user made active', '2019-07-17 10:04:00'),
(437, 3, 6, 'user made active', '2019-07-17 10:04:08'),
(438, 4, 6, 'user made active', '2019-07-17 10:04:10'),
(439, 4, 6, 'user made active', '2019-07-17 10:04:17'),
(441, 3, 5, 'user made passive', '2019-07-17 10:21:24'),
(442, 2, 5, 'user made passive', '2019-07-17 10:21:35'),
(443, 2, 6, 'user made active', '2019-07-17 10:21:39'),
(444, 3, 6, 'user made active', '2019-07-17 10:21:42'),
(445, 3, 5, 'user made passive', '2019-07-17 10:21:48'),
(446, 3, 6, 'user made active', '2019-07-17 10:21:53'),
(447, 1, 1, 'succesfully logined', '2019-07-17 10:23:12'),
(448, 1, 1, 'succesfully logined', '2019-07-17 10:26:28'),
(449, 1, 1, 'succesfully logined', '2019-07-17 10:29:10'),
(450, 1, 1, 'succesfully logined', '2019-07-17 10:33:32'),
(451, 1, 1, 'succesfully logined', '2019-07-17 11:09:13'),
(452, 1, 1, 'succesfully logined', '2019-07-17 15:31:26'),
(453, 1, 1, 'succesfully logined', '2019-07-17 15:33:46'),
(454, 1, 9, 'add drop started', '2019-07-17 15:34:00'),
(455, 1, 1, 'succesfully logined', '2019-07-17 15:47:22'),
(456, 1, 10, 'add drop ended', '2019-07-17 15:50:37'),
(457, 1, 9, 'add drop started', '2019-07-17 15:50:47'),
(458, 1, 10, 'add drop ended', '2019-07-17 15:52:32'),
(459, 1, 9, 'add drop started', '2019-07-17 15:52:32'),
(460, 1, 10, 'add drop ended', '2019-07-17 15:52:33'),
(461, 1, 9, 'add drop started', '2019-07-17 15:52:33'),
(462, 1, 10, 'add drop ended', '2019-07-17 15:52:34'),
(463, 1, 9, 'add drop started', '2019-07-17 15:52:34'),
(464, 1, 10, 'add drop ended', '2019-07-17 15:52:36'),
(465, 1, 9, 'add drop started', '2019-07-17 15:52:50'),
(466, 1, 1, 'succesfully logined', '2019-07-17 15:57:26'),
(467, 1, 10, 'add drop ended', '2019-07-17 15:57:39'),
(468, 1, 9, 'add drop started', '2019-07-17 15:57:40'),
(469, 1, 10, 'add drop ended', '2019-07-17 15:57:43'),
(470, 1, 9, 'add drop started', '2019-07-17 15:57:44'),
(471, 1, 10, 'add drop ended', '2019-07-17 15:57:45'),
(472, 1, 9, 'add drop started', '2019-07-17 15:57:50'),
(473, 1, 10, 'add drop ended', '2019-07-17 15:57:57'),
(474, 1, 1, 'succesfully logined', '2019-07-17 15:59:27'),
(475, 1, 1, 'succesfully logined', '2019-07-17 16:13:29'),
(476, 1, 1, 'succesfully logined', '2019-07-17 16:14:42'),
(477, 1, 1, 'succesfully logined', '2019-07-17 16:17:44'),
(478, 1, 9, 'add drop started', '2019-07-17 16:19:20'),
(479, 1, 10, 'add drop ended', '2019-07-17 16:19:58'),
(480, 1, 9, 'add drop started', '2019-07-17 16:20:06'),
(481, 1, 10, 'add drop ended', '2019-07-17 16:26:37'),
(482, 1, 9, 'add drop started', '2019-07-17 16:27:32'),
(483, 1, 10, 'add drop ended', '2019-07-17 16:27:35'),
(484, 1, 9, 'add drop started', '2019-07-17 16:27:37'),
(485, 1, 10, 'add drop ended', '2019-07-17 16:27:40'),
(486, 1, 9, 'add drop started', '2019-07-17 16:28:21'),
(487, 1, 1, 'succesfully logined', '2019-07-17 16:35:08'),
(488, 1, 10, 'add drop ended', '2019-07-17 16:35:18'),
(489, 1, 9, 'add drop started', '2019-07-17 16:35:19'),
(490, 1, 10, 'add drop ended', '2019-07-17 16:35:20'),
(491, 1, 9, 'add drop started', '2019-07-17 16:35:22'),
(492, 1, 10, 'add drop ended', '2019-07-17 16:35:22'),
(493, 1, 9, 'add drop started', '2019-07-17 16:35:23'),
(494, 1, 10, 'add drop ended', '2019-07-17 16:35:24'),
(495, 1, 9, 'add drop started', '2019-07-17 16:35:24'),
(496, 1, 10, 'add drop ended', '2019-07-17 16:35:24'),
(497, 1, 9, 'add drop started', '2019-07-17 16:35:25'),
(498, 1, 10, 'add drop ended', '2019-07-17 16:35:25'),
(499, 1, 1, 'succesfully logined', '2019-07-17 16:43:20'),
(500, 1, 9, 'add drop started', '2019-07-17 16:43:30'),
(501, 1, 10, 'add drop ended', '2019-07-17 16:46:58'),
(502, 1, 9, 'add drop started', '2019-07-17 16:46:59'),
(503, 1, 10, 'add drop ended', '2019-07-17 16:58:25'),
(504, 1, 9, 'add drop started', '2019-07-17 16:58:25'),
(505, 1, 10, 'add drop ended', '2019-07-17 16:58:26'),
(506, 1, 9, 'add drop started', '2019-07-17 16:58:26'),
(507, 1, 10, 'add drop ended', '2019-07-17 16:58:26'),
(508, 1, 9, 'add drop started', '2019-07-17 16:58:27'),
(509, 1, 10, 'add drop ended', '2019-07-17 16:58:27'),
(510, 1, 9, 'add drop started', '2019-07-17 16:58:27'),
(511, 1, 10, 'add drop ended', '2019-07-17 16:58:27'),
(512, 1, 9, 'add drop started', '2019-07-17 16:58:28'),
(513, 1, 10, 'add drop ended', '2019-07-17 16:58:28'),
(514, 1, 9, 'add drop started', '2019-07-17 16:58:28'),
(515, 1, 1, 'succesfully logined', '2019-07-17 17:47:29'),
(516, 1, 1, 'succesfully logined', '2019-07-17 17:55:12'),
(517, 1, 1, 'succesfully logined', '2019-07-17 17:58:34'),
(518, 1, 1, 'succesfully logined', '2019-07-17 18:05:48'),
(519, 1, 1, 'succesfully logined', '2019-07-18 09:16:50'),
(520, 1, 10, 'add drop ended', '2019-07-18 09:19:32'),
(521, 1, 1, 'succesfully logined', '2019-07-18 09:26:33'),
(522, 1, 1, 'succesfully logined', '2019-07-18 09:30:32'),
(523, 1, 1, 'succesfully logined', '2019-07-18 09:32:52'),
(524, 1, 1, 'succesfully logined', '2019-07-18 09:37:42'),
(525, 1, 1, 'succesfully logined', '2019-07-18 09:44:31'),
(526, 1, 1, 'succesfully logined', '2019-07-18 09:46:14'),
(527, 1, 9, 'add drop started', '2019-07-18 09:47:26'),
(528, 1, 10, 'add drop ended', '2019-07-18 09:47:28'),
(529, 1, 9, 'add drop started', '2019-07-18 09:47:28'),
(530, 1, 1, 'succesfully logined', '2019-07-18 09:48:54'),
(531, 1, 1, 'succesfully logined', '2019-07-18 10:00:05'),
(532, 1, 1, 'succesfully logined', '2019-07-18 10:21:57'),
(533, 1, 1, 'succesfully logined', '2019-07-18 10:22:59'),
(534, 1, 1, 'succesfully logined', '2019-07-18 10:24:50'),
(535, 1, 1, 'succesfully logined', '2019-07-18 10:27:02'),
(536, 1, 10, 'add drop ended', '2019-07-18 10:27:17'),
(537, 1, 9, 'add drop started', '2019-07-18 10:27:18'),
(538, 1, 10, 'add drop ended', '2019-07-18 10:27:20'),
(539, 1, 9, 'add drop started', '2019-07-18 10:27:21'),
(540, 1, 10, 'add drop ended', '2019-07-18 10:27:23'),
(541, 1, 9, 'add drop started', '2019-07-18 10:27:24'),
(542, 1, 10, 'add drop ended', '2019-07-18 10:27:24'),
(543, 1, 9, 'add drop started', '2019-07-18 10:27:32'),
(544, 1, 10, 'add drop ended', '2019-07-18 10:27:32'),
(545, 1, 9, 'add drop started', '2019-07-18 10:27:32'),
(546, 1, 10, 'add drop ended', '2019-07-18 10:27:33'),
(547, 1, 9, 'add drop started', '2019-07-18 10:27:33'),
(577, 1, 1, 'succesfully logined', '2019-07-18 12:02:34'),
(578, 1, 1, 'succesfully logined', '2019-07-18 12:12:57'),
(579, 2, 5, 'user made passive', '2019-07-18 12:13:24'),
(580, 1, 1, 'succesfully logined', '2019-07-18 12:15:21'),
(581, 1, 7, 'new term started', '2019-07-18 12:15:41'),
(582, 1, 1, 'succesfully logined', '2019-07-18 12:16:55'),
(583, 1, 1, 'succesfully logined', '2019-07-18 12:20:21'),
(584, 1, 1, 'succesfully logined', '2019-07-18 12:26:48'),
(585, 1, 7, 'new term started', '2019-07-18 12:27:19'),
(586, 1, 1, 'succesfully logined', '2019-07-18 13:46:07'),
(587, 1, 1, 'succesfully logined', '2019-07-18 13:46:48'),
(588, 1, 7, 'new term started', '2019-07-18 13:47:14'),
(589, 1, 10, 'add drop ended', '2019-07-18 13:47:16'),
(590, 1, 9, 'add drop started', '2019-07-18 13:47:18'),
(591, 1, 1, 'succesfully logined', '2019-07-18 13:50:24'),
(592, 1, 1, 'succesfully logined', '2019-07-18 13:56:03'),
(593, 1, 10, 'add drop ended', '2019-07-18 13:56:23'),
(594, 3, 4, 'password changed', '2019-07-18 14:05:39'),
(595, 1, 1, 'succesfully logined', '2019-07-18 14:05:58'),
(596, 1, 7, 'new term started', '2019-07-18 14:06:16'),
(597, 1, 1, 'succesfully logined', '2019-07-18 14:08:17'),
(598, 1, 7, 'new term started', '2019-07-18 14:09:26'),
(599, 1, 9, 'add drop started', '2019-07-18 14:09:33'),
(600, 1, 10, 'add drop ended', '2019-07-18 14:09:37'),
(601, 1, 7, 'new term started', '2019-07-18 14:10:31'),
(602, 1, 9, 'add drop started', '2019-07-18 14:10:39'),
(603, 1, 10, 'add drop ended', '2019-07-18 14:10:41'),
(604, 1, 1, 'succesfully logined', '2019-07-18 15:49:11'),
(605, 1, 1, 'succesfully logined', '2019-07-18 15:51:56'),
(606, 1, 1, 'succesfully logined', '2019-07-18 15:55:31'),
(607, 1, 9, 'add drop started', '2019-07-18 15:55:47'),
(608, 1, 10, 'add drop ended', '2019-07-18 15:55:48'),
(609, 1, 1, 'succesfully logined', '2019-07-18 16:12:48'),
(610, 1, 1, 'succesfully logined', '2019-07-18 16:13:42'),
(611, 1, 1, 'succesfully logined', '2019-07-18 16:50:49'),
(612, 1, 1, 'succesfully logined', '2019-07-18 16:54:46'),
(613, 1, 1, 'succesfully logined', '2019-07-18 16:57:00'),
(614, 1, 1, 'succesfully logined', '2019-07-18 16:58:25'),
(615, 1, 1, 'succesfully logined', '2019-07-18 17:03:41'),
(616, 1, 1, 'succesfully logined', '2019-07-18 17:06:26'),
(617, 1, 1, 'succesfully logined', '2019-07-18 17:10:50'),
(618, 1, 1, 'succesfully logined', '2019-07-18 18:03:19'),
(619, 1, 1, 'succesfully logined', '2019-07-18 18:18:36'),
(620, 1, 8, 'new term ended', '2019-07-18 18:20:39'),
(621, 1, 7, 'new term started', '2019-07-18 18:20:48'),
(622, 1, 8, 'new term ended', '2019-07-18 18:20:55'),
(623, 1, 7, 'new term started', '2019-07-18 18:21:04'),
(624, 1, 1, 'succesfully logined', '2019-07-19 09:16:07'),
(625, 1, 1, 'succesfully logined', '2019-07-19 09:23:21'),
(626, 3, 5, 'user made passive', '2019-07-19 09:25:47'),
(627, 4, 5, 'user made passive', '2019-07-19 09:25:50'),
(628, 4, 6, 'user made active', '2019-07-19 09:25:51'),
(629, 3, 6, 'user made active', '2019-07-19 09:25:53'),
(630, 1, 9, 'add drop started', '2019-07-19 09:28:45'),
(631, 1, 10, 'add drop ended', '2019-07-19 09:28:46'),
(632, 1, 9, 'add drop started', '2019-07-19 09:28:48'),
(633, 1, 10, 'add drop ended', '2019-07-19 09:28:56'),
(634, 1, 8, 'new term ended', '2019-07-19 09:28:58'),
(635, 1, 7, 'new term started', '2019-07-19 09:29:07'),
(636, 1, 11, 'new course created', '2019-07-19 09:29:43'),
(639, 1, 1, 'succesfully logined', '2019-07-19 10:04:34'),
(640, 1, 11, 'new course created', '2019-07-19 11:28:48'),
(641, 1, 1, 'succesfully logined', '2019-07-19 11:46:10'),
(642, 1, 1, 'succesfully logined', '2019-07-19 11:49:56'),
(643, 1, 1, 'succesfully logined', '2019-07-19 11:54:30'),
(644, 1, 1, 'succesfully logined', '2019-07-19 12:09:09'),
(645, 1, 1, 'succesfully logined', '2019-07-19 15:39:06'),
(646, 1, 1, 'succesfully logined', '2019-07-19 15:52:17'),
(647, 1, 1, 'succesfully logined', '2019-07-19 16:07:18'),
(648, 1, 1, 'succesfully logined', '2019-07-19 16:10:48'),
(649, 1, 1, 'succesfully logined', '2019-07-19 16:13:57'),
(650, 1, 1, 'succesfully logined', '2019-07-19 17:17:26');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `runtime_properties`
--

CREATE TABLE `runtime_properties` (
  `RUN_TIME_ID` int(11) NOT NULL,
  `CURRENT_TERM` int(11) DEFAULT NULL,
  `IS_OPEN_ADD_DROP` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `runtime_properties`
--

INSERT INTO `runtime_properties` (`RUN_TIME_ID`, `CURRENT_TERM`, `IS_OPEN_ADD_DROP`) VALUES
(1, 11, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `terms`
--

CREATE TABLE `terms` (
  `TERM_ID` int(11) NOT NULL,
  `START_DATE` datetime NOT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `TERM_NAME` varchar(25) NOT NULL,
  `STATUS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `terms`
--

INSERT INTO `terms` (`TERM_ID`, `START_DATE`, `END_DATE`, `TERM_NAME`, `STATUS`) VALUES
(1, '2019-07-03 00:00:00', NULL, '2019YAZ', 1),
(2, '2018-12-01 00:00:00', '2019-01-01 00:00:00', '2018KIS', 0),
(3, '2019-07-18 12:15:41', '2019-07-18 12:20:36', '2020GUZ', 0),
(4, '2019-07-18 12:27:19', '2019-07-18 12:27:23', '2021YAZ', 0),
(5, '2019-07-18 13:47:14', '2019-07-18 13:56:25', 'TEST', 0),
(6, '2019-07-18 14:06:16', '2019-07-18 14:09:15', 'TEST2', 0),
(7, '2019-07-18 14:09:25', '2019-07-18 14:09:55', 'TEST3', 0),
(8, '2019-07-18 14:10:31', '2019-07-18 18:20:39', '2021GUZ', 0),
(9, '2019-07-18 18:20:48', '2019-07-18 18:20:55', '2021BAHAR', 0),
(10, '2019-07-18 18:21:04', '2019-07-19 09:28:58', '2021YAZ', 0),
(11, '2019-07-19 09:29:07', NULL, 'YAZ2022', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL,
  `TCKN` varchar(25) NOT NULL,
  `NAME` varchar(25) NOT NULL,
  `SURNAME` varchar(25) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `STATUS` tinyint(1) NOT NULL,
  `TYPE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`USER_ID`, `TCKN`, `NAME`, `SURNAME`, `PASSWORD`, `EMAIL`, `STATUS`, `TYPE`) VALUES
(1, '11111111111', 'AYBERK', 'USLU', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ayberkuslu@gmail.com', 1, 0),
(2, '111111112', 'admin', 'admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'admin@gmail.com', 0, 0),
(3, '123', '123', '123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '123', 1, 0),
(4, '111111', 'denemeOgrenci', 'denemeOgrSoy', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'email', 1, 1),
(6, '1111', 'test2', 'test2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'test2', 1, 1),
(7, '11111', 'teacher', 'teacher', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'teacher', 1, 2),
(9, '111211', 'student', 'student', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'student', 1, 1),
(11, '121111', 'testOgrenci2', 'test', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'test', 1, 1),
(12, '1112211', 'testTeacher2', 'testTeacher2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'testTeacher2', 1, 2),
(22, '132121', 'HAKK?', 'DOGANER', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'hakkidoganer@gmail.com', 1, 2),
(23, '121212111', 'BASAK', 'Y?LD?R?M', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'basakyildirim@gmail.com', 1, 1),
(24, '213123123', 'AAAA', '123123', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'a@gmail.com', 1, 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user_details`
--

CREATE TABLE `user_details` (
  `DETAIL_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `PHONE` varchar(25) NOT NULL,
  `ADRESS` text,
  `BIRTHDAY` date DEFAULT NULL,
  `GENDER` varchar(25) NOT NULL,
  `CURRENT_GPA` double DEFAULT NULL,
  `GRADUATE` varchar(40) DEFAULT NULL,
  `MASTER` varchar(40) DEFAULT NULL,
  `EMERGENCY_PHONE` varchar(25) DEFAULT NULL,
  `SECRET_QUESTION` varchar(55) NOT NULL,
  `SECRET_ANSWER` varchar(255) NOT NULL,
  `REGISTER_DATE` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Tablo döküm verisi `user_details`
--

INSERT INTO `user_details` (`DETAIL_ID`, `USER_ID`, `PHONE`, `ADRESS`, `BIRTHDAY`, `GENDER`, `CURRENT_GPA`, `GRADUATE`, `MASTER`, `EMERGENCY_PHONE`, `SECRET_QUESTION`, `SECRET_ANSWER`, `REGISTER_DATE`) VALUES
(1, 1, '5549060502', 'Bu bir adres.', '2019-06-01', 'MALE', 4, NULL, NULL, NULL, 'Türkiyenin baskenti?', '569b96c4f396e9feba7a56c0bd0fc8837064d0ebd85436703933f390cf93ed2b', '2019-06-12 10:15:09'),
(2, 2, '000000', 'Adres2.', '2019-06-05', 'FEMALE', 4, NULL, NULL, NULL, '4+5?', '19581e27de7ced00ff1ce50b2047e7a567c76b1cbaebabe5ef03f7c3017bb5b7', '0000-00-00 00:00:00'),
(3, 3, '123', '123', '2019-06-10', 'MALE', NULL, '123', '123', '213', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-06-26 11:39:16'),
(4, 4, '555555', 'adres', '2019-06-04', 'MALE', NULL, 'lisans', 'yukseklisans', '5555555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-06-28 16:49:25'),
(5, 6, '555', 'adres', '2019-07-01', 'MALE', NULL, 'lisans', 'yuksek lisans', '555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-02 15:55:02'),
(6, 7, '5555', 'adres', '2019-07-01', 'FEMALE', NULL, 'lisans', 'yuksek lisans', '5555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-03 10:41:59'),
(7, 9, '5555', 'adres', '2019-07-01', 'MALE', NULL, '', '', '5555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-03 10:44:56'),
(8, 11, '555', 'adres', '2019-07-02', 'FEMALE', NULL, '', '', '555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-04 14:24:06'),
(9, 12, '5555', 'adres', '2019-07-01', 'MALE', NULL, '', '', '5555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-04 17:49:15'),
(17, 22, '555414514', 'ANKARA', '2019-06-01', 'MALE', NULL, 'TOBB', '', '55544455', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-11 17:46:14'),
(18, 23, '555555', 'ANKARA', '2019-02-06', 'FEMALE', NULL, 'TOBB', '', '555555', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-12 10:07:09'),
(19, 24, '444', 'ASDASD', '2019-07-01', 'MALE', NULL, '', '', '444', '1+1', 'd4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35', '2019-07-19 09:28:07');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`CLASSES_ID`),
  ADD KEY `LECTURER` (`USER_ID`),
  ADD KEY `COURSE` (`COURSE_ID`);

--
-- Tablo için indeksler `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`COURSE_ID`),
  ADD KEY `userid` (`USER_ID`),
  ADD KEY `TERM_ID` (`TERM_ID`);

--
-- Tablo için indeksler `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`LOG_ID`),
  ADD KEY `AS` (`USER_ID`);

--
-- Tablo için indeksler `runtime_properties`
--
ALTER TABLE `runtime_properties`
  ADD PRIMARY KEY (`RUN_TIME_ID`),
  ADD KEY `TERM` (`CURRENT_TERM`);

--
-- Tablo için indeksler `terms`
--
ALTER TABLE `terms`
  ADD PRIMARY KEY (`TERM_ID`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USER_ID`),
  ADD UNIQUE KEY `TCNO` (`TCKN`);

--
-- Tablo için indeksler `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`DETAIL_ID`),
  ADD KEY `USER_ID` (`USER_ID`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `classes`
--
ALTER TABLE `classes`
  MODIFY `CLASSES_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `courses`
--
ALTER TABLE `courses`
  MODIFY `COURSE_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `logs`
--
ALTER TABLE `logs`
  MODIFY `LOG_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=651;

--
-- Tablo için AUTO_INCREMENT değeri `runtime_properties`
--
ALTER TABLE `runtime_properties`
  MODIFY `RUN_TIME_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Tablo için AUTO_INCREMENT değeri `terms`
--
ALTER TABLE `terms`
  MODIFY `TERM_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `user_details`
--
ALTER TABLE `user_details`
  MODIFY `DETAIL_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `COURSE` FOREIGN KEY (`COURSE_ID`) REFERENCES `courses` (`COURSE_ID`),
  ADD CONSTRAINT `LECTURER` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);

--
-- Tablo kısıtlamaları `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `TERM_ID` FOREIGN KEY (`TERM_ID`) REFERENCES `terms` (`TERM_ID`),
  ADD CONSTRAINT `userid` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);

--
-- Tablo kısıtlamaları `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `AS` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);

--
-- Tablo kısıtlamaları `runtime_properties`
--
ALTER TABLE `runtime_properties`
  ADD CONSTRAINT `TERM` FOREIGN KEY (`CURRENT_TERM`) REFERENCES `terms` (`TERM_ID`);

--
-- Tablo kısıtlamaları `user_details`
--
ALTER TABLE `user_details`
  ADD CONSTRAINT `USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
