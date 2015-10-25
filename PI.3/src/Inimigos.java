import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Inimigos {

	public static float x,y;
	public int charx,chary;
	public int raio,vida;
	public boolean desenhaDano = false;
	public int danoRecebido;
	public BufferedImage imgMorte;
	public boolean stunado = false;
	public boolean vivo = true;
	public Sala s;
	public Object caminho;
	public static final String SEGUINDO = "Seguindo o personagem";
	public static final String ANDANDO = "Movendo-se.";
	public static final String FUGINDO = "Fugindo";
	public static final String ATACANDO = "Atacando";
	public static final String VIGIANDO = "Vigiando";
	public static final String VOLTANDO = "Voltando";
	public static final String ALERTA = "Alerta";
	public static final String CACANDO = "Caçando";
	public static final String BUSCANDO = "Buscando outro caminho";
	public String state = ANDANDO;
	public ArrayList<PathsInimigos> paths;
	public static ArrayList<TiroInimigo3> tiros;
	
	public abstract void SimulaSe(long diftime);
	public abstract void DesenhaSe(Graphics2D dbg, int xMundo, int yMundo);
	public abstract void CalculaIA(long diftime);
	public abstract void inflictDamage(int physicalDamage);
	public abstract Rectangle getRectangle();
	public abstract float posX();
	public abstract float posY();
	public abstract ArrayList<TiroInimigo3> getTiros();
	
}
