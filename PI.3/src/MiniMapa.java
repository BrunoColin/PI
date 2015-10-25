import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class MiniMapa {
	
	
	int x,y;
	int charx,chary;
	int cdAtt = 1000;
	int contAtt;
	
	Sala salaAtual;
	Color c;
	boolean desenha = true;
	
	int xD,yD;
	int xP,yP;
	
	
	public MiniMapa(Sala salaAtual){
		
		this.salaAtual = salaAtual;
		
		charx = GamePanel.PWIDTH/5;
		chary = GamePanel.PHEIGHT/5;
		x = GamePanel.PWIDTH-charx-20;
		y = GamePanel.PHEIGHT-chary-20;
		
		//System.out.println("taffarel "+this.salaAtual.inimigos.get(1).x);
		//System.out.println(CanvasGame2.salas.get(CanvasGame2.controlaSalas).inimigos.get(0).x);
	}
	
	public void SimulaSe(long diftime){
		
		//salaAtual.SimulaSe(diftime);
		contAtt += diftime;
		
		if(contAtt > cdAtt){
			
			desenha =! desenha;
			contAtt = 0;
		}
	}
	
	
	public void DesenhaSe(Graphics2D dbg){
		
		
		c = new Color(0,255,0,150);
		dbg.setColor(c);
		dbg.fillRect(x, y, charx, chary);
		

		xP = (int)(charx * salaAtual.p.x)/GamePanel.PWIDTH;
		yP = (int)(chary * salaAtual.p.y)/GamePanel.PHEIGHT;
		
		c = Color.black;
		dbg.setColor(c);
		
		for(int i = 0; i < salaAtual.tiles.size();i++){
			//System.out.println("a"+salaAtual.tiles.get(i).x);
			
			xD = (int)(charx*salaAtual.tiles.get(i).x)/GamePanel.PWIDTH;
			yD = (int)(chary*salaAtual.tiles.get(i).y)/GamePanel.PHEIGHT;
			
			dbg.fillRect(x + xD, y + yD, 3, 3);
			
		}
		
		
		c = Color.red;
		dbg.setColor(c);
		
		
		for(int i = 0; i < salaAtual.inimigos.size();i++){
			
			if(desenha){
				xD = (int)(charx*salaAtual.inimigos.get(i).posX())/GamePanel.PWIDTH;
				yD = (int)(chary*salaAtual.inimigos.get(i).posY())/GamePanel.PHEIGHT;
				
				
				int distx = xP - xD;
				int disty = yP - yD;
				
				int dist = distx*distx + disty*disty;
				
				if(dist< 5000){
					
					dbg.fillRect(x+xD,y+yD,2,2);
					
				}
				
				
			}
		}
		
		dbg.setColor(Color.YELLOW);
		
		dbg.fillRect(x+xP,y+yP,2,2);
		
		
//		width = x
//	    charx = xD
//	    
//	    charx * x = width * xd
//	    xd = charx * x /width
		
		
	}
	
}
