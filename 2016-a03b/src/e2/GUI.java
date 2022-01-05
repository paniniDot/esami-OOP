package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

public class GUI extends JFrame {

    private List<JButton> buttons = new ArrayList<>();
    
	public GUI(int size){
		JPanel jp = new JPanel();
		final Agent agent = new Agent(size);
		this.buttons = IntStream.range(0, size)
				.mapToObj(i -> new JButton(i == 0 ? "X" : " "))
				.peek(jb -> jb.setEnabled(true))
				.peek(this.buttons::add)
				.peek(jb -> jb.addActionListener(e -> {jb.setEnabled(false); agent.disable(this.buttons.indexOf(jb)); }))
				.peek(jp::add)
				.collect(Collectors.toList());
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {agent.stopAdvancing(); System.exit(0); });
        jp.add(exit);
		JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);		
        agent.start();
	}
	
	private class Agent extends Thread {

		private volatile boolean stop = false;
		private final Model model;
		
		public Agent(int size) {
			this.model = new ModelImpl(size);
		}
		
		public void disable(int index) {
			this.model.disable(index);
		}
		
		public void run() {
			while (!stop) {
			    this.model.move();
			    SwingUtilities.invokeLater(()-> buttons.stream().forEach(btn -> btn.setText(buttons.indexOf(btn) == this.model.getPosition() ? "X" : " ")));
			    if(this.model.isOver()) {
			    	this.stopAdvancing();
			    	System.exit(0);
			    }
				try {
					Thread.sleep(300);
				} catch (Exception ex) {
				}
			}
		}

		public void stopAdvancing() {
			this.stop = true;
		}
	}
}