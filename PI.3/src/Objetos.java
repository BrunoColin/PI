import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Objetos {

	int x, y;
	int charx, chary;
	int tipo;
	BufferedImage img;
	String abrir = "[E]";
	JanelaDialogo jD;
	ArrayList<Item> itens = new ArrayList<Item>();
	int rndItem = 0;
	boolean inventarioAtivo = false;
	CanvasLoot canvasInventario;

	public Objetos(int x, int y, BufferedImage img, int charx, int chary, int tipo, ArrayList<Item> itens) {

		this.x = x;
		this.y = y;
		this.img = img;
		this.charx = charx;
		this.chary = chary;
		this.tipo = tipo;
		jD = new JanelaDialogo(abrir, false, x, y, 20, 20);
		this.itens = itens;
		
		System.out.println(charx + " teste " + chary);
		

//		for (int i = 0; i < tipo * 1 + 1; i++) {
//
//			rndItem = CanvasGame2.rnd.nextInt(12);
//
//			switch (rndItem) {
//			case 0:
//				
//				itens.add(new Item(LoadResources.bigPotion, 0, 0,1, "Poção Grande",-1));
//				
//				break;
//			case 1:
//				
//				itens.add(new Item(LoadResources.potion, 0, 0,1, "Poção",-1));
//				
//				break;
//			case 2:
//	
//				itens.add(new Item(LoadResources.smallPotion, 0, 0,1, "Poção Pequena",-1));
//				
//				break;
//			case 3:
//				
//				itens.add(new Item(LoadResources.arrow, 0, 0,1, "Flecha",-1));
//				
//				break;
//			case 4:
//				
//				itens.add(new Item(LoadResources.flamingArrow, 0, 0,1, "Flecha de Fogo",-1));
//				
//				break;
//			case 5:
//				
//				itens.add(new Item(LoadResources.bow, 0, 0,1, "Arco",-1));
//				
//				break;
//			case 6:
//				
//				itens.add(new Item(LoadResources.sword, 0, 0,1, "Espada",-1));
//				
//				break;
//			case 7:
//				
//				itens.add(new Item(LoadResources.bread, 0, 0,1, "Pão",-1));
//				
//				break;
//			case 8:
//				
//				itens.add(new Item(LoadResources.strawberry, 0, 0,1, "Morango",-1));
//				
//				break;
//			case 9:
//				
//				itens.add(new Item(LoadResources.meat, 0, 0,1, "Carne",-1));
//				
//				break;
//			case 10:
//				
//				itens.add(new Item(LoadResources.torch, 0, 0,1, "Tocha",-1));
//				
//				break;
//			}
//
//		}
		
		//correto
		//canvasInventario = new CanvasLoot(tipo + 1, 1, this.itens, null);
		//teste
		canvasInventario = new CanvasLoot(this.itens.size(), 1, this.itens, null);

	}

	public void SimulaSe(long diftime) {

	}

	public void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo) {
		dbg.drawImage(img, x + xMundo, y + yMundo, charx, chary, null);

		//dbg.drawRect(x+xMundo, y+yMundo, charx,chary);
		
		jD.DesenhaSe(dbg, xMundo, yMundo, charx, chary,abrir);
	}

	public void AddItens(ArrayList<Item> itens2){
		
		System.out.println("dentro");
		for(int i = 0; i < itens2.size();i++){
			
			this.itens.add(itens2.get(i));
			
			System.out.println(itens.get(i).getName());
		}
		
		
	}
	
}
