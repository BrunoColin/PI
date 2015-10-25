import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class CanvasPorta implements myCanvas {

	public static CanvasPorta instance = null;
	
	int x = 100;
	int y = 100;
	int width = 600;
	int height = 200;
	boolean LEFT, RIGHT,SPACE;
	
	BufferedImage img;
	BufferedImage imgSel;
	
	JanelaDialogo jd;
	String texto = "This door is locked, you need the key";
	String texto2 = "to unlock";
	String texto3 = "Do you have the key?";
	String texto4 = "You don't have the key";
	String textoAtual;
	int idAtual = 0;
	int key;
	int id;
	
	int posX = 0;
	int posY = 0;
	int xDraw = 0;
	int yDraw = 0;
	
	public CanvasPorta(int id,int key){
		
		this.id = id;
		this.key = key;
		img= GamePanel.loadImage("fundoInventario.png");
		imgSel = GamePanel.loadImage("itemSel.png");
		
	}
	
	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		
		if(LEFT){
			
			if(posX == 1){
				posX= 0;
			}
			
			LEFT = false;
		}
		
		if(RIGHT){
			
			if(posX == 0){
				posX= 1;
			}
			
			RIGHT = false;
		}
		
		if(SPACE){
		
			switch (idAtual) {
			case 0:
				idAtual = 1;
				SPACE = false;
				break;
			case 1:
				if(posX == 0){
					
					if(CanvasInventario.instance.hasKey(key)){
						
						CanvasGame2.salas.get(CanvasGame2.controlaSalas).portas.get(id).lock = false;
						
						GamePanel.canvasAtivo = CanvasGame2.instance;
						
					}
					else{
						
						idAtual = 2;
						
					}
				}
				else{
					
					idAtual = 0;
					posX = 0;
					GamePanel.canvasAtivo = CanvasGame2.instance;
					
				}
				SPACE = false;
				break;
			case 2:
				
				idAtual = 0;
				GamePanel.canvasAtivo = CanvasGame2.instance;
				SPACE = false;
				break;
			}
			
		}
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		dbg.setColor(Color.WHITE);
		dbg.fillRect(x, y, width, height);
		dbg.drawImage(img, x, y, width, height, null);
		
		dbg.setColor(Color.WHITE);
		
		switch (idAtual) {
		case 0:
			
			dbg.drawString(texto + " " + key + texto2, x+10, y+20);
			
			dbg.setColor(Color.yellow);
			dbg.drawString("next", x + 100, y + 50);
			
			break;
		case 1:
			
			dbg.drawString(texto3, x+10, y+20);
			
			if(posX == 0){
				
				dbg.setColor(Color.yellow);
				dbg.drawString("Yes", x + 100, y + 50);
				dbg.setColor(Color.white);
				dbg.drawString("No", x + 50 + 100, y + 50);
				
				
			}
			else{
				
				dbg.setColor(Color.white);
				dbg.drawString("Yes", x + 100, y + 50);
				dbg.setColor(Color.yellow);
				dbg.drawString("No", x + 50 + 100, y + 50);
				
				
			}
			
			break;
		case 2:
			
			dbg.drawString(texto4, x+10, y+20);
			
			break;
			
		}
		
		
		dbg.setColor(Color.white);
		dbg.drawString("press SPACE", x + 400, y + 150);
		
//		xDraw = x + posX * 50 + 100;
//		yDraw = y + posY * 50 + 50;
//		
//		dbg.drawImage(imgSel, xDraw, yDraw, 50, 50, null);
		
		
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			
			LEFT = true;
			
		}
		if (keyCode == KeyEvent.VK_RIGHT) {

			RIGHT = true;
			
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			
		}
		if (keyCode == KeyEvent.VK_UP) {

		}
		if (keyCode == KeyEvent.VK_ENTER) {
			//jd.janelaDialogoAtiva = false;

			GamePanel.canvasAtivo = CanvasGame2.instance;

		}
		if (keyCode == KeyEvent.VK_SPACE) {
			
			SPACE = true;
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			LEFT = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			RIGHT = false;
		}
		if (keyCode == KeyEvent.VK_SPACE) {
			SPACE = false;
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
	
//	public boolean hasKey(int id){
//		
//		//boolean has = false;
//		
//		for(int i = 0; i < CanvasInventario.instance.itens2.size();i++){
//			
//			System.out.println(id + " " +i);
//			
//			if(CanvasInventario.instance.itens2.get(i).getId() == id){
//				
//				CanvasInventario.instance.itens2.remove(i);
//				
//				return true;
//				
//			}
//			
//		}
//		
//		return false;
//		
//	}

}
