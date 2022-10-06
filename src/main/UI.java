package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class UI {
	Base base;
	Graphics2D g2;
	Font maruMonica;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;
	public int slotCol = 0;
	public int slotRow = 0;
	int subState = 0;
	
	
	public UI(Base base) {
		this.base = base;
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch (FontFormatException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		//g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		if (base.gameState == base.titleState) {
			g2.setFont(maruMonica);
			drawTitleScreen();
		}
		if (base.gameState == base.playState) {
			g2.setFont(maruMonica);
			drawMessage();
		}
		if (base.gameState == base.pauseState) {
			g2.setFont(maruMonica);
			drawPauseScreen();
		}
		if (base.gameState == base.dialogueState) {
			g2.setFont(maruMonica);
			drawDialogueScreen();
		}
		if (base.gameState == base.characterState) {
			g2.setFont(maruMonica);
			drawCharacterScreen();
			drawInventroy();
		}
		if (base.gameState == base.gameOverState) {
			g2.setFont(maruMonica);
			drawGameOverScreen();
		}
		if (base.gameState == base.optionState) {
			g2.setFont(maruMonica);
			drawOptionScreen();
		}
		if (base.gameState == base.gameClearState) {
			g2.setFont(maruMonica);
			drawClearScreen();
		}
		if (base.gameState == base.questionState) {
			g2.setFont(null);
			drawQuestionScreen();
		}
		
		/*
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, base.tileSize/2, base.tileSize/2, base.tileSize, base.tileSize, null);
		g2.drawString("x" + base.player.hasKey, 74, 65);
		
		if (messageOn = true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, base.tileSize/2, base.tileSize*5);
			
			messageCounter++;
			
			if (messageCounter > 120) {
				messageCounter = 0;
				messageOn = false;
			}
		}
		*/
	}
	
	public void drawQuestionScreen() {
		int frameX = base.tileSize;
		int frameY = base.tileSize;
		int frameWidth = base.tileSize*14;
		int frameHeight = base.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		int x;
		int y;
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
		g2.setColor(Color.white);
		text = base.quest.getQuestion(base.player.question);
		x = getXforCenteredText(text);
		y = base.tileSize*4;
		g2.drawString(text, x, y);
	}
	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, base.screenWidth, base.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		text = "GameOver";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = base.tileSize*4;
		g2.drawString(text, x, y);
		
		
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
		text = "retry";
		x = getXforCenteredText(text);
		y += base.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {
			base.currentMap = 0;
			g2.drawString(">", x - 40, y);
		}
		
		text = "quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if (commandNum == 1) {
			g2.drawString(">", x - 40, y);
		}
	}
	
	public void drawClearScreen() {
		g2.setColor(new Color(0, 0, 0, 150));
		g2.fillRect(0, 0, base.screenWidth, base.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
		
		text = "Congratulation";
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = base.tileSize*4;
		g2.drawString(text, x, y);
		
		
		g2.setColor(Color.white);
		g2.drawString(text, x - 4, y - 4);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
		text = "You are escaped the school!";
		g2.setColor(Color.white);
		x = getXforCenteredText(text);
		y = base.tileSize*4;
		g2.drawString(text, x, y + 80);
		
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
		text = "Quit";
		x = getXforCenteredText(text);
		y += base.tileSize*4;
		g2.drawString(text, x, y);
		if (commandNum == 0) {g2.drawString(">", x - 40, y);}
	}
	
	public void drawMessage() {
		int messageX = base.tileSize;
		int messageY = base.tileSize*4;
		
		base.setFont(base.getFont().deriveFont(Font.BOLD, 32F));
		
		for (int i = 0; i < message.size(); i++) {
			if (message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if (messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen() {
		if (titleScreenState == 0) {
			Color c = new Color(0, 0, 0);
			g2.setColor(c);
			g2.fillRect(0, 0, base.screenWidth, base.screenHeight);
			
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Escape from School";
			int x = getXforCenteredText(text);
			int y = base.tileSize*3;
			
			
			c = new Color(128, 128, 128);
			g2.setColor(c);
			g2.drawString(text, x + 5, y + 5);
			
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			
			x = base.screenWidth/2 - (base.tileSize*2)/2;
			y += base.tileSize*2;
			g2.drawImage(base.player.down1, x, y, base.tileSize*2, base.tileSize*2, null);
			
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
			text = "Play Game";
			x = getXforCenteredText(text);
			y += base.tileSize*4;
			g2.drawString(text, x, y);
			if (commandNum == 0) {g2.drawString(">", x - base.tileSize, y);}
			
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
			text = "How to play";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {g2.drawString(">", x - base.tileSize, y);}
			
			
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
			text = "Quit";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {g2.drawString(">", x - base.tileSize, y);}
		}
		else if (titleScreenState == 1) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select Game Level";
			int x = getXforCenteredText(text);
			int y = base.tileSize*3;
			g2.drawString(text, x, y);
			
			
			text = "Easy";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 0) {g2.drawString(">", x - base.tileSize, y);}
			
			
			text = "Normal";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {g2.drawString(">", x - base.tileSize, y);}
			
			
			text = "Hard";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {g2.drawString(">", x - base.tileSize, y);}
			
			
			text = "Back";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 3) {g2.drawString(">", x - base.tileSize, y);}
		}
		else if (titleScreenState == 2) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "How to play";
			int x = getXforCenteredText(text);
			int y = base.tileSize*3;
			g2.drawString(text, x, y);
			
			
			text = "1. Solve the problem and leave the room!";
			x = getXforCenteredText(text);
			y += base.tileSize*2;
			g2.drawString(text, x, y);
			if (commandNum == 0) {g2.drawString(">", x - base.tileSize, y);}
			
			
			text = "2. Avoid Ghosts!";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 0) {g2.drawString(">", x - base.tileSize, y);}
			
			
			text = "3. Escape from school!";
			x = getXforCenteredText(text);
			y += base.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 0) {g2.drawString(">", x - base.tileSize, y);}
		}
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSE";
		int x = getXforCenteredText(text);
		int y = base.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		int x = base.tileSize*2;
		int y = base.tileSize/2;
		int width = base.screenWidth - (base.tileSize*4);
		int height = base.tileSize*5;
		drawSubWindow(x, y, width, height);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		x += base.tileSize;
		y += base.tileSize;
		
		for (String line : currentDialogue.split("\n"))  {
			g2.drawString(currentDialogue, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen() {
		final int frameX = base.tileSize*2;
		final int frameY = base.tileSize;
		final int frameWidth = base.tileSize*5;
		final int frameHeight = base.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + base.tileSize;
		final int lineHeight = 32;
		
		g2.drawString("asdf0", textX, textY); textY += lineHeight;
		g2.drawString("asdf1", textX, textY); textY += lineHeight;
		g2.drawString("asdf2", textX, textY); textY += lineHeight;
		g2.drawString("asdf3", textX, textY); textY += lineHeight;
		g2.drawString("asdf4", textX, textY); textY += lineHeight;
		g2.drawString("asdf5", textX, textY); textY += lineHeight;
		g2.drawString("asdf6", textX, textY); textY += lineHeight;
		g2.drawString("asdf7", textX, textY); textY += lineHeight;
		
		
		int tailX = (frameX + frameWidth) - 30;
		textY = frameY + base.tileSize;
		String value;
		
		value = String.valueOf(1);
		textX = getXforAlignToRightText(value, tailX);
		
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
		g2.drawString(value, textX, textY); textY += lineHeight;
	}
	
	public void drawInventroy() {
		
		int frameX = base.tileSize*8;
		int frameY = base.tileSize;
		int frameWidth = base.tileSize*6;
		int frameHeight = base.tileSize*5;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		
		for (int i = 0; i < base.player.inventory.size(); i++) {
			g2.drawImage(base.player.inventory.get(i).down1, slotX, slotY, null);
			
			slotX += base.tileSize;
			
			if (i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += base.tileSize;
			}
		}
		
		
		int cursorX = slotXstart + (base.tileSize*slotCol);
		int cursorY = slotYstart + (base.tileSize*slotRow);;
		int cursorWidth = base.tileSize;
		int cursorHeight = base.tileSize;
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
		
		
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = base.tileSize*3;
		drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
		
		
		int textX = dFrameX + 20;
		int textY = dFrameY + base.tileSize;
		g2.setFont(g2.getFont().deriveFont(28F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if (itemIndex < base.player.inventory.size()) {
			for (String line: base.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
		}
	}
	
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int frameX = base.tileSize*4;
		int frameY = base.tileSize;
		int frameWidth = base.tileSize*8;
		int frameHeight = base.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		switch (subState) {
		case 0: options_top(frameX, frameY); break;
		case 1: option_fullscreenNotification(frameX, frameY); break;
		case 2: option_control(frameX, frameY); break;
		case 3: option_endGameComfirmation(frameX, frameY); break;
		}
		
		base.key.enterPressed = false;
	}
	
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + base.tileSize;
		g2.drawString(text, textX, textY);
		
		
		textX = frameX + base.tileSize;
		textY += base.tileSize*2;
		g2.drawString("Full Screen", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			
			if (base.key.enterPressed == true) {
				if (base.fullScreenOn == false) {base.fullScreenOn = true;}
				else if (base.fullScreenOn == true) {base.fullScreenOn = false;}
				
				subState = 1;
			}
		}
		
		
		textY += base.tileSize;
		g2.drawString("Music", textX, textY);
		if (commandNum == 1) {g2.drawString(">", textX - 25, textY);}
		
		textY += base.tileSize;
		g2.drawString("Sound Effect", textX, textY);
		if (commandNum == 2) {g2.drawString(">", textX - 25, textY);}
		
		textY += base.tileSize;
		g2.drawString("Control", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}
		
		textY += base.tileSize;
		g2.drawString("Exit Game", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 3;
				commandNum = 0;
			}
		}
		
		textY += base.tileSize*2;
		g2.drawString("Back", textX, textY);
		if (commandNum == 5) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				base.gameState = base.playState;
				commandNum = 0;
			}
		}
		
		
		textX = frameX + (int)base.tileSize*4;
		textY = frameY + base.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(textX, textY, 24, 24);
		if (base.fullScreenOn == true) {
			g2.fillRect(textX, textY, 24, 24);
		}
		
		
		textY += base.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24*base.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		textY += base.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24*base.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		base.config.saveConfig();
	}
	
	public void option_fullscreenNotification(int frameX, int frameY) {
		int textX = frameX + base.tileSize;
		int textY = frameY + base.tileSize*3;
		
		currentDialogue = "You need to start again";
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		
		textY += frameY + (base.tileSize*9);
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">",  textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 0;
			}
		}
	}
	
	public void option_endGameComfirmation(int frameX, int frameY) {
		int textX = frameX + base.tileSize;
		int textY = frameY + base.tileSize*3;
		
		currentDialogue = "Are you sure to exit?";
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += base.tileSize*3;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 0;
				base.gameState = base.titleState;
			}
		}
		
		text = "No";
		textX = getXforCenteredText(text);
		textY += base.tileSize;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 0;
				commandNum = 3;
			}
		}
	}
	
	public void option_control(int frameX, int frameY) {
		int textX;
		int textY;
		
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + base.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + base.tileSize;
		textY += base.tileSize;
		g2.drawString("Move", textX, textY); textY += base.tileSize;
		g2.drawString("Confirm/Attack", textX, textY); textY += base.tileSize;
		g2.drawString("Shoot/Chat", textX, textY); textY += base.tileSize;
		g2.drawString("Character Screen", textX, textY); textY += base.tileSize;
		g2.drawString("Pause", textX, textY); textY += base.tileSize;
		g2.drawString("Option", textX, textY); textY += base.tileSize;
		
		
		textX = frameX + base.tileSize*6;
		textY = frameY + base.tileSize*2;
		g2.drawString("wasd", textX, textY); textY += base.tileSize;
		g2.drawString("enter", textX, textY); textY += base.tileSize;
		g2.drawString("f", textX, textY); textY += base.tileSize;
		g2.drawString("c", textX, textY); textY += base.tileSize;
		g2.drawString("p", textX, textY); textY += base.tileSize;
		g2.drawString("ecs", textX, textY); textY += base.tileSize;
		
		
		textX = frameX + base.tileSize;
		textY = frameY + base.tileSize*9;
		g2.drawString("Back", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (base.key.enterPressed == true) {
				subState = 0;
			}
	
		
		}
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + slotRow*5;
		return itemIndex;
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = base.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
