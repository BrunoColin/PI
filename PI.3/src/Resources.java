import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * An abstract class that contain all the path and images to resources in the
 * game.
 * 
 * @author Romulo
 * @since 1.0
 */
@SuppressWarnings("all")
public abstract class Resources {

	// Sprite Sheets
	protected static String SpriteSheetCharacter = "/Characters/vladimir.png";
	protected static String SpriteSheetHunterWalk = "/Characters/Enemies/hunterWalk.png";
	protected static String SpriteSheetItens = "/itens/itensSSH.png";
	protected static String SpriteSheetItemChest = "/itens/itemChest.png";

	// Itens
	protected static String Ammo = "ammo.png";
	protected static String FOOD = "food.png";

	protected static String Zombie1Up = "zombie1Up.png";
	protected static String Zombie1Down = "zombie1Down.png";
	protected static String Zombie1Right = "zombie1Right.png";
	protected static String Zombie1Left = "zombie1Left.png";
	protected static String Zombie2 = "zombie2.png";
	protected static String Zombie3 = "zombie3.png";
	protected static String Health = "health.png";
	protected static String Blood = "blood.png";

	// GUI Components
	protected static final String Slot = "/UserInterface/slot.png";
	protected static final String HealthBar = "/UserInterface/HealthBar.jpg";
	protected static final String Gold = "/UserInterface/gold_bar.png";
	protected static final String MainMenuLetter = "/UserInterface/MainMenuLetter.png";
	protected static final String GameOptionsLetter = "/UserInterface/GameOptionsLetter.png";
	protected static final String SlotSelected = "itemSel.png";
	protected static final String SelectedSlotItemChest = "/UserInterface/UI_ItemChest/botao.png";

	// Backgrounds
	protected static String GameOverScreen = "/Backgrounds/GameOverScreen.jpg";
	protected static String MainMenu = "/Backgrounds/MainMenu.jpg";
	protected static String GameOptionScreen = "/Backgrounds/GameOptionsScreen.jpg";
	protected static String UIitemChest = "/Backgrounds/UI_ItemChest.png";
	protected static String ItemShopRoom = "/Backgrounds/ShopRoom.png";

	protected static BufferedImage health;
	protected static BufferedImage blood;
	protected static BufferedImage spriteSheetCharacter;
	protected static BufferedImage spriteSheetItemChest;
	protected static BufferedImage spriteSheetHunterWalk;
	protected static BufferedImage itemShopRoom;
	protected static BufferedImage zombie3;
	protected static BufferedImage zombie2;
	protected static BufferedImage zombie1Up;
	protected static BufferedImage zombie1Right;
	protected static BufferedImage zombie1Left;
	protected static BufferedImage zombie1Down;
	protected static BufferedImage crossBow;
	protected static BufferedImage dagger;
	protected static BufferedImage arrowT;
	protected static BufferedImage flameArrow;
	protected static BufferedImage greenPill;
	protected static BufferedImage libraryKey;
	protected static BufferedImage slot;
	protected static BufferedImage ammo;
	protected static BufferedImage food;
	protected static BufferedImage healthBar;
	protected static BufferedImage gold;
	protected static BufferedImage gameOverScreen;
	protected static BufferedImage gameOptionsScreen;
	protected static BufferedImage uiItemChest;
	protected static BufferedImage mainMenu;
	protected static BufferedImage mainMenuLetter;
	protected static BufferedImage gameOptionsLetter;
	protected static BufferedImage portaH1;
	protected static BufferedImage portaH2;
	protected static BufferedImage portaV1;
	protected static BufferedImage portaV2;
	protected static BufferedImage fundoSelecao;
	protected static BufferedImage botaoSel;
	protected static BufferedImage botao;
	protected static BufferedImage selectedSlot;
	protected static BufferedImage selectedSlotItemChest;
	protected static BufferedImage inimigo1;
	protected static BufferedImage inimigo2;
	protected static BufferedImage inimigo3;

	// itens
	protected static BufferedImage scroll;
	protected static BufferedImage bigPotion;
	protected static BufferedImage potion;
	protected static BufferedImage smallPotion;
	protected static BufferedImage arrow;
	protected static BufferedImage flamingArrow;
	protected static BufferedImage bow;
	protected static BufferedImage sword;
	protected static BufferedImage bread;
	protected static BufferedImage meat;
	protected static BufferedImage strawberry;
	protected static BufferedImage key;
	protected static BufferedImage torch;
	protected static BufferedImage bag;

}
