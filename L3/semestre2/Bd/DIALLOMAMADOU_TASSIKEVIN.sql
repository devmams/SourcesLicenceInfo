-- TASSI KEVIN et Mamadou DIALLO

-- DROP TABLE Achats;
-- DROP TABLE Avis;
-- DROP TABLE Clients;
-- DROP TABLE Livres;

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
  idcl number REFERENCES Clients(idcl),
  refl varchar2(10) REFERENCES Livres(refl),
  dateachat date PRIMARY KEY,
  constraint verif_date CHECK(dateachat >= '01/JAN/2008'
    AND dateachat <= '31/DEC/2013')
);

-- création de la table Avis
CREATE TABLE Avis(
  idcl number REFERENCES Clients(idcl),
  refl varchar2(10) REFERENCES Livres(refl),
  note number(4,2),
  commentaire varchar2(50),
  constraint verif_note CHECK(note >= 1 AND note <= 20)
);

/* Insertion des différents tuples dans la table Clients */
INSERT INTO Clients VALUES (1, 'Rebecca', 'Armand', 'Saint-Didier' ,'06..');
INSERT INTO Clients VALUES (2, 'Aimee', 'Hebert', 'Marigny-le-Chetel' ,'07..');
INSERT INTO Clients VALUES (3, 'Marielle', 'Ribeiro', 'Mailleres' ,'08..');
INSERT INTO Clients VALUES (4, 'Hilaire', 'Savary','Conie-Molitard' ,'09..');

/* Insertion des différents tuples dans la table Livres */
INSERT INTO Livres VALUES ('ref1', 'baseSeDonnees', 'apo', 'information');
INSERT INTO Livres VALUES ('ref2', 'Reseau', 'zadi' ,'telecom');
INSERT INTO Livres VALUES ('ref3', 'Algo', 'irie' ,'programmation');
INSERT INTO Livres VALUES ('ref4', 'infoFonda','rene' ,'math');


/* Insertion des différents tuples dans la table Achats */
INSERT INTO Achats VALUES (1, 'ref3', '02/jan/2012');
INSERT INTO Achats VALUES (2, 'ref2', '05/dec/2011');
INSERT INTO Achats VALUES (3, 'ref1', '02/jul/2010');
INSERT INTO Achats VALUES (3, 'ref1', '06/mar/2009');
INSERT INTO Achats VALUES (3, 'ref1', '16/jan/2009');
INSERT INTO Achats VALUES (3, 'ref3', '09/JUN/2009');
INSERT INTO Achats VALUES (2, 'ref3', '23/mar/2009');


/* Insertion des différents tuples dans la table Avis */
INSERT INTO Avis VALUES (1, 'ref1', 18, 'bon livre');
INSERT INTO Avis VALUES (1, 'ref1', 15, 'bon livre');
INSERT INTO Avis VALUES (2, 'ref4', 10,'moyen livre');
INSERT INTO Avis VALUES (4, 'ref4', 1,'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref2', 2, 'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref3', 12,'assez bon livre');
INSERT INTO Avis VALUES (3, 'ref3', 13,NULL);
INSERT INTO Avis VALUES (1, 'ref1', 17,NULL);


-- Question 2-1
-- on commence par faire une jointure naturelle entre la table
-- Livres et Achats ce qui nous donne une table avec toutes les occurences
-- et ensuite nous regroupons par titre,auteur et genre
-- et on selectionne les livres qui apparaissent au moins deux fois

SELECT titre,auteur,genre FROM Livres NATURAL JOIN Achats
GROUP BY titre,auteur,genre
HAVING COUNT(refl) > 1;

-- Question 2-2
-- on fait une jointure naturelle entre la table Livres et Avis
-- ensuite on regroupe les éléments par titre et reference
-- et on selectionne les livres qui ont une note moyenne de 16

SELECT titre,refl,AVG(note) FROM Livres NATURAL JOIN Avis
GROUP BY titre,refl
HAVING AVG(note) >= 16;

-- Question 2-3
-- nous faisons deux jointures naturelles entre Livres , Avis et Clients
-- et nous selectionnons les lignes qui ont comme commentaire "NULL"
SELECT nom ,pren ,titre ,note
FROM (Livres NATURAL JOIN Avis) NATURAL JOIN Clients
where commentaire IS NULL;
