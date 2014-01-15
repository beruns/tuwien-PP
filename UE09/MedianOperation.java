import java.util.Arrays;

public class MedianOperation extends FilterOperation {

	public MedianOperation() {
	}

	public int filter(int[] values) {
		return values[(int) (values.length / 2)];
	}

}
