package e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    private final Model model;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
             
        this.model = new ModelImpl(size);
        
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var coords = new Pair<>(i, j);
                final JButton jb = new JButton(" ");
                jb.addActionListener(e -> {
                	this.model.hit(coords);
                	this.updateView();
                	if(this.model.gameOver()) {
                		System.exit(0);
                	}
                });
                this.cells.put(coords, jb);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void updateView() {
    	IntStream.range(0, this.model.getPressed().size()).forEach(i -> this.cells.get(this.model.getPressed().get(i)).setText(""+(i+1)));
    }
}
