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
	private String username, password, dbName, host;

	@Getter
	private int port;

	@Getter
	private ExecutorService exec = Executors.newCachedThreadPool();

	public MongoDB(String host, int port, String dbName) {
		this.host = host;
		this.port = port;
		this.dbName = dbName;
	}

	public MongoDB(String host, int port, String dbName, String username, String password) {
		this(host, port, dbName);
		this.username = username;
		this.password = password;
	}

	public void connect() {
		this.exec.submit(() -> {
			if (username.isEmpty()) {
				this.client = new MongoClient(this.host, this.port);
			} else {
				this.client = new MongoClient(new ServerAddress(this.host, this.port), Collections.singletonList(MongoCredential.createCredential(this.username, this.dbName, this.password.toCharArray())));
			}
			this.database = this.client.getDatabase(this.dbName);
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
