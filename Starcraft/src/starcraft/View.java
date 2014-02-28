package starcraft;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel {

	private static final long serialVersionUID = -1303141469178168350L;
	Map map;
	Cast cast;
	
	/**
	 * Constructs view starting with a cast of 4 marines.
	 */
	public View() {
		map = new Map();
		cast = new Cast();
		cast.addHumanSprite(new Marine(300, 180));
		cast.addHumanSprite(new Marine(350, 160));
		cast.addHumanSprite(new Marine(400, 140));
		cast.addHumanSprite(new Marine(450, 120));
		//cast.addHumanSprite(new BattleCruiser(250, 120));
	}

	/**
	 * Loads an image from 'resources'.
	 * 
	 * @param fileName
	 * @return buffered image
	 */
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
		for(Sprite s: cast.spriteList) {
			s.draw(g);
		}
	}

}
