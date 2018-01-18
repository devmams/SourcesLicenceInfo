DROP TABLE Achats;
DROP TABLE Avis;
DROP TABLE Clients;
DROP TABLE Livres;


CREATE TABLE Clients(
  idcl number PRIMARY KEY,
  nom varchar2(20),
  pren varchar2(15),
  adr varchar2(30),
  tel varchar2(12)
);

CREATE TABLE Livres(
  refl varchar2(10) PRIMARY KEY,
  titre varchar2(20),
  auteur varchar2(20),
  genre varchar2(15)
);

CREATE TABLE Achats(
  idcl number REFERENCES Clients(idcl),
  refl varchar2(10) REFERENCES Livres(refl),
  dateachat date PRIMARY KEY
);

CREATE TABLE Avis(
  idcl number REFERENCES Clients(idcl),
  refl varchar2(10) REFERENCES Livres(refl),
  note number(4,2),
  commentaire varchar2(50)
);

INSERT INTO Clients VALUES (1, 'Rebecca', 'Armand', 'Saint-Didier' ,'06..');
INSERT INTO Clients VALUES (2, 'Aimee', 'Hebert', 'Marigny-le-Chetel' ,'07..');
INSERT INTO Clients VALUES (3, 'Marielle', 'Ribeiro', 'Mailleres' ,'08..');
INSERT INTO Clients VALUES (4, 'Hilaire', 'Savary','Conie-Molitard' ,'09..');

----------------------------------------------

INSERT INTO Livres VALUES ('ref1', 'baseSeDonnees', 'apo', 'information');
INSERT INTO Livres VALUES ('ref2', 'Reseau', 'zadi' ,'telecom');
INSERT INTO Livres VALUES ('ref3', 'Algo', 'irie' ,'programmation');
INSERT INTO Livres VALUES ('ref4', 'infoFonda','rene' ,'math');

-----------------------------------------------

INSERT INTO Achats VALUES (1, 'ref3', '02/jan/2008');
INSERT INTO Achats VALUES (2, 'ref4', '05/dec/2011');
INSERT INTO Achats VALUES (3, 'ref1', '02/jul/2010');
INSERT INTO Achats VALUES (3, 'ref1', '06/mar/2009');
INSERT INTO Achats VALUES (3, 'ref1', '16/jan/2009');
INSERT INTO Achats VALUES (3, 'ref3', '09/JUN/2009');
INSERT INTO Achats VALUES (2, 'ref3', '23/mar/2009');

-----------------------------------------

INSERT INTO Avis VALUES (1, 'ref1', 18, 'bon livre');
INSERT INTO Avis VALUES (1, 'ref1', 15, 'bon livre');
INSERT INTO Avis VALUES (2, 'ref4', 10,'moyen livre');
INSERT INTO Avis VALUES (4, 'ref4', 1,'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref2', 2, 'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref3', 13,'assez bon livre');
INSERT INTO Avis VALUES (3, 'ref3', 13,NULL);
INSERT INTO Avis VALUES (1, 'ref1', 13,NULL);

SELECT titre,auteur,genre,refl FROM Livres
WHERE refl IN (SELECT refl FROM Achats
                GROUP BY refl
                HAVING COUNT(refl) > 2);

SELECT titre,refl,AVG(note) FROM Livres NATURAL JOIN Avis
GROUP BY titre,refl
HAVING AVG(note) > 10;

SELECT idcl, nom, titre,note,commentaire FROM (Livres NATURAL JOIN Avis)
                                    NATURAL JOIN Clients
where commentaire IS NULL;
