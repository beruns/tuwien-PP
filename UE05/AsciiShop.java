/* import Scanner to Root NS */
import java.util.Scanner;

/**
 * Reads an Ascii image from STDIN and performes actions on it
 *
 * @author Georg Hubinger (9947673)
 * @version 1.0
 */

public class AsciiShop {

	/** Entrypoint 
	 *  @param args comman line arguments
 	 */
	public static void main(String[] args) {
		
		/* Init Scanner to STDIN */
		Scanner sc = new Scanner(System.in);
		AsciiImage image;
	
		String command;

		int h, w;	
	
		/* Firts input shall be of format read NumOfLines */
		if(!sc.hasNext() || !sc.next().equals("create") || !sc.hasNextInt() || (w = sc.nextInt()) <= 0 || !sc.hasNextInt() || (h = sc.nextInt()) <= 0) {

			/* if not, bailout */
			System.out.println("INPUT MISMATCH");
			return;

		}
		
		image = new AsciiImage(w, h);

		while(sc.hasNext()) {
			
			command = sc.next();

			if(command.equals("clear")) {

				image.clear();

			} else if(command.equals("line")) {
			
				int x0, y0, x1, y1;
				char c;

				if(sc.hasNextInt() && (x0 = sc.nextInt()) == x0 && sc.hasNextInt() && (y0 = sc.nextInt()) == y0 && 
					sc.hasNextInt() && (x1 = sc.nextInt()) == x1 && sc.hasNextInt() && (y1 = sc.nextInt()) == y1 && 
						sc.hasNext() && (c = sc.next().charAt(0)) == c) {

					image.drawLine(x0, y0, x1, y1, c);
			
				} else {
					System.out.println("INPUT MISMATCH");
					return;
				}

			} else if(command.equals("load")) {

				if(sc.hasNext()) {

					String eof = sc.next();
					String line = "";
					int i = 0, j = 0;

					sc.nextLine();
		
					while(!line.equals(eof) && i < image.getHeight() && sc.hasNextLine()) {
						line = sc.nextLine();

						if(line.length() != image.getWidth()) {
							System.out.println("INPUT MISMATCH");
							return;
						}						

						for(j = 0; j<image.getWidth(); j++) {
							image.setPixel(j, i, line.charAt(j));
						}
						i++;
					}
		
					if(i < image.getHeight() - 1 || !sc.next().equals(eof)) {
						System.out.println("INPUT MISMATCH");
						return;
					}
		
//					sc.next();

				} else {
					System.out.println("INPUT MISMATCH");
					return;
				}

			} else if(command.equals("print")) {

				System.out.println(image);
//				System.out.println();

			} else if(command.equals("replace")) {
			
				char oldChar, newChar;
				if(sc.hasNext() && (oldChar = sc.next().charAt(0)) == oldChar && sc.hasNext() && (newChar = sc.next().charAt(0)) == newChar) {
					image.replace(oldChar, newChar);
				} else {
					System.out.println("INPUT MISMATCH");
					return;
				}
				
			} else if(command.equals("transpose")) {

				image.transpose();

			} else if(command.equals("fill")) {
			
				int x, y;
				char c;
		
				if(sc.hasNextInt() && (x = sc.nextInt()) == x && sc.hasNextInt() && (y = sc.nextInt()) == y && sc.hasNext() && (c = sc.next().charAt(0)) == c) {
					if(x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
						System.out.println("OPERATION FAILED");
						return;
					}
					image.fill(x, y, c);
				} else {
					System.out.println("INPUT MISMATCH");
					return;
				}
								

			} else {
				System.out.println("UNKNOWN COMMAND");
				return;
			}	

		}	
	
	}

}
