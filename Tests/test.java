package Tests;
import myMath.Polynom;
public class test {
	public static void main(String[] args) {
		Polynom test = new Polynom("-3x^2+12");
		System.out.println(test);
		System.out.println(test.root(-6, -1, 0.1));
		Polynom test2 = new Polynom("x^2+-3");
		System.out.println(test2.area(0, 3, 0.01));
	}

}
