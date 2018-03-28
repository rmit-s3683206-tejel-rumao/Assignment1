package rmit.social.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MiniNet {
	private Driver driver = new Driver();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	

	public String readName() throws IOException {
		System.out.println("Enter name of the user");
		return br.readLine();
	}

	public int readAge() throws IOException {
		System.out.println("Enter age");
		return Integer.parseInt(br.readLine());
	}

	public String readStatus() throws IOException {
		System.out.println("Enter Status");
		return br.readLine();
	}

	public String readRelation() throws IOException {
		System.out.println("Enter the Relation");
		return br.readLine();
	}

	public static void main(String args[]) throws Exception {
		MiniNet mn = new MiniNet();
		int choice = 0;
		do {
			try {
				int subChoice = 0;
				String name = null;
				int age = 0;

				System.out.println("\n\n1. List All Users\n" + "2. Register new user\n" + "3. Display user details\n"
						+ "4. Update user details\n" + "5. Delete the User\n" + "6. Connect two person\n"
						+ "7. Check if directly related\n" + "8. Find all relations\n" + "9. Find Children of person\n"
						+ "10. Find Parent of person\n" + "11. Find All Parent\n" + "12. Find All Children\n"
						+ "0.Exit");

				choice = Integer.parseInt(br.readLine());

				switch (choice) {
				case 1:
					System.out.println("All users in system are: ");
					System.out.println(mn.driver.displayAllUsers());
					break;

				case 2:
					name = mn.readName();
					System.out.println("Enter the age of new user");
					age = mn.readAge();

					User user = Driver.createUser(name, age);
					while (mn.driver.getNetwork().contains(user)) {
						System.out.println("User name already in use.\n Please enter new name");
						name = mn.readName();
						user = Driver.createUser(name, age);
					}
					mn.driver.addPerson(user);
					break;

				case 3:
					name = mn.readName();
					mn.driver.displayProfile(Driver.getUserFromName(name));
					break;
				case 4:
					name = mn.readName();
					User user1 = Driver.getUserFromName(name);
					System.out.println(
							" 1. Update name\n 2. Update age\n 3. Update Status \n4. Update user image \n   Enter your choice");
					subChoice = Integer.parseInt(br.readLine());
					switch (subChoice) {
						case 1:
							String name1 = mn.readName();
							user1.setName(name1);
							break;
						case 2:
							int age1 = mn.readAge();
							user1.setAge(age1);
							break;
						case 3:
							String status = mn.readStatus();
							user1.setStatus(status);
							break;
						case 4:
							System.out.println("Enter user image url of user");
							String url = br.readLine();
							user1.setUserImage(url);
							break;
						default:
							System.out.println("Invalid Choice");
					}
					break;
				case 5:
					name = mn.readName();
					mn.driver.deletePerson(Driver.getUserFromName(name));
					break;
				case 6:
					System.out.print("Enter the name of two users");
					name = mn.readName();
					String name1 = mn.readName();
					User first = Driver.getUserFromName(name1);
					User second = Driver.getUserFromName(name);
					System.out.print("Enter the relation, choose from: ");
					System.out.println("1.Friend\n 2.Child\n 3.None \n4.Parent \nEnter your choice");

					subChoice = Integer.parseInt(br.readLine());
					switch (subChoice) {
					case 1:
						first.updateConnection(ConnectionType.FRIEND, second);
						break;
					case 2:
						first.updateConnection(ConnectionType.CHILD, second);
						break;
					case 3:
						first.updateConnection(ConnectionType.NONE, second);
						break;
					case 4:
						first.updateConnection(ConnectionType.PARENT, second);
						break;
					default:
						System.out.println("Invalid Choice");
					}
					break;
				case 7:
					name = mn.readName();
					name1 = mn.readName();
					if (mn.driver.isDirectConnected(name1, name)) {
						System.out.println("Users have direct relation");
					} else {
						System.out.println("No direct relation found");
					}
					break;
				case 8:
					name = mn.readName();
					user = Driver.getUserFromName(name);
					System.out.println(user.getAllConnections());
					break;
				case 9:
					name = mn.readName();
					System.out.println(mn.driver.getChild(name));
					break;

				case 10:
					name = mn.readName();
					System.out.println(mn.driver.getParents(name));
					break;
				case 11:
					System.out.println(mn.driver.displayAllUsers(ConnectionType.CHILD));
					break;

				case 12:
					System.out.println(mn.driver.displayAllUsers(ConnectionType.PARENT));
					break;
				case 0:
					br.close();
					System.exit(1);
				default:
					System.out.println("Invalid Choice");
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} while (choice != 0);
	}

}
