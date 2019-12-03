package myMath;
import java.awt.Color;
import java.awt.Font;

import myMath.StdDraw;

public class FunctionGraph {
	public static void main(String[] args) {
		// number of line segments to plot
		/*int n = 100;
		double maxY = 2.0, minY = -2.0;
		// the function y = sin(4x), sampled at n+1 points
		// between x = 0 and x = pi
		double[] x = new double[n+1];
		double[] y = new double[n+1];
		for (int i = 0; i <= n; i++) {
			x[i] = Math.PI * i / n;
			y[i] = Math.sin(4*x[i]);
		}		
		// rescale the coordinate system
		StdDraw.setXscale(0, Math.PI);
		StdDraw.setYscale(minY, maxY);
		
		//////// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = 0; i <= n; i=i+10) {
			StdDraw.line(x[i], minY, x[i], maxY);
		}
		//////// horizontal  lines
		for (double i = minY; i <= maxY; i=i+0.5) {
			StdDraw.line(0, i, Math.PI, i);
		}
		//////// x axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(0, y[n/2], Math.PI, y[n/2]);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (int i = 0; i <= n; i=i+10) {
			StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-n/2));
		}
		//////// y axis
		StdDraw.line(x[n/2], minY, x[n/2], maxY);
		for (double i = minY; i <= maxY; i=i+0.5) {
			StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
		}

		// plot the approximation to the function
		for (int i = 0; i < n; i++) {
			StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
		}
		StdDraw.setPenColor(Color.RED);
		StdDraw.setPenRadius(0.01);
		StdDraw.point(x[n/2], 1);
		*/
		Polynom p1 = new Polynom("x^2");
		Polynom p2= new Polynom("x^3");
		ComplexFunction CF = new ComplexFunction("plus",p1,p2);
		
		StdDraw.setCanvasSize(400,400);
		StdDraw.setXscale(-10, 10);
		StdDraw.setYscale(-10, 10);
		StdDraw.line(-10,0,10,0);
		StdDraw.line(0,-10,0,10);
		double x1 =-10;
		StdDraw.setPenRadius(0.007);
		for(int i=0; i<1001;i++) {
			double x0 = x1+0.007;
			x1 = (x0+0.007);
			double y0 = CF.f(x0);
			double y1 = CF.f(x1);
			StdDraw.line(x0, y0, x1, y1);
			
			//StdDraw.point(x1, y1);
		}
		
	}

}