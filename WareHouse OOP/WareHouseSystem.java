import java.io.File;
import java.util.Map.Entry;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WareHouseSystem {
	Scanner input = new Scanner(System.in);

	Menu menu;
	Admin admin;
	Customers agent;

	private Map<String, Integer> waitOrder;
	private Map<String, Integer> allOrders;

	WareHouseSystem(Menu menu) {
		waitOrder = new HashMap<String, Integer>();
		allOrders = new HashMap<String, Integer>();

		admin = new Admin(this, menu);
		agent = new Customers(this, menu);
		this.menu = menu;

		this.restoreData();
		admin.setStart(true);
	}

//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
///////////////////////////////////////////////////////////////////////////// User
	public void signUp(String name, String pass) {
		if (agent.getCustomers().containsKey(name)) {
			System.out.println("This User Name is Taken");
			menu.start();
		}
		agent.getCustomers().put(name, pass);
	}

	public void Login(String name, String pass) {
		if (agent.getCustomers().containsKey(name) && agent.getCustomers().get(name).equals(pass)) {
			System.out.println("Login --> successful ");
			agent.setCustomerID(name);
			return;
		}
		System.out.println("User Not Found");
		menu.start();
	}

////////////////////////////////////////////////////////////////////////////// Filing
	public void writeFile(String fileName, String object) {

		try {
			FileWriter myWriter = new FileWriter(fileName + ".txt");
			myWriter.write(object);
			myWriter.close();
		} catch (IOException e) {
			System.out.println(fileName + " Not Save");
		}
	}

	public void save() {
		String obj = "";

		for (Map.Entry<String, sections> entry : admin.getSection().entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue().save() + "\n" + obj;
		}
		this.writeFile("box", obj);
		obj = "";

		for (Map.Entry<String, sections> entry : admin.getSection().entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue().capcity + "\n" + obj;
		}
		this.writeFile("section", obj);
		obj = "";
		for (Map.Entry<String, Integer> entry : waitOrder.entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue() + "\n" + obj;
		}
		this.writeFile("waitOrder", obj);

		for (Map.Entry<String, Integer> entry : allOrders.entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue() + "\n" + obj;
		}
		this.writeFile("allOrders", obj);
		obj = "";
		for (Map.Entry<String, String> entry : agent.getCustomers().entrySet()) {
			obj = entry.getKey() + ":" + entry.getValue() + "\n" + obj;
		}
		obj = obj + agent.getNumberOrder();
		this.writeFile("customers", obj);

		System.out.println("Data --> Stored");
	}

	public void readFile(String fileName, Map store) {
		try {
			File file = new File(fileName + ".txt");
			Scanner read = new Scanner(file);
			while (read.hasNextLine()) {
				String[] obj = read.nextLine().split(":");

				if (fileName.equals("section")) {
					admin.creatSection(obj[0], Integer.parseInt(obj[1]));
				} else if (fileName.equals("box")) {
					if (obj.length != 1) {
						admin.getSection().get(obj[0]).storeItem(obj[1], Integer.parseInt(obj[2]));
						String x = obj[2];
						admin.setEmptySize(admin.getEmptySize() - Integer.parseInt(x));
					}
				} else if (fileName.equals("customers")) {
					if ((read.hasNextLine())) {
						store.put(obj[0], obj[1]);
					} else {
						agent.setNumberOrder(Integer.parseInt(obj[0]));
					}

				} else {
					store.put(obj[0], obj[1]);
				}
			}
		} catch (Exception e) {
			System.out.println(fileName + " Not Restore");
		}
	}

	public void restoreData() {
		this.readFile("waitOrder", waitOrder);
		this.readFile("section", admin.getSection());

		this.readFile("box", admin.getSection());

		this.readFile("customers", agent.getCustomers());
		this.readFile("allOrders", allOrders);

		System.out.println("Restore Data --> Done");
	}

////////////////////////////////////////////////////////////////////////////// Methods
	public String status(String idOrder) {
		if (waitOrder.containsKey(idOrder)) {
			return "Pending...";
		}
		return "Accepted";
	}

	public void shoWaitOrders() {
		System.out.println("--------------------------------------");
		System.out.println("There are " + waitOrder.size() + " Order");
		String format = "%-10s%-10s%-18s%n";
		System.out.println("--------------------------------------");

		System.out.printf(format, "orderID", "   Status", "Size");
		for (Entry<String, Integer> entry : waitOrder.entrySet()) {
			System.out.printf(format, entry.getKey(), this.status(entry.getKey()), "  " + entry.getValue());
		}
		System.out.println("--------------------------------------");
	}

	public void shoWaitOrdersAll() {
		System.out.println("--------------------------------------");
		System.out.println("There are " + allOrders.size() + " Order");
		String format = "%-10s%-20s%-30s%n";
		System.out.println("--------------------------------------");

		System.out.printf(format, "orderID", "  Status", " Size");
		for (Entry<String, Integer> entry : allOrders.entrySet()) {
			System.out.printf(format, entry.getKey(), this.status(entry.getKey()), "  " + entry.getValue());
		}
		System.out.println("--------------------------------------");
	}

///////////////////////////////////////////////////////////////////////////// Set/Get
	public Map<String, Integer> getWaitOrder() {
		return waitOrder;
	}

	public Map<String, Integer> getAllOrders() {
		return allOrders;
	}
}