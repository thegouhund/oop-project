-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 15, 2024 at 04:41 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airplane_ticket`
--

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE `airline` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `airline`
--

INSERT INTO `airline` (`id`, `name`) VALUES
(2, 'Batik Air'),
(3, 'Citilink'),
(4, 'Garuda Indonesia'),
(5, 'AirAsia'),
(6, 'Lion Air'),
(7, 'Nam Air'),
(8, 'Pelita Air'),
(9, 'Sriwijaya Air'),
(10, 'Super Air Jet'),
(11, 'TransNusa'),
(12, 'Wings Air');

-- --------------------------------------------------------

--
-- Table structure for table `airport`
--

CREATE TABLE `airport` (
  `id` int(11) NOT NULL,
  `iata` varchar(5) NOT NULL,
  `city` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `airport`
--

INSERT INTO `airport` (`id`, `iata`, `city`) VALUES
(1, 'JFK', 'New York'),
(3, 'EWE', 'Agats'),
(4, 'ARD', 'Alor Island'),
(5, 'AHI', 'Amahai'),
(6, 'AMQ', 'Ambon'),
(7, 'OJU', 'Ampana'),
(8, 'AGD', 'Anggi'),
(9, 'ABU', 'Atambua'),
(10, 'AYW', 'Ayawasi'),
(11, 'BXB', 'Babo'),
(12, 'BPN', 'Balikpapan'),
(13, 'NDA', 'Banda'),
(14, 'BTJ', 'Banda Aceh'),
(15, 'TKG', 'Bandar Lampung'),
(16, 'BDO', 'Bandung'),
(17, 'KJT', 'Bandung'),
(18, 'BDJ', 'Banjarmasin'),
(19, 'BWX', 'Banyuwangi'),
(20, 'BTH', 'Batam'),
(21, 'LSE', 'Batu Islands'),
(22, 'BTW', 'Batulicin'),
(23, 'BUW', 'Baubau'),
(24, 'BXW', 'Bawean'),
(25, 'BKS', 'Bengkulu'),
(26, 'BJK', 'Benjina'),
(27, 'BEJ', 'Berau'),
(28, 'BIK', 'Biak'),
(29, 'BMU', 'Bima'),
(30, 'NTI', 'Bintuni'),
(31, 'KJX', 'Blangpidie'),
(32, 'BUI', 'Bokondini'),
(33, 'BXT', 'Bontang'),
(34, 'WSN', 'Buleleng'),
(35, 'PGQ', 'Buli'),
(36, 'UOL', 'Buol'),
(37, 'CPF', 'Cepu'),
(38, 'CXP', 'Cilacap'),
(39, 'CBN', 'Cirebon'),
(40, 'SIQ', 'Dabo'),
(41, 'DEX', 'Dekai'),
(42, 'DPS', 'Denpasar'),
(43, 'RTU', 'Derawan Islands'),
(44, 'DOB', 'Dobo'),
(45, 'DUM', 'Dumai'),
(46, 'EWI', 'Enarotali'),
(47, 'ENE', 'Ende'),
(48, '—', 'Enggano'),
(49, 'FKQ', 'Fakfak'),
(50, 'GLX', 'Galela'),
(51, 'GTO', 'Gorontalo'),
(52, 'GNS', 'Gunung Sitoli'),
(53, 'HLP', 'Jakarta'),
(54, 'CGK', 'Jakarta'),
(55, 'DJB', 'Jambi'),
(56, 'DJJ', 'Jayapura'),
(57, 'LMU', 'Jemaja Island'),
(58, 'JBB', 'Jember'),
(59, 'KNG', 'Kaimana'),
(60, 'KAZ', 'Kao'),
(61, 'KWB', 'Karimunjawa'),
(62, 'KDI', 'Kendari'),
(63, 'KRC', 'Kerinci'),
(64, 'KTG', 'Ketapang'),
(65, 'KSX', 'Kisar'),
(66, 'KXB', 'Kolaka'),
(67, 'KBU', 'Kotabaru'),
(68, 'TFY', 'Krui'),
(69, 'KLP', 'Kuala Pembuang'),
(70, 'KOE', 'Kupang'),
(71, 'LSR', 'Kutacane'),
(72, 'LBJ', 'Labuan Bajo'),
(73, 'LAH', 'Labuha'),
(74, 'LKA', 'Larantuka'),
(75, 'LWE', 'Lewoleba'),
(76, 'LSW', 'Lhokseumawe'),
(77, 'LPU', 'Long Apung'),
(78, 'LBW', 'Long Bawan'),
(79, 'LLJ', 'Lubuklinggau'),
(80, 'LUW', 'Luwuk'),
(81, 'DTD', 'Mahakam Ulu'),
(82, 'UPG', 'Makassar'),
(83, 'MLG', 'Malang'),
(84, 'LNU', 'Malinau'),
(85, 'MJU', 'Mamuju'),
(86, 'MDC', 'Manado'),
(87, 'MKW', 'Manokwari'),
(88, 'MXB', 'Masamba'),
(89, 'MWK', 'Matak'),
(90, 'LOP', 'Mataram'),
(91, 'MOF', 'Maumere'),
(92, 'KNO', 'Medan'),
(93, 'GHS', 'Melak'),
(94, 'MNA', 'Melonguane'),
(95, 'MKQ', 'Merauke'),
(96, 'MEQ', 'Meulaboh'),
(97, '—', 'Miangas'),
(98, 'JIO', 'Moa'),
(99, 'OTI', 'Morotai'),
(100, 'MOH', 'Morowali'),
(101, 'BUU', 'Muara Bungo'),
(102, 'HMS', 'Muara Teweh'),
(103, 'MPC', 'Mukomuko'),
(104, 'NBX', 'Nabire'),
(105, 'NAM', 'Namlea'),
(106, 'NRE', 'Namrole'),
(107, 'NPO', 'Nanga Pinoh'),
(108, 'FOO', 'Noemfoor'),
(109, 'NNX', 'Nunukan'),
(110, 'OKL', 'Oksibil'),
(111, 'PDG', 'Padang'),
(112, 'AEG', 'Padang Sidempuan'),
(113, 'PXA', 'Pagar Alam'),
(114, 'PKY', 'Palangkaraya'),
(115, 'PLM', 'Palembang'),
(116, 'LLO', 'Palopo'),
(117, 'PLW', 'Palu'),
(118, 'PGD', 'Pangandaran'),
(119, 'PGK', 'Pangkal Pinang'),
(120, 'PKN', 'Pangkalanbun'),
(121, 'PPR', 'Pasir Pengaraian'),
(122, 'PKU', 'Pekanbaru'),
(123, 'PNK', 'Pontianak'),
(124, 'PSJ', 'Poso'),
(125, 'PWL', 'Purbalingga'),
(126, 'PSU', 'Putussibau'),
(127, 'RAQ', 'Raha'),
(128, 'NTX', 'Ranai'),
(129, 'RGT', 'Rengat'),
(130, 'RTI', 'Rote'),
(131, 'RTG', 'Ruteng'),
(132, 'SBG', 'Sabang'),
(133, 'SAU', 'Sabu'),
(134, 'AAP', 'Samarinda'),
(135, 'SMQ', 'Sampit'),
(136, 'SQN', 'Sanana'),
(137, 'NAH', 'Sangihe'),
(138, 'SXK', 'Saumlaki'),
(139, 'SKO', 'Seko'),
(140, 'KSR', 'Selayar'),
(141, 'SRG', 'Semarang'),
(142, 'WABO', 'Serui'),
(143, 'FLZ', 'Sibolga'),
(144, 'DTB', 'Siborong-Borong'),
(145, 'SIW', 'Simalungun'),
(146, '—', 'Simpang Ampek'),
(147, 'LKI', 'Sinabang'),
(148, 'SKJ', 'Singkawang'),
(149, '—', 'Singkil'),
(150, 'SQG', 'Sintang'),
(151, 'RKI', 'Sipora'),
(152, 'SQR', 'Soroako'),
(153, 'SOQ', 'Sorong'),
(154, 'PCB', 'South Tangerang'),
(155, 'SWQ', 'Sumbawa Besar'),
(156, 'SUP', 'Sumenep'),
(157, 'SEQ', 'Sungai Pakning'),
(158, 'SUB', 'Surabaya'),
(159, 'SOC', 'Surakarta'),
(160, 'TXE', 'Takengon'),
(161, 'TMC', 'Tambolaka'),
(162, 'TRT', 'Tana Toraja'),
(163, 'TMH', 'Tanahmerah'),
(164, '—', 'Tangerang'),
(165, 'TJB', 'Tanjung Balai Karimun'),
(166, 'TJQ', 'Tanjung Pandan'),
(167, 'TNJ', 'Tanjung Pinang'),
(168, 'TJS', 'Tanjung Selor'),
(169, 'TRK', 'Tarakan'),
(170, 'TSY', 'Tasikmalaya'),
(171, 'TMB', 'Tembilahan'),
(172, 'TTE', 'Ternate'),
(173, 'TIM', 'Timika'),
(174, 'LUV', 'Tual'),
(175, 'WGP', 'Waingapu'),
(176, 'RJM', 'Waisai'),
(177, 'WMX', 'Wamena'),
(178, 'WNI', 'Wangi-Wangi'),
(179, 'WSR', 'Wasior'),
(180, 'WRR', 'Werur'),
(181, 'JOG', 'Yogyakarta'),
(182, 'YIA', 'Yogyakarta');

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE `passenger` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `age` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `airline_id` int(11) NOT NULL,
  `airport_arrival_id` int(11) NOT NULL,
  `airport_departure_id` int(11) NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `passenger_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `airport`
--
ALTER TABLE `airport`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `schedule_ibfk_1` (`airline_id`),
  ADD KEY `schedule_ibfk_2` (`airport_arrival_id`),
  ADD KEY `schedule_ibfk_3` (`airport_departure_id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `passenger_id` (`passenger_id`),
  ADD KEY `schedule_id` (`schedule_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airline`
--
ALTER TABLE `airline`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `airport`
--
ALTER TABLE `airport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=183;

--
-- AUTO_INCREMENT for table `passenger`
--
ALTER TABLE `passenger`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`airline_id`) REFERENCES `airline` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`airport_arrival_id`) REFERENCES `airport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`airport_departure_id`) REFERENCES `airport` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
