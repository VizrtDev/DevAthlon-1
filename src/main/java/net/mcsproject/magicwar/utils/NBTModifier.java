package net.mcsproject.magicwar.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTModifier {

	private Object nmsItemStack;
	private Object tagCompound;

	private final String VERSION;

	private Class<?> getCraftItemStackClass() {
		try {
			return Class.forName("org.bukkit.craftbukkit." + this.VERSION + ".inventory.CraftItemStack");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNewTagCompound() {
		try {
			return Class.forName("net.minecraft.server." + this.VERSION + ".NBTTagCompound").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNMSItemStack(ItemStack itemStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
			return asNMSCopy.invoke(craftItemStack, itemStack);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ItemStack getBukkitItemStack(Object nmsStack) {
		Class<?> craftItemStack = this.getCraftItemStackClass();
		try {
			Method asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", ItemStack.class);
			return (ItemStack) asBukkitCopy.invoke(craftItemStack, nmsStack);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object getNBTTagCompound(Object nmsItem) {
		Class<?> nmsItemClass = nmsItem.getClass();
		try {
			Method getTag = nmsItemClass.getMethod("getTag");
			return getTag.invoke(nmsItem);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Object setNBTTagCompound(Object nmsItem, Object tag) {
		Method setTag;
		try {
			setTag = nmsItem.getClass().getMethod("setTag", tag.getClass());
			setTag.invoke(nmsItem, tag);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return nmsItem;
	}

	public NBTModifier(ItemStack itemStack) {
		this.VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

		this.nmsItemStack = this.getNMSItemStack(itemStack);
		this.tagCompound = this.getNBTTagCompound(this.nmsItemStack);

		if (this.tagCompound == null) {
			this.tagCompound = this.getNewTagCompound();
		}
	}

}
