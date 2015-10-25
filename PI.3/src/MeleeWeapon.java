import java.awt.image.BufferedImage;

/**
 * 
 */

/**
 * @author romulo
 *
 */
public class MeleeWeapon extends Item {
	private int physicalDamage;
	private BufferedImage weaponImg;

	public MeleeWeapon(BufferedImage item, int physicalDamage) {
		super(item);
		this.physicalDamage = physicalDamage;
	}

	public int getPhysicalDamage() {
		return physicalDamage;
	}

	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	public BufferedImage getWeaponImg() {
		return weaponImg;
	}

	public void setWeaponImg(BufferedImage weaponImg) {
		this.weaponImg = weaponImg;
	}
}
