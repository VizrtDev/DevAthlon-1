package net.mcsproject.magicwar.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MongoDB {

	@Getter
	private MongoDatabase database;
	private MongoClient client;

	@Getter
	private String username = null;
	@Getter
	private String password = null;
	@Getter
	private String dbName;
	@Getter
	private String host;
	@Getter
	private int port;

	@Getter
	private ExecutorService exec;

	@Getter
	private DatabaseConnection connection;

	public MongoDB(String host, int port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;

		this.connection = new DatabaseConnection(this);

		this.exec = Executors.newCachedThreadPool();
	}

	public MongoDB(String host, int port, String dbName, String username, String password) {
		this(host, port, dbName);
		this.username = username;
		this.password = password;
	}

	public void connect() {
		System.out.println("Connecting...");
		this.exec.submit(() -> {
			System.out.println("test");
			if (username.equals(null)) {
				System.out.println("without credentials");
				this.client = new MongoClient(this.host, this.port);
			} else {
				System.out.println("with credentials");
				this.client = new MongoClient(new ServerAddress(this.host, this.port), Collections.singletonList(MongoCredential.createCredential(this.username, this.dbName, this.password.toCharArray())));
			}
			this.database = this.client.getDatabase(this.dbName);
			System.out.println("Connected to database!");
		});
	}

	public void disconnect() {
		try {
			this.exec.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.client.close();
	}

}
