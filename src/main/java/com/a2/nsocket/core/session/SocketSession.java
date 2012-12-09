package com.a2.nsocket.core.session;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public interface SocketSession {
	
	void setKey(SelectionKey key);
	
	void setSelecotr(Selector selector);
	
	void setIsServerChannel(boolean isServerChannel);
}
