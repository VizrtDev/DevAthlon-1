package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ingame.InteractListener;
import net.mcsproject.magicwar.game.listener.ingame.PlayerDeathListener;
import org.bukkit.Bukkit;

public class IngameCountdown extends Countdown {

	public IngameCountdown() {
		super(720);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onStart() {
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new InteractListener(), MagicWar.getInstance());
	}

	@Override
	public void onEnd() {

	}

}
