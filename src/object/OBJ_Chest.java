package object;

import entity.Entity;
import main.Base;

public class OBJ_Chest extends Entity {
	public OBJ_Chest(Base base) {
		super(base);
		
		name = "Chest";
		down1 = setup("/objects/chest", base.tileSize, base.tileSize);
		collision = true;
		description = "[" + name + "]\nasdf";
	}
}