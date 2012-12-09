package com.a2.nsocket.core.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.a2.nsocket.core.handler.IoHandler;

public abstract class AbstractIoSession implements IoSession{
	
	protected SelectionKey key;
	protected Selector selector;
	protected volatile boolean isServerChannel = false;

	public IoHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	public void write(Object message) throws IOException {
		if (this.key == null) {
			throw new IllegalArgumentException("key is null");
		}
		if (isServerChannel) {
			writeToServerChannel(this.key, message);
		} else {
			writeToCommonChannel(this.key,message);
		}

	}

	public void broadcast(Object message) {
		// TODO Auto-generated method stub

	}

	public Object read() {

		return null;
	}

	private void writeToServerChannel(SelectionKey key, Object message)
			throws IOException {
		ServerSocketChannel channel = (ServerSocketChannel) key.channel();
		SocketChannel client = channel.accept();
		client.configureBlocking(false);
		client.register(selector, SelectionKey.OP_READ);
		System.out.println("get" + client);
		ByteBuffer dst = ByteBuffer.allocate(1024);
		dst.put(message.toString().getBytes());
		dst.flip();
		client.write(dst);
	}

	/***/
	private void writeToCommonChannel(SelectionKey key, Object message)
			throws IOException {
		SocketChannel client = (SocketChannel) key.channel();
		client.configureBlocking(false);
		client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		ByteBuffer dst = ByteBuffer.allocate(1024);
		dst.put(message.toString().getBytes());
		dst.flip();
		client.write(dst);
	}
}
