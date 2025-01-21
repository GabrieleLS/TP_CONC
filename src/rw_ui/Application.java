package rw_ui;

import mvc.ListModel;
import resources.Boat;
import resources.Synchronizer;


public class Application {
	public static void main(String[] args) {
		ListModel<String> workingList = new ListModel<>();
		ListModel<String> idleReaders = new ListModel<>();
		ListModel<String> idleWriters = new ListModel<>();
		RWFrame frame = new RWFrame(workingList, idleWriters, idleReaders);
		GraphicController controller = new GraphicController(workingList, idleWriters, idleReaders);
		Synchronizer synchro = new Synchronizer();
		frame.register(new Boat("Lecteur1", 10000, 4000, synchro, controller));
		frame.register(new Boat("Lecteur2", 6000, 15000, synchro, controller));
		frame.register(new Boat("Lecteur3", 4000, 20000, synchro, controller));
		frame.register(new Boat("Redacteur1", 5000, 15000, synchro, controller));
		frame.register(new Boat("Redacteur2", 6000, 9000, synchro, controller));

		System.out.println("Appuyez sur demarrer");
	}
}
