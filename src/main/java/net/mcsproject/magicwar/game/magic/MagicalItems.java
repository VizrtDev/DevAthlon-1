package net.mcsproject.magicwar.game.magic;

import lombok.Getter;
import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.magic.items.*;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class MagicalItems {

	@Getter
	private HashMap<Integer, MagicalItem> itemMap = new HashMap<>();

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
		this.addItem(55, new SpeedPowder());
		this.addItem(60, new RegenerationPowder());
		this.addItem(65, new LightningEgg());
		this.addItem(75, new WitchsHead());
	}

	private void addItem(int value, MagicalItem item) {
		this.itemMap.put(value, item);
		Bukkit.getPluginManager().registerEvents(item, MagicWar.getInstance());
	}

	public MagicalItem getByValue(int value) {
		return this.itemMap.get(value);
	}

}
