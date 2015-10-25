import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Personagem extends PlayerAttack implements Attack {

	float x, y;
	int charx, chary;
	float vel;
	private boolean isMelee;
	private final int MAX_HEALTH = 100;
	private final int RUN_SPEED = 100;
	private final float WALK_SPEED;
	int frame = 0;
	int anim = 0;
	int animspd = 200;
	int somatimeanim = 0;
	int hp = MAX_HEALTH;
	private MeleeWeapon meleeWeapon;
	private int attackSpeed;
	private SpriteSheetCharacter spriteSheetCharacter;
	private Inimigos enemyAttacked;
	float oldx, oldy;
	int direcao = 0;
	private int gold = 0;
	ArrayList<Tiro> tiros = new ArrayList<Tiro>();

	int contTiro = 2001;
	int cdTiro = 2000;
	boolean atirar = false;
	private BufferedImage img;
	public int numFlechas = 3;

	public Tocha tocha;
	public Tocha tocha2;
	public Polygon playerPolygon;
	int[] xS = new int[8];
	int[] yS = new int[8];
	boolean bleeding = true;
	public boolean tochaAtiva = false;
	boolean isColliding;
	private boolean isRunning;
	private int cont;

	public Personagem(float x, float y, BufferedImage img, int charx, int chary, float vel) {
		this.x = x;
		this.y = y;
		this.charx = charx;
		this.chary = chary;
		spriteSheetCharacter = new SpriteSheetCharacter(img, 3, 4);
		this.img = spriteSheetCharacter.getLeftMovement().get(0);

		WALK_SPEED = vel;
		this.vel = vel;
		tocha = new Tocha((int) x, (int) y, 15, null, 1);
		tocha2 = new Tocha((int) x, (int) y, 15, null, 2);
	}

	public void SimulaSe(long diftime) {
		somatimeanim += diftime;
		frame = (somatimeanim / animspd) % 3;

		xS[0] = (int) x;
		xS[1] = (int) x;
		xS[2] = (int) x;
		xS[3] = (int) x + charx / 2;
		xS[4] = (int) x + charx;
		xS[5] = (int) x + charx;
		xS[6] = (int) x + charx;
		xS[7] = (int) x + charx / 2;

		yS[0] = (int) y;
		yS[1] = (int) y + chary / 2;
		yS[2] = (int) y + chary;
		yS[3] = (int) y + chary;
		yS[4] = (int) y + chary;
		yS[5] = (int) y + chary / 2;
		yS[6] = (int) y;
		yS[7] = (int) y;

		playerPolygon = new Polygon(xS, yS, 8);

		tocha.polygons = CanvasGame2.salas.get(CanvasGame2.controlaSalas).polygons;
		tocha2.polygons = CanvasGame2.salas.get(CanvasGame2.controlaSalas).polygons;

		if (hp == 0) {
			CanvasGame2.instance.setGameOver(true);
			GamePanel.canvasAtivo = new GameOverScreen();
		}
		oldx = x;
		oldy = y;

		contTiro += diftime;

		if (contTiro > cdTiro) {

			atirar = true;
			contTiro = 0;
		}

		if (CanvasGame2.LEFT) {

			x -= vel * diftime / 1000.0f;
			direcao = 0;
			CanvasGame2.player.setImg(spriteSheetCharacter.getLeftMovement().get(frame));

		}
		if (CanvasGame2.RIGHT) {

			x += vel * diftime / 1000.0f;
			direcao = 1;
			CanvasGame2.player.setImg(spriteSheetCharacter.getRightMovement().get(frame));
		}
		if (CanvasGame2.UP) {

			y -= vel * diftime / 1000.0f;
			direcao = 2;
			CanvasGame2.player.setImg(spriteSheetCharacter.getUpMovement().get(frame));
		}
		if (CanvasGame2.DOWN) {

			y += vel * diftime / 1000.0f;
			direcao = 3;
			CanvasGame2.player.setImg(spriteSheetCharacter.getDownMovement().get(frame));
		}
		if (CanvasGame2.RUN) {
			cont++;
			if (cont == 1) {
				vel += RUN_SPEED;
			}
		}
		if (!CanvasGame2.RUN) {
			cont = 0;
			vel = WALK_SPEED;
		}

		if (CanvasGame2.MELEE) {
			isMelee = true;
			if (isEnemyColission() && meleeWeapon != null) {
				performMeleeAttack();
			}
			CanvasGame2.MELEE = false;
		}
		if (!CanvasGame2.MELEE) {
			isMelee = false;

		}

		if (CanvasGame2.SPACE && atirar && numFlechas > 0) {

			if (direcao == 0) {
				tiros.add(new Tiro(x, y + 5, LoadResources.arrowT, 32, 32, direcao, 1));
			} else if (direcao == 1) {
				tiros.add(new Tiro(x + img.getWidth(), y + 5, LoadResources.arrowT, 32, 32, direcao, 1));
			} else if (direcao == 2) {
				tiros.add(new Tiro(x + 5, y, LoadResources.arrowT, 32, 32, direcao, 1));
			} else if (direcao == 3) {
				tiros.add(new Tiro(x + 5, y + img.getHeight(), LoadResources.arrowT, 32, 32, direcao, 1));
			}

			numFlechas--;

			atirar = false;
		}

		for (int i = 0; i < CanvasGame2.salas.get(CanvasGame2.controlaSalas).tiles.size(); i++) {

			if (colide(CanvasGame2.salas.get(CanvasGame2.controlaSalas).tiles.get(i))) {

				x = oldx;
				y = oldy;
			}

		}

		if (checkItemchestCollision() && CanvasGame2.E) {

			GamePanel.canvasAtivo = ItemChestUI.getInstance();

			CanvasGame2.E = false;
		}

		for (int i = 0; i < CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.size(); i++) {

			if (colidePortas(CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i))) {

				CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).jD.janelaDialogoAtiva = true;
				
				if( CanvasGame2.E){
				
				int destP = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).destP;
				int tipo = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).tipo;

				switch (tipo) {
				default:
					
					if (CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).lock == false) {
						if (CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).open) {

							CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).open = false;

						} else {
							CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).open = true;
						}

						CanvasGame2.salas.get(CanvasGame2.controlaSalas).attPolygon(i);
						CanvasGame2.salas.get(CanvasGame2.controlaSalas).attMapa(i,
								CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).open);
					} else {
						
						if(CanvasPorta.instance == null){
							GamePanel.canvasAtivo = new CanvasPorta(i,CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).idKey);
						}
						else{
							GamePanel.canvasAtivo = CanvasPorta.instance;
						}
					}
					break;
				case 1:
					CanvasGame2.controlaSalas = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).destS;
					x = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).x + 16;
					y = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).y;
					break;
				case 2:
					CanvasGame2.controlaSalas = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).destS;
					x = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).x - 32;
					y = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).y;
					break;
				case 3:
					CanvasGame2.controlaSalas = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).destS;
					x = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).x;
					y = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).y - 32;
					break;
				case 4:
					CanvasGame2.controlaSalas = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).destS;
					x = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).x;
					y = CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(destP).y + 16;
					break;
				}
				CanvasGame2.salas.get(CanvasGame2.controlaSalas).visitada = true;
				CanvasGame2.E = false;
			}
			}
			else{
				CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).jD.janelaDialogoAtiva = false;
			}

		}

		if (tochaAtiva) {
			tocha.SimulaSe(diftime, (int) (x + charx / 2), (int) (y + chary / 2));
		} else {
			tocha2.SimulaSe(diftime, (int) (x + charx / 2), (int) (y + chary / 2));
		}

		for (int i = 0; i < CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos.size(); i++) {

			if (colideObjetos(CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos.get(i))) {

				CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos.get(i).jD.janelaDialogoAtiva = true;
				//System.out.println("aaaaaaaaaaaa");
				if (CanvasGame2.E) {

					CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos.get(i).jD.janelaDialogoAtiva = false;
					CanvasGame2.E = false;
					GamePanel.canvasAtivo = CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos
							.get(i).canvasInventario.instance;
				}
			} else {
				CanvasGame2.salas.get(CanvasGame2.controlaSalas).objetos.get(i).jD.janelaDialogoAtiva = false;
			}

		}

		if (hp < 50) {
			bleeding = true;
		} else {
			bleeding = false;
		}

		for (int i = 0; i < tiros.size(); i++) {
			tiros.get(i).SimulaSe(diftime);
		}
	}

	public boolean isEnemyColission() {

		ArrayList<Inimigos> inimigos = CanvasGame2.salas.get(CanvasGame2.controlaSalas).inimigos;
		System.out.println("tnc"+inimigos.get(0).x);
		for (Inimigos enemy : inimigos) {
			if (checkCollison(enemy)) {
				return true;
			}

		}
		return false;
	}

	private boolean checkCollison(Inimigos inimigo) {
		Rectangle playerColissionRectangle = getRectangle();
		if (playerColissionRectangle.intersects(inimigo.getRectangle())) {
			setEnemyAttacked(inimigo);
			return true;
		}
		return false;
	}

	private boolean checkItemchestCollision() {
		ItemChest chest = CanvasGame2.instance.getItemChest();
		Rectangle chestCollisionArea = chest.getCollisionArea();
		Rectangle playerCollisionArea = CanvasGame2.player.getRectangle();
		if (chestCollisionArea.intersects(playerCollisionArea)) {
			return true;
		}
		return false;
	}

	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {

		if (!isMelee) {
			for (int i = 0; i < tiros.size(); i++) {

				tiros.get(i).DesenhaSe(dbg, xMundo, yMundo);

			}
		} else {

		}

		dbg.drawImage(img, (int) x + xMundo, (int) y + yMundo, null);

		xS[0] = (int) x + xMundo;
		xS[1] = (int) x + xMundo;
		xS[2] = (int) x + xMundo;
		xS[3] = (int) x + charx / 2 + xMundo;
		xS[4] = (int) x + charx + xMundo;
		xS[5] = (int) x + charx + xMundo;
		xS[6] = (int) x + charx + xMundo;
		xS[7] = (int) x + charx / 2 + xMundo;

		yS[0] = (int) y + yMundo;
		yS[1] = (int) y + chary / 2 + yMundo;
		yS[2] = (int) y + chary + yMundo;
		yS[3] = (int) y + chary + yMundo;
		yS[4] = (int) y + chary + yMundo;
		yS[5] = (int) y + chary / 2 + yMundo;
		yS[6] = (int) y + yMundo;
		yS[7] = (int) y + yMundo;

		for (int i = 0; i < 8; i++) {

			dbg.fillRect(xS[i], yS[i], 2, 2);

		}

		dbg.drawRect((int) x - 15 + xMundo, (int) y - 15 + yMundo, charx + 30, chary + 30);
		//dbg.drawRect((int) x -5 +xMundo, (int) y- 5 +yMundo , charx+10, chary+10);
	}

	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle((int) this.x, (int) this.y, img.getWidth(), img.getHeight());
		return rectangle;
	}

	public boolean colide(Tile p) {

		Rectangle rect = new Rectangle((int) x + 5, (int) y + 5, charx, chary);
		Rectangle rect2 = new Rectangle(p.x, p.y, p.tamanho, p.tamanho);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colidePortas(Portas p) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle(p.x - 15, p.y - 15, p.charx + 30, p.chary + 30);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean colideObjetos(Objetos p) {

		Rectangle rect = new Rectangle((int) x - 5, (int) y - 5, charx+10, chary+10);
		Rectangle rect2 = new Rectangle(p.x, p.y, p.charx, p.chary);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Tiro> getTiros() {
		return tiros;
	}

	public void setTiros(ArrayList<Tiro> tiros) {
		this.tiros = tiros;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void addHp(int hp) {

		if (this.hp + hp > MAX_HEALTH) {

			this.hp = MAX_HEALTH;

		} else {

			this.hp += hp;

		}

	}

	public int getMAX_HEALTH() {
		return MAX_HEALTH;
	}

	public void saveGame() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("saveFile.txt", "UTF-8");
			writer.println(hp);
			writer.println(x);
			writer.println(y);

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.getMessage();
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public MeleeWeapon getMeleeWeapon() {
		return meleeWeapon;
	}

	public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
		this.meleeWeapon = meleeWeapon;
	}

	public Inimigos getEnemyAttacked() {
		return enemyAttacked;
	}

	public void setEnemyAttacked(Inimigos enemyAttacked) {
		this.enemyAttacked = enemyAttacked;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
}
