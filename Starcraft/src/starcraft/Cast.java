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
	int[][] humanSquadSelectionBox;
	Random generator;
	
	/**
	 * Constructs an empty cast.
	 */
	public Cast() {
		spriteList = new ArrayList<Sprite>();
		humanSprites = new ArrayList<Sprite>();
		humanSquad = new ArrayList<Sprite>();
		humanSquadSelectionBox = new int[2][2];
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

	/**
	 * Sets the group of human sprites within the given x-y bounds 
	 * as the human squad.
	 *  
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void setHumanSquad(int x1, int y1, int x2, int y2) {
		humanSquadSelectionBox = PlayerMouseControls.updateSelectionBox(x1,y1,x2,y2);
		for (Sprite s: humanSquad) {
			s.setInSquad(false);
		}
		humanSquad = new ArrayList<Sprite>();
		for (Sprite s : humanSprites) {
			if (s.xCurrent >= humanSquadSelectionBox[0][0]
					&& s.xCurrent <= humanSquadSelectionBox[1][0]
					&& s.yCurrent >= humanSquadSelectionBox[0][1]
					&& s.yCurrent <= humanSquadSelectionBox[1][1]) {
				humanSquad.add(s);
				s.setInSquad(true);
			}
		}
	}
	
	/**
	 * Sorts cast for proper drawing.
	 */
	void sortCastByLocation() {
        Collections.sort(spriteList);
	}
}
