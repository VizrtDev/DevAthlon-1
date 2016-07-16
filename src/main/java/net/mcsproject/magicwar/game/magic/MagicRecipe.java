package net.mcsproject.magicwar.game.magic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

@AllArgsConstructor
public class MagicRecipe {

	@Getter
	private HashMap<ItemStack, Integer> ingredients = new HashMap<>();

	@Getter
	private ItemStack result;

}
