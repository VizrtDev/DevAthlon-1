package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ChatListener;
import net.mcsproject.magicwar.game.listener.JoinLeaveListener;
import net.mcsproject.magicwar.game.listener.lobby.BlockBreakListener;
import net.mcsproject.magicwar.game.listener.lobby.EntityDamageListener;
import net.mcsproject.magicwar.game.listener.lobby.WeatherChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public class LobbyCountdown extends Countdown {

	public LobbyCountdown() {
		super(60);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onInit() {
		Bukkit.getPluginManager().registerEvents(new JoinLeaveListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new ChatListener(), MagicWar.getInstance());

		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), MagicWar.getInstance());
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onEnd() {
		HandlerList.unregisterAll();
	}


}
