package main;

import entity.NPC_OldMan;
import object.OBJ_Book;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Door_Up;
import object.OBJ_Key;

public class AssetSetter {
	Base base;
	
	public AssetSetter(Base base) {
		this.base = base;
	}
	
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		// mapNum = 0
		// Door
		i++;
		base.obj[mapNum][i] = new OBJ_Door(base);
		base.obj[mapNum][i].worldX = base.tileSize*3;
		base.obj[mapNum][i].worldY = base.tileSize*17;
		i++;
		base.obj[mapNum][i] = new OBJ_Door(base);
		base.obj[mapNum][i].worldX = base.tileSize*12;
		base.obj[mapNum][i].worldY = base.tileSize*17;
		// Book
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*3;
		base.obj[mapNum][i].worldY = base.tileSize*4;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*6;
		base.obj[mapNum][i].worldY = base.tileSize*10;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*9;
		base.obj[mapNum][i].worldY = base.tileSize*7;
		
		
		// mapNum = 1
		// Door
		mapNum++;
		i = 0;
		i++;
		base.obj[mapNum][i] = new OBJ_Door(base);
		base.obj[mapNum][i].worldX = base.tileSize*3;
		base.obj[mapNum][i].worldY = base.tileSize*13;
		i++;
		base.obj[mapNum][i] = new OBJ_Door(base);
		base.obj[mapNum][i].worldX = base.tileSize*12;
		base.obj[mapNum][i].worldY = base.tileSize*13;
		i++;
		base.obj[mapNum][i] = new OBJ_Door_Up(base);
		base.obj[mapNum][i].worldX = base.tileSize*3;
		base.obj[mapNum][i].worldY = base.tileSize*19;
		i++;
		base.obj[mapNum][i] = new OBJ_Door_Up(base);
		base.obj[mapNum][i].worldX = base.tileSize*12;
		base.obj[mapNum][i].worldY = base.tileSize*19;
		i++;
		base.obj[mapNum][i] = new OBJ_Door_Up(base);
		base.obj[mapNum][i].worldX = base.tileSize*19;
		base.obj[mapNum][i].worldY = base.tileSize*19;
		i++;
		base.obj[mapNum][i] = new OBJ_Door_Up(base);
		base.obj[mapNum][i].worldX = base.tileSize*28;
		base.obj[mapNum][i].worldY = base.tileSize*19;
		
		i++;
		base.obj[mapNum][i] = new OBJ_Chest(base);
		base.obj[mapNum][i].worldX = base.tileSize*28;
		base.obj[mapNum][i].worldY = base.tileSize*17;
		
		// mapNum = 2
		// Book
		mapNum++;
		i = 0;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*6;
		base.obj[mapNum][i].worldY = base.tileSize*13;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*12;
		base.obj[mapNum][i].worldY = base.tileSize*10;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*9;
		base.obj[mapNum][i].worldY = base.tileSize*4;
		
		
		// mapNum = 3
		// Book
		mapNum++;
		i = 0;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*3;
		base.obj[mapNum][i].worldY = base.tileSize*4;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*12;
		base.obj[mapNum][i].worldY = base.tileSize*7;
		i++;
		base.obj[mapNum][i] = new OBJ_Book(base);
		base.obj[mapNum][i].worldX = base.tileSize*9;
		base.obj[mapNum][i].worldY = base.tileSize*13;
	}
	
	public void setNPC() {
		int mapNum = 2;
		int i = 0;
		// mapNum = 2
		base.npc[mapNum][i] = new NPC_OldMan(base);
		base.npc[mapNum][i].worldX = base.tileSize*16;
		base.npc[mapNum][i].worldY = base.tileSize*16;
		
		
		
		// mapNum = 3
		mapNum++;
		base.npc[mapNum][i] = new NPC_OldMan(base);
		base.npc[mapNum][i].worldX = base.tileSize*1;
		base.npc[mapNum][i].worldY = base.tileSize*16;
	}
}
