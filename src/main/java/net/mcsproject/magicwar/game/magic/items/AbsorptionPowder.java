package net.mcsproject.magicwar.game.magic.items;

import net.mcsproject.magicwar.game.magic.MagicalItem;
import net.mcsproject.magicwar.utils.ItemModifier;
import net.mcsproject.magicwar.utils.NBTModifier;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbsorptionPowder extends MagicalItem {

	public AbsorptionPowder() {
		super(new NBTModifier(new ItemModifier(new ItemStack(Material.INK_SACK, 1, (short) DyeColor.YELLOW.getDyeData()))
				.name("§eAbsorption-Powder").lore("Wert§7: §e50").get()).setInteger("item-id", 1).modify());
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getItem() == null)
			return;

		Integer i = new NBTModifier(e.getItem()).getInteger("item-id");
		if (i == null || i != 1) {
			return;
		}

		e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 10, 1);
		if (e.getItem().getAmount() == 1) {
			e.getPlayer().getInventory().setItemInMainHand(null);
		} else {
			e.getItem().setAmount(e.getItem().getAmount() - 1);
		}
		e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 1));
	}

}
