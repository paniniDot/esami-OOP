package e2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

public class Ex2GUI {
	
	private final List<JButton> buttons;
	private final Model model;
	public Ex2GUI(final int size) {
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		this.model = new ModelImpl();
		this.buttons = IntStream.range(0, size)
				.mapToObj(i -> new JButton("-"))
				.peek(jb -> jb.setEnabled(true))
				.peek(jb -> jp.add(jb))
				.collect(Collectors.toList());
		this.buttons.forEach(jb -> jb.addActionListener(e -> {
			this.model.addPosition(this.buttons.indexOf(jb)); 
			this.updateView();
		})); 
		jf.add(jp);
		jf.pack();
		jf.setVisible(true);
	}
	
	private void updateView() {
		for(int i=0; i<this.buttons.size(); i++) {
			this.buttons.get(i).setText(this.model.setText(i));
			this.buttons.get(i).setEnabled(this.model.isEnabled(i));
		}
	}
	
}
