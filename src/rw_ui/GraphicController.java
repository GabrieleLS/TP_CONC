package rw_ui;

import mvc.ListModel;
import resources.Ecluse;

public class GraphicController {
	/** lists for the three categories */
	private final ListModel<String> ecluseList;
	private final ListModel<String> idleBoatA;
	private final ListModel<String> idleBoatB;
	public final ListModel<String> ecluseEtat;
	public Ecluse ecluse;

	public GraphicController(ListModel<String> ecluseList, ListModel<String> idleBoatB, ListModel<String> idleBoatA, ListModel<String> ecluseEtat, Ecluse ecluse) {
		this.ecluseList = ecluseList;
		this.idleBoatB = idleBoatB;
		this.idleBoatA = idleBoatA;
		this.ecluseEtat = ecluseEtat;
		this.ecluse = ecluse;
	}

	public synchronized void endUsing(String name) {
		this.ecluse.change();
		this.ecluseEtat.clear();
		System.out.printf("les portes de l'ecluse s'ouvrent\n");
		this.ecluseEtat.add("les portes de l'ecluse sont ouvertes");
		System.out.printf("le bateau %s est passé\n", name);
		if (this.ecluse.position==0) {
			System.out.printf("l'ecluse est en A\n");
			this.ecluseEtat.add("l'ecluse est en A");
		} else {
			System.out.printf("l'ecluse est en B\n");
			this.ecluseEtat.add("l'ecluse est en B");
		}
		this.ecluseList.remove(name);
	}

	public synchronized void use(String name) {
		this.ecluseEtat.clear();
		System.out.printf("le bateau %s est dans l'ecluse\n", name);
		if (this.ecluse.position==0) {
			idleBoatA.remove(new String(name));
		} else {
			idleBoatB.remove(new String(name));
		}
		this.ecluseList.add(name);
	}
	
	public synchronized void mvtEcluse() {
		System.out.printf("les portes de l'ecluse se ferment\n");
		this.ecluseEtat.add("les portes de l'ecluse sont fermées");
		if (this.ecluse.position==0) {
			System.out.printf("l'eau dans l'ecluse monte\n");
			this.ecluseEtat.add("l'eau dans l'ecluse monte");
		} else {
			System.out.printf("l'eau dans l'ecluse descend\n");
			this.ecluseEtat.add("l'eau dans l'ecluse descend");
		}
	}

	public synchronized void wantToPassA(String name) {
		System.out.printf("le bateau %s veut passer en A\n", name);
		this.idleBoatA.add(name);
	}
	
	public synchronized void wantToPassB(String name) {
		System.out.printf("le bateau %s veut passer en B\n", name);
		this.idleBoatB.add(name);
	}
}
