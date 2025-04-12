-- Insertion des données
insert into TODO (ID_TODO, DESCRIPTION) values (1, 'Faire le TP avec sa tête et ses doigts.');
insert into TODO (ID_TODO, DESCRIPTION) values (2, 'Comprendre (ou presque) ce qui est fait.');
insert into TODO (ID_TODO, DESCRIPTION) values (3, 'Apprécier le résultat.');


INSERT INTO adherent VALUES
// password123 -> $2a$10$6QP1Gs5tbUT3KciBpwA9L.jQGYm0G/AUt688605DUpHg2oYSVq/se
(1, 'Dupont', 'Jean', '12 Rue de Paris, Lyon', '0612345678', 'jean.dupont@email.com', '$2a$10$6QP1Gs5tbUT3KciBpwA9L.jQGYm0G/AUt688605DUpHg2oYSVq/se'),
// pass456 -> $2a$10$rXBUwu3NMBMk6e4J7xp7peikH8JOQFPuZijC15G0mkzviBHMLgccu
(2, 'Martin', 'Claire', '45 Avenue Victor Hugo, Marseille', '0623456789', 'claire.martin@email.com', '$2a$10$rXBUwu3NMBMk6e4J7xp7peikH8JOQFPuZijC15G0mkzviBHMLgccu'),
// motdepasse789 -> $2a$10$HS2LTTcNwiSfQ71N3ddb2.EtOfWJu2ZAEN.z9Jb9lGSowpYz2RQGG
(3, 'Bernard', 'Luc', '89 Boulevard Haussmann, Paris', '0634567890', 'luc.bernard@email.com', '$2a$10$HS2LTTcNwiSfQ71N3ddb2.EtOfWJu2ZAEN.z9Jb9lGSowpYz2RQGG'),
// sophiepwd -> $2a$10$JqRWJXOldjci8BCVrpkYuuSf76NuWBjbscqmH9LaXyPV/37LKgnYS
(4, 'Petit', 'Sophie', '5 Rue de Bretagne, Rennes', '0645678901', 'sophie.petit@email.com', '$2a$10$JqRWJXOldjci8BCVrpkYuuSf76NuWBjbscqmH9LaXyPV/37LKgnYS'),
// pwdrobert -> $2a$10$qilN.r2AEyx57aP/0ZMzi.u.FHTKoVrDR/ALDeKde4msmJzwLvczy
(5, 'Robert', 'Paul', '28 Rue Lafayette, Toulouse', '0656789012', 'paul.robert@email.com', '$2a$10$qilN.r2AEyx57aP/0ZMzi.u.FHTKoVrDR/ALDeKde4msmJzwLvczy'),
// juliepass -> $2a$10$yATMyKLC7Ye5Fm.U4MEKkeMzjAbTvdQw0TtJEeIaQn9pkXnHZfmEG
(6, 'Richard', 'Julie', '77 Rue Nationale, Bordeaux', '0667890123', 'julie.richard@email.com', '$2a$10$yATMyKLC7Ye5Fm.U4MEKkeMzjAbTvdQw0TtJEeIaQn9pkXnHZfmEG'),
// hugosecret -> $2a$10$VxdsNhHR8F7eRVE.0gZbru6OslZs2XLD40TlUQu7aqY.0jqC.COBq
(7, 'Durand', 'Hugo', '33 Rue de Strasbourg, Nice', '0678901234', 'hugo.durand@email.com', '$2a$10$VxdsNhHR8F7eRVE.0gZbru6OslZs2XLD40TlUQu7aqY.0jqC.COBq'),
// emmapwd -> $2a$10$9W07N.K5hC/AbbG6i5RP2OnR7FQxwoVA89LCPLdPIVj18Sgpmy5J.
(8, 'Moreau', 'Emma', '14 Avenue de la République, Lille', '0689012345', 'emma.moreau@email.com', '$2a$10$9W07N.K5hC/AbbG6i5RP2OnR7FQxwoVA89LCPLdPIVj18Sgpmy5J.'),
// thomaspwd -> $2a$10$UngONDvOc26blI9dDQx9N.tcWu/JdP0igsPgM9VW4rY7gAdRaEqSq
(9, 'Simon', 'Thomas', '55 Rue Saint-Michel, Nantes', '0690123456', 'thomas.simon@email.com', '$2a$10$UngONDvOc26blI9dDQx9N.tcWu/JdP0igsPgM9VW4rY7gAdRaEqSq'),
// camillepass -> $2a$10$j2jAqnK0ZVxosvhiqeD.o./yrlTwP9Q06l3zfN5zygW0QxstU3N6S
(10, 'Laurent', 'Camille', '19 Rue du Faubourg, Montpellier', '0601234567', 'camille.laurent@email.com', '$2a$10$j2jAqnK0ZVxosvhiqeD.o./yrlTwP9Q06l3zfN5zygW0QxstU3N6S');


INSERT INTO tournoi VALUES
(101, 'Open de Paris', '2025-05-10', 'Paris'),
(102, 'Coupe du Sud', '2025-06-15', 'Marseille'),
(103, 'Tournoi de Printemps', '2025-04-20', 'Lyon'),
(104, 'Challenge Atlantique', '2025-07-01', 'Bordeaux'),
(105, 'Rencontre Nationale', '2025-09-10', 'Strasbourg'),
(106, 'Compétition Régionale', '2025-03-25', 'Rennes'),
(107, 'Tournoi des Alpes', '2025-08-05', 'Grenoble'),
(108, 'Coupe des Villes', '2025-10-12', 'Toulouse'),
(109, 'Open du Nord', '2025-11-20', 'Lille'),
(110, 'Championnat Estival', '2025-07-18', 'Nice');

INSERT INTO Inscription VALUES
(1, 101, '2025-04-01'),
(1, 104, '2025-04-15'),
(2, 103, '2025-03-30'),
(2, 102, '2025-04-10'),
(2, 105, '2025-05-20'),
(3, 106, '2025-03-01'),
(4, 101, '2025-04-02'),
(5, 110, '2025-06-30'),
(6, 108, '2025-07-05'),
(6, 109, '2025-08-01'),
(7, 102, '2025-05-01'),
(7, 103, '2025-04-01'),
(8, 107, '2025-06-01'),
(8, 108, '2025-06-15'),
(9, 105, '2025-07-20'),
(9, 109, '2025-10-01'),
(9, 110, '2025-07-01'),
(10, 101, '2025-03-25');

