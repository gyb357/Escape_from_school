package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import main.Base;
import main.Key;
import object.OBJ_Key;

public class Player extends Entity {
	Key key;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	
	public int question, currect = 0;
	
	
	public Player(Base base, Key key) {
		super(base);
		
		this.key = key;
		
		screenX = base.screenWidth/2 - (base.tileSize/2);
		screenY = base.screenHeight/2 - (base.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
		setDefaultValue();
		getPlayerImage();
		setItems();
	}
	
	public void setDefaultValue() {
		worldX = base.tileSize*4;
		worldY = base.tileSize*4;
		speedFactor = 2;
		speed = speedFactor*base.tileFactor;
		//speed = speedFactor*(base.worldWidth/600)*base.tileFactor;
		
		direction = "down";
	}
	
	public void setItems() {
		
	}
	
	
	public void getPlayerImage() {
		int x = base.tileSize;
		int y = base.tileSize + 5;
		
		up1 = setup("/player/player_up_1", x, y);
		up2 = setup("/player/player_up_2", x, y);
		down1 = setup("/player/player_down_1", x, y);
		down2 = setup("/player/player_down_2", x, y);
		right1 = setup("/player/player_right_1", x, y);
		right2 = setup("/player/player_right_2", x, y);
		left1 = setup("/player/player_left_1", x, y);
		left2 = setup("/player/player_left_2", x, y);
	}
	
	public void setDefaultPosition() {
		worldX = base.tileSize*4;
		worldY = base.tileSize*4;
		direction = "down";
	}
	
	public void update() {
		if (moving == false) {
			if (key.upPressed == true || key.downPRessed == true || key.rightPressed == true || key.leftPressed == true) {
				if (key.upPressed == true) {direction = "up";}
				else if (key.downPRessed == true) {direction = "down";}
				else if (key.rightPressed == true) {direction = "right";}
				else if (key.leftPressed == true) {direction = "left";}
				
				moving = true;
				
				collisionOn = false;
				base.cChecker.checkTile(this);
				
				int objIndex = base.cChecker.checkObject(this,  true);
				int npcIndex = base.cChecker.checkEntity(this, base.npc);
				objIndex = base.cChecker.checkObject(this,  true);
				npcIndex = base.cChecker.checkEntity(this, base.npc);
				
				interactObject(objIndex);
				interactNPC(npcIndex);
				base.event.checkEvent();
			}
			else {
				standCounter++;
				
				if (standCounter == base.tileSize/2) {
					spriteNum = 1;
					standCounter = 0;
				}
				spriteNum = 1;
			}
		}
		
		if (moving == true) {
			if (collisionOn == false) {
				switch (direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "right": worldX += speed; break;
				case "left": worldX -= speed;break;
				}
			}
			
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
	}
	
	public void interactObject(int i) {	//public void pickUpObject(int i) {
		if (i != 999) {
			String text = "";
			String objectName = base.obj[base.currentMap][i].name;
			
			// Key
			if (objectName == "Key") {
				if (inventory.size() != maxInventorySize) {
					inventory.add(base.obj[base.currentMap][i]);
					base.playSE(1);
					text = "got a " + base.obj[base.currentMap][i].name + "!";
				}
				else {
					text = "you cannot carry anymore!";
				}
			}
			
			
			// Print Message
			//base.ui.addMessage(text);
			//base.obj[base.currentMap][i] = null;
			
			
			// Door open
			if (objectName == "Door" || objectName == "Door_Up") {
				int value = 0;
				if (hasKey > 0) {
					base.playSE(2);
					
					if (base.currentMap == 0) {value = 1;}
					if (base.currentMap == 2) {value = 1;}
					if (base.currentMap == 3) {value = 1;}
					
					base.obj[base.currentMap][i] = null;
					base.obj[value][i] = null;
					hasKey--;
					base.ui.addMessage("You opened the door");
				}
				else {
					base.ui.addMessage("You need a key");
				}
			}
			
			
			// Book
			if (objectName == "Book") {
				base.gameState = base.questionState;
				
				Random random = new Random();
				question = random.nextInt(13);
				base.quest.getQuestion(question);
				
				//System.out.println(base.quest.getAnswer(question));
				String answer = base.scan.nextLine();
				
				if (answer.equals(base.quest.getAnswer(question))) {
					base.gameState = base.playState;
					currect++;
					System.out.println("Currect! " + currect);
					
					if (inventory.size() != maxInventorySize) {
						inventory.add(base.obj[base.currentMap][i]);
						base.obj[base.currentMap][i] = null;
						base.playSE(1);
					}
					if (currect == 3 || currect == 6) {
						hasKey++;
						System.out.println("You got a key");
						base.obj[base.currentMap][99] = new OBJ_Key(base);
						base.obj[base.currentMap][99].worldX = 10*base.tileSize;
						base.obj[base.currentMap][99].worldY = 10*base.tileSize;
					}
				}
				else {
					base.gameState = base.playState;
					System.out.println("Wrong answer!");
					base.playSE(2);
				}
			}
			
			
			if (objectName == "Chest") {
				if (currect == 9) {
					base.gameState = base.gameClearState;
					base.currentMap = 0;
					currect = 0;
				}
			}
		}
	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			base.gameState = base.gameOverState;
			//base.npc[base.currentMap][i].speak();
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
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
		
		int x = screenX;
		int y = screenY;
		
		if (screenX > worldX) {x = worldX;}
		if (screenY > worldY) {y = worldY;}
		
		int rightOffset = base.screenWidth - screenX;
		int bottomOffset = base.screenHeight - screenY;
		
		
		if (rightOffset > base.worldWidth - worldX) {
			x = base.screenWidth - (base.worldWidth - worldX);
		}
		if (bottomOffset > base.worldHeight - worldY) {
			y = base.screenHeight - (base.worldHeight - worldY);
		}
		
		
		g2.drawImage(image, x, y - 5, null);
		
		g2.setColor(Color.red);
		g2.drawRect(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);
	}
}
