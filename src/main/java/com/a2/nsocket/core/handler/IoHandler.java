package com.a2.nsocket.core.handler;

import com.a2.nsocket.core.session.IoSession;

/**
 * IoHandler provide the interface for user to do detail implements
 * @author ChenHui
 *
 */
public interface IoHandler {
	
	/**while doing the init in socket --binding*/
	void sessionOpening() throws Exception;
	
	void sessionClosed() throws Exception;
	
	/**when accept or connect */
	void sessionOpened(IoSession session) throws Exception;
	
	/**when message receives*/
	void messageReceive(IoSession session, Object message) throws Exception;
	/**when message send, actually its the time before sending the msg*/
	void messageSend(IoSession session, Object message) throws Exception;

	void execptionCaught(IoSession session, Throwable cause);
}
