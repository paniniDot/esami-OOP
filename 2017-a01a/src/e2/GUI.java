package e2;

import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

public class GUI extends JFrame{
	
	private List<JButton> buttons;
	private Model model;
	public GUI(int size){
		this.setSize(500, 100);
		model = new ModelImpl(size);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		this.buttons = IntStream.range(0, size)
				.mapToObj(i -> new JButton("0"))
				.peek(jb -> this.getContentPane().add(jb))
				.peek(jb -> jb.addActionListener(e -> {
					jb.setText(this.model.getText(this.buttons.indexOf(jb))); 
					jb.setEnabled(this.model.checkIfEnabled(this.buttons.indexOf(jb)));
					this.updateView();
				}))
				.collect(Collectors.toList());
		JButton print = new JButton("Print");
		print.addActionListener(e -> System.out.println(this.model.print()));
		this.getContentPane().add(print);
		this.setVisible(true);
	}
	
	private void updateView() {
		if(this.model.shouldQuit()) {
			System.exit(0);
		}
	}
	
}
