package sk.kebapp.puzzle.play;

import java.io.Serializable;

import sk.kebapp.puzzle.Puzzle;
import sk.kebapp.puzzle.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class PuzzlePiece extends Actor implements Serializable{

	private static final long serialVersionUID = 1L;
	private int boardX;
	private int boardY;
	private int textureX, textureY, pieceSize;
	private Texture texture;
	private PuzzlePiece pp;
	private Puzzle gamee;
	private boolean solvedX;
	private boolean solvedY;
	
	private float XpositionInmenu;
	private float YpositionInmenu;
	
	private float inMenuSize = 5;
	
	public PuzzlePiece(int x, int y, int size, Texture image, Puzzle game) {
		textureX = x;
		textureY = y;
		pieceSize = size;
		texture = image;
		this.gamee = game;

		setSize(Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1),
				Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1));

		boardY = (Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1) / 2);
		boardX = (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2
				+ boardY;

		setPosition(x * getWidth() + boardX, y * getHeight() + boardY);
		setY(Gdx.graphics.getHeight() - getY() - 2 * boardY);

		this.pp = this;
		addListener(new DragListener() {
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				pp.setOrigin(Gdx.input.getX(), Gdx.input.getY());
				pp.setPosition(getX() + x - getWidth() / 2, getY() + y
						- getHeight() / 2);
				setSize(Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1),
						Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1));
				gamee.getPlay().setDraggedPiece(pp);
			}
		});

		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				snapToGrid();
				gamee.getPlay().setDraggedPiece(null);
				gamee.getPlay().checkSolved();
				gamee.getPlay().save();
				System.out.println("piece ma board x a y " + boardX + boardY);
				super.clicked(event, x, y);
			}
		});
		setInMenuSize(getWidth()*.8f);
	}

	public boolean isOnRightPlace() {
		return solvedX && solvedY;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getWidth(), getHeight(), textureX
				* pieceSize, textureY * pieceSize, pieceSize, pieceSize, false,
				false);
	}

	public void snapToGrid() {
		int centerX = (int) (getWidth() / 2 + getX());
		int centerY = (int) (getHeight() / 2 + getY());
		solvedY = solvedX = false;
		if (centerX > boardX && centerX < Gdx.graphics.getWidth() - boardX) {
			if (centerY > boardY && centerY < Gdx.graphics.getHeight() - boardY) {
				// System.out.println("je na mriezke, ideme prichytit");
				snapHorizontally();
				snapVertically();
				System.out
						.println("je na spravnom mieste ?" + isOnRightPlace());
			}
		} else {
			setPosition(XpositionInmenu, YpositionInmenu);
			setSize(inMenuSize, inMenuSize);
		}

	}

	public void snapHorizontally() {
		int xCoor = 0;
		float X = getWidth() / 2 + getX() - boardX;
		while (X > 0) {
			X -= getWidth();
			xCoor++;
		}
		xCoor--;
		float snapX = xCoor * getWidth() + boardX;
		setX(snapX);

		if (textureX == xCoor)
			solvedX = true;
		else
			solvedX = false;
	}

	public void snapVertically() {
		int yCoor = 0;
		float Y = getHeight() / 2 + getY() - boardY;
		while (Y > 0) {
			Y -= getHeight();
			yCoor++;
		}
		yCoor--;
		float snapY = yCoor * getHeight() + boardY + 2;
		setY(snapY);

		if (Settings.getPuzzleSize() - textureY == yCoor + 1)
			solvedY = true;
		else
			solvedY = false;
		System.out.println("snap " + (yCoor + 1) + " first "
				+ (Settings.getPuzzleSize() - textureY));
	}
	
	public void setInMenuPosition(float f, float g){
		XpositionInmenu = f;
		YpositionInmenu = g;
	}
	
	public void setInMenuSize(float size) {
		this.inMenuSize = size;
		setX(getX() + (getWidth() - size)/2);
		setY(getY() + (getHeight() - size)/2);
		setInMenuPosition(getX(), getY());
		//setSize(size, size);
	}
	
	public int getTextureX() {
		return textureX;
	}
	
	public int getTextureY() {
		return textureY;
	}
	
	public int getPieceSize() {
		return pieceSize;
	}
	
	public int getBoardX() {
		return boardX;
	}
	
	public int getBoardY() {
		return boardY;
	}
	
	public float getInMenuSize() {
		return inMenuSize;
	}
	
	public float getXpositionInmenu() {
		return XpositionInmenu;
	}
	
	public float getYpositionInmenu() {
		return YpositionInmenu;
	}
	
	public void setPieceSize(int pieceSize) {
		this.pieceSize = pieceSize;
	}
	public void setBoardXY(int boardX,int boardY) {
		this.boardX = boardX;
		this.boardY = boardY;
	}
	
	public void setXYpositionInmenu(float xpositionInmenu,float ypositionInmenu) {
		XpositionInmenu = xpositionInmenu;
		YpositionInmenu = ypositionInmenu;
	}
}
