inicio :- write("puto").

%hechos
planeta("venus").
planeta(tierra).
planeta(jupiter).

mamifero(leon).
mamifero(delfin).
mamifero(tigre).
mamifero(caballo).

%reglas - relaciones
se_alimenta(leon,carne).
se_alimenta(tigre,carne).
se_alimenta(delfin,peces).
se_alimenta(puma,carne).
se_alimenta(caballo,planta).

%unificacion - retroceso(backtracking)
carnivoro(A) :- mamifero(A), se_alimenta(A,carne).

suma(A,B,Res) :- Res is A+B.
suma(A,B) :- Res is A+B, write(Res).
suma :- nl, write('valor 1: '), read(A), nl, write('valor 2:'), read(B), suma(A,B), nl.

pita(C, D, Pi) :- Pi is ((C^2) + (D^2))^(1/2).

factorial(0, 1).
factorial(N, Result) :-
    N > 0,
    N1 is N - 1,
    factorial(N1, Result1),
    Result is Result1 * N.
