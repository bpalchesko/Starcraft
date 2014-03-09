package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Defines a player or CPU-controlled sprite.
 * 
 * @author Brad Palchesko
 *
 */
public abstract class Sprite implements Comparable<Sprite> {

	int xCurrent;
	int yCurrent;
	int xDestination;
	int yDestination;
	int speed;
	int frame;
	boolean shooting;
	boolean inSquad;
	int dx;
	int dy;
	final int STANDING_FRAME;
	final int SHOOTING_FRAME;
	final int LAST_FRAME;	
	Orientation orientation;
	BufferedImage spriteImage;
	BufferedImage[][] spriteImages;
	
	/**
	 * Builds sprite by setting location, starting image, and constants for
	 * standing and shooting image groups.
	 * 
	 * @param spriteName
	 * @param standingFrame
	 * @param lastFrame
	 * @param x
	 * @param y
	 */
	public Sprite(String spriteName, int standingFrame, int lastFrame, int x, int y) {
		STANDING_FRAME = standingFrame;
		SHOOTING_FRAME = STANDING_FRAME - 1;
		LAST_FRAME = lastFrame;
		frame = STANDING_FRAME;
		spriteImages = loadImages(spriteName);
		shooting = false;
		inSquad = false;
		orientation = Orientation.SSE;
		xCurrent = xDestination = x;
		yCurrent = yDestination = y;
		setMovement();
	}

	/**
	 * Defines values to associate the direction a sprite is facing 
	 * with the appropriate images.
	 * 
	 * @author Brad Palchesko
	 *
	 */
	public enum Orientation {
		N(0), NNE(1), ENE(2), E(3), ESE(4), SSE(5), S(6), SSW(7), WSW(8), W(9), WNW(
				10), NNW(11);
		int imageGroup;

		private Orientation(int imageGroup) {
			this.imageGroup = imageGroup;
		}
	};
	
	/**
	 * Loads the appropriate group of images for a sprite.
	 * 
	 * @param spriteName
	 * @return array of sprite images
	 */
	BufferedImage[][] loadImages(String spriteName) {
		spriteImages = new BufferedImage[12][13];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j <= LAST_FRAME; j++) {
				spriteImages[i][j] = View.loadImage(spriteName + i + "-" + j
						+ ".gif");
			}
		}
		return spriteImages;
	}
	
	/**
	 * Determines if the sprite is still moving towards its destination.
	 * 
	 * @return whether moving
	 */
	boolean isMoving() {
		return xDestination - xCurrent > speed/2
				|| xDestination - xCurrent < -speed/2
				|| yDestination - yCurrent > speed/2
				|| yDestination - yCurrent < -speed/2;
	}
	
	/**
	 * Selects the direction for a sprite to face, based on its 
	 * current location and destination.
	 * 
	 * @return direction
	 */
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
			if (slope <= -1)
				return Orientation.NNE;
			else if (slope < 0)
				return Orientation.ENE;
			else if (slope < 1)
				return Orientation.WNW;
			else
				return Orientation.NNW;
		} else {
			double slope = (double)yDistance / (double)xDistance;
			if (slope <= -1)
				return Orientation.SSW;
			else if (slope < 0)
				return Orientation.WSW;
			else if (slope < 1)
				return Orientation.ESE;
			else
				return Orientation.SSE;
		}
	}

	/**
	 * Sets sprite's current direction and rate of movement.
	 */
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
			spriteImage = spriteImages[orientation.imageGroup][STANDING_FRAME];
			dy = 0;
			dx = 0;
		}
	}

	/**
	 * Updates the location and image frames of the sprite while moving.
	 */
	void move() {
		spriteImage = spriteImages[orientation.imageGroup][frame];
		setMovement();
		yCurrent += dy;
		xCurrent += dx;
		if (frame < LAST_FRAME) frame++;
		else frame = STANDING_FRAME;
	}

	/**
	 * Selects images to animate the sprite while shooting.
	 */
	void shoot() {
		spriteImage = spriteImages[orientation.imageGroup][frame];
		if (frame < SHOOTING_FRAME) frame++;
		else if(frame == SHOOTING_FRAME) frame--;
		else frame = 0;
	}

	/**
	 * Animates and updates the sprite based on whether moving or shooting.
	 */
	void update() {
		if(shooting) shoot();
		else move();
	}
	
	/**
	 * Sets whether sprite is in the side's current squad.
	 * 
	 * @param squad
	 */
	public void setInSquad(boolean squad) {
		inSquad = squad;
	}

	/**
	 * Displays sprite image.
	 * 
	 * @param g
	 */
	void draw(Graphics g) {
		if(inSquad) {
			g.setColor(Color.YELLOW);
			g.drawOval(xCurrent, yCurrent + 2*spriteImage.getHeight()/3, 
					spriteImage.getWidth(), spriteImage.getHeight()/3);
		}
		g.drawImage(spriteImage, xCurrent, yCurrent, null);
	}
	
	/**
	 * Compares sprites based on y-coordinate location for proper 
	 * drawing placement.
	 */
	public int compareTo(Sprite that) {
		int result;
		if(this.yCurrent < that.yCurrent) result = -1;
		else if(this.yCurrent > that.yCurrent) result = 1;
		else result = 0;
		return result;
	}

}
