package starcraft;

/**
 * Defines the sprite Battle Cruiser.
 * 
 * @author Brad Palchesko
 *
 */
public class BattleCruiser extends Sprite {
	
	/**
	 * Constructs a battle cruiser at the given location using the
	 * 'BattleCruiser' image group and speed of 3.
	 * 
	 * @param x
	 * @param y
	 */
	public BattleCruiser(int x, int y) {
		super("BattleCruiser", 2, 2, x, y);
		speed = 3;
	}

}
