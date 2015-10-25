import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CanvasInventario implements myCanvas {

	public static CanvasInventario instance = null;

	int x = 100;
	int y = 100;
	int width = 600;
	int height = 400;
	boolean LEFT, RIGHT;
	ArrayList<Item> itens;
	int capacidadeX;
	int capacidadeY;
	int contl = 0;
	int contc = 0;
	private Item[][] matrizItens;

	public Item[][] getMatrizItens() {
		return matrizItens;
	}

	public void setMatrizItens(Item[][] matrizItens) {
		this.matrizItens = matrizItens;
	}

	int posX = 0;
	int posY = 0;
	int xDraw = 0;
	int yDraw = 0;
	BufferedImage img;
	BufferedImage imgSel;
	BufferedImage imgItem;
	JanelaDialogo2 jd;
	ArrayList<Item> itens2 = new ArrayList<Item>();
	ArrayList<Integer> aux = new ArrayList<Integer>();
	ArrayList<String> nomes = new ArrayList<String>();
	boolean continua = false;

	int cont = 0;
	int cont2 = 0;

	public CanvasInventario(int capacidadeX, int capacidadeY, ArrayList<Item> itens) {

		instance = this;
		this.capacidadeX = capacidadeX;
		this.capacidadeY = capacidadeY;
		this.itens = itens;
		matrizItens = new Item[capacidadeX][capacidadeY];
		img = GamePanel.loadImage("fundoInventario.png");
		imgSel = GamePanel.loadImage("itemSel.png");
		imgItem = GamePanel.loadImage("itemBox.png");

		jd = new JanelaDialogo2(" Você deseja usar o item ", " ?", false, x, y + 50, 300, 200);

		int a = 0;
		int b = 0;

//		for (int i = 0; i < capacidadeX * capacidadeY; i++) {
//
//			if (a == capacidadeX) {
//				a = 0;
//				b++;
//			}
//			if (i < itens.size()) {
//				int quantity = itens.get(i).getQuantity();
//
//				if (quantity > 1) {
//					matrizItens[contl][contc] = itens.get(i);
//					for (int j = 1; j <= quantity; j++) {
//						matrizItens[contl][contc].num++;
//					}
//				} else {
//					if (contc == capacidadeY) {
//						contc = 0;
//					}
//
//					matrizItens[contl][contc] = itens.get(i);
//					contl++;
//				}
//			} else {
//
//				matrizItens[a][b] = new Item(null, 0, 0, 0, null, -1);
//			}
//			a++;
//		}
//		
		for(int i = 0; i < itens.size();i++){
			
			qtdItem(itens.get(i));

		}
		
		removeItensQtdNula();
		
		for(int i = 0; i < capacidadeX * capacidadeY;i++){
			
			if(a == capacidadeX){
				
				a = 0;
				b+=1;
			}
			
			if(i < itens2.size()){
				
				
				matrizItens[a][b] = itens2.get(i);
				
			}
			else {

				matrizItens[a][b] = new Item(null, 0, 0, 0, null, -1);
			}
			
			a++;
			
		}
		
		
//		for(int i = 0; i<itens2.size();i++){
//			
//			System.out.println(itens2.get(i).getName()+" :" +itens2.get(i).getQuantity());
//			
//		}
		
	}

	@Override
	public void SimulaSe(long diftime) {
		if (jd.janelaDialogoAtiva) {
			jd.SimulaSe(diftime);
		}

	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {

		CanvasGame2.salas.get(CanvasGame2.controlaSalas).DesenhaSe(dbg);

		dbg.clipRect(x, y, width, height);
		dbg.drawImage(img, x, y, width, height, null);
		xDraw = x + posX * 50 + 100;
		yDraw = y + posY * 50 + 50;

		for (int i = 0; i < capacidadeX; i++) {
			for (int j = 0; j < capacidadeY; j++) {

				if (i == posX && j == posY) {
					dbg.drawImage(imgSel, xDraw + i * 20, yDraw + j * 20, 50, 50, null);
				} else {
					dbg.drawImage(imgItem, x + i * 50 + 100 + i * 20, y + j * 50 + 50 + j * 20, null);
				}
				if (matrizItens[i][j].getName() != null) {
					dbg.drawImage(matrizItens[i][j].getItem(), x + i * 50 + 100 + i * 20 + 5,
							y + j * 50 + 50 + j * 20 + 5, null);
					dbg.setColor(Color.red);
					dbg.drawString("" + matrizItens[i][j].getQuantity(), x + i * 50 + 100 + i * 20 + 5,
							y + j * 50 + 50 + j * 20 + 5);
					
					if(matrizItens[i][j].getName().compareTo("Key") == 0){
						
					
						dbg.setColor(Color.yellow);
						dbg.drawString("" + matrizItens[i][j].getId(), x + i * 50 + 100 + i * 20 + 5,
								y + j * 50 + 50 + j * 20 + 20);
						
					}
					
				}
			}
		}

		jd.DesenhaSe(dbg, 50, 50);
	}

	@Override
	public void keyPressed(KeyEvent e) {
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

				}
			} else {
				jd.add = !jd.add;
			}
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (posY < capacidadeY) {

				posY++;

			}
			if (posY == capacidadeY) {

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

				posY = capacidadeY - 1;

			}

		}
		if (keyCode == KeyEvent.VK_ENTER) {
			jd.janelaDialogoAtiva = false;

			GamePanel.canvasAtivo = CanvasGame2.instance;

		}
		if (keyCode == KeyEvent.VK_SPACE) {

			cont++;
			if (matrizItens[posX][posY].getName() != null) {
				JanelaDialogo2.dialogoAtivo = matrizItens[posX][posY].getName();

				if (jd.add) {

					if (cont == 2) {
						System.out.println("cont:" + cont);
						itemAction(posX, posY);
						cont = 0;
					}

					jd.janelaDialogoAtiva = !jd.janelaDialogoAtiva;

				} else {
					//System.out.println("aaaaaaaaaa");
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

	public void atualizaItens(Item item) {

		int a = 0;
		int b = 0;
		boolean add = false;
		boolean add2 = false;

		for (int i = 0; i < capacidadeX * capacidadeY; i++) {

			if (a == capacidadeX) {
				a = 0;
				b++;
			}

			if (add == false) {
				
				if(matrizItens[a][b].getName() != null){
				if (matrizItens[a][b].getName().compareTo(item.getName()) == 0 && matrizItens[a][b].getQuantity() < matrizItens[a][b].numMax ) {
				
					matrizItens[a][b].addQuantity(1);
					add = true;
					break;
				}
			}
			}
			a++;
		}

		if (add == false) {
	
			int aa = 0;
			int bb = 0;

			for (int j = 0; j < capacidadeX * capacidadeY; j++) {

				if (aa == capacidadeX) {
					aa = 0;
					bb++;
				}

				if (add2 == false) {
					if (matrizItens[aa][bb].getName() == null) {
						item.addQuantity(1);
						matrizItens[aa][bb] = item;
						add2 = true;
					}
				}
				aa++;
			}
		}
	}

	public void atualizaItens2(Item item) {

		int a = 0;
		int b = 0;
		boolean add = false;
		boolean add2 = false;

		for (int i = 0; i < capacidadeX * capacidadeY; i++) {

			if (a == capacidadeX) {
				a = 0;
				b++;
			}

			if (add == false) {
				if (matrizItens[a][b].getName() == item.getName() && matrizItens[a][b].getQuantity() < matrizItens[a][b].numMax ) {
					matrizItens[a][b].num++;
					add = true;
					break;
				}
			}
			a++;
		}

		if (add == false) {

			int aa = 0;
			int bb = 0;

			for (int j = 0; j < capacidadeX * capacidadeY; j++) {

				if (aa == capacidadeX) {
					aa = 0;
					bb++;
				}

				if (add2 == false) {
					if (matrizItens[aa][bb].getName() == null) {
						item.num++;
						matrizItens[aa][bb] = item;
						add2 = true;
					}
				}
				aa++;
			}
		}
	}
	
	
	public void removeItens(Item item, int x, int y) {

		matrizItens[x][y] = new Item(null, 0, 0, 0, null, -1);
	}
	
	public void qtdItem(Item item){

		int qtd = 0;
		
		boolean add = false;

		if(nomes.isEmpty()){

			nomes.add(0,item.getName());

			itens2.add(0,new Item(item.getItem(), item.getX(), item.getY(), item.getQuantity(), item.getName(), item.getId()));

			add = true;
			
			for(int j = 0; j < itens.size();j++){

				if(itens.get(j).getName() == item.getName()){

					qtd += item.getQuantity();

				}

			}

		}
		else if(nomes.get(0) != item.getName()){

			add = true;
			
			nomes.add(0,item.getName());

			itens2.add(0,new Item(item.getItem(), item.getX(), item.getY(), item.getQuantity(), item.getName(), item.getId()));

			for(int j = 0; j < itens.size();j++){

				if(itens.get(j).getName() == item.getName()){

			
					qtd += item.getQuantity();

				}

			}

		}	

		
		if(add){
			
				itens2.remove(0);

				int numDivMax = 0;
				int numModMax = 0;
				if (qtd != 0 && item.numMax != 0) {
					numDivMax = qtd / item.numMax;
					numModMax = qtd % item.numMax;
				}

				for (int i = 0; i <= numDivMax; i++) {

					if (i < numDivMax) {

						itens2.add(0,new Item(item.getItem(), item.getX(), item.getY(), item.getQuantity(), item.getName(), item.getId()));
						itens2.get(0).setQuantity(item.numMax);
					} else {
						itens2.add(0,new Item(item.getItem(), item.getX(), item.getY(), item.getQuantity(), item.getName(), item.getId()));
						itens2.get(0).setQuantity(numModMax);
					}
				}
				//itens2.get(0).setQuantity(qtd);
			}	
	}

	public void removeItensQtdNula(){
		
		for(int i = 0; i < itens2.size();i++){
			
			if(itens2.get(i).getQuantity() == 0){
				
				itens2.remove(i);
				
			}
			
		}
		
		
	}
	
	public void itemAction(int posx, int posy) {

		switch (matrizItens[posx][posy].getName()) {
		case "Potion":
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.addHp(50);

			break;
		case "Small Potion":
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.addHp(25);

			break;
		case "Meat":
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.addHp(25);

			break;
		case "Arrow":
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.numFlechas += 1;

			break;
		case "Torch":
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.tocha.t = 15;
			CanvasGame2.player.tochaAtiva = true;

			break;
		case "Scroll":
			/**
			 * The player is in save point? if yes he can save, else send a
			 * feedback message
			 */
			if (matrizItens[posx][posy].num > 0) {
				matrizItens[posx][posy].num--;
			} else {
				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
			}
			CanvasGame2.player.saveGame();
			break;
		case "Key":

//			for (int i = 0; i < CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.size(); i++) {
//				System.out.println("id porta" + CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).idKey);
//				System.out.println("id chave" + matrizItens[posx][posy].getId());
//
//				System.out.println(matrizItens[posx][posy]
//						.getId() == CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).idKey);
//
//				if (matrizItens[posx][posy]
//						.getId() == CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).idKey) {
//
//					CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(i).lock = false;
//				}
//
//			}
//
//			if (matrizItens[posx][posy].num > 0) {
//				matrizItens[posx][posy].num--;
//			} else {
//				matrizItens[posx][posy] = new Item(null, 0, 0, 0, null, -1);
//			}
			break;
		case "Dagger":
			CanvasGame2.player.setMeleeWeapon(new MeleeWeapon(matrizItens[posx][posy].getItem(), 10));

			break;
		default:
			break;
		}

	}
	
	public boolean hasKey(int id){
		
		//boolean has = false;
		
		int a = 0;
		int b = 0;

		for (int i = 0; i < capacidadeX * capacidadeY; i++) {

			if (a == capacidadeX) {
				a = 0;
				b++;
			}
			
			if(matrizItens[a][b].getId() == id){
				
				matrizItens[a][b] =  new Item(null, 0, 0, 0, null, -1);
				
				return true;
				
			}
			
			a++;
			
		}
		
		return false;
		
	}
	
}
