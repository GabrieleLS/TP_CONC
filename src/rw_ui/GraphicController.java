package rw_ui;

import mvc.ListModel;

public class GraphicController {
	/** lists for the three categories */
	private final ListModel<String> workingList;
	private final ListModel<String> idleReaders;
	private final ListModel<String> idleWriters;

	public GraphicController(ListModel<String> workingList, ListModel<String> idleWriters, ListModel<String> idleReaders) {
		this.workingList = workingList;
		this.idleWriters = idleWriters;
		this.idleReaders = idleReaders;
	}

	public synchronized void endWriting(String name) {
		System.out.printf("le redacteur %s a fini d'ecrire\n", name);
		this.workingList.remove(name);
	}

	public synchronized void endReading(String name) {
		System.out.printf("le lecteur %s a fini de lire\n", name);
		this.workingList.remove(name);
	}

	public synchronized void write(String name) {
		System.out.printf("le redacteur %s est en pleine ecriture\n", name);
		this.idleWriters.remove(new String(name));
		this.workingList.add(name);
	}

	public synchronized void read(String name) {
		System.out.printf("le lecteur %s est en pleine lecture\n", name);
		this.idleReaders.remove(new String(name));
		this.workingList.add(name);
	}

	public synchronized void wantToWrite(String name) {
		System.out.printf("le redacteur %s veut ecrire\n", name);
		this.idleWriters.add(name);
	}

	public synchronized void wantToRead(String name) {
		System.out.printf("le lecteur %s veut lire\n", name);
		this.idleReaders.add(name);
	}
}
