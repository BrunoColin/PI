import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Class responsible for load in memory all resources in the game and stay ready
 * to use.
 * 
 * @author Romulo
 * @since 1.0
 * 
 */
public class LoadResources extends Resources {

	public LoadResources() {
		loadItensRes();
		loadGUIComponents();
		loadSpriteSheets();
		loadBackgrounds();
	}

	private void loadBackgrounds() {
		gameOverScreen = loadImage(GameOverScreen);
		mainMenu = loadImage(MainMenu);
		gameOptionsScreen = loadImage(GameOptionScreen);
		uiItemChest = loadImage(UIitemChest);
		itemShopRoom = loadImage(ItemShopRoom);
	}

	private void loadSpriteSheets() {
		spriteSheetCharacter = loadImage(SpriteSheetCharacter);
		spriteSheetItemChest = loadImage(SpriteSheetItemChest);
		portaH1 = loadImage("portaH1.png");
		portaH2 = loadImage("portaH2.png");
		portaV1 = loadImage("portaV1.png");
		portaV2 = loadImage("portaV2.png");
		inimigo1 = loadImage("enemy1.png");
		inimigo2 = loadImage("enemy2.png");
		inimigo3 = loadImage("enemy3.png");
	}

	private void loadGUIComponents() {
		healthBar = loadImage(HealthBar);
		mainMenuLetter = loadImage(MainMenuLetter);
		fundoSelecao = loadImage("fundoSelecao.png");
		botaoSel = loadImage("botaoSel.png");
		botao = loadImage(SelectedSlotItemChest);
		selectedSlot = loadImage(SlotSelected);
		selectedSlotItemChest = loadImage(SelectedSlotItemChest);
		gold = loadImage(Gold);

	}

	public static void loadItensRes() {
		blood = loadImage(Blood);
		scroll = loadImage(SpriteSheetItens).getSubimage(10 * 34, 17 * 34, 34, 34);
		arrowT = loadImage("arrow.png");
		flameArrow = loadImage(SpriteSheetItens);
		dagger = loadImage(SpriteSheetItens).getSubimage(5 * 34, 6 * 34, 34, 34);
		bigPotion = loadImage(SpriteSheetItens).getSubimage(5 * 34, 2 * 34, 34, 34);
		potion = loadImage(SpriteSheetItens).getSubimage(12 * 34, 2 * 34, 34, 34);
		smallPotion = loadImage(SpriteSheetItens).getSubimage(12 * 34, 3 * 34, 34, 34);
		arrow = loadImage(SpriteSheetItens).getSubimage(8 * 34, 7 * 34, 34, 34);
		flamingArrow = loadImage(SpriteSheetItens).getSubimage(0 * 34, 27 * 34, 34, 34);
		bow = loadImage(SpriteSheetItens).getSubimage(0 * 34, 11 * 34, 34, 34);
		sword = loadImage(SpriteSheetItens).getSubimage(11 * 34, 5 * 34, 34, 34);
		bread = loadImage(SpriteSheetItens).getSubimage(7 * 34, 1 * 34, 34, 34);
		strawberry = loadImage(SpriteSheetItens).getSubimage(8 * 34, 0 * 34, 34, 34);
		meat = loadImage(SpriteSheetItens).getSubimage(13 * 34, 1 * 34, 34, 34);
		key = loadImage(SpriteSheetItens).getSubimage(7 * 34, 16 * 34, 34, 34);
		torch = loadImage(SpriteSheetItens).getSubimage(5 * 34, 16 * 34, 34, 34);
		bag = loadImage("bag.png");
	}

	public static BufferedImage loadImage(String source) {
		BufferedImage image = null;
		try {
			System.out.println(GamePanel.instance.getClass().getResource(source));
			BufferedImage tmp = ImageIO.read(GamePanel.instance.getClass().getResource(source));
			image = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(tmp, 0, 0, null);
			tmp = null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			e.fillInStackTrace();
		}
		return image;
	}

	public static void freeResources() {
		health.flush();
		blood.flush();
		ammo.flush();
		spriteSheetCharacter.flush();
		spriteSheetHunterWalk.flush();
		spriteSheetItemChest.flush();
		food.flush();
		gameOverScreen.flush();
		healthBar.flush();
		mainMenu.flush();
		mainMenuLetter.flush();
		slot.flush();
		zombie1Down.flush();
		zombie1Left.flush();
		zombie1Right.flush();
		zombie1Up.flush();
		scroll.flush();
		// zombie2.flush();
		// zombie3.flush();

	}
}
