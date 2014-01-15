public class LoadOperation implements Operation {

	private String data;


	public LoadOperation(String data) {

		this.data = data;	
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {
		
		String[] rows = this.data.split("\n");
		int w = 0;

		if(rows.length - 1 != img.getHeight()) {
			throw new OperationException("Expected Height of " + img.getHeight() + ". " + rows.length + " given");
		}	

		AsciiImage newImg = new AsciiImage(img);
		for(int i = 0; i < rows.length - 1; i++) {
			if(w != 0 && w != rows[i].length()) {
				throw new OperationException("Expected Width of " + img.getWidth() + ". " + rows[i].length() + " given");
			} else {
				w = rows[i].length();
			}

			for(int j = 0; j < w; j++) {
				try {
					newImg.setPixel(j, i, rows[i].charAt(j));
				} catch(IndexOutOfBoundsException e) {
					throw new OperationException();
				}
			}
			
		}
		
		return newImg;
		
	}

}
