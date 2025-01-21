package resources;

/**
 * Class in charge of the synchronization between workers. It gives priority to writers. It uses Java monitors for mutual exclusion
 **/
public class Synchronizer {
	// State variables
	protected int writers = 0;
	protected int readers = 0;
	protected int waiting = 0;
	
	protected boolean invariant(int readers, int writers) {
		return (((writers == 0 && readers >= 0) || (writers == 1 && readers == 0)) && waiting == 0);
	}
	
	protected boolean canRead() {
		return this.invariant(this.readers + 1, this.writers);
	}
	
	protected boolean canWrite() {
		return this.invariant(this.readers, this.writers + 1);
	}
	
	public synchronized void askWriting() {
		while (!canWrite()) {
			this.waiting++;
			try {
				wait();
			} catch (Exception e) {
			}
			this.waiting--;
		}
		this.writers++;
	}
	
	public synchronized void askRead() {
		while (!canRead()) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		this.readers++;
	}
	
	public synchronized void endWriting() {
		this.writers--;
		notifyAll();
	}
	
	public synchronized void endRead() {
		this.readers--;
		notifyAll();
	}
	
}
