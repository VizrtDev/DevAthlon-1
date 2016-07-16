package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.game.Countdown;
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
	public void onInit() {

	}

	@Override
	public void onStart() {
		WorldCreator wc = new WorldCreator("MagicalWorld");
		wc.environment(World.Environment.NORMAL);
		wc.generateStructures(false);
		wc.seed(); //TODO: Own generator
		World magicalworld = Bukkit.createWorld(wc);
		magicalworld.setPVP(true);
		magicalworld.setDifficulty(Difficulty.NORMAL);
		magicalworld.setTime(14000);
		magicalworld.setGameRuleValue("doDaylightCycle", "false");
	}

	@Override
	public void onEnd() {

	}

}
