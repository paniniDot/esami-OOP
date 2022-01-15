package e2;

import java.awt.BorderLayout;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;



public class GUI extends JFrame{
	
	private final List<JButton> buttonsPlayerA;
	private final List<JButton> buttonsPlayerB;
	private final Model model;
	
	public GUI(int size){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		JPanel jNorth = new JPanel();
		JPanel jSouth = new JPanel();
		JPanel jCenter = new JPanel();
		this.getContentPane().add(BorderLayout.NORTH, jNorth);
		this.getContentPane().add(BorderLayout.SOUTH, jSouth);
		this.getContentPane().add(BorderLayout.CENTER, jCenter);
		this.model = new ModelImpl(size);
		this.buttonsPlayerA = IntStream.range(0, size)
				.mapToObj(i -> new JButton("*"))
				.peek(jNorth::add)
				.peek(jb -> jb.setEnabled(false))
				.collect(Collectors.toList());
		this.buttonsPlayerB = IntStream.range(0, size)
				.mapToObj(i -> new JButton("*"))
				.peek(jSouth::add)
				.peek(jb -> jb.setEnabled(false))
				.collect(Collectors.toList());
		JButton play = new JButton("Play");
		play.addActionListener(e -> {
			this.model.play();
			this.updateView();
			if(this.model.gameOver()) {
				System.exit(0);
			}
		});
		jCenter.add(play);
		this.pack();
		this.setVisible(true);
	}
	
	private void updateView() {
		IntStream.range(0, this.buttonsPlayerA.size())
			.forEach(i -> this.buttonsPlayerA.get(i).setText(i <= this.model.getPointsOfA() ? "*" : " "));
		IntStream.range(0, this.buttonsPlayerB.size())
			.forEach(i -> this.buttonsPlayerB.get(i).setText(i <= this.model.getPointsOfB() ? "*" : " "));
	}
	
	public static void main(String[] args) {
		new GUI(6);
	}

}

