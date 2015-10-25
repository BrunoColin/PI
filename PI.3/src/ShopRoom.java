import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class that represents the Shop Room.
 * 
 * @author romulo
 *
 */
public class ShopRoom extends ItemShopUI {
	private BufferedImage image;
	private NPC merchant;

	public ShopRoom(BufferedImage shop) {
		this.image = shop;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void drawShopRoom(Graphics2D g2d) {
		g2d.drawImage(image, 150, 140, 225, 300, null);
	}
}
