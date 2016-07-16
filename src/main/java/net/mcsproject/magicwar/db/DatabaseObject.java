package net.mcsproject.magicwar.db;

import org.bson.Document;

public abstract class DatabaseObject {

	public abstract Document writeToDocument();

	public abstract void readDocument(Document document);

}
