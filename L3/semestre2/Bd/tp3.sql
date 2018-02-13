-- TASSI KEVIN et Mamadou DIALLO

DROP TABLE Achats;
DROP TABLE Avis;
DROP TABLE Clients;
DROP TABLE Livres;
DROP SEQUENCE maSequence;
-- DROP TABLE parcours;
-- DROP TABLE compo_parcours;
-- DROP TABLE inscrip_parcours;
-- DROP TABLE inscrip_evt;
CREATE SEQUENCE maSequence START WITH 0 INCREMENT BY 1 MINVALUE 0 ;

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
  genre varchar2(15),
  note_moy number (4,2)
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

-- CREATE TABLE parcours(
--   idp varchar2(10) PRIMARY KEY,
--   intitulep varchar2(15),
--   genre varchar2(15),
--   date_deb date
-- );
--
--   CREATE TABLE compo_parcours(
--     idp varchar2(10),
--     id_evt varchar2(10),
--     PRIMARY KEY(idp,id_evt)
-- );
--
--
-- CREATE TABLE inscrip_parcours(
--     idcl number,
--     idp varchar2(10),
--     PRIMARY KEY(idcl,idp)
--   );
--
-- CREATE TABLE inscrip_evt(
--   idcl number,
--   idp varchar2(10),
--   id_evt varchar2(10),
-- PRIMARY KEY (idcl,idp,id_evt)
--
-- );
/* Insertion des différents tuples dans la table Clients */
INSERT INTO Clients VALUES (maSequence.nextval, 'Rebecca', 'Armand', 'Saint-Didier' ,'06..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Aimee', 'Hebert', 'Marigny-le-Chetel' ,'07..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Marielle', 'Ribeiro', 'Mailleres' ,'08..');
INSERT INTO Clients VALUES (maSequence.nextval, 'Hilaire', 'Savary','Conie-Molitard' ,'09..');



-- /* Insertion des différents tuples dans la table Livres */
INSERT INTO Livres VALUES ('ref1', 'baseSeDonnees', 'apo', 'information',NULL);
INSERT INTO Livres VALUES ('ref2', 'Reseau', 'zadi' ,'telecom',NULL);
INSERT INTO Livres VALUES ('ref3', 'Algo', 'irie' ,'programmation',NULL);
INSERT INTO Livres VALUES ('ref4', 'infoFonda','rene' ,'math',NULL);

/* Insertion des différents tuples dans la table Achats */
INSERT INTO Achats VALUES (2, 'ref3', '02/jan/2012');
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
INSERT INTO Avis VALUES (2, 'ref1', 17,NULL);



-- (a) un bloc PL/SQL qui met à jour
-- l'attribut note_moy de la table livres à partir de la relation Avis,
-- pour une référence de livre passé en paramètre


-- SET SERVEROUTPUT ON;
-- DECLARE
--   moyenne number;
--   numRefl Avis.refl%type;
--
-- BEGIN
--   numRefl := '&paramRefl';
--   select AVG(note) into moyenne
--   FROM Avis
--   WHERE refl = numRefl;
--
--   UPDATE Livres
--   SET note_moy = moyenne
--   WHERE refl = numRefl;
--
--   DBMS_OUTPUT.PUT_line('moyenne : ' || moyenne);
--
-- END;
-- /

-- (b) Un bloc
-- PL/SQL qui met à jour l'attribut note_moy de la table
-- livres pour tous les livres.



-- DECLARE
--   cursor c1 is SELECT refl FROM Livres;
--   ligne c1% rowtype;
-- BEGIN
--   FOR ligne in c1 LOOP
--   UPDATE Livres
--     SET note_moy=(SELECT AVG(note) FROM Avis WHERE refl=Livres.refl)
--     WHERE refl=Livres.refl;
--   END LOOP;
-- END;
-- /
-- SHOW ERROR


-- (c) Reprendre la question précédente en
-- écrivant une procédure.

CREATE OR REPLACE PROCEDURE note_update
is
  cursor c1 is SELECT refl FROM Livres;
  ligne c1% rowtype;
BEGIN
  FOR ligne in c1 LOOP
  UPDATE Livres
    SET note_moy=(SELECT AVG(note) FROM Avis WHERE refl=Livres.refl)
    WHERE refl=Livres.refl;
  END LOOP;
END note_update;
/
SHOW ERROR

EXEC note_update;



-- Q2. un déclencheur (trigger) qui metà jours l'attribut note_moy de la table Livres
-- apres l'ajout ou la modification d'un avis, met à jour l'attribut

CREATE OR REPLACE TRIGGER decl_upd_note_moy
  BEFORE INSERT OR UPDATE ON Avis
  FOR EACH ROW
  DECLARE
   s number;
   n number;
   new_moy number(4,2);

  BEGIN
  SELECT sum(note),COUNT(note) into s,n
  FROM Avis  WHERE refl = :new.refl;
  new_moy := (s + :new.note)/(n+1);
  UPDATE LIVRES
    SET note_moy = new_moy
    WHERE refl= :new.refl;
  END;
  /
  SHOW ERROR

INSERT INTO Avis VALUES (1, 'ref1', 10, 'moy livre');



-- Cohérence Avis-Achat
-- Q1. un déclencheur qui assure qu'un client
-- qui insère un avis a bien acheté le livre

CREATE OR REPLACE TRIGGER decl_avis_clt_bef_ins
  BEFORE INSERT ON AVIS
  FOR EACH ROW
  DECLARE
  err_ins EXCEPTION;
  nb integer;
  BEGIN
   SELECT count(*) into nb FROM Achats
   WHERE idcl = :new.idcl and refl = :new.refl;
    IF nb = 0 THEN RAISE err_ins;
    END IF;
  EXCEPTION
      WHEN err_ins then
      RAISE_APPLICATION_ERROR(-20001, 'Ce client n a pas achete le livre qu il veut noter');
  END;
  /
  SHOW ERROR

INSERT INTO Avis VALUES (1, 'ref2', 10, 'moy livre');

-- Q2 Coherence Avis - Achat

DECLARE
  nb integer;
  id_client Avis.idcl%TYPE;
  refl_Livre Avis.refl%TYPE;
  new_comment Avis.commentaire%TYPE;
  err_droit EXCEPTION;

  BEGIN
    id_client := &id_client;
    refl_livre := '&refl_livre';
    new_comment := '&new_comment';
    SELECT COUNT(*) INTO nb FROM Avis
    WHERE refl = refl_livre AND idcl = id_client;
    IF(nb = 0) THEN RAISE err_droit;
    ELSE
      UPDATE Avis
      SET commentaire = new_comment
      WHERE idcl = id_client AND refl = refl_livre;
    END IF;
    EXCEPTION
      WHEN err_droit THEN
      DBMS_OUTPUT.PUT_LINE('Ce client n a pas d avis sur ce livre');
    END err_droit;
    /
    SHOW ERROR

select * From Avis;
















-- fh;o,;okh
