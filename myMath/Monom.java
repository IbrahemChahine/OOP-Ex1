package myMath;
import java.util.Comparator;

import javax.management.RuntimeErrorException;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Ibrahem Chahine and Ofir Peller.
 *
 */
public class Monom implements function{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4678829995977516702L;
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	/*
	 * The fuction create a new Monom.
	 * @param a the coefficient of the Monom.
	 * @param b the power of the Monon.
	 */
	public Monom(double a, int b){ 
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {// copy constructor.
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {// get _coefficient.
		return this._coefficient;
	}
	public int get_power() {// get _power. 
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	@Override
	public double f(double x) {// returns the value of y for a specific x. 
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {// Checks if the Monom is eqaul to 0.
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************
	/**
	 * The function create a Monom from a Monom represented as a string.
	 * Lets assume that s is the sting that represents a monom.
	 * s should be represented as ax^b, such that a is the coefficient and it a double and b is the power and its an int.
	 * Example:
	 * 		s = {"x^2","5x^7","-11111x^9","10.5x","10"}.
	 */
	public Monom(String s) {
		s = s.replaceAll(" ", ""); //remove spaces
		if(s.isEmpty()) {
			System.err.println("Entered string is empty. Monom will be created as 0x^0.");
			this.set_coefficient(0);
			this.set_power(0);
			return;
		}
		if(s.endsWith("^")) {
			System.err.println("Invalid power! Monom will be created empty, and throwing IllegalArgumentException!");
			throw new IllegalArgumentException();
		}

		int Xposition = -1;
		boolean KeepWorking = true;
		for (int i = 0; i < s.length() && KeepWorking; i++) {
			String sub = s.substring(i, i+1).toUpperCase();
			if(sub.equals("X"+"")) {
				Xposition = i;
				KeepWorking = false;
			}
		}

		if(Xposition<s.length()-1) { 
			if(Xposition!=-1 && s.charAt(Xposition+1)!='^') {
				System.err.println("Invalid input! Created Monom will be empty!");
				return;
			}
		}

		//if Xposition remains -1, no X was found. This means this monom's power is 0.
		//if Xposition is 0 this means the coefficient is 1.
		//else, I found X somewhere else. Up until that position is the coefficient.
		//After that and the symbol '^', if it's there, is the power of the monom.

		switch (Xposition) {

		case -1:
			try {
				this.set_coefficient(Double.parseDouble(s));
			}
			catch (Exception e) {
				System.err.println("Invalid coefficient! Monom will be created empty, and throwing IllegalArgumentException!");
				throw new IllegalArgumentException();
			}

			this.set_power(0);
			break;
			//In this case Xposition is -1, meaning 'X' isn't in the string, and so all of it is the coefficient.

		case 0: //no coefficient stated before X, so it's 1.
			if (s.length()==1) { //input is "X"
				this.set_coefficient(1);
				this.set_power(1);
				//Must have this case - otherwise the code will try to access the string at a place greater then it's length
				//(Out of bounds)
			}
			else {
				try {
					this.set_power(Integer.parseInt(s.substring(2, s.length())));
					this.set_coefficient(1);
				}

				catch(Exception e){
					System.err.println("Invalid coefficient! Monom will be created empty, and throwing IllegalArgumentException!");
					throw new IllegalArgumentException();
				}

				//In this case X is the first char in the string. Thus coefficient is 1.
				//The rest of the string is the power. I am parsing the relevant substring and parsing/casting it to int.
			}
			break; //end case 0

		default:
			String coefficient = s.substring(0,Xposition);
			if (coefficient.equals("-")) {
				this.set_coefficient(-1);
			}
			else {
				try {
					this.set_coefficient(Double.parseDouble(coefficient));
				}
				catch(Exception e) {
					throw new RuntimeException("Can't use the value given as coefficient. Please check your input.");
				}

			}
			
			try { //now set power

				int power;
				if (Xposition+2>=s.length()) {power = 1;} //If X is at the end of the string, the power is 1.

				else {power = Integer.parseInt(s.substring(Xposition+2,s.length()));} //else, use substring to extract the power and convert it to integer.

				this.set_power(power);
			} catch (Exception e) {
				System.err.println("Invalid coefficient or power! Monom will be created empty, and throwing IllegalArgumentException!");
				throw new IllegalArgumentException();
			}


			//In the default case Xposition is somewhere within the string.
			//Until Xposition is the coefficient. After that is the power.
			//As it is possible there will not be a '^' symbol, I make sure to check the string's length.
			//If it is greater then Xposition+2, there is a '^' symbol and numbers after to indicate the power.
			//Otherwise the power is 1.
		}
	}
	/**
	 * the function add two Monoms with thew same power.
	 * @param m1 the Monom that is added.
	 * 
	 */
	public void add(Monom m) {
		if(this._power == m._power) {
			this._coefficient += m._coefficient;
			int temp = (int)(this._coefficient*10000000);
			this._coefficient = (double)temp/10000000; //compensating for up to 0.0000001 [EPSILON] mistake
			
		} 
		else {
			throw new RuntimeException("Inorder to add a Monom the power of the Monoms should be the same.");
		}
	}
	/**
	 * The function multiplys two Monoms
	 * @param d the monom that is multiplyed.
	 */
	public void multiply(Monom d) {
		this.set_coefficient(this.get_coefficient()*d.get_coefficient());
		this.set_power(this.get_power()+d.get_power());
		if(this._coefficient == 0) {
			this._power = 0;
		}
	}
	/**
	 * The function checks if the Monoms are equal.
	 * @param l - the monom that is checked.
	 */
	public boolean equals(Monom l) {
		double co1 = this.get_coefficient();
		double co2 = l.get_coefficient();
		if(co1==0) {
			if(co2==0) {return true;}
			else {return false;}
		}
		int temp1 = (int)(co1*10000000);
		co1 = (double)(temp1/10000000);
		int temp2 = (int)(co2*10000000);
		co2 = (double)(temp2/10000000); //compensating for up to 0.0000001 [EPSILON] mistake
		
		if(co1 == co2 && get_power() == l.get_power()){
			return true;
		}
		else {
			return false;
		}
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Monom) {
			return this.equals((Monom)obj);
		}
		else if(obj instanceof Polynom_able) {
			return ((Polynom)obj).equals(this);
		}
		else {throw new RuntimeException("Was asked if a non Monom Object is equals to a Monom - illegal!");}
	}
	
	
	/*
	 * returns a String represented as a monom.
	 */
	@Override
	public String toString() {
		if(this.isZero()) {return "0";}
		String ans;
		if(this.get_coefficient()==0) {return "";}
		int power = this.get_power();
		if (power==0) {
			ans = Double.toString(this.get_coefficient());
		}
		else {
			ans = (this.get_coefficient() + "X^" + power);
		}

		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}
	@Override
	public function copy() {
		Monom temp = new Monom(this);
		return temp;
	}


}
