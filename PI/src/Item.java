import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Item {

	private BufferedImage item;

	private int x;
	private int y;
	private String name;
	private int id;
	private int quantity;
	int num = 0;
	int numMax;
	private String description;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(int num){
		
		this.quantity += num;
		
	}

	public Item(BufferedImage item) {
		this.item = item;
	}

	public Item(BufferedImage item, int x, int y) {
		this.item = item;
		this.x = x;
		this.y = y;
	}

	public Item(BufferedImage item, int x, int y, int qt, String itemName, int id) {
		this.item = item;
		this.x = x;
		this.y = y;
		this.name = itemName;
		this.id = id;
		this.quantity = qt;

		if (itemName == "Arrow" || itemName == "Flaming arrow") {

			numMax = 10;

		} else if (itemName == "Key") {
			numMax = 1;
		}
		else{
			numMax = 3;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getItem() {
		return item;
	}

	public void setItem(BufferedImage item) {
		this.item = item;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Rectangle getRectangle() {
		Rectangle rectangle = new Rectangle(this.x, this.y, item.getWidth(), item.getHeight());
		return rectangle;
	}

	public void drawItem(Graphics2D g2d) {
		g2d.drawImage(item, x, y, null);
	}

	public void drawItem(Graphics2D g2d, int x, int y) {
		g2d.drawImage(item, x, y, null);
	}

	public void drawItem(Graphics2D g2d, ArrayList<Item> items) {
		for (Item item : items) {
			g2d.drawImage(item.item, item.x, item.y, null);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
