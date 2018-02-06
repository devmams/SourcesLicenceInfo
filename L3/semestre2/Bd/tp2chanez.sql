/*AMRI Chanez  HUMBERT--ROPERS Marie*/
/* Groupe 684J */

/*TP2 SQL*/

--Suppression des tables 
DROP TABLE Clients CASCADE CONSTRAINTS;
DROP TABLE Livres CASCADE CONSTRAINTS;
DROP TABLE Achats CASCADE CONSTRAINTS;
DROP TABLE Avis CASCADE CONSTRAINTS;
DROP SEQUENCE maSeq;

--Création des tables 
CREATE TABLE Clients (
idcl number PRIMARY KEY,
nom VARCHAR2(20),
pren VARCHAR2(15),
adr VARCHAR2(30),
tel VARCHAR2(12)
);

CREATE TABLE Livres (
refl VARCHAR2(10) PRIMARY KEY,
titre VARCHAR2(20),
auteur VARCHAR2(15),
genre VARCHAR2(30)
);


CREATE TABLE Achats (
idcl number REFERENCES Clients(idcl),
refl VARCHAR2(10) REFERENCES Livres(refl),
dateachat date,
CONSTRAINT CK_date CHECK (dateachat BETWEEN to_date('01-JAN-2014', 'DD-MON-YYYY') and  to_date('31-DEC-2014', 'DD-MON-YYYY')),
PRIMARY KEY (idcl, refl, dateachat)
);


CREATE TABLE Avis (
idcl number REFERENCES Clients(idcl),
refl VARCHAR2(10) REFERENCES Livres(refl),
commentaire VARCHAR2(50),
note number(4,2) CHECK (note >= 1 and note <= 20),
PRIMARY KEY (idcl, refl)
);



--Q1 Créer un séquence pour idcl

CREATE SEQUENCE maSeq START WITH 0 INCREMENT BY 1 MINVALUE 0;
INSERT INTO Clients VALUES(maSeq.nextval, 'Weasley','Ron','Londres', '0627937807') ;
 

-- Insertion de tuples pour tester 

--Livres disponibles 
INSERT INTO Livres 
VALUES('L001', 'Harry Potter', 'J.K. Rowling', 'fantastique'
);

INSERT INTO Livres 
VALUES('L002', 'L aiguille Creuse', 'Maurice Leblanc', 'Policier'
);

INSERT INTO Livres 
VALUES('L003', 'Germinal', 'Emile Zola', 'litterature'
);

--Clients 
--Au lieu de mettre le numéro à la main, le numéro de client est créé par la séquence
INSERT INTO Clients 
Values(maSeq.nextval,'Lupin', 'Arsène', 'Paris', '3300000000'
);

INSERT INTO Clients 
Values(maSeq.nextval,'Sholmes', 'Herlock', 'Londres', '0000000000'
);

INSERT INTO Clients 
Values(maSeq.nextval,'Granger', 'Hermione', 'Londres', '0000000011'
);

-- Achats 
INSERT INTO Achats
Values(4,'L003','05-JAN-2014'
);

INSERT INTO Achats
Values(2,'L002','11-OCT-2014'
);

INSERT INTO Achats
Values(3,'L002','07-NOV-2014'
);

INSERT INTO Achats
Values(3,'L002','10-DEC-2014'
);

INSERT INTO Achats
Values(4,'L001','06-AUG-2014'
);


-- Avis des clients sur les livres achetés  
INSERT INTO Avis
Values(4,'L003','Très sombre mais bien écrit', '14' 
);

INSERT INTO Avis
Values(4,'L001','Magique!!', '19' 
);

INSERT INTO Avis
Values(2,'L002','Magnifique! ', '20' 
);

INSERT INTO Avis
Values(3,'L002', NULL , '1' 
);



--Q2 : test de la requête


--La requête suivante supprime les élèments de toutes les tables sans supprimer les tables elle-même. Les tables sont donc vide, un SELECT renvoie des colonnes vides.

-- SELECT 'DELETE FROM ' || table_name || ';' FROM USER_TABLES ; 


--Q3 : effacer.sql contient les commandes suivantes:

SET ECHO OFF
SPOOL effacer.sql
SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET PAGESIZE 0
SELECT 'DELETE FROM ' || table_name || ';' FROM USER_TABLES ; 
SPOOL OFF
SET ECHO ON

--en lançant @effacer dans le terminal, on observe ensuite dans ce fichier les trois commandes suivantes: 
--DELETE FROM CLIENTS;                                                            
--DELETE FROM LIVRES;                                                             
--DELETE FROM ACHATS;                                                             
--DELETE FROM AVIS;   

--le contenu des tables a bien été effacé.

--Q4 Nous avons utilisé l'exemple suivant pour faire apparaître la table client dans le fichier effacer:
SET ECHO OFF
SPOOL clients.sql
SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET PAGESIZE 0
SELECT * FROM Clients ; 
SPOOL OFF
SET ECHO ON

--Le fichier nous affiche bien la table de clients dans le fichier clients.sql , le contenu est le suivant:
--         1 Weasley              Ron             Londres                         
--0627937807                                                                      
                                                                                
--         2 Lupin                Ars??ne         Paris                           
--3300000000                                                                      
                                                                                
--        3 Sholmes              Herlock         Londres                         
--0000000000                                                                      
                                                                                
--         4 Granger              Hermione        Londres                         
--0000000011                                                                      
       

--Q5 ajout de l'attribut prix dans la table Achats
ALTER TABLE Livres ADD prix INTEGER;

UPDATE Livres SET prix=4 WHERE refl='L001';
UPDATE Livres SET prix=9 WHERE refl='L002';
UPDATE Livres SET prix=5 WHERE refl='L003';

--vérification de l'ajout des prix dans la table Livres
SELECT refl,prix 
FROM Livres;

--Q6 : Requête SQL et code PL\SQL

SET ECHO OFF
SPOOL 2013-01-28-achats.lst  
SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET PAGESIZE 100

SET SERVEROUTPUT ON 
-- nous utilisons deux curseurs: le premier parcourt la table clients d'idcl en idcl. 
--Le deuxième permet de retrouver la date d'achat, le genre et le prix des livres achetés par un unique client (selon l'idcl) et ordonné par date.
--Les boucles permettent de récupérer ligne par ligne les données en utilisant un curseur.
DECLARE
	CURSOR c2 is SELECT DISTINCT idcl FROM Achats ORDER BY idcl ASC;
	L c2%rowtype;
	var1 INTEGER;
	var2 INTEGER; 

BEGIN
	DBMS_OUTPUT.Put_line('Achats des clients ');
	FOR L in c2 LOOP
		DBMS_OUTPUT.PUT_LINE('Idcl	' || 'Date achat	' || 'Genre ' || '		Prix 		' );
		FOR cursor1 IN (SELECT idcl, dateachat, genre, prix FROM Achats INNER JOIN LIVRES ON Achats.refl = Livres.refl WHERE idcl = L.idcl ORDER BY dateachat ASC ) 
			LOOP
				--affichage du contenu de la ligne actuelle
            	DBMS_OUTPUT.PUT_LINE( cursor1.idcl ||'		'|| cursor1.dateachat ||'	'||cursor1.genre ||'	'|| cursor1.prix);
        END LOOP;
		SELECT AVG(prix) INTO var1 FROM Achats INNER JOIN LIVRES ON Achats.refl = Livres.refl WHERE idcl = L.idcl;
		SELECT sum(prix) INTO var2 FROM Achats INNER JOIN LIVRES ON Achats.refl = Livres.refl WHERE idcl = L.idcl;
		DBMS_OUTPUT.PUT_LINE('avg : 		' || var2 ); --affichage moyenne
		DBMS_OUTPUT.PUT_LINE('sum : 		' || var1 ); --affichage somme
	END LOOP;

	
END;
/

SPOOL OFF
SET ECHO ON


--Voici le contenu affiché dans le fichier 2013-01-28-achats.lst:

--Achats des clients                                                              
--Idcl	Date achat	Genre 		Prix 		                                                 
--2		11-OCT-14	Policier	9                                                         
--avg : 		9                                                                       
--sum : 		9                                                                       
--Idcl	Date achat	Genre 		Prix 		                                                 
--3		07-NOV-14	Policier	9                                                         
--3		10-DEC-14	Policier	9                                                         
--avg : 		18                                                                      
--sum : 		9                                                                       
--Idcl	Date achat	Genre 		Prix 		                                                 
--4		05-JAN-14	litterature	5                                                      
--4		06-AUG-14	fantastique	4                                                      
--avg : 		9                                                                       
--sum : 		5                                                                       

























