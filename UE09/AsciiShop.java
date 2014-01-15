import java.util.Scanner;
import java.util.HashMap;

public class AsciiShop {

	public static void main(String args[]) throws OperationException {

		HashMap<String, Factory> factories = new HashMap<String, Factory>();

		factories.put("clear", new ClearFactory());
		factories.put("filter", new FilterFactory());
		factories.put("binary", new BinaryFactory());
		factories.put("load", new LoadFactory());
		factories.put("replace", new ReplaceFactory());

		Scanner sc = new Scanner(System.in);

		AsciiImage image;
		AsciiStack stack = new AsciiStack();
		Operation op;

		String command;
		int w, h;
		String charset;

		if(!sc.hasNext() || !sc.next().equals("create") 
			|| !sc.hasNextInt() || (w = sc.nextInt()) <= 0 || !sc.hasNextInt() || (h = sc.nextInt()) <= 0
			|| !sc.hasNext() || (charset = sc.next()) != charset) {
			
				System.out.println("INPUT MISMATCH");
				return;

		}

		image = new AsciiImage(w, h, charset);
	
		while(sc.hasNext()) {

			command = sc.next();

			if(factories.containsKey(command)) {

				try {

					stack.push(new AsciiImage(image));
					op = factories.get(command).create(sc);
					image = op.execute(image);
					
				} catch (FactoryException e) {
					System.out.println("INPUT MISMATCH");
					return; 
				} catch (OperationException e) {
					System.out.println("OPERATION FAILED");
					return;
				}

			} else if(command.equals("print")) {

				System.out.println(image);

			} else if(command.equals("undo")) {
				
				if(stack.empty()) {
					System.out.println("STACK EMPTY");
				} else {
					image = stack.pop();
				}

			} else {

				System.out.println("UNKNOWN COMMAND");
				return;

			}

		}
		

	}

}
