import java.util.Scanner;
public class BinaryFactory implements Factory {

	public BinaryFactory() {
	}

	public Operation create(Scanner sc) throws FactoryException {

		char treshold;		

		if(!sc.hasNext()) {
			throw new FactoryException("Insufficient Arguments");
		}
	
		treshold = sc.next().charAt(0);

		return new BinaryOperation(treshold);		
	}

}
