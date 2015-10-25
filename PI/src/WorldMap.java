import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class WorldMap implements myCanvas{
	
	public static WorldMap instance = null;
	
	ArrayList<Sala> salas;
	float tamanho = (float) 2;
	
	public WorldMap(ArrayList<Sala> salas){
		
		instance = this;
		
		this.salas = salas;
		
	}
	
	
	@Override
	public void SimulaSe(long diftime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DesenhaSe(Graphics2D dbg) {
		// TODO Auto-generated method stub
		
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		
		Color c = new Color(50,50,255,200);
		
		
		
		for(int i = 0; i < salas.size();i++){
			
//			dbg.setColor(Color.black);
//			dbg.drawRect((int)salas.get(i).xmapa/16, (int)salas.get(i).ymapa/16, (int)salas.get(i).nx, (int)salas.get(i).ny);
			
			if( salas.get(i).visitada){
				c = new Color(50,50,255,200);
			
			}
			if( salas.get(i).visitada == false){
				c = new Color(255,255,255,200);
			
			}
			if( i == CanvasGame2.controlaSalas){
				c = new Color(50,255,50,200);
			
			}
			
			dbg.setColor(c);
//			dbg.fillRect((int)salas.get(i).xmapa/16, (int)salas.get(i).ymapa/16, (int)salas.get(i).nx, (int)salas.get(i).ny);
			if(salas.get(i).polygons.isEmpty() == false){
				
			dbg.fillPolygon(salas.get(i).mapBounds(tamanho));
			}
		}
		
		for(int i = 0; i < salas.size();i++){
			
			dbg.setColor(Color.black);
			
			for(int g = 0; g < salas.get(i).tiles.size();g++){
				dbg.fillRect((int)(salas.get(i).xmapa/tamanho+salas.get(i).tiles.get(g).x/tamanho),(int)(salas.get(i).ymapa/tamanho+salas.get(i).tiles.get(g).y/tamanho), (int)(16/tamanho),(int)(16/tamanho));
			}
			
			
			for(int j = 0; j < salas.get(i).portas.size();j++){
				
				if(salas.get(i).portas.get(j).lock){
					dbg.setColor(Color.red);
				}
				else{
					dbg.setColor(Color.green);
				}
				dbg.fillRect((int)(salas.get(i).xmapa/tamanho+salas.get(i).portas.get(j).x/tamanho), (int)(salas.get(i).ymapa/tamanho+salas.get(i).portas.get(j).y/tamanho),(int)(16/tamanho), (int)(16/tamanho));
				
			}
		}
		
		dbg.setColor(Color.yellow);
		dbg.fillRect((int)(CanvasGame2.salas.get(CanvasGame2.controlaSalas).xmapa/tamanho + CanvasGame2.player.x/tamanho),(int)(CanvasGame2.salas.get(CanvasGame2.controlaSalas).ymapa/tamanho + CanvasGame2.player.y/tamanho), (int)(16/tamanho), (int)(16/tamanho));
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_ENTER) {
		
			GamePanel.canvasAtivo = CanvasGame2.instance;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
