package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	Base base;
	
	public Config(Base base) {
		this.base = base;
	}
	
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
			
			if (base.fullScreenOn == true) {bw.write("on");}
			if (base.fullScreenOn == false) {bw.write("off");}
			bw.newLine();
			
			bw.write(String.valueOf(base.music.volumeScale));
			bw.newLine();
			
			bw.write(String.valueOf(base.se.volumeScale));
			bw.newLine();
			
			bw.close();
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("config.txt"));
			
			String s = br.readLine();
			
			if (s.equals("on")) {base.fullScreenOn = true;}
			if (s.equals("off")) {base.fullScreenOn = false;}
			
			s = br.readLine();
			base.music.volumeScale = Integer.parseInt(s);
			
			s = br.readLine();
			base.se.volumeScale = Integer.parseInt(s);
			
			br.close();
		}
		catch (IOException e) {e.printStackTrace();}
	}
}
