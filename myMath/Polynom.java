package myMath;

import java.util.ArrayList;
import java.util.Collections;

import myMath.Functions_GUI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.regex.Pattern;


import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral.
 * 2. Finding a numerical value between two values.
 * 3. Derivative
 * 
 * @author Ibrahem chahine.
 *
 */
public class Polynom implements Polynom_able{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7415068870735956797L;
	private ArrayList <Monom> Polynom = new ArrayList<Monom>();
	private final Monom_Comperator comperator = new Monom_Comperator();
	/**
	 * Default constructor
	 */
	public Polynom() {
		this.Polynom = new ArrayList<>();
	}
	/**
	 * A copy constructor 
	 */
	public Polynom(Polynom p) { // copy constructor
		Polynom = new ArrayList<>(); 
		Iterator<Monom> it = p.iteretor();
		while(it.hasNext()) {
			Monom m = new Monom(it.next());
			Polynom.add(m);
		}
	}
	/**
	 * A constructor that make a polynon from a polynom represented as a string.
	 * init a Polynom from a String such as: 
	 * Example: s = {"x", "(3)+(1.4X^3)+(-34x)","(2x^2-4)"};
	 * @param s  is a string represents a Polynom
	 * @param ps  An array of Strings to deal with the monoms separately.
	 * @param co _coefficient.
	 * @param s1  It gonna be converted to _coefficient or as we called it in the function bellow co.
	 * @param pow  _ power.
	 * @param s2  It gonna be converted to _power or as we called it in the function below - pow.
	 * @param j  its gonna help us to determine the index of the 'x' and the '*'
	 */
	/*
	public Polynom(String str){//string constractor
		str = str.replaceAll("X", "x");	
		this.Polynom=new ArrayList<Monom>(0);
		str = str.replaceAll("\\-", "+-");
		str = str.replaceAll("\\*", "");
		if(str.charAt(0)=='+') {
			str=str.substring(1);
		}
		for(String m :str.split("\\+")) Polynom.add(new Monom(m));
		Polynom.sort(new Monom_Comperator());	
	}
	*/
	public Polynom(String s) {
		s = s.replaceAll(" ", ""); //remove spaces
		if (s.equals("") || s.equals("EmptyPolynom")) {return;}		
		//empty string or copying from an empty polynom - the polynom will be empty.

		StringTokenizer st = new StringTokenizer(s,"+-", true);
		ArrayList<String> stringPartsArrayList = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			if(temp.equals("+")) {stringPartsArrayList.add(st.nextToken());}
			else if(temp.equals("-")) {stringPartsArrayList.add('-'+st.nextToken());}
			else {stringPartsArrayList.add(temp);}
		} //end while

		//Each +- sign will be used as tokens, to seperate monoms making up the polynom.
		//+ signs shouldn't matter, just add a monom.
		//- signs will be given as part of the string to the monom constructor
		//to assure the monom will have a negative coefficient.

		try {
			for (String i: stringPartsArrayList) {
				this.add(new Monom(i));
			}
		}
		catch(Exception e) {
			System.err.println("Invalid input for one or more power(s) or coefficient(s)! Created Polynom will be empty!");
			this.Polynom.clear();
			throw new IllegalArgumentException();
		}
		//Receiving an exception here can only happen from an invalid power for a Monom that is part of the Polynom.
		//If this happens, I choose to instead create an empty Polynom - so as to not calculate wrong values for it, should I be requsted to.
		//My reason being - having all calculating functions (area, root) return 0 for it, should make it obvious for the user there is a mistake.

		Collections.sort(this.Polynom, new Monom_Comperator());
		this.cleanZeroCoefficient();
		//I want the Polynom to be organized. Do this by power level of each monom.
		//Also, there might be monoms with 0 coefficient. Delete those, as they are useless.
	}

	/**
	 * The function will return the value of f(x).
	 * @param sum: the sum of value of f(x) in every one of the monoms.
	 * @param it - The itarator that will go over the polynom.
	 * @param x - the value of x.
	 * @return sum - f(x). 
	 */
	@Override
	public double f(double x) {
		double sum=0;
		Iterator<Monom> it = Polynom.iterator();
		while(it.hasNext()) {
			sum+=it.next().f(x);
		}
		return sum;
	}
	/**
	 * The function will add a polynom to the polynom.
	 * The function will go over the polynom and add the monoms using the Monom's add function.
	 * param it  The iterator.
	 * @param p1 The polynom that the user want to add.
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			this.add(m);
		}
	}
	/**
	 * The function will add a monom to th e polynom, and sort it using the sort function.
	 * @param flag  will help us determine if the monom that we are adding has no monom with similar power in the polynom.
	 * @param it  the iterator.
	 * @param m1  The monom that is added
	 */
	@Override
	public void add(Monom m1) {
		boolean flag = false;
		Iterator<Monom> it = this.iteretor();
		if(m1.get_coefficient()!=0) {
			while(it.hasNext()) {
				Monom m = it.next();
				if(comperator.compare(m1,m)==0) {
					m.add(m1);
					flag=true;
				}
			}
			if(!flag) {
				Polynom.add(m1);
				this.sort();
			}
		}
	}
	/**
	 * The function will subtract polnom by multiplying each monoms in -1 and calling add(polynom p1).
	 * @param minus: An monom with the value -1.
	 * Were multiplying each monom in p1 int this polynom in order to change its sign.
	 * @param p1 the polynom that is subtracted.
	 */
	@Override
	public void substract(Polynom_able p1) {
		Monom minus = new Monom(-1,0);
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom m=it.next();
			m.multiply(minus);
			this.add(m);
		}
	}
	/**
	 * Multiplying 2 Polynoms.
	 * @param helper an aid Polynom to copy it the result.
	 * @param itAble  p1's iterator.
	 * @param itPoly  this.Polynom's iterator.
	 * @param mA  MonomAble - points p1's Monoms.
	 * @param mP  MonomPolynom - points this.Polynom's Monoms.
	 * @param mH  MonomHelper - points helper's Monoms.
	 * @param p1  The Polynom that is multipied.
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom helper = new Polynom();
		Iterator<Monom> itAble = p1.iteretor();
		while(itAble.hasNext()) {
			Monom mA = itAble.next();
			Iterator<Monom> itPoly = Polynom.iterator();
			while(itPoly.hasNext()) {
				Monom mP = new Monom(itPoly.next());
				mP.multiply(mA);
				helper.add(mP);

			}
		}
		Polynom.clear();
		Iterator<Monom> itHelp = helper.iteretor();
		while(itHelp.hasNext()){
			Monom mH =new Monom(itHelp.next());
			Polynom.add(mH);
		}
	}
	/** 
	 * The function compares between each 2 Monoms at the same place in the 2 Polynom's ArrayListand 
	 * @return false if they are not equal.
	 * @param itAble  p1's iterator.
	 * @param itPoly  this.Polynom's iterator.
	 * @param p1  The Polynom that this.Polynom is being compared to
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> itAble = p1.iteretor();
		Iterator<Monom> itPoly = Polynom.iterator();
		while(itPoly.hasNext() && itAble.hasNext()) {
			Monom mp = itPoly.next();
			Monom ma = itAble.next();
			if(mp.get_power() == ma.get_power()) {
				if(mp.get_coefficient() == ma.get_coefficient());
				else return false;
			}
			else return false;
		}
		if(itAble.hasNext() || itPoly.hasNext()) return false;
		return true;
	}
	/**
	 * The function check's if the Polynom is empty - if it is, it means it is the zero Polynom.
	 *@return true if it is the zero Polynom.
	 * 
	 */
	@Override
	public boolean isZero() { //TODO - get rid of monom's with zero coefficient. Otherwise this method will fail.
		if (Polynom.isEmpty()) return true;
		return false;
	}
	/**
	 * A function that finds The root of The Polynom in a certain segment
	 * @return the function's root in the recieved values, with an exception of eps, using the bisection method.
	 * param mid  the middle of each segment we're checking.
	 * @param x0 
	 * @param x1
	 * @param eps
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double fX0=f(x0);
		double fX1=f(x1);
		if(fX0*fX1>0){
			throw new RuntimeException("the values of f(x0) and f(x1) are not opposite");
		}
		double mid=(x0+x1)/2;
		double fMid = f(mid);
		while(Math.abs((fMid))>eps) {
			if(fX0*fMid==0) return fMid;
			else if(fX0*fMid>0) x0=mid;
			else x1=mid;
			mid=(x0+x1)/2;
			fMid = f(mid);
			fX0=this.f(x0);
		}
		return fMid;
	}
	/**
	 * @return a Polynom that is a copy of another Polynom
	 */
	@Override
	public Polynom_able copy() {
		return new Polynom(this);
	}
	/**
	 * The function returns the derivative of the polynom
	 * param helper an aid Polynom to copy the result to afterwards.
	 * param it  the polynom's iterator
	 * param ithelper helper's iterator
	 */
	@Override
	public Polynom_able derivative() {
		Polynom helper = new Polynom();
		Iterator<Monom> it = Polynom.iterator();
		while(it.hasNext()) {
			Monom m = new Monom(it.next());
			m.derivative();
			if(m.get_coefficient()!=0)
				helper.add(m);
		}
		return helper;
	}
	/**
	 * @return the value of the integral using rieman's method.
	 * @param x0
	 * @param x1
	 * @param eps
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		x0=x0+eps;
		double fx0 = f(x0);
		double sum=0;
		while(x0<=x1) {
			if(fx0>=0) {
				sum+=fx0*eps;
			}
			x0+=eps;
			fx0=f(x0);
		}
		return sum;
	}
	/**
	 * @return the Polynom's iterator.
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = Polynom.iterator();
		return it;
	}
	/**
	 * The function multipliys polynom with monom.
	 * We pass on the monoms and multiply them with m1.
	 */
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> it = Polynom.iterator();
		if(m1.get_coefficient()!=0) {
			while (it.hasNext()) {
				Monom m = it.next();
				m.multiply(m1);
			}
		}
		else{
			Polynom.clear();
		}
		this.sort();
	}
	/**
	 * Sorting the Polynom's using the comperator.
	 */
	public void sort() {
		this.Polynom.sort(comperator); 
	}
	/**
	 *clearing the Polynom (making it the zero polynom).
	 */
	public void empty() { // clearing the Polynom (making it the zero polynom).
		Polynom.clear();
	}
	
	public String toString() {
		String answer = "";
		if(this.isZero()) {return "EmptyPolynom";}

		for (Monom i:this.Polynom) {
			if (i.get_coefficient()<0) {
				answer = answer + i.toString();
			}
			else if (!answer.isEmpty()){
				answer = answer + "+" + i.toString();
			}
			else if (answer.isEmpty()){
				answer = i.toString();
			}
		}
		return answer;
	}
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}
	public void cleanZeroCoefficient() {
		int monomIndex = -1;
		Iterator<Monom> itr = this.iteretor();
		Monom current;
		while(itr.hasNext()) {
			current = itr.next();
			monomIndex+=1;
			if (current.get_coefficient()==0) {
				Polynom.remove(monomIndex);
				itr = this.iteretor();
				monomIndex=-1;
			} //end if
		}// end for
	}// end function



}
