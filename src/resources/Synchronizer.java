package resources;

/**
 * Class in charge of the synchronization between boats. It gives priority to boats with the same height. It uses Java monitors for mutual exclusion
 **/
public class Synchronizer {
	// State variables
	protected int boat = 0;
	protected int waiting = 0;
	
	protected boolean canUse() {
		return this.boat==0;
	}
	
	public synchronized void askUsing() {
		while (!canUse()) {
			this.waiting++;
			try {
				wait();
			} catch (Exception e) {
			}
			this.waiting--;
		}
		this.boat++;
	}
	
	public synchronized void endUsing() {
		this.boat--;
		notifyAll();
	}
	
}
