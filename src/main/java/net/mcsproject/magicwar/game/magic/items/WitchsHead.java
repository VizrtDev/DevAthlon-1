package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
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
		if (e.getItem() == null) return;
		if (e.getItem().getItemMeta().getDisplayName().equals("§cWitch's Head")) {
			WitherSkull skull = (WitherSkull) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.WITHER_SKULL);
			skull.setShooter(e.getPlayer());
			skull.setDirection(e.getPlayer().getEyeLocation().getDirection().multiply(2));
			skull.setGlowing(true);

			e.getPlayer().getItemOnCursor().setAmount(e.getPlayer().getItemOnCursor().getAmount() - 1);
			e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 0);
		}
	}

}
