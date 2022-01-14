package e2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GUI extends JFrame {
	
	private final static String ACTIVATED = "*";
	private final static String NOT_ACTIVATED = " ";
    
	private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
	private final Model model;
	
	public GUI(int size) {
		this.model = new ModelImpl(size);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		JPanel panel = new JPanel(new GridLayout(size,size));
        for(int i=0; i<size; i++) {
        	for(int j=0; j<size; j++) {
        		JButton btn = new JButton(" ");
        		buttons.put(btn, new Pair<>(i, j));
        		btn.addActionListener(e -> {
        			this.model.hit(this.buttons.get(btn).getX(), this.buttons.get(btn).getY());
        			this.updateView();
        		});
        		panel.add(btn);
        	}
        }
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.setVisible(true);
    }
	
	private void updateView() {
		this.buttons.entrySet().stream().forEach(jb -> jb.getKey().setText(this.model.getGridStatus(jb.getValue().getX(), jb.getValue().getY()) ? ACTIVATED : NOT_ACTIVATED));
	}
}
