-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-11-2012 a las 07:52:07
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `base_cine`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cine`
--

CREATE TABLE IF NOT EXISTS `cine` (
  `ID_CINE` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(40) NOT NULL,
  `X_COORD` varchar(20) NOT NULL,
  `Y_COORD` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_CINE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `cine`
--

INSERT INTO `cine` (`ID_CINE`, `NOMBRE`, `X_COORD`, `Y_COORD`) VALUES
(1, 'CineMark - City Mall', '-2.139959', '-79.909401 '),
(2, 'CineMark - Mall del Sol', '-2.154743', '-79.891076'),
(3, 'CineMark - Mall del Sur', '-2.22875', '-79.89894');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE IF NOT EXISTS `factura` (
  `ID_FACTURA` varchar(20) NOT NULL,
  `NOMBRE_PELICULA` varchar(30) NOT NULL,
  `HORARIO_FUNCION` varchar(10) NOT NULL,
  `SALA_FUNCION` varchar(10) NOT NULL,
  `ASIENTO_FUNCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`ID_FACTURA`, `NOMBRE_PELICULA`, `HORARIO_FUNCION`, `SALA_FUNCION`, `ASIENTO_FUNCION`) VALUES
('10F', 'ACTIVIDAD PARANORMAL', '20:00', '3', '30E'),
('50C', 'AVATAR', '19:30', '1', '20A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `CEDULA` varchar(10) NOT NULL,
  `NOMBRE` varchar(10) NOT NULL,
  `APELLIDO` varchar(10) NOT NULL,
  `HORA` varchar(10) NOT NULL,
  `CINE` varchar(50) NOT NULL,
  `HORARIO_FUNCION` varchar(10) NOT NULL,
  `SALA_FUNCION` varchar(10) NOT NULL,
  `ASIENTO_FUNCION` varchar(10) NOT NULL,
  `DETALLE` varchar(50) NOT NULL,
  `TOTAL` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_PEDIDO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`ID_PEDIDO`, `CEDULA`, `NOMBRE`, `APELLIDO`, `HORA`, `CINE`, `HORARIO_FUNCION`, `SALA_FUNCION`, `ASIENTO_FUNCION`, `DETALLE`, `TOTAL`) VALUES
(4, '123', 'Jose', 'Camacho', '20-11-2012', '', '20:00', '3', '30E', 'Canguil Mediano    ', '3'),
(5, '123', 'Jose', 'Camacho', '20-11-2012', 'CineMark - City Mall', '20:00', '3', '30E', 'Canguil Mediano    ', '3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `snack`
--

CREATE TABLE IF NOT EXISTS `snack` (
  `NOMBRE` varchar(20) NOT NULL,
  `TAMANO` varchar(20) NOT NULL,
  `MARCA` varchar(20) NOT NULL,
  `VALOR` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `CEDULA` varchar(10) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `APELLIDO` varchar(20) NOT NULL,
  `USER` varchar(15) NOT NULL,
  `SALDO` varchar(4) NOT NULL,
  PRIMARY KEY (`CEDULA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`CEDULA`, `NOMBRE`, `APELLIDO`, `USER`, `SALDO`) VALUES
('123', 'Jose', 'Camacho', 'jcm', '20'),
('1234', 'Juan', 'Caceres', 'jc', '30');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
