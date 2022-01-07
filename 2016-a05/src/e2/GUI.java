package e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.*;

public class GUI {
      
	private final List<JButton> buttons;
	Model model;
    public GUI(int size){
    	JPanel jp = new JPanel();
    	this.model = new ModelImpl(size);
    	this.buttons = IntStream.range(0, size)
    			.mapToObj(i -> new JButton(" "))
    			.peek(jb -> jb.setEnabled(false))
    			.peek(jp::add)
    			.collect(Collectors.toList());
    	this.updateView();
        JButton move = new JButton("Move");
        move.addActionListener(e -> {this.model.move(); this.updateView();});
        JCheckBox goRight = new JCheckBox("goRight", true);
        goRight.addActionListener(e -> this.model.changeDirection());
        jp.add(move);
        jp.add(goRight);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
    
    private void updateView() {
    	this.buttons.forEach(jb -> jb.setText(this.buttons.indexOf(jb) == this.model.getPosition() ? "X" : " "));
    }

}
