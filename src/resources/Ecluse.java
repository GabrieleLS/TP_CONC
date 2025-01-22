package resources;

public class Ecluse {
	public int position;

	public Ecluse() {
		this.position = 0;
	}
	
	public void change() {
		if (position==0) {
			position = 1;
		} else {
			position = 0;
		}
	}
}
