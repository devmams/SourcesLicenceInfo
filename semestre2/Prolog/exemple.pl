%------------------- HOMME ---------------------------%
homme(jean).
homme(michel).
homme(popeye).
homme(gaston).
homme(david).
homme(charly).
homme(mimosa).
%--------------------- FEMME ------------------------%
femme(marie).
femme(gertrude).
femme(laure).
femme(olive).
femme(virginie).
femme(julie).
femme(sidonie).
%----------------------------------------------%
pere(jean,popeye).
pere(jean,laure).
pere(jean,gaston).
pere(popeye,mimosa).
pere(popeye,sidonie).
pere(michel,charly).
pere(michel,julie).
pere(gaston,david).
pere(gaston,virginie).
%----------------------------------------------%
mere(marie,gaston).
mere(marie,laure).
mere(marie,popeye).
mere(gertrude,david).
mere(gertrude,virginie).
mere(laure,charly).
mere(laure,julie).
mere(olive,mimosa).
mere(olive,sidonie).
%----------------------------------------------%
epoux(jean,marie).
epoux(gaston,gertrude).
epoux(michel,laure).
epoux(popeye;olive).
%------------------- EPOUSE ---------------------------%
epouse(Y,X) :- epoux(X,Y).
%------------------- PARENT ---------------------------%
parent(X,Y) :- pere(X,Y) ; mere(X,Y).
%------------------- ENFANT ---------------------------%
enfant(X,Y) :- pere(_,X) ; mere(_,X).
%------------------- FILLE ---------------------------%
fille(X,Y) :- femme(X),parent(Y,X).
