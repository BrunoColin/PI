import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Tile {

	
	int x,y;
	BufferedImage img;
	int tipo;
	int tamanho = 16;
	int dest;
	public Tile (int x, int y, BufferedImage img, int tipo,int dest){
		
		this.x = x;
		this.y = y;
		this.img = img;
		this.tipo = tipo;
		this.dest = dest;
	}
	
	
	public void DesenhaSe(Graphics2D dbg, int mapX, int mapY){
		
		dbg.drawImage(img,x+mapX,y+mapY,20,20,null);
		
	}
	
	
}
