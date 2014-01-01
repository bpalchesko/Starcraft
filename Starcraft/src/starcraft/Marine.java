package starcraft;

import java.awt.Graphics;
import java.awt.Image;

public class Marine extends Sprite {
	
	int frame;
	int cycles;
	Image marineImage;
	Image[][] marineImages;
	
	public Marine(int x, int y) {
		marineImages = new Image[12][13];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				marineImages[i][j] = View.loadImage("Marine" + i + "-" + j
						+ ".gif");
			}
		}
		marineImage = marineImages[0][0];
		this.x = x;
		this.y = y;
		dy = 2;
		dx = 1;
		frame = 4;
	}
	
	void walk() {
		y += dy;
		x += dx;
		marineImage = marineImages[5][frame];
		if (frame < 12) frame++;
		else frame = 4;
	}
	
	void shoot() {
		marineImage = marineImages[5][frame];
		if (frame < 3) frame++;
		else frame = 2;
	}
	
	
	@Override
	void update() {
		if(cycles > 175) marineImage = marineImages[5][4];
		else if(cycles > 150) walk();
		else if(cycles > 125) shoot();
	    else if(cycles > 100) walk();
		else if(cycles > 75) shoot();
		else if(cycles > 50) walk();
		else if(cycles > 25) shoot();
		else walk();
//		if(y>500) walk();
//		else shoot();	
		cycles++;
	}
	
	@Override
	void draw(Graphics g) {
		g.drawImage(marineImage, x, y, null);
	}

}
