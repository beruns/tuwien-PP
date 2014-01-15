/* import Scanner to Root NS */
import java.util.Scanner;

/**
 * Reads an Ascii image from STDIN floodfills requested area with given char  
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
		
		/* Lines to read, array indexer, x y coords to fill */
		int read, i = 0, x, y;
		/* Image String Array */
		String[] image;
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
		/* Create String Array of size read */
		image = new String[read];

		/* Move to next line of input */
		if(sc.hasNextLine()) {
			sc.nextLine();
		}

		/* Read Image into Array {{{
		   While we have a next line and we haven't reached NumOfLines */
		while(i < read && sc.hasNextLine()) {

			/* Read next line into array */
			image[i] = sc.nextLine();
		
			/* If line length is not equal to previous line length (if we had one already) ... */
			if((i > 0) && image[i-1].length() != image[i].length()) {
			
				/* ... bailout */
				System.out.println("INPUT MISMATCH");
				return;

			}
			/* Increase Array indexer */
			i++;
		}
		/* }}} */

		/* if not enough lines have been read into the array */
		if(read != i) {

			/* ... bailout */
			System.out.println("INPUT MISMATCH");
			return;
			
		}

		/* Read fill commands {{{ */
		while(sc.hasNext()) { 

			/* if we don't get the string 'fill' followed by two integers and one string (using the firts char only) ...
			   (x = sc.nextInt()) != x is used to satisfy the fact, that java can only use boolean types in if
			   This also bailes out, when more lines of image have been provided than stated by the read command
			*/
			if(!sc.next().equals("fill") || !sc.hasNextInt() || (x = sc.nextInt()) != x || !sc.hasNextInt() || 
				(y = sc.nextInt()) != y || !sc.hasNext() || (c = sc.next().charAt(0)) != c)  {

				/* ... bailout */
				System.out.println("INPUT MISMATCH");
				return;

			}
	
			/* If coordinates exceed our array */
			if(y < 0 || y > i - 1 || x < 0 || x >= image[i - 1].length()) {

				/* Operation failed */	
				System.out.println("OPERATION FAILED");
				return;
	
			}

			/* Fill image */
			fill(image, x, y, c);

		}
		/* }}} */
	
		/* Output (filled) Image */
		for(read = 0; read < i; read++) {
			System.out.println(image[read]);
		}
		System.out.println(image[read-1].length() + " " + read);
	

	}

	/** FloodFill Image with given char starting at given coords. This function is called recursively 
	 * @param image Ascii Image String Array
	 * @param x x position
	 * @param y y position
	 * @param c char to flood the area with
	 * @see <a href="http://de.wikipedia.org/wiki/Floodfill">Wikipedia</a>
	 */
	public static void fill(String[] image, int x, int y, char c) {
		
		/* Get the current char at given position */
		char cur = image[y].charAt(x);
		/* Replace it with the given char */
		image[y]  = image[y].substring(0, x) + c + image[y].substring(x + 1);

		/* if we have a left neighbour, whose value equals cur ... */
		if(x > 0 && image[y].charAt(x - 1) == cur) {
			/* ... fill it */
			fill(image, x - 1, y, c);
		}

		/* if we have a left neighbour, whose value equals cur ... */
		if(x < image[y].length() - 1 && image[y].charAt(x + 1) == cur) {
			/* ... fill it */
			fill(image, x + 1, y, c);
		}

		/* if we have a top neighbour, whose value equals cur ... */
		if(y > 0 && image[y - 1].charAt(x) == cur) {
			/* ... fill it */
			fill(image, x, y - 1, c);
		}

		/* if we have a bottom neighbour, whose value equals cur ... */
		if(y < image.length - 1 && image[y + 1].charAt(x) == cur) {
			/* ... fill it */
			fill(image, x, y + 1, c);
		}
			
		
	}

}
