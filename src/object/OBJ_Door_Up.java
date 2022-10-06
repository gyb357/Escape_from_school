package object;

import entity.Entity;
import main.Base;

public class OBJ_Door_Up extends Entity {
	public OBJ_Door_Up(Base base) {
		super(base);
		
		name = "Door";
		down1 = setup("/objects/door_up", base.tileSize, base.tileSize);
		collision = true;
		description = "[" + name + "]\nasdf";
		
		
		//solidArea.x = 96;
		//solidArea.y = 768;
		//solidArea.width = base.tileSize;
		//solidArea.height = base.tileSize*2;
		//solidAreaDefaultX = solidArea.x;
		//solidAreaDefaultY = solidArea.y;
		
	}
}
