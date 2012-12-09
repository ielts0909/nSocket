package com.a2.nsocket.transport.socket.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.a2.nsocket.core.handler.IoHandler;
import com.a2.nsocket.core.polling.NioSocketAcceptorPoll;
import com.a2.nsocket.core.service.AbstractIoAcceptor;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.core.session.NioAcceptorSession;
import com.a2.nsocket.transport.socket.SocketAcceptor;

public final class NioSocketAcceptor extends AbstractIoAcceptor implements
		SocketAcceptor {

	/** new session */
	private IoSession session = null;
	private IoHandler handler = null;

	private ServerSocketChannel serverSocketChannel;
	private Selector selector;

	private Executor executor;

	public NioSocketAcceptor() {
		super();
		try {
			serverSocketChannel = ServerSocketChannel.open();
			selector = Selector.open();
			session = new NioAcceptorSession();
			executor = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected final IoSession bind0(Set<SocketAddress> localAddresses) {
		if (serverSocketChannel.isOpen() && selector.isOpen()) {
			try {
				serverSocketChannel.bind(localAddresses.iterator().next());
				if (serverSocketChannel.isOpen() && selector.isOpen()) {
					serverSocketChannel.configureBlocking(false);
					serverSocketChannel.register(selector,
							SelectionKey.OP_ACCEPT);
				}
				//
				init();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.session;
	}

	@Override
	public boolean isBlocakingChannel() {
		return false;
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
		if (this.handler == null) {
			throw new IllegalArgumentException(
					"You need to add handler for this Communication");
		}
		return this.handler;
	}

	@Override
	public void init() throws Exception {
		if (this.handler == null) {
			throw new IllegalArgumentException(
					"You need to add handler for this Communication");
		}
		handler.sessionOpening();
		executor.execute(new NioSocketAcceptorPoll(this.handler, this.session,
				this.selector));
	}

	public boolean isReuseAddress() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getBacklog() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setBacklog(int backlog) {
		// TODO Auto-generated method stub

	}

}
