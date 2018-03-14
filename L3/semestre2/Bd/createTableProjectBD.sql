DROP TABLE Locations;
DROP TABLE Vehicules;
DROP TABLE TypesVehicule;
DROP TABLE Agences;
DROP TABLE Client;
DROP ROLE R_alpha;
DROP ROLE R_kevin;
DROP ROLE R_oury;
DROP ROLE R_mamadou;


CREATE TABLE Agences(
  id_ag       varchar2(10) PRIMARY KEY,
  ville       varchar2(20),
  nom_resp    varchar2(10),
  pren_resp   varchar2(25),
  tel_resp    varchar2(15)
);

CREATE TABLE TypesVehicule(
  id_veh      varchar2(10) PRIMARY KEY,
  marque      varchar2(20),
  type_veh    varchar2(10),
  cout_jour   number(5)
);
CREATE TABLE Client(
  id_client     varchar2(10) PRIMARY KEY,
  nom_client    varchar2(10),
  prenom_client varchar2(25),
  adr_client   varchar2(30),
  tel_client   varchar2(15)
);

CREATE TABLE Vehicules(
  imm_veh     varchar2(10) PRIMARY KEY,
  id_veh      varchar2(10) REFERENCES TypesVehicule(id_veh) ON DELETE CASCADE,
  id_ag       varchar2(10) REFERENCES Agences(id_ag) ON DELETE CASCADE
);

CREATE TABLE Locations(
  date_loc      Date,
  date_retour   Date,
  imm_veh       varchar2(10) REFERENCES Vehicules(imm_veh) ON DELETE CASCADE,
  id_client     varchar2(10) REFERENCES Client(id_client) ON DELETE CASCADE,
  PRIMARY KEY(date_loc, date_retour, imm_veh)
);


-- # INSERTION DES TUPLES DANS LES RELATIONS

INSERT INTO Agences VALUES ('A01', 'Nantes', 'Tavis', 'Jose', '0712364');
INSERT INTO Agences VALUES ('A02', 'Paris', 'Ferdi', 'Cesar', '0695312');
INSERT INTO Agences VALUES ('A03', 'Angers', 'Wayne', 'Tom', '0607434');
INSERT INTO Agences VALUES ('A04', 'Lyon', 'Piro', 'Naldo', '0777726');
INSERT INTO Agences VALUES ('A05', 'Rennes', 'Galtier', 'Romeo', '0777725');

INSERT INTO Client VALUES ('clt055', 'Foyer', 'Zadi', 'Brest', '06070502');
INSERT INTO Client VALUES ('clt124', 'Balde', 'Thomas', 'Lille', '07530102');
INSERT INTO Client VALUES ('clt230' ,'Sylla', 'kevin', 'Nantes', '06478645');
INSERT INTO Client VALUES ('clt017', 'Maalox', 'mouji', 'Marseille', '02212325');
INSERT INTO Client VALUES ('clt111', 'Amdu', 'Chacul', 'Lyon', '06539603');
INSERT INTO Client VALUES ('clt369', 'Kiffi', 'Batoi', 'Paris', '06257493');
INSERT INTO Client VALUES ('clt458', 'Sow', 'Nicolas', 'Nantes', '09523684');
INSERT INTO Client VALUES ('clt499', 'Kodis', 'Malta', 'Angers', '06521493');
INSERT INTO Client VALUES ('clt363', 'Cenic', 'John', 'Rennes', '07526348');

INSERT INTO TypesVehicule VALUES ('veh001', 'Toyota', 'Utilitaire', '80');
INSERT INTO TypesVehicule VALUES ('veh002', 'Mercedes', 'Tourisme', '55');
INSERT INTO TypesVehicule VALUES ('veh003', 'Renault', 'Tourisme', '125');
INSERT INTO TypesVehicule VALUES ('veh004', 'Mercedes', 'Transport', '250');


 INSERT INTO Vehicules VALUES ('im005', 'veh001', 'A01');
 INSERT INTO Vehicules VALUES ('im045', 'veh001', 'A02');
 INSERT INTO Vehicules VALUES ('im451', 'veh003', 'A03');
 INSERT INTO Vehicules VALUES ('im122', 'veh001', 'A01');
 INSERT INTO Vehicules VALUES ('im634', 'veh002', 'A04');
 INSERT INTO Vehicules VALUES ('im412', 'veh002', 'A05');
 INSERT INTO Vehicules VALUES ('im199', 'veh001', 'A02');
 INSERT INTO Vehicules VALUES ('im269', 'veh003', 'A01');
 INSERT INTO Vehicules VALUES ('im500', 'veh004', 'A03');
 INSERT INTO Vehicules VALUES ('im916', 'veh003', 'A05');



INSERT INTO Locations VALUES ('10/Dec/2017', '15/Jan/2018', 'im005', 'clt055');
INSERT INTO Locations VALUES ('10/Dec/2017', '15/Feb/2018', 'im045', 'clt124');
INSERT INTO Locations VALUES ('03/Oct/2017', '13/Feb/2018', 'im451', 'clt124');
INSERT INTO Locations VALUES ('10/Feb/2017', '25/Feb/2017', 'im122', 'clt230');
INSERT INTO Locations VALUES ('14/Jun/2017', '12/Sep/2017', 'im634', 'clt017');
INSERT INTO Locations VALUES ('25/Dec/2017', '14/Jan/2018', 'im412', 'clt111');
INSERT INTO Locations VALUES ('01/Feb/2011', '13/May/2017', 'im199', 'clt369');
INSERT INTO Locations VALUES ('30/Jan/2017', '11/Mar/2017', 'im269', 'clt458');
INSERT INTO Locations VALUES ('14/Jan/2017', '16/Nov/2017', 'im500', 'clt499');
INSERT INTO Locations VALUES ('05/Feb/2018', '25/Feb/2018', 'im916', 'clt363');

-- CREATION DES ROLES

CREATE ROLE R_alpha;
CREATE ROLE R_kevin;
CREATE ROLE R_oury;
CREATE ROLE R_mamadou;

-- Attribution des privilèges aux rôles.

-- Droit d'insertion et de selection pour Alpha
GRANT SELECT, INSERT ON Locations TO R_alpha;
GRANT SELECT, INSERT ON Vehicules TO R_alpha;
GRANT SELECT, INSERT ON TypesVehicule TO R_alpha;
GRANT SELECT, INSERT ON Agences TO R_alpha;
GRANT SELECT, INSERT ON Client TO R_alpha;

-- Droit de suppression et de selection pour Kevin
GRANT SELECT, DELETE ON Locations TO R_kevin;
GRANT SELECT, DELETE ON Vehicules TO R_kevin;
GRANT SELECT, DELETE ON TypesVehicule TO R_kevin;
GRANT SELECT, DELETE ON Agences TO R_kevin;
GRANT SELECT, DELETE ON Client TO R_kevin;

--Droit de mise à jour pour Mamadou Oury
GRANT SELECT, UPDATE ON Locations TO R_oury;
GRANT SELECT, UPDATE ON Vehicules TO R_oury;
GRANT SELECT, UPDATE ON TypesVehicule TO R_oury;
GRANT SELECT, UPDATE ON Agences TO R_oury;
GRANT SELECT, UPDATE ON Client TO R_oury;

--Attribution des roles aux differents users

GRANT R_alpha TO L3_24;
GRANT R_kevin TO L3_35;
GRANT R_oury TO L3_22;



CREATE OR REPLACE TRIGGER trigger_location_invalid_date
  BEFORE INSERT OR UPDATE ON Locations
  FOR EACH ROW
  DECLARE
  invalid_date EXCEPTION;

  BEGIN
    IF :new.date_loc > :new.date_retour THEN
      RAISE invalid_date;
    END IF;
    EXCEPTION
      WHEN invalid_date THEN
        RAISE_APPLICATION_ERROR(-20001, 'Inserer une date de retour superieure a la date de Location');
  END;
/
SHOW ERROR

CREATE OR REPLACE TRIGGER trigger_vehicule_indisponible
  BEFORE INSERT OR UPDATE ON Locations
  FOR EACH ROW
  DECLARE
  veh_indisponible EXCEPTION;
  cursor c1 is SELECT imm_veh,date_retour FROM Locations;
  ligne c1% rowtype;
  BEGIN
    FOR ligne in c1 LOOP
      IF ligne.imm_veh = :new.imm_veh AND ligne.date_retour > :new.date_loc THEN
        RAISE veh_indisponible;
      END IF;
    END LOOP;
    EXCEPTION
      WHEN veh_indisponible THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ce Vehicule est en cours de location');
  END;
/
SHOW ERROR

CREATE OR REPLACE PROCEDURE dates_update
is
  nb integer;
  new_date_deb Locations.date_loc%TYPE;
  new_date_ret Locations.date_retour%TYPE;
  matricule_veh Locations.imm_veh%TYPE;
  id_clt Locations.id_client%TYPE;
  error_modif EXCEPTION;

  BEGIN
    id_clt := '&id_client';
    new_date_deb := '&date_loc';
    new_date_ret := '&date_retour';
    matricule_veh := '&imm_veh';

    SELECT COUNT(*) INTO nb FROM Locations
    WHERE imm_veh = matricule_veh AND id_client = id_clt;
    IF(nb = 0) THEN RAISE error_modif;
    ELSE
      UPDATE Locations
      SET date_loc = new_date_deb , date_retour = new_date_ret
      WHERE id_client = id_clt AND imm_veh = matricule_veh;
    END IF;
    EXCEPTION
      WHEN error_modif THEN
        DBMS_OUTPUT.PUT_LINE('Ce client n a effectue aucune Locations');
  END dates_update;
/
SHOW ERROR
EXEC dates_update;
DATEDIFF ('2000-01-01','2000-01-02') AS DateDiff;


CREATE OR REPLACE VIEW historique_clients AS
SELECT id_client,nom_client,prenom_client,imm_veh,date_loc,date_retour
FROM Client NATURAL JOIN Locations
ORDER BY id_client;


CREATE OR REPLACE VIEW Facture_location AS
SELECT nom_client,prenom_client, id_veh, cout_jour, TO_DATE(date_retour, 'DD/MM/YYYY') - TO_DATE(date_loc, 'DD/MM/YYYY') as Nb_Jour,
      (TO_DATE(date_retour, 'DD/MM/YYYY') - TO_DATE(date_loc, 'DD/MM/YYYY'))* cout_jour as Montant_total
FROM TypesVehicule NATURAL JOIN Vehicules NATURAL JOIN Locations NATURAL JOIN Client;


-- select * from Locations;
-- select * from historique_clients where id_client = 'clt124';
select * from Facture_location ;

COMMIT;
-- createTableProjectBD
