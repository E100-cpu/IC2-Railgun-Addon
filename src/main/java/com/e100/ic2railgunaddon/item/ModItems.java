package com.e100.ic2railgunaddon.item;

import com.e100.ic2railgunaddon.RailGun;
import com.e100.ic2railgunaddon.item.custom.RailGunItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, RailGun.MODID);
	
	public static final RegistryObject<Item> CORE = ITEMS.register("core", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> BARREL = ITEMS.register("barrel", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> STOCK = ITEMS.register("stock", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> MAG_RAIL = ITEMS.register("mag_rail", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> JACKET = ITEMS.register("jacket", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> PENETRATOR = ITEMS.register("penetrator", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> RAILGUNROUND = ITEMS.register("railgunround", 
			() -> new Item(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	public static final RegistryObject<Item> RAILGUN = ITEMS.register("railgun", 
            () -> new RailGunItem(new Item.Properties().tab(ModCreativeModeTab.IC2CRAILGUNADDON_TAB)));
	
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

}
