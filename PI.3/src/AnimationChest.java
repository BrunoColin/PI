import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author romulo <br>
 *         </br>
 *         Interface for create chest animation open and close.All chest classes
 *         must implement this interface.
 */
interface AnimationChest {
	/**
	 * 
	 * @param spriteSheetChest
	 *            - The sprite sheet of the chest
	 * @param context
	 *            - The graphics context of the game
	 * @param x
	 *            - the chest x position in map
	 * @param y
	 *            - the chest y position in map
	 * @param time
	 *            - speed of the animation
	 * @param isOpen
	 *            - if is opening or closing
	 */
	public void animationChest(BufferedImage spriteSheetChest, Graphics2D context, int x, int y, float time,
			boolean isOpening);
}
