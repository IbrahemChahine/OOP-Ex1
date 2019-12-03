package Tests;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Polynom;
import myMath.Range;
public class tester {

	public static void main(String[] args) {
		ComplexFunction f = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		//String s = f.toSrting();
		//System.out.println(s);
		ComplexFunction f1 = (ComplexFunction) f.initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5-1.2999999999999998x +5.0),plus(div(1.0x +1.0,mul(mul(1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(1.0x^4 +2.4x^2 +3.1,0.1x^5-1.2999999999999998x +5.0),1.0x^4 +2.4x^2 +3.1)),1.0x^4 +2.4x^2 +3.1),0.1x^5-1.2999999999999998x +5.0)");
		String s1 = f.toSrting();
		System.out.println(s1);
		Functions_GUI GUI = new Functions_GUI();
		Polynom polynom1 = new Polynom("x^3");
		ComplexFunction f2 = new ComplexFunction("divid",new Polynom("1"),new Polynom("x^2"));
		GUI.add(new Polynom("x-1"));
		GUI.add(polynom1);
		GUI.add(f);
		GUI.add(f2);
		GUI.add(new Polynom("x^2-2x+1"));
		GUI.add(new Polynom("x^2+2x-1"));
		Range rx = new Range(-10,10);
		Range ry = new Range(-10,10);

		GUI.drawFunctions(400, 400, rx, ry, 10000);
		
	}
}
