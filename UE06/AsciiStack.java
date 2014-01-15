
public class AsciiStack {

	int increment;
	int capacity;
	int size;
	AsciiImage[] stack;

	public AsciiStack(int increment) {
		this.increment = increment;
		this.capacity = increment;
		this.size = 0;
		this.stack = new AsciiImage[this.capacity];
	}

	public int capacity() {
		return this.capacity;
	}

	public boolean empty(){
		return (this.size == 0);
	}

	public AsciiImage pop() {

		if(this.size == 0) {
			return null;
		}

		AsciiImage i = this.stack[--this.size];
		this.stack[this.size] = null;
		this.shrinkIfPossible();
		return i;

	}

	public AsciiImage peek() {

		if(this.size == 0) {
			return null;
		} else {
			return this.stack[this.size];
		}
		
	}

	public void push(AsciiImage img) {
		this.growIfNeeded();
		this.stack[this.size++] = img;
	}

	public int size() {
		return this.size;
	}

	private void growIfNeeded() {
		
		if(this.size == this.capacity) {

			int i;
			this.capacity += this.increment;
			AsciiImage[] s = new AsciiImage[this.capacity];

			for(i = 0; i<size; i++) {
				s[i] = this.stack[i];
			}
			
			this.stack = s;
			
		}
		
	}

	private void shrinkIfPossible() {

		if(this.size < this.capacity - this.increment && this.capacity > this.increment) {

			int i;
			this.capacity -= this.increment;
			AsciiImage[] s = new AsciiImage[this.capacity];

			for(i = 0; i<size; i++) {
				s[i] = this.stack[i];
			}
			
			this.stack = s;
		}
	}
	

}
