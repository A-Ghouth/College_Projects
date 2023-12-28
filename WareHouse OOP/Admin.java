import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {
	WareHouseSystem storge;
	Menu menu;
	Scanner input = new Scanner(System.in);

	private boolean start = false;

	private final int fullSize = 100;
	private int emptySizeSection = 100;
	private int emptySize = 100;

	private Map<String, Integer> waitOrder;
	private Map<String, Integer> allOrders;
	private Map<String, sections> section;

	Admin(WareHouseSystem storge, Menu menu) {
		this.storge = storge;
		this.menu = menu;

		section = new HashMap<String, sections>();
		waitOrder = storge.getWaitOrder();
		allOrders = storge.getAllOrders();
	}

//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
///////////////////////////////////////////////////////////////////////////// Section
	public void creatSection(String name, int size) {
		if (size > emptySizeSection) {
			System.out.println("No Size");
			menu.menuSection();
		}
		section.put(name, new sections(size));
		emptySizeSection = emptySizeSection - size;

		// Because the restore files
		if (start == true)
			menu.menuSection();
	}

	public void viewSection() {
		int sectionList[][] = this.sectionArray();

		String format = "%-10s%-10s%-10s%-15s%n";

		System.out.println("There are " + section.size() + " Sections");
		System.out.println("Storge Full Size [ " + fullSize + " ] " + "\n" + "EmptySectionSize [ " + emptySizeSection
				+ " ] " + "\n" + "EmptySizeOrders [ " + emptySize + " ] ");
		System.out.println("--------------------------------------");
		System.out.printf(format, "Section", "Capcity", "Empty", "UsedSize");

		int i = 0;
		for (Map.Entry<String, sections> entry : section.entrySet()) {
			System.out.printf(format, "  " + entry.getKey(), "  " + sectionList[i][0], "  " + sectionList[i][1],
					"  " + sectionList[i][2]);
			i++;
		}
		System.out.println("--------------------------------------");
		menu.menuSection();
	}

	public void editSection(String id, int editSize) {
		if (!(section.containsKey(id))) {
			System.out.println("Section Does Not Exist");
			menu.menuSection();
		}
		if ((emptySizeSection + section.get(id).capcity) < editSize) {
			System.out.println("No More Size ");
			menu.menuSection();
		}
		if (!(section.get(id).used <= editSize)) {
			System.out.println("You cant edit Because there are items  ");
			menu.menuSection();
		}
		emptySizeSection = emptySizeSection + section.get(id).capcity - editSize;

		section.get(id).emptySize = editSize - section.get(id).used;
		section.get(id).capcity = editSize;

		menu.menuSection();
	}

	public void removSection(String id) {
		if (section.containsKey(id) == false) {
			System.out.println("Section Does Not exist");
			menu.menuSection();

		}
		if (section.get(id).used > 0) {
			System.out.println("You cant edit Because there are items");
			menu.menuSection();
		}
		emptySizeSection = emptySizeSection + section.get(id).capcity;
		section.remove(id);
		menu.menuSection();
	}

///////////////////////////////////////////////////////////////////////////// Orders
	public void pickOrder(String orderID) {
		if (waitOrder.containsKey(orderID) == false) {
			System.out.println("Order Does not Exist");
			menu.menuOrderAdmin();
		}

		String x = String.valueOf(waitOrder.get(orderID));
		int size = Integer.parseInt(x);

		if (size > emptySize) {
			System.out.println("No more Space");
			menu.menuOrderAdmin();
		}
		System.out.println("Wich Section Store ?");
		String name = input.next();

		if (!(section.containsKey(name))) {
			System.out.println("This Section Does Not Exist");
			menu.menuOrderAdmin();
		}
// Pass for check if there is size in section to store
		boolean pass;
		pass = section.get(name).storeItem(orderID, size);
		if (pass == true) {
			emptySize = emptySize - size;
			waitOrder.remove(orderID);

		}

		menu.menuOrderAdmin();
	}

	public void removeOrder(String idOrder) {
		if (!(allOrders.containsKey(idOrder))) {
			System.out.println("Item Does not Exist");
			menu.menuOrderAdmin();
		}

		for (Map.Entry<String, sections> entry : section.entrySet()) {
			if (entry.getValue().searchItem(idOrder) == true) {
				entry.getValue().removeItem(idOrder);
				System.out.println("Section [" + entry.getKey() + "] , Order ID [" + idOrder + "] --> Removed");
				break;
			}
		}
		String x = String.valueOf(allOrders.get(idOrder));
		emptySize = emptySize + Integer.parseInt(x);

		allOrders.remove(idOrder);
		if (waitOrder.containsKey(idOrder))
			waitOrder.remove(idOrder);
		menu.menuOrderAdmin();
	}

	public void searchOrderSection(String idOrder) {
		if (!(allOrders.containsKey(idOrder))) {
			System.out.println("Order Does Not Exist");
			menu.menuOrderAdmin();
		}

		if (storge.status(idOrder).equals("Accepted")) {
			System.out.println("Order Exists and Accepted ");
			menu.menuOrderAdmin();
		}
		System.out.println("Order Exists and pending... ");
		menu.menuOrderAdmin();
	}

///////////////////////////////////////////////////////////////////////////// Sort
	public void sortingByCapcity() {
		int array[][] = this.sectionArray();
		String sectioName[] = this.sectionName();
		String format = "%-10s%-10s%n";
		System.out.printf(format, "Section", "Capcity");

		boolean flag = true;
		while (flag == true) {
			flag = false;
			for (int i = 0; i < (section.size() - 1); i++) {
				int x = array[i][0];
				int z = array[i + 1][0];
				if (array[i][0] < array[i + 1][0]) {
					int temp = array[i][0];
					String nameTemp = sectioName[i];
					sectioName[i] = sectioName[1 + i];
					sectioName[i + 1] = nameTemp;

					array[i][0] = array[i + 1][0];
					array[i + 1][0] = temp;
					flag = true;
				}
			}
		}
		for (int i = 0; i < section.size(); i++) {
			System.out.printf(format, "  " + sectioName[i], "  " + array[i][0]);
		}
		menu.menuSection();
	}

	public void sortingByUsed() {
		int array[][] = this.sectionArray();
		String sectioName[] = this.sectionName();

		String format = "%-10s%-10s%n";
		System.out.printf(format, "Section", "UsedSize");

		boolean flag = true;
		while (flag == true) {
			flag = false;
			for (int i = 0; i < section.size() - 1; i++) {
				if (array[i][2] < array[i + 1][2]) {
					int temp = array[i][2];
					String nameTemp = sectioName[i];

					sectioName[i] = sectioName[i + 1];
					sectioName[i + 1] = nameTemp;

					array[i][2] = array[i + 1][2];
					array[i + 1][2] = temp;
					flag = true;
				}
			}
		}
		for (int i = 0; i < section.size(); i++) {
			System.out.printf(format, "  " + sectioName[i], "  " + array[i][2]);
		}
		menu.menuSection();
	}

///////////////////////////////////////////////////////////////////////////// Methods	
	public int[][] sectionArray() {
		int i = 0;
		int array[][] = new int[section.size()][3];
		for (Map.Entry<String, sections> entry : section.entrySet()) {
			array[i][0] = entry.getValue().capcity;
			array[i][1] = entry.getValue().emptySize;
			array[i][2] = entry.getValue().used;
			i++;
		}
		return array;
	}

	public String[] sectionName() {
		String sectioName[] = new String[section.size()];
		int j = 0;

		for (Map.Entry<String, sections> entry : section.entrySet()) {
			sectioName[j] = entry.getKey();
			j++;
		}
		return sectioName;
	}

///////////////////////////////////////////////////////////////////////////// Set/Get
	public int getEmptySize() {
		return emptySize;
	}

	public void setEmptySize(int emptySize) {
		this.emptySize = emptySize;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public Map<String, sections> getSection() {
		return section;
	}

}
