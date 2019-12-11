package Tests;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.Operation;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Monom;
import myMath.Polynom;
import myMath.Range;
import myMath.function;
import myMath.functions;
/**
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {
	public static void main(String[] a) {
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}
		
		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);	
	}
	private functions _data=null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
        System.out.println("This is a Junit Class for the Function_GUI Class");
	}
	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}
	@Test
	void testFunctions_GUI() {
		Functions_GUI First = new Functions_GUI();
		Functions_GUI Second = new Functions_GUI();
		First.group.add(new Polynom("x^2"));
		First.group.add(new Polynom("x^2-1"));
		First.group.add(new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2-1")));
		Second.group.add(new Polynom("x^2"));
		Second.group.add(new Polynom("x^2-1"));
		Second.group.add(new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2-1")));
		for (int i = 0; i < 3; i++) {
			assertTrue(First.group.get(i).equals(Second.group.get(i)));
		}
	}
	@Test
	void testInitFromFile() {
		Functions_GUI First = new Functions_GUI();
		Functions_GUI Second = new Functions_GUI();
		try {
			First.initFromFile("testInitFromFile.txt");
			Second.initFromFile("testInitFromFile.txt");
			for (int i = 0; i < 3; i++) {
				assertTrue(First.group.get(i).equals(Second.group.get(i)));
			}
		} catch (IOException e) {
			
		}
	}
	@Test
	void testSaveToFile() {
		Functions_GUI First = new Functions_GUI();
		Functions_GUI Second = new Functions_GUI();
		First.group.add(new Polynom("x^2"));
		First.group.add(new Polynom("x^2-1"));
		First.group.add(new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2-1")));
		Second.group.add(new Polynom("x^2"));
		Second.group.add(new Polynom("x^2-1"));
		Second.group.add(new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2-1")));
	 	
	    boolean areFilesIdentical = true;
	    try {
	    First.saveToFile("testSaveToFile_First.txt");
		Second.saveToFile("testSaveToFile_Second.txt");
	    FileInputStream fis1 = new FileInputStream("testSaveFromFile_First.txt");
	    FileInputStream fis2 = new FileInputStream("testSaveFromFile_Second.txt");
	    int i1 = fis1.read();
	    int i2 = fis2.read();
	    while (i1 != -1) {
	    	if (i1 != i2) {
	    		
	    		areFilesIdentical = false;
	    		break;
	        }
	        i1 = fis1.read();
	        i2 = fis2.read();
	     }
	     fis1.close();
	     fis2.close();
	     assertTrue(areFilesIdentical);
	    }
	     catch (IOException e) {
	    }
	    
	}
	@Test
	void testDrawFunctions() {
		_data.drawFunctions("GUI_params.json");
	}
	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		_data.drawFunctions(w,h,rx,ry,res);
	}
	public static Functions_GUI FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return (Functions_GUI) ans;
	}
}
