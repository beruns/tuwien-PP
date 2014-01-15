import java.util.Scanner;

public class AsciiShop {

	public static void main(String args[]) throws OperationException {

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
		
			if(command.equals("clear")) {

				op = new ClearOperation();
				stack.push(new AsciiImage(image));

				try {
					image = op.execute(image);
				} catch(OperationException e) {
					System.out.println("OPERATION FAILED");
					return;
				}
				
			} else if(command.equals("filter")) {

				if(!sc.hasNext() || !sc.next().equals("median")) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				op = new MedianOperation();
				stack.push(new AsciiImage(image));

				try {
					image = op.execute(image);
				} catch(OperationException e) {
					System.out.println("OPERATION FAILED");
					return;
				}

			} else if(command.equals("load")) {

				String eof;
				String data = "", line = "";

				if(!sc.hasNext() || (eof = sc.next()) != eof) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				sc.nextLine();
				
				while(!line.equals(eof) && sc.hasNextLine()) {
					data += (line = sc.nextLine()) + "\n";
				}

				if(!line.equals(eof)) {
					System.out.println("INPUT MISMATCH");
					return;
				}

				op = new LoadOperation(data);
				stack.push(new AsciiImage(image));

				try {
					image = op.execute(image);
				} catch(OperationException e) {
					System.out.println("OPERATION FAILED");
					return;
				}

			} else if(command.equals("print")) {

				System.out.println(image);

			} else if(command.equals("replace")) {

				char oldChar, newChar;
				if(!sc.hasNext() || (oldChar = sc.next().charAt(0)) != oldChar|| (newChar = sc.next().charAt(0)) != newChar) {
					System.out.println("INPUT MISMATCH");
					return;
				}
				op = new ReplaceOperation(oldChar, newChar);
				stack.push(new AsciiImage(image));

				try {
					image = op.execute(image);
				} catch(OperationException e) {
					System.out.println("OPERATION FAILED");
					return;
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
