package com.a2.nsocket.core.session;


import java.io.IOException;

import com.a2.nsocket.core.handler.IoHandler;

/**
 * process the interface of reading and writing
 * 
 * @author ChenHui
 * 
 */
public interface IoSession {

	IoHandler getHandler();

	void write(Object message) throws IOException;

	void broadcast(Object message);

	Object read();
}
