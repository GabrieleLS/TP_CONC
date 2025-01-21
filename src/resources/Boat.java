package resources;

import rw_ui.GraphicController;

public class Boat extends Thread {
		/** position specify if boat is in A (position = 0) or in B (position = 1) */
		private int position = 0;
		/** workTime specify the amount of time the boat needs to enter or get out of the basin */
		private int workTime = 0;
		/** synchronization object */
		protected Synchronizer synchro = new Synchronizer();
		/** controller */
		protected GraphicController controller;

		public Boat(Runnable task, String name, int position, int workTime, Synchronizer synchro, GraphicController controller) {
			super(task, name);
			this.position = position;
			this.workTime = workTime;
			this.synchro = synchro;
			this.controller = controller;
		}

		public Boat(String name, int position, int workTime, Synchronizer synchro, GraphicController controller) {
			super(name);
			this.position = position;
			this.workTime = workTime;
			this.synchro = synchro;
			this.controller = controller;
		}

		protected void work() {
			waitFor(this.workTime);
		}


		private static void waitFor(int waitingTime) {
			try {
				sleep(waitingTime);
			} catch (InterruptedException e) {
			}
		}
		
		/** Life of a reader */
		@Override
		public void run() {
			String name = this.getName();
			do {
				System.out.printf("le bateau %s cherche a passer\n", name);

				System.out.printf("le lecteur %s lit\n", name);
				work();

				System.out.printf("le lecteur %s a fini de lire\n", name);
			} while (true);
		}

}
