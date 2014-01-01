package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Map {

	Image backgroundImage;
	
	public Map() {
		backgroundImage = View.loadImage("map.jpg");
		System.out.println(backgroundImage);
	}
	
	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		g.drawImage(backgroundImage, 0, 0, g.getClipBounds().width,
				g.getClipBounds().height, null);
	}
}
