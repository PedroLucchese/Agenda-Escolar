-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 30, 2021 at 02:36 AM
-- Server version: 10.6.5-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agenda_escolar`
--

-- --------------------------------------------------------

--
-- Table structure for table `avaliacao`
--

CREATE TABLE `avaliacao` (
  `id` int(11) NOT NULL,
  `formula` varchar(60) NOT NULL,
  `numeroComponentes` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avaliacao`
--

INSERT INTO `avaliacao` (`id`, `formula`, `numeroComponentes`) VALUES
(0, 'M = (n1 * p1 + n2 * p2) / p1 + p2', 2);

-- --------------------------------------------------------

--
-- Table structure for table `componenteavaliacaoaluno`
--

CREATE TABLE `componenteavaliacaoaluno` (
  `id` int(11) NOT NULL,
  `id_avaliacao` int(11) NOT NULL,
  `valor` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `componentesavaliacao`
--

CREATE TABLE `componentesavaliacao` (
  `id` int(11) NOT NULL,
  `id_avaliacao` int(11) NOT NULL,
  `componente` varchar(11) DEFAULT NULL,
  `peso` int(11) DEFAULT NULL,
  `id_turma` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `componentesavaliacao`
--

INSERT INTO `componentesavaliacao` (`id`, `id_avaliacao`, `componente`, `peso`, `id_turma`) VALUES
(6, 0, 'n2', 10, 6),
(5, 0, 'n1', 10, 6);

-- --------------------------------------------------------

--
-- Table structure for table `curso`
--

CREATE TABLE `curso` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `nro_materias` int(11) DEFAULT NULL,
  `dt_inicio` date DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `disciplina`
--

CREATE TABLE `disciplina` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `codigo` varchar(30) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `id_professor` int(11) NOT NULL,
  `id_coordenador` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `disciplina`
--

INSERT INTO `disciplina` (`id`, `nome`, `codigo`, `id_curso`, `id_professor`, `id_coordenador`) VALUES
(1, 'Disciplina teste', 'ads123', 0, 20, 22),
(2, 'Disciplina teste', 'ads123', 0, 20, 22),
(3, 'Disciplina teste 3', 'ads12345', 0, 20, 22),
(4, 'Disciplina teste', 'ADS8001F', 0, 20, 22);

-- --------------------------------------------------------

--
-- Table structure for table `turma`
--

CREATE TABLE `turma` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `horario` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_disciplina` bigint(20) NOT NULL,
  `id_professor` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `turma`
--

INSERT INTO `turma` (`id`, `nome`, `horario`, `id_disciplina`, `id_professor`) VALUES
(6, 'Turma teste', '71-72', 3, 24);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `id_disciplina` int(11) DEFAULT NULL,
  `id_turma` bigint(20) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `senha` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `id_curso`, `id_disciplina`, `id_turma`, `tipo`, `senha`) VALUES
(19, 'Aluno', NULL, NULL, NULL, 6, 1, '123'),
(20, 'professor', NULL, NULL, NULL, 0, 2, '123'),
(22, 'Coordenador', NULL, NULL, NULL, 0, 3, '123'),
(23, 'Pedro', NULL, NULL, NULL, 6, 1, '123'),
(24, 'Professor 2', NULL, NULL, NULL, NULL, 2, '123'),
(25, 'Professor 3', NULL, NULL, NULL, NULL, 2, '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avaliacao`
--
ALTER TABLE `avaliacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `componenteavaliacaoaluno`
--
ALTER TABLE `componenteavaliacaoaluno`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `componentesavaliacao`
--
ALTER TABLE `componentesavaliacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `turma`
--
ALTER TABLE `turma`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `componentesavaliacao`
--
ALTER TABLE `componentesavaliacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `curso`
--
ALTER TABLE `curso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `disciplina`
--
ALTER TABLE `disciplina`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `turma`
--
ALTER TABLE `turma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
