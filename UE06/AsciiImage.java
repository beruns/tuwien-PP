import java.util.ArrayList;
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
	
	private char[][] image;

	/**
	 * Ctor
	 */
	public AsciiImage(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.image = new char[height][width];
		this.clear();
	}

	/**
	 * Copy CTOR
	 */
	public AsciiImage(AsciiImage img) {

		int i, j;

		this.width = img.getWidth();
		this.height = img.getHeight();
	
		this.image = new char[this.height][this.width];
		for(i = 0; i < this.height; i++) {
			
			for(j = 0; j < this.width; j++) {
				this.image[i][j] = img.getPixel(j, i);
			}
		}
	
	}

	/**
	 * Get Pixel at given position 
	 * @param x x-choord in Image
	 * @param y y-choord in Image
	 * @return char pixel
	 */
	public char getPixel(int x, int y) {
		return this.image[y][x];
	}

	/**
	 * Get Pixel at given AsciiPoint 
	 * @param p AsciiPoint
	 */
	public char getPixel(AsciiPoint p) {
		return this.getPixel(p.getX(), p.getY());
	}

	/**
	 * Set Pixel at given position 
	 * @param x x-choord in Image
	 * @param y y-choord in Image
	 * @param c New Pixel
	 */
	public void setPixel(int x, int y, char c) {
		this.image[y][x] = c;
	}

	/**
	 * Set Pixel at given AsciiPoint 
	 * @param p AsciiPoint
	 * @param c char to set
	 */
	public void setPixel(AsciiPoint p, char c) {
		this.setPixel(p.getX(), p.getY(), c);
	}

	/**
	 * Draw Line 
	 */
	public void drawLine(int x0, int y0, int x1, int y1, char c) {

		double xs, xe, ys, ye, dx, dy, d;
		double i;

		dx = (double) (x1 - x0);
		dy = (double) (y1 - y0);

		if(Math.abs(dx) < Math.abs(dy)) {

			xs = (y0>y1)?x1:x0;
			xe = (y0>y1)?x0:x1;
			ys = (y0>y1)?y1:y0;
			ye = (y0>y1)?y0:y1;

			dx = (double) (xe - xs);
			dy = (double) (ye - ys);
			d = dx / dy;

			this.setPixel((int) Math.round(xs), (int) Math.round(ys), c);
			this.setPixel((int) Math.round(xe), (int) Math.round(ye), c);
	
			for(i = ys + 1; i <= ye - 1; i++) {

				xs += d;
				ys += 1;
		
				this.setPixel((int) Math.round(xs), (int) Math.round(ys), c);
		
			}
		
		} else {

			xs = (x0>x1)?x1:x0;
			xe = (x0>x1)?x0:x1;
			ys = (x0>x1)?y1:y0;
			ye = (x0>x1)?y0:y1;

			dx = (double) (xe - xs);
			dy = (double) (ye - ys);
			d = dy / dx;

			this.setPixel((int) Math.round(xs), (int) Math.round(ys), c);
			this.setPixel((int) Math.round(xe), (int) Math.round(ye), c);
	
			for(i = xs + 1; i <= xe - 1; i++) {

				xs += 1;
				ys += d;
		
				this.setPixel((int) Math.round(xs), (int) Math.round(ys), c);	
			}

		}		

		
	}

	/**
	 * Replace all Pixels with '.'
	 */
	public void clear() {

		int i, j;
		for(i = 0; i < this.getHeight(); i++) {
			for(j = 0; j < this.getWidth(); j++) {
				this.image[i][j] = '.';
			}
		}
	}


	/**
	 * Replace all occurancec of oldChar with newChar
	 * @param oldChar
	 * @param newChar
	 */
	public void replace(char oldChar, char newChar) {
		int i, j;

		for(i = 0; i < this.getHeight(); i++) {
			for(j = 0; j < this.getWidth(); j++) {
				if(this.image[i][j] == oldChar) {
					this.image[i][j] = newChar;
				}
			}
		}
	}

	/**
	 * Calculate Centroid of given char
	 * @param c
	 */
	public AsciiPoint getCentroid(char c) {

		int i;
		double x = 0, y = 0;
		ArrayList<AsciiPoint> l = this.getPointList(c);
		AsciiPoint p;

		if(l.size() == 0) {
			return null;
		}		

		for(i = 0; i < l.size(); i++) {
			p = (AsciiPoint) l.get(i);
			x += p.getX();
			y += p.getY();
		}

		return new AsciiPoint((int) Math.round(x / l.size()), (int) Math.round(y / l.size()));
	}

	/**
	 * Replace all empty pixels (filled with '.') with given char if it's one of their neighbours.
	 * @param c char to look for
	 */
	public void growRegion(char c) {

		int i;

		ArrayList<AsciiPoint> l = this.getPointList(c);
                AsciiPoint p;
		if(l.size() == 0) {
			return;
		}

		for(i = 0; i < l.size(); i++) {
			p = (AsciiPoint) l.get(i);

			if(p.getX() > 0 && this.getPixel(p.getX() - 1, p.getY()) == '.') {
				this.setPixel(p.getX() - 1, p.getY(), c);
			}

			if(p.getX() < this.getWidth() - 1 && this.getPixel(p.getX() + 1, p.getY()) == '.') {
				this.setPixel(p.getX() + 1, p.getY(), c);
			}

			if(p.getY() > 0 && this.getPixel(p.getX(), p.getY() - 1) == '.' ) {
				this.setPixel(p.getX(), p.getY() - 1, c);
			}

			if(p.getY() < this.getHeight() - 1 && this.getPixel(p.getX(), p.getY() + 1) == '.') {
				this.setPixel(p.getX(), p.getY() + 1, c);
			}
				
		}
		

	}

	/**
	 * Remove interfering pixels
	 * @param c Char to looku for
	 */
	public void straightenRegion(char c) {

		ArrayList<AsciiPoint> l = this.getPointList(c);
		AsciiPoint p = null;
		if(l.size() == 0) return;

		for(int i = 0; i < l.size(); i++) {
			int relevantNeighbours = 0;
			p = (AsciiPoint) l.get(i);
			if(p.getX() > 0 && this.getPixel(p.getX() - 1, p.getY()) == c)  relevantNeighbours++;
			if(p.getX() < this.getWidth() - 1 && this.getPixel(p.getX() + 1, p.getY()) == c) relevantNeighbours++; 
			if(p.getY() > 0 && this.getPixel(p.getX(), p.getY() - 1) == c ) relevantNeighbours++;
			if(p.getY() < this.getHeight() - 1 && this.getPixel(p.getX(), p.getY() + 1) == c) relevantNeighbours++;
			if(relevantNeighbours <= 1) this.setPixel(p, '.');
		}
		
	}

	/**
	 * Return AsciiPoint List of all occurances of the given char
	 * @params c char to look for
	 */
	public ArrayList<AsciiPoint> getPointList(char c) {
		int i, j;
		ArrayList<AsciiPoint> l = new ArrayList<AsciiPoint>();
		for(i = 0; i < this.height; i++) { 
			for(j = 0; j < this.width; j++) { 
				if(this.getPixel(j, i) == c) {
					l.add(new AsciiPoint(j, i));
				}
			}
		}
		return l;
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
	 * Transpose Image. Makes columns rows and vice versa
	 */
	public void transpose() {

		int i, j;
		char[][] tmp = new char[this.width][this.height];

		for(i = 0; i < this.height; i++) {
			for(j = 0; j < this.width; j++) {
				tmp[j][i] = this.image[i][j];
			}
		}

		this.image = tmp;
		this.height = this.width;
		this.width = i;	

	}

	/**
	 * Flood fill image.
	 * @param x x-choord of starting char 
	 * @param y y-choord of starting char
	 * @param c char to fill area with
	 */
	public void fill(int x, int y, char c) {

		char current = this.getPixel(x, y);
		this.setPixel(x, y, c);

		if(x > 0 && this.getPixel(x - 1, y) == current) {
			this.fill(x - 1, y, c);
		}

		if(x < this.width - 1 && this.getPixel(x + 1, y) == current) {
			this.fill(x + 1, y, c);
		}

		if(y > 0 && this.getPixel(x, y - 1) == current) {
			this.fill(x, y - 1, c);
		}

		if(y < this.height - 1 && this.getPixel(x, y + 1) == current) {
			this.fill(x, y + 1, c);
		}
		
	}

	/**
	 * Format Image for String Output (add newlines after each row
	 * @return Formatted String
	 */
	public String toString() {

		int i, j;
		String s = "";

		for(i = 0; i < this.getHeight(); i++) {
			for(j = 0; j < this.getWidth(); j++) {
				s += this.image[i][j];
			}
		//	if(i < this.getHeight() - 1) {
				s += "\n";
		//	}
		}

		return s;
	}	

}
