package methods;

import java.util.Iterator;

import objects.Matrix;
import objects.Vertice;

public class GraphColoring {
	public static boolean fill(Matrix m) {
		boolean success = false;
		
		for(int row = 0; row < 9; row ++) {
			for (int col = 0; col < 9; col ++) {
				Vertice v = m.getVertice(row, col);
				if (v.getOptionCount() == 1) {
					m.putValue(row, col, v.getSingleOption());
					success = true;
				}
			}
		}
		return success;
	}
}
