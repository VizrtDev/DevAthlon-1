package net.mcsproject.magicwar;

import lombok.Getter;
import net.mcsproject.magicwar.db.MongoDB;
import net.mcsproject.magicwar.game.GamePhase;
import net.mcsproject.magicwar.game.commands.ForceStartCommand;
import net.mcsproject.magicwar.game.commands.StatsCommand;
import net.mcsproject.magicwar.game.listener.ChatListener;
import net.mcsproject.magicwar.game.listener.LoginListener;
import net.mcsproject.magicwar.game.listener.ingame.ItemPickupListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MagicWar extends JavaPlugin {
	@Getter
	private MongoDB mongodb;

	@Getter
	private static MagicWar instance;

	@Getter
	private GamePhase gamePhase;

	public void setGamePhase(GamePhase gamePhase) {
		if (this.gamePhase != null) {
			this.gamePhase.getCountdown().stop();
		}
		this.gamePhase = gamePhase;
		this.gamePhase.getCountdown();
	}

	public void onEnable() {
		instance = this;

		getDataFolder().mkdir();
		getConfig().options().copyDefaults(true);
		saveConfig();

		// mongodb = new MongoDB("127.0.0.1", 27017, "test");
		// mongodb.connect();

		registerCommands();
		registerListener();

		this.setGamePhase(GamePhase.LOBBY);

		Bukkit.getWorlds().get(0).setPVP(false);
		Bukkit.getWorlds().get(0).setDifficulty(Difficulty.PEACEFUL);
		Bukkit.getWorlds().get(0).setGameRuleValue("doDaylightCycle", "false");
	}

	private void registerListener() {
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new LoginListener(), this);
		Bukkit.getPluginManager().registerEvents(new ItemPickupListener(), this);
	}

	private void registerCommands() {
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("forcestart").setExecutor(new ForceStartCommand());
	}

	public void onDisable() {
		//mongodb.disconnect();
		Bukkit.unloadWorld("MagicalWorld", false);
		new File("MagicalWorld").delete();
	}
}
