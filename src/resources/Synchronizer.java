package resources;

import mvc.ListModel;

/**
 * Class in charge of the synchronization between boats. It gives priority to boats with the same height. It uses Java monitors for mutual exclusion
 **/
public class Synchronizer {
	// State variables
	protected int boat = 0;
	protected int waitingA = 0;
	protected int waitingB = 0;
	
	protected boolean canUse(Ecluse ecluse, int position, ListModel<String> ecluseEtat) {
		return ((this.boat==0) && ((ecluse.position==position) || (waiting(ecluse, ecluseEtat))));
	}
	
	protected boolean waiting(Ecluse ecluse, ListModel<String> ecluseEtat) {
		if (ecluse.position==0 && waitingA==0 && waitingB!=0) {
			ecluse.change();
			System.out.printf("les portes de l'ecluse se ferment\n");
			System.out.printf("l'eau dans l'ecluse monte\n");
			ecluseEtat.add("l'eau dans l'ecluse monte");
			System.out.printf("les portes de l'ecluse s'ouvrent\n");
			return true;
		} else if (ecluse.position==1 && waitingB==0 && waitingA!=0) {
			ecluse.change();
			System.out.printf("les portes de l'ecluse se ferment\n");
			System.out.printf("l'eau dans l'ecluse descend\n");
			ecluseEtat.add("l'eau dans l'ecluse descend");
			System.out.printf("les portes de l'ecluse s'ouvrent\n");
			return true;
		}
		return false;
	}
	
	public synchronized void askUsing(Ecluse ecluse, int position, ListModel<String> ecluseEtat) {
		while (!canUse(ecluse, position, ecluseEtat)) {
			if (position==0) {
				this.waitingA++;
			} else {
				this.waitingB++;
			}
			try {
				wait();
			} catch (Exception e) {
			}
			if (position==0) {
				this.waitingA--;
			} else {
				this.waitingB--;
			}
		}
		this.boat++;
	}
	
	public synchronized void endUsing() {
		this.boat--;
		notifyAll();
	}
	
}
