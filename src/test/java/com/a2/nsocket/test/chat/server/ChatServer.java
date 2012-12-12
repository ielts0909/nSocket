package com.a2.nsocket.test.chat.server;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.net.InetSocketAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.a2.nsocket.core.service.IoAcceptor;
import com.a2.nsocket.transport.socket.nio.NioSocketAcceptor;

public class ChatServer extends JFrame{
	
	private JTextArea area;
	private JTextField field;
	private JButton btn;
	
	public ChatServer(){
		this.setSize(500,600);
		this.setLayout(new FlowLayout());
		
		area=new JTextArea();
		field=new JTextField();
		btn=new JButton("发送");
		
		this.add(area);
		this.add(field);
		this.add(btn);
		
		this.setVisible(true);
		
		btn.addMouseListener(new MouseAdapter() {
			
		});
		
	}
	
	
	
	
	public static void main(String[] args) {
		IoAcceptor acceptor=new NioSocketAcceptor();
		acceptor.setHandler(new ChatServerHandler());
		acceptor.setLocalAddress(new InetSocketAddress(9020));
		acceptor.bind();
	}
	
}
