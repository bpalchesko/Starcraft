package starcraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Starcraft extends JFrame {

	JPanel bottomBar;
	JLabel tempStatus;
	JButton addMarine;
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
		add(view, BorderLayout.CENTER);
		addBottomBar();
		addMarineButton();
		addTempStatus();
		setVisible(true);
		startGame();

		view.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {

				for (Sprite s : view.cast.humanSprites) {
					s.xDestination = e.getX() - s.spriteImage.getWidth() / 2;
					s.yDestination = e.getY() - s.spriteImage.getHeight();
					view.cast.preventSpriteOverlaps(s);
				}
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
		for (Sprite s : view.cast.spriteList) {
			s.update();
		}
		view.repaint();
		// tempStatus.setText((view.marine1.xDestination-view.marine1.xCurrent)
		// + " " + (view.marine1.yDestination - view.marine1.yCurrent) +
		// " " + view.marine1.orientation);
	}

	void addBottomBar() {
		bottomBar = new JPanel();
		bottomBar.setLayout(new BorderLayout());
		bottomBar.setBackground(Color.black);
		bottomBar.setPreferredSize(new Dimension(800, 75));
		add(bottomBar, BorderLayout.SOUTH);
	}

	void addTempStatus() {
		tempStatus = new JLabel();
		tempStatus.setText("ok");
		tempStatus.setForeground(Color.WHITE);
		bottomBar.add(tempStatus, BorderLayout.CENTER);
	}

	void addMarineButton() {
		addMarine = new JButton("Marine");
		addMarine.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Marine m;
				view.cast.addHumanSprite(m = new Marine(10, 10));
				view.cast.preventSpriteOverlaps(m);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
		bottomBar.add(addMarine, BorderLayout.EAST);
	}
}
