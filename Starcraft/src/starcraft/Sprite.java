package starcraft;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Sprite {

	int xCurrent;
	int yCurrent;
	int xDestination;
	int yDestination;
	int speed;
	int dx;
	int dy;
	Orientation orientation;
	BufferedImage spriteImage;
	BufferedImage[][] spriteImages;

	public enum Orientation {
		N(0), NNE(1), ENE(2), E(3), ESE(4), SSE(5), S(6), SSW(7), WSW(8), W(9), WNW(
				10), NNW(11);
		int imageGroup;

		private Orientation(int imageGroup) {
			this.imageGroup = imageGroup;
		}
	};
	
	BufferedImage[][] loadImages(String spriteName) {
		spriteImages = new BufferedImage[12][13];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				spriteImages[i][j] = View.loadImage(spriteName + i + "-" + j
						+ ".gif");
			}
		}
		return spriteImages;
	}
	
	boolean isMoving() {
		return xDestination - xCurrent > speed/2
				|| xDestination - xCurrent < -speed/2
				|| yDestination - yCurrent > speed/2
				|| yDestination - yCurrent < -speed/2;
	}
	
	Orientation selectOrientation() {
		int xDistance = xDestination - xCurrent;
		int yDistance = yDestination - yCurrent;
		if (!isMoving()) {
			return orientation;
		} else if (xDistance == 0) {
			if (yDistance < 0)
				return Orientation.N;
			else
				return Orientation.S;
		} else if (yDistance == 0) {
			if (xDistance > 0)
				return Orientation.E;
			else
				return Orientation.W;
		} else if (yDistance < 0) {
			double slope = (double)yDistance / (double)xDistance;
			if (slope <= -1)	//was -1
				return Orientation.NNE;
			else if (slope < 0)
				return Orientation.ENE;
			else if (slope < 1)	//was 1
				return Orientation.WNW;
			else
				return Orientation.NNW;
		} else {
			double slope = (double)yDistance / (double)xDistance;
			if (slope <= -1)	//was -1
				return Orientation.SSW;
			else if (slope < 0)
				return Orientation.WSW;
			else if (slope < 1)		//was 1
				return Orientation.ESE;
			else
				return Orientation.SSE;
		}
	}

	void setMovement() {
		if (isMoving()) {
			switch (orientation) {
			case N:
				dy = -speed;
				dx = 0;
				break;
			case NNE:
				dy = -speed;
				dx = speed / 2;
				break;
			case ENE:
				dy = -speed / 2;
				dx = speed;
				break;
			case E:
				dy = 0;
				dx = speed;
				break;
			case ESE:
				dy = speed / 2;
				dx = speed;
				break;
			case SSE:
				dy = speed;
				dx = speed / 2;
				break;
			case S:
				dy = speed;
				dx = 0;
				break;
			case SSW:
				dy = speed;
				dx = -speed / 2;
				break;
			case WSW:
				dy = speed / 2;
				dx = -speed;
				break;
			case W:
				dy = 0;
				dx = -speed;
				break;
			case WNW:
				dy = -speed / 2;
				dx = -speed;
				break;
			case NNW:
				dy = -speed;
				dx = -speed / 2;
				break;
			}
		} else {
			spriteImage = spriteImages[orientation.imageGroup][4];
			dy = 0;
			dx = 0;
		}
	}

	abstract void move();

	abstract void shoot();

	abstract void update();

	abstract void draw(Graphics g);

}
