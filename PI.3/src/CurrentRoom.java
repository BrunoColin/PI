
public class CurrentRoom {

	
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	int width;
	int height;
	
	int numTilesAcrossX;
    int numTilesAcrossY;
	
	float x;
	float y;
	
	// drawing
	int rowOffset;
	int colOffset;
    int numRowsToDraw;
	int numColsToDraw;
	
	Sala s;
	
	public CurrentRoom(Sala s){
		
		numRowsToDraw = GamePanel.PHEIGHT / CanvasGame2.instance.sizeTile + 2;
		numColsToDraw = GamePanel.PWIDTH / CanvasGame2.instance.sizeTile + 2;
		tween = 1;
		
		numTilesAcrossX = s.nx;
		numTilesAcrossY = s.ny;
		
		width = numTilesAcrossX * CanvasGame2.instance.sizeTile;
		height = numTilesAcrossY * CanvasGame2.instance.sizeTile;
		
		xmin = GamePanel.PWIDTH - width;
		xmax = 0;
		ymin = GamePanel.PHEIGHT - height;
		ymax = 0;
		
	}
	
	


	public void setPosition(double x, double y) {


		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;

		fixBounds();

		colOffset = (int)-this.x / CanvasGame2.instance.sizeTile;
		rowOffset = (int)-this.y / CanvasGame2.instance.sizeTile;

	}

	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}


}
