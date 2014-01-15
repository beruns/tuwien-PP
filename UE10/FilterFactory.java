import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	public Operation create(Scanner sc) throws FactoryException {

		String type;

		if(!sc.hasNext() || (type = sc.next()) != type) {
			throw new FactoryException("Input mismatch");
		}

		if(type.equals("median")) {
			return new MedianOperation();
		} else if(type.equals("average")) {
			return new AverageOperation();
		} else {
			throw new FactoryException("Unkown Filter Type");
		}
	}
}
