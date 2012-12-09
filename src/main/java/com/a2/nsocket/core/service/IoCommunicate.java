package com.a2.nsocket.core.service;

import java.net.SocketAddress;
import java.util.Set;

import com.a2.nsocket.core.handler.IoHandler;

/**
 * the super interface for IoConnector and IoAcceptor
 * 
 * @author ChenHui
 * 
 */
public interface IoCommunicate {

	void setLocalAddress(SocketAddress localAddress);

	void setLocalAdderesses(SocketAddress... localAddresses);

	SocketAddress getLocalAddress();

	Set<SocketAddress> getLocalAddresses();

	void setHandler(IoHandler handler);

	/** get handler related to this communication */
	IoHandler getHandler();

	/** return the blocking type */
	boolean isBlocakingChannel();
	
	/**create open session operation*/
	void init() throws Exception;
}
