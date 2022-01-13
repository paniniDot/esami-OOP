package e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
	private final List<JButton> buttons;
	private final Model model;
	
    public GUI(int size) {
    	this.model = new ModelImpl(size);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        this.buttons = IntStream.range(0, size*size)
        		.mapToObj(i -> new JButton(" "))
        		.peek(jb -> panel.add(jb))
        		.collect(Collectors.toList());
        this.buttons.forEach(jb -> jb.addActionListener(e -> this.updateView(this.buttons.indexOf(jb))));
        this.setVisible(true);
    }
    
    private void updateView(int position) {
    	this.model.hit(position);
    	this.buttons.get(position).setText(this.model.getText(position));
    	if(this.model.shouldQuit()) {
    		System.exit(0);
    	}
    }
    
}
