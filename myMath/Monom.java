package myMath;
import java.util.Comparator;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Ibrahem Chahine.
 *
 */
public class Monom implements function{
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
		s = s.toLowerCase();
		if(s.contains("x")&&s.charAt(s.length()-1)!='x'&&!s.contains("^")) {
			throw new RuntimeException("You inputed an invalid string that present a Monom");
		}
		if((s.contains("x"))&&s.contains("^")&&(s.indexOf("x")>s.indexOf("^"))) {
			throw new RuntimeException("You inputed an invalid string that present a Monom");
		}
		if((s.contains("x"))&&s.contains("^")&&(s.charAt(s.length()-1))=='^') {
			throw new RuntimeException("You inputed an invalid string that present a Monom");
		}
		if((s.contains("x"))&&s.contains("^")&&(s.charAt(s.indexOf("^")-1)!='x')) {
			throw new RuntimeException("You inputed an invalid string that present a Monom");
		}
		if(s.length()==1) {
			if(s.charAt(0)=='x') {
				this.set_coefficient(1);
				this.set_power(1);
			}
			else {
				double a = Double.valueOf(s);
				this.set_coefficient(a);
				this.set_power(0);
			}
		}
		else if(s.length()==2 && s.charAt(0)=='-' && s.charAt(1) == 'x') {
			this.set_coefficient(-1);
			this.set_power(1);
		}
		else {
			String str = "";
			int n=0;
			if(s.charAt(0)=='x') {
				s = "1" + s;
			}
			if(s.charAt(0)=='-'&&s.charAt(1)=='x') {
				String s2 = s;
				s = s2.substring(0,0)+"-1"+s2.substring(1);
			} 
			for(int i = 0; i<s.length(); i++) {
				if(s.charAt(i) == 'x') {
					n=i;
					break;
				}
				else if (s.charAt(i) == '*') {;}
				else {
					str = str + s.charAt(i);
				}
			}
			double a = Double.valueOf(str);
			this.set_coefficient(a);
			str = "";
			if(n==0) {
				this.set_power(0);
			}
			else {
				for(int i = n; i<s.length(); i++) {
					if(s.charAt(i) == 'x') {;}
					else if(s.charAt(i) == '^') {;}
					else {
						str = str + s.charAt(i);
					}
				}
				if(str=="") {
					this.set_power(1);
				}
				else {
					int b = Integer.parseInt(str);
					this.set_power(b);
				}
			}
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
		if(this.get_coefficient() == l.get_coefficient() && get_power() == l.get_power()){
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * returns a String represented as a monom.
	 */
	public String toString() {
		String s="";
		if(this.get_power()==0) { // when the power is 0 case 
			if(this.get_coefficient()>0)
				s+=this.get_coefficient();
			else if(this.get_coefficient()<0)
				s+=""+this.get_coefficient()+"";
			else s+="0";
		}

		else {
			if(this.get_coefficient()>0) // when the power is not 0 case
				s+=this._coefficient+"*"+"x"+"^"+this._power;
			else if(this.get_coefficient()<0)
				s+=this._coefficient+"*"+"x"+"^"+this._power;
			else s+="0";
		}
		return s;
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
