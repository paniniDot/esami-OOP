package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;

public class GUI extends JFrame {
	
	private final static int ELEMENTS = 4;
	
    private final List<JButton> buttons = new ArrayList<JButton>();
    
	public GUI(int size){
		JPanel jp = new JPanel();
		for(int i = 0; i < size; i++) {
			var btn = new JButton(i < 3 ? "*" : " ");
			btn.setEnabled(false);
			this.buttons.add(btn);
			jp.add(btn);
		}
		final Agent agent = new Agent();
		JButton stop = new JButton("Exit");
		stop.addActionListener(e -> { agent.stopAdvancing(); System.exit(0); });
		jp.add(stop);
        JFrame jf = new JFrame();
        jf.getContentPane().add(jp);
        jf.pack();
        jf.setVisible(true);	
        agent.start();
	}
	
	private class Agent extends Thread {
		
		
		private volatile boolean stop = false;
		private final Model model = new ModelImpl(ELEMENTS, buttons.size());
		
		public void run() {
			while (!stop) {
			    this.model.updateCurrentPositions();
			    SwingUtilities.invokeLater(() -> buttons.stream().forEach(btn -> btn.setText(this.model.getPositions().contains(buttons.indexOf(btn)) ? "*" : " ")));
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
