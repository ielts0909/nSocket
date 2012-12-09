package com.a2.nsocket.transport.socket;

import com.a2.nsocket.core.service.IoAcceptor;

/**
 * interface for socket
 * @author ChenHui
 *
 */
public interface SocketAcceptor extends IoAcceptor{
	
	public boolean isReuseAddress();
	
	/**the size of backlog*/
	public int getBacklog();
	
	/**operate when the class is not bound*/
	public void setBacklog(int backlog);
}
