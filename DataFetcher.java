package rmit.social.network;

import java.util.Set;

public interface DataFetcher {
	public Set<User> fetchAllUsersData();
	public User fetchUserData(String name);
	public User fetchUserData(String name, String age);
	public void initRelationship();
}
