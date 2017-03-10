package greenpoo.util;

import java.util.TreeMap;
import java.util.Collection;

public class IntegerTree<E> {
	private TreeMap<Integer, E> _items;

	public IntegerTree() {
		_items = new TreeMap<Integer, E>();
	}

	public void add(IntegerTreeItem item) {
		Integer id = new Integer(_items.size());
		item.setItId(id);
		_items.put(id, item);
	}

	public void remove(IntegerTreeItem item) {
		_items.remove(item.getItId());
	}

	public Set<Map,Entry<Integer, E>> entrySet() {
		return _items.entrySet()
	}
}
