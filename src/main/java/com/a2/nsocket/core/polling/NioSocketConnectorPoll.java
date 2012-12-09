package com.a2.nsocket.core.polling;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import com.a2.nsocket.core.handler.IoHandler;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.core.session.NioConnectorSession;

public class NioSocketConnectorPoll implements Runnable {

	private IoHandler handler;
	private NioConnectorSession session;
	private Selector selector;
	private Collection<SelectionKey> keys;
	private AtomicInteger count = new AtomicInteger(0);

	public NioSocketConnectorPoll(IoHandler handler, IoSession session,
			Selector selector) {
		this.handler = handler;
		this.session = (NioConnectorSession) session;
		this.selector = selector;
	}

	public void run() {
		try {
			while (true) {
				int n = selector.select();
				if (n == 0) {
					continue;
				}
				keys = selector.selectedKeys();
				Iterator<SelectionKey> iter = keys.iterator();

				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					iter.remove();
					if (key.isValid()) {
						/**config*/
						if (count.compareAndSet(0, 1)) {
							validOperation(key);
						}
						if (key.isReadable()) {
							readOperation(key);
						}else if(key.isWritable()){
							
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void validOperation(SelectionKey key) throws Exception {
		session.setSelecotr(selector);
		session.setKey(key);
		handler.sessionOpened(session);
	}

	private void readOperation(SelectionKey key) throws Exception {
		session.setSelecotr(selector);
		session.setKey(key);
		SocketChannel channel = (SocketChannel) key.channel();
		int n = -1;
		ByteBuffer dst = ByteBuffer.allocate(1024);
		try {
			n = channel.read(dst);
		} catch (Exception e) {
			System.err.println("client read error");
		}
		if (n == -1) {
			key.cancel();
			channel.close();
			return;
		}
		dst.flip();

		handler.messageReceive(session, dst);
	}

	/** this should be considered */
	public SocketChannelIterator getSocketChannelIter() {
		if (keys == null) {
			throw new IllegalStateException("the key is null");
		}
		return new SocketChannelIterator(keys);
	}

	private static class SocketChannelIterator implements
			Iterator<SocketChannel> {

		private final Iterator<SelectionKey> iterator;

		private SocketChannelIterator(Collection<SelectionKey> keys) {
			iterator = keys.iterator();
		}

		public boolean hasNext() {

			return iterator.hasNext();
		}

		public SocketChannel next() {
			SelectionKey key = iterator.next();
			if (key.isValid()) {
				return (SocketChannel) key.channel();
			}
			return null;
		}

		public void remove() {
			iterator.remove();
		}
	}
}
