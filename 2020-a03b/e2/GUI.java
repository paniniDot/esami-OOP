package e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    private final Model model;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
             
        this.model = new ModelImpl(size);
        
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++) {
            	var p = new Pair<>(i, j);
                final JButton jb = new JButton(this.model.getBishopCoords().equals(p) ? "B" : this.model.getPawnsCoords().contains(p) ? "*" : " ");
                jb.setEnabled(this.model.getBishopMoves().contains(p) && !p.equals(this.model.getBishopCoords()));
                jb.addActionListener(e -> {
                	this.model.hit(p);
                	this.updateView();
                });
                this.cells.put(jb, new Pair<>(i,j));
                grid.add(jb);
            }
        }   	
        this.setVisible(true);
    }
    
    private void updateView() {
    	this.cells.entrySet().forEach(entry -> {
    		entry.getKey().setText(this.model.getBishopCoords().equals(entry.getValue()) ? "B" : this.model.getPawnsCoords().contains(entry.getValue()) ? "*" : " ");
    		entry.getKey().setEnabled(this.model.getBishopMoves().contains(entry.getValue()) && !entry.getValue().equals(this.model.getBishopCoords()));
    	});
    }
    
}
