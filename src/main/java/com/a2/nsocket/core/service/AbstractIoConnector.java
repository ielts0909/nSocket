package com.a2.nsocket.core.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.a2.nsocket.core.session.IoSession;

public abstract class AbstractIoConnector extends AbstractIoCommunicate
		implements IoConnector {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractIoConnector.class);

	private SocketAddress remoteAddress;

	public final IoSession connect(SocketAddress remoteAddress) throws IOException{
		if (remoteAddress == null) {
			throw new IllegalArgumentException("remote address cannot be null");
		}
		this.remoteAddress = remoteAddress;
		return connect0(this.remoteAddress);
	}

	public final IoSession connect(String HOST, int PORT) throws IOException{
		if (HOST == null) {
			HOST = "127.0.0.1";
			LOGGER.info("the remote address is 127.0.0.1 the HOST is null");
		}
		if (PORT <= 0 || PORT >= 65535) {
			throw new IllegalArgumentException(
					"the PORT should between 1~65535");
		}
		remoteAddress = new InetSocketAddress(HOST, PORT);
		return connect(remoteAddress);
	}

	/** the detail implements by its sub class 
	 * @throws Exception */
	protected abstract IoSession connect0(SocketAddress remoteAddress) throws IOException;

	public void dispose() throws IOException{
		dispose0();
	}

	protected abstract void dispose0() throws IOException;

}
