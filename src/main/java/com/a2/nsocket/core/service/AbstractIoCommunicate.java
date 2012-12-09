package com.a2.nsocket.core.service;

import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.a2.nsocket.core.handler.IoHandler;

public abstract class AbstractIoCommunicate implements IoCommunicate {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(AbstractIoCommunicate.class);

	private final Set<SocketAddress> localAddresses = new HashSet<SocketAddress>();

	private Object addressLock = new Object();

	// TODO
	protected AbstractIoCommunicate() {
		super();
	}

	public final void setLocalAddress(SocketAddress localAddress) {
		setLocalAdderesses(localAddress);
	}

	public final void setLocalAdderesses(SocketAddress... localAddresses) {

		if (localAddresses == null) {
			throw new IllegalArgumentException("local addresses");
		}

		synchronized (addressLock) {
			synchronized (this.localAddresses) {
				for (SocketAddress address : localAddresses) {
					this.localAddresses.add(address);
				}
			}
		}
		LOGGER.info("set local addresses successful");
	}

	public final SocketAddress getLocalAddress() {
		if (localAddresses.isEmpty()) {		
			return null;
		}
		return localAddresses.iterator().next();
	}

	public final  Set<SocketAddress> getLocalAddresses() {
		if (this.localAddresses.isEmpty()) {
			throw new IllegalArgumentException("set local address first");
		}
		return this.localAddresses;
	}

	public abstract void setHandler(IoHandler handler);

	
	public abstract IoHandler getHandler();
	
	
	public abstract void init() throws Exception;
	

	/** rely on its sub classes */
	public abstract boolean isBlocakingChannel();
}
