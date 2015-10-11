package sk.kebapp.puzzle;

import com.badlogic.gdx.graphics.Texture;

public class Settings {
	
	private static int puzzleSize = 6; // tu nastavuj iba parne cisla aby to vyzeralo dobre
	private static Texture image;
	
	public static Texture getImage() {
		return image;
	}
	
	public static void setImage(Texture image) {
		Settings.image = image;
	}
	
	public static int getPuzzleSize(){
		return puzzleSize;
	}
	
	public static void setPuzzleSize(int puzzleSize) {
		Settings.puzzleSize = puzzleSize;
	}
}
