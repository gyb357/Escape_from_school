package main;

import java.awt.Rectangle;

public class Event {
	Base base;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public Event(Base base) {
		this.base = base;
		
		eventRect = new Rectangle();
		eventRect.x = 10;
		eventRect.y = 10;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultX = eventRect.y;
	}
	
	public void checkEvent() {
		if (base.currentMap == 0) {
			if (hit(3, 17, "down") == true)  {base.currentMap = 1; teleport(base.gameState, 3, 14);}
			if (hit(12, 17, "down") == true) {base.currentMap = 1; teleport(base.gameState, 12, 14);}
		}
		
		if (base.currentMap == 1) {
			if (hit(3, 14, "up") == true)   {base.currentMap = 0; teleport(base.gameState, 3, 17);}
			if (hit(12, 14, "up") == true)  {base.currentMap = 0; teleport(base.gameState, 12, 17);}
			if (hit(19, 14, "up") == true)  {base.currentMap = 2; teleport(base.gameState, 3, 17);}
			if (hit(28, 14, "up") == true)  {base.currentMap = 2; teleport(base.gameState, 12, 17);}
			if (hit(3, 20, "down") == true)  {base.currentMap = 3; teleport(base.gameState, 3, 1);}
			if (hit(12, 20, "down") == true) {base.currentMap = 3; teleport(base.gameState, 12, 1);}
			
			
			if (hit(19, 20, "down") == true) {base.currentMap = 4; teleport(base.gameState, 3, 1);}
			if (hit(28, 20, "down") == true) {base.currentMap = 4; teleport(base.gameState, 12, 1);}
			
			
			if (hit(30, 15, "right") || hit(30, 16, "right") || hit(30, 17, "right") || hit(30, 18, "right")) {
				//base.gameState = base.gameClearState;
				//base.currentMap = 0;
			}
		}
		
		if (base.currentMap == 2) {
			if (hit(3, 17, "down") == true)  {base.currentMap = 1; teleport(base.gameState, 19, 14);}
			if (hit(12, 17, "down") == true) {base.currentMap = 1; teleport(base.gameState, 28, 14);}
		}
		
		if (base.currentMap == 3) {
			if (hit(3, 1, "up") == true)  {base.currentMap = 1; teleport(base.gameState, 3, 21);}
			if (hit(12, 1, "up") == true) {base.currentMap = 1; teleport(base.gameState, 12, 21);}
		}
		
		if (base.currentMap == 4) {
			if (hit(3, 1, "up") == true)  {base.currentMap = 1; teleport(base.gameState, 19, 21);}
			if (hit(12, 1, "up") == true)  {base.currentMap = 1; teleport(base.gameState, 28, 21);}
		}
	}
	
	public boolean hit(int eventCol, int eventRow, String regDirection) {
		boolean hit = false;
		
		base.player.solidArea.x = base.player.worldX + base.player.solidArea.x;
		base.player.solidArea.y = base.player.worldY + base.player.solidArea.y;
		
		eventRect.x = eventCol*base.tileSize + eventRect.x;
		eventRect.y = eventRow*base.tileSize + eventRect.y;
		
		if (base.player.solidArea.intersects(eventRect)) {
			if (base.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		base.player.solidArea.x = base.player.solidAreaDefaultX;
		base.player.solidArea.y = base.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	}
	
	public void teleport(int gameState, int x, int y) {
		base.gameState = gameState;
		base.ui.currentDialogue = "Teleport";
		base.player.worldX = base.tileSize*x;
		base.player.worldY = base.tileSize*y;
	}
}
