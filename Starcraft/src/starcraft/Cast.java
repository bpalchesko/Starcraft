package starcraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Defines and organizes sprites and their interactions.
 * 
 * @author Brad Palchesko
 *
 */
public class Cast {

	ArrayList<Sprite> spriteList;
	ArrayList<Sprite> humanSprites;
	ArrayList<Sprite> humanSquad;
	Random generator;
	
	/**
	 * Constructs an empty cast.
	 */
	public Cast() {
		spriteList = new ArrayList<Sprite>();
		humanSprites = new ArrayList<Sprite>();
	}
	
	/**
	 * Adds a particular sprite to the list of player-controlled
	 * sprites.
	 * 
	 * @param sprite
	 */
	void addHumanSprite(Sprite sprite) {
		humanSprites.add(sprite);
		spriteList.add(sprite);
	}
	
	/**
	 * Checks if a particular sprite is approaching the same destination
	 * as any other player-controlled sprites.
	 * 
	 * @param sprite
	 * @return
	 */
	boolean spriteOverlaps(Sprite sprite) {
		for (Sprite t : humanSprites) {
				if (!t.equals(sprite) && Math.abs(sprite.xDestination - t.xDestination) < 10
						&& Math.abs(sprite.yDestination - t.yDestination) < 10) return true;
		}
		return false;
	}
	
	/**
	 * Updates sprite destinations as to prevent overlapping sprites.
	 * 
	 * @param sprite
	 */
	void preventSpriteOverlaps(Sprite sprite) {
		generator = new Random();
		while (spriteOverlaps(sprite)) {
			boolean offsetLeft = generator.nextBoolean();
			if (offsetLeft) {
				sprite.xDestination -= 30;
			} else
				sprite.xDestination += 30;
			sprite.yDestination += 20;
		}
	}
	
	/**
	 * Updates all sprite orientations.
	 * 
	 * @param nearDestination
	 */
	void updateSpriteOrientations(boolean nearDestination) {
		for (Sprite s: spriteList) {
			if(!nearDestination || s.xCurrent - s.xDestination < 10 || 
					s.yCurrent - s.yDestination < 10) {
			    s.orientation = s.selectOrientation();
			}
		}
	}
	
//	void findSpriteDestination() {
//		for (Sprite s: spriteList) {
//			s.orientation = s.selectOrientation();
//		}
//	}
	
	/**
	 * Sorts cast for proper drawing.
	 */
	void sortCastByLocation() {
        Collections.sort(spriteList);
	}
}
