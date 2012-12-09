package com.a2.nsocket.transport.socket.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.a2.nsocket.core.handler.IoHandler;
import com.a2.nsocket.core.polling.NioSocketConnectorPoll;
import com.a2.nsocket.core.service.AbstractIoConnector;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.core.session.NioConnectorSession;
import com.a2.nsocket.transport.socket.SocketConnector;

public final class NioSocketConnector extends AbstractIoConnector implements
		SocketConnector {

	private IoHandler handler;
	private IoSession session;

	private SocketChannel channel;
	private Selector selector;

	private Executor executor;

	public NioSocketConnector() {

		try {
			session = new NioConnectorSession();
			selector = Selector.open();
			executor = Executors.newCachedThreadPool();
			channel = SocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** this method should be considered */
	public boolean isNonblocaking() {
		return true;
	}

	@Override
	protected IoSession connect0(SocketAddress remoteAddress)
			throws IOException {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (channel.isOpen() && selector.isOpen()) {
			channel.configureBlocking(false);
			channel.connect(remoteAddress);
			channel.register(selector, SelectionKey.OP_READ
					| SelectionKey.OP_WRITE);
			/** make sure */
			while (!channel.finishConnect()) {

			}
		}
		executor.execute(new NioSocketConnectorPoll(this.handler, this.session,
				this.selector));
		return this.session;
	}

	@Override
	protected void dispose0() throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void setHandler(IoHandler handler) {
		if (handler == null) {
			throw new IllegalArgumentException(
					"You need to add handler for this Communication");
		}
		this.handler = handler;
	}

	@Override
	public IoHandler getHandler() {
		return this.handler;
	}

	@Override
	public void init() throws Exception {
		if (this.handler == null) {
			throw new IllegalArgumentException(
					"You need to add handler for this Communication");
		}
		handler.sessionOpening();
	}

	@Override
	public boolean isBlocakingChannel() {
		return false;
	}

}
