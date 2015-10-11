package sk.kebapp.puzzle.screens;

import sk.kebapp.puzzle.Puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

	private Puzzle puzzle;
	private Stage stage;
	private TextButton play;
	private TextButton settings;
	private TextButton continueButton;
	private FreeTypeFontGenerator ftfg;
	private Label banner;

	public MenuScreen(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	@Override
	public void show() {
		if (stage == null) {
			stage = new Stage();

			ftfg = new FreeTypeFontGenerator(
					Gdx.files.internal("data/font.ttf"));
			TextButtonStyle bs = new TextButtonStyle();
			bs.font = ftfg.generateFont(Gdx.graphics.getWidth() / 15);

			this.play = new TextButton("New Game", bs);
			this.continueButton = new TextButton("Continue", bs);
			this.settings = new TextButton("Settings", bs);

			settings.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					puzzle.setScreen(puzzle.getSettings());
					System.out.println("settings");
				}
			});

			play.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					puzzle.setScreen(puzzle.getPlay());
					System.out.println("play");
				}
			});

			LabelStyle ls = new LabelStyle();
			ls.font = ftfg.generateFont(Gdx.graphics.getWidth() / 12);
			banner = new Label("Puzzle", ls);

			banner.setPosition(Gdx.graphics.getWidth() / 2 - banner.getWidth()/2,
					Gdx.graphics.getHeight() - banner.getHeight() * 1.5f);

			continueButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					puzzle.setScreen(puzzle.getPlay());
					puzzle.getPlay().loadGame();
					super.clicked(event, x, y);
				}
			});

			continueButton.setPosition(Gdx.graphics.getWidth() / 2
					- continueButton.getWidth() / 2,
					Gdx.graphics.getHeight() / 2);
			play.setPosition(Gdx.graphics.getWidth() / 2 - play.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 - play.getHeight());
			settings.setPosition(
					Gdx.graphics.getWidth() / 2 - settings.getWidth() / 2,
					play.getY() - play.getHeight());
		}

		Gdx.input.setInputProcessor(stage);
		stage.addActor(play);
		stage.addActor(continueButton);
		stage.addActor(settings);
		stage.addActor(banner);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.0f, .9f, .6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
	}

	public FreeTypeFontGenerator getFtfg() {
		return ftfg;
	}

	@Override
	public void resize(int width, int height) {

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

}
