
import java.awt.image.BufferedImage;

public abstract class Sprite {
	private BufferedImage image = null;
	private int x;
	private int y;
	private int imageHeigth;
	private int imageWidth;


	public void drawSprite() {
		image.getGraphics().drawImage(image, 0, 0, null);
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

	public int getImageHeigth() {
		return imageHeigth;
	}

	public void setImageHeigth(int imageHeigth) {
		this.imageHeigth = imageHeigth;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void drawSprite(int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

}
