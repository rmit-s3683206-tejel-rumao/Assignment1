package rmit.social.network;

import java.util.HashSet;
import java.util.Set;

public class HardCodedDataFetcher implements DataFetcher {

	@Override
	public Set<User> fetchAllUsersData() {
		Set<User> users = new HashSet<>();
		users.add(Driver.createUser("Sumit", 25));
		users.add(Driver.createUser("Sachin", 27));
		users.add(Driver.createUser("Jill", 13));
		users.add(Driver.createUser("Steve", 41));
		users.add(Driver.createUser("Roman", 18));
		users.add(Driver.createUser("Rick", 60));
		users.add(Driver.createUser("Morty", 15));
		users.add(Driver.createUser("Jessica", 30));
		users.add(Driver.createUser("James", 30));
		users.add(Driver.createUser("Harry", 33));
		users.add(Driver.createUser("Roach", 40));
		users.add(Driver.createUser("Ritu", 27));
		return users;
	}

	@Override
	public User fetchUserData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUserData(String name, String age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initRelationship() {
		// setting relationship read as NAME2 is a CONNECTION_TYPE of NAME1
		// Sachin[name2] is a friend of Sumit[name1]
		Driver.connectPeople("Sumit", "Sachin", ConnectionType.FRIEND);
		Driver.connectPeople("Jill", "Jessica", ConnectionType.PARENT);
		Driver.connectPeople("Harry", "Jill", ConnectionType.CHILD);
		Driver.connectPeople("Jessica", "Harry", ConnectionType.FRIEND);
		Driver.connectPeople("Jessica", "Sachin", ConnectionType.FRIEND);
		Driver.connectPeople("Sumit", "Jessica", ConnectionType.FRIEND);
		Driver.connectPeople("Harry", "Sachin", ConnectionType.FRIEND);
		Driver.connectPeople("Roach", "Harry", ConnectionType.FRIEND);
		Driver.connectPeople("Morty", "Ritu", ConnectionType.PARENT);
		Driver.connectPeople("James", "Morty", ConnectionType.CHILD);
		Driver.connectPeople("Jill", "Morty", ConnectionType.FRIEND);
	}

}
