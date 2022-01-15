package e2;

import java.awt.*;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private final List<JButton> buttons;
	private final Model model;
	
	public GUI() {
		this.model = new ModelImpl();
		this.setSize(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		this.buttons = IntStream.range(0, this.model.getSize())
				.mapToObj(i -> new JButton(""+this.model.getStatus().get(i)))
				.peek(this.getContentPane()::add)
				.collect(Collectors.toList());
		this.buttons.forEach(jb -> jb.addActionListener(e -> {
			this.model.hit(this.buttons.indexOf(jb));
			this.buttons.get(this.buttons.indexOf(jb)).setEnabled(false);
		}));
		JButton draw = new JButton("Draw");
		draw.addActionListener(e -> {
			this.model.draw();
			this.updateView();
		});
		this.getContentPane().add(draw);
		this.setVisible(true);
	}
	
	private void updateView() {
		IntStream.range(0, this.model.getSize())
			.forEach(i -> {
				this.buttons.get(i).setText(""+this.model.getStatus().get(i)); 
				this.buttons.get(i).setEnabled(true);
			});
	}
}