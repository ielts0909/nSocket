package com.a2.nsocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import com.a2.nsocket.core.handler.IoHandlerAdapter;
import com.a2.nsocket.core.service.IoAcceptor;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.transport.socket.nio.NioSocketAcceptor;

public class TestIoAcceptor extends IoHandlerAdapter{
	
	@Override
	public void sessionOpening() throws Exception {
		System.out.println("session open...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		session.write("welcome!!! \r\n");
	}
	

	@Override
	public void messageReceive(IoSession session, Object message)
			throws Exception {
		ByteBuffer msg=ByteBuffer.allocate(1024);
		msg=(ByteBuffer) message;
		while(msg.hasRemaining()){
			char s=(char)msg.get();
			System.out.print(s);
			session.write(s);
		}
		msg.clear();
	}


	public static void main(String[] args) throws IOException {
		
		SocketAddress net=new InetSocketAddress(8090);
		System.out.println(net);
		
		IoAcceptor acceptor=new NioSocketAcceptor();
		acceptor.setLocalAddress(new InetSocketAddress(9020));
		acceptor.setHandler(new TestIoAcceptor());
		acceptor.bind();

		System.out.println("linsening port..."+acceptor.getLocalAddress());
	}
}
