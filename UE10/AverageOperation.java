public class AverageOperation extends FilterOperation {

	public  AverageOperation() {
	}

	public int filter(int[] values)  {

		double avg = 0;

		for(int i = 0; i < values.length; i++) {
			avg += values[i];
		}
		
		return (int) Math.round(avg / values.length);
	}	
	

}
