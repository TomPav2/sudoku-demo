package objects;

public class Matrix {
	private Vertex[][] matrix = new Vertex[9][9];
	private short remaining = 81;

	/**
	 * Initializes the matrix with vertices.
	 */
	public Matrix() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				matrix[row][col] = new Vertex(pickSegment(row, col));
			}
		}
	}

	/**
	 * Inputs valid values from a 2D array into the matrix itself.
	 * 
	 * @param input 9×9 arrray of characters
	 */
	public void loadMatrix(char[][] input) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				char in = input[row][col];
				if (in >= '1' && in <= '9') {
					putValue(row, col, (short) Character.getNumericValue(in));
				}
			}
		}
	}

	/**
	 * 
	 * @return true if all vertices have an assigned value
	 */
	public boolean isComplete() {
		return remaining == 0;
	}

	/**
	 * Calculates which segment the coordinates belong to.
	 * 
	 * @param row
	 * @param col
	 * @return the number of the segment
	 */
	// Segments:
	// 0 1 2
	// 3 4 5
	// 6 7 8
	private static int pickSegment(int row, int col) {
		return (row / 3 * 3) + (col / 3);
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return the vertex at provided coordinates
	 */
	public Vertex getVertex(int row, int col) {
		return matrix[row][col];
	}

	/**
	 * Assigns the value to the vertex and removes the possible value from vertices
	 * in the same row, column, or segment.
	 * 
	 * @param row
	 * @param col
	 * @param val
	 */
	public void putValue(int row, int col, short val) {
		remaining--;
		matrix[row][col].setValue(val);

		// remove option from row and column
		for (int i = 0; i < 9; i++) {
			matrix[row][i].removeOption(val);
			matrix[i][col].removeOption(val);
		}

		// remove option from segment
		int seg = matrix[row][col].getSegment();
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				matrix[seg / 3 * 3 + r][seg % 3 * 3 + c].removeOption(val);
			}
		}
	}

	/**
	 * Transforms the matrix into String and prints to console output, or just
	 * returns the String;
	 * 
	 * @param toConsole if true, it will be printed to console output
	 * @return the String if toConsole is false
	 */
	public String printMatrix(boolean toConsole) {
		StringBuilder output = new StringBuilder();
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0)
				output.append("\r\n");
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0)
					output.append(" ");
				output.append(matrix[row][col].getValue());
			}
			output.append("\r\n");
		}
		output.append("\r\n");

		if (toConsole) {
			System.out.println(output.toString());
			return null;
		} else
			return output.toString();
	}

	/**
	 * Print the amount of possible values of all vertices. For debugging purposes.
	 */
	public void printOptions() {
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0)
				System.out.println();
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0)
					System.out.print(" ");
				System.out.print(matrix[row][col].getOptionCount());
			}
			System.out.println();
		}
	}

}
