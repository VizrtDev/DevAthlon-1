package net.mcsproject.magicwar.game.magic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class MagicRecipe {

	@Getter
	private int requiredValue;

	@Getter
	private ItemStack result;

}
