import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class JanelaDialogo2 {

	public static String dialogoAtivo;
	boolean janelaDialogoAtiva;
	int linha = 0;
	BufferedImage fundo;
	BufferedImage botaoSel;
	BufferedImage botao;
	int charx,chary;
	int x,y;
	String text;
	String text2;
	boolean add = false;
	int xAwser = 50;
	int yAwser = 50;

	public JanelaDialogo2(String text,String text2,boolean janelaDialogoAtiva,int x,int y, int charx, int chary){

		//this.dialogoAtivo = abrir;
		this.text = text;
		this.text2 = text2;
		this.janelaDialogoAtiva = janelaDialogoAtiva;
		//this.img = GamePanel.loadImage("barraDialogo.png");
		this.x = x;
		this.y = y;
		this.charx = charx;
		this.chary = chary;
		
		fundo = LoadResources.fundoSelecao;
		botaoSel = LoadResources.botaoSel;
		botao = LoadResources.botao;

	}

	public void SimulaSe(long diftime){

		if(add){
			xAwser = 100;
		}
		else{
			xAwser = 200;
		}
		
	}


	public void DesenhaSe(Graphics2D dbg,int charxI, int charyI){

		if(janelaDialogoAtiva){

			//System.out.println("a");
			//dbg.drawImage(img, x,y,charx,chary,null);
			//dbg.setColor(new Color(25,255,0,255));
			//dbg.fillRect(x+charxI, y-charyI,charx, chary);
			dbg.drawImage(fundo,x+charxI, y-charyI,charx, chary,null);
			dbg.setColor(Color.white);

			Font f = new Font("Tahoma",Font.PLAIN,12);
			dbg.setFont(f);

			dbg.drawString(text + dialogoAtivo + text2, x+charxI,y-2);
			
			//dbg.setColor(Color.red);
			dbg.fillRect(x+xAwser, y+yAwser/2, 50, 50);
			
			dbg.drawImage(botao, x+100, y+yAwser/2, 50, 50,null);
			dbg.drawImage(botao, x+200, y+yAwser/2, 50, 50,null);
			dbg.drawImage(botaoSel, x+xAwser, y+yAwser/2, 50, 50,null);
			
			dbg.setColor(Color.white);
			dbg.drawString("Não", x+115, y+yAwser);
			dbg.drawString("Sim", x+215, y+yAwser);

			//dbg.drawString("Pressione Enter Para Sair..", 450, 560);
		}


	}

}
