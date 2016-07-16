package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.ingame.InteractListener;
import net.mcsproject.magicwar.game.listener.ingame.PlayerDeathListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IngameCountdown extends Countdown {

	public IngameCountdown() {
		super(720);
	}

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onStart() {
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), MagicWar.getInstance());
		Bukkit.getPluginManager().registerEvents(new InteractListener(), MagicWar.getInstance());

		Bukkit.getOnlinePlayers().forEach(player -> {
			player.getInventory().setItem(0, new ItemStack(Material.WOOD_SWORD));
			player.getInventory().setItem(1, new ItemStack(Material.WOOD, 10));
			player.getInventory().setItem(9, new ItemStack(Material.CAULDRON_ITEM));
			player.getInventory().setArmorContents(new ItemStack[]{
					new ItemStack(Material.LEATHER_HELMET),
					new ItemStack(Material.LEATHER_CHESTPLATE),
					new ItemStack(Material.LEATHER_LEGGINGS),
					new ItemStack(Material.LEATHER_BOOTS)
			});
		});
	}

	@Override
	public void onEnd() {

	}

}
