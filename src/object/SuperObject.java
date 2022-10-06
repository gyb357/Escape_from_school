package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Base;
import main.UtilityTool;

public class SuperObject {
	public BufferedImage image;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2, Base base) {
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
		
		
		if (worldX + base.tileSize > base.player.worldX - base.player.screenX && 
			worldX - base.tileSize < base.player.worldX + base.player.screenX &&
			worldY + base.tileSize > base.player.worldY - base.player.screenY &&
			worldY - base.tileSize < base.player.worldY + base.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, base.tileSize, base.tileSize, null);
		}
		else if (base.player.screenX > base.player.worldX ||
				 base.player.screenY > base.player.worldY ||
				 rightOffset > base.worldWidth - base.player.worldX ||
				 bottomOffset > base.worldHeight - base.player.worldY) {
			g2.drawImage(image, screenX, screenY, base.tileSize, base.tileSize, null);
		}
	}
}
