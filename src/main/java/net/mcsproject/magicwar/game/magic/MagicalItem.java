package net.mcsproject.magicwar.game.magic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public abstract class MagicalItem implements Listener {
	@Getter
	private ItemStack item;
}
