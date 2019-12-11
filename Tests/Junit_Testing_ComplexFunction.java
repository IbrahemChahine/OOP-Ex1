package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Polynom;

class Junit_Testing_ComplexFunction {
	@Test
	void plus() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_Plus_f1 = new ComplexFunction("plus",f2,f3);
		f1.plus(f1);
		assertTrue(f1_Plus_f1.equals(f1));
	}
	@Test
	void mul() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_Times_f1 = new ComplexFunction("mul",f2,f3);
		f1.mul(f1);	
		assertTrue(f1_Times_f1.equals(f1));
	}
	@Test
	void div() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_Divid_f1 = new ComplexFunction("div",f2,f3);
		f1.div(f1);	
		assertTrue(f1_Divid_f1.equals(f1));
	}
	@Test
	void max() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_max_f1 = new ComplexFunction("max",f2,f3);
		f1.max(f1);	
		assertTrue(f1_max_f1.equals(f1));
	}
	@Test
	void min() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_min_f1 = new ComplexFunction("min",f2,f3);
		f1.min(f1);	
		assertTrue(f1_min_f1.equals(f1));
	}
	@Test
	void comp() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f3 = new ComplexFunction("plus",new Polynom("x^2"),new Polynom("x^2"));
		ComplexFunction f1_max_f1 = new ComplexFunction("comp",f2,f3);
		f1.comp(f1);	
		assertTrue(f1_max_f1.equals(f1));
	}
	@Test 
	void fTest() {
		double x = 4;
		double outCome = 36;
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("1x^1"), new Polynom("2x^2"));
		assertEquals(outCome,f1.f(x));
		
	}
	@Test
	void initFromString() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("1x^1"), new Polynom("2x^2"));
		ComplexFunction f2 = (ComplexFunction) f1.initFromString("Plus(1x^1,2x^2)");
		assertTrue(f1.equals(f2));
	}
	@Test
	void eqauls() {
		ComplexFunction f1 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		ComplexFunction f2 = new ComplexFunction("plus",new Polynom("x^2"), new Polynom("x^2"));
		assertTrue(f1.equals(f2));
	}	
}
