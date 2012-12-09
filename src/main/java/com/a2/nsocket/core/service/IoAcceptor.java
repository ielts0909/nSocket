package com.a2.nsocket.core.service;

import java.net.SocketAddress;
import java.util.Set;

import com.a2.nsocket.core.session.IoSession;

/**
 * interface for server socket
 * @author ChenHui
 *
 */
public interface IoAcceptor extends IoCommunicate{
	
	IoSession bind();
	
	IoSession bind(SocketAddress localAddress);
	
	IoSession bind(Set<SocketAddress> localAddresses);
	
	void unbind();
	
	void unbind(SocketAddress localAddress);
	
	void unbind(Set<SocketAddress> localAddresses);
}
