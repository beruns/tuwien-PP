public class BinaryOperation implements Operation {

	private char threshold;

	public BinaryOperation(char threshold) {
		this.threshold = threshold;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {

		AsciiImage newImg = new AsciiImage(img);
		String charset = img.getCharset();

		char h = charset.charAt(0);
		char l = charset.charAt(charset.length() - 1);

		int t;

		if((t = charset.indexOf(this.threshold)) < 0) {
			throw new OperationException("Invalid Character");
		}

		for(int i = 0; i < newImg.getHeight(); i++) {
			for(int j = 0; j < newImg.getWidth(); j++) {
				if(charset.indexOf(newImg.getPixel(j, i)) < t) {
					newImg.setPixel(j, i, h);
				} else {
					newImg.setPixel(j, i, l);
				}
			}
		}
	
		return newImg;

	}

}
