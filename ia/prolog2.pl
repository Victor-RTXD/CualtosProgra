% Caso base: el primer y segundo número de Fibonacci son 1.
fibo(1, 1).
fibo(2, 1).

% Caso recursivo: N es mayor que 2.
fibo(N, F) :-
    N > 2,
    N1 is N - 1,
    N2 is N - 2,
    fibo(N1, F1),  % Calcula Fibonacci de N-1
    fibo(N2, F2),  % Calcula Fibonacci de N-2
    F is F1 + F2.   % F es la suma de los dos anteriores
