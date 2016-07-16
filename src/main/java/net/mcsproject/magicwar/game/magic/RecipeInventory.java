package net.mcsproject.magicwar.game.magic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecipeInventory {

	private List<Inventory> recipeInvs = new ArrayList<>();

	private ItemStack placeholder = new ItemStack(Material.)

	public RecipeInventory(List<MagicRecipes> magicRecipes) {
		magicRecipes.forEach(r -> {
			Inventory inventory = Bukkit.createInventory(null, 9 * 3)
		});
	}

	public void open(Player p, int page) {
		if (this.recipeInvs.size() == page) {
			return;
		}
		p.openInventory(this.recipeInvs.get(page));
	}

}
