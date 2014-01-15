import java.util.ArrayList;

public class Histogram {

	/**
	 * Generate Histogram for given AsciiImage
	 * @param img AsciiImage
	 */
	public static AsciiImage getHistogram(AsciiImage img) {

		String newCharset = img.getCharset();
		int captions[] = calcCaption(img);

		/* We need to put each char in one of our captions (they are ints actually, but they will be inserted as chars) into our valid
		   chars string ...
		 */	
		for(int i : captions) {
			String s = Integer.toString(i);
			for(int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				newCharset += (newCharset.indexOf(c) < 0?c:"");
			}
		}
		
		/* ... And also '#' and '.' */
		newCharset += (newCharset.indexOf('#') < 0?"#":"") + (newCharset.indexOf('.') < 0?".":"");	
		
		/* Create our Histogram AsciiImage with the new charset */
		AsciiImage H = createHistogramm(img, newCharset);
		/* Draw the Cations (and the legend) */
		imageCaption(H, img, captions);
		/* Draw the bars */
		drawLines(H, img, captions);
			
		/* Return Histogram */
		return H;
	}

	/**
	 * Create new AsciiImage containing the Histogram Data
	 * @param img AsciiImage to create the Histogram from
	 * @param charset String containing all new valid chars
	 * @return AsciiImage containing the  Histogramm
	 */
	private static AsciiImage createHistogramm(AsciiImage img, String newCharset) {

		AsciiImage H = new AsciiImage(3 + img.getCharset().length(), 16, newCharset);

		/* We don't know if '.' was the brightest char in the original image, so we'll fill it dotts */
		for(int i = 0; i < H.getHeight(); i++) {
			for(int j = 0; j < H.getWidth(); j++) {
				H.setPixel(j, i, '.');
			}
		}
		return H;
		
	}

	/**
	 * Calculte Caption values
	 * @param img AsciiImage to create Histogram from
	 * @return int array containing the captions in descending order
	 */
	private static int[] calcCaption(AsciiImage img) {

		int captions[] = new int[15], i;
		double highest = 0;
		
		/* How many chars does our image hold altogether */
		double area = img.getHeight() * img.getWidth();

		/* the difference between two entries in our array */
		double factor;
		String charset = img.getCharset();
		
		for(i = 0; i < charset.length(); i++) {

			/* for each valid char */
			char c = charset.charAt(i);
			/* we count it's occurance and replace current highest if higher */
			ArrayList<AsciiPoint> list = img.getPointList(c);
			if(list.size() > highest) highest = list.size();

		}

		/* Round up in percent */
		highest = Math.round(highest / area * 100);

		/* Calculate factor */
		factor = highest / 15;
		
		/* First entry shall be the largest value */
		captions[0] = (int) highest;

		/* All other are calculates from that ound rounded up */
		for(i = 1; i < captions.length; i++) {
			captions[i] = (int) Math.round(highest - (i*factor));
		}

		return captions;
	
	}

	/**
	 * Find out how high tghe bar for a char shal be (base upon it's rounded occurance percentage 
	 * @param p percentage of occurance
	 * @param captions int[] array of captions
	 * @return height of bar
	 */
	private static int calcLineHeight(int p, int captions[]) {
		/* if percentage is really zero, don't draw a line */
		if(p == 0) return 0;
		int line = 1;
		/* We'll check the value against each of our captions. The first caption greater than  or equal to the value indicates out bar height */
		for(int i = captions.length - 1; i >= 0; i--) {
			if(p <= captions[i]) return line;
			line++;
		}
		/* percentage seems to be a bit larger than our highest caption. draw a full bar*/
		return 15;
	}

	/**
	 * Draw the percentage bars
	 * @param H AsciiImage containing the Histogram
	 * @param img original AsciiImage
	 * @param captions Captions array
	 */
	private static void drawLines(AsciiImage H, AsciiImage img, int captions[]) {
		
		String charset = img.getCharset();
		double area = img.getHeight() * img.getWidth();

		/* draw percentage bar for each char in the original charset */
		for(int i = 0; i < charset.length(); i++) {

			/* Calcluate percentage */
			char c = charset.charAt(i);
			double occurance = img.getPointList(c).size();
			int p = (int) Math.ceil(occurance / area * 100);
	
			/* calculate bar height */
			int lineHeight = calcLineHeight(p, captions);

			/* draw the bar */
			for(int j = 1; j <= lineHeight; j++) {
				H.setPixel(i + 3, H.getHeight() - (j + 1), '#');
			}	
		}

	}

	/**
	 * Draw the caption and the legend
	 * @param H AsciiImage holding the Histogram
	 * @param img original AsciiImage
	 * @param captions int array containing all our caption values (the percentage steps) 
	 */
	private static void imageCaption(AsciiImage H, AsciiImage img, int[] captions) {

		String charset = img.getCharset();

		/* We only draw every second caption */
		for(int i = 0; i < captions.length; i += 2) {

			String s = Integer.toString(captions[i]);
			int pos = 2;

			/* Transform int to string and put int into the image charwise */
			for(int j = s.length() - 1; j >= 0; j--) {

				H.setPixel(pos, i, s.charAt(j));
				pos --;

			}

		}

		/* Draw the legend */
		for(int i = 0; i < charset.length(); i++) {

			H.setPixel(i + 3, 15, charset.charAt(i));

		}

	}

}
