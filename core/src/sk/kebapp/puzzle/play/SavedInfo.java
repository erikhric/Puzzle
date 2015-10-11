package sk.kebapp.puzzle.play;

import java.io.Serializable;
import java.util.ArrayList;

import sk.kebapp.puzzle.Puzzle;
import sk.kebapp.puzzle.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SavedInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	private ArrayList<PuzzlePiece> pieces;
	private long startTime;

	public SavedInfo(ArrayList<PuzzlePiece> pieces, long startTime) {
		this.startTime = startTime;
		this.pieces = pieces;
	}

	public ArrayList<PuzzlePiece> getPieces() {
		return pieces;
	}

	public long getStartTime() {
		return startTime;
	}

	public void save() {
		Preferences prefs = Gdx.app.getPreferences("saved");
		prefs.putLong("startTime", startTime);
		prefs.putInteger("size", pieces.size());
		prefs.putInteger("puzzleSize", Settings.getPuzzleSize());
		int i = 0;
		for (PuzzlePiece piece : pieces) {
			prefs.putFloat("piece" + i + "X", piece.getX());
			prefs.putFloat("piece" + i + "Y", piece.getY());
			prefs.putFloat("piece" + i + "W", piece.getWidth());
			prefs.putFloat("piece" + i + "H", piece.getHeight());

			prefs.putInteger("piece" + i + "TX", piece.getTextureX());
			prefs.putInteger("piece" + i + "TY", piece.getTextureY());
			prefs.putInteger("piece" + i + "TS", piece.getPieceSize());
			
			prefs.putInteger("piece" + i + "BX", piece.getBoardX());
			prefs.putInteger("piece" + i + "BY", piece.getBoardY());
			
			prefs.putFloat("piece" + i + "Xmenu", piece.getXpositionInmenu());
			prefs.putFloat("piece" + i + "Ymenu", piece.getYpositionInmenu());
		i++;
		}
		prefs.flush();
	}

	public void load(Puzzle game) {
		pieces.clear();
		Preferences prefs = Gdx.app.getPreferences("saved");
		startTime = prefs.getLong("startTime");
		Settings.setPuzzleSize(prefs.getInteger("puzzleSize"));
		int size = prefs.getInteger("size");
		try {
		for (int i = 0; i < size; i++) {
			float x = prefs.getFloat("piece" + i + "X");
			float y = prefs.getFloat("piece" + i + "Y");
			float w = prefs.getFloat("piece" + i + "W");
			float h = prefs.getFloat("piece" + i + "H");

			int boardX = prefs.getInteger("piece" + i + "TX");
			int boardY = prefs.getInteger("piece" + i + "TY");
			int boardS = prefs.getInteger("piece" + i + "TS");

			PuzzlePiece loaded = new PuzzlePiece(boardX, boardY, boardS,
					Settings.getImage(), game);
			pieces.add(loaded);
			loaded.setBounds(x, y, w, h);
			//loaded.setBoardXY(boardX, boardY);
			float xpositionInmenu = prefs.getFloat("piece" + i + "Xmenu");
			float ypositionInmenu = prefs.getFloat("piece" + i + "Ymenu");
		
			//loaded.setInMenuSize(loaded.getWidth()*.8f);
			
			loaded.setXYpositionInmenu(xpositionInmenu, ypositionInmenu);
			loaded.snapToGrid();
			//System.out.println(x+y+w+h);
		}
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
	}
}
