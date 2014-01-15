/* Alias java.util.Scanner to Scanner */
import java.util.Scanner;

/**
 * Reads formatted input from STDIN and outputs Labels and Bars  
 *
 * @author Georg Hubinger (9947673)
 * @version 1.0
 */
public class BarPlot {

	/** EntryPoint
	 * Reads formatted input from STDIN.
         * Expects a string (the label) followed by an integer or a double value (the length of the bar)
	 * @params args command line arguments
	 */
	public static void main(String[] args) {

		/* Storage for the label */
		String label;
		/* Storage for the bar's length.
		   We can store an integer in a double in java without loss because java uses double-precision 64-bit IEEE 754 floating point.
		   (which has a mantissa of 52 bit and integer uses 32 bit)
		*/
		double len;

		/* initialize Scanner on STDIN */
		Scanner sc = new Scanner(System.in);

		/* While we have a next token (Standard delimiter is whitespace  */
		while(sc.hasNext()) {

			/* Fetch the label. We fetch the tokens in pairs, so the first one is always the label */
			label = sc.next();

			/* If the next token can be parsed as an int and is between 0 and 30 (both included) */
			if(sc.hasNextInt() && (len = sc.nextInt()) >= 0 && len <= 30) {
	
				/* Create String for printing later. We need to cast len to int here to match the correct overloaded method */
				label = drawBar(label, (int) len);

			/* If the next token can be parsed as a double and is between 0 and 1 (both included)*/
			} else if(sc.hasNextDouble() && (len = sc.nextDouble()) >= 0.0 && len <= 1.0) {
				
				/* Create String for printing later */
				label = drawBar(label,  len);

			/* Else we seem to have in Input Error according to our formatting rules */
			} else {
				
				/* Bailout */
				System.out.println("INPUT ERROR");
				return;

			}

			/* Print out Sequence */
			System.out.println(label);

		}
	}

	/** Repeat Char c n times 
	 *  @param c Char to be repested
	 *  @param n amount of repeats
	 */
	private static String repeat(char c, int n) {

		String s = "";

		for(int i = 0; i<n; i++) {
			s += c;
		}

		return s;
	}

	/** Format Label part of output 
	 *  @param label label to be formatted
	 *  @param n	 length of formatted label
	 */
	private static String drawLabel(String label, int n) {
		/* Trim Label to fit 8 char format */
		if(label.length() > 8) {
			label = label.substring(0, 8);
		}
		/* Fill Remaining chars with whitespace */
		return label + repeat(' ', n - label.length());
	}

	/** Draw labelled bar absolue width 
	 * @param label label to be formatted
	 * @param value absolute length of bar to be formatted
	 */
	private static String drawBar(String label, int value) {
		/* First format label, append "|", draw the bar and append "|" again */
		return drawLabel(label, 8) + "|" + repeat('#', value) + repeat(' ', 30 - value) + "|";
		
	}
	
	/** Draw labelled bar relative width 
	 * @param label label to be formatted
	 * @param value relative length of bar to be formatted
	 */
	private static String drawBar(String label, double value) {
		/* Math.round rounds to nearest integer. Calculate bar size draw bar. We need to explicitly cast here or it might get implicitly casted to double */
		return drawBar(label, (int) Math.round(30 * value));
	}
}
