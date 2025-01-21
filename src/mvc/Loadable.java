package mvc;

import java.util.List;

public interface Loadable<E> {
	List<E> load();
}
