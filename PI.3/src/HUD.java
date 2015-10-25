import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class HUD extends UI {

	HealthBar healthBar;

	public HUD() {
		healthBar = new HealthBar(12, 14);

	}

	@Override
	public void SimulaSe(long diftime) {

	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {

		dbg.setColor(Color.ORANGE);
		dbg.drawString("HP: " + CanvasGame2.player.getHp(), 26, 14);
		dbg.drawString("numero de flechas : " + CanvasGame2.player.numFlechas, 100, 14);
		dbg.drawString("X : " + CanvasGame2.player.getGold(), 275, 18);
		healthBar.draw(dbg);
		dbg.drawImage(LoadResources.gold, 250, 0, null);

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
