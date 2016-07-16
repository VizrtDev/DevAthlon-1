package net.mcsproject.magicwar.db;

import com.mongodb.client.MongoDatabase;
import net.mcsproject.magicwar.MagicWar;
import org.bukkit.Bukkit;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class DatabaseConnection {

	private MongoDatabase db;
	private ExecutorService exec;

	public DatabaseConnection(MongoDB mongoDB) {
		this.db = mongoDB.getDatabase();
		this.exec = mongoDB.getExec();
	}

	private void async(Runnable runnable) {
		this.exec.submit(runnable);
	}

	private void sync(Runnable runnable) {
		Bukkit.getScheduler().runTask(MagicWar.getInstance(), runnable);
	}

	private <T> void sync(Consumer<T> consumer, T param) {
		Bukkit.getScheduler().runTask(MagicWar.getInstance(), () -> consumer.accept(param));
	}

	// IMPLEMENTATIONS OF GETTERS AND SETTERS OF DATABASE OBJECTS

}
