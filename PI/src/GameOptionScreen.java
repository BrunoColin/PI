import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameOptionScreen extends UI implements Background {

	private final int X_SCREENSIZE_BUTTON = GamePanel.PWIDTH / 3;
	private final int Y_SCREENSIZE_BUTTON = GamePanel.PHEIGHT / 5;
	private final int X_GRAPHICS_BUTTON = GamePanel.PWIDTH / 3 + 10;
	private final int Y_GRAPHICS_BUTTON = GamePanel.PHEIGHT / 5 + 43;
	private final int X_CONTROLS_BUTTON = GamePanel.PWIDTH / 3 + 15;
	private final int Y_CONTROLS_BUTTON = GamePanel.PHEIGHT / 5 + 85;
	private final int X_SOUND_BUTTON = GamePanel.PWIDTH / 3 + 33;
	private final int Y_SOUND_BUTTON = GamePanel.PHEIGHT / 5 + 125;
	private final int X_RETURN_BUTTON = GamePanel.PWIDTH / 3 + 5;
	private final int Y_RETURN_BUTTON = GamePanel.PHEIGHT / 5 + 165;
	private final int WIDTH = 170;
	private final int HEIGHT = 43;
	private Button sound;
	private Button screenSize;
	private Button graphics;
	private Button controls;
	private Button back;
	private boolean UP;
	private boolean DOWN;
	private boolean ENTER;

	public GameOptionScreen() {
		screenSize = new Button(X_SCREENSIZE_BUTTON, Y_SCREENSIZE_BUTTON, WIDTH, HEIGHT - 5);
		graphics = new Button(X_GRAPHICS_BUTTON, Y_GRAPHICS_BUTTON, WIDTH - 20, HEIGHT - 10);
		controls = new Button(X_CONTROLS_BUTTON, Y_CONTROLS_BUTTON, WIDTH - 32, HEIGHT - 9);
		sound = new Button(X_SOUND_BUTTON, Y_SOUND_BUTTON, WIDTH - 65, HEIGHT - 10);

		back = new Button(X_RETURN_BUTTON, Y_RETURN_BUTTON, WIDTH - 5, HEIGHT - 8);
	}

	private enum GameOptionsButtons {
		SOUND, SCREENSIZE, GRAPHICS, CONTROLS, BACK;
	}

	private GameOptionsButtons selectedButton = GameOptionsButtons.SCREENSIZE;

	@Override
	public void drawBackground(Graphics2D g2d, BufferedImage returnground) {
		g2d.drawImage(returnground, 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);

	}

	public GameOptionsButtons getselectedButton() {
		return selectedButton;
	}

	public void setselectedButton(GameOptionsButtons selectedButton) {
		this.selectedButton = selectedButton;
	}

	@Override
	public void SimulaSe(long diftime) {
		if (DOWN) {
			if (selectedButton == GameOptionsButtons.SCREENSIZE) {
				selectedButton = GameOptionsButtons.GRAPHICS;
			} else if (selectedButton == GameOptionsButtons.GRAPHICS) {
				selectedButton = GameOptionsButtons.CONTROLS;
			} else if (selectedButton == GameOptionsButtons.CONTROLS) {
				selectedButton = GameOptionsButtons.SOUND;
			} else if (selectedButton == GameOptionsButtons.SOUND) {
				selectedButton = GameOptionsButtons.BACK;
			} else if (selectedButton == GameOptionsButtons.BACK) {
				selectedButton = GameOptionsButtons.SCREENSIZE;
			}
			DOWN = false;
		} else if (UP) {
			if (selectedButton == GameOptionsButtons.SCREENSIZE) {
				selectedButton = GameOptionsButtons.BACK;
			} else if (selectedButton == GameOptionsButtons.BACK) {
				selectedButton = GameOptionsButtons.SOUND;
			} else if (selectedButton == GameOptionsButtons.SOUND) {
				selectedButton = GameOptionsButtons.CONTROLS;
			} else if (selectedButton == GameOptionsButtons.CONTROLS) {
				selectedButton = GameOptionsButtons.GRAPHICS;
			} else if (selectedButton == GameOptionsButtons.GRAPHICS) {
				selectedButton = GameOptionsButtons.SCREENSIZE;
			}
			UP = false;
		}
		if (ENTER) {
			if (selectedButton == GameOptionsButtons.SCREENSIZE) {
				GamePanel.instance.changeSizeScreen(800, 600);
			}
			if (selectedButton == GameOptionsButtons.GRAPHICS) {

			}
			if (selectedButton == GameOptionsButtons.SOUND) {
				CanvasGame2.instance.setGameOver(false);
				GamePanel.instance.resetGame();
			}

			if (selectedButton == GameOptionsButtons.CONTROLS) {
				GamePanel.instance.clearBackground();
				GamePanel.canvasAtivo = new GameOptionScreen();
			}
			if (selectedButton == GameOptionsButtons.BACK) {
				GamePanel.instance.clearBackground();
				GamePanel.canvasAtivo = new MainMenu();

			}
			ENTER = false;
		}

	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		drawBackground(dbg, LoadResources.gameOptionsScreen);
		drawGameOptionButtons(selectedButton, dbg);
		drawButtonLetters(dbg);

	}

	public void drawButtonLetters(Graphics2D dbg) {
		dbg.drawImage(LoadResources.gameOptionsLetter, 160, 30, 260, 40, null);
		Font font = new Font("arial", Font.BOLD, 30);
		dbg.setFont(font);
		dbg.setColor(Color.WHITE);

		dbg.drawString("Screen Size", GamePanel.PWIDTH / 3, GamePanel.PHEIGHT / 5 + 30);
		dbg.drawString("Graphics", GamePanel.PWIDTH / 3 + 20, GamePanel.PHEIGHT / 5 + 70);
		dbg.drawString("Controls", GamePanel.PWIDTH / 4 + 75, GamePanel.PHEIGHT / 3 + 60);
		dbg.drawString("Sound", GamePanel.PWIDTH / 5 + 120, GamePanel.PHEIGHT / 3 + 100);
		dbg.drawString("Main Menu", GamePanel.PWIDTH / 4 + 63, GamePanel.PHEIGHT / 2 + 74);
	}

	public void drawGameOptionButtons(GameOptionsButtons selectedButton, Graphics2D dbg) {
		if (selectedButton == GameOptionsButtons.SCREENSIZE) {
			screenSize.drawSelectedButton(dbg);
		} else {
			screenSize.draw(dbg);
		}
		if (selectedButton == GameOptionsButtons.GRAPHICS) {
			graphics.drawSelectedButton(dbg);
		} else {
			graphics.draw(dbg);
		}
		if (selectedButton == GameOptionsButtons.CONTROLS) {
			controls.drawSelectedButton(dbg);
		} else {
			controls.draw(dbg);
		}
		if (selectedButton == GameOptionsButtons.SOUND) {
			sound.drawSelectedButton(dbg);
		} else {
			sound.draw(dbg);
		}
		if (selectedButton == GameOptionsButtons.BACK) {
			back.drawSelectedButton(dbg);
		} else {
			back.draw(dbg);
		}
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
	public void drawUI(Graphics2D g2d, BufferedImage background) {
		// TODO Auto-generated method stub

	}

}
