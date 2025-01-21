package rw_ui;

import mvc.ListModel;
import resources.Boat;


public class Application {
	public static void main(String[] args) {
		ListModel<String> workingList = new ListModel<>();
		ListModel<String> idleReaders = new ListModel<>();
		ListModel<String> idleWriters = new ListModel<>();
		RWFrame frame = new RWFrame(workingList, idleWriters, idleReaders);
		//GraphicController controller = new GraphicController(workingList, idleWriters, idleReaders);
		//Synchronizer synchro = new Synchronizer();
		frame.register(new Boat());
		frame.register(new Boat());
		frame.register(new Boat());
		frame.register(new Boat());
		frame.register(new Boat());

		System.out.println("Appuyez sur demarrer");
	}
}
