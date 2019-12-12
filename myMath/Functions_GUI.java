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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This interface represents a collection of mathematical functions
 *  which can be presented on a GUI window and can be saved (and load) to file. 
 *  @author Ibrahem Chahine and Ofir Peller.
 */


public class Functions_GUI implements functions {
	public ArrayList<function> group = new ArrayList<function>();
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
	@Override
	public boolean add(function arg0) {
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
	public boolean contains(Object arg0) {
		return group.contains(arg0);
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
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}
	/*
	 * This method build's a new Collection of function with string represented as a function
	 * @param file  The given file that contains strings of functions.
	 * @param line helps us to build the functions.
	 * @param fileReaderr a class in java to read files.
	 * @param bufferedReader gives us the lines in the files.
	 */
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
	/**
	 * This function save to file a the collection of the function.
	 * @param fr the file reader.
	 * @param br the BufferedWriter.
	 */
	@Override
	public void saveToFile(String file) throws IOException {
		FileWriter fr = null;
		BufferedWriter br = null;
		try{
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			for(int i = this.group.size()-1; i>=0; i--){
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
	/*
	 * Draws all the functions in the collection in a GUI window using the
	 * given parameters for the GUI window and the range & resolution
	 * @param width - the width of the window - in pixels
	 * @param height - the height of the window - in pixels
	 * @param rx - the range of the horizontal axis
	 * @param ry - the range of the vertical axis
	 * @param resolution - the number of samples with in rx: the X_step = rx/resulution
	 */
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);


		int size = this.group.size();
		double[] xArray = new double[resolution+1];
		double[][] yArray = new double[size][resolution+1];
		double x_step = (rx.get_max()-rx.get_min())/resolution;
		double x0 = rx.get_min();
		for (int i=0; i<=resolution; i++) {
			xArray[i] = x0;
			for(int a=0;a<size;a++) {
				try {
					yArray[a][i] = this.group.get(a).f(xArray[i]);
				}
				catch(Exception e) {//in case if dividing by 0
					yArray[a][i] = 214748364;
				}

			}
			x0+=x_step;
		}

		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(),0,rx.get_max(),0);
		StdDraw.line(0,ry.get_min(),0,ry.get_max());
		StdDraw.setPenRadius(0.006);
		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);// set color.
			System.out.println(a+") "+Colors[a]+"f(x)= "+this.group.get(a).toString());
			for (int i = 0; i < resolution; i++) {
				if(yArray[a][i]==214748364 || yArray[a][i+1]==214748364) {
					continue;
				}
				
				StdDraw.line(xArray[i], yArray[a][i], xArray[i+1], yArray[a][i+1]);

			}
		}	
	}//end drawFunctions
	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
	@Override
	public void drawFunctions(String json_file) {
		String rangex;
		String rangey;
		int Width;
		int Height;
		int Resolution; 
		JSONObject jsonObject;
		try {jsonObject = (JSONObject) readJsonSimpleDemo(json_file);}
		catch(Exception e) {throw new RuntimeException("Error reading the Json file. Please make sure it exists and that you gave the correct path to it.");}

		try {
			Width = Long.valueOf((long) jsonObject.get("Width")).intValue();    
			Height = Long.valueOf((long)jsonObject.get("Height")).intValue();  
			Resolution = Long.valueOf((long)jsonObject.get("Resolution")).intValue();
			rangex = String.valueOf(jsonObject.get("Range_X"));
			rangey = String.valueOf(jsonObject.get("Range_Y"));
		}
		catch(Exception e) {throw new RuntimeException("Invalid or missing values form Json file.");}

		try {
			rangex.substring(1,rangex.length()-1);
		}
		catch(Exception e) {throw new RuntimeException("Range_X value too short, it is illegal");}

		int commaCounter=0;
		for(int i=0;i<rangex.length();i++) {
			if(rangex.charAt(i)==',') {commaCounter++;}
		}
		if(commaCounter>1 || commaCounter==0 || rangex.charAt(rangex.length()-1)==',') {throw new RuntimeException("Illegal value given for rangeX");}
		int commaLocation = rangex.indexOf(",");

		Range rx = new Range(0,0); 
		rx = new Range((int)Integer.parseInt(rangex.substring(1,commaLocation)),(int)Integer.parseInt(rangex.substring(commaLocation+1,rangex.length()-1)));


		commaCounter=0;
		for(int i=0;i<rangey.length();i++) {
			if(rangey.charAt(i)==',') {commaCounter++;}
		}
		if(commaCounter>1 || commaCounter==0 ||rangey.charAt(rangey.length()-1)==',') {throw new RuntimeException("Illegal value given for rangeY");}
		commaLocation = rangey.indexOf(",");
		Range ry= new Range(0,0);
		ry = new Range((int) Integer.parseInt(rangey.substring(1,commaLocation)),(int) Integer.parseInt(rangey.substring(commaLocation+1,rangey.length()-1)));
		drawFunctions(Width,Height,rx,ry,Resolution);
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader = new FileReader(filename);
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}



}
