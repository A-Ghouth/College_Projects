import java.util.Scanner;

public class Menu {
	Scanner input = new Scanner(System.in);
	String choose;
	WareHouseSystem storge;

	Menu() {
		storge = new WareHouseSystem(this);
		System.out.println("   Welcome");
		this.start();
	}

	public void start() {
		System.out.println("* * ** * ** * ** * ** * ** * ** * *");
		System.out.println("Login or SignUp");
		System.out.println("1 : Login ");
		System.out.println("2 : SignUp ");
		choose = input.next();

		switch (choose) {
		case "1":
			System.out.println("Your Name and PassWord ");
			String name = input.next();
			String pass = input.next();

			storge.Login(name, pass);
			if ("admin".equals(name))
				this.where();
			this.menuCustomer();

		case "2":
			System.out.println("Your Name and PassWord ");
			name = input.next();
			pass = input.next();

			storge.signUp(name, pass);
			storge.Login(name, pass);
			this.menuCustomer();
		default:
			System.out.println("invalid input" + "\n");
			this.start();
		}
	}

	public void menuCustomer() {
		System.out.println("* * ** * ** * ** * ** * ** * ** * *");
		System.out.println("1- Creat New Order");
		System.out.println("2- View Order Status");
		System.out.println("3- Search Order");
		System.out.println("0- Exit");
		choose = input.next();

		switch (choose) {
		case "1":
			System.out.println("How Many elements");
			int size = input.nextInt();
			storge.agent.newOrder(size);
			break;
		case "2":
			storge.agent.PrintCustomerOrders();
			break;
		case "3":
			System.out.println("Please insert Order ID");
			String idOrder = input.next();
			storge.agent.searchOrder(idOrder);
			break;
		case "0":
			storge.save();
			System.exit(0);

		default:
			System.out.println("invalid input" + "\n");
			this.menuCustomer();
		}
	}

	public void where() {
		System.out.println("1- Sections");
		System.out.println("2- Orders");
		System.out.println("0- Exit");
		choose = input.next();

		switch (choose) {
		case "1":
			this.menuSection();
		case "2":
			this.menuOrderAdmin();
		case "0":
			storge.save();
			System.exit(0);
//////////////////////////////////////////////////
		default:
			System.out.println("invalid input" + "\n");
			this.where();
		}
	}

	public void menuSection() {
		System.out.println("* * ** * ** * ** * ** * ** * ** * *");
		System.out.println("1- Crreate Sections");
		System.out.println("2- View Sections");
		System.out.println("3- Edit Sections");
		System.out.println("4- Remove Sections");
		System.out.println("5- Show Sort");
		System.out.println("0- <---");
		choose = input.next();

		switch (choose) {
		case "1":
			System.out.println("Enter Name and Size of Section");
			String name = input.next();
			int size = input.nextInt();

			storge.admin.creatSection(name, size);
			break;
		case "2":
			storge.admin.viewSection();
			break;
		case "3":
			System.out.println("Please Section Name and new Size");
			name = input.next();
			size = input.nextInt();

			storge.admin.editSection(name, size);
			break;
		case "4":
			System.out.println("Please Enter Section Name  ");
			name = input.next();

			storge.admin.removSection(name);
			break;
		case "5":
			System.out.println("Capcity or Used Size");
			System.out.println("1- Capcity");
			System.out.println("2- Used");
			choose = input.next();

			if (choose.equals("1"))
				storge.admin.sortingByCapcity();
			storge.admin.sortingByUsed();
			break;
		case "0":
			this.where();
		default:
			System.out.println("invalid input" + "\n");
			this.menuSection();
		}
	}

	public void menuOrderAdmin() {
		System.out.println("* * ** * ** * ** * ** * ** * ** * *");
		System.out.println("1- Allocate Orders");
		System.out.println("2- Remmove Orders");
		System.out.println("3- Search Order");
		System.out.println("0- <---");
		choose = input.next();

		switch (choose) {

		case "1":
			storge.shoWaitOrders();
			System.out.println("Please Enter Order ID  ");
			String idOrder = input.next();

			storge.admin.pickOrder(idOrder);
			break;
		case "2":
			storge.shoWaitOrdersAll();
			System.out.println("Please  Order ID");
			idOrder = input.next();

			storge.admin.removeOrder(idOrder);
			break;
		case "3":
			// storge.shoWaitOrdersAll();
			System.out.println("Please  Order ID");
			idOrder = input.next();

			storge.admin.searchOrderSection(idOrder);
			break;
		case "0":
			this.where();
		default:
			System.out.println("invalid input" + "\n");
			this.menuOrderAdmin();
		}
	}

}
