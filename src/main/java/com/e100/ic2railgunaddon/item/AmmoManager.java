package com.e100.ic2railgunaddon.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;

public class AmmoManager {
	public static ItemStack findAmmo(Player player) {
        for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
            ItemStack itemstack = player.getInventory().getItem(i);
            if (isAmmo(itemstack)) {
                return itemstack;
            }
        }
        return ItemStack.EMPTY;
    }

    public static boolean isAmmo(ItemStack stack) {
        return stack.is(ModItems.RAILGUNROUND.get()); // Extend this to include other ammo types
    }

}
