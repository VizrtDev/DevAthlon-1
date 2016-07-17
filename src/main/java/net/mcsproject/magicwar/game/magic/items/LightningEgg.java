package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import net.mcsproject.magicwar.utils.NBTModifier;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class LightningEgg extends MagicalItem {

	private List<Player> shot = new ArrayList<>();

	public LightningEgg() {
		super(new NBTModifier(new ItemModifier(new ItemStack(Material.EGG, 1))
				.name("Lightning-Egg").lore("Wert§7: §e65").get()).setInteger("item-id", 4).modify());
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getItem() == null)
			return;

		Integer i = new NBTModifier(e.getItem()).getInteger("item-id");
		if (i == null || i != 4) {
			return;
		}

		shot.add(e.getPlayer());
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		if (event.getEntityType() == EntityType.EGG) {
			if (event.getEntity().getShooter() instanceof Player) {
				Player p = (Player) event.getEntity().getShooter();
				if (shot.contains(p)) {
					event.getEntity().getWorld().spigot().strikeLightning(event.getEntity().getLocation(), false);
					shot.remove(p);
				}
			}
		}
	}

}
