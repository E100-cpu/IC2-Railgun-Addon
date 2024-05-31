package com.e100.ic2railgunaddon.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
	public static final CreativeModeTab IC2CRAILGUNADDON_TAB = new CreativeModeTab("ic2crailgunaddontab") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItems.BARREL.get());
		}
	};

}
