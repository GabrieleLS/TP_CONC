package rw_ui;

import mvc.ListModel;
import resources.Boat;
import resources.Ecluse;
import resources.Synchronizer;


public class Application {
	public static void main(String[] args) {
		ListModel<String> ecluseList = new ListModel<>();
		ListModel<String> idleBoatA = new ListModel<>();
		ListModel<String> idleBoatB = new ListModel<>();
		ListModel<String> ecluseEtat = new ListModel<>();
		RWFrame frame = new RWFrame(ecluseList, idleBoatB, idleBoatA, ecluseEtat);
		Ecluse ecluse = new Ecluse();
		GraphicController controller = new GraphicController(ecluseList, idleBoatB, idleBoatA, ecluseEtat, ecluse);
		Synchronizer synchro = new Synchronizer();
		frame.register(new Boat("Boat1", 0, 3000, synchro, controller, ecluse));
		frame.register(new Boat("Boat2", 0, 3000, synchro, controller, ecluse));
		frame.register(new Boat("Boat3", 1, 3000, synchro, controller, ecluse));
		frame.register(new Boat("Boat4", 1, 3000, synchro, controller, ecluse));
		frame.register(new Boat("Boat5", 1, 3000, synchro, controller, ecluse));

		System.out.println("Appuyez sur demarrer");
		System.out.println("l'ecluse est en A");
		ecluseEtat.add("l'ecluse est en A");
	}
}
