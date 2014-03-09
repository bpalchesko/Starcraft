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
	int[][] selectionBox;
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
		selectionBox = new int[2][2];
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
	
	/**
	 * Sets the selection box coordinates.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void setSelectionBox(int x1, int y1, int x2, int y2) {
		selectionBox = PlayerMouseControls.updateSelectionBox(x1,y1,x2,y2);
	}

	/**
	 * Sets whether the user is currently dragging the mouse,
	 * creating a selection box.
	 * 
	 * @param selecting
	 */
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
		g.drawRect(selectionBox[0][0], selectionBox[0][1], 
				selectionBox[1][0] - selectionBox[0][0],
				selectionBox[1][1] - selectionBox[0][1]);
		}
	}

}
