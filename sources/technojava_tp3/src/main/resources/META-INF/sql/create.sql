CREATE TABLE Adherent (
    numeroAdherent INT PRIMARY KEY,
    Nom VARCHAR(50),
    Prenom VARCHAR(50),
    Adresse VARCHAR(255),
    Telephone VARCHAR(20),
    Email VARCHAR(100),
    Password VARCHAR(255)
);

CREATE TABLE Tournoi (
    codeTournoi INT PRIMARY KEY,
    Nom VARCHAR(100),
    Date DATE,
    Lieu VARCHAR(100)
);

CREATE TABLE Inscription (
    numeroAdherent INT,
    codeTournoi INT,
    DateInscription DATE,
    PRIMARY KEY (numeroAdherent, codeTournoi),
    FOREIGN KEY (numeroAdherent) REFERENCES Adherent(numeroAdherent),
    FOREIGN KEY (codeTournoi) REFERENCES Tournoi(codeTournoi)
);