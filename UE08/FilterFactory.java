import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	public Operation create(Scanner sc) throws FactoryException {

		if(!sc.hasNext() || !sc.next().equals("median")) {
			throw new FactoryException("Input mismatch");
		}

		return new MedianOperation();
	}
}
