/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Structure de la table `author`
--

CREATE TABLE `author` (
  `idAuthor` int(11) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `author`
--

INSERT INTO `author` (`idAuthor`, `name`) VALUES
(1, 'John Smith'),
(2, 'Emily Johnson'),
(3, 'Michael Brown'),
(4, 'Jessica Davis'),
(5, 'William Garcia'),
(6, 'Olivia Martinez'),
(7, 'James Rodriguez'),
(8, 'Sophia Hernandez'),
(9, 'Benjamin Lopez'),
(10, 'Amelia Gonzalez'),
(11, 'Lucas Wilson'),
(12, 'Mia Anderson'),
(13, 'Alexander Thomas'),
(14, 'Isabella Taylor'),
(15, 'Ethan Moore'),
(16, 'Charlotte Jackson'),
(17, 'Daniel White'),
(18, 'Emma Lee'),
(19, 'Matthew Harris'),
(20, 'Ava Clark'),
(21, 'Henry Lewis'),
(22, 'Lily Young'),
(23, 'Samuel King'),
(24, 'Chloe Wright'),
(25, 'Joseph Scott'),
(26, 'Grace Hill'),
(27, 'David Green'),
(28, 'Hannah Adams'),
(29, 'Andrew Baker'),
(30, 'Ella Turner');
