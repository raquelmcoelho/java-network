-- Adminer 5.0.4 PostgreSQL 17.4 (Debian 17.4-1.pgdg120+2) dump

DROP TABLE IF EXISTS "Adherent";
CREATE TABLE "public"."Adherent" (
                                     "numeroAdherent" integer NOT NULL,
                                     "Adresse" character varying(128),
                                     "Email" character varying(64) NOT NULL,
                                     "Nom" character varying(64) NOT NULL,
                                     "Password" character varying(64) NOT NULL,
                                     "Prenom" character varying(64),
                                     "Salt" character varying(64) NOT NULL,
                                     "Telephone" character varying(18),
                                     CONSTRAINT "Adherent_pkey" PRIMARY KEY ("numeroAdherent")
) WITH (oids = false);

CREATE UNIQUE INDEX uk_hur1n53e8dmmud11fiv4c8b5j ON public."Adherent" USING btree ("Email");

DROP TABLE IF EXISTS "AdherentRole";
DROP SEQUENCE IF EXISTS "AdherentRole_id_seq";
CREATE SEQUENCE "AdherentRole_id_seq" INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."AdherentRole" (
                                         "id" integer DEFAULT nextval('"AdherentRole_id_seq"') NOT NULL,
                                         "role" character varying(255),
                                         "numeroAdherent" integer NOT NULL,
                                         CONSTRAINT "AdherentRole_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

DROP TABLE IF EXISTS "Tournoi";
CREATE TABLE "public"."Tournoi" (
                                    "codeTournoi" integer NOT NULL,
                                    "Date" date NOT NULL,
                                    "Lieu" character varying(128) NOT NULL,
                                    "Nom" character varying(128) NOT NULL,
                                    CONSTRAINT "Tournoi_pkey" PRIMARY KEY ("codeTournoi")
) WITH (oids = false);

DROP TABLE IF EXISTS "Inscription";
DROP SEQUENCE IF EXISTS inscription_id_seq;
CREATE SEQUENCE inscription_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;

CREATE TABLE "public"."Inscription" (
                                        "id" integer DEFAULT nextval('inscription_id_seq') NOT NULL,
                                        "codeTournoi" integer NOT NULL,
                                        "dateInscription" date NOT NULL,
                                        "numeroAdherent" integer NOT NULL,
                                        CONSTRAINT "inscription_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


ALTER TABLE ONLY "public"."AdherentRole" ADD CONSTRAINT "fk31232a5gnsfmx045bgk74jxot" FOREIGN KEY ("numeroAdherent") REFERENCES "Adherent"("numeroAdherent") NOT DEFERRABLE;

ALTER TABLE ONLY "public"."Inscription" ADD CONSTRAINT "fk8kdm0977me6fqskmpbj49lx9t" FOREIGN KEY ("numeroAdherent") REFERENCES "Adherent"("numeroAdherent") NOT DEFERRABLE;
ALTER TABLE ONLY "public"."Inscription" ADD CONSTRAINT "fkljxeq79s5pibjsmodn7q416ye" FOREIGN KEY ("codeTournoi") REFERENCES "Tournoi"("codeTournoi") NOT DEFERRABLE;


INSERT INTO "Adherent" ("numeroAdherent", "Adresse", "Email", "Nom", "Password", "Prenom", "Salt", "Telephone") VALUES
                                                                                                                    (1,	'salle serveur du club',	'admin@tennis.fr',	'Club',	'C4DB6B70309841819A6CDDD82BA27841A1D59CAECD103974A177233A190FD565',	'Admin',	'21C7D1DCAC3DC71787CA7C2CA7C6AA18605D1EB9875CA29B9A81B03EB1FD3960',	'0123456789'),
                                                                                                                    (2,	'au bar du club',	'manager@tennis.fr',	'Club',	'3B703734151C4D2BFC719D5579AC2CDACF4CF6056314DDC1C7DA8C578F1C34BF',	'Manager',	'23EB8E7D49B40F6ACB95DEE77B34E4D207918320E24F9A61896C5E6DB496C9D1',	'0111222333'),
                                                                                                                    (3,	'chez User1',	'user1@tennis.fr',	'Club',	'1F835F3C2D8B0D7B464C02FDF64424FBE4C96BD68273CEE476245AF08C21A2F4',	'User1',	'8A7499F0C1D259A65177055C2465F957940AD31955669188619681931F0D80F8',	'5551819');

INSERT INTO "AdherentRole" ("id", "role", "numeroAdherent") VALUES
                                                                (4,	'USER',	1),
                                                                (1,	'ADMIN',	1),
                                                                (2,	'MANAGER',	2),
                                                                (3,	'USER',	2),
                                                                (5,	'USER',	3);


INSERT INTO "Tournoi" ("codeTournoi", "Date", "Lieu", "Nom") VALUES
                                                                 (1,	'2025-06-24',	'Caen, France',	'Ensicaen International Masters 1000'),
                                                                 (2,	'2025-07-15',	'Londres',	'Grand Chelem of amateur Tennis');

-- 2025-03-13 16:01:42 UTC
