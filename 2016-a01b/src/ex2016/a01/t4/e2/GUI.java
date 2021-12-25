package ex2016.a01.t4.e2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUI {
    
	private Model model;
	private final List<JButton> buttons;
	
    public GUI(String fileName, int size){
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        this.model = new ModelImpl(fileName);
        this.buttons = new ArrayList<>();
        var val = this.model.hasNextNumber() ? this.model.nextNumber() : "-1";
        for(int i = 0; i < size; i++) {
        	buttons.add(new JButton(val));
        	buttons.get(i).addActionListener(e -> this.updateButtons());
        	jp.add(buttons.get(i));                                                       
        }      
        JButton jbReset = new JButton("Reset");
        jbReset.addActionListener(e -> resetButtons());
        jp.add(jbReset);
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
    
    private void updateButtons() {
    	if(model.hasNextNumber()) {
			var value = model.nextNumber();
			for(var button: buttons) {
				button.setText(value);
			}
		} else {
			for(var button: buttons) {
				button.setEnabled(false);
			}
		}
    }
    
    
    private void resetButtons() {
    	this.model = this.model.reset();
    	this.updateButtons();
    }

}
