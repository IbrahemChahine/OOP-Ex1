package myMath;

/** This interface represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions), 
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
 * @author Ibrahem Chahine and Ofir Peller.
**/

public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1785701227096562369L;
	/**
	 * 
	 */
	public function left;
	public function right;
	public Operation Op;
	/*
	 * A default constructor.
	 */
	public ComplexFunction() {
		this.left = new Polynom();
		this.right = null;
		this.Op = Operation.None;
	}
	/*
	 * A constructor that builds a complex function with String as the Operation and a pair of functions for the left and right.
	 * @param string - A string representing an Operation. 
	 * @param p1 - A function for the left side of this complex function, this function can be a ComplexFunction, a Polynom, or a Monom.
	 * @param p2 - A function for the left side of this complex function, this function can be a ComplexFunction, a Polynom, a Monom or null.
	 */
	public ComplexFunction(String string, function p1, function p2) {
		this.left = p1;
		this.right = p2;

		try {
			this.Op = setOp(string);
		}
		catch(Exception e){
			throw e;
		}


	}
	/*
	 * A constructor that builds a complex function with the Operation and a pair of functions for the left and right.
	 * @param OP - The Operation. 
	 * @param p1 - A function for the left side of this complex function, this function can be a ComplexFunction, a Polynom, or a Monom.
	 * @param p2 - A function for the left side of this complex function, this function can be a ComplexFunction, a Polynom, a Monom, or null.
	 */
	public ComplexFunction(Operation OP, function p1, function p2) {
		this.left = p1;
		this.right = p2;
		this.Op = OP;
	}
	/*
	 * A constructor that builds a complex function  with only a left function.
	 * @param left - A function for the left side of this complex function, this function can be a ComplexFunction, a Polynom, or a Monom.
	 * The Operation of this complex function is None.
	 */
	public ComplexFunction(function left) {
		this.left = left;
		this.Op = Operation.None;
	}
	/** Add to this complex_function the f1 function 
	 * @param f1 The function that will be the right side of the resulting complex_function.
	 * @param flag - This parameter helps us to know if this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void plus(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Plus;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Plus;
		}
	}
	/** multiply this complex_function and the given f1 function 
	 * @param f1 The function that will be the right side of the resulting CF.
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void mul(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Times;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Times;
		}
	}
	/** divide this complex_function and the given f1 function 
	 * @param f1 The function that will be the right side of the resulting CF.
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void div(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Divid;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Divid;
		}
	}
	/** max between this complex_function and the given f1 function 
	 * @param f1 The function that will be the right side of the resulting CF.  
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void max(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Max;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Max;
		}
	}	
	/** min between this complex_function and the given f1 function 
	 * @param f1 The function that will be the right side of the resulting CF.
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void min(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Min;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Min;
		}
	}
	/**
	 * This method wraps the given f1 function with this CF, so that after the method is done, this.f(x) = this.f(f1(x))
	 * @param f1 The function that will be the wrapped function in the resulting CF.
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
	 * @param helper - helper is a copy of this ComplexFunction, we use it if this is a ComplexFunction.
	 * @param helperPolynom - helper is a copy of this Polynom, we use it if this is a Polynom or Monom.
	 */
	@Override
	public void comp(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinite loop
				ComplexFunction helper2 = new ComplexFunction();
				helper2 = (ComplexFunction)helper.copy();
				this.right = helper2;
			}
			else{this.right = f1;}
			this.Op = Operation.Comp;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Comp;
		}
	}
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
	@Override
	public function left() {
		return this.left;
	}
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
	@Override
	public function right() {
		return this.right;
	}
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp, none or error
	 * @return this.Op - the Operation of this.
	 */
	@Override
	public Operation getOp() {
		return this.Op;
	}
	/*
	 * This method computes the value of the complex function for a given value x.
	 */
	@Override
	public double f(double x) {
		if(this.Op.name()=="None" ){
			if(this.right!=null){
				if(this.right instanceof Polynom_able) {
					Polynom tempPoly = (Polynom) this.right;
					if (tempPoly.isZero()) {return this.left.f(x);}
					else {throw new RuntimeException("Was asked to calculate f(x) for a ComplexFunction that has None as Operator and has both a Left and Right. Illegal!");}
				}
				else if(this.right instanceof Monom) {
					Monom tempMonom = (Monom) this.right;
					if(tempMonom.isZero()) {return this.left.f(x);}
					else {throw new RuntimeException("Was asked to calculate f(x) for a ComplexFunction that has None as Operator and has both a Left and Right. Illegal!");}
				}
				throw new RuntimeException("Was asked to calculate f(x) for a ComplexFunction that has None as Operator and has both a Left and Right. Illegal!");
				}
			else {
				if(this.left instanceof Polynom) {
					Polynom tempPoly = (Polynom) this.left;
					return tempPoly.f(x);
				}
				else if(this.left instanceof Monom) {
					Monom tempMonom = (Monom) this.left;
					return tempMonom.f(x);
				}
				else {
					ComplexFunction tempCF = (ComplexFunction) this.left;
					return tempCF.f(x);
				}
			}
		}//end OP is none case
		
		if(this.Op.name()=="Error") throw new RuntimeException("Used f function on ComplexFunction that has Error as it's OP");

		double LeftValue = 0;
		double RightValue = 0;

		if(this.Op.name()=="Comp") {
			RightValue = this.right.f(x);
			return this.left.f(RightValue);
		}

		LeftValue = this.left.f(x);

		if(this.right == null) {;}
		else {RightValue = this.right.f(x);}

		switch(this.Op) {

		case Plus:
			return (double) LeftValue + RightValue;
		case Times:
			return (double) LeftValue * RightValue;
		case Divid:
			if(RightValue==0) {throw new RuntimeException("Was asked to divide by 0");}
			return (double) LeftValue / RightValue;
		case Max:
			return LeftValue>RightValue? LeftValue:RightValue;
		case Min:
			return LeftValue<RightValue? LeftValue:RightValue;

			//the following cases are not needed, but are there so we will take care of all the cases, even if just in theory.
		case Comp:
			break;
		case Error:
			break;
		case None:
			break;
		default:
			break;
		}
		return 0; //general return, will not be used.
	}
	/*
	 * this method set the Operation with a string.
	 * please note that 'error' and 'none' will be accepted as an input and set the OP accordingly, but each have a special case when calculating this.f(x).
	 * Any string given with an invalid value (not an option of Operation class) will throw an exception.
	 */
	public Operation setOp(String s) {
		String string = s.toLowerCase();
		Operation answer = null;

		switch(string) {

		case "plus":
			answer = Operation.Plus;
			break;
		case "mul":
			answer = Operation.Times;
			break;
		case "multiply":
			answer = Operation.Times;
			break;
		case "times":
			answer = Operation.Times;
			break;
		case "div":
			answer = Operation.Divid;
			break;
		case "divid":
			answer = Operation.Divid;
			break;
		case "max":
			answer = Operation.Max;
			break;
		case "maximum":
			answer = Operation.Max;
			break;
		case "min":
			answer = Operation.Min;
			break;
		case "minimum":
			answer = Operation.Min;
			break;
		case "comp":
			answer = Operation.Comp;
			break;
		case "error":
			answer = Operation.Error;
			break;
		case "none":
			answer = Operation.None;
			break;
		default:
			throw new RuntimeException("Invalid value entered as input for setting the OP");	
		}

		return answer;

	}

	/*
	 * This method returns a function from a string represented an a function.
	 * @param s - A string that represents a function.
	 * @param helper - the function that the method will return.
	 * @param LocationOfFirstBracket - the location of the First Bracket in the string, if such exists.
	 * @param operatorString - A string that represents the Operation of the function.
	 * @param NumOfBrackets - the number of brackets found in the string.
	 * @param mainCommaIndex - the index of the main comma in the function, Example = say p and q are functions then 
	 * consider the input to be {"plus(p,q)"} we get that the main comma index is 6.
	 * @param Left_Flag - this boolean value is true if the left function in the string represents a ComplexFunction.
	 * @param leftSidePolynom - if Left_Flag is false then left is a polynom so this variable will be used as the left side.
	 * @param rightSideCF - if the right side is ComplexFunction then this parameter is the right side of the returned CF.
	 * @param rightSidePolynom - if the right side is Polynom then this parameter is the right side of the returned CF.
	 */
	@Override
	public function initFromString(String s) {
		if(!s.contains("(") && !s.contains(")") && !s.contains(",")) {
			//In this case the given string input is not a CF on it's own, but a Polynom(or Monom).
			//So we make a None Operator CF that has the input as it's left value
			ComplexFunction helper = new ComplexFunction();
			helper.setOp("None");
			helper.left = new Polynom(s);
			return helper;
		}

		ComplexFunction helper = new ComplexFunction();
		int LocationOfFirstBracket = s.indexOf("("); //location of first (
		if(LocationOfFirstBracket == -1) {throw new RuntimeException("No brackets found. Invalid input!");}

		String operatorString = s.substring(0,LocationOfFirstBracket);
		helper.Op = setOp(operatorString);

		int NumOfBrackets = 1;
		boolean Left_Flag = false; //left is complex function
		int mainCommaIndex = -1;
		char currentChar;
		for (int i = LocationOfFirstBracket+1; i < s.length(); i++) {
			currentChar = s.charAt(i);
			if(currentChar=='(') {
				NumOfBrackets++;
				Left_Flag = true;
			}
			if(currentChar==')') {
				NumOfBrackets--;}
			if(currentChar==',') {
				if(NumOfBrackets==1) {
					mainCommaIndex = i;
					i = s.length();} //done
			}
		}
		if(Left_Flag) { //if left side is CF
			function leftSideCF = new ComplexFunction();
			leftSideCF = initFromString(s.substring(LocationOfFirstBracket+1, mainCommaIndex));
			helper.left = leftSideCF;
		}
		else { //else left side is a Polynom
			Polynom leftSidePolynom = new Polynom(s.substring(LocationOfFirstBracket+1, mainCommaIndex));
			helper.left = leftSidePolynom;
		}
		if(mainCommaIndex!=-1) { //only if a mainCommaIndex was found, there is a right side to the CF
			String partial = s.substring(mainCommaIndex+1, s.length()-1);
			int nextCommaIndex = partial.indexOf(','); //checking if right side is also a CF	
			if (nextCommaIndex!=-1) { //right side is CF
				function rightSideCF = new ComplexFunction();
				rightSideCF = initFromString(partial);
				helper.right = rightSideCF;

			}
			else { //in this case right side is just a polynom
				Polynom rightSidePolynom = new Polynom(partial);
				helper.right = rightSidePolynom;
			}
		}
		return helper;
	}
	
	/*
	 * @returns a string that represent this ComplexFunction.
	 */
	@Override
	public String toString() {
		return toString2(this);
	}
	/*
	 * @param s - the given CF, that's toString value was asked.
	 * @returns a string that represent the given ComplexFunction.
	 */
	public String toString2(ComplexFunction s) {
		String left;
		String right;
		
		if(s.left instanceof ComplexFunction) {
			ComplexFunction temp = (ComplexFunction) s.left;
			left = temp.toString();
		}
		else if(s.left instanceof Polynom) {
			left = ((Polynom) s.left).toString();
		}
		else{
			left = ((Monom) s.left).toString();
		}
		
		if(s.right==null) {right="";}
		else if(s.right instanceof ComplexFunction) {
			ComplexFunction temp2 = (ComplexFunction) s.right;
			right = temp2.toString();
		}
		else if(s.right instanceof Polynom) {
			right = ((Polynom) s.right).toString();
		}
		else{
			right = ((Monom) s.right).toString();
		}
		
		return s.Op + "(" + left + "," + right + ")";
		
		 
	}
	/*
	 * @returns a copy of this complexfunction.
	 */
	@Override
	public function copy() {
		String s = this.toString();
		function f = initFromString(s);
		return f;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true; //case of actual same object
		if(this.toString().contentEquals(obj.toString())) return true; //case of same string
		if(!(obj instanceof function)) {return false;} //this is a CF, if the other isn't a function and it's string isn't equal to this, they are not equal.
		
		
		function compareMe;
		if(obj instanceof Monom) {compareMe = (Monom) obj;}
		else if (obj instanceof Polynom) {compareMe = (Polynom) obj;}
		else {compareMe = (ComplexFunction) obj;}
		double randomNumber;
		for (int i = -1000000; i < 1000000; i=i+2000) {
			randomNumber = Math.random()*2000 + i;
			double mine = this.f(randomNumber);
			double his = compareMe.f(randomNumber);
			double result = Math.abs(mine-his);
			if(result>0.001) {
				return false;
			}

		}
		return true;
	}
	
}
