package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class HealingPoint extends MagicalItem {

	public HealingPoint() {
		super(new ItemModifier(new ItemStack(Material.END_ROD)).name("§cHealing Point").lore("Erzeugt einen Heilkreis").get());
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlockPlaced().getType() == Material.END_ROD) {
			Bukkit.getScheduler().runTaskTimer(MagicWar.getInstance(), () -> {
				Location middle = e.getBlockPlaced().getLocation();
				double inc = (2 * Math.PI) / 20;
				for (int i = 0; i < 20; i++) {
					double angle = i * inc;
					e.getBlockPlaced().getWorld().spawnParticle(Particle.FIREWORKS_SPARK, middle.getX() + (4 * Math.cos(angle)), middle.getY(), middle.getZ() + (4 * Math.sin(angle)), 3);
				}
			}, 0L, 5L);
		}
	}

}
