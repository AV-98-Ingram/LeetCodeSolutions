import java.util.LinkedList;

class BoundedBlockingQueue {

	int size = 0;
	LinkedList<Integer> queue = new LinkedList<>();
	final int cap;

	public BoundedBlockingQueue(int capacity) {
		this.cap = capacity;
	}

	public void enqueue(int element) throws InterruptedException {
		synchronized (queue) {
			while (size == cap)
				queue.wait();
			queue.addFirst(element);
			size++;
			queue.notify(); // release comsumer
		}
	}

	public int dequeue() throws InterruptedException {
		synchronized (queue) {
			while (size == 0)
				queue.wait();

			int val = queue.removeLast();

			size--;
			queue.notify(); // releast producer
			return val;
		}
	}

	public int size() {
		synchronized (queue) {
			return size;
		}
	}
}
