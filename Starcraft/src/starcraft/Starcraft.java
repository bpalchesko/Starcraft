package starcraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Starcraft extends JFrame {
	
	JPanel bottomBar;
	JLabel tempStatus;
	View view;
	Timer timer;
	int x;
	int y;

	public static void main(String[] args) {
		new Starcraft().createGame();

	}

	void createGame() {
		view = new View();
		setSize(800, 600);
		setResizable(false);
		setLayout(new BorderLayout());
		addBottomBar();
		addTempStatus();
		setVisible(true);
		startGame();
		
		addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				view.marine1.xDestination = e.getX() - view.marine1.spriteImage.getWidth()/2;
				view.marine1.yDestination = e.getY() - view.marine1.spriteImage.getHeight();;
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
		});
	}
	
	private class Strobe extends TimerTask {
		public void run() {
			updateGame();
		}
	}
	
	void startGame() {
		timer = new Timer();
		timer.schedule(new Strobe(), 0, 40);
	}
	
	void updateGame() {
		view.marine1.update();
		view.marine2.update();
		view.marine3.update();
		view.marine4.update();
		view.repaint();
		tempStatus.setText((view.marine1.xDestination-view.marine1.xCurrent) + " " + (view.marine1.yDestination - view.marine1.yCurrent) +
				" " + view.marine1.orientation);
	}
	
	void addBottomBar() {
		bottomBar = new JPanel();
		bottomBar.setLayout(new BorderLayout());
		bottomBar.setBackground(Color.black);
		bottomBar.setPreferredSize(new Dimension(800,75));
		add(bottomBar, BorderLayout.SOUTH);
		add(view, BorderLayout.CENTER);
	}
	
	void addTempStatus() {
		tempStatus = new JLabel();
		tempStatus.setText("ok");
		tempStatus.setForeground(Color.WHITE);
		bottomBar.add(tempStatus, BorderLayout.CENTER);
	}
}
