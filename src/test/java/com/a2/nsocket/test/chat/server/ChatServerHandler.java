package com.a2.nsocket.test.chat.server;

import com.a2.nsocket.core.handler.IoHandlerAdapter;
import com.a2.nsocket.core.session.IoSession;

public class ChatServerHandler extends IoHandlerAdapter{
	
	public void messageReceive(IoSession session, Object message)
			throws Exception {
		
	}
	
	public void sessionOpened(IoSession session) throws Exception {
		session.write("welcome!!");
	}
	
	
}
