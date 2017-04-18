elem([A|_],A).
elem([B|T],A):- elem(T,A).


notelem([],_).
notelem([A|T],B) :- A \= B , notelem(T,B).


taill([],0).
taill([A|T],N) :- taill(T,N1), N1 is N-1.


sele([E|L],E,L).             % point de choix
sele([E1|L],E,[E1|L1]) :-
     sele(L,E,L1).
