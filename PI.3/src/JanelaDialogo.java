import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class JanelaDialogo {

	public static String dialogoAtivo;
	boolean janelaDialogoAtiva;
	int linha = 0;
	BufferedImage img;
	int charx,chary;
	int x,y;
	ArrayList<String> textos = new ArrayList<String>();
	String[] textos2;
	int idDialogo = 0;

	public JanelaDialogo(String abrir,boolean janelaDialogoAtiva,int x,int y, int charx, int chary){

		
		textos2 = abrir.split("#");
		
		for(int i = 0; i<textos2.length;i++){
			
			textos.add(textos2[i]);
			
		}
		
		this.dialogoAtivo = textos.get(idDialogo);
		this.janelaDialogoAtiva = janelaDialogoAtiva;
		this.x = x;
		this.y = y;
		this.charx = charx;
		this.chary = chary;

	}

	public void SimulaSe(long diftime){

		if(CanvasGame2.E){
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			dialogoAtivo+=1;
			CanvasGame2.SPACE = false;
		}
		
		
	}


	public void DesenhaSe(Graphics2D dbg,int xMundo,int yMundo,int charxI, int charyI, String dialogo){

		if(janelaDialogoAtiva){

			//System.out.println("a");
			//dbg.drawImage(img, x,y,charx,chary,null);
			dbg.setColor(new Color(25,255,0,255));
			dbg.fillRect(x+charxI+xMundo, y-charyI+yMundo,charx, chary);
			dbg.setColor(Color.white);

			//Font f = new Font("Tahoma",Font.PLAIN,16);
			//dbg.setFont(f);

			dbg.drawString(dialogo, x+charxI+xMundo,y+ yMundo-2);




			//dbg.drawString("Pressione Enter Para Sair..", 450, 560);
		}


	}

	public String getDialogoAtivo(int indice){
		
		return textos.get(indice);
		
	}
	
}
