package myMath;

import java.io.IOException;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Polynom;
import myMath.Range;
public class tester_inmyMath {

	public static void main(String[] args) {
		System.out.println("hello0");
		ComplexFunction f = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		System.out.println("I am here " + f.toString());
//		//String s = f.toSrting();
//		//System.out.println(s);
		ComplexFunction f1 = (ComplexFunction) f.initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5-1.2999999999999998x +5.0),plus(div(1.0x +1.0,mul(mul(1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(1.0x^4 +2.4x^2 +3.1,0.1x^5-1.2999999999999998x +5.0),1.0x^4 +2.4x^2 +3.1)),1.0x^4 +2.4x^2 +3.1),0.1x^5-1.2999999999999998x +5.0)");
		String s1 = f1.toString();
		System.out.println("I am here 2 " + s1);
//		Functions_GUI GUI = new Functions_GUI();
//		Polynom polynom1 = new Polynom("x^3");
//		ComplexFunction f2 = new ComplexFunction("divid",new Polynom("1"),new Polynom("x^2"));
//		GUI.add(new Polynom("x-1"));
//		GUI.add(polynom1);
//		GUI.add(f);
//		GUI.add(f2);
//		GUI.add(new Polynom("x^2-2x+1"));
//		GUI.add(new Polynom("x^2+2x-1"));
//		Range rx = new Range(-10,10);
//		Range ry = new Range(-10,10);
//
//		GUI.drawFunctions(400, 400, rx, ry, 10000);
//		System.out.println("hello1");
		
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p3 = new Polynom(s3[0]);
		
		Functions_GUI testFGUI = new Functions_GUI();
		try {
			testFGUI.initFromFile("function_file.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Range rxx = new Range(-10, 10);
		Range ryy = new Range(-10, 10);
		try{
			Polynom newP = new Polynom("x+3");
			System.out.println("For x+3 value of 5 for x gave - " + newP.f(5));
			System.out.println("Does testGUI contain x+3? - " + testFGUI.contains(newP));
			ComplexFunction CF2 = new ComplexFunction();
			CF2.initFromString("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
			System.out.println("does testFGUI contain CF2? - " + testFGUI.contains(CF2));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(testFGUI.toString());
		testFGUI.drawFunctions(900, 800, rxx, ryy, 5000);
		System.out.println("end");
		
	}
}
