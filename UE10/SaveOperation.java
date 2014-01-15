public class SaveOperation implements Operation {

	MetricSet<AsciiImage> saved;

	public SaveOperation(MetricSet<AsciiImage> saved) {
		this.saved = saved;
	}

	public AsciiImage execute(AsciiImage img) throws OperationException {
		if(!this.saved.add(img)) {
			throw new OperationException("Object already in Set");
		}
		return new AsciiImage(img);
	}

	public MetricSet<AsciiImage> getSaved() {
		return this.saved;
	}

}
