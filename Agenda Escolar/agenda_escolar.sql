-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 25-Nov-2021 às 00:26
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `agenda_escolar`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE IF NOT EXISTS `avaliacao` (
  `id_avaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `formula` varchar(60) NOT NULL,
  `numeroComponentes` int(11) NOT NULL,
  PRIMARY KEY (`id_avaliacao`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `avaliacao`
--

INSERT INTO `avaliacao` (`id_avaliacao`, `formula`, `numeroComponentes`) VALUES
(1, 'M = (n1 * p1 + n2 * p2) / p1 + p2', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `componenteavaliacaoaluno`
--

DROP TABLE IF EXISTS `componenteavaliacaoaluno`;
CREATE TABLE IF NOT EXISTS `componenteavaliacaoaluno` (
  `id_avlAluno` int(11) NOT NULL AUTO_INCREMENT,
  `valor` int(11) NOT NULL,
  PRIMARY KEY (`id_avlAluno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `componentesavaliacao`
--

DROP TABLE IF EXISTS `componentesavaliacao`;
CREATE TABLE IF NOT EXISTS `componentesavaliacao` (
  `id_avaliacao` int(11) NOT NULL,
  `id_componente` int(11) NOT NULL,
  `componente` int(11) NOT NULL,
  `peso` int(11) NOT NULL,
  PRIMARY KEY (`id_componente`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `nro_materias` int(11) DEFAULT NULL,
  `dt_inicio` date DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_curso`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE IF NOT EXISTS `disciplina` (
  `id_disciplina` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `media` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `id_curso` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_disciplina`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_curso` int(11) DEFAULT NULL,
  `id_disciplina` int(11) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `senha` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nome`, `email`, `id_curso`, `id_disciplina`, `tipo`, `senha`) VALUES
(19, 'Aluno', NULL, NULL, NULL, 1, '123'),
(20, 'professor', NULL, NULL, NULL, 2, '123'),
(22, 'Coordenador', NULL, NULL, NULL, 3, '123'),
(23, 'Pedro', NULL, NULL, NULL, 1, '123');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
