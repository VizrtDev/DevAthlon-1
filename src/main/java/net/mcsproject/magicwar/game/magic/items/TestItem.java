package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class TestItem extends MagicalItem {

	public TestItem() {
		super(new ItemModifier(new ItemStack(Material.STICK)).enchant(Enchantment.KNOCKBACK, 5).name("§cDer Stock des Todes!!!").get());
	}

}
