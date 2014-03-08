package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class View extends JPanel {

	private static final long serialVersionUID = -1303141469178168350L;
	Map map;
	Cast cast;
	PlayerMouseControls mouse;
	int[][] selectionBoxCoordinates;
	boolean selecting;
	
	/**
	 * Constructs view starting with a cast of 4 marines.
	 */
	public View() {
		map = new Map();
		cast = new Cast();
		cast.addHumanSprite(new Marine(300, 180));
		cast.addHumanSprite(new Marine(350, 160));
		cast.addHumanSprite(new Marine(400, 140));
		cast.addHumanSprite(new Marine(450, 120));
		//cast.addHumanSprite(new BattleCruiser(250, 120));
		selectionBoxCoordinates = new int[2][2];
		selecting = false;
	}

	/**
	 * Loads an image from 'resources'.
	 * 
	 * @param fileName
	 * @return buffered image
	 */
	static BufferedImage loadImage(String fileName) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("resources/" + fileName));
		} catch (IOException exc) {
			System.out.println("Can't load image: " + fileName);
		}
		return img;
	}
	
	public void setSelectionBox(int clickedX, int clickedY, int draggedX, int draggedY) {
		if(draggedX > clickedX) {
			selectionBoxCoordinates[0][0] = clickedX;
			selectionBoxCoordinates[1][0] = draggedX;
		} else {
			selectionBoxCoordinates[0][0] = draggedX;
			selectionBoxCoordinates[1][0] = clickedX;
		}
		if (draggedY > clickedY) {
			selectionBoxCoordinates[0][1] = clickedY;
			selectionBoxCoordinates[1][1] = draggedY;
		} else {
			selectionBoxCoordinates[0][1] = draggedY;
			selectionBoxCoordinates[1][1] = clickedY;
		}
	}

	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
	}
	
	@Override
	public void paint(Graphics g) {
		map.draw(g);
		for(Sprite s: cast.spriteList) {
			s.draw(g);
		}
		if(selecting) {
		g.setColor(Color.YELLOW);
		g.drawRect(selectionBoxCoordinates[0][0], selectionBoxCoordinates[0][1], 
				selectionBoxCoordinates[1][0] - selectionBoxCoordinates[0][0],
				selectionBoxCoordinates[1][1] - selectionBoxCoordinates[0][1]);
		}
	}

}
