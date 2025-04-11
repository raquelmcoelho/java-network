CREATE TABLE TODO (
    ID_TODO BIGINT not null,
    DESCRIPTION VARCHAR(200) not null,
    primary key (ID_TODO)
);

CREATE TABLE adherent (
  numero_adherent INT PRIMARY KEY,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  adresse VARCHAR(255),
  telephone VARCHAR(20),
  email VARCHAR(100),
  password VARCHAR(255)
);

CREATE TABLE tournoi (
 code_tournoi INT PRIMARY KEY,
 nom VARCHAR(100),
 date DATE,
 lieu VARCHAR(100)
);

CREATE TABLE Inscription (
 numero_adherent INT,
 code_tournoi INT,
 date_inscription DATE,
 PRIMARY KEY (numero_adherent, code_tournoi),
 FOREIGN KEY (numero_adherent) REFERENCES adherent(numero_adherent),
 FOREIGN KEY (code_tournoi) REFERENCES tournoi(code_tournoi)
);