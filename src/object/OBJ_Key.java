package object;

import entity.Entity;
import main.Base;

public class OBJ_Key extends Entity {
	
	public OBJ_Key(Base base) {
		super(base);
		
		name = "Key";
		down1 = setup("/objects/key", base.tileSize, base.tileSize);
		collision = true;
		description = "[" + name + "]\nasdf";
	}
}
