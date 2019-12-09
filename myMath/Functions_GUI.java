package myMath;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Functions_GUI implements functions {
	public ArrayList<function> group = new ArrayList<function>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
    private static int Width;
	private static int Height;
    private static int Resolution; 
    private static Range  Range_X;
    private static Range Range_Y;
	@Override
	public boolean add(function arg0) {
		//		if(arg0 instanceof Polynom) {
		////			Polynom tempPoly = new Polynom((Polynom) arg0);
		////			this.group.add(tempPoly);
		//		}
		//		else if(arg0 instanceof Monom) {
		//			this.group.add((Monom)arg0);
		//		}
		//		else {
		//			this.group.add((ComplexFunction)arg0);
		//		}
		//		System.out.println("I am here " + arg0.toString());
		this.group.add(arg0);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		Iterator<? extends function> itr = arg0.iterator();
		while(itr.hasNext()) {
			group.add(itr.next());
		}
		return true;
	}

	@Override
	public void clear() {
		group.clear();		
	}

	@Override
	public boolean contains(Object arg0) {//TODO check if this works
		if(!(arg0 instanceof function)) {
			throw new RuntimeException("Was asked to check if a Functions_GUI object contains a non-function object - illegal");
		}
		Iterator<function> itr = this.iterator();
		boolean answer = false;
		while(itr.hasNext() && !answer) {
			function current = itr.next();
			if(current==arg0) {return true;} //in case they point at the same object
			
			//Cast the current function object
			if(current instanceof Monom) {
				Monom temp = (Monom) current;
				System.out.println("Now comparing with " + temp.toString());
				if(temp.toString().contentEquals(arg0.toString())) {answer=true;}
			}
			else if(current instanceof Polynom) {
				Polynom temp = (Polynom) current;
				System.out.println("Now comparing with " + temp.toString());
				if(temp.toString().contentEquals(arg0.toString())) {answer=true;}
			}
			else {
				ComplexFunction temp = (ComplexFunction) current;
				System.out.println("Now comparing with " + temp.toString());
				if(temp.toString().contentEquals(arg0.toString())) {answer=true;}
			}
			
//			System.out.println("Now comparing with " + temp.toString());
			
//			if(temp.toString().contentEquals(arg0.toString())) {answer=true;}
		}
		return answer;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> itr = arg0.iterator();
		boolean now = true;

		try {
			while(itr.hasNext() && now) {
				now = this.contains(itr.next());
			}
		}
		catch(Exception e) {
			throw e;
		}
		return now;
	}

	@Override
	public boolean isEmpty() {
		return this.group.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return this.group.iterator();
	}

	@Override
	public boolean remove(Object arg0) { //return true if the collection was changed
		if(!(arg0 instanceof function)) {
			return false;
		}
		boolean contains = this.contains(arg0);
		if (!contains) return false;
		this.group.remove(arg0);
		return true;

	}

	@Override
	public boolean removeAll(Collection<?> arg0) { //return true if the collection was changed (i.e. at least one of the objects in the provided Collection was removed from the Functions_GUI object)
		Iterator<?> itr = arg0.iterator();
		int howManyWereRemoved = 0;
		while(itr.hasNext()) {
			if (this.remove(itr.next())) howManyWereRemoved++;
		}
		if(howManyWereRemoved!=0) return true;
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		ArrayList<function> temp = new ArrayList<function>();
		Iterator<?> itr = arg0.iterator();
		Object tempObject;
		while(itr.hasNext()) {
			tempObject = itr.next();
			if(this.contains(tempObject)) {
				temp.add((function) tempObject);
			}
		}
		if (temp.size() == this.group.size()) return false;
		this.group = temp;
		return true;
	}

	@Override
	public int size() {
		return this.group.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initFromFile(String file) throws IOException {
		String line = null;
		try {
			FileReader fileReaderr = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReaderr);
			while((line = bufferedReader.readLine()) != null) {
				line = line.replaceAll(" ", "");
				
				if(line.contains(",")) {
					ComplexFunction newCF = new ComplexFunction();
					newCF = (ComplexFunction) newCF.initFromString(line);
					this.add(newCF);
				}
				else { //this line is Polynom or Monom. We will make it a polynom
					Polynom newPoly = new Polynom(line);
					this.add(newPoly);

				} //end Polynom/Monom line case
			} //end While   
			bufferedReader.close();

		}
		catch(FileNotFoundException ex){throw ex;}
		catch(IOException ex) {throw new RuntimeException("Error reading file " + file + ". Cause described as " + ex.getMessage());}

	}

	@Override
	public void saveToFile(String file) throws IOException {
		   FileWriter fr = null;
	       BufferedWriter br = null;
	       //String dataWithNewLine=data+System.getProperty("line.separator");
	       try{
	           fr = new FileWriter(file);
	           br = new BufferedWriter(fr);
	           for(int i = this.group.size()-1; i>0; i--){
	               br.write(this.group.get(i).toString());
	               br.newLine();
	           }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                br.close();
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		/*StdDraw.setCanvasSize(width,height);
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(ry.get_min(),ry.get_max());
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		double X_step = ((Math.abs(rx.get_max()) + Math.abs(rx.get_min()))/resolution);
		double x0 =rx.get_min();
		double x1 = x0+X_step;
		double y0;
		double y1;
		StdDraw.setPenRadius(0.007);
		for(int j=0; j<this.group.size(); j++) {
			function currentFunction = this.group.get(j);
			StdDraw.setPenColor();
			for(int i=0; i<resolution;i++) {
				y0 = currentFunction.f(x0);
				y1 = currentFunction.f(x1);
				StdDraw.line(x0, y0, x1, y1);
				x0 = x1+X_step;
				x1 = (x0+X_step);
			}
			x0 =rx.get_min();
			x1 = x0+X_step;
		}*/
		
		StdDraw.setCanvasSize(width, height);
		
		
		int size = this.group.size();
		double[] xArray = new double[resolution+1];
		double[][] yArray = new double[size][resolution+1];
		double x_step = (rx.get_max()-rx.get_min())/resolution;
		double x0 = rx.get_min();
		for (int i=0; i<=resolution; i++) {
			xArray[i] = x0;
			for(int a=0;a<size;a++) {
				yArray[a][i] = this.group.get(a).f(xArray[i]);
			}
			x0+=x_step;
		}
		
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		StdDraw.setPenRadius(0.007);
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);// set color.
			System.out.println(a+") "+Colors[a]+"f(x)= "+this.group.get(a).toString());
			for (int i = 0; i < resolution; i++) {
				StdDraw.line(xArray[i], yArray[a][i], xArray[i+1], yArray[a][i+1]);
			}
		}	
	}//end drawFunctions
	@Override
	public void drawFunctions(String json_file) {
		 JSONObject jsonObject;
		try {
			 jsonObject = (JSONObject) readJsonSimpleDemo(json_file);
			 Width = Long.valueOf((long) jsonObject.get("Width")).intValue();    
		     Height = Long.valueOf((long)jsonObject.get("Height")).intValue();  
		     Resolution = Long.valueOf((long)jsonObject.get("Resolution")).intValue();  
		     String rangex = String.valueOf(jsonObject.get("Range_X"));   
		     rangex.substring(1,rangex.length()-1);
		     Range rx = new Range(0,0); 
		     
		     for(int i=0;i<rangex.length();i++) {
		       	if(rangex.charAt(i) == ',') {
		       		rx = new Range((int)Integer.parseInt(rangex.substring(1,rangex.indexOf(','))),(int)Integer.parseInt(rangex.substring(rangex.indexOf(',')+1,rangex.length()-1)));
		       	}
		     }
		     String rangey = String.valueOf(jsonObject.get("Range_Y")); 
		     Range ry= new Range(0,0);
		     for(int i=0;i<rangey.length();i++) {
		       	if(rangex.charAt(i) == ',') {
		       		ry = new Range((int) Integer.parseInt(rangex.substring(1,rangex.indexOf(','))),(int) Integer.parseInt(rangex.substring(rangex.indexOf(',')+1,rangex.length()-1)));
		        }
		     }
		     drawFunctions(Width,Height,rx,ry,Resolution);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
	public static Object readJsonSimpleDemo(String filename) throws Exception {
	    FileReader reader = new FileReader(filename);
	    JSONParser jsonParser = new JSONParser();
	    return jsonParser.parse(reader);
	}
	/*
	 {
	"Width":400,
	"Height":400,
	"Resolution":1500,
	"Range_X":[-10,10],
	"Range_Y":[-10,10]
	}
	 */
	

}
