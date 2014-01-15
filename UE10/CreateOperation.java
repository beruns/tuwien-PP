public class CreateOperation implements Operation {

	private int width;
	private int height;
	private String charset;

	public CreateOperation(int width, int height, String charset) {

		this.width = width;
		this.height = height;		
		this.charset = charset;

	}
	
	public AsciiImage execute(AsciiImage img) throws OperationException {
		return new AsciiImage(this.width, this.height, this.charset);
	}

}
