DROP TABLE Agences;
DROP TABLE Vehicules;
DROP TABLE Clients;
DROP TABLE Responsables;
DROP TABLE Location;

CREATE TABLE Agences(
  id_ag number PRIMARY KEY,
  ville varchar2(30),
  qte_veh number
);

CREATE TABLE Clients(
  id_client number PRIMARY KEY,
  nom_client varchar2(20),
  pren_client varchar2(15),
  adr_client varchar2(30),
  tel_client varchar2(12)
);

CREATE TABLE Vehicules(
  matricule number PRIMARY KEY,
  marque varchar2(20),
  type varchar2(15)
);

CREATE TABLE Responsables(
  id_resp number PRIMARY KEY,
  nom_resp varchar2(20),
  pren_resp varchar2(15),
  tel_resp varchar2(12)
);

CREATE TABLE Location(
  date_loc date PRIMARY KEY,
  date_retour date PRIMARY KEY,
  cout number,
);
