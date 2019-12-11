package Tests;
import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Polynom;
import myMath.Range;
public class test {
	public static void main(String[] args) {

//		ComplexFunction f2 = new ComplexFunction();
//		f2 = (ComplexFunction) f2.initFromString("None(-1.0X^4+2.4X^2+3.1,EmptyPolynom)");
		Polynom xRegular = new Polynom("X");
		Polynom xSquared = new Polynom("X^2");
		Polynom xPower3 = new Polynom("X^3");
		
		ComplexFunction OneOverX = new ComplexFunction();
		OneOverX = (ComplexFunction) OneOverX.initFromString("1-x^2");
		Functions_GUI fgui1 = new Functions_GUI();
//		fgui1.add(xSquared);
//		fgui1.add(xPower3);
//		fgui1.add(xRegular);
		fgui1.add(OneOverX);
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-10,10);
		fgui1.drawFunctions(w,h,rx,ry,res);
		
//		ComplexFunction f1 = new ComplexFunction("div",new Polynom("x^4"),new Polynom("x^2"));
//		Polynom p = new Polynom("x^2");
//		System.out.println(f1.equals(p));
		
//		
//		Polynom test = new Polynom("-3x^2+12");
//		System.out.println(test);
//		System.out.println(test.root(-6, -1, 0.1));
//		Polynom test2 = new Polynom("x^2+-3");
//		System.out.println(test2.area(0, 3, 0.01));
	}

}
