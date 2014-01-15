
/**
 * Represents an AsciiImage 
 *
 * @author Georg Hubinger (9947673)
 * @version 1.0
 */

public class AsciiImage {

	/* Private storage */
	private int width;
	private int height;
	
	private String image;

	/**
	 * Ctor
	 */
	public AsciiImage() {
		
		this.width = 0;
		this.height = 0;
		this.image = "";

	}

	/**
	 * Calculate the position in image string based on x and y choord
	 * @param x x-choord in Image
	 * @param y y-choord in Image
	 * @return int string pos
	 */
	private int getStringPos(int x, int y) {
		return (y * this.width) + x;
	}

	/**
	 * Get Pixel at given position 
	 * @param x x-choord in Image
	 * @param y y-choord in Image
	 * @return char pixel
	 */
	private char getPixel(int x, int y) {
		return this.image.charAt(this.getStringPos(x, y));
	}

	/**
	 * Set Pixel at given position 
	 * @param x x-choord in Image
	 * @param y y-choord in Image
	 * @param c New Pixel
	 * @return char replaced pixel
	 */
	private char setPixel(int x, int y, char c) {
		char current = this.getPixel(x, y);
		this.image = this.image.substring(0, this.getStringPos(x, y)) + c + this.image.substring(this.getStringPos(x, y) + 1);
		return current;
	}

	/**
	 * Add Line to Image 
	 * @param line Ascii line to add
	 * @return true if first line or line of the same size as all other lines. False otherwise 
	 */
	public boolean addLine(String line) {

		if(this.width != 0 && this.width != line.length()) {
			return false;
		}  else if(this.width == 0) {
			this.width = line.length();
		}
		
		this.image += line;
		this.height++;
		return true;
	
	}

	
	/**
	 * Get Image width
	 * @return image width
	 */
	public int getWidth() {

		return this.width;

	}
		
	/**
	 * Get Image height 
	 * @return image height
	 */
	public int getHeight() {

		return this.height;

	}

	/**
	 * Flip Image vertically. Flips first row with last row and so on
	 */
	public void flipV() {

		int i=0;
		String s = "";

		while(i < (this.image.length() - this.getWidth())) {
			s = this.image.substring(i, i + this.getWidth()) + s;
			i += this.getWidth();
		}

		this.image = this.image.substring(i) + s; 

	}	


	/**
	 * Flip Image vertically. Flips first column with last column and so on
	 */
	public void flipH() {
		char c;
		int i, j;
		
		for(i = 0; i < this.getHeight(); i++) {

			for(j = 0; j < this.getWidth() / 2; j++) {
				this.setPixel(j, i, this.setPixel(this.getWidth() - (j+1), i, this.getPixel(j, i)));
			}

		}
		
	}
	
	/**
	 * Transpose Image. Makes columns rows and vice versa
	 */
	public void transpose() {

		int i = 0, j  = 0;
		String tmp = "";
		
		for(j = 0; j < this.width; j++) {
			for(i = 0; i < this.height; i++) {
				tmp += this.getPixel(j, i);
			}
		}

		this.height = this.width;
		this.width = i; 
	
		this.image = tmp;	

	}

	/**
	 * Flood fill image.
	 * @param x x-choord of starting char 
	 * @param y y-choord of starting char
	 * @param c char to fill area with
	 */
	public void fill(int x, int y, char c) {

		int pos = this.getStringPos(x, y);
		char cur = this.setPixel(x, y, c);

		if(x > 0 && this.getPixel(x - 1, y) == cur) {
			this.fill(x - 1, y, c);
		}

		if(x < this.getWidth() - 1 && this.getPixel(x + 1, y) == cur) {
			this.fill(x + 1, y, c);
		}

		if(y > 0 && this.getPixel(x, y - 1) == cur) {
			this.fill(x, y - 1, c);
		}

		if(y < this.getHeight() - 1 && this.getPixel(x, y + 1) == cur) {
			this.fill(x, y + 1, c);
		}
		
	}

	/**
	 * Count unique chars in image.
	 * @return num of unique chars in image
	 */
	public int getUniqueChars() {

		String tmp = "";
		int i, len = this.width * this.height;
		CharSequence c;

		for(i = 0; i < 	len; i++) {
			c = this.image.subSequence(i, i + 1);
			if(!tmp.contains(c)) {
				tmp += c.charAt(0);
			}
		}
		
		return tmp.length();

	}

	/**
	 * Check if image is horizontically symmetric (each line is a Palindrom)
	 * @return false if not. True otherwise
	 */
	public boolean isSymmetricH() {

		int i, j;

		for(i = 0; i < this.getHeight(); i++) {
			for(j = 0; j < this.getWidth() / 2; j++) {
				if(this.getPixel(j, i) != this.getPixel(this.getWidth() - (j+1), i)) {
					return false;
				}
			}
		}

		return true;		

	}

	/**
	 * Format Image for String Output (add newlines after each row
	 * @return Formatted String
	 */
	public String toString() {

		int i =  0;
		String s = "";

		while(i < (this.image.length() - this.getWidth())) {
			s += this.image.substring(i, i + this.getWidth()) + "\n";
			i += this.getWidth();
		}

		return s += this.image.substring(i);

	}	

}
