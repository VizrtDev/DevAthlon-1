package net.mcsproject.magicwar.game.magic;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.magic.items.AbsorptionPowder;
import net.mcsproject.magicwar.game.magic.items.HealingPoint;
import net.mcsproject.magicwar.game.magic.items.WitchsHead;
import org.bukkit.Bukkit;

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
		this.addItem(25, new HealingPoint());
		this.addItem(50, new AbsorptionPowder());
		this.addItem(75, new WitchsHead());
	}

	public void addItem(int value, MagicalItem item) {
		this.map.put(value, item);
		Bukkit.getPluginManager().registerEvents(item, MagicWar.getInstance());
	}

	public MagicalItem getByValue(int value) {
		return this.map.get(value);
	}

}
