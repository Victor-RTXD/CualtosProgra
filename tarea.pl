%inorden(arbol(t,arbol(i,v,c),arbol(r,o,arbol(u,arbol(e," ",d),arbol(o,arbol(r,a,d),arbol(m," ",arbol(i,arbol(a,arbol(c,a,i),arbol(" ",s,arbol(a,m,c))),arbol(s,a,"")))))))).
%preorden(arbol(t,arbol(i,v,c),arbol(r,o,arbol(u,arbol(e," ",d),arbol(o,arbol(r,a,d),arbol(m," ",arbol(i,arbol(a,arbol(c,a,i),arbol(" ",s,arbol(a,m,c))),arbol(s,a,"")))))))).
%postorden(arbol(t,arbol(i,v,c),arbol(r,o,arbol(u,arbol(e," ",d),arbol(o,arbol(r,a,d),arbol(m," ",arbol(i,arbol(a,arbol(c,a,i),arbol(" ",s,arbol(a,m,c))),arbol(s,a,"")))))))).

% Predicado inorden con contador
inorden_con_contador(arbol(R,I,D), Contador) :-
    inorden_con_contador(I, ContadorI),
    write(R),
    inorden_con_contador(D, ContadorD),
    Contador is ContadorI + ContadorD + 1.

inorden_con_contador(H, Contador) :-
    write(H),
    Contador is 1.

% Predicado preorden con contador
preorden_con_contador(arbol(R,I,D), Contador) :-
    write(R),
    preorden_con_contador(I, ContadorI),
    preorden_con_contador(D, ContadorD),
    Contador is ContadorI + ContadorD + 1.

preorden_con_contador(H, Contador) :-
    write(H),
    Contador is 1.

% Predicado postorden con contador
postorden_con_contador(arbol(R,I,D), Contador) :-
    postorden_con_contador(I, ContadorI),
    postorden_con_contador(D, ContadorD),
    write(R),
    Contador is ContadorI + ContadorD + 1.

postorden_con_contador(H, Contador) :-
    write(H),
    Contador is 1.


%inorden(arbol(t,arbol(8,"(",c),arbol(r,o,''))).

arbol(t,arbol(i,v,c),arbol(r,o,''))


% pre-inf
preinf(X, S) :- 
    atom(X), !, 
    atom_string(X, S).
preinf(X, S) :- 
    number(X), !, 
    number_string(X, S).

% Operaciones aritméticas
preinf(+(A, B), S) :- 
    preinf(A, SA), 
    preinf(B, SB),
    string_concat("(", SA, S1),
    string_concat(S1, " + ", S2),
    string_concat(S2, SB, S3),
    string_concat(S3, ")", S).

preinf(-(A, B), S) :- 
    preinf(A, SA), 
    preinf(B, SB),
    string_concat("(", SA, S1),
    string_concat(S1, " - ", S2),
    string_concat(S2, SB, S3),
    string_concat(S3, ")", S).

preinf(*(A, B), S) :- 
    preinf(A, SA), 
    preinf(B, SB),
    string_concat("(", SA, S1),
    string_concat(S1, " * ", S2),
    string_concat(S2, SB, S3),
    string_concat(S3, ")", S).

preinf(/(A, B), S) :- 
    preinf(A, SA), 
    preinf(B, SB),
    string_concat("(", SA, S1),
    string_concat(S1, " / ", S2),
    string_concat(S2, SB, S3),
    string_concat(S3, ")", S).

% pre-post
prepost(X, S) :-
    atom(X), !,
    atom_string(X, S).
prepost(X, S) :-
    number(X), !,
    number_string(X, S).

% Operaciones aritméticas en notación postfija
prepost(+(A, B), S) :-
    prepost(A, SA),
    prepost(B, SB),
    string_concat(SA, " ", S1),
    string_concat(S1, SB, S2),
    string_concat(S2, " +", S).

prepost(-(A, B), S) :-
    prepost(A, SA),
    prepost(B, SB),
    string_concat(SA, " ", S1),
    string_concat(S1, SB, S2),
    string_concat(S2, " -", S).

prepost(*(A, B), S) :-
    prepost(A, SA),
    prepost(B, SB),
    string_concat(SA, " ", S1),
    string_concat(S1, SB, S2),
    string_concat(S2, " *", S).

prepost(/(A, B), S) :-
    prepost(A, SA),
    prepost(B, SB),
    string_concat(SA, " ", S1),
    string_concat(S1, SB, S2),
    string_concat(S2, " /", S).

% Definición de operadores
:- op(500, xfy, +).
:- op(600, xfy, -).
:- op(700, xfy, *).
:- op(800, xfy, /).

% Reglas para convertir a notación postfija
postfix(X, X) :- atomic(X).
postfix(X+Y, P) :- postfix(X, PX), postfix(Y, PY), atomic_list_concat([PX, PY, '+'], ' ', P).
postfix(X-Y, P) :- postfix(X, PX), postfix(Y, PY), atomic_list_concat([PX, PY, '-'], ' ', P).
postfix(X*Y, P) :- postfix(X, PX), postfix(Y, PY), atomic_list_concat([PX, PY, '*'], ' ', P).
postfix(X/Y, P) :- postfix(X, PX), postfix(Y, PY), atomic_list_concat([PX, PY, '/'], ' ', P).
%postfix((3+4)*5, X).

% Reglas para convertir a notación prefija
prefix(X, X) :- atomic(X).
prefix(X+Y, P) :- prefix(X, PX), prefix(Y, PY), atomic_list_concat(['+', PX, PY], ' ', P).
prefix(X-Y, P) :- prefix(X, PX), prefix(Y, PY), atomic_list_concat(['-', PX, PY], ' ', P).
prefix(X*Y, P) :- prefix(X, PX), prefix(Y, PY), atomic_list_concat(['*', PX, PY], ' ', P).
prefix(X/Y, P) :- prefix(X, PX), prefix(Y, PY), atomic_list_concat(['/', PX, PY], ' ', P).
% prefix((3+4)*5, X).

