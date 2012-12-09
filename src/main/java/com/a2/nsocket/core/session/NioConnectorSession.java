package com.a2.nsocket.core.session;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public final class NioConnectorSession extends AbstractIoSession implements SocketSession {

	public void setKey(SelectionKey key) {
		this.key=key;
	}

	public void setSelecotr(Selector selector) {
		this.selector=selector;
	}

	public void setIsServerChannel(boolean isServerChannel) {
		this.isServerChannel=false;
	}
	
}
