package net.mcsproject.magicwar.game.magic;

import org.bukkit.Location;

import java.util.HashMap;

public class MagicCauldron {

	private static MagicCauldron instance;

	public static MagicCauldron getInstance() {
		if (instance == null) {
			instance = new MagicCauldron();
		}
		return instance;
	}

	private HashMap<Location, Byte> values = new HashMap<>();

	public void addValue(Location location, int value) {
		if (values.containsKey(location)) {
			value += values.get(location);
		}
		values.put(location, (byte) value);
	}

	public int getValue(Location location) {
		return values.get(location);
	}

	public void removeCauldron(Location location) {
		values.remove(location);
	}

}
