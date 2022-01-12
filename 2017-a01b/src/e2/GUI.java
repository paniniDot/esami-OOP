package e2;

import java.awt.*;
import java.util.List;
import java.util.stream.*;

import javax.swing.*;

public class GUI extends JFrame{
	
	private final List<JButton> buttons;
	private final Model model;
	public GUI(int size){
		this.model = new ModelImpl(size);
		this.setSize(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		
		this.buttons = IntStream.range(0, size)
				.mapToObj(i -> new JButton("*"))
				.peek(jb -> this.getContentPane().add(jb))
				.collect(Collectors.toList());			
		this.buttons.forEach(jb -> jb.addActionListener(e -> this.updateView(this.buttons.indexOf(jb))));
		this.setVisible(true);
		
	}
	
	private void updateView(final int position) {
		if(this.model.hit(position)) {
			this.buttons.get(position).setText(this.model.getNumber(position));
			this.buttons.get(position).setEnabled(false);
		}
	}
	
}
