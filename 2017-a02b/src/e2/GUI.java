package e2;


import java.io.*;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.awt.*;
import javax.swing.*;

public class GUI {
	
	private final Model model;
	private final JComboBox<String> combo;
	
	public GUI(String fileName) throws IOException {
		this.model = new ModelImpl(fileName);
		final JFrame gui = new JFrame();
		this.combo = new JComboBox<>();
		this.updateView();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton remove = new JButton("Remove");
		remove.addActionListener(e -> {
			this.model.remove(combo.getSelectedIndex());
			this.updateView();
		});
		JButton concat = new JButton("Concat");
		concat.addActionListener(e -> {
			this.model.concat(combo.getSelectedIndex());
			this.updateView();
		});
		JButton add = new JButton("Add");
		add.addActionListener(e -> {
			this.model.add(combo.getSelectedIndex());
			this.updateView();
		});
		final JPanel main = new JPanel();
		main.setLayout(new FlowLayout());
		main.add(combo);
		main.add(remove);
		main.add(concat);
		main.add(add);
		gui.getContentPane().add(main);
		gui.pack();
		gui.setVisible(true);
	}
	
	private void updateView() {
		this.combo.removeAllItems();
		IntStream.range(0, this.model.getLines()).forEach(i -> this.combo.addItem(this.model.getLine(i)));
		combo.setSelectedIndex(0);
	}

}
