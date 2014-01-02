package starcraft;

import java.awt.Graphics;
import java.awt.Image;

public class Marine extends Sprite {
	
	int frame;
	int cycles;
	
	public Marine(int x, int y) {
		spriteImages = loadImages("Marine");
		speed = 2;
		orientation = Orientation.SSE;
		spriteImage = spriteImages[orientation.imageGroup][4];
		xCurrent = xDestination = x;
		yCurrent = yDestination = y;
		frame = 4;
	}
	
	@Override
	void move() {
		orientation = selectOrientation();
		spriteImage = spriteImages[orientation.imageGroup][frame];
		setMovement();
		yCurrent += dy;
		xCurrent += dx;
		if (frame < 12) frame++;
		else frame = 4;
	}
	
	@Override
	void shoot() {
		spriteImage = spriteImages[5][frame];
		if (frame < 3) frame++;
		else frame = 2;
	}
	
	
	@Override
	void update() {
		move();
//		if(cycles > 175) spriteImage = spriteImages[5][4];
//		else if(cycles > 150) move();
//		else if(cycles > 125) shoot();
//	    else if(cycles > 100) move();
//		else if(cycles > 75) shoot();
//		else if(cycles > 50) move();
//		else if(cycles > 25) shoot();
//		else move();	
		cycles++;
	}
	
	@Override
	void draw(Graphics g) {
		g.drawImage(spriteImage, xCurrent, yCurrent, null);
	}

}
