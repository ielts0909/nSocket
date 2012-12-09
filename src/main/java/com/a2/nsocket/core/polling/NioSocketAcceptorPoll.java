package com.a2.nsocket.core.polling;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.a2.nsocket.core.handler.IoHandler;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.core.session.NioAcceptorSession;

public class NioSocketAcceptorPoll implements Runnable {

	private IoHandler handler;
	private NioAcceptorSession session;
	private Selector selector;

	public NioSocketAcceptorPoll(IoHandler handler, IoSession session, Selector selector) {
		this.handler = handler;
		this.session = (NioAcceptorSession) session;
		this.selector = selector;
	}

	public void run() {
		try {
			while (true) {
				int n = selector.select();
				if (n == 0) {
					continue;
				}
				Iterator<SelectionKey> iter = selector.selectedKeys()
						.iterator();

				while (iter.hasNext()) {
					SelectionKey key = iter.next();

					if (!key.isValid()) {
						continue;
					} else if (key.isAcceptable()) {
						acceptOperation(key);
					} else if (key.isReadable()) {
						readOperation(key);
					} else if (key.isWritable()) {
						//TODO
					}
					iter.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			handler.execptionCaught(session, e);
		}
	}

	private void readOperation(SelectionKey key) throws Exception {
		session.setSelecotr(selector);
		session.setKey(key);
		session.setIsServerChannel(false);
		
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer dst = ByteBuffer.allocate(1024);
		int n = -1;
		try {
			n = channel.read(dst);
		} catch (Exception e) {
			System.err.println("cannot read io");
		}
		if (n == -1) {
			key.cancel();
			channel.close();
			return;
		}
		dst.flip();
		
		handler.messageReceive(session, dst);
	}

	private void acceptOperation(SelectionKey key) throws Exception {
		session.setSelecotr(selector);
		session.setKey(key);
		session.setIsServerChannel(true);
		handler.sessionOpened(session);
	}
}
