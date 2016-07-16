package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.lobby.EntityDamageListener;
import net.mcsproject.magicwar.game.listener.lobby.WeatherChangeListener;
import org.bukkit.Bukkit;

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
	}

	@Override
	public void onEnd() {

	}

}
