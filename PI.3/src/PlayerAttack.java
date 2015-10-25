import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author romulo
 *
 */
public class PlayerAttack implements MeleeAttack {

	@Override
	public void drawMeleeAttackAnimation(Graphics2D g2d, BufferedImage spriteSheet, BufferedImage meleeWeapon) {
		// TODO Auto-generated method stub
	}

	@Override
	public void performMeleeAttack() {
		Personagem player = CanvasGame2.player;
		Inimigos enemyAttacked = player.getEnemyAttacked();
		MeleeWeapon meleeWeapon = player.getMeleeWeapon();
		enemyAttacked.inflictDamage(meleeWeapon.getPhysicalDamage());
	}

}
