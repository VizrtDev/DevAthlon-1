package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Material;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WitchsHead extends MagicalItem {

	public WitchsHead() {
		super(new ItemModifier(new ItemStack(Material.SKULL_ITEM)).name("§cWitch's Head").lore("Schießt einen Witherkopf").get());
	}

	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		if (e.getItem().getItemMeta().getDisplayName().equals("§cWitch's Head")) {
			//e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WITHER_SKULL);
			e.getPlayer().launchProjectile(WitherSkull.class);
		}
	}

}
