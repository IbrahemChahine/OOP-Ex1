package Tests;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Polynom;

public class tester {

	public static void main(String[] args) {
		ComplexFunction f = new ComplexFunction("mul",new Polynom("x-x^2"),new Polynom("x^2"));
		//String s = f.toSrting();
		//System.out.println(s);
		ComplexFunction f1 = (ComplexFunction) f.initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5-1.2999999999999998x +5.0),plus(div(1.0x +1.0,mul(mul(1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(1.0x^4 +2.4x^2 +3.1,0.1x^5-1.2999999999999998x +5.0),1.0x^4 +2.4x^2 +3.1)),1.0x^4 +2.4x^2 +3.1),0.1x^5-1.2999999999999998x +5.0)");
		String s1 = f1.toSrting();
		System.out.println(s1);
	
		
	}
}
