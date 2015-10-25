import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Sala {

	int matrizColisao[][];
	int matrizColisao2[][];
	int matrizDest[][];
	int matrizXCol[][];
	int matrizYCol[][];
	int matrizXCol2[][];
	int matrizYCol2[][];
	int nx,ny;
	
	//double xSala;
	//double ySala;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	private int width;
	private int height;
	
	private int numTilesAcrossX;
	private int numTilesAcrossY;
	
	// drawing
	int rowOffset;
	int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	ArrayList<Inimigos> inimigos;
	ArrayList<Integer> dests;
	ArrayList<Polygon> polygons;
	ArrayList<Objetos> objetos;
	ArrayList<Portas> portas;
	Personagem p;

	BufferedImage tilesetT;
	BufferedImage tilesetI;

	Tile mapa[][];
	Tile mapa2[][];

	ArrayList<Tile>tiles2 = new ArrayList<Tile>();
	
	float x;
	float y;
	
	ArrayList<Tile> tiles = new ArrayList<Tile>();

	Point2D pt = new Point2D.Float();
	Point2D po;
	ArrayList<Point2D> ps = new ArrayList<Point2D>();
	ArrayList<Point2D> tilesSangue = new ArrayList<Point2D>();
	Point2D pSangue = new Point2D.Float();
	int cdSangue = 500;
	int contSangue = 0;
	int posSangueRemove;
	
	int posLX,posLY;
	
	boolean done = false;
	boolean visitada = false;
	
	BufferedImage fundo;
	BufferedImage luz;
	
	int sizeTile = CanvasGame2.instance.sizeTile;
	
	int xmapa;
	int ymapa;
	
	Polygon mapBounds;
	Polygon mapBounds2;
	int xs[];// = new int[6];
	int ys[];// = new int[6];
	int nps;
	
	public Sala(Personagem p,int matriz1[][],int matriz12[][],int matriz13[][],int matriz4[][],int matriz2[][],int matriz22[][],int matriz23[][],ArrayList<Inimigos>ii,ArrayList<Polygon>polygons,ArrayList<Objetos> objetos,ArrayList<Portas> portas,int nx, int ny,int xmapa,int ymapa, BufferedImage tilesetI){


		numRowsToDraw = GamePanel.PHEIGHT / sizeTile + 2;
		numColsToDraw = GamePanel.PWIDTH / sizeTile + 2;
		tween = 1;
		
		this.p = p;
		this.inimigos = ii;
		this.matrizColisao = matriz1;
		this.matrizXCol = matriz12;
		this.matrizYCol = matriz13;
		this.matrizDest = matriz4;
		this.matrizColisao2 = matriz2;
		this.matrizXCol2 = matriz22;
		this.matrizYCol2 = matriz23;
		this.polygons = polygons;
		this.nx = nx;
		this.ny = ny;
		this.xmapa = xmapa;
		this.ymapa = ymapa;
		this.tilesetI = tilesetI;	
		this.objetos = objetos;
		this.portas = portas;
		
		nps = this.polygons.get(0).npoints;
		
		xs = new int[nps];
		ys = new int[nps];
		
		if(this.polygons.isEmpty() == false){
		for(int i = 0; i < this.polygons.get(0).npoints;i++){
			
			xs[i] = this.polygons.get(0).xpoints[i]/8 + xmapa/8;
			ys[i] = this.polygons.get(0).ypoints[i]/8 + ymapa/8;
		}
		}
		
		mapBounds = new Polygon(xs, ys, nps);
		
		numTilesAcrossX = nx;
		numTilesAcrossY = ny;
		
		width = numTilesAcrossX * sizeTile;
		height = numTilesAcrossY * sizeTile;
		
		xmin = GamePanel.PWIDTH - width;
		xmax = 0;
		ymin = GamePanel.PHEIGHT - height;
		ymax = 0;
		
		mapa = new Tile[nx][ny];
		mapa2 = new Tile[nx][ny];
		
		
		for(int i = 0; i<nx;i++){
			for(int j = 0; j<ny; j++){



				tilesetT = tilesetI.getSubimage(matrizXCol[i][j]*sizeTile, matrizYCol[i][j]*sizeTile,sizeTile, sizeTile);
				mapa[i][j] = new Tile(i*sizeTile,j*sizeTile,tilesetT,matrizColisao[i][j],matrizDest[i][j]);

				if(matrizColisao2[i][j] == 1){
					tilesetT = tilesetI.getSubimage(matrizXCol2[i][j]*sizeTile, matrizYCol2[i][j]*sizeTile,sizeTile, sizeTile);	
					mapa2[i][j] = new Tile(i*sizeTile, j*sizeTile,tilesetT,matrizColisao2[i][j],matrizDest[i][j]);
					tiles.add(new Tile(i*sizeTile, j*sizeTile,tilesetT,matrizColisao2[i][j],matrizDest[i][j]));
				}
				else{

					mapa2[i][j] = new Tile(i*sizeTile, j*sizeTile,null,matrizColisao2[i][j],matrizDest[i][j]);
				}

			}
		}
		
		for(int i = 0; i < objetos.size();i++){
			
			mapa2[objetos.get(i).x/sizeTile][objetos.get(i).y/sizeTile].tipo = 1;
			
		}
		
		for(int i = 0; i < portas.size();i++){
			
			attMapa(i, false);
			attPolygon(i);
			
		}
		

//		for(int i = 0; i < portas.size();i++){
//			
//			if(portas.get(i).tipo == 0 && portas.get(i).open == false){
//				
//				mapa2[portas.get(i).x/sizeTile][portas.get(i).y/sizeTile].tipo = 1;
//				
//			}
//			else{
//				mapa2[portas.get(i).x/sizeTile][portas.get(i).y/sizeTile].tipo = 0;
//			}
//		}
		
		
		posLX = (int)p.x + p.charx/2;
		posLY = (int)p.y + p.chary/2;
		
		for(int i = 0; i < inimigos.size(); i++){
			
			inimigos.get(i).s = this;
			
		}
		
		//luz = GamePanel.loadImage("luz.png");
		//fundo = GamePanel.loadImage("LeeSin_2.jpg");
		fundo = GamePanel.loadImage("fundo2.png");
		
		
		
	}
	
	public void setPosition(double x, double y) {
		
		
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / 16;
		rowOffset = (int)-this.y / 16;
		
	}

	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	

	public void SimulaSe(long diftime){
		
		contSangue += diftime;
		
		p.SimulaSe(diftime);		
		
		posLX = (int)p.x + p.charx/2;
		posLY = (int)p.y + p.chary/2;
		
		for(int i = 0; i < inimigos.size();i++){

			inimigos.get(i).SimulaSe(diftime);

		}
		if(inimigos.isEmpty()){
			done = true;
		}

		for(int i = 0; i < objetos.size();i++){
				
			objetos.get(i).SimulaSe(diftime);
			
		}
		
		
		if(p.bleeding && contSangue > cdSangue){
			
			pSangue = new Point2D.Float();
			pSangue.setLocation((int)p.x/sizeTile, (int)p.y/sizeTile);
			
			if(tilesSangue.isEmpty()){
				
				tilesSangue.add(pSangue);
				
			}
			if(tilesSangue.contains(pSangue) == false){
				
				if(tilesSangue.size() > 20){
					
					tilesSangue.remove(0);
					
				}
				tilesSangue.add(pSangue);
				
			}
//			else{
//				
//				if(tilesSangue.size() > 20){
//					
//					tilesSangue.remove(0);
//					
//				}
//				tilesSangue.remove(pSangue);
//				tilesSangue.add(pSangue);
//				System.out.println(tilesSangue.size());
//				
//			}
			
			//System.out.println(tilesSangue.size());
			
			contSangue = 0;
			
		}

//		System.out.println(mapa2[608/16][9/16].tipo);
//		System.out.println(mapa2[608/16-1][9/16].tipo);
//		System.out.println(mapa2[608/16+1][9/16].tipo);
		

	}

	public void DesenhaSe(Graphics2D dbg){
		
		Color c2 = new Color(0,0,0,250);
		dbg.setColor(c2);
		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);

		//dbg.drawImage(fundo, 0,0,GamePanel.PWIDTH,GamePanel.PHEIGHT,null);
		
//		Color c = new Color(255,255,255,100);
//		dbg.setColor(c);
//		dbg.fillRect(0, 0, GamePanel.PWIDTH, GamePanel.PHEIGHT);
		
		
		if(p.tochaAtiva){
			p.tocha.DesenhaSeT(dbg,(int)x,(int)y);
		}
		else{
			p.tocha2.DesenhaSeT(dbg,(int)x,(int)y);
		}
		
		
		
		desenhaSala(dbg);
		
		
		dbg.setClip(CanvasGame2.instance.tela);
		
		
	}
	
	
	public void desenhaSala(Graphics2D dbg){
		
		for(int row = rowOffset;row < rowOffset + numRowsToDraw;row++) {
			
			if(row >= numTilesAcrossY) break;
			
			for(int col = colOffset;col < colOffset + numColsToDraw;col++) {
				
				if(col >= numTilesAcrossX) break;
				
				
				mapa[col][row].DesenhaSe(dbg,(int)x,(int)y);
				
				
			}
			
		}

//		for(int i = 0; i<tiles2.size();i++){
//
//			//tiles2.get(i).DesenhaSe(dbg,0,0);
//
//		}
		
		//System.out.println(tilesSangue.size());
		for(int i = 0; i < tilesSangue.size();i++){
			
			dbg.setColor(Color.red);
			dbg.fillRect((int)(tilesSangue.get(i).getX()*sizeTile+x),(int)(tilesSangue.get(i).getY()*sizeTile+y), sizeTile, sizeTile);
			
		}
		
		
		
		
		CanvasGame2.instance.getItemChest().draw(dbg, true,(int)x,(int)y);
		
		for(int i = 0; i < objetos.size();i++){
			
			objetos.get(i).DesenhaSe(dbg,(int)x, (int)y);
			
		}
		
		
		for(int i = 0; i < tiles.size();i++){
			
			if(tiles.get(i).tipo == 1){
			tiles.get(i).DesenhaSe(dbg, (int)x, (int)y);
			}
		}
		
		for(int i = 0; i < portas.size();i++){
			
			portas.get(i).DesenhaSe(dbg,(int)x, (int)y);
			
		}
		
		for(int i = 0; i < inimigos.size();i++){

			inimigos.get(i).DesenhaSe(dbg,(int)x,(int)y);

		}
		
		p.DesenhaSe(dbg,(int)x,(int)y);
		
		dbg.setColor(Color.white);

//		for(int i = 0; i < polygons.size();i++){
//
//			dbg.drawPolygon(polygons.get(i));
//
//		}
		
		
//		for(int i = 0; i < inimigos.size();i++){
//
//			inimigos.get(i).DesenhaSe(dbg,(int)x,(int)y);
//
//		}
		
		if(p.tochaAtiva){
			p.tocha.DesenhaSe(dbg,(int)x,(int)y);
		}
		else{
			p.tocha2.DesenhaSe(dbg,(int)x,(int)y);
		}
		
	}

	public void attPolygon(int i){

		
		if(portas.get(i).tipo >=5){
			if(portas.get(i).open == false){
				
				polygons.add(portas.get(i).polygon);
				
			}
			else{
				
				polygons.remove(portas.get(i).polygon);
				
			}
		}
	
	}
	
	public void attMapa(int i, boolean a){		
		
		
		if(portas.get(i).tipo == 5 || portas.get(i).tipo == 6){

			if(a){
				mapa2[portas.get(i).x/16][portas.get(i).y/16].tipo = 0;
				mapa2[portas.get(i).x/16+2][portas.get(i).y/16].tipo = 0;
				mapa2[portas.get(i).x/16+1][portas.get(i).y/16].tipo = 0;
			}
			else{
				mapa2[portas.get(i).x/16][portas.get(i).y/16].tipo = 2;
				mapa2[portas.get(i).x/16+2][portas.get(i).y/16].tipo = 2;
				mapa2[portas.get(i).x/16+1][portas.get(i).y/16].tipo = 2;
			}
		}
		else if(portas.get(i).tipo == 7 || portas.get(i).tipo == 8){
			
			if(a){
				mapa2[portas.get(i).x/16][portas.get(i).y/16].tipo = 0;
				mapa2[portas.get(i).x/16][portas.get(i).y/16+2].tipo = 0;
				mapa2[portas.get(i).x/16][portas.get(i).y/16+1].tipo = 0;
			}
			else{
				mapa2[portas.get(i).x/16][portas.get(i).y/16].tipo = 2;
				mapa2[portas.get(i).x/16][portas.get(i).y/16+2].tipo = 2;
				mapa2[portas.get(i).x/16][portas.get(i).y/16+1].tipo = 2;
			}
			
		}
		
		
		tiles.clear();
		
		for(int j = 0; j < nx;j++){
			
			for(int g = 0; g < ny; g++){
				
				if(mapa2[j][g].tipo == 1){
					
					tiles.add(new Tile(j*sizeTile, g*sizeTile,tilesetT,matrizColisao2[j][g],matrizDest[j][g]));
					
				}
				if(mapa2[j][g].tipo == 2){
					
					tiles.add(new Tile(j*sizeTile, g*sizeTile,tilesetT,2,matrizDest[j][g]));
					
				}
			}
			
		}
		
		//System.out.println(a);
		
	}
	
	public Polygon mapBounds(float tamanho){
		
		if(this.polygons.isEmpty() == false){
			for(int i = 0; i < this.polygons.get(0).npoints;i++){

				xs[i] = (int) (this.polygons.get(0).xpoints[i]/tamanho + xmapa/tamanho);
				ys[i] = (int) (this.polygons.get(0).ypoints[i]/tamanho + ymapa/tamanho);
			}
		}
		
		mapBounds2 = new Polygon(xs, ys, nps);
		
		return mapBounds2;
	}
	
	
}
