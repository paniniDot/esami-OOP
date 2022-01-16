package e2;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.awt.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = -6218820567019985015L;
	private final List<JButton> cells;
	private final JButton next = new JButton(">");
	private final Model model;
	
	public GUI(int rows) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(5 * 100, 100);

		JPanel panel = new JPanel(new GridLayout(1, rows));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.getContentPane().add(next, BorderLayout.SOUTH);

		this.model = new ModelImpl(rows);
		this.cells = IntStream.range(0, rows)
				.mapToObj(i -> new JButton(this.getTextAt(i)))
				.peek(panel::add)
				.collect(Collectors.toList());
		
		this.next.addActionListener(e -> {
			this.model.move();
			this.updateView();
		});
		
		this.setVisible(true);
	}
	
	private String getTextAt(final int i) {
		return i == this.model.getCurrentPosition() ? "*" : this.model.getObstaclePositions().containsKey(i) ? ""+this.model.getObstaclePositions().get(i) : " ";
	}
	
	private void updateView() {
		IntStream.range(0, this.cells.size())
			.forEach(i -> this.cells.get(i).setText(this.getTextAt(i)));
		if(this.model.gameOver()) {
			System.exit(0);
		}
	}
}
	
