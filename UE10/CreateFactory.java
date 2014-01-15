import java.util.Scanner;

public class CreateFactory implements Factory {
	
	public CreateFactory() {
	}

	public Operation create(Scanner sc) throws FactoryException {

		int w, h;
		String charset;

		if(!sc.hasNextInt() || (w = sc.nextInt()) <= 0 || !sc.hasNextInt() || (h = sc.nextInt()) <= 0
                        || !sc.hasNext() || (charset = sc.next()) != charset) {
				throw new FactoryException("Insufficient Arguments");
		}

		return new CreateOperation(w, h, charset);		

	}

}
