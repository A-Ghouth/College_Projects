import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Customers {
	Scanner input = new Scanner(System.in);

	WareHouseSystem storge;
	Menu menu;

	private String customerID;
	private int numberOrder;

	private Map<String, String> customers;
	private Map<String, Integer> waitOrder;
	private Map<String, Integer> allOrders;

	Customers(WareHouseSystem storge, Menu menu) {
		this.storge = storge;
		this.menu = menu;
		customers = new HashMap<String, String>();

		waitOrder = storge.getWaitOrder();
		allOrders = storge.getAllOrders();
	}

//-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
	public void newOrder(int orderSize) {

		String OrderID = customerID + "-" + numberOrder;
		System.out.println("Order ID [ " + OrderID + " ]");

		waitOrder.put(OrderID, orderSize);
		allOrders.put(OrderID, orderSize);

		numberOrder++;
		menu.menuCustomer();
	}

	public void searchOrder(String idOrder) {
		if (allOrders.containsKey(idOrder)) {
			System.out.println("Found. ");
			System.out.println("The size of the order is: " + allOrders.get(idOrder));
			System.out.println(" Status: " + storge.status(idOrder));
			menu.menuCustomer();
		}
		System.out.println("Not Found");
		menu.menuCustomer();
	}

	public void PrintCustomerOrders() {
		String format = "%-10s%-15s%-50s%n";
		System.out.println("--------------------------------------");
		System.out.printf(format, "orderID", "   Status", " Size");

		for (Entry<String, Integer> entry : allOrders.entrySet()) {
			String[] temp = entry.getKey().split("-");
			if (customerID.equalsIgnoreCase(temp[0])) {
				System.out.printf(format, entry.getKey(), storge.status(entry.getKey()), "  " + entry.getValue());
			}
		}
		System.out.println("--------------------------------------");
		menu.menuCustomer();
	}
///////////////////////////////////////////////////////////////////////////// Set/Get

	public String getCustomerID() {
		return customerID;
	}

	public Map<String, String> getCustomers() {
		return customers;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public int getNumberOrder() {
		return numberOrder;
	}

	public void setNumberOrder(int numberOrder) {
		this.numberOrder = numberOrder;
	}

}
