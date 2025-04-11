CREATE TABLE TODO (
    ID_TODO BIGINT not null,
    DESCRIPTION VARCHAR(255) not null,
    primary key (ID_TODO)
);

CREATE TABLE adherent (
  numero_adherent INT PRIMARY KEY,
  nom VARCHAR(255),
  prenom VARCHAR(255),
  adresse VARCHAR(255),
  telephone VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255)
);

CREATE TABLE tournoi (
 code_tournoi INT PRIMARY KEY,
 nom VARCHAR(255),
 date DATE,
 lieu VARCHAR(255)
);

CREATE TABLE Inscription (
 numero_adherent INT,
 code_tournoi INT,
 date_inscription DATE,
 PRIMARY KEY (numero_adherent, code_tournoi),
 FOREIGN KEY (code_tournoi) REFERENCES tournoi(code_tournoi) ON DELETE CASCADE,
 FOREIGN KEY (numero_adherent) REFERENCES adherent(numero_adherent) ON DELETE CASCADE
);