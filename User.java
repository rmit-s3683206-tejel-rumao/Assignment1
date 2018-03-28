package rmit.social.network;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

public abstract class User {
	protected String name;
	protected int age;
	protected String status;
	protected String userImage;
	protected Map<ConnectionType, Set<User>> connections;

	protected User(String name) {
		this.name = name;
		this.connections = new HashMap<>();
		this.status="";
		this.userImage="";
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public abstract void setAge(int age) throws OperationNotSupportedException;

	/**
	 * Add user connection to another user
	 * 
	 * @param connectionType
	 *            of other user
	 * @param anotherUser
	 *            user to connect
	 */
	public abstract void addConnection(ConnectionType connectionType, User anotherUser)
			throws OperationNotSupportedException;

	/**
	 * remove user connection to another user
	 * 
	 * @param connectionType
	 *            of other user
	 * @param anotherUser
	 *            user to remove connection
	 */
	public abstract void removeConnection(ConnectionType connectionType, User anotherUser)
			throws OperationNotSupportedException;

	/**
	 * update user connection to another user
	 * 
	 * @param connectionType
	 *            of other user to update
	 * @param anotherUser
	 *            user to connect
	 */
	public void updateConnection(ConnectionType connectionType, User anotherUser)
			throws OperationNotSupportedException {
		this.removeConnection(connectionType, anotherUser);
		this.addConnection(connectionType, anotherUser);
	}

	/**
	 * @param user
	 * @return Connection to the user
	 */
	public ConnectionType getConnection(User user) {
		Map<ConnectionType, Set<User>> allConnections = getAllConnections();
		for (ConnectionType type : allConnections.keySet()) {
			if (allConnections.get(type).contains(user))
				return type;
		}
		return ConnectionType.NONE;
	}

	/**
	 * get users for this type of connection
	 * 
	 * @param connectionType
	 *            of other user to update
	 * @return set of users
	 */
	public Set<User> getAllConnections(ConnectionType connectionType) {
		return connections.get(connectionType);
	}

	/**
	 * @return the connections
	 */
	public Map<ConnectionType, Set<User>> getAllConnections() {
		return connections;
	}

	/**
	 * @return user status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * user status to set
	 * 
	 * @param status
	 *            *
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the user image
	 */
	public String getUserImage() {
		return userImage;
	}

	/**
	 * sets user image
	 * 
	 * @param userImage
	 */
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User [name=%s, age=%s, status=%s]", name, age, status);
	}	
}
