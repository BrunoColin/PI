import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * A class that wants to be a dialog class of any type must implement this
 * interface.
 * 
 * @author Rômulo
 *
 */
interface GameDialogUI extends myCanvas {
	public void drawDialogBox(Graphics2D graphicsContext, BufferedImage textBox);
}