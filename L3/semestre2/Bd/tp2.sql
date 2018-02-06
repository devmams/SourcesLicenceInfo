-- TASSI KEVIN et Mamadou DIALLO

DROP TABLE Achats;
DROP TABLE Avis;
DROP TABLE Clients;
DROP TABLE Livres;
DROP SEQUENCE maSequence;

-- création de la table Clients
CREATE TABLE Clients(
  idcl number PRIMARY KEY,
  nom varchar2(20),
  pren varchar2(15),
  adr varchar2(30),
  tel varchar2(12)
);

-- création de la table Livres
CREATE TABLE Livres(
  refl varchar2(10) PRIMARY KEY,
  titre varchar2(20),
  auteur varchar2(20),
  genre varchar2(15)
);

-- création de la table Achats
CREATE TABLE Achats(
  idcl number REFERENCES Clients(idcl) ON DELETE CASCADE,
  refl varchar2(10) REFERENCES Livres(refl) ON DELETE CASCADE,
  dateachat date PRIMARY KEY,
  constraint verif_date CHECK(dateachat >= '01/JAN/2008'
    AND dateachat <= '31/DEC/2013')
);


-- création de la table Avis
CREATE TABLE Avis(
  idcl number REFERENCES Clients(idcl) ON DELETE CASCADE,
  refl varchar2(10) REFERENCES Livres(refl) ON DELETE CASCADE,
  note number(4,2),
  commentaire varchar2(50),
  constraint verif_note CHECK(note >= 1 AND note <= 20)
);

CREATE SEQUENCE maSequence START WITH 1 INCREMENT BY 1 ;

-- Insertion des différents tuples dans la table Clients */
INSERT INTO Clients VALUES (maSequence.nextval, 'Rebecca', 'Armand', 'Saint-Didier' ,'06..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Aimee', 'Hebert', 'Marigny-le-Chetel' ,'07..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Marielle', 'Ribeiro', 'Mailleres' ,'08..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Hilaire', 'Savary','Conie-Molitard' ,'09..');

/* Insertion des différents tuples dans la table Livres */
INSERT INTO Livres VALUES ('ref1', 'baseSeDonnees', 'apo', 'information');
INSERT INTO Livres VALUES ('ref2', 'Reseau', 'zadi' ,'telecom');
INSERT INTO Livres VALUES ('ref3', 'Algo', 'irie' ,'programmation');
INSERT INTO Livres VALUES ('ref4', 'infoFonda','rene' ,'math');




/* Insertion des différents tuples dans la table Achats */
INSERT INTO Achats VALUES (2, 'ref3', '02/jan/2012');
INSERT INTO Achats VALUES (2, 'ref2', '05/dec/2011');
INSERT INTO Achats VALUES (3, 'ref1', '02/jul/2010');
INSERT INTO Achats VALUES (3, 'ref1', '06/mar/2009');
INSERT INTO Achats VALUES (3, 'ref1', '16/jan/2009');
INSERT INTO Achats VALUES (3, 'ref3', '09/JUN/2009');
INSERT INTO Achats VALUES (2, 'ref3', '23/mar/2009');


/* Insertion des différents tuples dans la table Avis */
INSERT INTO Avis VALUES (2, 'ref1', 18, 'bon livre');
INSERT INTO Avis VALUES (2, 'ref1', 15, 'bon livre');
INSERT INTO Avis VALUES (2, 'ref4', 10,'moyen livre');
INSERT INTO Avis VALUES (4, 'ref4', 1,'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref2', 2, 'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref3', 12,'assez bon livre');
INSERT INTO Avis VALUES (3, 'ref3', 13,NULL);
INSERT INTO Avis VALUES (2, 'ref1', 17,NULL);
-- -- Q2

-- SELECT 'DELETE FROM ' || table_name || ';'
-- FROM USER_TABLES ;
--
-- -- -- Q3
-- SET ECHO OFF
-- SPOOL effacer.sql
-- SET ECHO OFF
-- SET FEEDBACK OFF
-- SET HEADING OFF
-- SET PAGESIZE 0
-- SELECT 'DELETE FROM ' || table_name || ';'
-- FROM USER_TABLES ;
-- SPOOL OFF
-- SET ECHO ON

-- Q4
ALTER TABLE Achats
  ADD prix number;

-- SET ECHO OFF
-- SPOOL alter.sql
-- SET ECHO OFF
-- SET FEEDBACK OFF
-- SET HEADING OFF
-- SET PAGESIZE 0
-- SELECT 'ALTER TABLE FROM ' || table_name || ';'
-- FROM USER_TABLES where = Clients;
-- SPOOL OFF
-- SET ECHO ON

UPDATE Achats
SET prix = '100'
WHERE idcl = '2'

SELECT * FROM Achats;
-- 
-- SELECT idcl,dateachat, 50 as prix , AVG(prix)
-- FROM Clients NATURAL JOIN Livres NATURAL JOIN Achats
-- GROUP BY idcl,dateachat
-- ORDER BY idcl,dateachat;
