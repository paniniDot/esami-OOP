package e2;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {
    
    private JButton l = new JButton("l");
    private JButton r = new JButton("r");
    private JComboBox<String> box;
    private Model model;
    
    public GUI(){
    	l.setEnabled(false);
    	r.setEnabled(false);
        box = new JComboBox<>();
        box.addItem("l,l,l,l,l");
        box.addItem("r,r,r");
        box.addItem("r,l,r,r,r,l");
        ActionListener al = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("rawtypes")
                String item = ((String)((JComboBox)e.getSource()).getSelectedItem());
                model = new ModelImpl(item);
                l.setEnabled(true);
                r.setEnabled(true);
            }
        };
        box.addActionListener(al);
        l.addActionListener(e -> { this.model.move("l"); this.checkWin(); });
        r.addActionListener(e -> { this.model.move("r"); this.checkWin(); }); 
        JPanel jp = new JPanel();
        jp.add(box);
        jp.add(l);
        jp.add(r);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);
    }
    
    private void checkWin() {
    	if(this.model.win()) {
    		System.exit(0);
    	}
    }
}