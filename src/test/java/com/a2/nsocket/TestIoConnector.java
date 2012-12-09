package com.a2.nsocket;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.a2.nsocket.core.handler.IoHandlerAdapter;
import com.a2.nsocket.core.service.IoConnector;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.transport.socket.nio.NioSocketConnector;

public class TestIoConnector extends IoHandlerAdapter{
	
	@Override
	public void sessionOpening() throws Exception {
		System.out.println("connecting...");
	}
	
	@Override
	public void messageReceive(IoSession session, Object message)
			throws Exception {
		ByteBuffer buf=(ByteBuffer) message;
		while(buf.hasRemaining()){
			System.out.print((char)buf.get());
			Thread.sleep(2000);
			//session.write("HAHAHAHA");
		}		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		//	session.write("I'm client");
	}


	public static void main(String[] args) throws IOException, Exception {
		IoConnector connector=new NioSocketConnector();
		connector.setHandler(new TestIoConnector());
		connector.connect("127.0.0.1", 9020);
	}
}
