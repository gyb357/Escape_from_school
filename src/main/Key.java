package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Key implements KeyListener {
	public boolean upPressed, downPRessed, rightPressed, leftPressed, enterPressed;
	boolean showDebugText = false;
	Base base;
	
	
	public Key(Base base) {
		this.base = base;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (base.gameState == base.titleState) {titleState(code);}
		else if (base.gameState == base.playState) {playState(code);}
		else if (base.gameState == base.pauseState) {pauseState(code);}
		else if (base.gameState == base.dialogueState) {dialogueState(code);}
		else if (base.gameState == base.characterState) {characterState(code);}
		else if (base.gameState == base.optionState) {optionState(code);}
		else if (base.gameState == base.gameOverState) {gameOverState(code);}
		else if (base.gameState == base.gameClearState) {gameClearState(code);}
		else if (base.gameState == base.questionState) {questionState(code);}
	}
	
	public void titleState(int code) {
		if (base.ui.titleScreenState == 0) {
			if (code == KeyEvent.VK_UP) {
				base.ui.commandNum--;
				if (base.ui.commandNum < 0) {base.ui.commandNum = 2;}
			}
			if (code == KeyEvent.VK_DOWN) {
				base.ui.commandNum++;
				if (base.ui.commandNum > 2) {base.ui.commandNum = 0;}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				if (base.ui.commandNum == 0) {
					base.ui.titleScreenState = 1;
					//base.gameState = base.playState;
					//base.playMusic(0);
				}
				if (base.ui.commandNum == 1) {
					base.ui.titleScreenState = 2;
					
				}
				if (base.ui.commandNum == 2) {System.exit(0);}
			}
		}
		else if (base.ui.titleScreenState == 2) {
			if (code == KeyEvent.VK_ENTER) {
				base.ui.titleScreenState = 0;
			}
		}
		else if (base.ui.titleScreenState == 1) {
			if (code == KeyEvent.VK_UP) {
				base.ui.commandNum--;
				if (base.ui.commandNum < 0) {base.ui.commandNum = 3;}
			}
			if (code == KeyEvent.VK_DOWN) {
				base.ui.commandNum++;
				if (base.ui.commandNum > 3) {base.ui.commandNum = 0;}
			}
			
			if (code == KeyEvent.VK_ENTER) {
				if (base.ui.commandNum == 0) {
					base.gameState = base.playState;
					base.level = 0.03;
					base.playMusic(0);
				}
				if (base.ui.commandNum == 1) {
					base.gameState = base.playState;
					base.level = 0.04;
				}
				if (base.ui.commandNum == 2) {
					base.gameState = base.playState;
					base.level = 0.05;
				}
				if (base.ui.commandNum == 3) {
					base.ui.titleScreenState = 0;
					base.ui.commandNum = 0;
				}
			}
		}
	}
	
	public void playState(int code) {
		if (code == KeyEvent.VK_UP) {upPressed = true;}
		if (code == KeyEvent.VK_DOWN) {downPRessed = true;}
		if (code == KeyEvent.VK_RIGHT) {rightPressed = true;}
		if (code == KeyEvent.VK_LEFT) {leftPressed = true;}
		if (code == KeyEvent.VK_W) {/*base.zoomInOut(1);*/}
		if (code == KeyEvent.VK_S) {/*base.zoomInOut(-1);*/}
		if (code == KeyEvent.VK_ENTER) {enterPressed = true;}
		if (code == KeyEvent.VK_T) {
			if (showDebugText == false) {showDebugText = true;}
			else if (showDebugText == true) {showDebugText = false;}
		}
		if (code == KeyEvent.VK_P) {base.gameState = base.pauseState;}
		if (code == KeyEvent.VK_R) {
			switch (base.currentMap) {
			case 0: base.tileM.loadMap("/maps/class_00", 0); break;
			case 1: base.tileM.loadMap("/maps/corridor_00", 1); break;
			case 2: base.tileM.loadMap("/maps/class_01", 2); break;
			case 3: base.tileM.loadMap("/maps/class_02", 3); break;
			case 4: base.tileM.loadMap("/maps/bathroom_00", 4); break;
			}
		}
		if (code == KeyEvent.VK_C) {base.gameState = base.characterState;}
		if (code == KeyEvent.VK_ESCAPE) {base.gameState = base.optionState;}
	}
	
	
	public void pauseState(int code) {
		if (code == KeyEvent.VK_P) {
			base.gameState = base.playState;
		}
	}
	
	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			base.gameState = base.playState;
		}
	}
	
	public void characterState(int code) {
		if (code == KeyEvent.VK_C) {
			base.gameState = base.playState;
		}
		if (code == KeyEvent.VK_UP) {
			if (base.ui.slotRow != 0) {base.ui.slotRow--;}
		}
		if (code == KeyEvent.VK_DOWN) {
			if (base.ui.slotRow != 3) {base.ui.slotRow++;}
		}
		if (code == KeyEvent.VK_RIGHT) {
			if (base.ui.slotCol != 4) {base.ui.slotCol++;}
		}
		if (code == KeyEvent.VK_LEFT) {
			if (base.ui.slotCol != 0) {base.ui.slotCol--;}
		}
		
	}
	
	public void gameOverState(int code) {
		if (code == KeyEvent.VK_UP) {
			base.ui.commandNum--;
			if (base.ui.commandNum < 0) {base.ui.commandNum = 1;}
		}
		if (code == KeyEvent.VK_DOWN) {
			base.ui.commandNum++;
			if (base.ui.commandNum > 1) {base.ui.commandNum = 0;}
		}
		
		if (code == KeyEvent.VK_ENTER) {
			if (base.ui.commandNum == 0) {
				base.gameState = base.playState;
				base.retry();
				base.stopMusic();
				base.playMusic(0);
			}
			if (base.ui.commandNum == 1) {
				base.gameState = base.titleState;
				base.restart();
				base.stopMusic();
				base.playMusic(0);
			}
		}
	}
	
	public void gameClearState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			if (base.ui.commandNum == 0) {
				base.gameState = base.titleState;
				base.restart();
				base.stopMusic();
				base.playMusic(0);
			}
		}
	}
	
	public void questionState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			base.gameState = base.playState;
		}
	}
	
	public void optionState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {base.gameState = base.playState;}
		if (code == KeyEvent.VK_ENTER) {enterPressed = true;}
		
		
		int maxCommandNum = 5;
		switch (base.ui.commandNum) {
		case 0: maxCommandNum = 5; break;
		}
		
		if (code == KeyEvent.VK_UP) {
			base.ui.commandNum--;
			base.playSE(1);
			if (base.ui.commandNum < 0) {base.ui.commandNum = 0;}
		}
		if (code == KeyEvent.VK_DOWN) {
			base.ui.commandNum++;
			base.playSE(1);
			if (base.ui.commandNum > maxCommandNum) {base.ui.commandNum = maxCommandNum;}
		}
		
		
		if (code == KeyEvent.VK_RIGHT) {
			if (base.ui.subState == 0) {
				if (base.ui.commandNum == 1 && base.music.volumeScale < 5) {
					base.music.volumeScale++;
					base.music.checkVolume();
					base.playSE(1);
				}
			}
			if (base.ui.subState == 0) {
				if (base.ui.commandNum == 2 && base.se.volumeScale < 5) {
					base.se.volumeScale++;
					base.playSE(1);
				}
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			if (base.ui.subState == 0) {
				if (base.ui.commandNum == 1 && base.music.volumeScale > 0) {
					base.music.volumeScale--;
					base.music.checkVolume();
					base.playSE(1);
				}
			}
			if (base.ui.subState == 0) {
				if (base.ui.commandNum == 2 && base.se.volumeScale > 0) {
					base.se.volumeScale--;
					base.playSE(1);
				}
			}
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_UP) {upPressed = false;}
		if (code == KeyEvent.VK_DOWN) {downPRessed = false;}
		if (code == KeyEvent.VK_RIGHT) {rightPressed = false;}
		if (code == KeyEvent.VK_LEFT) {leftPressed = false;}
	}

}
