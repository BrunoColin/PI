import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CanvasLoot implements myCanvas {

	public CanvasLoot instance = null;

	int x = 100;
	int y = 200;
	int width = 400;
	int height = 200;
	boolean LEFT, RIGHT;
	ArrayList<Item> itens;
	int capacidadeX;
	int capacidadeY;
	Item[][] matrizItens;
	int posX = 0;
	int posY = 0;
	int xDraw = 0;
	int yDraw = 0;
	JanelaDialogo2 jd;

	BufferedImage img;
	BufferedImage imgSel;
	BufferedImage imgItem;

	Inimigos pai;

	int cont = 0;

	public CanvasLoot(int capacidadeX, int capacidadeY, ArrayList<Item> itens, Inimigos pai) {

		instance = this;
		this.capacidadeX = capacidadeX;
		this.capacidadeY = capacidadeY;
		this.itens = itens;
		matrizItens = new Item[capacidadeX][capacidadeY];
		this.pai = pai;

		img = GamePanel.loadImage("fundoInventario.png");
		imgSel = GamePanel.loadImage("itemSel.png");
		imgItem = GamePanel.loadImage("itemBox.png");

		jd = new JanelaDialogo2(" Você deseja adicionar ", " ao inventário ?", false, x, y + 50, 300, 200);
		int a = 0;
		int b = 0;
		for (int i = 0; i < capacidadeX * capacidadeY; i++) {

			if (a == capacidadeX) {
				a = 0;
				b++;
			}

			if (i < itens.size()) {
				itens.get(i).setX(i * 50);
				itens.get(i).setY(i * 50);
				// System.out.println(itens.get(i).getX());
				matrizItens[a][b] = itens.get(i);
			} else {

				matrizItens[a][b] = new Item(null, 0, 0,0, null,-1);
			}
			a++;
		}

	}

	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub

		// if(posY < 0){
		// posY = 0;
		// }
		// if(posY > capacidadeY){
		// posY = 3;
		// }
		if (jd.janelaDialogoAtiva) {
			jd.SimulaSe(diftime);
		}
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// System.out.println("aaaaaaaaaaaaaaa");

		CanvasGame2.salas.get(CanvasGame2.controlaSalas).DesenhaSe(dbg);

		dbg.clipRect(x, y, width, height);

		// Color c = new Color(50,50,50,200);
		// dbg.setColor(c);
		// dbg.clearRect(x, y, width, height);
		// dbg.fillRect (x, y, width, height);
		dbg.drawImage(img, x, y, width, height, null);

		// dbg.setColor(Color.black);
		// dbg.fillRect(x, y, width, height);
		// dbg.drawImage(img,x,y,width,height,null);
		// dbg.drawImage(img,x+50,y+50,width/2,height/2,null);
		xDraw = x + posX * 50 + 100;
		yDraw = y + posY * 50 + 50;

		for (int i = 0; i < capacidadeX; i++) {
			for (int j = 0; j < capacidadeY; j++) {

				if (i == posX && j == posY) {
					dbg.drawImage(imgSel, xDraw + i * 20, yDraw + j * 20, 50, 50, null);
				} else {
					dbg.drawImage(imgItem, x + i * 50 + 100 + i * 20, y + j * 50 + 50 + j * 20, null);

				}
				dbg.drawImage(matrizItens[i][j].getItem(), x + i * 50 + 100 + i * 20 + 5, y + j * 50 + 50 + j * 20 + 5,
						null);
			}
		}

		// System.out.println(matrizItens[posX][posY].img2);
		// matrizItens[posX][posY].DesenhaInventario(dbg, x + posX*50, y +
		// posY*50,1);

		// dbg.drawImage(pao,x+posX*50,y+posY*50,25,23,null);
		// dbg.drawImage(,x + posX*50, y + posY*50,20,20,null);
		// dbg.setColor(Color.red);
		// dbg.drawRect(x + posX*50, y + posY*50,20,20);

		jd.DesenhaSe(dbg, 50, 50);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {

			if (jd.janelaDialogoAtiva == false) {
				boolean a = true;
				if (posX > 0) {

					posX--;
					a = false;
				}
				if (posX == 0 && a == true) {

					posX = capacidadeX - 1;
					// posY --;;

				}
			} else {
				jd.add = !jd.add;
			}
		}
		if (keyCode == KeyEvent.VK_RIGHT) {

			if (jd.janelaDialogoAtiva == false) {
				RIGHT = true;
				if (posX < capacidadeX) {

					posX++;

				}
				if (posX == capacidadeX) {

					posX = 0;
					// posY ++;

				}
			} else {
				jd.add = !jd.add;
			}
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			// RIGHT = true;
			if (posY < capacidadeY) {

				posY++;

			}
			if (posY == capacidadeY) {

				// posX = 0;
				posY = 0;

			}
		}
		if (keyCode == KeyEvent.VK_UP) {

			boolean a = true;
			if (posY > 0) {

				posY--;
				a = false;

			}

			if (posY == 0 && a == true) {

				// posX = 0;
				posY = capacidadeY - 1;

			}

		}
		if (keyCode == KeyEvent.VK_ENTER) {
			jd.janelaDialogoAtiva = false;
			CanvasGame2.salas.get(CanvasGame2.controlaSalas).inimigos.remove(pai);
			GamePanel.canvasAtivo = CanvasGame2.instance;

		}
		if (keyCode == KeyEvent.VK_SPACE) {

			cont++;
			System.out.println(matrizItens[posX][posY].getName());
			if (matrizItens[posX][posY].getName() != null) {
				JanelaDialogo2.dialogoAtivo = matrizItens[posX][posY].getName();

				if (jd.add) {

					if (cont == 2) {
						System.out.println("entrou");
						CanvasInventario.instance.atualizaItens(matrizItens[posX][posY]);
						matrizItens[posX][posY] = new Item(null, 0, 0,0, null,-1);
						cont = 0;
					}

					jd.janelaDialogoAtiva = !jd.janelaDialogoAtiva;

				} else {
					cont = 1;
					jd.janelaDialogoAtiva = !jd.janelaDialogoAtiva;
				}

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			LEFT = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			RIGHT = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
