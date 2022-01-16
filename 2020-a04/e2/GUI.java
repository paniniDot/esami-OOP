package e2;

import javax.swing.*;

import e2.Model.Piece;

import java.util.*;
import java.awt.*;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private final Model model;
    
    public GUI(int size) {
    	this.model = new ModelImpl(size);
    	
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        for(int i=0; i<size; i++) {
        	for(int j=0; j<size; j++) {
        		var jb = new JButton(" ");
        		panel.add(jb);
        		this.cells.put(jb, new Pair<>(i, j));
        	}
        }
       this.updateView();
       this.cells.entrySet().forEach(jb -> jb.getKey().addActionListener(e -> {
    	   System.out.println("[" + jb.getValue().getX() + ", " + jb.getValue().getY() + "]");
    	   this.model.move(jb.getValue().getX(), jb.getValue().getY());
    	   this.updateView();
    	   if(this.model.gameOver()) {
    		   System.exit(0);
    	   }
       }));
        
        this.setVisible(true);
    }
    
    private void updateView() {
    	for(int i=0; i<this.model.getLeftedPawns(); i++) {
    		for(var entry: this.cells.entrySet()) {
    			if(entry.getValue().equals(this.model.getPawnPositions().get(i))) {
        			entry.getKey().setText(Piece.PAWN.toString());
        		} else if(entry.getValue().equals(this.model.getPlayerPosition().getY())) {
        			entry.getKey().setText(this.model.getPlayerPosition().getX().toString());
        		} else if(!this.model.getPawnPositions().contains(entry.getValue()) && !this.model.getPlayerPosition().getY().equals(entry.getValue())) {
        			entry.getKey().setText(" ");
        		}
    		}
    	}
    }
    
}
