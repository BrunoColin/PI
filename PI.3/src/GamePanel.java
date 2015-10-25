import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 879683480665455845L;
	public static int PWIDTH;
	public static int PHEIGHT;

	public static GamePanel instance;

	private Thread animator;
	private boolean running = false;
	private BufferedImage dbImage;
	private Graphics2D dbg;
	LoadResources loadResources;
	public static int FPS, SFPS;
	public static int fpscount;

	public static myCanvas canvasAtivo = null;

	static BufferedImage iI;
	static BufferedImage iI2;
	static BufferedImage iI3;

	public GamePanel(int PWIDTH, int PHEIGHT) {
		instance = this;
		GamePanel.PWIDTH = PWIDTH;
		GamePanel.PHEIGHT = PHEIGHT;

		setBackground(Color.white);
		setPreferredSize(new Dimension(PWIDTH, PHEIGHT));

		// create game components
		setFocusable(true);

		requestFocus(); // JPanel now receives key events

		if (dbImage == null) {
			dbImage = new BufferedImage(PWIDTH, PHEIGHT, BufferedImage.TYPE_INT_ARGB);
			if (dbImage == null) {
				System.out.println("dbImage is null");
				return;
			} else {
				dbg = (Graphics2D) dbImage.getGraphics();
			}
		}

		// Adiciona um Key Listner
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (canvasAtivo != null) {
					canvasAtivo.keyPressed(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (canvasAtivo != null) {
					canvasAtivo.keyReleased(e);
				}
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (canvasAtivo != null) {
					canvasAtivo.mouseMoved(e);
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (canvasAtivo != null) {
					canvasAtivo.mouseDragged(e);
				}
			}
		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (canvasAtivo != null) {
					canvasAtivo.mouseReleased(e);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (canvasAtivo != null) {
					canvasAtivo.mousePressed(e);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		loadResources = new LoadResources();

		canvasAtivo = new CanvasGame2(false);

	} // end of GamePanel()

	public void addNotify() {
		super.addNotify(); // creates the peer
		startGame(); // start the thread
	}

	private void startGame()
	// initialise and start the thread
	{
		if (animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	} // end of startGame()

	public void stopGame()
	// called by the user to stop execution
	{
		running = false;
	}

	public void resetGame() {
		instance = null;
		instance = new GamePanel(PWIDTH, PHEIGHT);
	}

	public void clearBackground() {
		// dbg.setBackground(Color.WHITE);
	}

	public void changeSizeScreen(int width, int height) {
		GamePanel.instance = null;
		GamePanel ttPanel = new GamePanel(width, height);
		// create a JFrame to hold the timer test JPanel

		JFrame app = new JFrame("jogo");
		app.getContentPane().add(ttPanel, BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GraphicsEnvironment e =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// Font[] fonts = e.getAllFonts(); // Get the fonts
		// for (Font f : fonts) {
		// System.out.println(f.getFontName());
		// }

		app.pack();
		app.setResizable(false);
		app.setVisible(true);
		setPreferredSize(new Dimension(width, height));

	}

	public void run()
	/* Repeatedly update, render, sleep */
	{
		running = true;

		long DifTime, TempoAnterior;

		int segundo = 0;
		DifTime = 0;
		TempoAnterior = System.currentTimeMillis();

		while (running) {

			gameUpdate(DifTime); // game state is updated
			gameRender(); // render to a buffer
			paintImmediately(0, 0, PWIDTH, PHEIGHT); // paint with the buffer

			try {
				Thread.sleep(0); // sleep a bit
			} catch (InterruptedException ex) {
			}

			DifTime = System.currentTimeMillis() - TempoAnterior;
			TempoAnterior = System.currentTimeMillis();

			if (segundo != ((int) (TempoAnterior / 1000))) {
				FPS = SFPS;
				SFPS = 1;
				segundo = ((int) (TempoAnterior / 1000));
			} else {
				SFPS++;
			}

		}
		System.exit(0); // so enclosing JFrame/JApplet exits
	} // end of run()

	int timerfps = 0;

	private void gameUpdate(long DiffTime) {
		if (canvasAtivo != null) {
			canvasAtivo.SimulaSe(DiffTime);
		}
	}

	private void gameRender()
	// draw the current frame to an image buffer
	{
		if (canvasAtivo != null) {
			canvasAtivo.DesenhaSe(dbg);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (dbImage != null)
			g.drawImage(dbImage, 0, 0, null);
	}

	public static void main(String args[]) {
		GamePanel ttPanel = new GamePanel(800, 600);

		// create a JFrame to hold the timer test JPanel
		JFrame app = new JFrame("jogo");
		app.getContentPane().add(ttPanel, BorderLayout.CENTER);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// GraphicsEnvironment e =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// Font[] fonts = e.getAllFonts(); // Get the fonts
		// for (Font f : fonts) {
		// System.out.println(f.getFontName());
		// }

		app.pack();
		app.setResizable(false);
		app.setVisible(true);
	} // end of main()

	public static BufferedImage loadImage(String source) {
		BufferedImage image = null;
		try {
			BufferedImage tmp = ImageIO.read(GamePanel.instance.getClass().getResource(source));
			image = new BufferedImage(tmp.getWidth(), tmp.getHeight(), BufferedImage.TYPE_INT_ARGB);
			image.getGraphics().drawImage(tmp, 0, 0, null);
			tmp = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static Sala leSala(String s) {

		Sala sala;

		URL s2 = GamePanel.instance.getClass().getResource(s);

		int nMatriz2 = 0;
		int nX = 0;
		int nY = 0;
		int xmapa = 0;
		int ymapa = 0;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {
				String linha = br.readLine();
				// System.out.println(linha);

				if (nMatriz2 == 0) {
					xmapa = Integer.parseInt(String.valueOf(linha));
				} else if (nMatriz2 == 1) {
					ymapa = Integer.parseInt(String.valueOf(linha));
				} else if (nMatriz2 == 2) {
					nX = Integer.parseInt(String.valueOf(linha));
				} else if (nMatriz2 == 3) {
					nY = Integer.parseInt(String.valueOf(linha));
				}

				nMatriz2 += 1;
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		int matriz1[][] = new int[nX][nY];
		int matriz12[][] = new int[nX][nY];
		int matriz13[][] = new int[nX][nY];
		int matrizD[][] = new int[nX][nY];
		int matriz21[][] = new int[nX][nY];
		int matriz22[][] = new int[nX][nY];
		int matriz23[][] = new int[nX][nY];

		int z = 0;
		int nMatriz3 = 0;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {
				String linha = br.readLine();
				// System.out.println(linha);

				if (z > nY - 1) {
					z = 0;
				}

				// String strings[] = linha.split("#");
				// int a = Integer.parseInt(strings[0]);

				String arrayValores[] = linha.split("#");
				// int[] valorInteiros = new int[arrayValores.length];

				if (nMatriz3 >= 4 && nMatriz3 < nY + 4) {
					for (int i = 0; i < arrayValores.length; i++) {

						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						matriz1[i][z] = Integer.parseInt(arrayValores[i]);

					}
				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 2 + 4) {

					for (int i = 0; i < arrayValores.length; i++) {
						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						// matriz12[i][z] = valorInteiros[i];
						matriz12[i][z] = Integer.parseInt(arrayValores[i]);
						// System.out.println(x);
					}

				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 3 + 4) {

					for (int i = 0; i < arrayValores.length; i++) {
						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						// matriz13[i][z] = valorInteiros[i];
						matriz13[i][z] = Integer.parseInt(arrayValores[i]);
						// System.out.println(x);
					}

				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 4 + 4) {

					for (int i = 0; i < arrayValores.length; i++) {
						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						// matrizD[i][z] = valorInteiros[i];
						matrizD[i][z] = Integer.parseInt(arrayValores[i]);
						// System.out.println(x);
					}

				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 5 + 4) {
					for (int i = 0; i < arrayValores.length; i++) {
						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						// matriz21[i][z] = valorInteiros[i];
						matriz21[i][z] = Integer.parseInt(arrayValores[i]);

					}
				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 6 + 4) {

					for (int i = 0; i < arrayValores.length; i++) {
						// valorInteiros[i] = Integer.parseInt(String
						// .valueOf(arrayValores[i]));
						// matriz22[i][z] = valorInteiros[i];
						matriz22[i][z] = Integer.parseInt(arrayValores[i]);
					}

				} else if (nMatriz3 >= 4 && nMatriz3 < nY * 7 + 4) {

					for (int i = 0; i < arrayValores.length; i++) {
						matriz23[i][z] = Integer.parseInt(arrayValores[i]);
					}

				}

				if (nMatriz3 > 3) {
					z += 1;
				}
				nMatriz3 += 1;
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		boolean loop = true;

		ArrayList<Inimigos> i = new ArrayList<Inimigos>();
		int nMatriz = -1;
		float x = 0, y = 0, xD = 0, yD = 0;
		ArrayList<Point2D> pontos = new ArrayList<Point2D>();
		ArrayList<PathsInimigos> paths = new ArrayList<PathsInimigos>();
		int tipo = 0;
		boolean aaaa = false;
		Point2D point = new Point2D.Float();

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready() && loop) {

				String linha = br.readLine();

				if (linha.contains("Enemy")) {

					linha = br.readLine();
					aaaa = true;
				}
				if (linha.contains("End")) {

					linha = br.readLine();
					aaaa = false;
				}

				if (linha.contains("Polygon")) {

					linha = br.readLine();
					aaaa = false;
					loop = false;

				}
				if (aaaa) {

					nMatriz += 1;
					pontos.clear();
					paths.clear();
					String strings[] = linha.split("@");
					String strings2[];
					//int a = Integer.parseInt(strings[0]);
					//tipo = Integer.parseInt(strings[2]);
					for(int j = 0; j < strings.length; j++){
						System.out.println("taffarellllllll");
						strings2 = strings[j].split("#");
						
						if(j == 0){
							//int a = Integer.parseInt(strings[0]);
							tipo = Integer.parseInt(strings2[1]);
							
						for(int h = 2; h < strings2.length;h+=2){
							
							if (h == 2) {
								
								x = Integer.parseInt(strings2[h]);
								y = Integer.parseInt(strings2[h + 1]);
								point = new Point2D.Float();
								point.setLocation(Integer.parseInt(strings2[h]), Integer.parseInt(strings2[h + 1]));
								pontos.add(point);
							} else {
								point = new Point2D.Float();
								point.setLocation(Integer.parseInt(strings2[h]), Integer.parseInt(strings2[h + 1]));
								pontos.add(point);
							}
							
						}
						
						}
						else{

							for(int h = 0; h < strings2.length;h+=2){

								point = new Point2D.Float();
								point.setLocation(Integer.parseInt(strings2[h]), Integer.parseInt(strings2[h + 1]));
								pontos.add(point);

							}

						}
						
						//for(int g = 0; g < pontos.size();g++){
							//
							//System.out.println(pontos.get(g).getX());
							//
						//}
						paths.add(new PathsInimigos(pontos));
						pontos = new ArrayList<Point2D>();
						
					}
//					String strings[] = linha.split("#");
//					int a = Integer.parseInt(strings[0]);
//					tipo = Integer.parseInt(strings[1]);
//					for (int j = 2; j <= a * 2; j += 2) {
//						if (j == 2) {
//
//							x = Integer.parseInt(strings[j]);
//							y = Integer.parseInt(strings[j + 1]);
//						} else {
//							point = new Point2D.Float();
//							point.setLocation(Integer.parseInt(strings[j]), Integer.parseInt(strings[j + 1]));
//							pontos.add(point);
//						}
//					}

					switch (tipo) {
					case 1:
						Inimigo ini = new Inimigo(LoadResources.inimigo1, 200, x, y, 50, 50);
//						for (int g = 0; g < pontos.size(); g++) {
//
//							ini.addPonto(pontos.get(g));
//
//						}
						ini.paths = paths;
						
						i.add(ini);
						break;
					case 2:
						Inimigo2 ini2 = new Inimigo2(LoadResources.inimigo2, 100, x, y, 50, 50);

//						for (int g = 0; g < pontos.size(); g++) {
//							ini2.addPonto(pontos.get(g));
//						}
						ini2.paths = paths;
						
						i.add(ini2);
						break;
					case 3:
						Inimigo3 ini3 = new Inimigo3(LoadResources.inimigo3, 200, x, y, 50, 50);

//						for (int g = 0; g < pontos.size(); g++) {
//
//							ini3.addPonto(pontos.get(g));
//
//						}
						ini3.paths = paths;
						i.add(ini3);
						break;
					case 4:
						Inimigo2 ini4 = new Inimigo2(LoadResources.spriteSheetHunterWalk, 100, x, y, 45, 45);

//						for (int g = 0; g < pontos.size(); g++) {
//
//							ini4.addPonto(pontos.get(g));
//
//						}
						ini4.paths = paths;
						i.add(ini4);
						break;
					}
				}
			}

			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		int nMatriz5 = -1;
		boolean aaaaa = false;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {

				String linha = br.readLine();

				if (linha.contains("Enemy")) {

					linha = br.readLine();
					aaaaa = false;
				}
				if (linha.contains("Polygon")) {

					linha = br.readLine();
					aaaaa = true;
				}
				if (linha.contains("Object")) {

					linha = br.readLine();
					aaaaa = false;
				}
				if (linha.contains("Door")) {

					linha = br.readLine();
					aaaaa = false;
				}
				if (linha.contains("End")) {

					linha = br.readLine();
					aaaaa = false;
				}

				if (aaaaa) {

					nMatriz5 += 1;
					String strings[] = linha.split("#");
					int a = Integer.parseInt(strings[0]);

					int xS[] = new int[a];
					int yS[] = new int[a];

					int pos = 0;
					for (int j = 1; j <= a * 2; j += 2) {

						xS[pos] = Integer.parseInt(strings[j]);
						yS[pos] = Integer.parseInt(strings[j + 1]);
						pos++;
					}
					polygons.add(new Polygon(xS, yS, a));
				}

			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		ArrayList<Objetos> obs = new ArrayList<Objetos>();
		int nMatriz4 = -1;
		float xo = 0, yo = 0, charx = 0, chary = 0;
		int tipoo = 0;
		boolean aaaaaa = false;
		String name;
		int id;
		ArrayList<Item> itens = new ArrayList<Item>();
		
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {

				String linha = br.readLine();

				if (linha.contains("Enemy")) {

					linha = br.readLine();
					aaaaaa = false;
				}

				if (linha.contains("Polygon")) {

					linha = br.readLine();
					aaaaaa = false;

				}
				if (linha.contains("Object")) {

					linha = br.readLine();
					aaaaaa = true;
				}
				if (linha.contains("Door")) {

					linha = br.readLine();
					aaaaaa = false;
				}
				if (linha.contains("End")) {

					linha = br.readLine();
					aaaaaa = false;
				}

				if (aaaaaa) {

					nMatriz4 += 1;
					itens.clear();
					String strings[] = linha.split("@");
					String strings2[];
					for(int h = 0; h < strings.length;h++){

						if(h == 0){

							strings2 = strings[h].split("#");

							tipoo = Integer.parseInt(strings2[0]);
							xo = Integer.parseInt(strings2[1]);
							yo = Integer.parseInt(strings2[2]);
							charx = Integer.parseInt(strings2[3]);
							chary = Integer.parseInt(strings2[4]);

						}
						else{

							strings2 = strings[h].split("#");

							name = strings2[0];
							id = Integer.parseInt(strings2[1]);

							switch (name) {
							case "Scroll":

								itens.add(new Item(Resources.scroll, 0, 0, 0, name, id));

								break;
							case "Big Potion":

								itens.add(new Item(Resources.bigPotion, 0, 0, 0, name, id));

								break;
							case "Potion":

								itens.add(new Item(Resources.potion, 0, 0, 0, name, id));

								break;
							case "Small Potion":

								itens.add(new Item(Resources.smallPotion, 0, 0, 0, name, id));

								break;
							case "Arrow":

								itens.add(new Item(Resources.arrow, 0, 0, 0, name, id));

								break;
							case "Flame Arrow":

								itens.add(new Item(Resources.flamingArrow, 0, 0, 0, name, id));

								break;
							case "Bow":

								itens.add(new Item(Resources.bow, 0, 0, 0, name, id));

								break;
							case "Sword":

								itens.add(new Item(Resources.sword, 0, 0, 0, name, id));

								break;
							case "Bread":

								itens.add(new Item(Resources.bread, 0, 0, 0, name, id));

								break;
							case "Meat":

								itens.add(new Item(Resources.meat, 0, 0, 0, name, id));

								break;
							case "Strawberry":

								itens.add(new Item(Resources.strawberry, 0, 0, 0, name, id));

								break;
							case "Key":

								itens.add(new Item(Resources.key, 0, 0, 0, name, id));

								break;
							case "Torch":

								itens.add(new Item(Resources.torch, 0, 0, 0, name, id));

								break;

							}

						}
					}
					
					switch (tipoo) {
		
					case 0:
						Objetos obj = new Objetos((int) xo, (int) yo, GamePanel.loadImage("armarioHorizontal.png"),
								(int) charx, (int) chary, tipoo,itens);
						//obj.AddItens(itens);
						obs.add(obj);
						break;
					case 1:
						Objetos obj2 = new Objetos((int) xo, (int) yo, GamePanel.loadImage("armarioVertical.png"),
								(int) charx, (int) chary, tipoo,itens);
						//obj2.AddItens(itens);
						obs.add(obj2);
						break;
					case 2:
						Objetos obj3 = new Objetos((int) xo, (int) yo, GamePanel.loadImage("comoda.png"),
								(int) charx, (int) chary, tipoo,itens);
						//obj3.AddItens(itens);
						obs.add(obj3);
						break;
					default:
						break;
					}
				}
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		boolean loop3 = true;

		ArrayList<Portas> portas = new ArrayList<Portas>();
		int nMatriz7 = -1;
		float xp = 0, yp = 0, charxp = 0, charyp = 0;
		int destS = 0;
		int destP = 0;
		int idKey = 0;
		int tipooo = 0;
		boolean aaaaaaa = false;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {

				String linha = br.readLine();

				if (linha.contains("Enemy")) {

					linha = br.readLine();
					aaaaaaa = false;
				}

				if (linha.contains("Polygon")) {

					linha = br.readLine();
					aaaaaaa = false;

				}
				if (linha.contains("Object")) {

					linha = br.readLine();
					aaaaaaa = false;
				}
				if (linha.contains("Door")) {

					linha = br.readLine();
					aaaaaaa = true;
				}
				if (linha.contains("End")) {

					linha = br.readLine();
					aaaaaaa = false;
				}

				if (aaaaaaa) {

					String strings[] = linha.split("#");
					tipooo = Integer.parseInt(strings[0]);
					xp = Integer.parseInt(strings[1]);
					yp = Integer.parseInt(strings[2]);
					charxp = Integer.parseInt(strings[3]);
					charyp = Integer.parseInt(strings[4]);
					destS = Integer.parseInt(strings[5]);
					destP = Integer.parseInt(strings[6]);
					idKey = Integer.parseInt(strings[7]);


					if(tipooo < 5){
						portas.add(new Portas((int)xp, (int)yp, GamePanel.loadImage("porta.png"), (int)16, (int)16, tipooo, destS, destP,idKey));
					}
					
					switch (tipooo) {
					case 5:
						portas.add(new Portas((int)xp-16, (int)yp, LoadResources.portaH1, (int)48, (int)16, tipooo, destS, destP,idKey));
						break;
					case 6:
						portas.add(new Portas((int)xp-16, (int)yp, LoadResources.portaH2, (int)48, (int)16, tipooo, destS, destP,idKey));
						break;
					case 7:
						portas.add(new Portas((int)xp, (int)yp-16, LoadResources.portaV1, (int)16, (int)48, tipooo, destS, destP,idKey));
						break;
					case 8:
						portas.add(new Portas((int)xp, (int)yp-16, LoadResources.portaV2, (int)16, (int)48, tipooo, destS, destP,idKey));
						break;
					default:
						break;
					}
					
				}
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		System.out.println("xmapa : " + xmapa + " ymapa : " + ymapa);

		sala = new Sala(CanvasGame2.player, matriz1, matriz12, matriz13, matrizD, matriz21, matriz22, matriz23, i, polygons,
				obs, portas, nX, nY, xmapa, ymapa, loadImage("TilesSet4.png"));

		return sala;
	}

	public static int[][] leSala2(String s) {

		URL s2 = GamePanel.instance.getClass().getResource(s);

		int nMatriz2 = 0;
		int nX = 0;
		int nY = 0;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {
				String linha = br.readLine();
				// System.out.println(linha);

				if (nMatriz2 == 0) {
					nX = Integer.parseInt(String.valueOf(linha));
				} else if (nMatriz2 == 1) {
					nY = Integer.parseInt(String.valueOf(linha));
				}
				nMatriz2 += 1;
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		int matriz1[][] = new int[nX][nY];

		int z = 0;
		int nMatriz3 = 0;

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(s2.openStream()));

			while (br.ready()) {
				String linha = br.readLine();

				if (z > nY - 1) {
					z = 0;
				}

				char[] arrayValores = linha.toCharArray();
				int[] valorInteiros = new int[arrayValores.length];

				if (nMatriz3 >= 2) {
					for (int i = 0; i < arrayValores.length; i++) {
						valorInteiros[i] = Integer.parseInt(String.valueOf(arrayValores[i]));
						matriz1[i][z] = valorInteiros[i];
						System.out.println(valorInteiros[i]);
					}
				}

				if (nMatriz3 > 1) {
					z += 1;
				}
				nMatriz3 += 1;
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return matriz1;
	}

	public Graphics2D getDbg() {
		return dbg;
	}

	public void setDbg(Graphics2D dbg) {
		this.dbg = dbg;
	}

	public static int getPWIDTH() {
		return PWIDTH;
	}

	public static void setPWIDTH(int pWIDTH) {
		PWIDTH = pWIDTH;
	}

	public static int getPHEIGHT() {
		return PHEIGHT;
	}

	public static void setPHEIGHT(int pHEIGHT) {
		PHEIGHT = pHEIGHT;
	}

} // end of GamePanel class
