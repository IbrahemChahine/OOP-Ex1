package myMath;

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
	 * A constructor that builds a complex function with String as the Operation and a peer of functions for the left and right.
	 * @param string - A string represented as a Operation. 
	 * @param p1 - A function for the left side of this complex function, this function can be ComplexFunction or Polynom or Monom.
	 * @param p2 - A function for the left side of this complex function, this function can be ComplexFunction or Polynom or Monom or null.
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
	 * A constructor that builds a complex function with the Operation and a peer of functions for the left and right.
	 * @param OP - The Operation. 
	 * @param p1 - A function for the left side of this complex function, this function can be ComplexFunction or Polynom or Monom.
	 * @param p2 - A function for the left side of this complex function, this function can be ComplexFunction or Polynom or Monom or null.
	 */
	public ComplexFunction(Operation OP, function p1, function p2) {
		this.left = p1;
		this.right = p2;
		this.Op = OP;
	}
	/*
	 * A constructor that builds a complex function  with the left .
	 * @param left - A function for the left side of this complex function, this function can be ComplexFunction or Polynom or Monom.
	 * The Operation of this complex function is None.
	 */
	public ComplexFunction(function left) {
		this.left = left;
		this.Op = Operation.None;
	}
	/** Add to this complex_function the f1 complex_function 
	 * @param f1 the complex_function which will be added to this complex_function.
	 * @param flag - This parameter helps us to know if the this and f1 are eqaul.
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
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	/** multiply this complex_function and the given f1 complex_function 
	 * @param f1 the complex_function which will be multiplyed with this complex_function.
	 */
	@Override
	public void mul(function f1) {
		boolean flag = false;
		if(this==f1) {flag=true;}
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	/** divide this complex_function and the given f1 complex_function 
	 * @param f1 the complex_function which will be divided with this complex_function.
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
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	/** max between this complex_function and the given f1 complex_function 
	 * @param f1 the complex_function which will be compared with this complex_function. to compute  max  
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
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	/** min between this complex_function and the given f1 complex_function 
	 * @param f1 the complex_function which will be compared with this complex_function. to compute  min  .
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
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	/** min between this complex_function and the given f1 complex_function 
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function.
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
			if(flag) { //if you add something to itself this code is needed, or else it will create an infinit loop
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
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return this.Op - the OPeration of this.
	 */
	@Override
	public Operation getOp() {
		return this.Op;
	}
	/*
	 * This method computes the value of the complex function with value x.
	 */
	@Override
	public double f(double x) {
		if(this.Op.name()=="None" ){
			if(this.right!=null){throw new RuntimeException("Was asked to calculate f(x) for a ComplexFunction that has None as Operator and has both a Left and Right. Illegal!");}
			else {return this.left.f(x);}
		}
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
	 * @param LocationOfFirstBracket - the location of the First Bracket
	 * @param operatorString - A string that represents the Operation of the function.
	 * @param NumOfBrackets - the number of brackets found in the string.
	 * @param mainCommaIndex - the index of the main comma in the function, Example = say p and q is functions then 
	 * consider { "plus(p,q)"_} we get that the main comma index is 9.
	 * @param Left_Flag - this boolean value is true is the left function in the string is a ComplexFunction.
	 * @param leftSidePolynom - if Left_Flag is false then left is a polynom so this parameter is the left.
	 * @param rightSideCF - if the right side is ComplexFunction then this parameter is the right.
	 * @param rightSidePolynom - if the right side is Polynom then this parameter is the right.
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
	/*public String toSrting() {
		String left;
		String right;
		
		if(this.left instanceof ComplexFunction) {
			left = toString((ComplexFunction) this.left);
		}
		else if(this.left instanceof Polynom) {
			left = ((Polynom) this.left).toString();
		}
		else{
			left = ((Monom) this.left).toString();
		}
		
		if(this.right==null) {right="";}
		else if(this.right instanceof ComplexFunction) {
			right = toString((ComplexFunction) this.right);
		}
		else if(this.right instanceof Polynom) {
			right = ((Polynom) this.right).toString();
		}
		else{
			right = ((Monom) this.right).toString();
		}
		
		return this.Op + "(" + left + "," + right + ")";
	}*/
	//new, not on github.
	/*
	 * @returns a string that represent this ComplexFunction.
	 */
	@Override
	public String toString() {
		return toString2(this);
	}
	public String toString2(ComplexFunction s) {
//		if(s.left instanceof ComplexFunction && s.right instanceof ComplexFunction) {
//			return s.Op + "(" + toString((ComplexFunction) s.left) + "," + toString((ComplexFunction) s.right) + ")";
//		}
//		else if(s.left instanceof ComplexFunction) { // we need to add right = NULL case
//			return s.Op+"("+toString((ComplexFunction) s.left)+","+s.right.toString() + ")";
//		}
//		else if(s.right instanceof ComplexFunction) {
//			return s.Op +"("+ s.left.toString() +","+ toString((ComplexFunction) s.right) +")";
//		}
//		return s.Op + "(" + s.left.toString() + " , " + s.right.toString() + ")";
		
		String left;
		String right;
		
		if(s.left instanceof ComplexFunction) {
			ComplexFunction temp = (ComplexFunction) s.left;
			left = temp.toString();
//			left = toString2((ComplexFunction) s.left);
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
//			right = toString2((ComplexFunction) s.right);
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
