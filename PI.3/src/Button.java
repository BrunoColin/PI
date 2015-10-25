import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Romulo
 *
 */
public class Button {

	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage buttonImage;
	private String text;
	private boolean isclicked;

	public boolean isIsclicked() {
		return isclicked;
	}

	public void setIsclicked(boolean isclicked) {
		this.isclicked = isclicked;
	}

	private static final int THICKNESS = 5;

	public Button(BufferedImage buttonImage, String text, int x, int y) {
		this.buttonImage = buttonImage;
		this.setText(text);
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates an empty button.
	 * 
	 * @param buttonImage
	 * @param x
	 * @param y
	 */
	public Button(BufferedImage buttonImage, int x, int y) {
		this.buttonImage = buttonImage;
		this.x = x;
		this.y = y;
	}

	public Button(BufferedImage buttonImage, String text, int x, int y, int width, int height) {
		this.buttonImage = buttonImage;
		this.setText(text);
		this.x = x;
		this.y = y;
	}

	public Button(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics2D graphics2d) {
		graphics2d.drawImage(buttonImage, x, y, null);
		Font font = new Font("arial", Font.BOLD, 30);
		graphics2d.setFont(font);
		graphics2d.setColor(Color.WHITE);
		int centerHeight = buttonImage.getHeight() / 2 - 5;
		int centerWight = buttonImage.getWidth() / 2 - 5;
		graphics2d.drawString(this.text, centerWight, centerHeight);
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getButtonImage() {
		return buttonImage;
	}

	public void setButtonImage(BufferedImage buttonImage) {
		this.buttonImage = buttonImage;
	}

	public void draw(Graphics2D graphics2d, int x, int y, int width, int height) {
		graphics2d.drawImage(buttonImage, x, y, width, height, null);
	}

	public void drawSelectedButton(Graphics2D graphics2d) {
		graphics2d.setColor(Color.BLUE);
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.drawRoundRect(this.x, this.y, this.width, this.height, 20, 20);
		Color selectedButtonCollor = new Color(0f, 0.2f, 1f, 0.5f);
		graphics2d.setColor(selectedButtonCollor);
		graphics2d.setStroke(new BasicStroke(THICKNESS, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		graphics2d.fillRoundRect(this.x + 2, this.y + 2, this.width - 2, this.height - 2, 20, 20);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
