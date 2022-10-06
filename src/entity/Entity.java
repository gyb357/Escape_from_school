package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Base;
import main.UtilityTool;

public class Entity {
	Base base;
	
	public int worldX, worldY;
	public int speed, speedFactor;
	public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
	public String direction = "down";
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 46, 46);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public boolean moving = false;
	public int pixelCounter = 0;
	public int actionLockCounter = 0;
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	
	boolean attacking = false;
	
	public String description = "";
	
	
	public Entity(Base base) {
		this.base = base;
	}
	
	public void setAction() {
		
	}
	
	public void speak() {
		base.ui.currentDialogue = dialogues[dialogueIndex];
	}
	
	public void update() {
		setAction();
		collisionOn = false;
		//base.cChecker.checkTile(this);
		//base.cChecker.checkObject(this, false);
		//base.cChecker.checkPlayer(this);
		//base.cChecker.checkObject(this, collisionOn)
		
		int playerXpos = base.player.worldX;
		int playerYpos = base.player.worldY;
		int npcXpos = base.npc[base.currentMap][0].worldX;
		int npcYpos = base.npc[base.currentMap][0].worldY;
		
		int x = (playerXpos - npcXpos);
		int y = (playerYpos - npcYpos);
		double length;
		double directionX;
		double directionY;
		double speed;
		
		
		if (collisionOn == false) {
			length = Math.sqrt(x*x + y*y);
			directionX = x/length;
			directionY = y/length;
			speed = base.tileSize*base.level;
			
			if (length > 50) {
				worldX += directionX*speed;
				worldY += directionY*speed;
			}
			else {
				base.gameState = base.gameOverState;
				base.player.currect = 0;
				base.player.hasKey = 0;
			}
		}
		else {
			directionX = 0;
			directionY = 0;
			speed = 0;
		}
		/*
		switch (direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "right": worldX += speed; break;
		case "left": worldX -= speed;break;
		}
		*/
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		pixelCounter += speed;
		if (pixelCounter >= base.tileSize) {
			moving = false;
			pixelCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		int screenX = worldX - base.player.worldX + base.player.screenX;
		int screenY = worldY - base.player.worldY + base.player.screenY;
		
		if (base.player.screenX > base.player.worldX) {screenX = worldX;}
		if (base.player.screenY > base.player.worldY) {screenY = worldY;}
		
		
		int rightOffset = base.screenWidth - base.player.screenX;
		int bottomOffset = base.screenHeight - base.player.screenY;
		if (rightOffset > base.worldWidth - base.player.worldX) {
			screenX = base.screenWidth - (base.worldWidth - worldX);
		}
		if (bottomOffset > base.worldHeight - base.player.worldY) {
			screenY = base.screenHeight - (base.worldHeight - worldY);
		}
		
		/*
		switch (direction) {
		case "up":
			if (spriteNum == 1) {image = up1;}
			if (spriteNum == 2) {image = up2;}
			break;
		case "down":
			if (spriteNum == 1) {image = down1;}
			if (spriteNum == 2) {image = down2;}
			break;
		case "right":
			if (spriteNum == 1) {image = right1;}
			if (spriteNum == 2) {image = right2;}
			break;
		case "left":
			if (spriteNum == 1) {image = left1;}
			if (spriteNum == 2) {image = left2;}
			break;
		}
		*/
		image = down1;
		
		if (worldX + base.tileSize > base.player.worldX - base.player.screenX && 
			worldX - base.tileSize < base.player.worldX + base.player.screenX &&
			worldY + base.tileSize > base.player.worldY - base.player.screenY &&
			worldY - base.tileSize < base.player.worldY + base.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, image.getWidth(), image.getHeight(), null);
		}
		else if (base.player.screenX > base.player.worldX ||
				 base.player.screenY > base.player.worldY ||
				 rightOffset > base.worldWidth - base.player.worldX ||
				 bottomOffset > base.worldHeight - base.player.worldY) {
			g2.drawImage(image, screenX, screenY, image.getWidth(), image.getHeight(), null);
		}
		
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		} catch (IOException e) {e.printStackTrace();}
		
		return image;
	}
}
