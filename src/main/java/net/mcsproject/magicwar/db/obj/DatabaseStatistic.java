package net.mcsproject.magicwar.db.obj;

import lombok.Data;
import net.mcsproject.magicwar.db.DatabaseObject;
import org.bson.Document;

@Data
public class DatabaseStatistic extends DatabaseObject {

	private String uuid;
	private int kills;
	private int deaths;
	private int wins;
	private int playedGames;

	@Override
	public Document writeToDocument() {
		return new Document("uuid", this.uuid)
				.append("kills", this.kills)
				.append("deaths", this.deaths)
				.append("wins", this.wins)
				.append("playedGames", this.playedGames);
	}

	@Override
	public void readDocument(Document document) {
		this.uuid = document.getString("uuid");
		this.kills = document.getInteger("kills");
		this.deaths = document.getInteger("deaths");
		this.wins = document.getInteger("wins");
		this.playedGames = document.getInteger("playedGames");
	}

	public Document getInsertDocument(AccessRule... rules) {
		Document document = new Document("uuid", this.uuid);
		for (AccessRule rule : rules) {
			switch (rule) {
				case KILLS:
					document.append("kills", this.kills);
					break;
				case DEATHS:
					document.append("deaths", this.deaths);
					break;
				case WINS:
					document.append("wins", this.wins);
					break;
				case PLAYED_GAMES:
					document.append("playedGames", this.playedGames);
					break;
			}
		}
		return document;
	}

	public enum AccessRule {
		KILLS, DEATHS, WINS, PLAYED_GAMES;
	}

}
