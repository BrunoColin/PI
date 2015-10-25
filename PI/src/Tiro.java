import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tiro {

	float x;
	float y;
	BufferedImage img;
	int charx;
	int chary;
	int direcao;
	float vel = 200;
	int tipo;

	public int nFrames = 4; // quantidade de frames da animacao
	int frame = 0;
	int anim = 0;
	int animspd = 300; // velocidade da animacao
	int somatimeanim = 0;
	boolean broke = false;
	int chanceBroke = 50;
	int chanceBrokeInWall = 80;
	boolean moving = true;
	boolean hit = false;
	int dano = 100;
	int danoFlame = 5;
	int tempoFlame = 5000;
	boolean flame = false;
	// Inimigos iHit;

	public Tiro(float x, float y, BufferedImage img, int charx, int chary, int direcao, int tipo) {

		this.x = x;
		this.y = y;
		this.img = img;
		this.charx = charx;
		this.chary = chary;
		this.direcao = direcao;
		this.tipo = tipo;
		if (tipo == 1) {
			flame = true;
		}

	}

	public void SimulaSe(long diftime) {

		somatimeanim += diftime;
		frame = (somatimeanim / animspd) % nFrames;

		switch (direcao) {
		case 0:

			if (moving) {
				x -= vel * diftime / 1000.0f;
			}
			// else{
			// if(iHit != null){
			// x = iHit.x;
			// }
			// }
			anim = 1;

			break;
		case 1:
			if (moving) {
				x += vel * diftime / 1000.0f;
			}
			anim = 2;

			break;
		case 2:
			if (moving) {
				y -= vel * diftime / 1000.0f;
			}
			// else{
			// if(iHit != null){
			// y = iHit.y;
			// }
			// }
			anim = 3;

			break;
		case 3:
			if (moving) {
				y += vel * diftime / 1000.0f;
			}
			// else{
			// if(iHit != null){
			// y = iHit.y;
			// }
			// }
			anim = 0;

			break;

		default:
			break;
		}

		if (CanvasGame2.salas.get(CanvasGame2.controlaSalas).mapa2[(int) ((x + charx / 2) / 16)][(int) ((y + chary / 2)
				/ 16)].tipo == 1 || CanvasGame2.salas.get(CanvasGame2.controlaSalas).mapa2[(int) ((x + charx / 2) / 16)][(int) ((y + chary / 2)
						/ 16)].tipo == 2) {

			// broke = checkIfBroke(chanceBrokeInWall);

			// if(broke){
			CanvasGame2.player.tiros.remove(this);
			// }
			// else{
			// hit = true;
			// moving = false;
			// }
		}

	}

	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {

		// dbg.drawImage(img,(int)x,(int)y,null);
		if (moving) {
			dbg.drawImage(img, (int) x + xMundo, (int) y + yMundo, (int) x + xMundo + charx, (int) y + yMundo + chary,
					charx * frame, chary * anim, charx + charx * frame, chary + chary * anim, null);
		}

	}

	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle((int) this.x, (int) this.y, img.getWidth(), img.getHeight());
		return rectangle;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean checkIfBroke(int type) {

		int chance = CanvasGame2.rnd.nextInt(100);

		if (type == 0) {
			if (chance > chanceBroke) {

				return true;
			} else {
				return false;
			}
		} else {
			if (chance > chanceBrokeInWall) {

				return true;
			} else {
				return false;
			}
		}

	}

	public boolean rectColisionEnemy(Inimigos i) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) i.x, (int) i.y, i.charx, i.chary);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}

	}

	public int getDamage() {

		return dano;

	}
}
