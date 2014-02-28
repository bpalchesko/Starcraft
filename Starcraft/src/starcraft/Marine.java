package starcraft;

/**
 * Defines the sprite Marine.
 * 
 * @author Brad Palchesko
 *
 */
public class Marine extends Sprite {
	
	/**
	 * Constructs a marine at the given location using the 
	 * 'Marine' image group and a speed of 2.
	 * 
	 * @param x
	 * @param y
	 */
	public Marine(int x, int y) {
		super("Marine", 4, 12, x, y);
		speed = 2;
	}

}
