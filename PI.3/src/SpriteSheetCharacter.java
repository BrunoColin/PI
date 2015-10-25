import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheetCharacter {

	private int tileSheetWith;
	private int tileSheetHeight;
	private int frame_width;
	private int frame_height;
	private int nImagesX;
	private BufferedImage tileSheetCharacter;
	private ArrayList<BufferedImage> leftMovement;
	private ArrayList<BufferedImage> upMovement;
	private ArrayList<BufferedImage> rightMovement;
	private ArrayList<BufferedImage> downMovement;

	public SpriteSheetCharacter(BufferedImage tileSheetCharacter, int nImagesX,
			int nImagesY) {
		this.tileSheetCharacter = tileSheetCharacter;
		this.tileSheetWith = tileSheetCharacter.getWidth();
		this.tileSheetHeight = tileSheetCharacter.getHeight();
		this.nImagesX = nImagesX;
		this.frame_width = tileSheetWith / nImagesX;
		this.frame_height = tileSheetHeight / nImagesY;
		this.leftMovement = new ArrayList<BufferedImage>();
		this.downMovement = new ArrayList<BufferedImage>();
		this.rightMovement = new ArrayList<BufferedImage>();
		this.upMovement = new ArrayList<BufferedImage>();
		readDownMovement();
		readLeftMovement();
		readRightMovement();
		readUpMovement();
	}

	public void readDownMovement() {
		int x;
		for (int i = 0; i < this.nImagesX; i++) {
			x = this.frame_width * i;
			BufferedImage sprite = tileSheetCharacter.getSubimage(x, 0,
					this.frame_width, frame_height);
			this.downMovement.add(sprite);
		}
	}

	public void readLeftMovement() {
		int x;
		for (int i = 0; i < this.nImagesX; i++) {
			x = this.frame_width * i;
			BufferedImage sprite = tileSheetCharacter.getSubimage(x, frame_height,
					this.frame_width, frame_height);
			this.leftMovement.add(sprite);
		}

	}

	public void readRightMovement() {
		int x;
		for (int i = 0; i < this.nImagesX; i++) {
			x = this.frame_width * i;
			BufferedImage sprite = tileSheetCharacter.getSubimage(x,  frame_height*2,
					this.frame_width, frame_height);
			this.rightMovement.add(sprite);
		}
	}

	public void readUpMovement() {
		int x;
		for (int i = 0; i < this.nImagesX; i++) {
			x = this.frame_width * i;
			BufferedImage sprite = tileSheetCharacter.getSubimage(x, frame_height*3,
					this.frame_width, frame_height);
			this.upMovement.add(sprite);
		}
	}

	public ArrayList<BufferedImage> getLeftMovement() {
		return leftMovement;
	}

	public void setLeftMovement(ArrayList<BufferedImage> leftMovement) {
		this.leftMovement = leftMovement;
	}

	public ArrayList<BufferedImage> getUpMovement() {
		return upMovement;
	}

	public void setUpMovement(ArrayList<BufferedImage> upMovement) {
		this.upMovement = upMovement;
	}

	public ArrayList<BufferedImage> getRightMovement() {
		return rightMovement;
	}

	public void setRightMovement(ArrayList<BufferedImage> rightMovement) {
		this.rightMovement = rightMovement;
	}

	/**
	 * @return
	 */
	public ArrayList<BufferedImage> getDownMovement() {
		return downMovement;
	}

	public void setDownMovement(ArrayList<BufferedImage> downMovement) {
		this.downMovement = downMovement;
	}

	public BufferedImage getTileSheetCharacter() {
		return tileSheetCharacter;
	}

	public void setTileSheetCharacter(BufferedImage tileSheetCharacter) {
		this.tileSheetCharacter = tileSheetCharacter;
	}
}
