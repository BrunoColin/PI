import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 */

/**
 * Singleton pattern class that represents the user interface between the user
 * and the chest inventory. All user operations in chest inventory is handled
 * here.
 * 
 * @author Rômulo
 * @since 1.5-Alpha
 *
 */
public class ItemChestUI extends UI implements InterchangeItems {

	private static ItemChestUI instance = new ItemChestUI(LoadResources.uiItemChest);
	private int posX;
	private int posY;
	private int posXItemSelected;
	private int posYtemSelected;
	private Item itemSelected;
	private boolean isInChest;
	private boolean isSwapped;
	private boolean isItemSelected;
	private ArrayList<Item> userItens;
	private ArrayList<Item> chestItemList;
	private ItemChest itemChest;
	private static final int MAX_X = 9;
	private static final int MAX_Y = 3;

	private BufferedImage UIBackground;
	DialogFactory dialogFactory;
	GameDialogUI dialog;
	private boolean isSelected = false;
	private int cont;

	private static final int WIDTH_SLOT = 53;
	private static final int HEIGHT_SLOT = 53;

	private ItemChestUI(BufferedImage UIbackground) {
		this.UIBackground = UIbackground;
		drawUI(GamePanel.instance.getDbg(), UIbackground);
		Item[][] items = CanvasInventario.instance.getMatrizItens();
		getUserItens(items);
		itemChest = CanvasGame2.instance.getItemChest();
		chestItemList = (ArrayList<Item>) itemChest.getChestItemList();
	}

	// Get the only object available
	public static ItemChestUI getInstance() {
		return instance;
	}

	private void getUserItens(Item[][] items) {

		userItens = (ArrayList<Item>) twoDArrayToList(items);
		userItens = clean(userItens);
	}

	private ArrayList<Item> clean(ArrayList<Item> list) {
		List<Item> nullableItens = new ArrayList<Item>();
		for (Iterator<Item> iterator = list.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getItem() == null) {
				nullableItens.add(item);
			}
		}
		list.removeAll(nullableItens);
		return list;
	}

	public void drawUI(Graphics2D g2d, BufferedImage background) {
		g2d.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
	}

	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		dbg.drawImage(UIBackground, 0, 0, UIBackground.getWidth(), UIBackground.getHeight(), null);
		dbg.setColor(Color.RED);

		if (isSelected) {
			drawIsSelected(dbg);
		} else {
			drawUserSelection(dbg);
		}

		drawChestItens(dbg);
		drawUserItens(dbg);

	}

	/**
	 * Draw the user items in chest inventory in a thread safe manner.
	 * 
	 * @param dbg
	 */
	private void drawUserItens(Graphics2D dbg) {
		int contl = 0;
		int contc = 0;
		int newPosx;
		int newPosy;
		newPosy = 242 + (contc * HEIGHT_SLOT);
		List<Item> syncUserItems = Collections.synchronizedList(userItens);
		synchronized (syncUserItems) {
			for (Item item : syncUserItems) {

				if (isSelected && !isItemSelected && !isInChest) {
					if (posXItemSelected == contc && posYtemSelected == contl) {
						itemSelected = item;
						isItemSelected = true;
					}
				}

				int itemWidth = item.getItem().getWidth();
				int itemHeight = item.getItem().getHeight();

				if (contc == 0 && contl == 0) {
					dbg.drawImage(item.getItem(), 14, 242, itemWidth, itemHeight, null);
				} else {
					newPosx = 14 + (contc * WIDTH_SLOT);
					dbg.drawImage(item.getItem(), newPosx, newPosy, itemWidth, itemHeight, null);
				}

				contc++;
				if (contc == 9) {
					contc = 0;
					contl++;
					newPosy = 242 + (contl * HEIGHT_SLOT);
				}
				if (contl == 3) {
					return;
				}

			}
		}
	}

	/**
	 * Draw the chest items in chest inventory in a thread safe manner.
	 * 
	 * @param dbg
	 */
	private void drawChestItens(Graphics2D dbg) {
		int contl = 0;
		int contc = 0;
		int newPosx;
		int newPosy;
		newPosy = 39 + (contl * HEIGHT_SLOT);
		List<Item> syncChestItems = Collections.synchronizedList(chestItemList);
		synchronized (syncChestItems) {
			for (Item item : syncChestItems) {

				if (item != null) {

					if (isSelected && !isItemSelected && isInChest) {
						if (posXItemSelected == contc && posYtemSelected == contl) {
							itemSelected = item;
							isItemSelected = true;
						}
					}

					int itemWidth = item.getItem().getWidth();
					int itemHeight = item.getItem().getHeight();

					if (contl == 0 && contc == 0) {
						dbg.drawImage(item.getItem(), 14, 39, itemWidth, itemHeight, null);
					} else {
						newPosx = 14 + (contc * WIDTH_SLOT);
						dbg.drawImage(item.getItem(), newPosx, newPosy, itemWidth, itemHeight, null);
					}

				}

				contc++;
				if (contc == 9) {
					contc = 0;
					contl++;
					newPosy = 39 + (contl * HEIGHT_SLOT);
				}
				if (contl == 3) {
					return;
				}
			}
		}

	}

	private void drawUserSelection(Graphics2D dbg) {

		// If is isInChest, draw the selected slot in chest inventory
		// otherwise draw in the user inventory.
		if (isInChest) {
			drawSelectionInChest(dbg);
		} else {
			drawSelectionInUserInventory(dbg);
		}
	}

	private void drawIsSelected(Graphics2D dbg) {
		if (isInChest) {
			drawFixedSelectionInChestInventory(dbg);
			drawSelectionInUserInventory(dbg);
		} else {
			drawFixedSelectionInUserInventory(dbg);
			drawSelectionInChest(dbg);
		}
	}

	private void drawFixedSelectionInUserInventory(Graphics2D dbg) {
		int newPosx = 0;
		int newPosy = 0;
		if (posXItemSelected == 0 && posYtemSelected == 0) {
			dbg.drawRect(14, 240, WIDTH_SLOT - 3, HEIGHT_SLOT - 3);
		} else {
			newPosx = 14 + (posXItemSelected * WIDTH_SLOT);
			newPosy = 240 + (posYtemSelected * HEIGHT_SLOT);
			dbg.drawRect(newPosx, newPosy, WIDTH_SLOT, HEIGHT_SLOT);
		}

	}

	private void drawFixedSelectionInChestInventory(Graphics2D dbg) {
		int newPosx = 0;
		int newPosy = 0;
		if (posXItemSelected == 0 && posYtemSelected == 0) {
			dbg.drawRect(14, 39, WIDTH_SLOT - 3, HEIGHT_SLOT - 3);
		} else {
			newPosx = 14 + (posXItemSelected * WIDTH_SLOT);
			newPosy = 39 + (posYtemSelected * HEIGHT_SLOT);
			dbg.drawRect(newPosx, newPosy, WIDTH_SLOT, HEIGHT_SLOT);
		}

	}

	private void drawSelectionInUserInventory(Graphics2D dbg) {
		int newPosx;
		int newPosy;
		if (posX == 0 && posY == 0) {
			dbg.drawRect(14, 240, WIDTH_SLOT - 3, HEIGHT_SLOT - 3);
		} else {
			newPosx = 14 + (posX * WIDTH_SLOT);
			newPosy = 240 + (posY * HEIGHT_SLOT);
			dbg.drawRect(newPosx, newPosy, WIDTH_SLOT, HEIGHT_SLOT);
		}
	}

	private void drawSelectionInChest(Graphics2D dbg) {
		int newPosx;
		int newPosy;
		if (posX == 0 && posY == 0) {
			dbg.drawRect(14, 39, WIDTH_SLOT - 3, HEIGHT_SLOT - 3);
		} else {
			newPosx = 14 + (posX * WIDTH_SLOT);
			newPosy = 39 + (posY * HEIGHT_SLOT);
			dbg.drawRect(newPosx, newPosy, WIDTH_SLOT, HEIGHT_SLOT);
		}

	}

	/**
	 * Converts the given multidimensional array into a {@link List} object.
	 * 
	 * @param twoDArray
	 * @return <T> {@link List}
	 * @author Rômulo Sorato
	 * @since 1.5-alpha.
	 */

	public <T> List<T> twoDArrayToList(T[][] twoDArray) {
		List<T> list = new ArrayList<T>();
		for (T[] array : twoDArray) {
			list.addAll(Arrays.asList(array));
		}
		return list;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			if (posX >= 0) {
				posX--;
			}
			if (posX < 0) {
				posX = MAX_X - 1;
			}

		}
		if (keyCode == KeyEvent.VK_RIGHT) {

			if (posX < MAX_X) {

				posX++;

			}
			if (posX == MAX_X) {

				posX = 0;

			}

		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (posY < MAX_Y) {

				posY++;
			}
			if (posY == MAX_Y) {
				if (isInChest) {
					isInChest = false;
				} else {
					isInChest = true;
				}
				posY = 0;

			}

		}
		if (keyCode == KeyEvent.VK_UP) {

			if (posY >= 0) {
				posY--;
			}

			if (posY < 0) {
				if (isInChest) {
					isInChest = false;
				} else {
					isInChest = true;
				}
				posY = MAX_Y - 1;
			}

		}
		if (keyCode == KeyEvent.VK_SPACE) {
			cont = 0;
			posXItemSelected = posX;
			posYtemSelected = posY;

			if (isSelected) {
				isSelected = false;
				isSwapped = true;
			} else {
				if (isInChest) {
					if (!isEmptyInventory() && !isEmptyBlockChest()) {
						isSelected = true;
					}
				} else {
					if (!isEmptyInventory() && !isEmptyBlockUser()) {
						isSelected = true;
					}
				}

			}
			if (isSwapped) {
				if (isInChest) {
					swapItensfromChestToUser();
				} else {
					swapItensfromUserToChest();
				}
				isItemSelected = false;
				isSwapped = false;
			}
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			isSelected = false;
			isItemSelected = false;
			cont++;
			if (cont == 2) {
				GamePanel.canvasAtivo = CanvasGame2.instance;
				cont = 0;
			}
			CanvasGame2.LEFT = false;
			CanvasGame2.RIGHT = false;
			CanvasGame2.DOWN = false;
			CanvasGame2.UP = false;
			CanvasGame2.SPACE = false;
		}
	}

	private boolean isEmptyBlockUser() {
		int contl = 0;
		int contc = 0;

		List<Item> syncUserItems = Collections.synchronizedList(userItens);
		synchronized (syncUserItems) {
			for (Item item : syncUserItems) {

				if (posXItemSelected == contc && posYtemSelected == contl) {
					if (item != null) {
						return false;
					} else {
						return true;
					}
				}

				contc++;
				if (contc == 9) {
					contc = 0;
					contl++;
				}
			}
		}
		return true;
	}

	private boolean isEmptyBlockChest() {
		int contl = 0;
		int contc = 0;

		List<Item> syncUserItems = Collections.synchronizedList(chestItemList);
		synchronized (syncUserItems) {
			for (Item item : syncUserItems) {

				if (posXItemSelected == contc && posYtemSelected == contl) {
					if (item != null) {
						return false;
					} else {
						return true;
					}
				}

				contc++;
				if (contc == 9) {
					contc = 0;
					contl++;
				}
			}
		}
		return true;
	}

	private boolean isEmptyInventory() {
		if (isInChest) {
			if (chestItemList.size() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			boolean size = userItens.isEmpty();
			if (size) {
				return true;
			} else {
				return false;
			}
		}
	}

	private void swapItensfromChestToUser() {
		itemChest.removeItem(itemSelected);
		userItens.add(itemSelected);
	}

	private void swapItensfromUserToChest() {
		userItens.remove(itemSelected);
		itemChest.addItem(itemSelected);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			CanvasGame2.LEFT = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			CanvasGame2.RIGHT = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			CanvasGame2.DOWN = false;
		}
		if (keyCode == KeyEvent.VK_UP) {

			CanvasGame2.UP = false;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			CanvasGame2.SPACE = false;

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
