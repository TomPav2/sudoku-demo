package objects;

public class Matrix {
	private  Vertice[][] matrix = new Vertice[9][9];
	private short remaining = 81;

	public Matrix() {
			for (int row = 0; row < 9; row++) {
				for (int col = 0; col < 9; col++) {
					matrix[row][col] = new Vertice(pickSegment(row, col));
				}
			}
	}
	
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
	
	// Segments:
	// 0 1 2
	// 3 4 5
	// 6 7 8
	private static int pickSegment(int row, int col) {
		return (row/3*3) + (col/3);
	}
	
	public Vertice getVertice(int row, int col) {
		return matrix[row][col];
	}
	
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
				matrix[seg/3*3 + r][seg%3*3 + c].removeOption(val);
			}
		}
	}
	
	public void printMatrix() {
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0) System.out.println();
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0) System.out.print(" ");
				System.out.print(matrix[row][col].getValue());
			}
			System.out.println();
		}
	}
	
	public void printOptions() {
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0) System.out.println();
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0) System.out.print(" ");
				System.out.print(matrix[row][col].getOptionCount());
			}
			System.out.println();
		}
	}


}
