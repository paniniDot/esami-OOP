package e2;

import javax.swing.*;
import java.util.*;

public class GUI {
    
	private final List<JButton> btns = new ArrayList<>();
    private final JButton move = new JButton("Move");
    private final JButton reset = new JButton("Reset");
    private final Model model;
    
    public GUI(int size){
    	JPanel jp = new JPanel();
    	this.model = new ModelImpl(size);
    	for(int i = 0; i < size; i++) {
        	this.btns.add(new JButton(i == 0 ? "*" : " "));
        	var button = this.btns.get(i);
        	button.addActionListener(e -> {  
        		if(this.model.setDisabled(this.btns.indexOf(e.getSource()))) {
        			button.setEnabled(false);
        		}
        	});
        	jp.add(btns.get(i));
        } 
    	this.move.addActionListener(e -> updateBtns());
    	this.reset.addActionListener(e -> {
    		this.model.reset();
    		this.btns.stream().forEach(btn -> btn.setEnabled(true));
    		this.btns.stream().forEach(btn -> btn.setText(this.btns.indexOf(btn) == this.model.getCurrent() ? "*" : " "));
    	});
    	jp.add(this.move);
    	jp.add(this.reset);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
    
    private void updateBtns() {
    	this.model.step();
    	for(int i = 0; i < this.btns.size(); i++) {
    		if(i == this.model.getCurrent()) {
    			this.btns.get(i).setText("*");
    		} else {
    			this.btns.get(i).setText(" ");
    		}
    	}
    }
    
    public static void main(String[] s){
        new GUI(10);
    }

}