import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author Rômulo
 *
 */
class ConfirmationDialogUI implements GameDialogUI {
	private Button yesButton;
	private Button noButton;
	private BufferedImage dialogBox;
	private String confirmationMessage;

	/**
	 * Creates a Confirmation dialog with the specified Image
	 * 
	 * @param graphicsContext
	 *            - The game graphics context
	 * @param textBox
	 *            - The dialog image
	 * @param withButtons
	 *            - If is true draw with two default buttons
	 */
	public ConfirmationDialogUI(Graphics2D graphicsContext, BufferedImage textBox, boolean withButtons) {
		drawDialogBox(graphicsContext, textBox);
	}

	public ConfirmationDialogUI(Graphics2D graphicsContext, BufferedImage dialogBox, String confirmationMessage) {
		this.dialogBox = dialogBox;
		this.confirmationMessage = confirmationMessage;
	}

	public ConfirmationDialogUI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub

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
	public void drawDialogBox(Graphics2D graphicsContext, BufferedImage textBox) {
		// TODO Auto-generated method stub

	}
}
