package net.mcsproject.magicwar.game.phases;

import net.mcsproject.magicwar.MagicWar;
import net.mcsproject.magicwar.game.Countdown;
import net.mcsproject.magicwar.game.listener.lobby.BlockBreakListener;
import net.mcsproject.magicwar.game.listener.lobby.EntityDamageListener;
import net.mcsproject.magicwar.game.listener.lobby.ItemDropListener;
import net.mcsproject.magicwar.game.listener.pregame.PlayerMoveListener;
import net.mcsproject.magicwar.game.magic.MagicalItems;
import net.mcsproject.magicwar.game.magic.items.MagicBook;
import net.mcsproject.magicwar.utils.ChatUtils;
import net.mcsproject.magicwar.utils.ItemModifier;
import net.mcsproject.magicwar.utils.ListenerBundle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PregameCountdown extends Countdown {

	public PregameCountdown() {
		super(10, true);
	}

	private ListenerBundle bundle;

	@Override
	public void sendMessage() {
		// TODO Messages @ilou
	}

	@Override
	public void onInit() {
		this.bundle = new ListenerBundle(new PlayerMoveListener(), new BlockBreakListener(), new ItemDropListener(), new EntityDamageListener());
		this.bundle.register();
	}

	@Override
	public void onStart() {
		MagicBook book = new MagicBook(MagicalItems.getInstance().getItemMap());
		Bukkit.getPluginManager().registerEvents(book, MagicWar.getInstance());

		Bukkit.broadcastMessage(ChatUtils.fromConfig("gamestart"));
		Bukkit.getOnlinePlayers().forEach(p -> {
			PlayerInventory inv = p.getInventory();
			inv.setItem(0, new ItemModifier(new ItemStack(Material.WOOD_SWORD)).valueLore().get());
			inv.setItem(7, book.getItem());
			inv.setItem(8, new ItemModifier(new ItemStack(Material.CAULDRON_ITEM)).name("Zauberkessel").valueLore().get());
			inv.setItem(2, new ItemModifier(new ItemStack(Material.WOOD, 20)).valueLore().get());
			inv.setItem(1, new ItemModifier(new ItemStack(Material.IRON_PICKAXE)).valueLore().get());

			inv.setArmorContents(new ItemStack[]{
					new ItemStack(Material.LEATHER_BOOTS),
					new ItemStack(Material.LEATHER_LEGGINGS),
					new ItemStack(Material.LEATHER_CHESTPLATE),
					new ItemStack(Material.LEATHER_HELMET)
			});
		});
	}


	@Override
	public void onEnd() {
		this.bundle.unregister();
	}

}
