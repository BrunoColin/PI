import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Portas {

	int x,y;
	int charx,chary;
	int tipo;
	BufferedImage img;
	boolean sel = false;
	int destS;
	int destP;
	boolean lock;
	boolean open = false;
	int idKey;
	Polygon polygon;
	int xs[] = new int[4];
	int ys[] = new int[4];
	JanelaDialogo jD;
	String abrir = "[E]";
	
	public Portas(int x, int y,BufferedImage img, int charx, int chary, int tipo, int destS, int destP,int idKey){
		
		this.x = x;
		this.y = y;
		this.img = img;
		this.charx = charx;
		this.chary = chary;
		this.tipo = tipo;
		this.destS = destS;
		this.destP = destP;
		this.idKey = idKey;
		lock = true;
		jD = new JanelaDialogo(abrir, false, x, y, 20, 20);

		if(tipo == 5 || tipo == 6){
			xs[0] = x-4;
			xs[1] = x-4;
			xs[2] = x+charx+4;
			xs[3] = x+charx+4;

			ys[0] = y;
			ys[1] = y+chary;
			ys[2] = y+chary;
			ys[3] = y;
		}
		else if(tipo == 7 || tipo == 8){
		
			xs[0] = x;
			xs[1] = x;
			xs[2] = x+charx;
			xs[3] = x+charx;
			
			ys[0] = y-4;
			ys[1] = y+chary+4;
			ys[2] = y+chary+4;
			ys[3] = y-4;
			
		}
		else{
			xs[0] = x;
			xs[1] = x;
			xs[2] = x+charx;
			xs[3] = x+charx;
			
			ys[0] = y;
			ys[1] = y+chary;
			ys[2] = y+chary;
			ys[3] = y;
		}
		polygon = new Polygon(xs, ys, 4);
		
	}
	
	public void SimulaSe(long diftime){

		if(jD.janelaDialogoAtiva){
			
			jD.SimulaSe(diftime);
			
		}

	}

	public void DesenhaSe(Graphics2D dbg,int xMundo, int yMundo){

		
		if(open == false){
			dbg.drawImage(img,x+xMundo,y+yMundo,charx,chary,null);
		}
		
		//dbg.drawRect(x-10+xMundo, y-10+yMundo,charx+20, chary+20);

		
		xs[0] = x+xMundo;
		xs[1] = x+xMundo;
		xs[2] = x+charx+xMundo;
		xs[3] = x+charx+xMundo;
		
		ys[0] = y+yMundo;
		ys[1] = y+chary+yMundo;
		ys[2] = y+chary+yMundo;
		ys[3] = y+yMundo;
		
		
		dbg.drawPolygon(xs,ys,4);
		
		jD.DesenhaSe(dbg, xMundo, yMundo, 20, 20,abrir);
		
	}

	public boolean colisao(int MouseX, int MouseY){

		Rectangle rect = new Rectangle((int)x,(int)y,charx,chary);
		Rectangle rect2 =new Rectangle(MouseX,MouseY,1,1);

		if(rect.intersects(rect2)){
			return true; 
		}
		else{
			return false;
		}

	}
	
	public boolean colisao(Portas t){
		
		Rectangle rect = new Rectangle((int)x,(int)y,charx,chary);
		Rectangle rect2 =new Rectangle((int)t.x,(int)t.y,t.charx,t.chary);
	
	    if(rect.intersects(rect2)){
	    	return true; 
	    }
        else{
        	return false;
        }

    }
	
}
