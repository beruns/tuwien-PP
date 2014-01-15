import java.util.Collection; 
import java.util.LinkedHashSet;
import java.util.Iterator;

public class MetricSet<E> extends LinkedHashSet<E> {

	public MetricSet() {
		super(); 
	}

	public MetricSet(Collection<? extends E> c) {
		super(c); 
	}

	public MetricSet<E> search(E e, Metric<? super E> m) {

		int min = -1;
		int d;
		E current;

		MetricSet<E> ret = new MetricSet<E>();
		Iterator<E> it = this.iterator();

		while(it.hasNext()) {

			current = (E) it.next();
			d = m.distance(e, current);

			if(min == -1) {

				min = d;
				ret.add(current);
				
			} else if(d == min) {

				ret.add(current);			

			} else if(d < min) {
			
				ret.clear();
				min = d;
				ret.add(current);
			
			}
		}

		return ret;
		
	}


}
