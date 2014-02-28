package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Map {

	Image backgroundImage;
	
	/**
	 * Constructs a background by loading the background image.
	 */
	public Map() {
		backgroundImage = View.loadImage("map.jpg");
	}
	
	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		g.drawImage(backgroundImage, 0, 0, g.getClipBounds().width,
				g.getClipBounds().height, null);
	}
}
