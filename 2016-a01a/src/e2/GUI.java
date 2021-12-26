package e2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.*;

public class GUI {
    
	private final List<JButton> btns;
	private Model model;
	
    public GUI(String fileName, int size){
        JFrame jf = new JFrame();
        this.model = new ModelImpl(fileName);
        this.btns = IntStream.range(0, size).mapToObj(String::valueOf).map(JButton::new).collect(Collectors.toList());
        var first = this.model.hasNextNumber() ? this.model.nextNumber() : "null";
        this.btns.stream().forEach(btn -> { 
        	if(btn.getText().equals(first)) {
        		btn.setEnabled(true);
        	} else {
        		btn.setEnabled(false);
        	}
        });
        this.btns.stream().forEach(btn -> btn.addActionListener(e -> updateButtons()));
        JButton jbOK = new JButton("Reset");
        jbOK.addActionListener(e -> resetButtons());
        JPanel jp = new JPanel();
        this.btns.stream().forEach(btn -> jp.add(btn));
        jp.add(jbOK);
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
    
    private void updateButtons() {
    	if(this.model.hasNextNumber()) {
    		var number = this.model.nextNumber();
    		for(var button: this.btns) {
    			if(button.getText().equals(number)) {
    				button.setEnabled(true);
    			} else {
    				button.setEnabled(false);
    			}
    		}
    	} else {
    		for(var button: this.btns) {
    			button.setEnabled(false);
    		}
    	}
    }
    
    private void resetButtons() {
    	this.model = this.model.reset();
    	this.updateButtons();
    }

}
