package e2;

import java.awt.BorderLayout;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	private Model model;
	private List<JButton> buttons;
	
	public GUI(String fileName) throws IOException {
		final JFrame gui = new JFrame();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		this.model = new ModelImpl(fileName);
		this.buttons = IntStream.rangeClosed(1, this.model.getLines().size())
				.mapToObj(i -> new JButton("Linea "+i))
				.peek(jb -> main.add(jb))
				.collect(Collectors.toList());
		this.buttons.forEach(jb -> jb.addActionListener(e -> {
			this.model.disableLine(this.buttons.indexOf(jb));
			jb.setEnabled(false);
		}));
		final JPanel outer = new JPanel(new BorderLayout());
		outer.add(main,BorderLayout.CENTER);
		JButton close = new JButton("Close");
		close.addActionListener(e -> {
			this.model.writeFile();
			System.exit(0);
		});
		outer.add(close,BorderLayout.SOUTH); // nell'esame il pulsante Close va messo però in basso, e possibilmente delle giuste dimensioni
		gui.getContentPane().add(outer);
		gui.setSize(600,300);
		gui.setVisible(true);
	}

}

