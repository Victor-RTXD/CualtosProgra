:- dynamic animal/1.
animal(leon).

:- dynamic animal/2.
animal(oso, carne).
animal(perro,carnivoro).
animal(tigre, carnivoro).
animal(humano, omnivoro).
animal(caballo, herbivoro).

%crud
inicio :- consult('prolog3.pl').
crear(A) :- asserta(animal(A)),
    write("nuevo conocimiento").
buscar(A) :- animal(A).
eliminar(A) :- retract(animal(A)).
guardar :- tell('prolog3.pl'), 
    listing(animal/1)
    told,
    write('guardado').
mostrarTodo :- listing(animal/1).
fin :- guardar, retractall(animal(_)).

es_animal(A) :- buscar(A), write(A), !.
es_animal(A) :- crear(A), write('No existía, pero ahora existe el animal: '), write(A).
