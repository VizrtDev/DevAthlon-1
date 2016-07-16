package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import net.mcsproject.magicwar.utils.NBTModifier;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class SpeedPowder extends MagicalItem {

	public SpeedPowder() {
		super(new NBTModifier(new ItemModifier(new ItemStack(Material.INK_SACK, 1, (short) DyeColor.YELLOW.getDyeData()))
				.name("§bSpeed-Powder").lore("Wert§7: §e55").get()).setInteger("item-id", 2).modify());
	}

	@EventHandler
	public void onRightClick(org.bukkit.event.player.PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR || e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;

		Integer i = new NBTModifier(e.getItem()).getInteger("item-id");
		if (i == null || i != 2) {
			return;
		}

		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 10, 1);
		e.getPlayer().getItemOnCursor().setAmount(e.getPlayer().getItemOnCursor().getAmount() - 1);
		e.getPlayer().addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.SPEED, 1200, 1));
	}

}
