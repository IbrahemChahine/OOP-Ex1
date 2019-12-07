package myMath;

public class ComplexFunction implements complex_function {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1785701227096562369L;
	/**
	 * 
	 */
	private function left;
	private function right;
	private Operation Op;

	public ComplexFunction() {
		this.left = new Polynom();
		this.right = null;
		this.Op = Operation.None;
	}
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
	public ComplexFunction(Operation OP, function p1, function p2) {
		this.left = p1;
		this.right = p2;
		this.Op = OP;
	}

	public ComplexFunction(function left) {
		this.left = left;
		this.Op = Operation.None;
	}
	/** Add to this complex_function the f1 complex_function 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
	@Override
	public void plus(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Plus;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Plus;
		}
	}
	@Override
	public void mul(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Times;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Times;
		}
	}
	@Override
	public void div(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Divid;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Divid;
		}
	}
	@Override
	public void max(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Max;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Max;
		}
	}
	@Override
	public void min(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Min;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Min;
		}
	}
	@Override
	public void comp(function f1) {
		ComplexFunction helper = new ComplexFunction();
		if(this instanceof ComplexFunction) {
			helper= (ComplexFunction)this.copy();
			this.left = helper;
			this.right = f1;
			this.Op = Operation.Comp;
		}
		else {
			function helperPolynom = (Polynom) this.copy();
			this.left = helperPolynom;
			this.right = f1;
			this.Op = Operation.Comp;
		}
	}
	@Override
	public function left() {
		return this.left;
	}
	@Override
	public function right() {
		return this.right;
	}
	@Override
	public Operation getOp() {
		return this.Op;
	}
	@Override
	public double f(double x) {

		if(this.Op.name()=="None") return 0;
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


	@Override
	public function initFromString(String s) {

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
	public String toSrting() {
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
//		return toString(this);
		
	}
	//new, not on github.
	public String toString(ComplexFunction s) {
		String left;
		String right;
		
		if(s.left instanceof ComplexFunction) {
			left = toString((ComplexFunction) s.left);
		}
		else if(s.left instanceof Polynom) {
			left = ((Polynom) s.left).toString();
		}
		else{
			left = ((Monom) s.left).toString();
		}
		
		if(s.right==null) {right="";}
		else if(s.right instanceof ComplexFunction) {
			right = toString((ComplexFunction) s.right);
		}
		else if(s.right instanceof Polynom) {
			right = ((Polynom) s.right).toString();
		}
		else{
			right = ((Monom) s.right).toString();
		}
		
		return s.Op + "(" + left + "," + right + ")";
		
		
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
	}
	@Override
	public function copy() {
		String s = this.toSrting();
		function f = initFromString(s);
		return f;
	}
	
	
	public boolean equals(Object obj) {
		if(this.toString().contentEquals(obj.toString())) return true;
		
		
		
		return false;
	}
	
}
