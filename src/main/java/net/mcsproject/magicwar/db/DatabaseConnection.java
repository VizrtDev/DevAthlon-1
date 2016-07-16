package net.mcsproject.magicwar.db;

import com.mongodb.CursorType;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.db.obj.DatabasePlayer;
import net.mcsproject.magicwar.db.obj.DatabaseStatistic;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class DatabaseConnection {

	private MongoDatabase db;
	private ExecutorService exec;

	private final UpdateOptions UPSERT = new UpdateOptions().upsert(true);

	public DatabaseConnection(MongoDB mongoDB) {
		this.db = mongoDB.getDatabase();
		this.exec = mongoDB.getExec();
	}

	private void async(Runnable runnable) {
		this.exec.submit(runnable);
	}

	private <T> void sync(Consumer<T> consumer, T param) {
		Bukkit.getScheduler().runTask(MagicWar.getInstance(), () -> consumer.accept(param));
	}

	// IMPLEMENTATIONS OF GETTERS AND SETTERS OF DATABASE OBJECTS

	public void updateStatistic(DatabaseStatistic statistic, DatabaseStatistic.AccessRule... rules) {
		this.async(() -> {
			MongoCollection<Document> collection = this.db.getCollection("stats");
			collection.updateOne(new Document("uuid", statistic.getUuid()), statistic.getInsertDocument(rules), UPSERT);
		});
	}

	public void getStatistic(String uuid, Consumer<DatabaseStatistic> callback) {
		this.async(() -> {
			MongoCollection<Document> collection = this.db.getCollection("stats");
			FindIterable<Document> fi = collection.find(new Document("uuid", uuid)).cursorType(CursorType.NonTailable);

			Document doc = fi.first();
			if (doc == null) {
				this.sync(callback, null);
				return;
			}
			DatabaseStatistic dbObj = new DatabaseStatistic();
			dbObj.readDocument(doc);
			this.sync(callback, dbObj);
		});
	}

	public void updatePlayer(DatabasePlayer player) {
		this.async(() -> {
			MongoCollection<Document> collection = this.db.getCollection("players");
			collection.updateOne(new Document("uuid", player.getUuid()), player.writeToDocument(), UPSERT);
		});
	}

	public void getPlayer(String name, Consumer<DatabasePlayer> callback) {
		this.async(() -> {
			MongoCollection<Document> collection = this.db.getCollection("players");
			FindIterable<Document> fi = collection.find(new Document("lastNameLowercase", name.toLowerCase())).cursorType(CursorType.NonTailable);

			Document doc = fi.first();
			if (doc == null) {
				this.sync(callback, null);
				return;
			}
			DatabasePlayer dbObj = new DatabasePlayer();
			dbObj.readDocument(doc);
			this.sync(callback, dbObj);
		});
	}

}
