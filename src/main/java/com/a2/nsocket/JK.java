package com.a2.nsocket;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JK extends JFrame{
	public JK(){
		JButton btn=new JButton();
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
		});
	}
}
