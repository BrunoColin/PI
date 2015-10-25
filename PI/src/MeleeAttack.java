import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author romulo
 *
 */
public interface MeleeAttack {

	void drawMeleeAttackAnimation(Graphics2D g2d, BufferedImage spriteSheet, BufferedImage meleeWeapon);

	void performMeleeAttack();
}
