package Tests;

import myMath.Monom;
import myMath.Polynom;
import myMath.Polynom_able;

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		test3();
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		double aa = p1.area(0, 1, 0.0001);
		System.out.println("area of Polynom from 0 to 1 is: " + aa);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1);
		System.out.println("from string equals original - " + p1.equals(pp1));
	}
	@SuppressWarnings("unused")
	public static void test3() {
		Polynom first = new Polynom("3+2x^2");
		Polynom second = new Polynom("3+2x^2");
		System.out.println("First polynom is: " + first);
		System.out.println("Second polynom is: " + second);
		System.out.println("First and second are equal = " + first.equals(second));
		first.add(second);
		System.out.println("First is now First + Second, which is = " +first);
		System.out.println("Second is still: " + second + ", so they shouldn't be equal");
		System.out.println("First and second are equal?: " + first.equals(second));
		Polynom thirdFromString = new Polynom(first.toString());
		System.out.println("Create a new Polynom form First's string funciton. Are they equal?: " + first.equals(thirdFromString) + "\n");
		
		System.out.println("Now let's test some wrong values! Will now try to create a Polynom using invalid valuse");
		
		try {
			System.out.println("Trying --3x");
			Polynom wrongValues = new Polynom("--3x");
		}
		catch (Exception e) {System.err.println("Illegal values! Error catched");}
		
		try {
			System.out.println("Trying (5x^2)");
			Polynom wrongValues = new Polynom("(5x^2)");
		}
		catch (Exception e) {System.err.println("Illegal values! Error catched");}
		
		try {
			System.out.println("Trying [2x^0]");
			Polynom wrongValues = new Polynom("[2x^0]");
		}
		catch (Exception e) {System.err.println("Illegal values! Error catched");}
		
		System.out.println("\n OK! Now let's try to use the area and root functions. I will use the polynom x^2 for this. "
				+ "\n I will test to make sure we find the area between 0-3 as 9, and for root the polynom -3x^2+12, between -3 to -1, "
				+ "which shuold find (-2,0) and return -2");
		
		Polynom xSquared = new Polynom("x^2");
		Polynom minusThreeX = new Polynom ("-3x^2+12");
		System.out.println("Area of x^2 between 0 and 3 should be 9. Area function calculated it as: " + xSquared.area(0, 3, 0.01));
		System.out.println("Root should give back -2 with accuracy of 0.01, and it gave back : " + minusThreeX.root(-6, -1, 0.01));

	}
}
