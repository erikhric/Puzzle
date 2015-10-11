package sk.kebapp.puzzle.screens;

import java.util.ArrayList;

import sk.kebapp.puzzle.Puzzle;
import sk.kebapp.puzzle.PuzzleShuffler;
import sk.kebapp.puzzle.Settings;
import sk.kebapp.puzzle.play.EmpyPlace;
import sk.kebapp.puzzle.play.PuzzlePiece;
import sk.kebapp.puzzle.play.SavedInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayingScreen implements Screen {

	private Puzzle puzzle;
	private Stage stage;
	private Texture image;
	private PuzzlePiece draggedPiece;
	private PuzzlePiece lastDraggedPiece;
	private ArrayList<PuzzlePiece> pieces;
	private long startTime;
	private Label time;
	private TextButton menuButton;

	public PlayingScreen(Puzzle puzzle) {
		this.puzzle = puzzle;

	}

	@Override
	public void show() {
		// if (stage == null) {
		stage = new Stage();
		if (Settings.getImage() == null) {
			image = new Texture("data/default.jpeg");
			Settings.setImage(image);
		} else {
			image = Settings.getImage();
		}
		pieces = new ArrayList<PuzzlePiece>();
		// }
		Gdx.input.setInputProcessor(stage);

		for (int i = 0; i < Settings.getPuzzleSize(); i++) {
			for (int j = 0; j < Settings.getPuzzleSize(); j++) {
				PuzzlePiece newPiece = new PuzzlePiece(i, j, image.getWidth()
						/ Settings.getPuzzleSize(), image, puzzle);
				stage.addActor(newPiece);
				pieces.add(newPiece);
				EmpyPlace ep = new EmpyPlace(i, j, image, puzzle);
				stage.addActor(ep);
			}
		}
		new PuzzleShuffler(pieces).shuffle();
		startTime = System.currentTimeMillis();
		LabelStyle ls = new LabelStyle(puzzle.getMenu().getFtfg()
				.generateFont(Gdx.graphics.getWidth() / 25), Color.WHITE);

		time = new Label("0:00:00", ls);
		time.setPosition(Gdx.graphics.getWidth() / 2 - time.getWidth() / 2,
				Gdx.graphics.getHeight() - time.getHeight() * .85f);

		TextButtonStyle bs = new TextButtonStyle();
		bs.font = ls.font;

		this.menuButton = new TextButton("Menu", bs);

		menuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				puzzle.setScreen(puzzle.getMenu());
			}

		});
		menuButton.setPosition(Gdx.graphics.getWidth() / 2 - time.getWidth()
				/ 2, 0);
		stage.addActor(time);
		stage.addActor(menuButton);

	}

	public void loadGame() {
		SavedInfo save = new SavedInfo(pieces, startTime);
		save.load(puzzle);
		startTime = save.getStartTime();
		stage.clear();
		for (int i = 0; i < Settings.getPuzzleSize(); i++) {
			for (int j = 0; j < Settings.getPuzzleSize(); j++) {

				EmpyPlace ep = new EmpyPlace(i, j, image, puzzle);
				stage.addActor(ep);
			}
		}
		pieces = save.getPieces();
		for (PuzzlePiece piece : pieces) {
			stage.addActor(piece);
		}
		stage.addActor(time);
		stage.addActor(menuButton);
		// stage.addActor(load);
	}

	public void save() {
		SavedInfo save = new SavedInfo(pieces, startTime);

		save.save();
	}

	public void setDraggedPiece(PuzzlePiece draggedPiece) {
		this.draggedPiece = draggedPiece;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.0f, .9f, .6f, .9f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		long milis = System.currentTimeMillis() - startTime;

		int seconds = (int) (milis / 1000) % 60;
		int minutes = (int) ((milis / (1000 * 60)) % 60);
		int hours = (int) ((milis / (1000 * 60 * 60)) % 24);
		time.setText(hours + ":"
				+ ((minutes < 10) ? 0 + "" + minutes : minutes) + ":"
				+ ((seconds < 10) ? 0 + "" + seconds : seconds));
		stage.draw();
		stage.act();
		if (draggedPiece != null) {
			stage.getBatch().begin();
			draggedPiece.draw(stage.getBatch(), 1);
			stage.getBatch().end();
			lastDraggedPiece = draggedPiece;
		} else if (lastDraggedPiece != null) {
			// lastDraggedPiece.snapToGrid();
			stage.addActor(lastDraggedPiece);
			lastDraggedPiece = null;
		}
	}

	public void checkSolved() {
		boolean allSolved = true;
		for (PuzzlePiece pp : pieces) {
			if (!pp.isOnRightPlace()) {
				allSolved = false;
				break;
			}
		}
		if (!allSolved)
			System.out.println("neboli na mieste");
		else {
			System.out.println("boli na mieste");
			puzzle.setScreen(new SolvedScreen(puzzle, time.getText().toString()));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Texture getImage() {
		return image;
	}

}
