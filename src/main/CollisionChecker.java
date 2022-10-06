package main;

import entity.Entity;

public class CollisionChecker {
	Base base;
	
	public CollisionChecker (Base base) {
		this.base = base;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/base.tileSize;
		int entityRightCol = entityRightWorldX/base.tileSize;
		int entityTopRow = entityTopWorldY/base.tileSize;
		int entityBottomRow = entityBottomWorldY/base.tileSize;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/base.tileSize;
			tileNum1 = base.tileM.mapTileNum[base.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = base.tileM.mapTileNum[base.currentMap][entityRightCol][entityTopRow];
			
			if (base.tileM.tile[tileNum1].collision == true || base.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/base.tileSize;
			tileNum1 = base.tileM.mapTileNum[base.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = base.tileM.mapTileNum[base.currentMap][entityRightCol][entityBottomRow];
			
			if (base.tileM.tile[tileNum1].collision == true || base.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/base.tileSize;
			tileNum1 = base.tileM.mapTileNum[base.currentMap][entityRightCol][entityTopRow];
			tileNum2 = base.tileM.mapTileNum[base.currentMap][entityRightCol][entityBottomRow];
			
			if (base.tileM.tile[tileNum1].collision == true || base.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/base.tileSize;
			tileNum1 = base.tileM.mapTileNum[base.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = base.tileM.mapTileNum[base.currentMap][entityLeftCol][entityBottomRow];
			
			if (base.tileM.tile[tileNum1].collision == true || base.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for (int i = 0; i < base.obj[1].length; i++) {
			if (base.obj[base.currentMap][i] != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				base.obj[base.currentMap][i].solidArea.x = base.obj[base.currentMap][i].worldX + base.obj[base.currentMap][i].solidArea.x;
				base.obj[base.currentMap][i].solidArea.y = base.obj[base.currentMap][i].worldY + base.obj[base.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(base.obj[base.currentMap][i].solidArea)) {
						if (base.obj[base.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if (player = true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(base.obj[base.currentMap][i].solidArea)) {
						if (base.obj[base.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if (player = true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(base.obj[base.currentMap][i].solidArea)) {
						if (base.obj[base.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if (player = true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(base.obj[base.currentMap][i].solidArea)) {
						if (base.obj[base.currentMap][i].collision == true) {
							entity.collisionOn = true;
						}
						if (player = true) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				base.obj[base.currentMap][i].solidArea.x = base.obj[base.currentMap][i].solidAreaDefaultX;
				base.obj[base.currentMap][i].solidArea.y = base.obj[base.currentMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		
		for (int i = 0; i < target.length; i++) {
			if (target[base.currentMap][i] != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				target[base.currentMap][i].solidArea.x = target[base.currentMap][i].worldX + target[base.currentMap][i].solidArea.x;
				target[base.currentMap][i].solidArea.y = target[base.currentMap][i].worldY + target[base.currentMap][i].solidArea.y;
				
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(target[base.currentMap][i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(target[base.currentMap][i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(target[base.currentMap][i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(target[base.currentMap][i].solidArea)) {
						entity.collisionOn = true;
						index = i;
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[base.currentMap][i].solidArea.x = target[base.currentMap][i].solidAreaDefaultX;
				target[base.currentMap][i].solidArea.y = target[base.currentMap][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		base.player.solidArea.x = base.player.worldX + base.player.solidArea.x;
		base.player.solidArea.y = base.player.worldY + base.player.solidArea.y;
		
		switch (entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			if (entity.solidArea.intersects(base.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			if (entity.solidArea.intersects(base.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			if (entity.solidArea.intersects(base.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			if (entity.solidArea.intersects(base.player.solidArea)) {
				entity.collisionOn = true;
			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		base.player.solidArea.x = base.player.solidAreaDefaultX;
		base.player.solidArea.y = base.player.solidAreaDefaultY;
	}
}
