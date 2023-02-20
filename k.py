from math import sqrt


x0 = float(input("x0: "))
x1 = float(input("x1: "))
x2 = float(input("x2: "))
fx0 = float(input("fx0: "))
fx1 = float(input("fx1: "))
fx2 = float(input("fx2: "))

a = ((x1-x2)*(fx0-fx2) - (x0-x2)*(fx1-fx2))/((x0-x2)*(x1-x2)*(x0-x1))
print(a)
b = (((x0-x2) ** 2) * (fx1-fx2) - ((x1-x2) ** 2) * (fx0-fx2)) / ((x0-x2)*(x1-x2)*(x0-x1))
print(b)
c = fx2

x3 = x2 - (2*c)/(b+sqrt((b**2) - 4 * a *c))
print(x3)