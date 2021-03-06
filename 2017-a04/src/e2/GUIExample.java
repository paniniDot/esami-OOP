package e2;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUIExample extends JFrame{
	
	public GUIExample(){
		this.setSize(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		
		JButton jb1 = new JButton("0");
		JButton jb2 = new JButton("0");
		ActionListener ac = e -> {
			final JButton jb = (JButton)e.getSource();
			jb.setEnabled(false);
			jb.setText(""+new java.util.Random().nextInt(100)); // 0..99
		};
		jb1.addActionListener(ac);
		jb2.addActionListener(ac);
		this.getContentPane().add(jb1);
		this.getContentPane().add(jb2);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new GUIExample();
	}
	
	

}

