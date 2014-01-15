import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

public class AsciiShop {

	public static void main(String args[]) throws OperationException {

		HashMap<String, Factory> factories = new HashMap<String, Factory>();
		MetricSet<AsciiImage> saved = new MetricSet<AsciiImage>();

		factories.put("create", new CreateFactory());
		factories.put("clear", new ClearFactory());
		factories.put("filter", new FilterFactory());
		factories.put("binary", new BinaryFactory());
		factories.put("load", new LoadFactory());
		factories.put("replace", new ReplaceFactory());
		factories.put("save", new SaveFactory(saved));
		factories.put("search", new SearchFactory(saved));

		Scanner sc = new Scanner(System.in);

		AsciiImage image;
		AsciiStack stack = new AsciiStack();
		Operation op;

		String command;

		if(!sc.hasNext() || !sc.next().equals("create")) {
			System.out.println("INPUT MISMATCH");
			return; 
		}

		try {
			op = factories.get("create").create(sc);
			image = op.execute(null);
		} catch (FactoryException e) {
			System.out.println("INPUT MISMATCH");
			return; 
		}

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

			} else if(command.equals("printsaved")) {
	
				if(saved.isEmpty()) {
					System.out.println("NO SAVED IMAGES");
				} else {
					Iterator<AsciiImage> it = saved.iterator();
					while(it.hasNext()) {
						System.out.println(it.next());
					}
				}
							
				
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
