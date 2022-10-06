package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import question.Questions;
import tile.TileManager;

public class Base extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public final int tilePixel = 16;
	public final int tileFactor = 3;
	public int tileSize = tilePixel*tileFactor;
	
	public int maxScreenCol = 16;
	public int maxScreenRow = 16;
	public int screenWidth = tileSize*maxScreenCol;
	public int screenHeight = tileSize*maxScreenRow;
	
	public final int maxWorldCol = 32;
	public final int maxWorldRow = 32;
	public final int worldWidth = tileSize*maxWorldCol;
	public final int worldHeight = tileSize*maxWorldRow;
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	public final int maxMap = 10;
	public int currentMap = 0;
	
	public double level = 0;
	
	
	int FPS = 60;
	
	
	TileManager tileM = new TileManager(this);
	Key key = new Key(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Event event = new Event(this);
	public Questions quest = new Questions(this);
	public Scanner scan = new Scanner(System.in);
	Config config = new Config(this);
	Thread gameThread;
	
	public Player player = new Player(this, key);
	public Entity obj[][] = new Entity[maxMap][100];
	public Entity npc[][] = new Entity[maxMap][10];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionState = 5;
	public final int gameOverState = 6;
	public final int gameClearState = 7;
	public final int questionState = 8;
	
	
	public Base() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		stopMusic();
		gameState = titleState;
		
		//tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		//g2 = (Graphics2D)tempScreen.getGraphics();
		
		//setFullScreen();
	}
	
	public void setFullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(Main.window);
		
		//screenWidth2 = Main.window.getWidth();
		//screenHeight2 = Main.window.getHeight();
	}
	
	public void retry() {
		player.setDefaultPosition();
		aSetter.setNPC();
		aSetter.setObject();
	}
	
	public void restart() {
		player.setDefaultValue();
		player.setDefaultPosition();
		aSetter.setNPC();
		aSetter.setObject();
	}
	
	/*
	public void zoomInOut(int i) {
		int oldWorldWidth = tileSize*maxWorldCol;
		tileSize += i;
		int newWorldWidth = tileSize*maxWorldCol;
		
		player.speed = player.speedFactor*((double)newWorldWidth/600)*tileFactor;
		
		double multiplier = (double)newWorldWidth/oldWorldWidth;
		
		double newPlayerWorldX = player.worldX*multiplier;
		double newPlayerWorldY = player.worldY*multiplier;
		
		player.worldX = newPlayerWorldX;
		player.worldY = newPlayerWorldY;
	}
	*/
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				//drawToTempScreen();
				//drawToScreen();
				delta--;
			}
		}
	}
	
	public void update() {
		if (gameState == playState) {
			player.update();
			
			for (int i = 0; i < npc.length; i++) {
				if (npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
		}
		if (gameState == pauseState) {
			
		}
		//System.out.println(gameState);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		long drawStart = 0;
		
		if (key.showDebugText == true) {drawStart = System.nanoTime();}
		
		if (gameState == titleState) {ui.draw(g2);}
		
		else {
			tileM.draw(g2);
			
			entityList.add(player);
			
			for (int i = 0; i < npc.length; i++) {
				if (npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			
			for (int i = 0; i < obj.length; i++) {
				if (obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}
			}
			
			Collections.sort(entityList, new Comparator<Entity>() {
				@Override
				public int compare(Entity o1, Entity o2) {
					int result = Integer.compare(o1.worldX, o2.worldY);
					return result;
				}
			}
			);
			
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			
			for (int i = 0; i < entityList.size(); i++) {
				entityList.remove(i);
			}
			
			ui.draw(g2);
		}
		
		if (key.showDebugText == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.setColor(Color.blue);
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
			
			g2.drawString("Draw Time " + passed, x, y); y += lineHeight;
			
			g2.drawString("Map Index " + currentMap, x, y);// y += lineHeight;
		}
		g2.dispose();
	}
	
	public void drawToScreen() {
		Graphics g = getGraphics();
		g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
		g2.dispose();
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}
