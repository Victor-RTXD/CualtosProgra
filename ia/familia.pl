hombre('bonifacio_Enriquez_Torres').
hombre('David_Macias_Hernandez').

mujer('Maria_Gloria_Padilla_Gonzales').
mujer('Maria_del_Socorro_Enriquez_Padilla').
hombre('Marisol_Enriquez_Padilla').

hombre('Olga_Macias_Enriquez').
mujer('Elva_Macias_Enriquez').
mujer('Rosalva_Macias_Enriquez').
mujer('Hilda_negro').
hombre('Maria_Juana_Macias_Enriquez').
hombre('Miguel').
hombre('Ricardo').
hombre('Rafa').
hombre('Armando').
hombre('Victor_Macias_Rosales').

hombre('Victor_Eduardo_Macias_Macias').
mujer('Karol_Fernanda_Macias_Macias').
mujer('Jimena_Robredo_Macias').
mujer('Emilie_negro').
mujer('Samantha_Negro').
hombre('Alonso_Marquez_Macias').
hombre('Roberto_Marquez_Macias').
hombre('Sebastian_Marquez_Macias').
hombre('Armando_Macias_Lopez').

progenitor('bonifacio_Enriquez_Torres','Maria_del_Socorro_Enriquez_Padilla').

progenitor('Maria_Gloria_Padilla_Macias','Maria_del_Socorro_Enriquez_Padilla').

progenitor('bonifacio_Enriquez_Torres','Marisol_Enriquez_Padilla').

progenitor('Maria_Gloria_Padilla_Macias','Marisol_Enriquez_Padilla').

progenitor('David_Macias_Hernandez','Olga_Macias_Enriquez').

progenitor('Maria_del_Socorro_Enriquez_Padilla','Olga_Macias_Enriquez').

progenitor('David_Macias_Hernandez','Elva_Macias_Enriquez').

progenitor('Maria_del_Socorro_Enriquez_Padilla','Elva_Macias_Enriquez').

progenitor('David_Macias_Hernandez','Rosalva_Macias_Enriquez').

progenitor('Maria_del_Socorro_Enriquez_Padilla','Rosalva_Macias_Enriquez').

progenitor('David_Macias_Hernandez','Hilda_negro').

progenitor('Maria_del_Socorro_Enriquez_Padilla','Hilda_negro').

progenitor('David_Macias_Hernandez','Maria_Juana_Macias_Enriquez').

progenitor('Maria_del_Socorro_Enriquez_Padilla','Maria_Juana_Macias_Enriquez').

progenitor('Victor_Macias_Rosales','Victor_Eduardo_Macias_Macias').

progenitor('Maria_Juana_Macias_Enriquez','Victor_Eduardo_Macias_Macias').

progenitor('Victor_Macias_Rosales','Karol_Fernanda_Macias_Macias').

progenitor('Maria_Juana_Macias_Enriquez','Karol_Fernanda_Macias_Macias').

progenitor('Elva_Macias_Enriquez','Jimena_Robredo_Macias').
progenitor('Hilda_negro','Emilie_negro').
progenitor('Hilda_negro','Samantha_Negro').
progenitor('Rosalva_Macias_Enriquez','Alonso_Marquez_Macias').
progenitor('Roberto_Marquez_Macias','Sebastian_Marquez_Macias').
progenitor('Rosalva_Macias_Enriquez','Roberto_Marquez_Macias').
progenitor('Olga_Macias_Enriquez','Armando_Macias_Lopez').
progenitor('Ricardo','Jimena_Robredo_Macias').
progenitor('Armando','Armando_Macias_Lopez').
progenitor('Miguel','Alonso_Marquez_Macias').
progenitor('Miguel','Sebastian_Marquez_Macias').
progenitor('Miguel','Roberto_Marquez_Macias').
progenitor('Rafa','Emilie_negro').
progenitor('Rafa','Samantha_Negro').

%relaciones
padre(P,H) :- progenitor(P,H), hombre(P).
madre(M, H) :- progenitor(M, H), mujer(M).
hijo(H,P) :- progenitor(P,H), hombre(H).
abuelo(A,N) :- hombre(A), progenitor(P,N), progenitor(A,P).
abuelo2(A,N) :- padre(A,P), progenitor(P,N).
abuela(A,N) :- mujer(A,P), progenitor(P,N).
abuelo_pat(A,N) :- padre(A,P), padre(P,N).
abuelo_mat(A,N) :- padre(A,M), madre(M, N).
hermanos(H1, H2) :- progenitor(P,H1), progenitor(P,H2), H1\=H2.
ancestro(H,D) :- progenitor(H,D).
ancestro(A,D) :- progenitor(A,H), ancestro(H,D).

tio(A, B) :- madre(X, B),hermanos(X, A).
tio(A, B) :- padre(X, B),hermanos(X, A).
primo(A, B) :- tio(X, A), padre(X, B), A\=B.
primo(A, B) :- tio(X, A), madre(X, B), A\=B.

tioabuelo(A,N) :- padre(A,P), padre(P,N), hermanos(H2,A).

%primos(Primo1, Primo2) :- progenitor(Padre1, Primo1), progenitor(Padre2, Primo2), hermanos(Padre1, Padre2).

pareja(P, M) :- padre(P, H), madre(M, H).


