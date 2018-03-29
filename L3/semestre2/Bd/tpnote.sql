-- Ce fichier doit s'appeller gpeTP_nom_prenom_PSA_2018
-- Changez le nom du fichier en remplaçant "gpeTP", "nom" et "prénom" par votre groupe de TP, votre nom et prénom.

-- @rendu_sujet_DPI2018
set serveroutput on
set pagesize 400
set linesize 400
spool rendu_sujet_PSA_2018.log

prompt 684J
prompt MAMADOU DIALLO
prompt COMPTE L3_23
prompt
prompt Suppression des tables car ce script sera reexecute plusieurs fois

DROP TABLE Clients cascade constraints;
DROP TABLE Velos cascade constraints;
DROP TABLE LocationsEnCours cascade constraints;
DROP TABLE LocationsPassees cascade constraints;

prompt Creation des tables
prompt ===================

prompt Creation table VELOS
CREATE TABLE Velos(
velo_id number PRIMARY KEY,
marque varchar2(20),
num_serie varchar2(20) UNIQUE,
date_achat date,
commentaires varchar2(50)
);

prompt Creation table CLIENTS
CREATE TABLE Clients(
client_id number PRIMARY KEY,
nom varchar2(20),
prenom varchar2(20),
date_premiere_location date,
date_derniere_location date
);


prompt Creation table LOCATIONSENCOURS
CREATE TABLE LocationsEnCours(
client_id number REFERENCES Clients,
velo_id number REFERENCES Velos,
date_location date,
CONSTRAINT PKLocations PRIMARY KEY (client_id,velo_id,date_location)
);

prompt Creation table LOCATIONSPASSEES
CREATE TABLE LocationsPassees(
client_id number,
velo_id number,
date_location date,
date_rendu date,
duree number,
CONSTRAINT PKHistorique PRIMARY KEY (client_id,velo_id,date_location)
);


prompt Population des tables
prompt =====================
INSERT INTO Velos values (1,'laBonneMarque', 'numeserie1',TO_DATE('20-03-13','DD-MM-YY'),NULL);
INSERT INTO Velos values (2,'laMeilleure', 'numeserie2',TO_DATE('20-01-12','DD-MM-YY'),NULL);
INSERT INTO Velos values (3,'laBonneMarque', 'numeserie3',TO_DATE('01-01-11','DD-MM-YY'),NULL);

INSERT INTO Clients values (10,'Toto', 'Titi',TO_DATE('13-08-66','DD-MM-YY'),TO_DATE('13-08-13','DD-MM-YY'));
INSERT INTO Clients values (20,'Lolo', 'Lili',TO_DATE('20-08-66','DD-MM-YY'),TO_DATE('20-08-13','DD-MM-YY'));

INSERT INTO LocationsEnCours values (10, 1, TO_DATE('27-03-2018 15:00','DD-MM-YYYY HH24:MI'));
INSERT INTO LocationsEnCours values (20, 2, TO_DATE('27-03-2018 17:00','DD-MM-YYYY HH24:MI'));

INSERT INTO LocationsPassees values (10, 1, TO_DATE('20-03-2018 15:00','DD-MM-YYYY HH24:MI'),TO_DATE('20-03-2018 16:30','DD-MM-YYYY HH24:MI'),NULL);
INSERT INTO LocationsPassees values (20, 2, TO_DATE('26-03-2018 17:00','DD-MM-YYYY HH24:MI'),TO_DATE('26-03-2018 17:30','DD-MM-YYYY HH24:MI'),NULL);


prompt *************************************************************
prompt ******************** QUESTION 1 *****************************
prompt *************************************************************

--Question 1 : Requête
--Afficher les vélos, leur date d’achat et le nombre de fois qu’ils ont été loués (dans le passé). Afficher aussi les vélos qui n’ont pas été loués. Ordonner par ordre décroissant selon le nombre de locations des vélos.





prompt *************************************************************
prompt ******************** QUESTION 2 *****************************
prompt *************************************************************
-- Cette question a deux parties :
-- Partie 1. Ecrire une fonction nommé CalculDuree qui reçoit en entrée 2 dates : la date de location d'un vélo et la date de rendu du vélo. Elle doit retourner la durée de la location en nombre de minutes.







-- Partie 2. Ecrire un bloc PL/SQL anonyme qui calcule la durée de la location pour chaque tuple de la table LOCATIONSPASSEES et fait les mises à jour correspondantes.








prompt *************************************************************
prompt ******************** QUESTION 3 *****************************
prompt *************************************************************

--Faire un trigger qui permet de réaliser le rendu d'un vélo. Le rendu d'un vélo qui n'est pas en location ne doit pas être fait, dans ce cas, une exception doit être levée.
--Lorsqu'un vélo est rendu :
-- a) un tuple doit être ajouté à la table LOCATIONSPASSEES, l'attribut duree doit être calculé en appelant la fonction calculDuree,
-- b) le tuple correspondant doit être supprimé de la table LOCATIONENCOURS, et
-- c) l'attribut date_derniere_location du tuple de la table CLIENTS correspondant doit être mise à jour avec l'attribut date_location.












-- Test d'insertion
--DOIT PAS FAIRE L'INSERTION ET LEVER UNE EXCEPTION
INSERT INTO LocationsPassees values (30, 2, TO_DATE('26-03-2018 17:00','DD-MM-YYYY HH24:MI'),TO_DATE('26-03-2018 17:30','DD-MM-YYYY HH24:MI'),NULL);

-- DOIT FAIRE L'INSERTION
INSERT INTO LocationsPassees values (20, 2, TO_DATE('27-03-2018 17:00','DD-MM-YYYY HH24:MI'),TO_DATE('27-03-2018 17:30','DD-MM-YYYY HH24:MI'),NULL);


prompt *************************************************************
prompt ******************** QUESTION 4 *****************************
prompt *************************************************************
--requete qui doit être optimisée
select EXTRACT(month FROM date_location),count(*)
from locationspassees
GROUP BY EXTRACT(month FROM date_location);







prompt *************************************************************
prompt ******************** QUESTION 5 *****************************
prompt *************************************************************

--Considerez le mode d’isolation par défaut d’Oracle.
--L'utilisateur David exécute un UPDATE sur un certain nombre de lignes d'une table mais n'a pas encore validé les changements (pas de COMMIT). L'utilisatrice Isabelle fait un SELECT sur les lignes qui sont en cours de mise à jour par David.
-- Quelle(s) assertion(s) est/sont correcte(s) ?
prompt A) Isabelle ne pourra pas faire un SELECT sur les lignes en cours de modification car elles sont verrouillees.
-- Supprimer la mauvaise réponse
prompt VRAI
prompt FAUX

prompt B) Isabelle pourra voir les nouvelles valeurs mais seulement si elle se connecte avec le meme compte que David.
-- Supprimer la mauvaise réponse
prompt VRAI
prompt FAUX

prompt C)Isabelle verra les anciennes versions des lignes.
-- Supprimer la mauvaise réponse
prompt VRAI
prompt FAUX

commit;
spool off
/
