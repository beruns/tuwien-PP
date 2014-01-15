import java.util.Scanner;
public class LoadFactory implements Factory {

	public LoadFactory() {
	}
	
	public Operation create(Scanner sc) throws FactoryException {

		String eof;
		String data = "", line = "";

		if(!sc.hasNext() || (eof = sc.next()) != eof) {
			throw new FactoryException("Insufficient parameter");
		}

		sc.nextLine();

		while(!line.equals(eof) && sc.hasNextLine()) {
			data += (line = sc.nextLine()) + "\n";
		}

		if(!line.equals(eof)) {
			throw new FactoryException("Input Mismatch");
		}

		return new LoadOperation(data);
	}

}
