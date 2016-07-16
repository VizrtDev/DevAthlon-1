package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HealingPoint extends MagicalItem {

	public HealingPoint() {
		super(new ItemModifier(new ItemStack(Material.END_ROD)).name("§cHealing Point").lore("Erzeugt einen Heilkreis").get());
	}

}
