package mvc;

import java.util.HashSet;
import java.util.Set;

public class Model {

	private final Set<View> views = new HashSet<View>();

	public void addView(View v) {
		this.views.add(v);
	}

	public void notifyViews() {
		for (View v : this.views)
			v.modelChanged(this);
	}

	public void removeView(View v) {
		this.views.remove(v);
	}
}
