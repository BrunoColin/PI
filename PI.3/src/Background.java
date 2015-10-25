import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface Background {

	abstract void drawBackground(Graphics2D g2d, BufferedImage background);
}
