import java.util.ArrayList;

public class AsciiImage {

	private int height;
	private int width;
	private String charset;
	private char[][] image;

	private boolean validCharset(String charset) {

		if(charset.length() == 0) return false;
	
		for(int i = 0; i < charset.length(); i++) {
			char c = charset.charAt(i);
			if(charset.indexOf(c, i+1) >= 0) {
				return false;
			}
		}

		return true;
	
	}

	public AsciiImage(int width, int height, String charset) {

		if(width <= 0 || height <= 0 || !this.validCharset(charset)) {
			throw new IllegalArgumentException();
		}

		this.height = height;
		this.width = width;
		this.charset = charset;
	
		char l = this.charset.charAt(this.charset.length() - 1);

		this.image = new char[this.height][this.width];

		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				this.setPixel(j, i, l);
			}
		}
		
			
	}

	public AsciiImage(AsciiImage img) {

		this(img.getWidth(), img.getHeight(), img.getCharset());
		
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				this.setPixel(j, i, img.getPixel(j, i));
			}
		}
		
	}

	public String getCharset() {

		return this.charset;

	}

	public int getHeight() {
	
		return this.height;

	}

	public int getWidth() {

		return this.width;

	}

	public char getPixel(int x, int y) {

		if(x < 0 || x >= this.width || y < 0 || y >= this.height) {
			throw new IndexOutOfBoundsException(x + " " + y);
		}
	
		return this.image[y][x];

	}

	public char getPixel(AsciiPoint p) {

		return this.getPixel(p.getX(), p.getY());

	}

	public ArrayList<AsciiPoint> getPointList(char c) {

		ArrayList<AsciiPoint> l = new ArrayList<AsciiPoint>();		

		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				if(this.getPixel(j, i) == c) {
					l.add(new AsciiPoint(j, i));
				}
			}
		}

		return l;

	}

	public void setPixel(int x, int y, char c) {

		if(x < 0 || x >= this.width || y < 0 || y >= this.height || this.charset.indexOf(c) < 0) {
			throw new IndexOutOfBoundsException(x + " " + y);
		}

		this.image[y][x] = c;

	}

	public void setPixel(AsciiPoint p, char c) {

		this.setPixel(p.getX(), p.getY(), c);

	}

	public String toString() {

		String s = "";
		for(int i = 0; i < this.height; i++) {
			for(int j = 0; j < this.width; j++) {
				s += this.image[i][j];
			}
			s += "\n";
		}
		return s;
		
	}

}
