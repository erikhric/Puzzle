package sk.kebapp.puzzle.screens;

import javafx.scene.control.MenuButton;
import sk.kebapp.puzzle.Puzzle;
import sk.kebapp.puzzle.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.StringBuilder;

public class SolvedScreen implements Screen {

	private TextButton menuButton;
	private Stage stage;
	private Puzzle game;
	private String time;
	private Label message;

	public SolvedScreen(Puzzle puzzle, String time) {
		this.game = puzzle;
		this.time = time;
	}

	@Override
	public void show() {
		if (stage == null) {
			stage = new Stage();
			FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(
					Gdx.files.internal("data/font.ttf"));
			TextButtonStyle bs = new TextButtonStyle();
			bs.font = ftfg.generateFont(Gdx.graphics.getWidth() / 15);
			menuButton = new TextButton("Menu", bs);
			menuButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					game.setScreen(game.getMenu());

					super.clicked(event, x, y);
				}
			});

			LabelStyle ls = new LabelStyle();
			ls.font = bs.font;

			message = new Label("You have solved puzzle in\n                " + time, ls);
			message.setPosition(Gdx.graphics.getWidth() / 2 - message.getWidth() / 2,
					menuButton.getHeight()*2+ menuButton.getY());
			menuButton.setPosition(
					Gdx.graphics.getWidth() / 2 - menuButton.getWidth() / 2,
					menuButton.getHeight());
			Gdx.input.setInputProcessor(stage);
			stage.addActor(menuButton);
			stage.addActor(message);
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.0f, .9f, .6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		stage.act();
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

}
