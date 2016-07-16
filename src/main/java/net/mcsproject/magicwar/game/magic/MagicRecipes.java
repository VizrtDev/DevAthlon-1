package net.mcsproject.magicwar.game.magic;

import java.util.ArrayList;
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

}
