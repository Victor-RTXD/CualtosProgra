
FUNCTION factorial:
t1 = n <= 1
if t1 goto L1
return 1
goto L2
L1:
t3 = n - 1
t2 = CALL factorial(t3)
t4 = n * t2
return t4
L2:

FUNCTION main:
a = 20000000000000000000000000000000000000000
return 0