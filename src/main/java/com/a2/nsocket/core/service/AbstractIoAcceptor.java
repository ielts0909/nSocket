package com.a2.nsocket.core.service;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

import com.a2.nsocket.core.session.IoSession;

public abstract class AbstractIoAcceptor extends AbstractIoCommunicate
		implements IoAcceptor {

	/** return io session form niosocketacceptor */
	// private IoSession session;

	private Set<SocketAddress> tmpBindAddresses = new HashSet<SocketAddress>();

	protected AbstractIoAcceptor() {
		super();
	}

	public IoSession bind() {
		
		Set<SocketAddress> defaultLocalAddresses=new HashSet<SocketAddress>();
		defaultLocalAddresses.addAll(getLocalAddresses());
		tmpBindAddresses.addAll(defaultLocalAddresses);
		
		return bind0(defaultLocalAddresses);
	}

	public IoSession bind(SocketAddress localAddress) {
		if (localAddress == null) {
			throw new IllegalArgumentException("use bind() when params is null");
		}
		Set<SocketAddress> addresses = new HashSet<SocketAddress>();
		addresses.add(localAddress);
		tmpBindAddresses.addAll(addresses);
		return bind0(addresses);
	}

	public IoSession bind(Set<SocketAddress> localAddresses) {
		if (localAddresses.isEmpty()) {
			throw new IllegalArgumentException("use bind()");
		}
		Set<SocketAddress> addresses = new HashSet<SocketAddress>();
		addresses.addAll(localAddresses);
		tmpBindAddresses.addAll(addresses);
		return bind0(addresses);
	}

	/** bind0 implements by its sub class */
	protected abstract IoSession bind0(Set<SocketAddress> localAddresses);

	public void unbind() {
		// TODO Auto-generated method stub
	}

	public void unbind(SocketAddress localAddress) {
		// TODO Auto-generated method stub

	}

	public void unbind(Set<SocketAddress> localAddresses) {
		// TODO Auto-generated method stub

	}

}
