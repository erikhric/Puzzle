package sk.kebapp.puzzle.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {

	private TextButton menuButton;
	private TextButton defaultButton;
	private Stage stage;
	private Puzzle game;
	private TextField filePath;
	private Label customImage;

	public SettingsScreen(Puzzle puzzle) {
		this.game = puzzle;
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

					try {
						Settings.setImage(new Texture(Gdx.files
								.absolute(filePath.getText())));
					} catch (Exception w) {

					}

					super.clicked(event, x, y);
				}
			});

			LabelStyle ls = new LabelStyle();
			ls.font = bs.font;

			customImage = new Label("Custom image:", ls);
			

			defaultButton = new TextButton("Default image", bs);
			defaultButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					Settings.setImage(new Texture("data/default.jpeg"));
					super.clicked(event, x, y);
				}
			});
			defaultButton.setPosition(Gdx.graphics.getWidth() / 2
					- defaultButton.getWidth() / 2, Gdx.graphics.getHeight()
					/ 2 - defaultButton.getHeight());

			TextFieldStyle ts = new TextFieldStyle();
			ts.font = ftfg.generateFont(Gdx.graphics.getWidth() / 25);
			ts.fontColor = Color.WHITE;

			filePath = new TextField(System.getProperty("user.home"), ts);
			filePath.setWidth(Gdx.graphics.getWidth() / 2);
			filePath.setPosition(
					Gdx.graphics.getWidth() / 2 - filePath.getWidth() / 2,
					Gdx.graphics.getHeight() / 2);

		}
		customImage.setPosition(
				Gdx.graphics.getWidth() / 2 - customImage.getWidth() / 2,
				filePath.getHeight() + filePath.getY());
		filePath.setText(System.getProperty("user.home"));
		menuButton.setPosition(
				Gdx.graphics.getWidth() / 2 - menuButton.getWidth() / 2,
				menuButton.getHeight());
		Gdx.input.setInputProcessor(stage);
		stage.addActor(menuButton);
		stage.addActor(filePath);
		stage.addActor(defaultButton);
		stage.addActor(customImage);
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
