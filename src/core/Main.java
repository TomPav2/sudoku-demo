package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.DataFormatException;

import methods.GraphColoring;
import methods.Validator;
import objects.Matrix;

public class Main {
	private static final String fileIn = "resources\\input.txt";
	private static final String fileOut = "resources\\output.txt";

	public static void main(String[] args) {
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

		if (!Validator.validate(matrix)) {
			System.out.println("Validation failed, the input is impossible to solve.");
		}

		while (!matrix.isComplete()) {
			if (!GraphColoring.fill(matrix))
				break;
		}

		if (Validator.validate(matrix)) {
			if (matrix.isComplete())
				System.out.println("Sudoku puzzle completed.");
			else
				System.out.println("Sudoku puzzle could not be completed.");
		} else {
			System.out.println("Validation failed, the puzzle contains errors.");
		}

		try {
			writeOutput(matrix);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Attempts to read input.txt in resources.
	 * 
	 * @return a 2D array of characters
	 * @throws IOException
	 * @throws DataFormatException
	 */
	private static char[][] readInput() throws IOException, DataFormatException {
		BufferedReader br = new BufferedReader(new FileReader(fileIn));
		char[][] plainMatrix = new char[9][9];
		try {
			int index = 0;
			String line = br.readLine();

			while (line != null) {
				if (line.length() != 9 || index > 8)
					throw new DataFormatException("Wrong puzzle dimension on line " + index);
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

	/**
	 * Writes the matrix values into output.txt in resources.
	 * 
	 * @param m the matrix to print
	 * @throws IOException
	 */
	private static void writeOutput(Matrix m) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileOut));
		writer.write(m.printMatrix(false));
		writer.close();
	}

}