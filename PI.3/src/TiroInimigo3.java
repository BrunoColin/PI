import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class TiroInimigo3 {

	float x;
	float y;
	BufferedImage img;
	int charx;
	int chary;
	float vel = 100;

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
	double ang = 0;
	double dxD;
	double dyD;
	Inimigos pai;
	// Inimigos iHit;

	public TiroInimigo3(float x, float y, BufferedImage img, int charx, int chary, double ang, Inimigos pai) {

		this.x = x;
		this.y = y;
		this.img = img;
		this.charx = charx;
		this.chary = chary;
		this.ang = ang;
		this.pai = pai;

	}

	public void SimulaSe(long diftime) {

		somatimeanim += diftime;
		frame = (somatimeanim / animspd) % nFrames;

		x += Math.cos(ang) * vel * diftime / 1000.0;
		y += Math.sin(ang) * vel * diftime / 1000.0;

		if (CanvasGame2.salas.get(CanvasGame2.controlaSalas).mapa2[(int) ((x + charx / 2) / 16)][(int) ((y + chary / 2)
				/ 16)].tipo == 1 || CanvasGame2.salas.get(CanvasGame2.controlaSalas).mapa2[(int) ((x + charx / 2) / 16)][(int) ((y + chary / 2)
						/ 16)].tipo == 2) {
			
			pai.getTiros().remove(this);

		}
		
		if(rectColisionPersonagem(CanvasGame2.player)){
			
			pai.getTiros().remove(this);
			CanvasGame2.player.hp -= 20;
			
		}

	}

	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {

		// dbg.drawImage(img,(int)x,(int)y,null);
		if (moving) {
//			dbg.drawImage(img, (int) x + xMundo, (int) y + yMundo, (int) x + xMundo + charx, (int) y + yMundo + chary,
//					charx * frame, chary * anim, charx + charx * frame, chary + chary * anim, null);
			dbg.drawImage(img, (int)x+xMundo, (int)y+yMundo,charx,chary,null);
		}

	}

	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle((int) this.x, (int) this.y, img.getWidth(), img.getHeight());
		return rectangle;
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

	public boolean rectColisionPersonagem(Personagem p) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) p.x, (int) p.y, p.charx, p.chary);

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
