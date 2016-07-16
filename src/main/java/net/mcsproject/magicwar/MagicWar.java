package net.mcsproject.magicwar;

import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import net.mcsproject.magicwar.db.MongoDB;
import net.mcsproject.magicwar.game.commands.ForceStartCommand;
import net.mcsproject.magicwar.game.commands.StatsCommand;
import net.mcsproject.magicwar.game.listener.ChatListener;
import net.mcsproject.magicwar.game.listener.JoinLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicWar extends JavaPlugin {
	@Getter
	private MongoDatabase database;

	@Getter
	private static MagicWar instance;

	public void onEnable() {
		instance = this;

		MongoDB mongodb = new MongoDB("127.0.0.1", 27017, "test");
		mongodb.connect();

		database = mongodb.getDatabase();
	}

	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
	}

	private void registerCommands() {
		this.getCommand("stats").setExecutor(new StatsCommand());
		this.getCommand("forcestart").setExecutor(new ForceStartCommand());
	}

	public void onDisable() {
		//TODO: DB disconnect
	}
}
