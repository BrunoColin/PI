import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CanvasGame2 implements myCanvas {

	public static CanvasGame2 instance = null;

	static Random rnd = new Random();

	public static boolean LEFT, RIGHT, UP, DOWN, SPACE, FIRE, OVER, ENTER, A, E, I, RUN, MELEE;

	boolean start = false;

	boolean sel;
	boolean pause = false;
	private boolean gameOver = false;

	int MouseX, MouseY;

	// All items from this room
	ArrayList<Item> roomItens = new ArrayList<Item>();

	static ArrayList<Sala> salas = new ArrayList<Sala>();
	static int controlaSalas = 0;
	static Personagem player;
	HUD hud;
	LoadResources resources;
	private static ItemChest itemChest;
	private ShopRoom shopRoom;
	MiniMapa miniMapa;

	public ItemChest getItemChest() {
		return itemChest;
	}

	public void setItemChest(ItemChest itemChest) {
		CanvasGame2.itemChest = itemChest;
	}

	Sala s;

	CanvasInventario canvasInventario;

	// screen polygon
	int xScreen[] = { 0, 0, GamePanel.PWIDTH, GamePanel.PWIDTH };
	int yScreen[] = { 0, GamePanel.PHEIGHT, GamePanel.PHEIGHT, 0 };

	Polygon tela = new Polygon(xScreen, yScreen, 4);

	int sizeTile = 16;

	public CanvasGame2(boolean iscontinue) {

		instance = this;
		resources = new LoadResources();
		hud = new HUD();

		if (iscontinue) {
			continueGame();
		} else {
			player = new Personagem(82, 180, LoadResources.spriteSheetCharacter, 30, 30, 200);
		}
		roomItens = new ArrayList<Item>();
		roomItens.add(new Item(LoadResources.meat, 0, 0, 5, "Meat", -1));
		roomItens.add(new Item(LoadResources.flamingArrow, 0, 0, 2, "Flaming arrow", -1));
		roomItens.add(new Item(LoadResources.smallPotion, 0, 0, 4, "Small Potion", -1));
		roomItens.add(new Item(LoadResources.torch, 0, 0, 3, "Torch", -1));
		roomItens.add(new Item(LoadResources.scroll, 0, 0, 10, "Scroll", -1));
		//roomItens.add(new Item(LoadResources.key, 0, 0, 5, "Key", 1));
		roomItens.add(new Item(LoadResources.dagger, 0, 0, 1, "Dagger", -1));
		canvasInventario = new CanvasInventario(7, 5, roomItens);
		s = GamePanel.leSala("testt.txt");
		s.visitada = true;
		salas.add(s);
		s = GamePanel.leSala("testt2.txt");
		 salas.add(s);
		 s = GamePanel.leSala("testt3.txt");
		 salas.add(s);
		// s = GamePanel.leSala("test4.txt");
		miniMapa  = new MiniMapa(salas.get(controlaSalas));
		itemChest = new ItemChest(LoadResources.spriteSheetItemChest, 70, 20, 4);
		shopRoom = new ShopRoom(LoadResources.itemShopRoom);
		
	}

	@Override
	public void SimulaSe(long diftime) {
		salas.get(controlaSalas).SimulaSe(diftime);
		salas.get(controlaSalas).setPosition((GamePanel.PWIDTH / 2) - (int) player.x,
				(GamePanel.PHEIGHT / 2 - (int) player.y));
		hud.SimulaSe(diftime);
		miniMapa.SimulaSe(diftime);
	}

	@Override
	public void DesenhaSe(Graphics2D graphicsContext) {
		graphicsContext.setColor(Color.white);
		graphicsContext.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		salas.get(controlaSalas).DesenhaSe(graphicsContext);
		hud.DesenhaSe(graphicsContext);
		miniMapa.DesenhaSe(graphicsContext);
		//shopRoom.drawShopRoom(graphicsContext);

	}

	public void continueGame() {
		// 1 step:To open the file
		File file = new File("saveFile.txt");
		List<String> date = new ArrayList<String>();
		int hp;
		float x;
		float y;
		try {
			date.addAll(Files.readAllLines(file.toPath()));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		hp = Integer.parseInt(date.get(0));
		x = Float.parseFloat(date.get(1));
		y = Float.parseFloat(date.get(2));

		player = new Personagem(x, y, LoadResources.spriteSheetCharacter, 35, 34, 50);
		player.setHp(hp);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_A) {
			LEFT = true;
		}
		if (keyCode == KeyEvent.VK_D) {
			RIGHT = true;
		}
		if (keyCode == KeyEvent.VK_W) {
			UP = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			DOWN = true;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			SPACE = true;
		}
		if (keyCode == KeyEvent.VK_E) {
			E = true;
		}
		if (keyCode == KeyEvent.VK_I) {
			GamePanel.canvasAtivo = CanvasInventario.instance;
		}
		if (keyCode == KeyEvent.VK_A) {
			A = true;
		}
		if (keyCode == KeyEvent.VK_F) {
			MELEE = true;
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			RUN = true;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			ENTER = true;
			if (WorldMap.instance == null) {
				GamePanel.canvasAtivo = new WorldMap(salas);
			} else {
				GamePanel.canvasAtivo = WorldMap.instance;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_A) {
			LEFT = false;
		}
		if (keyCode == KeyEvent.VK_D) {
			RIGHT = false;
		}
		if (keyCode == KeyEvent.VK_W) {
			UP = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			DOWN = false;
		}
		if (keyCode == KeyEvent.VK_E) {
			E = false;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			SPACE = false;
		}
		if (keyCode == KeyEvent.VK_A) {
			A = false;
		}
		if (keyCode == KeyEvent.VK_F) {
			MELEE = false;
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			RUN = false;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			ENTER = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// FIRE = false;
		sel = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		sel = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	public HUD getHud() {
		return hud;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public ShopRoom getShopRoom() {
		return shopRoom;
	}

	public void setShopRoom(ShopRoom shopRoom) {
		this.shopRoom = shopRoom;
	}

}
