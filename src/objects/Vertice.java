package objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private int segment;
	private short val = 0;
	private Set<Short> options = new HashSet<>(Arrays.asList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5,
			(short) 6, (short) 7, (short) 8, (short) 9));

	public int getOptionCount() {
		return options == null ? 0 : options.size();
	}

	public short getSingleOption() {
		return options.iterator().next();
	}

	public Set<Short> getOptions() {
		return options;
	}

	protected Vertice(int segment) {
		super();
		this.segment = segment;
	}

	protected int getSegment() {
		return segment;
	}

	protected short getValue() {
		return val;
	}

	protected void setValue(short set) {
		this.val = set;
		this.options = null;
	}

	protected void removeOption(short rem) {
		if (options != null) {
			options.remove(rem);
		}
	}

}
