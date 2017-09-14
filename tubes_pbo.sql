-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 19 Apr 2016 pada 12.05
-- Versi Server: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

create database tubes_pbo;
use tubes_pbo;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubes_pbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `energi`
--

CREATE TABLE IF NOT EXISTS `tenergi` (
  `username` varchar(50) PRIMARY KEY,
  `energi` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `energi`
--

INSERT INTO `tenergi` (`username`, `energi`) VALUES
('ranggi', 0),
('rahman', 0),
('ridha', 0);

