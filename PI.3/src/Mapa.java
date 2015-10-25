import java.awt.Graphics2D;


public class Mapa {

	int mapX;
	int mapY;
	int nTileX;
	int nTileY;
	int nTileTelaX;
	int nTileTelaY;
	
	Tile[][] mapa;
	
	public Mapa(int nTileX, int nTileY, int nTileTelaX, int nTileTelaY, int[][] mapa){
		
		mapX = 0;
		mapY = 0;
		this.nTileX = nTileX;
		this.nTileY = nTileY;
		this.nTileTelaX = nTileTelaX;
		this.nTileTelaY = nTileTelaY;
		
		this.mapa = new Tile[nTileX][nTileY];
		
		for(int i = 0; i < nTileX; i++){
			for(int j = 0; j < nTileY; j++){
				
				//System.out.println(mapa[i][j]);
				
				if(mapa[i][j] == 0){
					this.mapa[i][j] = new Tile(i*20, j*20, GamePanel.loadImage("t1.png"), 0,0);
				}
				if(mapa[i][j] == 1){
					this.mapa[i][j] = new Tile(i*20, j*20, GamePanel.loadImage("t2.png"), 1,0);
				}
				if(mapa[i][j] == 2){
					this.mapa[i][j] = new Tile(i*20, j*20, GamePanel.loadImage("t3.png"), 2,0);
				}
			}
		}
		
	}
	
	public void CriaMapa(){
		
	}
	
	public void Posiciona(int x, int y){
		
		int X = x / 20;
		int Y = y / 20;
		
		if(X <= 0){
			//System.out.println("aaaaaaaaaa");
			mapX = 0;
		}
		else if(X > nTileX - nTileTelaX){
			mapX = nTileX - nTileTelaX;
		}
		else{
			mapX = x/20;
		}
		
		if(Y <= 0){
			mapY = 0;
		}
		else if(Y > nTileY - nTileTelaY){
			mapY = nTileY - nTileTelaY;
		}
		else{
			mapY = y/20;
		}
		
		//System.out.println("x"+x);
		//System.out.println("y"+y);
		//System.out.println("X"+X);
		//System.out.println("Y"+Y);
		//System.out.println("mapx"+mapX);
		//System.out.println("mapY"+mapY);
		//
	}
	
	public void DesenhaSe(Graphics2D dbg){
		
		int offx = mapX&0x0f; 
    	int offy = mapY&0x0f;
    	int somax,somay;
    	if(offx>0){
    		somax = 1;
    	}else{
    		somax = 0;
    	}
    	if(offy>0){
    		somay = 1;
    	}else{
    		somay = 0;
    	}
        for(int i = mapX; i < nTileTelaX + mapX; i++){            
            for(int j = mapY; j < nTileTelaY + mapY; j++){
            	
                mapa[i][j].DesenhaSe(dbg,mapX,mapY);     
            	
            }
        }
       
		
	}
	
}
