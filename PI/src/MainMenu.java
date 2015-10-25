import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MainMenu extends UI implements Background {

	private final int WIDTH = 150;
	private final int HEIGHT = 30;
	private final int X_CONTINUE_BUTTON = GamePanel.PWIDTH / 3 + 32;
	private final int Y_CONTINUE_BUTTON = GamePanel.PHEIGHT / 4 + 17;
	private final int X_NEWGAME_BUTTON = GamePanel.PWIDTH / 3 + 30;
	private final int Y_NEWGAME_BUTTON = GamePanel.PHEIGHT / 4 + 50;
	private final int X_OPTION_BUTTON = GamePanel.PWIDTH / 3 + 41;
	private final int Y_OPTION_BUTTON = GamePanel.PHEIGHT / 3 + 85;
	private final int X_RANK_BUTTON = GamePanel.PWIDTH / 2 - 45;
	private final int Y_RANK_BUTTON = GamePanel.PHEIGHT / 3 + 118;
	private final int X_QUIT_BUTTON = GamePanel.PWIDTH / 2 - 45;
	private final int Y_QUIT_BUTTON = GamePanel.PHEIGHT / 3 + 150;
	private Button continueButton;
	private Button newGameButton;
	private Button optionButton;
	private Button rankButton;
	private Button quitButton;

	private enum MainMenuOptions {
		CONTINUE, NEWGAME, OPTIONS, RANK, QUIT;
	}

	private MainMenuOptions selectedButton = MainMenuOptions.CONTINUE;
	private boolean UP;
	private boolean DOWN;
	private boolean ENTER;

	public MainMenu() {

		continueButton = new Button(X_CONTINUE_BUTTON, Y_CONTINUE_BUTTON, WIDTH - 50, HEIGHT);
		newGameButton = new Button(X_NEWGAME_BUTTON, Y_NEWGAME_BUTTON, WIDTH - 40, HEIGHT);
		optionButton = new Button(X_OPTION_BUTTON, Y_OPTION_BUTTON, WIDTH - 67, HEIGHT);
		rankButton = new Button(X_RANK_BUTTON, Y_RANK_BUTTON, WIDTH - 90, HEIGHT);
		quitButton = new Button(X_QUIT_BUTTON, Y_QUIT_BUTTON, WIDTH - 96, HEIGHT);
	}

	public void drawButtonLetters(Graphics2D dbg) {
		dbg.drawImage(LoadResources.mainMenuLetter, 160, 40, 260, 40, null);

		Font font = new Font("arial", Font.BOLD, 15);
		dbg.setFont(font);
		dbg.setColor(Color.WHITE);
		dbg.drawString("Continue", GamePanel.PWIDTH / 3 + 40, GamePanel.PHEIGHT / 4 + 40);
		dbg.drawString("New Game", GamePanel.PWIDTH / 3 + 35, GamePanel.PHEIGHT / 3 + 40);
		dbg.drawString("Options", GamePanel.PWIDTH / 4 + 95, GamePanel.PHEIGHT / 2 + 40);
		dbg.drawString("Rank", GamePanel.PWIDTH / 4 + 110, GamePanel.PHEIGHT / 2 + 73);
		dbg.drawString("Quit", GamePanel.PWIDTH / 4 + 112, GamePanel.PHEIGHT / 2 + 105);
	}

	public void drawMainMenuButtons(MainMenuOptions selectedButton, Graphics2D dbg) {
		if (selectedButton == MainMenuOptions.CONTINUE) {
			continueButton.drawSelectedButton(dbg);
		} else {
			continueButton.draw(dbg);
		}
		if (selectedButton == MainMenuOptions.NEWGAME) {
			newGameButton.drawSelectedButton(dbg);
		} else {
			newGameButton.draw(dbg);
		}
		if (selectedButton == MainMenuOptions.OPTIONS) {
			optionButton.drawSelectedButton(dbg);
		} else {
			optionButton.draw(dbg);
		}
		if (selectedButton == MainMenuOptions.QUIT) {
			quitButton.drawSelectedButton(dbg);
		} else {
			quitButton.draw(dbg);
		}
		if (selectedButton == MainMenuOptions.RANK) {
			rankButton.drawSelectedButton(dbg);
		} else {
			rankButton.draw(dbg);
		}
	}

	@Override
	public void drawUI(Graphics2D g2d, BufferedImage background) {

	}

	@Override
	public void SimulaSe(long diftime) {
		if (DOWN) {
			if (selectedButton == MainMenuOptions.CONTINUE) {
				selectedButton = MainMenuOptions.NEWGAME;
			} else if (selectedButton == MainMenuOptions.NEWGAME) {
				selectedButton = MainMenuOptions.OPTIONS;
			} else if (selectedButton == MainMenuOptions.OPTIONS) {
				selectedButton = MainMenuOptions.RANK;
			} else if (selectedButton == MainMenuOptions.RANK) {
				selectedButton = MainMenuOptions.QUIT;
			} else if (selectedButton == MainMenuOptions.QUIT) {
				selectedButton = MainMenuOptions.CONTINUE;
			}
			DOWN = false;
		} else if (UP)

		{
			if (selectedButton == MainMenuOptions.CONTINUE) {
				selectedButton = MainMenuOptions.QUIT;
			} else if (selectedButton == MainMenuOptions.QUIT) {
				selectedButton = MainMenuOptions.RANK;
			} else if (selectedButton == MainMenuOptions.RANK) {
				selectedButton = MainMenuOptions.OPTIONS;
			} else if (selectedButton == MainMenuOptions.OPTIONS) {
				selectedButton = MainMenuOptions.NEWGAME;
			} else if (selectedButton == MainMenuOptions.NEWGAME) {
				selectedButton = MainMenuOptions.CONTINUE;
			}
			UP = false;
		}
		if (ENTER)

		{
			if (selectedButton == MainMenuOptions.CONTINUE) {
				GamePanel.canvasAtivo = new CanvasGame2(true);
			}
			if (selectedButton == MainMenuOptions.NEWGAME) {
				GamePanel.instance.clearBackground();
				GamePanel.canvasAtivo = new CanvasGame2(false);
			}
			if (selectedButton == MainMenuOptions.OPTIONS) {
				GamePanel.instance.clearBackground();
				GamePanel.canvasAtivo = new GameOptionScreen();
			}
			if (selectedButton == MainMenuOptions.RANK) {
				CanvasGame2.instance.setGameOver(false);
				GamePanel.instance.resetGame();

			}
			if (selectedButton == MainMenuOptions.QUIT) {
				// Free resources and shut down the game
				LoadResources.freeResources();
				GamePanel.canvasAtivo = null;
				GamePanel.instance.getDbg().dispose();
				GamePanel.instance = null;
				System.exit(0);

			}
		}
		ENTER = false;

	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		drawBackground(dbg, LoadResources.mainMenu);
		drawMainMenuButtons(selectedButton, dbg);
		drawButtonLetters(dbg);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			UP = true;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			DOWN = true;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			ENTER = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			UP = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			DOWN = false;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			ENTER = false;
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

	@Override
	public void drawBackground(Graphics2D g2d, BufferedImage background) {
		g2d.drawImage(background, 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);
	}

}
