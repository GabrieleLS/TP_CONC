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
		private Ecluse ecluse;

		public Boat(Runnable task, String name, int position, int workTime, Synchronizer synchro, GraphicController controller, Ecluse ecluse) {
			super(task, name);
			this.position = position;
			this.workTime = workTime;
			this.synchro = synchro;
			this.controller = controller;
			this.ecluse = ecluse;
		}

		public Boat(String name, int position, int workTime, Synchronizer synchro, GraphicController controller, Ecluse ecluse) {
			super(name);
			this.position = position;
			this.workTime = workTime;
			this.synchro = synchro;
			this.controller = controller;
			this.ecluse = ecluse;
		}

		protected void work() {
			waitFor(this.workTime);
		}
		protected void rest() {
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
				rest();
				if (this.position==0) {
					this.controller.wantToPassA(name);
				} else {
					this.controller.wantToPassB(name);
				}
				this.synchro.askUsing(this.ecluse, this.position, this.controller.ecluseEtat);
				this.controller.use(name);
				this.controller.mvtEcluse();
				work();
				this.controller.endUsing(name);
				waitFor(1000);
				this.synchro.endUsing();
				
			} while (true);
		}

}
