public class ClearOperation implements Operation {

	public ClearOperation() {
	}

	public AsciiImage execute(AsciiImage img) {

		AsciiImage newImg = new AsciiImage(img.getWidth(), img.getHeight(), img.getCharset());
		return newImg;

	}

}
