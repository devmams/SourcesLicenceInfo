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

/*----------------------------------------------*/

INSERT INTO Livres VALUES ('ref1', 'baseSeDonnees', 'apo', 'information');
INSERT INTO Livres VALUES ('ref2', 'Reseau', 'Hebert', 'zadi' ,'telecom');
INSERT INTO Livres VALUES ('ref3', 'Algo', 'Ribeiro', 'irie' ,'programmation');
INSERT INTO Livres VALUES ('ref4', 'infoFonda', 'Savary','rene' ,'math');

/*-----------------------------------------------*/

INSERT INTO Achats VALUES (1, 'ref1', 'Armand', '02/jan/2030');
INSERT INTO Achats VALUES (2, 'ref4', 'Hebert', '02/dec/2050');
INSERT INTO Achats VALUES (3, 'ref1', 'Ribeiro', '02/jul/2070');
INSERT INTO Achats VALUES (3, 'ref3', 'Savary','02/mar/2077');

/*----------------------------------------------*/

INSERT INTO Avis VALUES (1, 'ref1', 'bon livre');
INSERT INTO Avis VALUES (2, 'ref4', 'Hebert','moyen livre');
INSERT INTO Avis VALUES (3, 'ref1', 'Ribeiro', 'mauvais livre');
INSERT INTO Avis VALUES (3, 'ref3', 'Savary','assez bon livre');

SELECT * FROM Clients;
