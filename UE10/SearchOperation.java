import java.util.Iterator;

public class SearchOperation implements Operation {

	MetricSet<AsciiImage> saved;
	Metric<AsciiImage> m;

	public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
		this.saved = saved;
		this.m = m;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {

		if(saved.isEmpty()) {
			throw new OperationException("No Items in Store");
		}

		MetricSet<AsciiImage> found = this.saved.search(img, this.m);
		Iterator<AsciiImage> it = found.iterator();
		
		return it.next();
		

	}

}
