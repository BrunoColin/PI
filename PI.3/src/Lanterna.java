import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;


public class Lanterna {

	ArrayList<Double> angsRays = new ArrayList<Double>();
	ArrayList<Double> tRays = new ArrayList<Double>();
	ArrayList<Point2D> points = new ArrayList<Point2D>();
	ArrayList<Polygon> polygons;
	
	Point2D pt = new Point2D.Float();
	Point2D po;
	ArrayList<Point2D> ps = new ArrayList<Point2D>();
	BufferedImage img = GamePanel.loadImage("tocha.png");
	int charx = 16;
	int chary = 16;
	
	int x;
	int y;
	double t;
	
	int tipo;
	
	public Polygon pl = new Polygon();
	
	int temp = 10000;
	int cd = 5000;
	int cont = 0;
	int tmin =10;
	double ang;
	double angLanterna;
	
	
	public Lanterna(int x, int y,double t, ArrayList<Polygon> polygons, int tipo){
		
		this.x = x;
		this.y = y;
		this.t = t;
		this.polygons = polygons;
		this.tipo = tipo;
		
		po = new Point2D.Float();
		
	}
	
	public void SimulaSe(long diftime,int xL, int yL){
		
		if(tipo == 1){
			cont += diftime;

			if(cont>cd){

				if(t > tmin){
					t -= 0.5;
				}
				else{
					CanvasGame2.player.tochaAtiva = false;
				}

				cont = 0;
			}
		}
		
		
			
		
			x = xL;
			y = yL;

			tRays.clear();
			points.clear();
			angsRays.clear();
			
			angLanterna = Math.atan2((CanvasGame2.instance.MouseY - y- CanvasGame2.salas.get(CanvasGame2.controlaSalas).y),(CanvasGame2.instance.MouseX - x -CanvasGame2.salas.get(CanvasGame2.controlaSalas).x));
			
			po.setLocation(CanvasGame2.player.x + CanvasGame2.player.charx/2, CanvasGame2.player.y + CanvasGame2.player.chary/2);
			
			switch (CanvasGame2.player.direcao) {
			case 0:
		//		po.setLocation(CanvasGame2.p.x + CanvasGame2.p.charx/2+27, CanvasGame2.p.y + CanvasGame2.p.chary/2);
				break;
			case 1:
		//		po.setLocation(CanvasGame2.p.x + CanvasGame2.p.charx/2-23, CanvasGame2.p.y + CanvasGame2.p.chary/2);
				break;
			case 2:
		//		po.setLocation(CanvasGame2.p.x + CanvasGame2.p.charx/2, CanvasGame2.p.y + CanvasGame2.p.chary/2+25);
				break;

			case 3:
		//		po.setLocation(CanvasGame2.p.x + CanvasGame2.p.charx/2, CanvasGame2.p.y + CanvasGame2.p.chary/2-25);
				break;


			default:
				break;
			}
			
			
			points.add(po);
			
			for(int i = 0; i < polygons.size(); i++){

				for(int j =0 ; j < polygons.get(i).npoints;j++){

					
					ang = Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x);
					
					if(ang >= angLanterna-Math.toRadians(30) && ang <= angLanterna+Math.toRadians(30)){
						
						angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
						angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
						angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));
						
					}
					
					
//					switch (CanvasGame2.p.direcao) {
//					case 0:
//						
//						ang = Math.toDegrees(Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//						
//						if(ang> -135 && ang < -225 ){
//						angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
//						angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//						angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));
//						}
//
//						break;
//					case 1:
//
//						ang = Math.toDegrees(Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//
//						if(ang> -45 && ang < 45 ){
//							angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));
//						}
//
//						break;
//
//					case 2:
//
//						ang = Math.toDegrees(Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//
//						if(ang> -135 && ang < -45 ){
//							angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));
//						}
//
//						break;
//
//					case 3:
//
//						ang = Math.toDegrees(Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//
//						if(ang> 45 && ang < 135 ){
//							angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
//							angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));
//						}
//
//						break;
//
//
//					default:
//						break;
//					}
					
				}
			}

			addAngsFixos(CanvasGame2.player.direcao);

			Collections.sort(angsRays);


			ps.clear();

			for(int r = 0; r < angsRays.size();r++){	
				ps.clear();
				for(int i = 0; i < polygons.size(); i++){



					for(int j = 0 ; j < polygons.get(i).npoints;j++){
						if(j < polygons.get(i).npoints-1){

							po = RayToLineSegmentIntersection((float) x,(float) y,(float) (x + t * Math.cos(angsRays.get(r))),(float) (y +t * Math.sin(angsRays.get(r))),(float) (polygons.get(i).xpoints[j]), (float) (polygons.get(i).ypoints[j]),(float) (polygons.get(i).xpoints[j+1]),(float) (polygons.get(i).ypoints[j+1]));

						}
						else{

							po = RayToLineSegmentIntersection((float) x,(float) y,(float) (x + t * Math.cos(angsRays.get(r))),(float) (y +t * Math.sin(angsRays.get(r))),(float) (polygons.get(i).xpoints[j]), (float) (polygons.get(i).ypoints[j]),(float) (polygons.get(i).xpoints[0]),(float) (polygons.get(i).ypoints[0]));
						}


						ps.add(po);
					}
				}




				for(int i = 0; i < ps.size();i++){

					if(i == 0){

						pt = ps.get(i);

					}
					else if(calcDist(x, y, pt) > calcDist(x, y, ps.get(i))){

						pt = ps.get(i);

					}

				}

				points.add(pt);

				tRays.add((double) calcDist(x, y, pt));

			}
	}
	
	
	public void DesenhaSeT(Graphics2D dbg,int xMundo, int yMundo){
		
	
			int clipx[] = new int[points.size()];
			int clipy[] = new int[points.size()];
			//System.out.println(points.size());
			for(int i = 0; i < points.size();i++){



				clipx[i] = (int)(points.get(i).getX()+xMundo);
				clipy[i] = (int)(points.get(i).getY()+yMundo);

				//	System.out.println(i+" :"+clipx[i]);

			}



			pl = new Polygon(clipx, clipy, points.size());
			//dbg.fillRect(0, 0, 1000, 1000);
			

			dbg.setClip(pl);

			//Color c = new Color(255,0,255,255);
			//dbg.setColor(c);
			
			//dbg.fillPolygon(pl);
			//dbg.fillRect(200, 200, 10, 10);
		
	}
	
	public void DesenhaSe(Graphics2D dbg,int xMundo, int yMundo){
		
	
			//dbg.drawImage(img, x+xMundo, y+yMundo, charx, chary, null);

			Color c; 

			if(tipo == 1){
				c = new Color(204,204,0,80);
			}
			else{
				c = new Color(189,183,107,0);
			}
			dbg.setColor(c);

			dbg.fillPolygon(pl);
		
	}
	
	Point2D RayToLineSegmentIntersection(float x1_, float y1_, float x2_, float y2_, 
			float x3_, float y3_, float x4_, float y4_)
	{
		Point2D result = new Point2D.Float();
		result.setLocation(x1_ + t * (x2_ - x1_), y1_ + t * (y2_ - y1_));
		float r, s, d;
		// Make sure the lines aren't parallel
		if ((y2_ - y1_) / (x2_ - x1_) != (y4_ - y3_) / (x4_ - x3_))
		{
			d = (((x2_ - x1_) * (y4_ - y3_)) - (y2_ - y1_) * (x4_ - x3_));
			if (d != 0)
			{
				r = (((y1_ - y3_) * (x4_ - x3_)) - (x1_ - x3_) * (y4_ - y3_)) / d;
				s = (((y1_ - y3_) * (x2_ - x1_)) - (x1_ - x3_) * (y2_ - y1_)) / d;
				if (r >= 0)
				{
					if (s >= 0 && s <= 1)
					{
						result.setLocation(x1_ + r * (x2_ - x1_), y1_ + r * (y2_ - y1_));
					}
				}
			}
		}
		
		return result;
	}

	float calcDist(float px, float py, Point2D pt){

		float dx = (float) (pt.getX() - px);
		float dy = (float) (pt.getY() - py);
		float dist = dx*dx + dy*dy;

		return (float) Math.sqrt(dist);

	}
	
	public void addAngsFixos(int direcao){
		
		
		//angsRays.add(angLanterna-Math.toRadians(45));
		angsRays.add(angLanterna-Math.toRadians(30));
		angsRays.add(angLanterna-Math.toRadians(25));
		angsRays.add(angLanterna-Math.toRadians(20));
		angsRays.add(angLanterna-Math.toRadians(15));
		angsRays.add(angLanterna-Math.toRadians(10));
		angsRays.add(angLanterna-Math.toRadians(5));
		angsRays.add(angLanterna);
		angsRays.add(angLanterna+Math.toRadians(5));
		angsRays.add(angLanterna+Math.toRadians(10));
		angsRays.add(angLanterna+Math.toRadians(15));
		angsRays.add(angLanterna+Math.toRadians(20));
		angsRays.add(angLanterna+Math.toRadians(25));
		angsRays.add(angLanterna+Math.toRadians(30));
		//angsRays.add(angLanterna+Math.toRadians(45));
		
		
		
//		switch (direcao) {
//		case 0:
//			
//			angsRays.add(Math.toRadians(-135));
//			angsRays.add(Math.toRadians(-150));
//			angsRays.add(Math.toRadians(-165));
//			angsRays.add(Math.toRadians(-180));
//			angsRays.add(Math.toRadians(-195));
//			angsRays.add(Math.toRadians(-210));
//			angsRays.add(Math.toRadians(-225));
//			
//			break;
//		case 1:
//			
//			angsRays.add(Math.toRadians(-45));
//			angsRays.add(Math.toRadians(-30));
//			angsRays.add(Math.toRadians(-15));
//			angsRays.add(Math.toRadians(0));
//			angsRays.add(Math.toRadians(15));
//			angsRays.add(Math.toRadians(30));
//			angsRays.add(Math.toRadians(45));
//			
//			break;
//
//		case 2:
//			
//			angsRays.add(Math.toRadians(-45));
//			angsRays.add(Math.toRadians(-60));
//			angsRays.add(Math.toRadians(-75));
//			angsRays.add(Math.toRadians(-90));
//			angsRays.add(Math.toRadians(-105));
//			angsRays.add(Math.toRadians(-120));
//			angsRays.add(Math.toRadians(-135));
//			
//			break;
//
//		case 3:
//			
//			angsRays.add(Math.toRadians(45));
//			angsRays.add(Math.toRadians(60));
//			angsRays.add(Math.toRadians(75));
//			angsRays.add(Math.toRadians(90));
//			angsRays.add(Math.toRadians(105));
//			angsRays.add(Math.toRadians(120));
//			angsRays.add(Math.toRadians(135));
//			
//			break;
//
//		default:
//			break;
//		}{
//			
//		}
//		
//		angsRays.add(Math.toRadians(0));
//		angsRays.add(Math.toRadians(1));
//		angsRays.add(Math.toRadians(14));
//		angsRays.add(Math.toRadians(15));
//		angsRays.add(Math.toRadians(16));
//		angsRays.add(Math.toRadians(29));
//		angsRays.add(Math.toRadians(30));
//		angsRays.add(Math.toRadians(31));
//		angsRays.add(Math.toRadians(44));
//		angsRays.add(Math.toRadians(45));
//		angsRays.add(Math.toRadians(46));
//		angsRays.add(Math.toRadians(59));
//		angsRays.add(Math.toRadians(60));
//		angsRays.add(Math.toRadians(61));
//		angsRays.add(Math.toRadians(74));
//		angsRays.add(Math.toRadians(75));
//		angsRays.add(Math.toRadians(76));
//		angsRays.add(Math.toRadians(89));
//		angsRays.add(Math.toRadians(90));
//		angsRays.add(Math.toRadians(91));
//		angsRays.add(Math.toRadians(104));
//		angsRays.add(Math.toRadians(105));
//		angsRays.add(Math.toRadians(106));
//		angsRays.add(Math.toRadians(119));
//		angsRays.add(Math.toRadians(120));
//		angsRays.add(Math.toRadians(121));
//		angsRays.add(Math.toRadians(134));
//		angsRays.add(Math.toRadians(135));
//		angsRays.add(Math.toRadians(136));
//		angsRays.add(Math.toRadians(149));
//		angsRays.add(Math.toRadians(150));
//		angsRays.add(Math.toRadians(151));
//		angsRays.add(Math.toRadians(164));
//		angsRays.add(Math.toRadians(165));
//		angsRays.add(Math.toRadians(166));
//		angsRays.add(Math.toRadians(179));
//		angsRays.add(Math.toRadians(180));
//		//parte cima
//		angsRays.add(Math.toRadians(-0));
//		angsRays.add(Math.toRadians(-1));
//		angsRays.add(Math.toRadians(-14));
//		angsRays.add(Math.toRadians(-15));
//		angsRays.add(Math.toRadians(-16));
//		angsRays.add(Math.toRadians(-29));
//		angsRays.add(Math.toRadians(-30));
//		angsRays.add(Math.toRadians(-31));
//		angsRays.add(Math.toRadians(-44));
//		angsRays.add(Math.toRadians(-45));
//		angsRays.add(Math.toRadians(-46));
//		angsRays.add(Math.toRadians(-59));
//		angsRays.add(Math.toRadians(-60));
//		angsRays.add(Math.toRadians(-61));
//		angsRays.add(Math.toRadians(-74));
//		angsRays.add(Math.toRadians(-75));
//		angsRays.add(Math.toRadians(-76));
//		angsRays.add(Math.toRadians(-89));
//		angsRays.add(Math.toRadians(-90));
//		angsRays.add(Math.toRadians(-91));
//		angsRays.add(Math.toRadians(-104));
//		angsRays.add(Math.toRadians(-105));
//		angsRays.add(Math.toRadians(-106));
//		angsRays.add(Math.toRadians(-119));
//		angsRays.add(Math.toRadians(-120));
//		angsRays.add(Math.toRadians(-121));
//		angsRays.add(Math.toRadians(-134));
//		angsRays.add(Math.toRadians(-135));
//		angsRays.add(Math.toRadians(-136));
//		angsRays.add(Math.toRadians(-149));
//		angsRays.add(Math.toRadians(-150));
//		angsRays.add(Math.toRadians(-151));
//		angsRays.add(Math.toRadians(-164));
//		angsRays.add(Math.toRadians(-165));
//		angsRays.add(Math.toRadians(-166));
//		angsRays.add(Math.toRadians(-179));
//		angsRays.add(Math.toRadians(-180));
		
	}
	
}
