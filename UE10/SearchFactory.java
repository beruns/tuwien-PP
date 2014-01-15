import java.util.Scanner;

public class SearchFactory implements Factory {

	private MetricSet<AsciiImage> saved;

	public SearchFactory(MetricSet<AsciiImage> saved) {
		
		this.saved = saved;	
	
	}

	public Operation create(Scanner sc) throws FactoryException {

		String type = "";
		Metric<AsciiImage> m;

		if(!sc.hasNext() || (type = sc.next()) != type || !(type.equals("uniquechars") || type.equals("pixelcount"))) {
			throw new FactoryException("Invaild Arguments " + type);
		}

		m = (type.equals("uniquechars")?new UniqueCharsMetric():new PixelCountMetric());
		

		return new SearchOperation(this.saved, m);

	}

}
