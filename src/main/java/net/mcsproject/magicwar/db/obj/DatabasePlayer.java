package net.mcsproject.magicwar.db.obj;

import lombok.Data;
import net.mcsproject.magicwar.db.DatabaseObject;
import org.bson.Document;

@Data
public class DatabasePlayer extends DatabaseObject {

	private String lastName;
	private String lastNameLowercase;
	private String uuid;

	@Override
	public Document writeToDocument() {
		return new Document("lastName", this.lastName)
				.append("lastNameLowercase", this.lastNameLowercase)
				.append("uuid", this.uuid);
	}

	@Override
	public void readDocument(Document document) {
		this.lastName = document.getString("lastName");
		this.lastNameLowercase = document.getString("lastNameLowercase");
		this.uuid = document.getString("uuid");
	}

}
