import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameOverScreen extends UI implements Background {

	private final int X_CONTINUE_BUTTON = GamePanel.PWIDTH / 2;
	private final int Y_CONTINUE_BUTTON = GamePanel.PHEIGHT / 2;
	private final int X_QUIT_BUTTON = GamePanel.PWIDTH / 2;
	private final int Y_QUIT_BUTTON = GamePanel.PHEIGHT / 2 + 60;
	private final int WIDTH = 215;
	private final int HEIGHT = 60;
	private Button continueButton;
	private Button quitButton;

	private enum GameOverOptions {
		CONTINUE, QUIT;
	}

	private GameOverOptions selectedButton = GameOverOptions.CONTINUE;

	public GameOverScreen() {
		continueButton = new Button(X_CONTINUE_BUTTON, Y_CONTINUE_BUTTON, WIDTH, HEIGHT);
		quitButton = new Button(X_QUIT_BUTTON, Y_QUIT_BUTTON, WIDTH, HEIGHT);
	}

	public void drawBackground(Graphics2D g2d, BufferedImage background) {
		g2d.drawImage(background, 0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT, null);
	}

	public GameOverOptions getselectedButton() {
		return selectedButton;
	}

	public void setselectedButton(GameOverOptions selectedButton) {
		this.selectedButton = selectedButton;
	}

	@Override
	public void SimulaSe(long diftime) {
		if (CanvasGame2.DOWN) {
			if (selectedButton == GameOverOptions.CONTINUE) {
				selectedButton = GameOverOptions.QUIT;
			} else {
				selectedButton = GameOverOptions.CONTINUE;
			}
			CanvasGame2.DOWN = false;
		} else if (CanvasGame2.UP) {
			if (selectedButton == GameOverOptions.CONTINUE) {
				selectedButton = GameOverOptions.QUIT;
			} else {
				selectedButton = GameOverOptions.CONTINUE;
			}
		}
		if (CanvasGame2.ENTER) {
			if (selectedButton == GameOverOptions.CONTINUE) {
				CanvasGame2.instance.setGameOver(false);
				GamePanel.instance.resetGame();

			}
		}
		CanvasGame2.UP = false;

	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		drawBackground(dbg, LoadResources.gameOverScreen);

		if (selectedButton == GameOverOptions.CONTINUE) {
			continueButton.drawSelectedButton(dbg);
			quitButton.draw(dbg);
		} else {
			quitButton.drawSelectedButton(dbg);
			continueButton.draw(dbg);
		}

		Font font = new Font("arial", Font.BOLD, 50);
		dbg.setFont(font);
		dbg.setColor(Color.WHITE);
		dbg.drawString("Continue", GamePanel.PWIDTH / 2, GamePanel.PHEIGHT / 2 + 50);
		dbg.drawString("Quit", GamePanel.PWIDTH / 2, GamePanel.PHEIGHT / 2 + 100);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
