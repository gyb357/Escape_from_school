package main;

import java.awt.Window;

import javax.swing.JFrame;

public class Main {
	public static Window window;

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Escape from School");
		
		Base base = new Base();
		window.add(base);
		
		base.config.loadConfig();
		if (base.fullScreenOn == true) {window.setUndecorated(true);}
		window.pack();
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		base.setupGame();
		base.startGameThread();
	}

}
