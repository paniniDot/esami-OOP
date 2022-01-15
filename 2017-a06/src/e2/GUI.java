package e2;

import java.awt.*;
import java.util.stream.*;
import javax.swing.*;
import java.util.List;

public class GUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<JButton> buttons;
	private final Model model;
	private final JButton reset;
	
	public GUI(int size){
		this.setSize(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		this.model = new ModelImpl(size);
		this.buttons = IntStream.range(0, size)
				.mapToObj(i -> new JButton(this.model.getStatusAt(i)))
				.peek(this.getContentPane()::add)
				.collect(Collectors.toList());
		this.buttons.forEach(jb -> jb.addActionListener(e -> {
			this.model.hit(this.buttons.indexOf(jb));
			this.updateView();
		}));
		this.reset = new JButton("Reset");
		this.reset.addActionListener(e -> {
			this.model.reset();
			this.updateView();
		});
		this.getContentPane().add(reset);
		this.setVisible(true);
	}
	
	private void updateView() {
		IntStream.range(0, this.buttons.size()).forEach(i -> this.buttons.get(i).setText(this.model.getStatusAt(i)));
	}
}