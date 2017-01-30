%------------------- HOMME ------------------------%
homme(jean).
homme(michel).
homme(popeye).
homme(gaston).
homme(david).
homme(charly).
homme(mimosa).
%--------------------- FEMME ----------------------%
femme(marie).
femme(gertrude).
femme(laure).
femme(olive).
femme(virginie).
femme(julie).
femme(sidonie).
%---------------------- PERE ----------------------%
pere(jean,popeye).
pere(jean,laure).
pere(jean,gaston).
pere(popeye,mimosa).
pere(popeye,sidonie).
pere(michel,charly).
pere(michel,julie).
pere(gaston,david).
pere(gaston,virginie).
%---------------------- MERE ----------------------%
mere(marie,gaston).
mere(marie,laure).
mere(marie,popeye).
mere(gertrude,david).
mere(gertrude,virginie).
mere(laure,charly).
mere(laure,julie).
mere(olive,mimosa).
mere(olive,sidonie).
%--------------------- EPOUX ---------------------%
epoux(jean,marie).
epoux(gaston,gertrude).
epoux(michel,laure).
epoux(popeye;olive).
%------------------- EPOUSE ----------------------%
epouse(Y,X) :- epoux(X,Y).
%------------------- PARENT ----------------------%
parent(X,Y) :- pere(X,Y) ; mere(X,Y).
%------------------- ENFANT ----------------------%
enfant(X,Y) :- parent(Y,X).
%------------------- FILLE -----------------------%
fille(X,Y) :- femme(X) , parent(Y,X).
%------------------- FILS ------------------------%
fils(X,Y) :- homme(X) , parent(Y,X).
%------------------- FRERE -----------------------%
frere(X,Y) :- homme(X) , parent(Z,X) , parent(Z,Y).
%------------------- SOEUR -----------------------%
soeur(X,Y) :- frere(Y,X).
%---------------- GRAND_PARENT -------------------%
grand_parent(X,Y) :- parent(Z,Y),parent(X,Z).
%------------------- ONCLE -----------------------%
%------------------- TANTE -----------------------%
%------------------- ANCETRE ---------------------%
