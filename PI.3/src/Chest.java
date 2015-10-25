import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author romulo </br>
 *         Abstract class to represent all chest objects in the game. A class
 *         that wants to represent a chest in the game must extend this class.
 */
public abstract class Chest implements AnimationChest {

	protected BufferedImage sshChest;
	protected BufferedImage open;
	protected BufferedImage closed;
	private Rectangle collisionArea;

	public BufferedImage getSshChest() {
		return sshChest;
	}

	public void setSshChest(BufferedImage sshChest) {
		this.sshChest = sshChest;
	}

	public BufferedImage getOpen() {
		return open;
	}

	public void setOpen(BufferedImage open) {
		this.open = open;
	}

	public BufferedImage getClose() {
		return closed;
	}

	public void setClose(BufferedImage close) {
		this.closed = close;
	}

	protected int x;
	protected int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getChest() {
		return sshChest;
	}

	public void setChest(BufferedImage chest) {
		this.sshChest = chest;
	}

	public Chest(BufferedImage chest, int x, int y) {
		this.sshChest = chest;
		this.x = x;
		this.y = y;

	}

	@Override
	public void animationChest(BufferedImage spriteSheetChest, Graphics2D context, int x, int y, float time,
			boolean isOpening) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D context, boolean isClosed, int xmundo, int ymundo) {
		if (isClosed) {
			context.drawImage(closed, x+xmundo, y+ymundo, null);
		} else {
			context.drawImage(open, x+xmundo, y+ymundo, null);
		}
	}

	public Rectangle getCollisionArea() {
		return collisionArea;
	}

	public void setCollisionArea(Rectangle collisionArea) {
		this.collisionArea = collisionArea;
	}
}
