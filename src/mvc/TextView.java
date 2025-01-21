package mvc;

import javax.swing.JTextArea;

public class TextView extends JTextArea implements View {

	private ListModel<?> l = null;

	public TextView(ListModel<?> m) {
		this.l = m;
		this.l.addView(this);
	}

    	public void modelChanged(Model m) {
		StringBuilder res = new StringBuilder();
		for (Object element : this.l)
			res.append(element).append("\n");
		setText(res.toString());
	}
    
}
