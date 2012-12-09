package com.a2.nsocket.core.session;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * this class direct implements IoSession TODO for abstract class
 * 
 * @author ChenHui
 * 
 */
public final class NioAcceptorSession extends AbstractIoSession implements SocketSession{
	
	public final void setKey(SelectionKey key) {
		this.key = key;
	}

	public final void setSelecotr(Selector selector) {
		this.selector = selector;
	}

	public final void setIsServerChannel(boolean isServerChannel) {
		this.isServerChannel=isServerChannel;
	}

}
