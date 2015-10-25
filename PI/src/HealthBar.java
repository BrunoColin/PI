import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HealthBar {

	private BufferedImage healthBar;
	private int x;
	private int y;
	private int width;
	private int height;
	private int xRec;
	private int yRec;
	private int widthRec;
	private int heightRec;
	private final int MAX_WIDTH_HP_BAR;

	private enum Condition {
		FINE, MEDIUM, DANGER
	}

	public HealthBar(int x, int y) {
		healthBar = LoadResources.healthBar;
		this.x = x;
		this.y = y;
		this.width = healthBar.getWidth();
		this.height = healthBar.getHeight();

		this.xRec = this.x + 5;
		this.yRec = this.y + 5;
		this.MAX_WIDTH_HP_BAR = this.width - 11;
		this.widthRec = this.MAX_WIDTH_HP_BAR;
		this.heightRec = this.height - 10;
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(healthBar, x, y, this.width, this.height, null);

		g2d.setColor(getColorCondition());

		g2d.fillRect(xRec, yRec, getCurrentWith(), heightRec);
	}

	private int getCurrentWith() {

		return widthRec = (CanvasGame2.instance.player.getHp() * MAX_WIDTH_HP_BAR)
				/ CanvasGame2.instance.player.getMAX_HEALTH();
	}

	private Color getColorCondition() {
		if (widthRec >= 60) {
			// Condition Fine
			return Color.GREEN;
		} else if (widthRec >= 40 && widthRec < 65) {
			// Condition Normal
			return Color.YELLOW;
		}
		// Condition Danger
		return Color.RED;
	}

	public Condition getCondition() {
		if (CanvasGame2.player.getHp() >= 75) {
			// Condition Fine
			return Condition.FINE;
		} else if (CanvasGame2.instance.player.getHp() >= 50
				&& CanvasGame2.instance.player.getHp() < 75) {
			// Condition Normal
			return Condition.MEDIUM;
		}
		return Condition.DANGER;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return height;
	}

	public void setHeigth(int height) {
		this.height = height;
	}
}
