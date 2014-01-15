import java.util.Scanner;

public class FilterFactory implements Factory {

	public FilterFactory() {
	}

	public Operation create(Scanner scanner) throws FactoryException {

		String type;

		/* Default depth */
		int filterDepth = 3;
		/* Default edgeHandling */
		String edgeHandling = "x";

		FilterOperation filter;

		/* We'll take out the whole line, and create a new scanner, because if we would do
		*  hasNext and next to fetch the edgeHandling parameter, we could possibliy just traverse to the next line and thus the next command
		*/
		String args = scanner.nextLine();
		Scanner sc = new Scanner(args);

		/* We need a filter type*/
		if(!sc.hasNext() || (type = sc.next()) != type) {
			throw new FactoryException("Input mismatch");
		}

		/* We can either have a depth, an edgeHandling or both, or none, but the depth has to come before the edgeHandling */
		if(sc.hasNextInt()) filterDepth = sc.nextInt();
		if(sc.hasNext()) edgeHandling = sc.next();

		/* We only accept odd depth larger than 1*/
		if(filterDepth <= 1 || filterDepth % 2 != 1) {
			throw new FactoryException("Incorrect Filter Depth (must be an odd number larger than 1)");
		}

		/* check if the edgeHandling command is valid */
		if(!edgeHandling.equals("x") && !edgeHandling.equals("circular") && !edgeHandling.equals("replicate") && !edgeHandling.equals("symmetric")) {
			throw new FactoryException("Unknown Edge Handling. Must be one of 'x', 'circular', 'replicate' or 'symmetric')");
		}

		/* Create the filter */
		if(type.equals("median")) {
			filter =  new MedianOperation();
		} else if(type.equals("average")) {
			filter = new AverageOperation();
		} else {
			throw new FactoryException("Unkown Filter Type");
		}

		/* Provide extra parameters */		
		filter.setDepth(filterDepth);
		filter.setEdgeHandling(edgeHandling);

		return filter;

	}
}
