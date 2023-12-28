import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class sections {

	int capcity;
	int emptySize;
	int used;
	private Map<String, Integer> box = new HashMap<String, Integer>();

	sections(int x) {
		emptySize = capcity = x;
	}

	public boolean storeItem(String id, int size) {
		if (size > emptySize) {
			System.out.println("No Space TO Store");
			return false;
		}
		box.put(id, size);
		used = used + size;
		emptySize = emptySize - size;
		return true;
	}

	public boolean searchItem(String id) {
		return box.containsKey(id);
	}

	public void removeItem(String id) {
		if (box.containsKey(id) == false) {
			System.out.println("Order Not Found");
			return;
		}
		used = used - box.get(id);
		;
		emptySize = emptySize + box.get(id);
		box.remove(id);
	}

	public void print() {
		System.out.println("----------------------------------------");
		String format = "%-10s%-10s%n";

		System.out.printf(format, "orderID", "Size");
		for (Entry<String, Integer> entry : box.entrySet()) {
			System.out.printf(format, entry.getKey(), entry.getValue());
		}
		System.out.println("----------------------------------------");
	}

	public String save() {
		String obj = "";

		for (Map.Entry<String, Integer> entry : box.entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue() + "\n" + obj;
		}
		return obj;
	}

}
