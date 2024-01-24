package methods;

import java.util.HashSet;
import java.util.Set;

import objects.Matrix;

public class Validator {

	public static boolean validate(Matrix m) {
		Set<Short> checked = new HashSet<>();

		for (int row = 0; row < 9; row++) {
			checked.clear();
			for (int col = 0; col < 9; col++) {
				Short num = m.getVertice(row, col).getValue();
				if (!checked.add(num)) {
					System.out.println("Duplicate number " + num + " on row " + row);
					return false;
				}
			}
		}

		for (int col = 0; col < 9; col++) {
			checked.clear();
			for (int row = 0; row < 9; row++) {
				Short num = m.getVertice(row, col).getValue();
				if (!checked.add(num)) {
					System.out.println("Duplicate number " + num + " in column " + col);
					return false;
				}
			}
		}

		for (int seg = 0; seg < 9; seg++) {
			checked.clear();
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					Short num = m.getVertice(seg / 3 * 3 + r, seg % 3 * 3 + c).getValue();
					if (!checked.add(num)) {
						System.out.println("Duplicate number " + num + " in segment " + seg);
						return false;
					}
				}
			}
		}

		return true;
	}
}
