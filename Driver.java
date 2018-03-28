package rmit.social.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public class Driver {
	private static Set<User> network;
	private static DataFetcher fetcher;

	static {
		fetcher = new HardCodedDataFetcher();
		network = fetcher.fetchAllUsersData();
		fetcher.initRelationship();
	}

	/**
	 * @return the network
	 */
	public Set<User> getNetwork() {
		return network;
	}

	public static User createUser(String name, int age) {
		User user = null;
		if (age > 0)
			if (age > 16)
				user = new AdultUser(name);
			else
				user = new ChildUser(name);
		try {
			user.setAge(age);
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void addPerson(User user) {
		if (user.getAge() > 0)
			network.add(user);
	}

	public void addPerson(String name, int age) {
		User user = createUser(name, age);
		addPerson(user);
	}

	public void deletePerson(User user) {
		for (ConnectionType type : user.connections.keySet())
			for (User anotherUser : user.connections.get(type))
				try {
					user.removeConnection(type, anotherUser);
				} catch (OperationNotSupportedException e) {
					e.printStackTrace();
				}
		network.remove(user);
	}

	public static void connectPeople(User user1, User user2, ConnectionType connectionType) {
		try {
			user1.addConnection(connectionType, user2);
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public static void connectPeople(String name1, String name2, ConnectionType connectionType) {
		User user1 = getUserFromName(name1);
		User user2 = getUserFromName(name2);

		try {
			user1.addConnection(connectionType, user2);
		} catch (OperationNotSupportedException e) {
			e.printStackTrace();
		}
	}

	public boolean isDirectConnected(String name1, String name2) {
		User user = getUserFromName(name1);
		User anotherUser = getUserFromName(name2);
		return isDirectConnected(user, anotherUser);
	}

	public boolean isDirectConnected(User user, User anotherUser) {
		for (ConnectionType type : ConnectionType.values()) {
			if (user.connections.get(type).contains(anotherUser))
				return true;
		}
		return false;
	}

	public List<String> getChild(User user) {
		List<String> names = new ArrayList<>();
		for (User child : new ArrayList<>(user.getAllConnections(ConnectionType.CHILD))) {
			names.add(child.getName());
		}
		return names;
	}

	public List<String> getChild(String name) {
		User user = getUserFromName(name);
		return getChild(user);
	}

	public List<String> getParents(String name) {
		User user = getUserFromName(name);
		return getParents(user);
	}

	public List<String> getParents(User user) {
		List<String> names = new ArrayList<>();
		for (User parent : new ArrayList<>(user.getAllConnections(ConnectionType.PARENT))) {
			names.add(parent.getName());
		}
		return names;
	}

	public void displayProfile(User user) {
		System.out.println("User Profile is:");
		System.out.println("Name :" + user.getName());
		System.out.println("Age :" + user.getAge());
		System.out.println("Status :" + user.getStatus());
		System.out.println("Profile Pic :" + user.getUserImage());
	}

	public static User getUserFromName(String name) {
		Iterator<User> itr = network.iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.getName().equals(name))
				return user;
		}
		throw new RuntimeException("User with name: " + name + " is not in network. Plase add first");
	}

	public List<String> displayAllUsers() {
		List<String> names = new ArrayList<>();
		Iterator<User> itr = getNetwork().iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			names.add(user.getName());
		}
		return names;
	}

	public List<String> displayAllUsers(ConnectionType type) {
		List<String> names = new ArrayList<>();
		Iterator<User> itr = getNetwork().iterator();
		while (itr.hasNext()) {
			User user = itr.next();
			if (user.connections.containsKey(type))
				names.add(user.getName());
		}
		return names;
	}
}
