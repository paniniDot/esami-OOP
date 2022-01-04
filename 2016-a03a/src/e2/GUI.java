package e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.*;

public class GUI {
    
    private final List<JButton> buttons = new ArrayList<>();
	private final Model model;
	public GUI(int size){
		this.model = new ModelImpl(size);
		JPanel jp = new JPanel();
		for(int i = 0; i < size; i++) {
			var btn = new JButton(i == 0 ? "A" : i == 1 ? "B" : " ");
			btn.setEnabled(false);
			this.buttons.add(btn);
			jp.add(btn);
		}
		JButton moveA = new JButton("MoveA");
		moveA.addActionListener(e -> {
			this.model.moveA(); 
			this.updateView();}
		);
		JButton moveB = new JButton("MoveB");
		moveB.addActionListener(e -> {
			this.model.moveB(); 
			this.updateView();
		});
		JButton reset = new JButton("Reset");
		reset.addActionListener(e -> {
			this.model.reset(); 
			this.updateView();
		});
        jp.add(moveA);
        jp.add(moveB);
        jp.add(reset);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
	
	private void updateView() {
		for(int i = 0; i < this.buttons.size(); i++) {
			this.buttons.get(i).setText(i == this.model.getACurrentPosition() ? "A" : i == this.model.getBCurrentPosition() ? "B" : " ");
		}
		if(this.model.isOver()) {
			System.exit(0);
		}
	}
    
}
