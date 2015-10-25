import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Inimigo3 extends Inimigos {

	private BufferedImage img;
	BufferedImage bag;
	float x, y;
	private float vel = 0;
	float velMove = 45;
	private int estado = 0;
	private int estadoAndando = 0;
	private int contIA = 0;
	public double ang = 0;
	private int pos = 0;
	int pathAatual = 0;
	private int posSangue = 0;
	double dxSangue = 0;
	double dySangue = 0;
	double distSangue = 0;
	boolean setPosSangue = false;
	private final int ATTACKDAMAGE = 10;
	private final int ATACKCOOLDOWN = 2000;
	private final int INITIALHEALTH = 100;
	private int contAttack = 0;
	ArrayList<Point2D> point2ds = new ArrayList<Point2D>();
	private boolean attack = false;
	double dxD;
	double dyD;
	public AEstrela aestrela;
	public int caminho[] = null;
	boolean setEstrela = true;
	int nFrames = 8;
	private int frame = 0;
	private int anim = 0;
	private int animspd = 200;
	private int somatimeanim = 0;
	public boolean setouobjetivo = true;
	float oldx, oldy;
	int cont = 0;
	int charx, chary;
	int health = 100;
	int maxHealth = 100;
	int charxBag = 24;
	int cdVigiando = 1000;
	int contVigiando = 0;
	boolean vigiando = false;
	boolean dead = false;
	boolean burn = false;
	boolean findSangue = false;
	boolean left = true;
	double ang2 = 0;
	double angVisao = 0;
	int contSeguindo = 0;
	int cdSeguindo = 1000;
	boolean voltando = false;
	int pontoObjetivo = 0;
	int contAlerta = 0;
	int cdAlerta = 1500;
	int contAtacando = 0;
	int cdAtacando = 500;
	int contAtacando2 = 0;
	int cdAtacando2 = 250;
	float velAtacando = 250;
	boolean atacando = false;
	boolean posAlerta = false;
	Point2D lastPosPlayer = new Point2D.Float();
	boolean setCaminho = true;
	boolean wayNotFound = false;
	int numTry = 0;

	JanelaDialogo jd;
	CanvasLoot cL;
	ArrayList<Item> itens = new ArrayList<Item>();
	ArrayList<TiroInimigo3> tiros = new ArrayList<TiroInimigo3>();
	int contTiro = 0;
	int cdTiro = 2000;
	
	
	CampoVisao visao;

	int tipoItem = 0;
	Color c;

	public Inimigo3(BufferedImage image, float vel, float x, float y, int charx, int chary) {

		this.img = image;
		bag = LoadResources.bag;
		this.vel = vel;
		this.x = x;
		this.y = y;
		this.charx = charx;
		this.chary = chary;
		Point2D p = new Point2D.Float();
		p.setLocation(x, y);

		point2ds.add(0, p);

		jd = new JanelaDialogo("E", false, (int) this.x, (int) this.y, 20, 20);

		tipoItem = CanvasGame2.rnd.nextInt(4);

		visao = new CampoVisao((int) x, (int) y, 20, null);

		switch (tipoItem) {
		case 0:

			itens.add(new Item(LoadResources.smallPotion, 0, 0, 1, "Poção Pequena", -1));

			break;
		case 1:

			itens.add(new Item(LoadResources.arrow, 0, 0, 1, "Flecha", -1));

			break;
		case 2:

			itens.add(new Item(LoadResources.potion, 0, 0, 1, "Poção", -1));

			break;
		case 3:

			itens.add(new Item(LoadResources.torch, 0, 0, 1, "Tocha", -1));

			break;
		}
		


	}

	public void SimulaSe(long diftime) {
		
		
		//System.out.println("size "+ paths.get(1).points.size());
		
//		for(int i = 0; i < paths.size();i++){
//			for(int j = 0; j < paths.get(i).points.size();j++){
//				
//				System.out.println(paths.get(i).points.get(j).getX() + "  " + paths.get(i).points.get(j).getY());
//				
//			}
//			
//		}
//		
		somatimeanim += diftime;
		frame = (somatimeanim / animspd) % nFrames;
		contIA += diftime;

		s = CanvasGame2.salas.get(CanvasGame2.controlaSalas);
		
		oldx = x;
		oldy = y;

		if (dead == false) {

			visao.polygons = CanvasGame2.salas.get(CanvasGame2.controlaSalas).polygons;

			visao.SimulaSe(diftime, (int) (x + charx / 2), (int) (y + chary / 2), angVisao, (int) x + charx / 2,
					(int) y + chary / 2);

			if (s.tilesSangue.isEmpty() == false && state != CACANDO) {
				// System.out.println("taffarelllllll" + s.tilesSangue.size());
				for (int i = 0; i < s.tilesSangue.size(); i++) {

					if (rectColision(s.tilesSangue.get(i))) {

						posSangue = i;
						findSangue = true;
						int size = i;

						for (int j = 1; j < size; j++) {

							s.tilesSangue.remove(j - 1);
							size--;

						}

					}

					// else{
					// findSangue = false;
					// }

				}

			}

			if (setEstrela) {

				aestrela = new AEstrela(s);
				setEstrela = false;
			}

			if (setouobjetivo == true) {
				setaObjetivo((int) (CanvasGame2.player.x + CanvasGame2.player.charx / 2) / 16,
						(int) (CanvasGame2.player.y + CanvasGame2.player.chary / 2) / 16);
				setouobjetivo = false;
			}

			if (aestrela.iniciouAestrela) {
				if (aestrela.achoufinal == false) {
					int[] retorno = aestrela.continuapath();
					if (retorno != null) {
						AEstrela atmp = new AEstrela(s);
						int[] caminho2 = atmp.StartAestrela((int) ((x + charx / 2) / 16), (int) ((y + chary / 2) / 16),
								retorno[0], retorno[1], 50);

						for (int i = 0; i < (caminho2.length / 2); i++) {
							int pat1x = caminho2[i * 2];
							int pat1y = caminho2[i * 2 + 1];
							for (int j = 0; j < (retorno.length / 2); j++) {
								if (pat1x == retorno[j * 2] && pat1y == retorno[j * 2 + 1]) {
									caminho = new int[i * 2 + ((retorno.length / 2) - j) * 2];
									for (int z = 0; z < i * 2; z++) {
										caminho[z] = caminho2[z];
									}
									for (int z = 0; z < ((retorno.length / 2) - j) * 2; z++) {
										caminho[i * 2 + z] = retorno[j * 2 + z];
									}

									aestrela.achoufinal = true;
									estado = 0;
									return;
								}
							}
						}
					}

				}
			}

			// if(visao.find){
			//
			// state = SEGUINDO;
			// vigiando = false;
			// contVigiando = 0;
			//
			// }
			// else{
			//
			// //state = VOLTANDO;
			// }

			if (contIA > 100) {
				CalculaIA(diftime);
				contIA = 0;
			}

			x += Math.cos(ang) * vel * diftime / 1000.0;
			y += Math.sin(ang) * vel * diftime / 1000.0;

			double oposto = Math.sin(ang) * vel;
			double adjac = Math.cos(ang) * vel;

			if (adjac < 0 && Math.abs(adjac) > Math.abs(oposto)) {
				// img = LoadResources.zombie1Up;
				anim = 3;
			} else {
				if (adjac > 0 && adjac > Math.abs(oposto)) {
					// img = LoadResources.zombie1Down;
					anim = 2;
				} else {
					if (oposto > 0 && oposto > Math.abs(adjac)) {
						// img = LoadResources.zombie1Left;
						anim = 0;
					} else {
						if (oposto < 0 && Math.abs(oposto) > Math.abs(adjac)) {
							// img = LoadResources.zombie1Right;
							anim = 1;
						}
					}
				}
			}

			collisionWithPlayer();
			contAttack += diftime;

			if (contAttack >= ATACKCOOLDOWN) {
				attack = true;
				contAttack = 0;
			}

			for (int i = 0; i < CanvasGame2.player.tiros.size(); i++) {
				if (rectColisionEnemy(CanvasGame2.player.tiros.get(i))) {

					health -= CanvasGame2.player.tiros.get(i).getDamage();
					CanvasGame2.player.tiros.remove(i);
				}
			}

			checkDeath();
		} else {

			charx = charxBag;
			chary = charxBag;

			if (rectColision(CanvasGame2.player)) {

				jd.janelaDialogoAtiva = true;

				if (CanvasGame2.E) {

					jd.janelaDialogoAtiva = false;

					CanvasGame2.E = false;
					cL = new CanvasLoot(1, 1, itens, this);
					GamePanel.canvasAtivo = cL;

				}

			} else {

				jd.janelaDialogoAtiva = false;

			}
		}

		for(int i = 0; i < tiros.size();i++){
			
			tiros.get(i).SimulaSe(diftime);
			
		}
		
	}

	public boolean collisionWithPlayer() {
		if (CanvasGame2.player.getRectangle().intersects(getRectangle()) && attack) {
			// CanvasGame2.p.setHp(CanvasGame2.p.getHp() - ATTACKDAMAGE);
			attack = false;
			return true;
		}
		return false;
	}

	public int getATTACKDAMAGE() {
		return -ATTACKDAMAGE;
	}

	public void CalculaIA(long diftime) {
		dxD = paths.get(pathAatual).points.get(pos).getX() - x;
		dyD = paths.get(pathAatual).points.get(pos).getY() - y;
		double distD = dxD * dxD + dyD * dyD;

		int cd = 100;

		cont += diftime;
		contTiro += diftime;
		
		if (state == ATACANDO) {

			
			if (visao.find == false) {
				lastPosPlayer.setLocation(CanvasGame2.player.x, CanvasGame2.player.y);
				setCaminho = true;
				contAlerta = 0;
				vigiando = false;
				state = ALERTA;
				voltando = true;
			}
			else{
			if(contTiro > cdTiro){
				
				dxD = CanvasGame2.player.x - x;
				dyD = CanvasGame2.player.y - y;
				
				ang = Math.atan2(dyD, dxD);
				
				tiros.add(new TiroInimigo3(x, y, bag, 20, 20, ang, this));
				
				contTiro = 0;
				
			}
			state = SEGUINDO;
			}
			

		}

		if (state == CACANDO) {

			if (visao.find) {
				state = SEGUINDO;
				posSangue = 0;
			}
			// else{
			//
			// if(findSangue){
			//
			// state = CACANDO;
			//
			// }
			//
			// }

			if (s.tilesSangue.isEmpty() == false) {

				vel = velMove;
				// System.out.println(posSangue);
				dxSangue = s.tilesSangue.get(posSangue).getX() * 16 - x;
				dySangue = s.tilesSangue.get(posSangue).getY() * 16 - y;
				distSangue = dxSangue * dxSangue + dySangue * dySangue;
				ang = Math.atan2(dySangue, dxSangue);
				angVisao = ang;

				if (distSangue < 169) {
					if (posSangue < s.tilesSangue.size() - 1) {
						posSangue += 1;
					} else {
						System.out.println("final do sangue");
						posSangue = 0;
						state = ALERTA;
					}
					setPosSangue = false;
				}
			} else {

				state = ALERTA;

			}

		}
		if (state == SEGUINDO) {

			if (visao.find == false) {

				lastPosPlayer.setLocation(CanvasGame2.player.x, CanvasGame2.player.y);
				setCaminho = true;
				contAlerta = 0;
				vigiando = false;
				state = ALERTA;
				voltando = true;
			}

			angVisao = Math.atan2(CanvasGame2.player.y - y, CanvasGame2.player.x - x);
			// ESTRELA

			if (cont > cd) {

				caminho = setaObjetivo((int) CanvasGame2.player.x / 16, (int) CanvasGame2.player.y / 16);
				cont = 0;
			}
			if (caminho != null) {

				if ((estado * 2) < caminho.length) {

					double dx = ((caminho[estado * 2] * 16) + 8) - (x + (charx / 2));
					double dy = ((caminho[estado * 2 + 1] * 16) + 8) - (y + (chary / 2));
					double dist = dx * dx + dy * dy;

					if (dist < 64) {
						estado++;
					} else {

						ang = Math.atan2(dy, dx);

					}
					vel = velMove;
				} else {

					vel = velMove;
				}
			} else {

				vel = velMove;
			}

			if (rectColisionVisao(CanvasGame2.player)) {

				state = ATACANDO;
				// System.out.println("aaaaaaa");
				caminho = null;

			}
			else{
				
				state = ALERTA;
				
			}

		}

		if (state == ALERTA) {

			//if (posAlerta) {
				contAlerta += diftime;
			//}
			if (visao.find) {

				state = SEGUINDO;
				contAlerta = 0;

			} else {
				// System.out.println(findSangue);
				if (findSangue) {

					state = CACANDO;
					contAlerta = 0;

				}

			}

			// if(contAlerta >cdAlerta){
			// System.out.println("Set outro caminho");
			// //state = VOLTANDO;
			//
			// //setCaminho = false;
			//
			// //posAlerta = true;
			//
			//
			// contAlerta = 0;
			//
			// }
			if (vigiando == false) {

				if (setCaminho) {
					System.out.println("Set caminho inicial");
					// caminho = setaObjetivo((int)lastPosPlayer.getX()/16,
					// (int)lastPosPlayer.getY()/16);
					setCaminho = false;
				}

				if (wayNotFound) {
					System.out.println("caminho não encontrado");
					numTry++;
					// setCaminho = true;
					// caminho = setaObjetivo((int)lastPosPlayer.getX()/16,
					// (int)lastPosPlayer.getY()/16);
					// wayNotFound = false;
					numTry = 0;
					wayNotFound = false;
					state = VOLTANDO;

				}
			}
			if (numTry > 0) {

				numTry = 0;
				state = VOLTANDO;
				System.out.println("num max");

			}

			angVisao = ang;

			// ESTRELA
			if (caminho != null) {

				if ((estado * 2) < caminho.length) {

					double dx = ((caminho[estado * 2] * 16) + 8) - (x + (charx / 2));
					double dy = ((caminho[estado * 2 + 1] * 16) + 8) - (y + (chary / 2));
					double dist = dx * dx + dy * dy;

					if (s.mapa2[caminho[estado * 2]][caminho[estado * 2 + 1]].tipo == 2) {
						System.out.println("bloqueado");
						wayNotFound = true;
						vigiando = false;

					}

					if (dist < 64) {
						estado++;
					} else {

						ang = Math.atan2(dy, dx);

					}
					vel = velMove;
					angVisao = ang;
					ang2 = ang;
					left = true;
				} else {
					// setCaminho = true;
					// posAlerta = true;
					wayNotFound = true;
					vigiando(diftime);
				}
			} else {
				// setCaminho = true;
				// posAlerta = true;
				wayNotFound = true;
				vigiando(diftime);
			}
		}

		if (state == ANDANDO) {

			if (visao.find) {
				state = SEGUINDO;
			} else {

				if (findSangue) {

					state = CACANDO;

				}

			}

			if (vigiando == false) {
				ang = Math.atan2(dyD, dxD);
				angVisao = ang;
				ang2 = ang;
				left = true;
			}

			if (distD < 169) {
				vigiando(diftime);

			}

		}

		if (state == VOLTANDO) {

			if (visao.find) {

				state = SEGUINDO;

			} else {

				if (findSangue) {

					state = CACANDO;

				}

			}

			if (voltando) {

				double distt = 1000000;
				double dist2 = 0;

				for (int i = 0; i < point2ds.size(); i++) {

					dxD = point2ds.get(i).getX() - x;
					dyD = point2ds.get(i).getY() - y;
					dist2 = dxD * dxD + dyD * dyD;

					if (dist2 < distt) {
						distt = dist2;
						pontoObjetivo = i;
					}

				}

				caminho = setaObjetivo((int) point2ds.get(pontoObjetivo).getX() / 16,
						(int) point2ds.get(pontoObjetivo).getY() / 16);
				// pos = pontoObjetivo;
				voltando = false;

			}

			if (caminho != null) {

				if ((estado * 2) < caminho.length) {

					double dx = ((caminho[estado * 2] * 16) + 8) - (x + (charx / 2));
					double dy = ((caminho[estado * 2 + 1] * 16) + 8) - (y + (chary / 2));
					double dist = dx * dx + dy * dy;

					if (s.mapa2[caminho[estado * 2]][caminho[estado * 2 + 1]].tipo == 2) {

						voltando = true;

					}
					if (dist < 64) {
						estado++;
					} else {

						ang = Math.atan2(dy, dx);
						angVisao = Math.atan2(dy, dx);

					}
					vel = velMove;
				} else if (aestrela.blocked) {
					System.out.println("lol");
					voltando = true;

				} else {

					double dx = point2ds.get(pontoObjetivo).getX() - x;
					double dy = point2ds.get(pontoObjetivo).getY() - y;
					double dist = dx * dx + dy * dy;

					if (dist < 640) {

						vel = velMove;
						state = ANDANDO;
						vigiando = false;
						contVigiando = 0;

					} else {

						voltando = true;

					}

				}
			} else {

				System.out.println("second");

				double dx = x - point2ds.get(pontoObjetivo).getX();
				double dy = y - point2ds.get(pontoObjetivo).getY();
				double dist = dx * dx + dy * dy;

				if (dist < 64) {

					vel = velMove;
					state = ANDANDO;
					vigiando = false;
					contVigiando = 0;

				} else {

					voltando = true;

				}

			}

		}

	}

	public int[] setaObjetivo(int objetivox, int objetivoy) {

		int caminho[];

		caminho = aestrela.StartAestrela((int) ((x + charx / 2) / 16), (int) ((y + chary / 2) / 16), objetivox,
				objetivoy, 100);
		estado = 0;

		return caminho;

	}

	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle((int) this.x, (int) this.y, charx, chary);
		return rectangle;
	}

	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {

		if (dead == false) {
			dbg.drawImage(img, (int) x + xMundo, (int) y + yMundo, (int) x + xMundo + charx, (int) y + yMundo + chary,
					charx * frame, chary * anim, charx + charx * frame, chary + chary * anim, null);
		} else {
			dbg.drawImage(bag, (int) x + xMundo, (int) y + yMundo, charxBag, charxBag, null);
		}

		if (state == SEGUINDO) {
			dbg.setColor(Color.red);
		} else if (state == ANDANDO) {
			dbg.setColor(Color.blue);
		} else {
			dbg.setColor(Color.green);
		}
		Stroke stk = dbg.getStroke();
		dbg.setStroke(new BasicStroke(1.5f));
		if (caminho != null) {
			for (int i = 0; i < (caminho.length / 2) - 1; i++) {

				int x1 = caminho[(i * 2)] * 16 + 8;
				int y1 = caminho[(i * 2) + 1] * 16 + 8;
				int x2 = caminho[((i + 1) * 2)] * 16 + 8;
				int y2 = caminho[((i + 1) * 2) + 1] * 16 + 8;
				dbg.drawLine(x1 + xMundo, y1 + yMundo, x2 + xMundo, y2 + yMundo);
			}
		}

		switch (state) {
		case SEGUINDO:

			c = new Color(255, 0, 0, 125);

			break;
		case CACANDO:

			c = new Color(100, 0, 0, 125);

			break;
		case ATACANDO:

			c = new Color(100, 0, 100, 125);

			break;
		case ALERTA:

			c = new Color(255, 255, 0, 125);

			break;
		case ANDANDO:

			c = new Color(0, 255, 0, 125);

			break;
		case VOLTANDO:

			c = new Color(0, 255, 0, 125);

			break;
		}

		visao.DesenhaSe(dbg, xMundo, yMundo, c);

		if (dead == false) {
			int vidaAtual = charx * 2 * health / maxHealth;
			// dbg.fillRect((int) x - charx / 2, (int) y - 10, vidaAtual, 10);
			dbg.setColor(Color.red);
			dbg.fillRect((int) x + xMundo - charx / 2, (int) y + yMundo - 20, charx * 2, 11);
			dbg.setColor(Color.yellow);
			dbg.fillRect((int) x + xMundo - charx / 2, (int) y + yMundo - 20, vidaAtual, 10);
			dbg.drawRect((int) x - 200 +xMundo, (int) y - 200+yMundo, charx + 400, chary + 400);
		}
		
		
		for(int i = 0; i < tiros.size();i++){
			
			tiros.get(i).DesenhaSe(dbg, xMundo, yMundo);
			
		}
	}

	void addPonto(Point2D p) {

		if (point2ds == null) {

			point2ds.add(p);

		} else {
			if (point2ds.contains(p) == false) {
				point2ds.add(p);
			}
		}

	}

	public void checkDeath() {

		if (health <= 0 && dead == false) {

			dead = true;

		}

	}

	public void checkHealth() {

		if (health < INITIALHEALTH / 33) {

			state = FUGINDO;

		}

	}

	public boolean rectColisionEnemy(Tiro i) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) i.x, (int) i.y, i.charx, i.chary);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean rectColisionVisao(Personagem i) {

		Rectangle rect = new Rectangle((int) x - 200, (int) y - 200, charx + 400, chary + 400);
		Rectangle rect2 = new Rectangle((int) i.x - 15, (int) i.y - 15, i.charx + 30, i.chary + 30);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean rectColision(Personagem i) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) i.x, (int) i.y, i.charx, i.chary);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean rectColision(Tile i) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) i.x, (int) i.y, i.tamanho, i.tamanho);

		if (rect.intersects(rect2)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean rectColision(Point2D sangue) {

		Rectangle rect = new Rectangle((int) x, (int) y, charx, chary);
		Rectangle rect2 = new Rectangle((int) sangue.getX() * 16, (int) sangue.getY() * 16, 16, 16);

		if (rect.intersects(rect2)) {
			// System.out.println("entrou");
			return true;
		} else {
			return false;
		}

	}

	public void vigiando(long diftime) {

		vigiando = true;
		vel = 0;

		contVigiando += diftime;
		if (contVigiando <= cdVigiando + 500) {

			if (left) {

				ang -= Math.toRadians(10);

				if (ang2 - Math.toRadians(90) > ang) {

					left = false;

				}

			} else {

				ang += Math.toRadians(10);

				if (ang2 + Math.toRadians(90) < ang) {

					left = true;

				}
			}

			angVisao = ang;
		} else {
			System.out.println("entrou");

			vel = 50;
			contVigiando = 0;
			if (pos < paths.get(pathAatual).points.size() - 1) {
				pos += 1;
			} else {
				if(pathAatual < paths.size()-1){
					
					pathAatual +=1;
					pos = 0;
				}
				else{
					
					pathAatual = 0;
					pos = 0;
					
				}
				
				
			}

			vigiando = false;

		}

	}

//	public void vigiando(long diftime) {
//
//		vigiando = true;
//		vel = 0;
//		
//
//		contVigiando += diftime;
//		if (contVigiando <= cdVigiando + 500) {
//
//			if (left) {
//
//				ang -= Math.toRadians(10);
//
//				if (ang2 - Math.toRadians(90) > ang) {
//
//					left = false;
//
//				}
//
//			} else {
//
//				ang += Math.toRadians(10);
//
//				if (ang2 + Math.toRadians(90) < ang) {
//
//					left = true;
//
//				}
//			}
//
//			angVisao = ang;
//		} else {
//			//System.out.println("entrou");
//
//			vel = 50;
//			contVigiando = 0;
//			if (pos < paths.get(pathAatual).points.size() - 1) {
//				pos += 1;
//				vigiando = false;
//				go = false;
//			} else {
//			
//				
//				if(pathAatual < paths.size()-1){
//					
//					pathAatual +=1;
//					pos = 0;
//					
//				}
//				else{
//					
//					pathAatual = 0;
//					pos = 0;
//					
//				}
//				
//				caminho = setaObjetivo((int) paths.get(pathAatual).points.get(pos).getX() / 16,
//					(int) paths.get(pathAatual).points.get(0).getY() / 16);
//			// pos = pontoObjetivo;
//				go = true;
//				//vigiando = false;
//			
//			}
////			if(go){
////				
////				if (pos < paths.get(pathAatual).points.size() - 1) {
////					pos += 1;
////				} else {
////					System.out.println("aaa");
////					go = false;
////					
////				}
////
////				
////			}
////			else{
////				
////				if (pos > 0) {
////					pos -= 1;
////					
////				}
////				else{
////					if(pathAatual < paths.size()-1){
////
////						pathAatual +=1;
////						pos = 0;
////						go = true;
////					}
////					else{
////
////						pathAatual = 0;
////						pos = 0;
////						go = true;
////
////					}
////				}
////
////			}
//			
//			
//
//		}
//
//		
//		
//		
//	}
	
	public void inflictDamage(int physicalDamage) {
		if (health > 0) {
			health -= physicalDamage;
		}

	}

	@Override
	public float posX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float posY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public ArrayList<TiroInimigo3> getTiros() {
		// TODO Auto-generated method stub
		return tiros;
	}

}