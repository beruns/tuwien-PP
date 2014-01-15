import java.util.Arrays;

public class MedianOperation implements Operation {

	public MedianOperation() {
	}

	public AsciiImage execute(AsciiImage img) {

		AsciiImage newImg = new AsciiImage(img);
		
		for(int i = 0; i < newImg.getHeight(); i++) {
			for(int j = 0; j < newImg.getWidth(); j++) {
				newImg.setPixel(j, i, this.getMedian(img, new AsciiPoint(j, i)));
			}
		}
	
		//System.out.println(" At First: " + this.getMedian(img, new AsciiPoint(0, 0)));
		//return new AsciiImage(0, 0, "AA");	
		return newImg;

	}

	private char getMedian(AsciiImage img, AsciiPoint p) {

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
		
		return charset.charAt(m[4]);

	}

}
