package com.a2.nsocket.core.service;

import java.io.IOException;
import java.net.SocketAddress;

import com.a2.nsocket.core.session.IoSession;

public interface IoConnector extends IoCommunicate{
	
	IoSession connect(SocketAddress remoteAddress) throws IOException;
	
	IoSession connect(String HOST,int PORT) throws IOException;
	
	/**dispose the link if there is only one link
	 * @throws IOException */
	void dispose() throws IOException;

}
