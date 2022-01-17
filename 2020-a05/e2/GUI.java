package e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Model model;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.model = new ModelImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	final var pair = new Pair<>(i, j);
                final JButton jb = new JButton(""+this.model.getGridStatus().get(pair));
                jb.addActionListener(e -> {
                	this.model.hit(pair.getX(), pair.getY());
                	this.updateView();
                	if(this.model.gameOver()) {
                		System.exit(0);
                	}
                });
                this.cells.put(jb, new Pair<>(i, j));
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void updateView() {
    	this.cells.forEach((k, v) -> k.setText(this.model.getGridStatus().containsKey(v) ? ""+this.model.getGridStatus().get(v) : "X"));
    }
    
}
