% Definición de operadores
:- op(500, xfy, +).
:- op(600, xfy, -).
:- op(700, xfy, *).
:- op(800, xfy, /).

% Reglas para convertir de notación postfija a prefija
post_to_pre(X, X) :- atomic(X).
post_to_pre(X+Y, P) :- post_to_pre(Y, PY), post_to_pre(X, PX), atomic_list_concat(['+', PX, PY], ' ', P).
post_to_pre(X-Y, P) :- post_to_pre(Y, PY), post_to_pre(X, PX), atomic_list_concat(['-', PX, PY], ' ', P).
post_to_pre(X*Y, P) :- post_to_pre(Y, PY), post_to_pre(X, PX), atomic_list_concat(['*', PX, PY], ' ', P).
post_to_pre(X/Y, P) :- post_to_pre(Y, PY), post_to_pre(X, PX), atomic_list_concat(['/', PX, PY], ' ', P).
