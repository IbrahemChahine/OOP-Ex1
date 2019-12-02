package Tests;

import myMath.ComplexFunction;
import myMath.Polynom;

public class tester {

	public static void main(String[] args) {
		ComplexFunction f = new ComplexFunction("mul",new Polynom("x^2"),new Polynom("x^2"));
		String s = f.toSrting();
		System.out.println(s);
		ComplexFunction f1 = (ComplexFunction) f.initFromString("dfgb(1.0*x^2 + 5.0 , 1.0*x^2)");
		//ComplexFunction f2 = (ComplexFunction) f.initFromString("Times(1.0*x^2 + 5.0 , -1.0*x^2)");
		//ComplexFunction f3 = (ComplexFunction) f.initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 +-1.2999999999999998x +5.0),plus(div(1.0x +1.0,mul(mul(1.0x +3.0,+1.0x +-2.0),+1.0x +-4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 +-1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 +-1.2999999999999998x +5.0)");
		
		//f1.plus(f2);
		//f1.plus(f2);
		//String s1 = f3.toSrting();
		//System.out.println(s1);
		
		System.out.println(f1.f(2));
	}

}
