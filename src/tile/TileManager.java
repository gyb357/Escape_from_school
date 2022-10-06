package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Base;
import main.UtilityTool;

public class TileManager {
	Base base;
	public Tile[] tile;
	public int mapTileNum[][][];
	
	public TileManager(Base base) {
		this.base = base;
		
		tile = new Tile[100];
		mapTileNum = new int[base.maxMap][base.maxWorldCol][base.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/class_00", 0);
		loadMap("/maps/corridor_00", 1);
		loadMap("/maps/class_01", 2);
		loadMap("/maps/class_02", 3);
		loadMap("/maps/bathroom_00", 4);
	}
	
	public void getTileImage() {
		setup(0, "null", true);
		setup(1, "floor_tile_00", false);
		setup(2, "floor_tile_01", false);
		setup(3, "floor_tile_dark_00", false);
		setup(4, "floor_tile_dark_01", false);
		
		setup(5, "desk_00", true);
		setup(6, "chair_00", false);
		
		setup(7, "wall_top_corner", true);
		setup(8, "wall_top_horizon", true);
		setup(9, "wall_top_vertical", true);
		setup(10, "wall_top_00", true);
		setup(11, "wall_bottom_00", true);
		
		setup(12, "floor_corridor_00", false);
		setup(13, "floor_corridor_dark_00", false);
		
		setup(14, "door_top_00", false);
		setup(15, "door_bottom_00", false);
		
		setup(16, "tile_carpet_00", false);
		
		setup(17, "tile_exit_carpet_00", false);
		setup(18, "tile_exit_carpet_01", false);
		setup(19, "tile_exit_carpet_02", false);
		setup(20, "tile_exit_carpet_03", false);
		setup(21, "tile_exit_carpet_04", false);
		setup(22, "tile_exit_carpet_05", false);
		setup(23, "tile_exit_carpet_06", false);
		setup(24, "tile_exit_carpet_07", false);
		setup(25, "tile_exit_carpet_08", false);
		
		setup(26, "tile_bathroom_00", false);
		
		setup(27, "board_00", true);
		setup(28, "board_01", true);
		setup(29, "board_02", true);
		setup(30, "board_03", true);
		setup(31, "board_04", true);
		setup(32, "board_05", true);
		
		setup(33, "cabinet_top_00", true);
		setup(34, "cabinet_bottom_00", true);
		setup(35, "cabinet_top_01", true);
		
		setup(36, "tile_stair_00", true);
		
		setup(37, "bulletin_board_00", true);
		setup(38, "bulletin_board_01", true);
		
		setup(39, "washbasin_00", true);
		setup(40, "washbasin_01", true);
		setup(41, "washbasin_02", true);
		setup(42, "washbasin_03", true);
		
		setup(43, "wall_window_00", true);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, base.tileSize, base.tileSize);
			tile[index].collision = collision;
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath + ".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < base.maxWorldCol && row < base.maxWorldRow) {
				String line = br.readLine();
				
				while (col < base.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if (col == base.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch (Exception e) {}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while (worldCol < base.maxWorldCol && worldRow < base.maxWorldRow) {
			int tileNum = mapTileNum[base.currentMap][worldCol][worldRow];
			
			int worldX = worldCol*base.tileSize;
			int worldY = worldRow*base.tileSize;
			int screenX = worldX - base.player.worldX + base.player.screenX;
			int screenY = worldY - base.player.worldY + base.player.screenY;
			
			if (base.player.screenX > base.player.worldX) {screenX = worldX;}
			if (base.player.screenY > base.player.worldY) {screenY = worldY;}
			
			
			int rightOffset = base.screenWidth - base.player.screenX;
			int bottomOffset = base.screenHeight - base.player.screenY;
			if (rightOffset > base.worldWidth - base.player.worldX) {
				screenX = base.screenWidth - (base.worldWidth - worldX);
			}
			if (bottomOffset > base.worldHeight - base.player.worldY) {
				screenY = base.screenHeight - (base.worldHeight - worldY);
			}
			
			
			if (worldX + base.tileSize > base.player.worldX - base.player.screenX && 
				worldX - base.tileSize < base.player.worldX + base.player.screenX &&
				worldY + base.tileSize > base.player.worldY - base.player.screenY &&
				worldY - base.tileSize < base.player.worldY + base.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			else if (base.player.screenX > base.player.worldX ||
					 base.player.screenY > base.player.worldY ||
					 rightOffset > base.worldWidth - base.player.worldX ||
					 bottomOffset > base.worldHeight - base.player.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if (worldCol == base.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
	}
}
