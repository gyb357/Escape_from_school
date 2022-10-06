package entity;

//import java.util.Random;

import main.Base;

public class NPC_OldMan extends Entity {
	public NPC_OldMan(Base base) {
		super(base);
		
		direction = "down";
		speedFactor = 1;
		speed = 0;//speedFactor*base.tileFactor;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		down1 = setup("/npc/ghost", base.tileSize, base.tileSize);
	}
	
	public void setDialogue() {
		dialogues[0] = "asdf";
		dialogues[1] = "asdf";
		dialogues[2] = "asdf";
		dialogues[3] = "asdf";
	}
	
	public void setAction() {
		/*
		actionLockCounter++;
		
		if (actionLockCounter >= 100) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			
			if (i <= 25) {
				direction = "up";
			}
			else if (i > 25 && i <= 50) {
				direction = "down";
			}
			else if (i > 50 && i <= 75) {
				direction = "right";
			}
			else if (i > 75 && i <= 100) {
				direction = "left";
			}
			
			actionLockCounter = 0;
		}
		*/
	}
	
	public void speak() {super.speak();}
}
