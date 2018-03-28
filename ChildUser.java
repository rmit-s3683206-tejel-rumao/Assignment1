/**
 * 
 */
package rmit.social.network;

import java.util.HashSet;

import javax.naming.OperationNotSupportedException;

public class ChildUser extends User {

	public ChildUser(String name) {
		super(name);
	}

	@Override
	public void setAge(int age) throws OperationNotSupportedException {
		if (age < 16 && age > 0)
			this.age = age;
		else
			throw new UnsupportedOperationException("Age should be between 0-16");
	}

	@Override
	public void addConnection(ConnectionType connectionType, User anotherUser) throws OperationNotSupportedException {
		connections.putIfAbsent(connectionType, new HashSet<>());
		if (connectionType.equals(ConnectionType.CHILD))
			throw new OperationNotSupportedException("Connection can't be child because this user is also child.");

		if (connectionType.equals(ConnectionType.PARENT)) {			
			if (getAllConnections(ConnectionType.PARENT).size() >= 2)
				throw new OperationNotSupportedException("This User already has a child");
			else {
				connections.get(connectionType).add(anotherUser);
				connectionType = ConnectionType.CHILD;
				anotherUser.getAllConnections().putIfAbsent(connectionType, new HashSet<>());
				anotherUser.getAllConnections().get(connectionType).add(this);
			}
		}
		if (connectionType.equals(ConnectionType.FRIEND))
			if (anotherUser.getAge() < 16 && Math.abs(anotherUser.getAge() - this.getAge()) <= 3
					&& !anotherUser.getAllConnections(ConnectionType.PARENT).isEmpty() && this.getAge() > 2
					&& anotherUser.getAge() > 2) {
				connections.get(connectionType).add(anotherUser);

				anotherUser.getAllConnections().putIfAbsent(connectionType, new HashSet<>());
				anotherUser.getAllConnections().get(connectionType).add(this);
			} else {
				throw new OperationNotSupportedException("This User can not have friends");
			}				
	}

	@Override
	public void removeConnection(ConnectionType connectionType, User anotherUser)
			throws OperationNotSupportedException {
		connections.get(connectionType).remove(anotherUser);
		if (!connectionType.equals(ConnectionType.PARENT))
			connectionType = ConnectionType.CHILD;

		anotherUser.getAllConnections().get(connectionType).remove(this);

	}

}
