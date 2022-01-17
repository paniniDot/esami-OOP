package e2;

import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Pair<Integer,Integer>, JButton> cells = new HashMap<>();
    private final JButton next = new JButton(">");
    private final Model model;
    
    public GUI(int size) {
        this.model = new ModelImpl(size);
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,next);
        
       for(int i=0; i<size; i++) {
    	   for(int j=0; j<size; j++) {
    		   var p = new Pair<>(i, j);
    		   var jb = new JButton("");
    		   grid.add(jb);
    		   jb.addActionListener(e -> {
    			   this.model.hitManually(p.getX(), p.getY());
    			   this.updateView();
    		   });
    		   this.cells.put(p, jb);
    	   }
       }
       this.next.addActionListener(e -> {
    	   this.model.hitFromButton();
    	   this.updateView();
       });
    	this.updateView();
        this.setVisible(true);
    }
    
    private void updateView() {
    	this.cells.entrySet().forEach(entry -> entry.getValue().setText(this.model.getGridStatus().contains(entry.getKey()) ? ""+this.model.getGridStatus().indexOf(entry.getKey()) : " "));
    }
    
}
