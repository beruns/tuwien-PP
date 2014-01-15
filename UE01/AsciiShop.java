/* Alias java.util.Scanner to Scanner */
import java.util.Scanner;

/**
 * Count Rows and columns in an Ascii Image
 *
 * @author Georg Hubinger (9947673)
 * @version 1.0
 */

public class AsciiShop {

	/** EntryPoint 
	 *  Reads an Ascii Image from STDIN. If not all rows have the same length outputs "INPUT MISMATCH".
	 *  Otherwise output width and height of image
	 *  @param args Command Line Arguments
	 */
	public static void main(String[] args) {

		/* Column Counter */
		int colNum = 0;
		/* Row Counter */
		int rowNum = 0;
		/* tmp variable to hold line string */
		String s;
		
		/* initialize Scanner on STDIN */
		Scanner sc = new Scanner(System.in);
		/* While we have a next line */
		while(sc.hasNextLine()) {
			/* Fetch it */
			s = sc.nextLine();
			/* If we already had a line before, we check, if the current one is of the same length */
			if(colNum != 0 && (s.length() != colNum)) {
				/* If not, we have an INPUT MISMATCH. Output and return */
				System.out.println("INPUT MISMATCH");
				return;
			} else {
				/* Set colNum once. If any of the other lines has a different length we'll return anyway (Scanner.NextLine() strips newline chars already) */
				colNum = s.length();
			}
			/* Increase rowCount */
			rowNum++;
			
		}

		/* Output format */
		System.out.println(colNum + " " + rowNum);

	}

	

}
