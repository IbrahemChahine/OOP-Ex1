# Readme                                                                         ![Image description](https://img.techpowerup.org/191212/math-png-icon-17-1148.png)

This Project is an Exercise in an object **Object-oriented** programming (**OOP**) course in **Ariel university**.

Authors: **Ibrahem chahine, Ofir Peller**

## Description

This project is made to create and use **Polynoms** and **Monoms** objects. 
The project allows to build **Monoms** And **Polynoms** objects, either with a String or by creating a new object.
The user of this project can add, subtract, multiply and **derivate Monoms** and **Polynoms**. 


In **addition** the user can find a root of a **Polynom** and compute area's using **Riemann's Integral**. 


**- In the class Monom**: 
• There are 2 constructors. A default constructor and a copy constructor. The power of the **Monom** is always Positive.
• The user can use the following methods : 
 ```
add(Monom m1): This method adds 2 Monoms, only if they have the same power.
derivative(): This method returns a Monom such that it is the derivative of this Monom.
f(double x): This method returns the value of f(x).
get_coefficient(): this method returns the coefficient of the Monom.
get_power(): This method returns the power of the Monom.
multiply(Monom m1):This method multiplies two Monoms.
Monom(String s): This method create a Monom from a Monom represented as a String. 
toString():This method returns a string that represent the Monom.
eqauls(Monom l):This method check if monoms are eqaul.
```
**- In the class Polynom**:
```
Default constructor - initialize to the zero Polynom.

String constructor - will build a Polynom from a String. will only accept Polynom of the shape ax^b or ax^b (even if a/b=0/1 or a=-1 it needs to be written). if the Polynom starts correctly but continues wrongly, it will build what is correct.

copy constructor - copies a Polynom to another. • The user can use the following methods:

add(Monom m1) - Add m1 to this Polynom.

add (Polynom_able p1) - Add p1 to this Polynom.

area(double x0, double x1,double eps) - Compute Riemanns Integral over this Polynom, starting from x0, till x1 using eps size steps, see: https://en.wikipedia.org/wiki/Riemann_integral The algorithm is like this: while x0<= at x1:

Make x0=x0+eps.
sum x0*|f(eps)|.
advance x0 by eps.
return sum. Note: The function calculates the integral, whetear the function is below the x-axis or above

copy() Create a deep copy of this Polynom.
derivative() Creates a new Polynom which is the derivative of this Polynom.
equals (Polynom_able p1) - Test if this Polynom is equals to p1.
f(double x) Sums the value of Monons f(x) for each Monom in the Polynom.
iteretor() - A Java iterator to go over the Polynom
isZero() - Test if this is the Zero Polynom.
multiply (Polynom_able p1) - Multiply this Polynom by p1
root(double x0, double x1,double eps) - Returns the x-ax cutting point of the Polynom with an eps deviation, in the received segment, using the bisection method. This function was written with the help of: https://en.wikipedia.org/wiki/Bisection_method
sort() - Sorting the Polynoms using the Monom_comperator and the Java ArrayList Sort.
substract (Polynom_able p1) - Subtract p1 from this Polynom.
toString() - Concatenation of the Polynom.
``` 
**- In the Functions_GUI class**
```
initFromFIle(String file) - This method adds all the function in a given file to this.
saveToFIle(String file) - This method saves all the functions in a file .
drawFunctons(int width,int height,Range rx,Range ry, int resolution) - This method draws all the the function with the given values.
drawFunction(String json_file) -  This method draws all the the function with the given values in a json file.
```


**- In the Complex_Function class**
```
Default constructor  initialize this.left to the zero polynom, this.right with null and this.Op = none.
ComplexFunction(String string, function p1, function p2) -  build a complexfunciton with the given params. 
ComplexFunction(Operation OP, function p1, function p2) -  build a complexfunciton with the given params. 
plus(function f1) - add f1 to this.
mul(function f1) - multiplyes f1 with this.
div(function f1) - divides f1 with this .
max(function f1) - max between f1 and this.
min(function f1) - min between f1 and this. 
comp(function f1) -comp = f(f1).
f(double x) - returns the value of f(x).
initFromString(String s) - creates a complex function from string.
toString() -  returns a string that represents a complex function.
equals(Object obj) - check if this and obj are eqaul.
```

**- Monom_comperator** class - A class that compares between **Monoms** - will return 0 if they are equal.

## Support

For help you can go to the javadoc. 
you can get a better explanations for the methods in the classes.

In the wiki we explain how to use this project, its prefered to read the instructions in the wiki **Home** page.
## Contributing

If you want to make changes to the code i will recommend to go over the tester before, it will help you to understand how
the Methods and the Classes work.
when you start its better to start with the **Monom** class and after that with the **Polynom** class and then The ComplexFunction and the Functions_GUI.

## Authors and acknowledgment
The Authors of this project are **Ibrahem chahine, Ofir Peller.**


I also want to thank all the **Open Source Coders** in GitHub.
Thanks to :
1. GeeksforGeeks.org, 
2. [stackoverflow.com].
3. [GitHub.com].
4. [https://www.math.ucla.edu/~akrieger/teaching/18s/pic20a/examples/complex/]
5. [https://www.makeareadme.com/#template-1]
6. [youtube.com]
7. [https://sites.cs.ucsb.edu/~pconrad/cs56/12W/lect/02.02/Polynomial.java]

