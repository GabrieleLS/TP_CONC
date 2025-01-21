package mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListModel<E extends Object> extends Model implements Iterable<E> {

	private Loadable<E> source;
	protected final List<E> content = new ArrayList<E>();

	public ListModel() {}

	public ListModel(Loadable<E> source) {
		setSource(source);
	}

	public boolean add(E e) {
		boolean result = this.content.add(e);
		if (result)
			notifyViews();
		return result;
	}

	public void clear() {
		this.content.clear();
		notifyViews();
	}

	public E get(int index) {
		return this.content.get(index);
	}

	public Iterator<E> iterator() {
		return this.content.iterator();
	}

	public void refresh() {
		this.content.clear();
		if (this.source != null) {
			List<E> data = this.source.load();
			if (data != null)
				this.content.addAll(data);
		}
		notifyViews();
	}

	public void remove(E e) {
		this.content.remove(e);
		notifyViews();
	}

	public int size() {
		return this.content.size();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("[");
		boolean first = true;
		for (E e : this.content) {
			res.append((first ? "" : ",")).append(e);
			first = false;
		}
		return res.append("]").toString();
	}

	protected void setSource(Loadable<E> source) {
		if (source != null) {
			this.source = source;
		}
		refresh();
	}

	protected void synchronise() {
		refresh();
	}
}
