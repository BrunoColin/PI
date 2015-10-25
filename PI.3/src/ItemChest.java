import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author romulo
 *
 */
public class ItemChest extends Chest {

	private static final int MAX_CAPACITY = 100;
	private int numSprite;
	private List<Item> chestItemList;
	private CanvasInventario playerInventory;
	private int width;
	private int height;

	/**
	 * 
	 * @param chest
	 *            -Sprite sheet to chest object;
	 * @param x
	 *            - The x coordinate of the object that will appear in the map
	 * @param y
	 *            - The y coordinate of the object that will appear in the map
	 * @param numSprites
	 *            - The number of sprites in width dimension.
	 */
	public ItemChest(BufferedImage chest, int x, int y, int numSprites) {
		super(chest, x, y);
		this.numSprite = numSprites;
		this.width = sshChest.getWidth() / numSprite;
		this.height = sshChest.getHeight();
		getOpenChest();
		getClosedChest();
		setCollisionArea(new Rectangle(x, y, width, height));
		chestItemList = new ArrayList<Item>();
	}

	public List<Item> getChestItemList() {
		return chestItemList;
	}

	public void setChestItemList(List<Item> chestItemList) {
		this.chestItemList = chestItemList;
	}

	public CanvasInventario getPlayerInventory() {
		return playerInventory;
	}

	public void setPlayerInventory(CanvasInventario playerInventory) {
		this.playerInventory = playerInventory;
	}

	public static int getMaxCapacity() {
		return MAX_CAPACITY;
	}

	public void getClosedChest() {
		try {
			closed = sshChest.getSubimage(0, 0, width, height);
		} catch (RasterFormatException e) {
			e.getMessage();
			e.fillInStackTrace();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public void getOpenChest() {
		try {
			/**
			 * Get the new x position to get the last sprite in sprite sheet,
			 * that represents the open chest.
			 **/
			int posx = (numSprite - 1) * width;
			open = sshChest.getSubimage(posx, 0, width, height);
		} catch (RasterFormatException e) {
			e.getMessage();
			e.fillInStackTrace();
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	/**
	 * Add item to the item chest
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		chestItemList.add(item);
	}

	public void removeItem(Item item) {
		chestItemList.remove(item);
	}
}
