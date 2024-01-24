package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import methods.GraphColoring;
import objects.Matrix;
import objects.Vertice;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start");
		Matrix matrix = new Matrix();
		
		try {
			matrix.loadMatrix(readInput());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (DataFormatException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(0);
		}
		
		Scanner input = new Scanner(System.in);;
		while (true) {
			String in = input.nextLine();
			if (in.equals("exit")) break;
			else if (in.equals("options")) matrix.printOptions();
			else if (in.contains(" ")) {
				String[] coords = in.split(" ");
				Vertice v = matrix.getVertice(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
				System.out.println(v.getOptions());
			}
			else {
				GraphColoring.fill(matrix);
				matrix.printMatrix();
			}
		}
		input.close();
		
	}	
	
	private static char[][] readInput() throws IOException, DataFormatException {
		BufferedReader br = new BufferedReader(new FileReader("resources\\input.txt"));
	    char[][] plainMatrix = new char[9][9];
		try {
			int index = 0;
		    String line = br.readLine();

		    while (line != null) {
		    	if (line.length() != 9 || index > 8) throw new DataFormatException("Wrong puzzle dimension on line " + index);
		    	for (int i = 0; i < 9; i++) {
		    		plainMatrix[index][i] = line.charAt(i);
		    	}
		    	
		    	line = br.readLine();
		    	index++;
		    }
		} finally {
		    br.close();
		}
	    return plainMatrix;
	}
}