package methods;

import objects.Matrix;
import objects.Vertex;

public class GraphColoring {

	/**
	 * Iterates all fields of the matrix and fills in where only one option is
	 * possible.
	 * 
	 * @param m the matrix to be filled
	 * @return true if any field was filled in this iteration
	 */
	public static boolean fill(Matrix m) {
		boolean success = false;

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				Vertex v = m.getVertex(row, col);
				if (v.getOptionCount() == 1) {
					m.putValue(row, col, v.getSingleOption());
					success = true;
				}
			}
		}
		return success;
	}
}
