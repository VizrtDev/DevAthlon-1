package net.mcsproject.magicwar.utils;

import net.mcsproject.magicwar.game.magic.ItemValues;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemModifier {

	private ItemStack itemStack;

	public ItemModifier(ItemStack itemStack) {
		this.itemStack = itemStack;
	}

	public ItemModifier name(String name) {
		ItemMeta meta = this.itemStack.getItemMeta();
		meta.setDisplayName(name);
		this.itemStack.setItemMeta(meta);
		return this;
	}

	public ItemModifier enchant(Enchantment enchantment, int level) {
		ItemMeta meta = this.itemStack.getItemMeta();
		meta.addEnchant(enchantment, level, true);
		this.itemStack.setItemMeta(meta);
		return this;
	}

	public ItemModifier lore(String... lore) {
		ItemMeta meta = this.itemStack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		this.itemStack.setItemMeta(meta);
		return this;
	}

	public ItemModifier valueLore() {
		return this.lore("Wert§7: §e" + ItemValues.valueOf(this.itemStack.getType().toString()).getValue());
	}

	public ItemStack get() {
		return this.itemStack;
	}

}
