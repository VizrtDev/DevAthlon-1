package net.mcsproject.magicwar.game.magic;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MagicRecipes {

	private static MagicRecipes instance;

	private List<MagicRecipe> recipes;

	public static MagicRecipes getInstance() {
		if (instance == null) instance = new MagicRecipes();
		return instance;
	}

	private MagicRecipes() {
		this.recipes = new ArrayList<>();
	}

	public void addRecipe(MagicRecipe recipe) {
		this.recipes.add(recipe);
	}

	public MagicRecipe getRecipeWithIngredients(HashMap<ItemStack, Integer> items) {
		return this.recipes.stream().filter(r -> r.getIngredients().equals(items)).findFirst().orElse(null);
	}

}
