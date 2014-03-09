package starcraft;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class PlayerMouseControls extends MouseInputAdapter {

	View view;
	public final int X = 0;
	public final int Y = 1;
	public final int CLICKED = 0;
	public final int DRAGGED = 1;
	int[][] selectedCoordinates;
	boolean mousePressed;
	
	PlayerMouseControls(View view) {
		this.view = view;
		mousePressed = false;
		selectedCoordinates = new int[2][2];
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (view.cast.humanSquad.size() > 0) {
			for (Sprite s : view.cast.humanSquad) {
				if (SwingUtilities.isRightMouseButton(e)) {
					s.xDestination = e.getX() - s.spriteImage.getWidth() / 2;
					s.yDestination = e.getY() - s.spriteImage.getHeight();
					s.shooting = false;
				} 
//					else if (SwingUtilities.isRightMouseButton(e)) {
//					s.xDestination = s.xCurrent;
//					s.yDestination = s.yCurrent;
//					s.shooting = true;
//					view.cast.preventSpriteOverlaps(s);
//				}
				view.cast.preventSpriteOverlaps(s);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { 
		if (SwingUtilities.isLeftMouseButton(e)) {
			mousePressed = true;
			selectedCoordinates[CLICKED][X] = e.getX();
			selectedCoordinates[CLICKED][Y] = e.getY();
			selectedCoordinates[DRAGGED][X] = e.getX();
			selectedCoordinates[DRAGGED][Y] = e.getY();
			view.setSelecting(mousePressed);
			view.setSelectionBox(selectedCoordinates[CLICKED][X], 
					selectedCoordinates[CLICKED][Y], 
					selectedCoordinates[DRAGGED][X], 
					selectedCoordinates[DRAGGED][Y]);
		}
	}
		
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			mousePressed = false;
			view.setSelecting(mousePressed);
			view.cast.selectHumanSquad(selectedCoordinates[CLICKED][X],
					selectedCoordinates[CLICKED][Y],
					selectedCoordinates[DRAGGED][X],
					selectedCoordinates[DRAGGED][Y]);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			selectedCoordinates[DRAGGED][X] = e.getX();
			selectedCoordinates[DRAGGED][Y] = e.getY();
			view.setSelectionBox(selectedCoordinates[CLICKED][X],
					selectedCoordinates[CLICKED][Y],
					selectedCoordinates[DRAGGED][X],
					selectedCoordinates[DRAGGED][Y]);
		}
	}
	
	public boolean getPressed() {
		return mousePressed;
	}
	
}
