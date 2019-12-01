package myMath;
public class ComplexFunction implements complex_function {
	private function left;
	private function right;
	private Operation Op;
	
	public ComplexFunction() {
		
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
	}
	@Override
	public void div(function f1) {
	}
	@Override
	public void max(function f1) {
	}
	@Override
	public void min(function f1) {
	}
	@Override
	public void comp(function f1) {
	}
	@Override
	public function left() {
		return null;
	}
	@Override
	public function right() {
		return null;
	}
	@Override
	public Operation getOp() {
		return null;
	}
	@Override
	public double f(double x) {
		return 0;
	}
	@Override
	public function initFromString(String s) {
		ComplexFunction helper = new ComplexFunction();
		int LocationOfFirstBracket = s.indexOf("("); //location of first (
		if(LocationOfFirstBracket == -1) {throw new RuntimeException("No brackets found. Invalid input!");}
		
		String operatorString = s.substring(0,LocationOfFirstBracket);
		helper.Op = Operation.valueOf(operatorString);
		
		int NumOfBrackets = 1;
		boolean Left_Flag = false; //left is complex function
		int mainCommaIndex = -1;
		for (int i = LocationOfFirstBracket+1; i < s.length(); i++) {
			if(s.charAt(i)=='(') {
				NumOfBrackets++;
				Left_Flag = true;
			}
			if(s.charAt(i)==')') {NumOfBrackets--;}
			if(s.charAt(i)==',') {
				if(NumOfBrackets==1) {mainCommaIndex = i;}
				i = s.length(); //done
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
		return this.Op + "(" + this.left.toString() + " , " + this.right.toString() + ")";
	}
	@Override
	public function copy() {
		String s = this.toSrting();
		function f = initFromString(s);
		return f;
	}

}
