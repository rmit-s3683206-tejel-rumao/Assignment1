package rmit.social.network;

import java.util.HashSet;

import javax.naming.OperationNotSupportedException;

public class AdultUser extends User {

	public AdultUser(String name) {
		super(name);
	}

	@Override
	public void setAge(int age) throws OperationNotSupportedException {
		if (age > 0)
			this.age = age;
		else
			throw new UnsupportedOperationException("Age can not be less than 0");
	}

	@Override
	public void addConnection(ConnectionType connectionType, User anotherUser) throws OperationNotSupportedException {
		connections.putIfAbsent(connectionType, new HashSet<>());
		if (connectionType.equals(ConnectionType.PARENT))
			throw new OperationNotSupportedException("Connection can't be parent because this user is not child.");

		if (connectionType.equals(ConnectionType.CHILD)) {			
			if (!getAllConnections(ConnectionType.CHILD).isEmpty())
				throw new OperationNotSupportedException("This User already has a child");
			else {
				connections.get(connectionType).add(anotherUser);
				connectionType = ConnectionType.PARENT;
			}
		}
		if (ConnectionType.FRIEND.equals(connectionType)) {
			connections.get(connectionType).add(anotherUser);
		}

		anotherUser.getAllConnections().putIfAbsent(connectionType, new HashSet<>());
		anotherUser.getAllConnections().get(connectionType).add(this);
	}

	@Override
	public void removeConnection(ConnectionType connectionType, User anotherUser) {
		connections.get(connectionType).remove(anotherUser);
		if (!connectionType.equals(ConnectionType.CHILD))
			connectionType = ConnectionType.PARENT;

		anotherUser.getAllConnections().get(connectionType).remove(this);
	}
}
