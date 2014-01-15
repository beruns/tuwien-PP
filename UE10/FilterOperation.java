import java.util.Arrays;

public abstract class FilterOperation implements Operation {

	public FilterOperation() {
	}

	public AsciiImage execute(AsciiImage img) {

		AsciiImage newImg = new AsciiImage(img);
		String charset = newImg.getCharset();
		
		for(int i = 0; i < newImg.getHeight(); i++) {
			for(int j = 0; j < newImg.getWidth(); j++) {
				newImg.setPixel(j, i, charset.charAt(this.filter(this.getNeighbours(img, new AsciiPoint(j, i)))));
			}
		}
	
		return newImg;

	}

	private int[] getNeighbours(AsciiImage img, AsciiPoint p) {

		int[] m = new int[9];
		int i, j, index = 0;

		String charset = img.getCharset();
		int l = charset.length() - 1;	

		for(i = p.getY() - 1; i <= p.getY() + 1; i++) {
		
			for(j = p.getX() - 1; j <= p.getX() + 1; j++) {
				try {
					m[index] = charset.indexOf(img.getPixel(j, i));
				} catch(IndexOutOfBoundsException e) {
					m[index] = l;
				}
				index++;
			}
			
		}

		Arrays.sort(m);
		
		return m;

	}
	
	public abstract int filter(int[] values);

}
