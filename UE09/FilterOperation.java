import java.util.Arrays;

public abstract class FilterOperation implements Operation {

	int depth = 3;
	String edgeHandling = "x";

	public FilterOperation() {
	}

	/* Mutate default depth val */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/* Mutate default edgeHandling val */
	public void setEdgeHandling(String edgeHandling) {
		this.edgeHandling = edgeHandling;
	}

	/**
	 * @param img AsciiImage to performa filter on
	 */
	public AsciiImage execute(AsciiImage img) throws OperationException {

		AsciiImage newImg = new AsciiImage(img);
		String charset = newImg.getCharset();
		
		/* Iterate over all pixels  */
		for(int i = 0; i < newImg.getHeight(); i++) {
			for(int j = 0; j < newImg.getWidth(); j++) {
				/* and use this.filter (to be implementes by FilterType), based on all neighbours to the current pixel */
				newImg.setPixel(j, i, charset.charAt(this.filter(this.getNeighbours(img, new AsciiPoint(j, i)))));
			}
		}
	
		return newImg;

	}

	/**
	 * Get all neighbours, based upon depth and edgeHandling
	 * @return sorted array of Ascii Values of all the neighbours
	 */
	private int[] getNeighbours(AsciiImage img, AsciiPoint p) throws OperationException {

		
		int[] m = new int[this.depth * this.depth];
		int i, j, k, l, index = 0, n = this.depth / 2;
		

		String charset = img.getCharset();

		/* from depth/2 lines above the given point to depth/2 lines below */
		for(i = p.getY() - n; i <= p.getY() + n; i++) {
		
			/* from depth/2 columns left to the given point to depth/2 column rigth */
			for(j = p.getX() - n; j <= p.getX() + n; j++) {
				try {
					/* If it's an in-Image neighbour, use it's pixel's Ascii value */
					m[index] = charset.indexOf(img.getPixel(j, i));
				} catch(IndexOutOfBoundsException e) {
					/* Neighbour is out of image bounds, so set the value based upon the edgehandling*/
					if(this.edgeHandling.equals("x")) {
						m[index] = charset.length() - 1;
					} else if(this.edgeHandling.equals("circular")) {
					
						/* If we leave the image to the left side, we'll enter it from the right and vv */
						if(j < 0) { k = img.getWidth() + (j % img.getWidth()); }
						else if(j >= img.getWidth())  { k = j % img.getWidth(); }
						else { k = j; }

						/* If we leave the image to the top side, we'll enter it from the bottom and vv */
						if(i < 0) { l = img.getHeight() + (i % img.getHeight()); }
						else if(i >= img.getHeight()) { l = i % img.getHeight(); }
						else { l = i; }

						m[index] = charset.indexOf(img.getPixel(k, l));
					
					} else if(this.edgeHandling.equals("replicate")) {		 

						/* If we leave the image to the left side we'll keep the value of the last valid pixel (same to the right) */
						if(j < 0) { k = 0; }
						else if(j >= img.getWidth()) { k = img.getWidth() - 1; }
						else { k = j; }

						/* If we leave the image to the top side we'll keep the value of the last valid pixel (same to the bottom)*/
						if(i < 0) { l = 0; }
						else if(i >= img.getHeight()) { l = img.getHeight() - 1; }
						else { l = i; }

						m[index] = charset.indexOf(img.getPixel(k, l));

					} else if(this.edgeHandling.equals("symmetric")) {

						/* If we leave the image to the left side, we'll enter a mirrored image from the left and vv */
						if(j < 0) { k = -1 - (j % img.getWidth()); }
						else if(j >= img.getWidth()) { k = (img.getWidth() - (j % img.getWidth()) - 1); }
						else { k = j; }

						/* If we leave the image to the top side, we'll enter a mirrored image from the bottom and vv */
						if(i < 0) { l = -1 - (i % img.getHeight()); }
						else if(i >= img.getHeight()) { l = (img.getHeight() - (i % img.getHeight()) - 1); }
						else { l = i; }

						m[index] = charset.indexOf(img.getPixel(k, l));
					
					}
				}
				index++;
			}
			
		}

		/* Sort the array, this is needed for MedianFilter for example */
		Arrays.sort(m);

		return m;

	}

	/* To be implemented in FilterType */	
	public abstract int filter(int[] values);

}
