package net.mcsproject.magicwar.game.magic;

import net.mcsproject.magicwar.game.magic.items.TestItem;

import java.util.HashMap;

public class MagicalItems {

	private HashMap<Integer, MagicalItem> map = new HashMap<>();

	private static MagicalItems instance;

	public static MagicalItems getInstance() {
		if (instance == null) {
			instance = new MagicalItems();
		}
		return instance;
	}

	private MagicalItems() {
		this.map.put(25, new TestItem());
	}

	public MagicalItem getByValue(int value) {
		return this.map.get(value);
	}

}
