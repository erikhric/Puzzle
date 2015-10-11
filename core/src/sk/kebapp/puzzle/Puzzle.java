package sk.kebapp.puzzle;

import sk.kebapp.puzzle.screens.MenuScreen;
import sk.kebapp.puzzle.screens.SettingsScreen;
import sk.kebapp.puzzle.screens.PlayingScreen;

import com.badlogic.gdx.Game;

public class Puzzle extends Game {
	
	private MenuScreen menu;
	private PlayingScreen play;
	private SettingsScreen settings;
	
	public Puzzle() {
		menu = new MenuScreen(this);
		play = new PlayingScreen(this);
		settings = new SettingsScreen(this);
	}
	
	@Override
	public void create () {
		setScreen(menu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	public MenuScreen getMenu() {
		return menu;
	}
	
	public PlayingScreen getPlay() {
		return play;
	}
	
	public SettingsScreen getSettings() {
		return settings;
	}
}
