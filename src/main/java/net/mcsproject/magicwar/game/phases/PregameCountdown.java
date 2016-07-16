package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.lobby.EntityDamageListener;
import net.mcsproject.magicwar.game.listener.lobby.WeatherChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class PregameCountdown extends Countdown {

	public PregameCountdown() {
		super(600);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onStart() {
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), MagicWar.getInstance());

		WorldCreator wc = new WorldCreator("MagicalWorld");
		wc.environment(World.Environment.NORMAL);
		wc.generateStructures(false);
		wc.seed(); //TODO: Own generator
		World magicalworld = Bukkit.createWorld(wc);
		magicalworld.setPVP(true);
		magicalworld.setDifficulty(Difficulty.NORMAL);
		magicalworld.setTime(14000);
	}

	@Override
	public void onEnd() {

	}

}
