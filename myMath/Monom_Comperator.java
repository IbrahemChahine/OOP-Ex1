package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public Monom_Comperator() {;}
	/**
	 * This method compares between two Monoms.
	 * @param o1: the first monom;
	 * param o2: the second monom;
	 * param dp: the difference between the powers of the Monon.
	 */
	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		return dp;
	}
}
