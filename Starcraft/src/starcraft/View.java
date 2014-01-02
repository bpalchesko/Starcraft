package starcraft;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel {

	Map map;
	Marine marine1;
	Marine marine2;
	Marine marine3;
	Marine marine4;
	
	public View() {
		map = new Map();
		marine1 = new Marine(300, 180);
		marine2 = new Marine(350, 160);
		marine3 = new Marine(400, 140);
		marine4 = new Marine(450, 120);
	}

	static BufferedImage loadImage(String fileName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("resources/" + fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image: " + fileName);
		}
		return img;
	}

	@Override
	public void paint(Graphics g) {
		map.draw(g);
		marine1.draw(g);
		marine2.draw(g);
		marine3.draw(g);
		marine4.draw(g);
	}

}
