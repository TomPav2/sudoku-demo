package objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Vertex {
	private int segment;
	private short val = 0;
	private Set<Short> options = new HashSet<>(Arrays.asList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5,
			(short) 6, (short) 7, (short) 8, (short) 9));

	/**
	 * @return the value, or 0 if none was assigned yet
	 */
	public short getValue() {
		return val;
	}

	/**
	 * @return the amount of numbers that this vertex can be
	 */
	public int getOptionCount() {
		return options == null ? 0 : options.size();
	}

	/**
	 * @return the first number in the set of options; should be used when only one
	 *         possible number remains
	 */
	public short getSingleOption() {
		return options.iterator().next();
	}

	/**
	 * @return the set of possible numbers in this vertex
	 */
	public Set<Short> getOptions() {
		return options;
	}

	/**
	 * @param segment assigns the number of the segment where this vertex is located
	 */
	protected Vertex(int segment) {
		this.segment = segment;
	}

	/**
	 * @return the number of the segment where this vertex is located
	 */
	protected int getSegment() {
		return segment;
	}

	/**
	 * @param set the final value to set to this vertex
	 */
	protected void setValue(short set) {
		this.val = set;
		this.options = null;
	}

	/**
	 * @param rem the number which this vertex can no longer contain
	 */
	protected void removeOption(short rem) {
		if (options != null) {
			options.remove(rem);
		}
	}

}
