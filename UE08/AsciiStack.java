public class AsciiStack {

	private AsciiStackNode head;

	public AsciiStack() {
	}

	public boolean empty() {
		return this.head == null;
	}

	public AsciiImage pop() {

		if(this.empty()) return null;
		AsciiStackNode n = this.head;
		this.head = n.next;
		return n.image;

	}

	public AsciiImage peek() {

		if(this.empty()) return null;
		return this.head.image;

	}

	public void push(AsciiImage img) {

		this.head = new AsciiStackNode(img, this.head);

	} 

	public int size() {
		
		return (this.empty()?0:this.head.size());

	}

	private class AsciiStackNode {

		public AsciiImage image;
		public AsciiStackNode next;

		public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
			this.image = image;
			this.next = next;
		}

		public int size() {
			if(this.next == null) return 1;
			return this.next.size() + 1;
		}

	}

}
