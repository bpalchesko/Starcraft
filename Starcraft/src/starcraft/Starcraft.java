package starcraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Defines Starcraft minigame gameplay.
 * 
 * @author Brad Palchesko
 *
 */
public class Starcraft extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel bottomBar;
	JLabel tempStatus;
	JButton addMarine;
	View view;
	Timer timer;
	int x;
	int y;

	/**
	 * Main method for this game application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Starcraft().createGame();
	}

	/**
	 * Builds game frame and player controls.
	 */
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

			@Override
			public void mouseClicked(MouseEvent e) {
				for (Sprite s : view.cast.humanSprites) {
					if (SwingUtilities.isLeftMouseButton(e)) {
						s.xDestination = e.getX() - s.spriteImage.getWidth()/2;
						s.yDestination = e.getY() - s.spriteImage.getHeight();
						s.shooting = false;
					} else if (SwingUtilities.isRightMouseButton(e)) {
						s.xDestination = s.xCurrent;
						s.yDestination = s.yCurrent;
						s.shooting = true;
						view.cast.preventSpriteOverlaps(s);
					}
					view.cast.preventSpriteOverlaps(s);
				}
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
	}

	/**
	 * Controls cast updates for animation.
	 * 
	 * @author Brad Palchesko
	 *
	 */
	private class Strobe extends TimerTask {
		public void run() {
			updateGame();
			view.cast.updateSpriteOrientations(true);
		}
	}
	
	/**
	 * Controls less frequent updates for character navigation.
	 * 
	 * @author Brad Palchesko
	 *
	 */
	private class OrientationUpdate extends TimerTask {
		public void run() {
			view.cast.updateSpriteOrientations(false);
		}
	}

	/**
	 * Starts updates for animation and gameplay.
	 */
	void startGame() {
		timer = new Timer();
		timer.schedule(new Strobe(), 0, 40);
		timer.schedule(new OrientationUpdate(), 0, 1000);
	}

	/**
	 * Updates individual sprites and sorts for proper animation.
	 */
	void updateGame() {
		for (Sprite s : view.cast.spriteList) {
			s.update();
		}
		view.cast.sortCastByLocation();
		view.repaint();
	}

	/**
	 * Adds panel for player unit selection and creation.
	 */
	void addBottomBar() {
		bottomBar = new JPanel();
		bottomBar.setLayout(new BorderLayout());
		bottomBar.setBackground(Color.black);
		bottomBar.setPreferredSize(new Dimension(800, 75));
		add(bottomBar, BorderLayout.SOUTH);
	}

	/**
	 * Adds label currently just for testing.
	 */
	void addTempStatus() {
		tempStatus = new JLabel();
		tempStatus.setText("ok");
		tempStatus.setForeground(Color.WHITE);
		bottomBar.add(tempStatus, BorderLayout.CENTER);
	}

	/**
	 * Adds button to create a Marine sprite.
	 */
	void addMarineButton() {
		addMarine = new JButton("Marine");
		addMarine.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Marine m;
				view.cast.addHumanSprite(m = new Marine(30, 10));
				view.cast.preventSpriteOverlaps(m);
			}

			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		bottomBar.add(addMarine, BorderLayout.EAST);
	}
}
