-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2021 at 04:34 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pulsa`
--

-- --------------------------------------------------------

--
-- Table structure for table `harga`
--

CREATE TABLE `harga` (
  `id_harga` int(11) NOT NULL,
  `operator` varchar(100) NOT NULL,
  `nominal` int(11) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `harga`
--

INSERT INTO `harga` (`id_harga`, `operator`, `nominal`, `harga`) VALUES
(1, 'XL', 5000, 5850),
(2, 'XL', 15000, 15100),
(3, 'AXIS', 15000, 15100),
(5, 'IM3', 5000, 5875),
(6, 'SIMPATI', 10000, 10450),
(7, 'THREE', 1000, 1250),
(8, 'SMARTFREN', 5000, 5125),
(9, 'AXIS', 25000, 24850),
(12, 'IM3', 10000, 10900),
(13, 'XL', 10000, 10825),
(14, 'SIMPATI', 15000, 15200),
(16, 'SMARTFREN', 20000, 19900),
(17, 'THREE', 5000, 5400),
(18, 'THREE', 10000, 10400),
(19, 'SIMPATI', 25000, 25150),
(20, 'SIMPATI', 5000, 5500),
(23, 'IM3', 20000, 19928),
(24, 'SIMPATI', 20000, 20100);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nama` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `nama`) VALUES
(1, 'Admin'),
(2, 'Member');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_harga` int(11) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `tgl_transaksi` datetime DEFAULT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `id_harga`, `no_hp`, `tgl_transaksi`, `status`) VALUES
(1, 2, 1, '081832149558', '2021-07-08 19:44:14', 'TERKIRIM'),
(2, 1, 1, '08946627181', '2021-07-08 19:47:25', 'TERKIRIM'),
(3, 1, 3, '087736364758', '2021-07-08 21:35:26', 'TERKIRIM'),
(4, 4, 3, '0747748293', '2021-07-08 22:03:26', 'TERKIRIM'),
(5, 4, 1, '081277283424', '2021-07-08 21:35:18', 'TERKIRIM'),
(6, 6, 5, '0182983493', '2021-07-08 22:10:47', 'TERKIRIM'),
(7, 2, 6, '0899383387', '2021-07-08 22:11:13', 'TERKIRIM'),
(8, 2, 1, '08112828392', '2021-07-08 22:29:42', 'TERKIRIM'),
(9, 6, 2, '081722637372', '2021-07-09 10:04:09', 'TERKIRIM'),
(10, 6, 17, '0895421396612', '2021-07-09 11:16:30', 'TERKIRIM'),
(11, 2, 5, '08472727381', '2021-07-09 12:08:36', 'TERKIRIM'),
(12, 7, 2, '08177273813', '2021-07-09 20:26:26', 'BELUM TERKIRIM'),
(13, 8, 7, '0882737321', '2021-07-09 20:38:02', 'TERKIRIM'),
(14, 9, 17, '0821391496612', '2021-07-09 21:18:21', 'BELUM TERKIRIM');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `roleID` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `roleID`, `nama`, `username`, `password`) VALUES
(1, 2, 'User', 'user', '6ad14ba9986e3615423dfca256d04e3f'),
(2, 2, 'User Satu', 'user1', 'b1157bdfaff95d0f8e650cc8efb6f3b0'),
(3, 1, 'Orang Baik', 'ob', 'f5e9e1fedd38738c9afb0a62fb1f5cd1'),
(4, 2, 'member oke', 'memberOK', 'a510166163833c79aa703646f59c04bb'),
(6, 2, 'ucup surucup', 'ucup', '1e17778d0d8217b035daffba02c06054'),
(7, 2, 'fulan bin fulan', 'fulan', '59ee8bd9e54c300ed35f1ead57cfdcf0'),
(8, 2, 'putri f', 'putri', '4093fed663717c843bea100d17fb67c8'),
(9, 2, 'Member Setia', 'member', 'a510166163833c79aa703646f59c04bb');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `harga`
--
ALTER TABLE `harga`
  ADD PRIMARY KEY (`id_harga`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_harga` (`id_harga`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `roleID` (`roleID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `harga`
--
ALTER TABLE `harga`
  MODIFY `id_harga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_harga`) REFERENCES `harga` (`id_harga`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
