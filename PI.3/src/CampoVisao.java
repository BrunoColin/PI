import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;


public class CampoVisao {
	
	ArrayList<Double> angsRays = new ArrayList<Double>();
	ArrayList<Point2D> points = new ArrayList<Point2D>();
	ArrayList<Point2D> pointsPlayer = new ArrayList<Point2D>();
	ArrayList<Polygon> polygons;

	Point2D pt = new Point2D.Float();
	Point2D pt2 = new Point2D.Float();
	Point2D po;
	ArrayList<Point2D> ps = new ArrayList<Point2D>();
	ArrayList<Point2D> ps2 = new ArrayList<Point2D>();
	BufferedImage img = GamePanel.loadImage("tocha.png");
	int charx = 16;
	int chary = 16;

	int x;
	int y;
	double t;

	public Polygon pl = new Polygon();

	double ang;
	double angVisao;

	ArrayList<Double> angsPlayer = new ArrayList<Double>();
	
	boolean find = false;

	int clipx[];
	int clipy[];

	int clipx2[];
	int clipy2[];

	public CampoVisao(int x, int y,double t, ArrayList<Polygon> polygons){

		this.x = x;
		this.y = y;
		this.t = t;
		this.polygons = polygons;

		po = new Point2D.Float();

	}

	public void SimulaSe(long diftime,int xL, int yL, double angvisao, int charx2, int chary2){

		x = xL;
		y = yL;

		points.clear();
		angsRays.clear();
		angsPlayer.clear();
		pointsPlayer.clear();

		angVisao = angvisao ;

		//angVisao = 45;
		
		for(int i = 0; i < polygons.size(); i++){

			for(int j =0 ; j < polygons.get(i).npoints;j++){


				ang = Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x);

				if(ang >= angVisao-Math.toRadians(30) && ang <= angVisao+Math.toRadians(30)){

					angsRays.add(Math.atan2( polygons.get(i).ypoints[j] -y-0.5, polygons.get(i).xpoints[j] - x-0.5));
					angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y, polygons.get(i).xpoints[j] - x));
					angsRays.add( Math.atan2( polygons.get(i).ypoints[j] -y+0.5, polygons.get(i).xpoints[j] - x+0.5));

				}

			}
		}

		addAngsFixos(CanvasGame2.player.direcao);

		Collections.sort(angsRays);

		for(int r = 0; r < angsRays.size();r++){	

			pt = RayToLineSegmentIntersection((float) x,(float) y,(float) (x + t * Math.cos(angsRays.get(r))),(float) (y +t * Math.sin(angsRays.get(r))),0,0,0,0);

			points.add(pt);

		}

		ps.clear();

		for(int r = 0; r < angsRays.size();r++){	
			ps.clear();
			ps2.clear();
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

			for(int j = 0 ; j < CanvasGame2.player.playerPolygon.npoints;j++){
				if(j < CanvasGame2.player.playerPolygon.npoints-1){

					po = RayToLineSegmentIntersection((float) x,(float) y,(float) (x + t * Math.cos(angsRays.get(r))),(float) (y +t * Math.sin(angsRays.get(r))),(float) (CanvasGame2.player.playerPolygon.xpoints[j]), (float) (CanvasGame2.player.playerPolygon.ypoints[j]),(float) (CanvasGame2.player.playerPolygon.xpoints[j+1]),(float) (CanvasGame2.player.playerPolygon.ypoints[j+1]));

				}
				else{

					po = RayToLineSegmentIntersection((float) x,(float) y,(float) (x + t * Math.cos(angsRays.get(r))),(float) (y +t * Math.sin(angsRays.get(r))),(float) (CanvasGame2.player.playerPolygon.xpoints[j]), (float) (CanvasGame2.player.playerPolygon.ypoints[j]),(float) (CanvasGame2.player.playerPolygon.xpoints[0]),(float) (CanvasGame2.player.playerPolygon.ypoints[0]));
				}

				ps2.add(po);
			}

			for(int i = 0; i < ps.size();i++){

				if(i == 0){

					pt = ps.get(i);

				}
				else if(calcDist(x, y, pt) > calcDist(x, y, ps.get(i))){

					pt = ps.get(i);

				}

			}

			pointsPlayer.add(pt);

			for(int i = 0; i < ps2.size();i++){

				if(i == 0){

					pt2 = ps2.get(i);

				}
				else if(calcDist(x, y, pt2) > calcDist(x, y, ps2.get(i))){

					pt2 = ps2.get(i);

				}

			}

			if(calcDist(x, y, pt) > calcDist(x, y, pt2)){

				find = true;
				break;

			}
			else{
				find = false;
			}

		}

		po.setLocation(charx2, chary2);
		points.add(po);
		pointsPlayer.add(po);

		//System.out.println(find);

	}

	public void DesenhaSe(Graphics2D dbg,int xMundo, int yMundo,Color c){

		clipx = new int[points.size()];
		clipy = new int[points.size()];

		for(int i = 0; i < points.size();i++){

			clipx[i] = (int)(points.get(i).getX()+xMundo);
			clipy[i] = (int)(points.get(i).getY()+yMundo);

		}

		pl = new Polygon(clipx, clipy, points.size());


//		if(find == true){
//			c = new Color(255,0,0,125);
//		}
//		else{
//			c = new Color(0,255,0,125);
//		}
		dbg.setColor(c);

		dbg.fillPolygon(pl);

		clipx2 = new int[pointsPlayer.size()];
		clipy2 = new int[pointsPlayer.size()];

		for(int i = 0; i < pointsPlayer.size();i++){

			clipx2[i] = (int)(pointsPlayer.get(i).getX()+xMundo);
			clipy2[i] = (int)(pointsPlayer.get(i).getY()+yMundo);

		}

		pl = new Polygon(clipx2, clipy2, pointsPlayer.size());

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
		angsRays.add(angVisao-Math.toRadians(30));
		angsRays.add(angVisao-Math.toRadians(25));
		angsRays.add(angVisao-Math.toRadians(20));
		angsRays.add(angVisao-Math.toRadians(15));
		angsRays.add(angVisao-Math.toRadians(10));
		angsRays.add(angVisao-Math.toRadians(5));
		angsRays.add(angVisao);
		angsRays.add(angVisao+Math.toRadians(5));
		angsRays.add(angVisao+Math.toRadians(10));
		angsRays.add(angVisao+Math.toRadians(15));
		angsRays.add(angVisao+Math.toRadians(20));
		angsRays.add(angVisao+Math.toRadians(25));
		angsRays.add(angVisao+Math.toRadians(30));
		//angsRays.add(angLanterna+Math.toRadians(45));
	}
}

