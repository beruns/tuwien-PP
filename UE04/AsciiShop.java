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
		
		/* Lines to read, line counter, x y coords to fill */
		int read, i = 0;
		/* AsciiImage storage */
		AsciiImage image = new AsciiImage();
		/* Command to execute */
		String command;
		/* For Fill Command. Choords */
		int x, y;
		/* Char to fill */
		char c;
	
		/* Firts input shall be of format read NumOfLines */
		if(!sc.hasNext() || !sc.next().equals("read") || !sc.hasNextInt()) {

			/* if not, bailout */
			System.out.println("INPUT MISMATCH");
			return;

		}
		
		/* NumOfLines */
		read = sc.nextInt();

		/* Move to next line of input */
		if(sc.hasNextLine()) {
			sc.nextLine();
		}

		/* Read Image {{{
		   While we have a next line and we haven't reached NumOfLines */
		while(i < read && sc.hasNextLine()) {

			/* If addLine Method returns false, we have a line width mismatch */	
			if(!image.addLine(sc.nextLine())) {
				/* ... bailout */
				System.out.println("INPUT MISMATCH");
				return;

			}
			/* Increase Counter */
			i++;
		}
		/* }}} */

		/* if not enough lines have been read into the array */
		if(read != i) {

			/* ... bailout */
			System.out.println("INPUT MISMATCH");
			return;
			
		}

		/* Read and execute commands {{{ */
		while(sc.hasNext()) { 
			command = sc.next();
			if(command.equals("uniqueChars")) {
				System.out.println(image.getUniqueChars());
			} else if(command.equals("flip-v")) {
				image.flipV();
			} else if(command.equals("transpose")) {
				image.transpose();
			} else if(command.equals("fill")) {
				if(sc.hasNextInt() && (x = sc.nextInt()) == x && sc.hasNextInt() && (y = sc.nextInt()) == y && sc.hasNext() && (c = sc.next().charAt(0)) == c)  {
					if(x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
						System.out.println("OPERATION FAILED");
						return;
			
					} else {
						image.fill(x, y, c);
					}
				} else {
					System.out.println("INPUT MISMATCH");
					return;
				}
			} else if(command.equals("symmetric-h")) {
				System.out.println(image.isSymmetricH());
			} else {
				System.out.println("INPUT MISMATCH");
				return;
			}
				
		}
		/* }}} */
	
		System.out.println(image);
		System.out.println(image.getWidth() + " " + image.getHeight());
	

	}

}
