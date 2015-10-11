package sk.kebapp.puzzle.play;

import sk.kebapp.puzzle.Puzzle;
import sk.kebapp.puzzle.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EmpyPlace extends Actor {

	private int boardX;
	private int boardY;
	private Texture texture;
	private Puzzle gamee;
	private float scretch = .9f;

	public EmpyPlace(int x, int y, Texture image, Puzzle game) {
		this.gamee = game;
		texture = image;
		setSize(Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1),
				Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1));

		boardY = (Gdx.graphics.getHeight() / (Settings.getPuzzleSize() + 1) / 2);
		boardX = (Gdx.graphics.getWidth() - Gdx.graphics.getHeight()) / 2
				+ boardY;

		setPosition(x * getWidth() + boardX, y * getHeight() + boardY);
		setY(Gdx.graphics.getHeight() - getY() - 2 * boardY);

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX() + getWidth() * ((1 - scretch) / 2), getY()
				/*+ getHeight() * ((1 - scretch) / 4)*/, getWidth() * scretch,
				getHeight() * scretch);
	}

}
