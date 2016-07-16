package net.mcsproject.magicwar;

import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import net.mcsproject.magicwar.db.MongoDB;
import net.mcsproject.magicwar.game.GamePhase;
import net.mcsproject.magicwar.game.commands.ForceStartCommand;
import net.mcsproject.magicwar.game.commands.StatsCommand;
import net.mcsproject.magicwar.game.listener.ingame.ItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicWar extends JavaPlugin {
	private MongoDB mongodb;

	@Getter
	private MongoDatabase db;

	@Getter
	private static MagicWar instance;

	@Getter
	@Setter
	private GamePhase gamePhase;

	//private List<Listener> lobbyListeners = new Ar

	public void onEnable() {
		instance = this;

		getConfig().options().copyDefaults(true);

		mongodb = new MongoDB("127.0.0.1", 27017, "test");
		mongodb.connect();

		db = mongodb.getDatabase();

		registerListeners();
		registerCommands();

		this.setGamePhase(GamePhase.LOBBY);
		this.getGamePhase().getCountdown();

		Bukkit.getWorlds().get(0).setPVP(false);
		Bukkit.getWorlds().get(0).setDifficulty(Difficulty.PEACEFUL);
		Bukkit.getWorlds().get(0).setGameRuleValue("doDaylightCycle", "false");
	}

	private void registerListeners() {
		//test
		this.getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
	}

	private void registerCommands() {
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("forcestart").setExecutor(new ForceStartCommand());
	}

	public void onDisable() {
		mongodb.disconnect();
	}
}
