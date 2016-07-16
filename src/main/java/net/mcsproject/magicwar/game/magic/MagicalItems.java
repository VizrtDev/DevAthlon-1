package net.mcsproject.magicwar.game.magic;

import net.mcsproject.magicwar.game.magic.items.AbsorptionPowder;
import net.mcsproject.magicwar.game.magic.items.HealingPoint;
import net.mcsproject.magicwar.game.magic.items.WitchsHead;

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
		this.map.put(25, new HealingPoint());
		this.map.put(50, new AbsorptionPowder());
		this.map.put(75, new WitchsHead());
	}

	public MagicalItem getByValue(int value) {
		return this.map.get(value);
	}

}
