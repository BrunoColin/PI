import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

abstract class UI implements myCanvas {

	private final float ALPHA = 0.6f;

	// private void drawReadyUI() {
	// Graphics g = game.getGraphics();
	//
	// g.drawARGB(155, 0, 0, 0);
	// g.drawString("Tap to Start.", 400, 240, paint);
	//
	// }
	public abstract void drawUI(Graphics2D g2d, BufferedImage background);

	protected void drawPausedUI(Graphics g) {
		// Darken the entire screen so you can display the Paused screen or the
		// inventory.
		Color c = new Color(0, 0, 0, ALPHA);
		g.setColor(c);
		g.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
	}

	public Object inventoryOption() {
		String[] message = new String[2];
		message[0] = "Use";
		message[1] = "Discard";
		Object selectedValue = JOptionPane.showInputDialog(GamePanel.instance, "Choose option", "Input",
				JOptionPane.INFORMATION_MESSAGE, null, message, message[0]);
		return selectedValue;
	}
}
