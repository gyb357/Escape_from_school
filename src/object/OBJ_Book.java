package object;

import entity.Entity;
import main.Base;

public class OBJ_Book extends Entity {
	
	public OBJ_Book(Base base) {
		super(base);
		
		name = "Book";
		down1 = setup("/objects/book", base.tileSize, base.tileSize);
		collision = true;
		description = "[" + name + "]\nasdf";
	}
}
