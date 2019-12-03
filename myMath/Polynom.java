package myMath;

import java.util.ArrayList;
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
	private ArrayList <Monom> Polynom;
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
		Polynom = new ArrayList<>();
		//s=s.replaceAll("\\s","");// replacing all unnecessary chars
		s=s.replaceAll("\\s","");
		s=s.replaceAll("\\)","");
		s=s.replaceAll("\\(","");
		s = s.replaceAll("\\-", "+-");
		if(s.charAt(0)=='+') {
			s = s.substring(1,s.length());
		}
		if(s.length()==0) {
			throw new RuntimeException("Invalid value used to create a polynom");
		}	
		String[] ps = s.split(Pattern.quote("+"));//Dividing between the '+' to a strings array
		for(int i=0; i<ps.length; i++) {
			if(ps[i].charAt(0)=='x') {
				ps[i] = "1" + ps[i];
			}
			if(ps[i].charAt(0)=='-' && ps[i].charAt(1)=='x') {
				String s2 = ps[i];
				ps[i] = s2.substring(0,0)+"-1"+s2.substring(1);
			} 
			else if(ps[i].charAt(0)=='-' && ps[i].charAt(1)!='x') {
				String s3 = ps[i];
				ps[i] = s3.substring(0,0)+"-"+s3.substring(1);
			}
		}
		int i=0;
		while(i<ps.length) {
			double co=0;
			int pow=0;
			String s1;//represents the coefficient.
			String s2;//represent the power
			//the following if will only accept a String that starts with the terms we have established (read ReadMe)
			if(Character.isDigit(ps[i].charAt(0)) || ps[i].charAt(0)=='-' || ps[i].charAt(0)=='+') 	{
				if(ps[i].contains("x")) {
					if(ps[i].contains("*")) {//in case there's '*'.
						int j=ps[i].indexOf('*'); 
						s1= ps[i].substring(0, j);
						s2=ps[i].substring(j+3, ps[i].length());
						co=Double.parseDouble(s1);
						pow=(int) Double.parseDouble(s2);
					}
					else {//if there isn't any '*'.
						int j=ps[i].indexOf('x');
						s1 = ps[i].substring(0, j);
						if(j+2<ps[i].length()) {
							s2=ps[i].substring(j+2, ps[i].length());
							co=Double.parseDouble(s1);
							pow=(int) Double.parseDouble(s2);
						}
						else {
							co=Double.parseDouble(s1);
						}
					}
				}
				else {//if ther isn't any 'x'
					co=Double.parseDouble(ps[i]);
				}
				Monom m = new Monom(co,pow);//creating the monom with co and pow that were set by the String.
				Polynom.add(m);
				i++;
			}
			else {//if the String doesn't starts in the terms we have set.
				break;
			}
		}
		Polynom.sort(comperator);//sorting 
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
		Iterator<Monom> it = Polynom.iterator();
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
	
	public String toString() { // toString function
		String polystring="";
		Iterator<Monom> it = this.iteretor();
		if(this.isZero())
			polystring+="0";
		else {
			while(it.hasNext()) {
				Monom m =it.next();
				if(m.get_coefficient()!=0) {
					polystring+=m.toString();
					if(it.hasNext())
						polystring+=" + ";
				}
			}
		}
		return polystring;
	}
	@Override
	public function initFromString(String s) {
		return new Polynom(s);
	}
	public void GUI(double x0, double x1, double eps) {

		System.out.println("The total area on the X axis is: " );
		System.out.println(area(x0, x1, eps));
		System.out.println();
		Functions_GUI frame = new Functions_GUI(this, x0, x1, eps);

		frame.setVisible(true);

	}
	public LinkedList<Double> extremaPoints(double x0, double x1, double eps) {

		LinkedList<Double> answer = new LinkedList<>();

		if (x0 > x1)

			return answer;

		Polynom der = (Polynom)this.derivative();

		double pointer = x0;

		while (pointer <= x1) {

			double changeDer = der.f(pointer)*der.f(pointer-eps); 

			if (changeDer < 0 )

				answer.add(pointer);

			else if (changeDer == 0 && der.f(pointer)==0) //pointer is extreme point

				answer.add(pointer);

			pointer += eps;

		}

		return answer;

	}

}
