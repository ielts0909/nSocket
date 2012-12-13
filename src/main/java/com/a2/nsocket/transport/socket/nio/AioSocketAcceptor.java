package com.a2.nsocket.transport.socket.nio;

import java.net.SocketAddress;
import java.util.Set;

import com.a2.nsocket.core.handler.IoHandler;
import com.a2.nsocket.core.service.AbstractIoAcceptor;
import com.a2.nsocket.core.session.IoSession;
import com.a2.nsocket.transport.socket.SocketAcceptor;

public class AioSocketAcceptor extends AbstractIoAcceptor implements SocketAcceptor{

	@Override
	protected IoSession bind0(Set<SocketAddress> localAddresses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHandler(IoHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IoHandler getHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isBlocakingChannel() {
		// TODO Auto-generated method stub
		return false;
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
