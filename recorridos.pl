%inorden(arbol(t,arbol(i,v,c),arbol(r,o,''))).

arboln(nombre, arbol(t,arbol(i,v,c),arbol(r,o,''))).
arboln(ejemplo,rbol(r,i,d)).
recorrer(NA, ino) :- arboln(NA,A), inorden(A).
recorrer(NA, pre) :- arboln(NA,A), preorden(A).
recorrer(NA, post) :- arboln(NA,A), postorden(A).

inorden(arbol(R,I,D)) :- 
inorden(I),
write(R),
inorden(D), !.
inorden(H) :- write(H).

preorden(arbol(R,I,D)) :- 
write(R),
preorden(I),
preorden(D), !.
preorden(H) :- write(H).

postorden(arbol(R,I,D)) :- 
postorden(I),
postorden(D),
write(R), !.
postorden(H) :- write(H).

%arboll(nom,[raiz,[hijo_izq],[hijo_der]]).
arboll(nom,[t,[i,v,c],[r,o,'']]).

%grafos
g1(a,b).
g1(a,d).
g1(a,i).
g1(b,c).
g1(b,a).
g1(b,e).
g1(c,b).
g1(d,a).
g1(d,e).
g1(d,i).
g1(e,b).
g1(e,d).
g1(e,f).
g1(f,e).
g1(f,m).
g1(i,a).
g1(i,d).
g1(m,f).

imp_salto(N1,N2) :-
    write(N1), write('->'), write(N2), nl.
camino(O,D) :- g1(O,D), imp_salto(O,D), !.
camino(O,D) :- g1(O,Dt), imp_salto(O,Dt), camino(Dt,D).

%grafos con listas
grafo(australia,[
        aus_occ-terr_nor,
        aus_occ-aus_sur,
        terr_nor-aus_sur,
        terr_nor-queen,
        aus_sur-queen,
        aus_sur-vic,
        aus_sur-nueva_gs,
        queen-nueva_gs,
        nueva_gs-vic,
        tas-tas]).
grafo(gnn,
      [a-i,a-b,a-d,b-c,b-e,d-e,d-i,e-f,f-m]).

%vecinos
vecinos(N1,N2,G) :-
grafo(G,La),
(member(N1-N2,La);member(N2-N1,La)).

vecinos(N,G).
%bagof devuelve una lista con los valores consultados
%setof devuelve una lista con los valores consultados y ordena y elimina repetidos

nodosb(G,Ln) :- bagof(N1,N2^vecinos(N1,N2,G),Ln).
nodoss(G,Ln) :- setof(N1,N2^vecinos(N1,N2,G),Ln).

listar_n(G) :- grafo(G,L), nodos2(L,[]).
nodos2([],Lr) :- sort(Lr,Lf), write(Lf).
nodos2([N1-N2|T],Lr) :-
    L1 = [N1|Lr],
    L2 = [N2|L1],
    nodos2(T,L2).

recorrido(G,O,D,Cr):-
    auxiliar(G,O,[D],Cr).

auxiliar(_,O,[O|Cl], [O,Cl]).
auxiliar(G,Ox,[Dx|Cx],Cr) :-
    vecinos(Ot,Dx,G),
    not(member(Ot,[Dx|Cx])),
    auxiliar(G,Ox,[Ot,Dx|Cx],Cr).